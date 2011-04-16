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

import org.eclipse.epsilon.cli.exceptions.wrappers.ParsingException;
import org.eclipse.epsilon.eol.IEolExecutableModule;

public class Program {

	private final File program;
	
	public Program(File program) {
		this.program = program;
	}
	
	public String getName() {
		return program.getName();
	}

	public String getExtension() {
		final String[] segments = getName().split("\\.");
		return segments[segments.length-1];
	}

	public boolean parseWith(IEolExecutableModule module) throws ParsingException {
		try {
			return module.parse(program);			

		} catch (Exception e) {
			throw new ParsingException(this, e);
		}
	}
}
