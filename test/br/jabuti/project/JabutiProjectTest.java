package br.jabuti.project;


import java.io.File;
import java.util.Set;
import java.util.zip.ZipFile;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;

import br.icmc.labes.testing.loader.clazz.JarClassLoader;
import br.jabuti.lookup.Program;
import br.jabuti.probe.desktop.ProberInstrum;
import br.jabuti.runner.junit.CollectorListener;
import br.jabuti.runner.junit.InstrumenterListener;

public class JabutiProjectTest
{
	JabutiProject project;
	
	
	@Before
	public void setUp() throws Exception
	{
	}

	@Test
	public void testConstructorProgram() throws Exception
	{
		/*
		String srcJarFilename = "/home/magsilva/Projects-ICMC/JaBUTi/samples/junit.jar";
		String srcClasspath = "/home/magsilva/Projects-QualiPSo/EvaluatedSoftware/junit-3.8.2/build/app:/home/magsilva/Projects-QualiPSo/EvaluatedSoftware/junit-3.8.2/build/tests";
		String testJarFilename = "/tmp/junit.jar";
		String testClasspath = testJarFilename;
		String testSuite = "junit.tests.framework.TestCaseTest";
		String projectFilename ="/tmp/junit.prj";
		*/

		String srcJarFilename = "/tmp/factorial.jar";
		String srcClasspath = "/home/magsilva/Projects-ICMC/JaBUTi/samples:/home/magsilva/Projects-ICMC/JaBUTi/bin";
		String testJarFilename = "/tmp/factorial-instr.jar";
		String testClasspath = testJarFilename;
		String testSuite = "br.jabuti.samples.TestFactorial";
		String projectFilename ="/tmp/factorial.prj";

		Program program = Program.createFromPackage(srcJarFilename);
		project = new JabutiProject(program);
		project.setClasspath(srcClasspath);
		project.setProjectFile(new File(projectFilename));
		project.setJarFileName(testJarFilename);
		
		File f = new File(project.getInstrumentedJarFileName());
		f.delete();
		
		String[] classes = program.getCodeClasses();
		for (String clazz : classes) {
			project.addInstr(clazz);
		}
		
		project.rebuild();
		TestSet.initialize(project, project.getTraceFileName());
		project.saveProject();

		ProberInstrum.instrumentProject(project, testJarFilename);
		
		
		JarClassLoader classLoader = new JarClassLoader();
		classLoader.add(testClasspath);
		// classLoader.addClassPath(testClasspath);
		Class<?> clazz = classLoader.loadClass(testSuite);
		
		
		// Instrument it (JUnitJabutiCore.runInstrumenting)
		Set<String> testSet = null;
		JUnitCore jc = new JUnitCore();
		InstrumenterListener il = new InstrumenterListener(project.getTraceFileName(), testSet,  System.out);
		jc.addListener(il);
		jc.run(clazz);
		
		// Collect info (JUnitJabutiCore.runCollecting)
		CollectorListener cl = new CollectorListener(System.out);
		jc.removeListener(il);
		jc.addListener(cl);
		jc.run(clazz); // classname
		cl.getTestSet();
		
		TestSet.loadTraceFile(project);
		TestSet.updateOverallCoverage(project);		
				
		project.saveProject();
		System.out.println(project.coverage2TXT(""));
	}
}
