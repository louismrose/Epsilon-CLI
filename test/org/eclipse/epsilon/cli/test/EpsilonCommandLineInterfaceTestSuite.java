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
package org.eclipse.epsilon.cli.test;

import org.eclipse.epsilon.cli.test.logging.LoggingSuite;
import org.eclipse.epsilon.cli.test.modules.ModulesSuite;
import org.eclipse.epsilon.cli.test.parameters.ParametersSuite;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ParametersSuite.class, ModulesSuite.class, LoggingSuite.class})
public class EpsilonCommandLineInterfaceTestSuite {

}
