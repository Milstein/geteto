package br.jabuti.lookup;


import org.apache.bcel.classfile.*;
import org.apache.bcel.generic.ConstantPoolGen;
import org.apache.bcel.generic.Instruction;
import org.apache.bcel.generic.InstructionHandle;
import org.apache.bcel.generic.InstructionList;
import org.apache.bcel.generic.InvokeInstruction;
import org.apache.bcel.generic.MethodGen;

import java.io.*;
import java.util.Vector;

import br.jabuti.util.Persistency;

/**
 <p>This class is used to store information about a given class
 in a program. A program is represented by {@link Program} object.
 A {@link RClassCode} object extends {@link RClass} and so
 it stores information about which subclasses
 extends the class and which classes implements it (if it is 
 an interface).<BR>
 In addition, it stores complete information about the class 
 itself. For that it uses a {@link de.fub.bytecode.classfile.JavaClass}
 object
 </p>

 <p>This class is used in the context of a program to represent those 
 classes that are in the scope of interest. For example, if 
 the program is built over a set of 5 classes <BR>

 <UL>
 <LI> MyClass1
 <LI> MyClass2
 <LI> MyClass3
 <LI> MyClass4
 <LI> MyClassFive
 </UL> <BR>

 these classes are represented using this {@link RClassCode} class and
 the relationship about them (subclassing and implementation) are also
 registered.
 </p>

 @version 0.00001
 @author Marcio Delamaro
 @see RClass
 @see Program

 */public class RClassCode extends RClass {

    /** Stores the complete information about the class */
    private JavaClass theClass; // non persistent
    private String theClassPersistent; // persistent
    
    /* Guarda algumas informacoes sobre a classe: */
    
    /** Stores the superclass name */
    private String superName;
    
    /** Is an interface ? */
    private boolean isInter;
    
    /** List of impleented interfaces */
    private String[] interfaces;

    /** Creates a RClassCode given its name and a {@link JavaClass}
     object 

     @param y The {@link JavaClass} object already created for this class
     @param x The complete name of the class
     */
    public RClassCode(JavaClass y, String x) {
        super(x);
        theClass = null;
        theClassPersistent = Persistency.add(y.getBytes());
        if ( theClassPersistent == null)
        	theClass = y;
        superName = y.getSuperclassName();
        interfaces = y.getInterfaceNames();
        isInter = y.isInterface();
    }

    /** Return the name of the superclass. The name is extracted from
     the {@link RClassCode#theClass} field.

     @return The complete name of its superclass
     */
    public String getSuperClass() {
        return superName;
    }

    /** Return the list of interfaces this class implements. 
     The names are extracted from
     the {@link RClassCode#theClass} field.

     @return The complete names of its interfaces
     */
    public String[] getInterfaces() {
        return interfaces;
    }

    /** Return whether this class is an interface. The
     information is extracted from
     the {@link RClassCode#theClass} field.

     @return True if this is an interface
     */
    public boolean isInterface() {
        return isInter;
    }

    /** Return the {@link JavaClass} object that represents this class

     @return the {@link JavaClass} object that represents this class
     */
    public JavaClass getTheClass() {
    	if ( theClass != null )
	        return theClass;
	    try
	    {
		    byte[] b = (byte[]) Persistency.get(theClassPersistent);
		    ByteArrayInputStream bais = new ByteArrayInputStream(b);
	        JavaClass javaClass = new ClassParser(bais, null).parse();
	        bais.close();
	        return javaClass;
    	}
    	catch (Exception e)
    	{
	    	return null;
	    }
    }

    /** Retorna uma lista de metodos chamados por um dado metodo
     * desta classe.
     * @param assinatura - a assinatura do metodo que se deseja analisar
     * @return a lista de metodos chamados pelo metodo passado como argumento.
     * Se o metodo solicitado nao for encontrado na classe, retorna null.
     */
    public String[] getCalledMethods(String assinatura)
    {
    	JavaClass jc = this.getTheClass();
    	Method[] mv = jc.getMethods();
    	Method m = null;
    	String met = new String();
    	int i;
    	for (i = 0; i < mv.length; i++)
    	{
    		m = mv[i];
    		
    		
    		met = jc.getClassName() + "." + mv[i].getName()+ mv[i].getSignature();
    		System.out.println("Metodo Aplica��o = " + met);
    		System.out.println("Metodo Parametro = " + assinatura);
    		
    		if (met.equals(assinatura))
    			break;
    	}
    	if ( i == mv.length )
    		return null;
    	ConstantPoolGen cp =
            new ConstantPoolGen(jc.getConstantPool());
    	MethodGen mg =
            new MethodGen(m, jc.getClassName(), cp);
    	
    	InstructionList il = mg.getInstructionList();
    	InstructionHandle[] ih = il.getInstructionHandles();
    	Vector v = new Vector();
    	
    	for(int x = 0; x < ih.length; x++)
    	{
    		Instruction ins = ih[x].getInstruction();
    		if ( ins instanceof InvokeInstruction )
    		{
    			InvokeInstruction invoke = (InvokeInstruction) ins;
    			String s =  invoke.getClassName(cp)+ "." + invoke.getMethodName(cp) + invoke.getSignature(cp);
    			
    			//System.out.println("gettype = " + invoke.getClassType(cp));
    			System.out.println("metodo retornado = " + s);
    			v.add(s);
    		}
    	}
    	return (String[]) v.toArray(new String[0]);
    }


    /**
     Send a few information to the standard output like <BR>
     <UL>
     <LI> Interface or class
     <LI> The name 
     <LI> The superclass
     <LI> The interfaces it implements
     <LI> Its subclasses
     <LI> Its implementations
     </UL>

     */
    public void print() {
        System.out.println();
        String s = isInterface() ? "Interface" : "Class";

        System.out.println("******* Code " + s + " " + getName() + " *********");
        System.out.println("Extends: " + getSuperClass());
        String[] subs = getInterfaces();

        System.out.println("Implements: ");
        for (int i = 0; i < subs.length; i++) {
            System.out.println(subs[i] + " ");
        }
			
        subs = getSubClasses();
        System.out.println("Extended by: ");
        for (int i = 0; i < subs.length; i++) {
            System.out.println(subs[i] + " ");
        }

        subs = getImplementations();
        System.out.println("Implemented by: ");
        for (int i = 0; i < subs.length; i++) {
            System.out.println(subs[i] + " ");
        }
    }
	
}
