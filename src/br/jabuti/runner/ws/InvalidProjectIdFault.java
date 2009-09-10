package br.jabuti.runner.ws;

public class InvalidProjectIdFault extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 609307731434291260L;

	public InvalidProjectIdFault(String message) {
		super(message);
	}
}
