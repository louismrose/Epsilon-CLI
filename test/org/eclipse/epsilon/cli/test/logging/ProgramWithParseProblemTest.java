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

import java.io.File;

import org.junit.Test;

public class ProgramWithParseProblemTest extends AbstractLoggingTest {
	
	private final String program = "DoesNotParse.eol";
	
	@Override
	protected File getProgram() {
		return getNearbyFile(program, ProgramWithParseProblemTest.class);
	}
	
	@Test
	public void logShouldContainParseProblems() throws Exception {
		assertThat(getContentsOfLogFile(), startsWith("Problems encountered whilst parsing " + program + ":"));
	}
	
	@Test
	public void logShouldContainProblem() throws Exception {
		assertThat(getContentsOfLogFile(), containsString("Line: 4, Column: 2, Reason: no viable alternative at input '*'"));
	}
}
