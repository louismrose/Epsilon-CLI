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

import org.eclipse.epsilon.cli.exceptions.LoggingException;
import org.eclipse.epsilon.commons.util.FileUtil;

public class Logger {

	private final File destination;
	private final StringBuilder contents = new StringBuilder();
	
	public Logger(File destination) {
		this.destination = destination;
	}
	
	public void append(Object message) {
		contents.append(message.toString());
	}

	public void appendNewLine() {
		contents.append(System.getProperty("line.separator"));
	}
	
	public void writeToDisk() throws LoggingException {
		try {
			FileUtil.setFileContents(contents.toString(), destination);
		} catch (Exception e) {
			throw new LoggingException(destination, contents.toString(), e);
		}
	}
}
