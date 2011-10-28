package br.jabuti.runner.ws;

public class InvalidNameFault extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4394064478284373658L;

	public InvalidNameFault(String message) {
		super(message);
	}
}
