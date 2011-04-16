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

import org.eclipse.epsilon.cli.test.logging.UnknownFileExtensionTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ExistingLogFileShouldBeOverwritten.class,
               ExceptionWhileWritingToLogFile.class,
               UnknownFileExtensionTest.class,
               ModelLoadingProblemTest.class,
               ModelStoringProblemTest.class,
               NonExistentProgram.class,
               ProgramWithParseProblemTest.class,
               ProgramWithRuntimeErrorTest.class})
public class LoggingSuite {

}
