package br.icmc.usp.jabuti.service;

public class OperationSequenceFault extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6780330937291901257L;

	public OperationSequenceFault(String message) {
		super(message);
	}
}
