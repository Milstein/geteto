package br.icmc.usp.jabuti.service;

import javax.activation.DataHandler;

/**
 * Description of the class InstrumentedProjectDetails.
 *
 *
 */
public class InstrumentedProjectDetails {

	public DataHandler file;
	public String commandLine;

	public DataHandler getFile() {
		return file;
	}
	public void setFile(DataHandler file) {
		this.file = file;
	}
	public String getCommandLine() {
		return commandLine;
	}
	public void setCommandLine(String commandLine) {
		this.commandLine = commandLine;
	}
}