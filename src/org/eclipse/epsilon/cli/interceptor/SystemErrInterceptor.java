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
package org.eclipse.epsilon.cli.interceptor;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.eclipse.epsilon.cli.exceptions.CommandLineInterfaceException;

public class SystemErrInterceptor {

	public InterceptedResult captureErrMessages(Interceptable interceptable) {
		final PrintStream           originalErr = System.err;
		final ByteArrayOutputStream errContent  = new ByteArrayOutputStream();
		
		CommandLineInterfaceException exception = null;
		
		try {
			System.setErr(new PrintStream(errContent));
			interceptable.run();
			
		} catch (CommandLineInterfaceException e) {
			exception = e;
			
		} finally { 
			System.setErr(originalErr);
		}
		
		return new InterceptedResult(errContent.toString(), exception); 
	}
}