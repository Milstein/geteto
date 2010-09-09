package br.jabuti.graph;


import java.util.*;
import org.apache.bcel.generic.*;
import br.jabuti.verifier.*;


public class CFGCallNode extends CFGNode {

	
	/**
	 * Added to jdk1.5.0_04 compiler
	 */
	private static final long serialVersionUID = -600189833884301571L;

	static public int NOTHING_SPECIAL = 0,
					  ASPECT_METHOD = 1;
	static private Vector aspectVector = new Vector();
	
	static {
		aspectVector.add("ajc$afterReturning$"); 
		aspectVector.add("ajc$after$"); 
		aspectVector.add("ajc$before$"); 
		aspectVector.add("ajc$around$"); 
	}

    /** The class where the called method is */
    private String classe[],
            
            /** The method name */
            name,
            
            /** The parameter types */
            param[];	
            
    /** indicates whether the method is something special, like aspects */
    private int specialMethod;
	
    public CFGCallNode(CFGNode x, InstructionNode ins, ConstantPoolGen cp) {
        super(x);
        InvokeInstruction y = (InvokeInstruction) ins.ih.getInstruction();

        name = y.getMethodName(cp);
        Type[] vt = y.getArgumentTypes(cp);

        param = new String[vt.length];
        for (int i = 0; i < vt.length; i++) {
            param[i] = vt[i].getSignature();
        }
		
        if (y instanceof INVOKESTATIC || y instanceof INVOKESPECIAL) {
            classe = new String[1];
            classe[0] = y.getClassName(cp);
        } else {
            Vector vtype = new Vector();

            vt = ins.getStackAt(ins.argStackFrom);
            for (int i = 0; i < vt.length; i++) {
                String s = vt[i].getSignature();

                if (!vtype.contains(s)) {
                    vtype.add(s);
                }
            }
            classe = (String[]) vtype.toArray(new String[0]);
        }
        
        specialMethod = NOTHING_SPECIAL;
        for (int i = 0; i < aspectVector.size(); i++)
        {
        	String s = (String) aspectVector.elementAt(i);
			if ( name.startsWith(s) )
			{
				specialMethod = ASPECT_METHOD;
				break;
			}
       }
     }
	
	public String[] getClasse() {
		return classe;
	}
	
	public String getName() {
		return name;
	}
	
	public String[] getParam() {
		return param;
	}
	
	public int getSpecialMethod() {
		return specialMethod;
	}
	
    public String toString() {
        String str = super.toString();

        for (int h = 0; h < classe.length; h++) {
            str += "Call to " + classe[h] + "." + name + "(";
            int i;

            for (i = 0; i < param.length - 1; i++) {
                str += param[i] + ", ";
            }
            if (i < param.length) {
                str += param[i];
            }
            str += ")\n";
        }
        return str;
    }
}		

