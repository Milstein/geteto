package br.icmc.usp.jabuti.service;


/**
 * Description of the class RequiredElementsDetails.
 *
 *
 */
public class RequiredElementsDetails {

	public String methodName;
	public Criterion[] criterion;
	
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public Criterion[] getCriterion() {
		return criterion;
	}
	public void setCriterion(Criterion[] criterion) {
		this.criterion = criterion;
	}
}