package br.jabuti.ui.gui.workers;

import java.util.Set;

import javax.swing.SwingWorker;

import br.jabuti.instrumenter.InstrumentedCode;
import br.jabuti.instrumenter.SourceCode;

public class Instrumenter extends SwingWorker<Set<InstrumentedCode>, InstrumentedCode>
{

	private Set<SourceCode> sourceCodeSet;
	
	private Instrumenter()
	{
	}

	public Instrumenter(Set<SourceCode> sourceCodeSet)
	{
		this.sourceCodeSet = sourceCodeSet;
	}

	@Override
	protected Set<InstrumentedCode> doInBackground() throws Exception
	{
		//As you process information from within the worker instance, you can call the setProgress method to update this property.
		return null;
	}

	// When the doInBackground method completes, SwingWorker calls the done method from the EDT
	@Override
	protected void done()
	{
		// Whether we retrieved anything or not, we're done, so set the progress indicator accordingly
        setProgress(100);
        if (isCancelled()) {
            return;
        }
	}
}
