package br.jabuti.runner.ws;




/**
 * Description of the class CriterionCoveredUncovered.
 *
 *
 */
public class CriterionCoveredUncovered {

	public String name;
	public String[] coveredElements;
	public String[] uncoveredElements;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String[] getCoveredElements() {
		return coveredElements;
	}
	public void setCoveredElements(String[] coveredElements) {
		this.coveredElements = coveredElements;
	}
	public String[] getUncoveredElements() {
		return uncoveredElements;
	}
	public void setUncoveredElements(String[] uncoveredElements) {
		this.uncoveredElements = uncoveredElements;
	}



}