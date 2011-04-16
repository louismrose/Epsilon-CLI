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
package org.eclipse.epsilon.cli.test.parameters.invalid;

import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.assertThat;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.eclipse.epsilon.cli.test.common.CliTest;
import org.eclipse.epsilon.cli.test.common.ParameterStringBuilder;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class ExtraParameter extends CliTest {
	
	private static final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private static PrintStream originalOut;
	
	@BeforeClass
	public static void setUpStreams() {
		originalOut = System.out;
	    System.setOut(new PrintStream(outContent));
	}

	@AfterClass
	public static void cleanUpStreams() {
	    System.setOut(originalOut);
	}
	
	@Override
	protected String createParameterString(ParameterStringBuilder builder) {
		return builder.inSpecifiedOrder() + " --extra";
	}

	@Test
	public void usageIsPrinted() throws Exception {
		assertThat(outContent.toString(), startsWith("Usage:"));
	}
}
