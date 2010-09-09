package br.jabuti.runner.ws;




/**
 * Description of the class CoverageDetails.
 *
 *
 */
public class CoverageDetails {

	public String name;
	public CoverageCriterionDetails[] criteria;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public CoverageCriterionDetails[] getCriteria() {
		return criteria;
	}
	public void setCriteria(CoverageCriterionDetails[] criteria) {
		this.criteria = criteria;
	}

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