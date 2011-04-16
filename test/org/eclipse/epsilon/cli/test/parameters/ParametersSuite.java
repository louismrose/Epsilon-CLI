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
package org.eclipse.epsilon.cli.test.parameters;

import org.eclipse.epsilon.cli.test.parameters.invalid.ExtraParameter;
import org.eclipse.epsilon.cli.test.parameters.invalid.NoParameters;
import org.eclipse.epsilon.cli.test.parameters.valid.ParametersInSpecifiedOrder;
import org.eclipse.epsilon.cli.test.parameters.valid.ParametersInWrongOrder;
import org.eclipse.epsilon.cli.test.parameters.valid.ParametersWithRelativePaths;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ParametersInSpecifiedOrder.class,
               ParametersInWrongOrder.class,
               ParametersWithRelativePaths.class,
               NoParameters.class,
               ExtraParameter.class})
public class ParametersSuite {

}
