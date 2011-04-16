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
package org.eclipse.epsilon.cli.test.common;

import org.eclipse.epsilon.cli.Parameters;

public class ParameterStringBuilder {

	private final Parameters parameters;
	
	public ParameterStringBuilder(Parameters parameters) {
		this.parameters = parameters;
	}
	
	public String inSpecifiedOrder() {
		return "--mma "    + parameters.sourceMetamodel.getAbsoluteFile() + " " +
		       "--mmb "    + parameters.targetMetamodel.getAbsoluteFile() + " " + 
		       "--transf " + parameters.program.getAbsoluteFile()         + " " +
		       "--ma "     + parameters.sourceModel.getAbsoluteFile()     + " " +
		       "--mb "     + parameters.targetModel.getAbsoluteFile()     + " " +
		       "--log "    + parameters.log.getAbsoluteFile();
	}
	
	public String inWrongOrder() {
		return "--log "    + parameters.log.getAbsoluteFile()             + " " +
		       "--mb "     + parameters.targetModel.getAbsoluteFile()     + " " +
		       "--ma "     + parameters.sourceModel.getAbsoluteFile()     + " " +
		       "--transf " + parameters.program.getAbsoluteFile()         + " " +
		       "--mmb "    + parameters.targetMetamodel.getAbsoluteFile() + " " +
		       "--mma "    + parameters.sourceMetamodel.getAbsoluteFile();
	}
	
	public String withRelativePaths() {
		return "--mma "    + parameters.sourceMetamodel.getName() + " " +
		       "--mmb "    + parameters.targetMetamodel.getName() + " " + 
		       "--transf " + parameters.program.getAbsoluteFile() + " " + // FIXME should be relative
		       "--ma "     + parameters.sourceModel.getName()     + " " +
		       "--mb "     + parameters.targetModel.getName()     + " " +
		       "--log "    + parameters.log.getName();
	}
}
