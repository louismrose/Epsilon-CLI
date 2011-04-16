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

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class ExceptionWhileWritingToLogFile extends AbstractLoggingTest {
		
	private static ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private static PrintStream originalErr;
	
	@BeforeClass
	public static void setUpStreams() {
		originalErr = System.err;
	    System.setErr(new PrintStream(errContent));
	}

	@AfterClass
	public static void cleanUpStreams() {
	    System.setErr(originalErr);
	}
	
	
	@Override
	protected File getProgram() {
		return getNearbyFile("ProducesRuntimeError.eol", ExceptionWhileWritingToLogFile.class);
	}
	
	@Override
	protected File getLogFile() {
		return getNearbyFile("ExceptionWhileWritingToLogFile.class", ExceptionWhileWritingToLogFile.class).getParentFile();
	}

	
	@Test
	public void ensureLogFileIsDirectory() throws Exception {
		assume("The log file should be a directory.", getLogFile().isDirectory());
	}
	
	@Test
	public void errorMessageShouldBePrintedToSystemErr() throws Exception {
		assertThat(errContent.toString(), startsWith("Error encountered whilst writing to log file " + getLogFile().getName() + ":"));
	}
	
	@Test
	public void causeShouldBePrintedToSystemErr() throws Exception {
		assertThat(errContent.toString(), containsString("java.io.FileNotFoundException"));
	}
	
	@Test
	public void logContentsShouldBePrintedToSystemErr() throws Exception {
		assertThat(errContent.toString(), containsString("The log file would have contained the following:"));
		assertThat(errContent.toString(), containsString("/ by zero"));
	}
	
	private static void assume(String message, boolean assumption) {
		assertTrue("Test assumption failed: " + message, assumption);
	}
}
