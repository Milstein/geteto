package br.jabuti.graph;


import br.jabuti.verifier.*;
import org.apache.bcel.generic.*;


public class CFGSuperNode extends CFGCallNode {

	/**
	 * Added to jdk1.5.0_04 compiler
	 */
	private static final long serialVersionUID = 9190824248421013922L;

	public CFGSuperNode(CFGNode x, InstructionNode ins, ConstantPoolGen cp) {
        super(x, ins, cp);
    }
	
}		

