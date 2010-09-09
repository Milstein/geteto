package br.jabuti.graph;


import java.util.*;


public class DominatorTreeNode extends ReduceNode {
	
    /**
	 * Added to jdk1.5.0_04 compiler
	 */
	private static final long serialVersionUID = -4963913295760006124L;
	boolean covered;
	
    /** Creates an empty node */
    public DominatorTreeNode() {
        super();
        covered = false;
    }
	
    /** Creates from existing node */
    public DominatorTreeNode(GraphNode x) {
        super(x);
        covered = false;
    }
	
    /** Creates from an existing set of nodes */
    public DominatorTreeNode(Collection x) {
        super(x);
        covered = false;
    }

    public void setCovered(boolean b) {
        covered = b;
    }

    public boolean getCovered() {
        return covered;
    }
}

