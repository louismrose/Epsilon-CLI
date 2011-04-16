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

import org.eclipse.epsilon.cli.exceptions.UnknownFileExtensionException;
import org.eclipse.epsilon.cli.exceptions.wrappers.ModelLoadingException;
import org.eclipse.epsilon.eol.EolModule;
import org.eclipse.epsilon.eol.IEolExecutableModule;
import org.eclipse.epsilon.etl.EtlModule;
import org.eclipse.epsilon.flock.FlockModule;

public class ModuleFactory {

	public Module initialiseModuleFor(Program program, Model... models) throws UnknownFileExtensionException, ModelLoadingException {
		return new Module(createEpsilonModuleFor(program), program, models);
	}
	
	private IEolExecutableModule createEpsilonModuleFor(Program program) throws UnknownFileExtensionException {
		final String extension = program.getExtension();
		
		if ("eol".equals(extension))
			return new EolModule();
		
		else if ("etl".equals(extension))
			return new EtlModule();
		
		else if ("mig".equals(extension))
			return new FlockModule();
		
		throw new UnknownFileExtensionException(extension);
	}
}
