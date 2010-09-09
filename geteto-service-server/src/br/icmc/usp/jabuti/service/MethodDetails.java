package br.icmc.usp.jabuti.service;




/**
 * Description of the class MethodDetails.
 *
 *
 */
public class MethodDetails {

	public String methodName;
	public CriterionCoveredUncovered[] criteria;
	
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public CriterionCoveredUncovered[] getCriteria() {
		return criteria;
	}
	public void setCriteria(CriterionCoveredUncovered[] criteria) {
		this.criteria = criteria;
	}
}