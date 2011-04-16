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
package org.eclipse.epsilon.cli;

import org.eclipse.epsilon.cli.domain.Model;
import org.eclipse.epsilon.cli.domain.Module;
import org.eclipse.epsilon.cli.domain.ModuleFactory;
import org.eclipse.epsilon.cli.domain.Program;
import org.eclipse.epsilon.cli.exceptions.CommandLineInterfaceException;
import org.eclipse.epsilon.cli.exceptions.LoggingException;
import org.eclipse.epsilon.cli.exceptions.UnknownFileExtensionException;
import org.eclipse.epsilon.cli.exceptions.wrappers.ModelLoadingException;

public class ParameterInterpreter {

	private final Logger  logger;
	private final Program program;
	private final Model   source, target;
	
	public ParameterInterpreter(Parameters parameters) {
		this.logger   = new Logger(parameters.log);
		this.program  = new Program(parameters.program);
		this.source   = new Model("Source", parameters.sourceModel, parameters.sourceMetamodel);
		this.target   = new Model("Target", parameters.targetModel, parameters.targetMetamodel);
	}
	
	public void run() {
		try {
			createModule().execute();
		
		} catch (CommandLineInterfaceException e) {
			e.describeTo(logger);
			
		} finally {
			writeLog();
		}
	}

	private Module createModule() throws UnknownFileExtensionException, ModelLoadingException {
		return new ModuleFactory().initialiseModuleFor(program, source, target);
	}
	
	private void writeLog() {
		try {
			logger.writeToDisk();
		
		} catch (LoggingException e) {
			e.describeTo(System.err);
		}
	}
}
