package br.jabuti.ui.ant;

import java.io.File;

import org.apache.tools.ant.taskdefs.MatchingTask;

/**
 * http://ant.apache.org/manual/tutorial-writing-tasks.html
 * @author magsilva
 *
 */
public class InstrumentTask extends MatchingTask
{
	private File jabutiProject;
	
	private File binDir;
	
	private File instrumentedDir;
	
	private File traceFilename;
	

	public void init()
	{
		super.init();
	}

	
	@Override
	public void execute()
	{
		log("Raw classes: " + binDir);
		log("Instrumented classes: " + instrumentedDir);
		log("JaBUTi project file: " + jabutiProject);
		log("Trace file: " + traceFilename);
	}


	public void setBinDir(File binDir)
	{
		this.binDir = binDir;
	}


	public void setInstrumentedDir(File instrumentedDir)
	{
		this.instrumentedDir = instrumentedDir;
	}


	public void setTraceFilename(File traceFilename)
	{
		this.traceFilename = traceFilename;
	}


	public void setJabutiProject(File jabutiProject)
	{
		this.jabutiProject = jabutiProject;
	}
}
