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
package org.eclipse.epsilon.cli.test.common;

import java.io.File;

import org.eclipse.epsilon.cli.EpsilonCommandLineInterface;
import org.eclipse.epsilon.cli.Parameters;
import org.eclipse.epsilon.commons.util.FileUtil;
import org.eclipse.epsilon.emc.emf.EmfModelFactory;
import org.eclipse.epsilon.eol.exceptions.models.EolModelLoadingException;
import org.eclipse.epsilon.test.util.ModelWithEolAssertions;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

public abstract class CliTest {
	
	private static final File graphMetamodel = getNearbyFile("Graph.ecore");
	private static final File sourceModel    = getNearbyFile("Source.model");
	private static final File targetModel    = getNearbyFile("Target.model");
	private static final File program        = getNearbyFile("Default.eol");
	private static final File logFile        = getNearbyFile("log.txt");

	
	protected File getSourceModel() {
		return sourceModel;
	}
	
	protected File getTargetModel() {
		return targetModel;
	}
	
	protected File getProgram() {
		return program;
	}
	
	protected File getLogFile() {
		return logFile;
	}
	
	
	protected static ModelWithEolAssertions source, target; 
	
	protected static File getNearbyFile(String path) {
		return getNearbyFile(path, CliTest.class);
	}
	
	protected static File getNearbyFile(String path, Class<?> relativeTo) {
		final String binPath = FileUtil.getFile(path, relativeTo).getAbsolutePath();
		final String srcPath = binPath.replaceAll("\\bin", "\\test").replaceAll("/bin", "/test");
		
		return new File(srcPath);
	}
	
	@BeforeClass
	public static void clean() {
		targetModel.delete();
		logFile.delete();
	}
	
	@Before
	public void invokeCli() throws Exception {
		if (!targetModel.exists()) {
			final Parameters             parameters      = createParameters();
			final ParameterStringBuilder builder         = new ParameterStringBuilder(parameters);
			final String                 parameterString = createParameterString(builder);
						
			run(parameterString);
			
			source = createGraphModelWithAssertions("Source", getSourceModel());
			target = createGraphModelWithAssertions("Target", getTargetModel());
		}
	}
	
	private Parameters createParameters() {
		final Parameters parameters = new Parameters();
		parameters.sourceMetamodel = graphMetamodel;
		parameters.targetMetamodel = graphMetamodel;
		parameters.program         = getProgram();
		parameters.sourceModel     = getSourceModel();
		parameters.targetModel     = getTargetModel();
		parameters.log             = getLogFile();
		return parameters;
	}
	
	protected abstract String createParameterString(ParameterStringBuilder builder);
	
	@AfterClass
	public static void disposeModels() {
		if (source != null) source.dispose();
		if (target != null) target.dispose();
	}

	private static ModelWithEolAssertions createGraphModelWithAssertions(String name, File modelFile) throws EolModelLoadingException {
		if (!modelFile.exists())
			return null;
		
		return new ModelWithEolAssertions(EmfModelFactory.getInstance().loadEmfModel(name, modelFile, graphMetamodel));
	}
	
	private static void run(String parameters) throws Exception {
		final String[] args = parameters.isEmpty() ? new String[]{} : parameters.split(" ");
		EpsilonCommandLineInterface.main(args);
	}
}
