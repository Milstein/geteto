package br.jabuti;

import br.jabuti.criteria.AbstractCriterion;
import br.jabuti.criteria.Criterion;
import br.jabuti.project.Coverage;
import br.jabuti.project.JabutiProject;

public class CoverageDetails {
	String criterionname;
	int numberofrequiredelements, numberofcoveredrequiredelements;
	float coveragepercentage;
	
	public String getCriterionname() {
		return criterionname;
	}
	public void setCriterionname(String criterionname) {
		this.criterionname = criterionname;
	}
	public int getNumberofrequiredelements() {
		return numberofrequiredelements;
	}
	public void setNumberofrequiredelements(int numberofrequiredelements) {
		this.numberofrequiredelements = numberofrequiredelements;
	}
	public int getNumberofcoveredrequiredelements() {
		return numberofcoveredrequiredelements;
	}
	public void setNumberofcoveredrequiredelements(int numberofcoveredrequiredelements) {
		this.numberofcoveredrequiredelements = numberofcoveredrequiredelements;
	}
	public float getCoveragepercentage() {
		return coveragepercentage;
	}
	public void setCoveragepercentage(float coveragepercentage) {
		this.coveragepercentage = coveragepercentage;
	}
	
	public static CoverageDetails[] generate(JabutiProject jbtproject) {
		CoverageDetails coverdetails[] = new CoverageDetails[Criterion.NUM_CRITERIA];
		
		for (int i = 0; i < Criterion.NUM_CRITERIA; i++) {
			coverdetails[i] = new CoverageDetails();
			coverdetails[i].setCriterionname(AbstractCriterion.getName(i));
			Coverage c = jbtproject.getProjectCoverage(i);
			coverdetails[i].setNumberofrequiredelements(c.getNumberOfRequirements());
			coverdetails[i].setNumberofcoveredrequiredelements(c.getNumberOfCovered());
			coverdetails[i].setCoveragepercentage(c.getPercentage());
		}
		return coverdetails;
	}
	
	@Override
	public String toString() {
		String ret = criterionname + ": " + numberofcoveredrequiredelements + " of " + numberofrequiredelements + " (" + coveragepercentage + "%)";  
		
		return ret;
	}
}
