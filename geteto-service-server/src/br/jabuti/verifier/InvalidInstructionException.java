package br.jabuti.verifier;


public class InvalidInstructionException extends Exception {
	
    /**
	 * Added to jdk1.5.0_04 compiler
	 */
	private static final long serialVersionUID = 6065700412574190130L;

	public InvalidInstructionException(String x) {
        super(x);
    }
}
