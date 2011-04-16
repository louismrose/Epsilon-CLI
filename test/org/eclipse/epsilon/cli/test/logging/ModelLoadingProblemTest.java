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

import org.junit.After;
import org.junit.Test;

public class ModelLoadingProblemTest extends AbstractLoggingTest {
	
	private final String sourceModel = "DoesNotConform.model";

	@Override
	protected File getSourceModel() {
		return getNearbyFile(sourceModel, ModelLoadingProblemTest.class);
	}
	
	@After
	public void disposeModel() {
		if (source != null) source.dispose();
	}
	
	@Test
	public void logShouldContainModelLoadingMessage() throws Exception {
		assertThat(getContentsOfLogFile(), startsWith("Error encountered whilst loading model " + sourceModel + ":"));
	}

	@Test
	public void logShouldContainCause() throws Exception {
		assertThat(getContentsOfLogFile(), containsString("Feature 'contents' not found."));
	}
}
