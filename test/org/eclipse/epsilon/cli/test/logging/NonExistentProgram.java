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

public class NonExistentProgram extends AbstractLoggingTest {
	
	private final String program = "DoesNotExist.eol";
	
	@Override
	protected File getProgram() {
		return getNearbyFile(program, NonExistentProgram.class);
	}
	
	@Test
	public void logShouldContainParseException() throws Exception {
		assertThat(getContentsOfLogFile(), startsWith("Error encountered whilst parsing " + program + ":"));
	}
	
	@Test
	public void logShouldContainCause() throws Exception {
		assertThat(getContentsOfLogFile(), containsString("DoesNotExist.eol (No such file or directory)"));
	}
}
