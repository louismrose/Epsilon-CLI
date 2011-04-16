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

import java.io.File;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;

public class Parameters {
	
	@Parameter(names = "--mma", description = "Path to the source metamodel", required = true)
	public File sourceMetamodel;
	
	@Parameter(names = "--mmb", description = "Path to the target metamodel", required = true)
	public File targetMetamodel;
	
	@Parameter(names = "--transf", description = "Path to the Epsilon program", required = true)
	public File program;
	
	@Parameter(names = "--ma", description = "Path to the source model", required = true)
	public File sourceModel;
	
	@Parameter(names = "--mb", description = "Path to the target model", required = true)
	public File targetModel;
	
	@Parameter(names = "--log", description = "Path to the log file", required = true)
	public File log;

	private boolean valid;
	
	public Parameters() {}
	
	public Parameters(String[] args) {
		final JCommander commander = new JCommander(this);
		
		try {
			commander.parse(args);
			valid = true;
			
		} catch (ParameterException e) {
			commander.usage();
			valid = false;
		}
	}
	
	public boolean areValid() {
		return valid;
	}
}
