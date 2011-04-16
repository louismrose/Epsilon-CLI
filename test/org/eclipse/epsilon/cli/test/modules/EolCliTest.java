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
package org.eclipse.epsilon.cli.test.modules;

import java.io.File;

import org.eclipse.epsilon.cli.test.common.CliThatRunsToCompletionTest;
import org.junit.Test;

public class EolCliTest extends CliThatRunsToCompletionTest {

	@Override
	protected File getProgram() {
		return getNearbyFile("Combine.eol", EolCliTest.class);
	}
	
	@Test
	public void targetShouldContainASingleNode() throws Exception {
		target.assertEquals(1, "Node.all.size");
	}
	
	@Test
	public void nodeInTargetShouldBeCombinedNameOfNodesInSource() throws Throwable {
		final String combinedName = (String)source.evaluate("Node.all.collect(n|n.name).concat()");
		
		target.assertEquals(combinedName, "Node.all.first.name");
	}
}
