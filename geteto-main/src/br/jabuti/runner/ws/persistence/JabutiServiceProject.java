package br.jabuti.runner.ws.persistence;

public class JabutiServiceProject {
	private String projid;
	private String name;
	private String testsuiteclassname;
	private String selectedclasses;
	private String ignoredclasses;
	
	public String getProjid() {
		return projid;
	}
	public void setProjid(String projid) {
		this.projid = projid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTestsuiteclassname() {
		return testsuiteclassname;
	}
	public void setTestsuiteclassname(String testsuiteclassname) {
		this.testsuiteclassname = testsuiteclassname;
	}
	public String getSelectedclasses() {
		return selectedclasses;
	}
	public void setSelectedclasses(String selectedclasses) {
		this.selectedclasses = selectedclasses;
	}
	public String getIgnoredclasses() {
		return ignoredclasses;
	}
	public void setIgnoredclasses(String ignoredclasses) {
		this.ignoredclasses = ignoredclasses;
	}
}
