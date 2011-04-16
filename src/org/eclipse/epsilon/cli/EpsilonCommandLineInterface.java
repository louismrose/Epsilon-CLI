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

public class EpsilonCommandLineInterface {
	
	public static void main(String[] args) {
		new EpsilonCommandLineInterface().run(args);
	}

	private void run(String[] args) {		
		final Parameters parameters = new Parameters(args);	
		
		if (parameters.areValid()) {
			new ParameterInterpreter(parameters).run();
		}
	}
}
