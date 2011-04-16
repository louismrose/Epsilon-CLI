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

import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.assertThat;

import java.io.File;

import org.junit.Test;

public class UnknownFileExtensionTest extends AbstractLoggingTest {
	
	@Override
	protected File getProgram() {
		return getNearbyFile("Foo.txt", UnknownFileExtensionTest.class);
	}
	
	@Test
	public void logShouldContainUnknownFileExtensionException() throws Exception {
		assertThat(getContentsOfLogFile(), startsWith("Unknown file extension: txt"));
	}
}
