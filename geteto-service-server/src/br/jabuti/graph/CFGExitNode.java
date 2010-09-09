package br.jabuti.graph;




public class CFGExitNode extends CFGNode {
	/**
	 * Added to jdk1.5.0_04 compiler
	 */
	private static final long serialVersionUID = -2475415871430218933L;
	
	/** The class where the called method is */
    public String classe[],
            
            /** The method name */
            name,
            
            /** The parameter types */
            param[];	
	
    public CFGExitNode() {
        super();
    }
	
    public String toString() {
        String str = "node " + number + " dummy exit node";

        return str;
    }
}		

