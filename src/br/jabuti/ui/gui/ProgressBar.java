package br.jabuti.ui.gui;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JProgressBar;

/**
 * ProgressListener listens to "progress" property changes in the Swing workers.
 */
public class ProgressBar implements PropertyChangeListener
{
	private JProgressBar progressBar;
	
	private static int DEFAULT_MIN_VALUE = 0;

	private static int DEFAULT_MAX_VALUE = 100;
	
	private int minValue;
	
	private int maxValue;
	
	public int getMinValue()
	{
		return minValue;
	}

	public void setMinValue(int minValue)
	{
		this.minValue = minValue;
	}

	public int getMaxValue()
	{
		return maxValue;
	}

	public void setMaxValue(int maxValue)
	{
		this.maxValue = maxValue;
	}

	
	// Prevent creation without providing a progress bar.
	private ProgressBar()
	{
	}

	public ProgressBar(JProgressBar progressBar)
	{
		this(progressBar, DEFAULT_MIN_VALUE, DEFAULT_MAX_VALUE);
	}

	public ProgressBar(JProgressBar progressBar, int minValue, int maxValue)
	{
		this.progressBar = progressBar;
		this.progressBar.setValue(0);
		this.progressBar.setIndeterminate(true);
		this.progressBar.setMinimum(minValue);
		this.progressBar.setMaximum(maxValue);
	}

	public void propertyChange(PropertyChangeEvent evt)
	{
		String strPropertyName = evt.getPropertyName();
		if ("progress".equals(strPropertyName)) {
			int progress = (Integer) evt.getNewValue();
			progressBar.setValue(progress);
		}
	}
}
