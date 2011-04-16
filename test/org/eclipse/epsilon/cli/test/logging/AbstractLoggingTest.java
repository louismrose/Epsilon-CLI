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

import org.eclipse.epsilon.cli.test.common.CliWithValidParametersTest;
import org.eclipse.epsilon.commons.util.FileUtil;

public class AbstractLoggingTest extends CliWithValidParametersTest {

	protected String getContentsOfLogFile() throws Exception {
		return FileUtil.getFileContents(getLogFile());
	}
}
