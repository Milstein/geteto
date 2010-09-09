package br.jabuti.gui;

import java.io.*;

import br.jabuti.util.*;

/*
 * This class is responsable to create a new thread
 * that keep checking whether the size of the trace file
 * has changed or not.
 * When changed, the update button ( located on the menu )
 * became RED indicating that new test cases can be added.
 */
class ProbeCheck extends Thread {
	long newSize, oldSize; // control the size of the trace file

	public ProbeCheck(JabutiGUI f) {
		newSize = 0L;
		oldSize = 0L;
	}

	public void run() {
		try {
			while (!interrupted()) {
				checkProbeChanged(JabutiGUI.getProject().getTraceFileName());
				sleep(6000);
			}
		} catch (InterruptedException e) {
		}
	}

	private void checkProbeChanged(String traceFileName) {
		if (traceFileName != null) {
			try {
				File fileTrace = new File(traceFileName);

				if (fileTrace.exists()) {
					newSize = fileTrace.length();
					if (newSize != oldSize) {
						JabutiGUI.mainWindow().setUpdateLabelImage(
								JabutiGUI.mainWindow().getSemaforoRedImage());
					}
				}
			} catch (Exception e) {
				ToolConstants.reportException(e, ToolConstants.STDERR);
			}
		}
	}

	void setOldSize(long s) {
		oldSize = s;
	}

	long getOldSize() {
		return oldSize;
	}

	void setNewSize(long s) {
		newSize = s;
	}

	long getNewSize() {
		return newSize;
	}
}