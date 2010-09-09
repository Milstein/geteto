package br.jabuti.probe;


/** <P>This class represents a node reached by the execution. To characterize
 the node it is necessary to know <BR>
 <UL>
 <LI> the thread that executed the node
 <LI> the object executing the code
 <LI> the class and method of the node
 <LI> the node number
 </UL><BR>
 */

import java.io.*;


public class ProbedNode implements Serializable {
	
    /**
	 * Added to jdk1.5.0_04 compiler
	 */
	private static final long serialVersionUID = -5221701009238934021L;
	public String 	threadCode;  // o Hashcode da thread que executou
    public String 	objectCode;  // o hashcode do objecto que executou
    public String		clazz; 		// the number of the class where the node is
    public int 	metodo;		// the number of the method
    public String  node;		// the label of the node
	
    public ProbedNode(String th, String ob, String cl, int mt, String n) {
        threadCode = th;
        objectCode = ob;
        clazz = cl;
        metodo = mt;
        node = n;
    }
	
    public boolean isSame(ProbedNode x) {
        return threadCode.equals(x.threadCode)
                && objectCode.equals(x.objectCode) && clazz.equals(x.clazz)
                && metodo == x.metodo;
    }
	
    public String toString() {
        return "<Thread: " + threadCode + ", Object: " + objectCode
                + ", Class: " + clazz + ", Method: " + metodo + ", Node: "
                + node + ">";
    }
	
    public boolean equals(Object x) {
        if (!(x instanceof ProbedNode)) {
            return false;
        }
        ProbedNode y = (ProbedNode) x;

        return isSame(y) && node.equals(y.node);
    }
	
    public int hashCode() {
        return threadCode.hashCode() + objectCode.hashCode() + (clazz.hashCode() * 10)
                + (metodo * 100) + node.hashCode();
    }
	
    public void setNode(String s) {
        node = s;
    }
	
    public String getNode() {
        return node;
    }
	
}
