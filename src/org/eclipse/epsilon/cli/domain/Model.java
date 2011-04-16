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

import java.io.File;

import org.eclipse.epsilon.cli.exceptions.wrappers.ModelLoadingException;
import org.eclipse.epsilon.cli.exceptions.wrappers.ModelStorageException;
import org.eclipse.epsilon.cli.interceptor.Interceptable;
import org.eclipse.epsilon.cli.interceptor.InterceptedResult;
import org.eclipse.epsilon.cli.interceptor.SystemErrInterceptor;
import org.eclipse.epsilon.emc.emf.EmfModelFactory;
import org.eclipse.epsilon.emc.emf.EmfModelFactory.AccessMode;
import org.eclipse.epsilon.eol.exceptions.models.EolModelLoadingException;
import org.eclipse.epsilon.eol.models.IModel;
import org.eclipse.epsilon.eol.models.ModelRepository;

public class Model {

	public final String name;
	public final File modelLocation, metamodelLocaation;
	
	private IModel emcModel;
	
	public Model(String name, File modelLocation, File metamodelLocation) {
		this.name               = name;
		this.modelLocation      = modelLocation;
		this.metamodelLocaation = metamodelLocation;
	}
	
	public void addToRepository(ModelRepository repository) throws ModelLoadingException {
		load();
		repository.addModel(emcModel);
	}
	
	private IModel load() throws ModelLoadingException {
		try {
			final AccessMode accessMode = modelLocation.exists() ? AccessMode.READ_WRITE : AccessMode.WRITE_ONLY;
			emcModel = EmfModelFactory.getInstance().loadEmfModel(name, modelLocation, metamodelLocaation, accessMode);
			return emcModel;
		
		} catch (EolModelLoadingException e) {
			throw new ModelLoadingException(modelLocation, e);
		}
	}
	
	public void dispose() throws ModelStorageException {
		// Currently, AbstractEmfModel#store uses e.printStackTrace
		final InterceptedResult result = new SystemErrInterceptor().captureErrMessages(new Interceptable() {
			
			public void run() {
				emcModel.dispose();
			}
		});
		
		if (!result.errors.isEmpty()) {
			throw new ModelStorageException(modelLocation, result.errors);
		}
	}
}
