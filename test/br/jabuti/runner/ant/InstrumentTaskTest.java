package br.jabuti.runner.ant;


import java.io.File;

import org.apache.tools.ant.BuildFileTest;
import org.apache.tools.ant.Project;

import br.jabuti.TestConfiguration;

public class InstrumentTaskTest extends BuildFileTest
{
	InstrumentTask task;
	
	public InstrumentTaskTest(String name)
	{
		super(name);
	}

	public void setUp()
	{
		// configureProject("build.xml");
		task = new InstrumentTask();
		task.setProject(new Project());
	}

	public void testSimple()
	{
		task.setBinDir(new File(TestConfiguration.SAMPLES_ROOT));
		task.setInstrumentedDir(new File("/tmp"));
		task.setJabutiProject(new File("/tmp/teste.jbt"));
		task.setTraceFilename(new File("/tmp/teste.trc"));
		task.execute();
	}

}
