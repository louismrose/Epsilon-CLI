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
package org.eclipse.epsilon.cli.test.logging;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.eclipse.epsilon.commons.util.FileUtil;
import org.junit.BeforeClass;
import org.junit.Test;

public class ExistingLogFileShouldBeOverwritten extends AbstractLoggingTest {
	
	private static final File logFile = getNearbyFile("log.txt");
	
	@Override
	protected File getProgram() {
		return getNearbyFile("Valid.eol", ExceptionWhileWritingToLogFile.class);
	}
	
	@Override
	protected File getLogFile() {
		return logFile;
	}
	
	@BeforeClass
	public static void setLogFileContents() throws Exception {
		FileUtil.setFileContents("Existing content", logFile);
	}
	
	@Test
	public void logShouldBeEmpty() throws Exception {
		assertEquals("", getContentsOfLogFile());
	}
}
