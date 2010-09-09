package br.jabuti.webservices;

public class CoverageDetails {
	private int numberOfelements, numberOfcoveredelements;
	private float coveragepercentage;
	private String criterionname;
	public int getNumberOfelements() {
		return numberOfelements;
	}
	public void setNumberOfelements(int numberOfelements) {
		this.numberOfelements = numberOfelements;
	}
	public int getNumberOfcoveredelements() {
		return numberOfcoveredelements;
	}
	public void setNumberOfcoveredelements(int numberOfcoveredelements) {
		this.numberOfcoveredelements = numberOfcoveredelements;
	}
	public float getCoveragepercentage() {
		return coveragepercentage;
	}
	public void setCoveragepercentage(float coveragepercentage) {
		this.coveragepercentage = coveragepercentage;
	}
	public String getCriterionname() {
		return criterionname;
	}
	public void setCriterionname(String criterionname) {
		this.criterionname = criterionname;
	}
}
