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
package org.eclipse.epsilon.cli.exceptions.wrappers;

import org.eclipse.epsilon.cli.domain.Program;
import org.eclipse.epsilon.eol.exceptions.EolRuntimeException;

public class ExecutionException extends CommandLineInteraceExceptionWithUnderlyingCause {
	
	// Generated by Eclipse
	private static final long serialVersionUID = -7712042577245970778L;
	
	public ExecutionException(Program program, EolRuntimeException cause) {
		super("Error encountered whilst executing " + program.getName(), cause);
	}
}
