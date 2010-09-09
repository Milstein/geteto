package br.icmc.usp.jabuti.service;

import javax.activation.DataHandler;




/**
 * Description of the class GraphDetails.
 *
 *
 */
public class GraphDetails {

	public String methodName;
	public DataHandler image;

	public DataHandler getImage() {
		return image;
	}
	
	public void setImage(DataHandler image) {
		this.image = image;
	}
	
	public String getMethodName() {
		return methodName;
	}
	
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
}