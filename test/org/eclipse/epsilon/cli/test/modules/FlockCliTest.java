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

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.Collection;

import org.eclipse.epsilon.cli.test.common.CliThatRunsToCompletionTest;
import org.junit.Test;

public class FlockCliTest extends CliThatRunsToCompletionTest {

	@Override
	protected File getProgram() {
		return getNearbyFile("Uppercase.mig", FlockCliTest.class);
	}
	
	@Test
	public void targetShouldContainAsManyNodesAsSource() throws Throwable {
		assertEquals(source.evaluate("Node.all.size"), target.evaluate("Node.all.size"));
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void targetShouldContainNodesWithUppercasedNames() throws Throwable {
		final Collection<String> originalNames = (Collection<String>)source.evaluate("Node.all.collect(n|n.name)");
		
		for (String originalName : originalNames) {
			final String uppercaseName = originalName.toUpperCase();
			target.assertTrue("Node.all.exists(n|n.name = '" + uppercaseName + "')");
		}		
	}
}
