/*******************************************************************************
 * Copyright (c) 2011 The University of York.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Louis Rose - initial API and implementation
 ******************************************************************************/
package org.eclipse.epsilon.cli.domain;

import java.util.Arrays;
import java.util.Collection;

import org.eclipse.epsilon.cli.exceptions.CommandLineInterfaceException;
import org.eclipse.epsilon.cli.exceptions.ParsingFailedException;
import org.eclipse.epsilon.cli.exceptions.wrappers.ExecutionException;
import org.eclipse.epsilon.cli.exceptions.wrappers.ModelLoadingException;
import org.eclipse.epsilon.cli.exceptions.wrappers.ModelStorageException;
import org.eclipse.epsilon.cli.exceptions.wrappers.ParsingException;
import org.eclipse.epsilon.cli.interceptor.Interceptable;
import org.eclipse.epsilon.cli.interceptor.InterceptedResult;
import org.eclipse.epsilon.cli.interceptor.SystemErrInterceptor;
import org.eclipse.epsilon.eol.IEolExecutableModule;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;

public class Module {

	private final IEolExecutableModule module;
	private final Program program;
	private final Collection<Model> models;
	
	public Module(IEolExecutableModule module, Program program, Model... models) throws ModelLoadingException {
		this.module  = module;
		this.program = program;
		this.models  = Arrays.asList(models);
		
		addModelsToModule();
	}

	private void addModelsToModule() throws ModelLoadingException {
		for (Model model : models)
			model.addToRepository(module.getContext().getModelRepository());
	}

	public void execute() throws CommandLineInterfaceException {
		try {
			parse();
			module.execute();
			disposeModels();
		
		} catch (EolRuntimeException e) {
			throw new ExecutionException(program, e);
		}
	}

	private void parse() throws ParsingException, ParsingFailedException {
		// Currently, AbstractModule#parse uses e.printStackTrace()
		final InterceptedResult result = new SystemErrInterceptor().captureErrMessages(new Interceptable() {
			
			public void run() throws ParsingException {
				program.parseWith(module);
			}
		});
		
		if (!result.errors.isEmpty()) {
			throw new ParsingException(program, result.errors);
		}
		
		if (result.exception != null) {
			throw new ParsingException(program, result.exception);
		}
				
		if (!module.getParseProblems().isEmpty())
			throw new ParsingFailedException(program, module.getParseProblems());
	}
	
	private void disposeModels() throws ModelStorageException {
		for (Model model : models)
			model.dispose();
	}
}
