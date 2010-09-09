package br.jabuti.verifier;


public class InvalidStackArgument extends Exception {
	
    /**
	 * Added to jdk1.5.0_04 compiler
	 */
	private static final long serialVersionUID = -5398971498917432041L;

	public InvalidStackArgument(String x) {
        super(x);
    }
}
