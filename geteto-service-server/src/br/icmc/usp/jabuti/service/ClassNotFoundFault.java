package br.icmc.usp.jabuti.service;

public class ClassNotFoundFault extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2894216439766675781L;

	public ClassNotFoundFault(String message) {
		super(message); 
	}
}
