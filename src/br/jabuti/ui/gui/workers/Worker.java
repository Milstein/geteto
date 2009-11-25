package br.jabuti.ui.gui.workers;

import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

import br.jabuti.ui.gui.ProgressBar;


public class Worker
{
	private ProgressBar progressBar;
	
	private SwingWorker<?, ?> swingWorker;

	public Worker()
	{
		JProgressBar jProgressBar = new JProgressBar();
		progressBar = new ProgressBar(jProgressBar);
		
	}
	
	public SwingWorker<?, ?> getSwingWorker()
	{
		return swingWorker;
	}

	public void setSwingWorker(SwingWorker<?, ?> swingWorker)
	{
		this.swingWorker = swingWorker;
		swingWorker.addPropertyChangeListener(progressBar);
	}
	
	public void execute()
	{
		swingWorker.execute();
	}
}
