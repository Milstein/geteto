package br.jabuti.gui;


import javax.swing.JMenuItem;

import br.jabuti.graph.GraphNode;



/**
 * This class implements a pesonalized JMenuItem.
 * It is used to build the popup menu both in the
 * text panels and also inthe CFG.
 */
public class MyJMenuItem extends JMenuItem {
    /**
	 * Added to jdk1.5.0_04 compiler
	 */
	private static final long serialVersionUID = 1729443672476127917L;
	private int methodId = -1;
    private GraphNode gn; // The GraphNode where the variable is defined
    private String varName; // The name of the variable ( the one used into the bytecode)
    private String itemName; /* The name presented in the popup menu
     If the bytecode panel is active, this name is
     the same as varName. If the source panel is presented
     this variable has the real name of the bytecode variable.
     Observe that this is only possible if the .class file was
     compiled using the debbug option (-g) */
					
    public MyJMenuItem(int id, GraphNode g, String vn, String in) {
        super(in);
        methodId = id;
        gn = g;
        varName = vn;
        itemName = in;
    }

    public int getMethodId() {
        return methodId;
    }
		
    public GraphNode getGraphNode() {
        return gn;
    }

    public String getVarName() {
        return varName;
    }
		
    public String getItemName() {
        return itemName;
    }
}
