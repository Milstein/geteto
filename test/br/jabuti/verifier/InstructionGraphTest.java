package br.jabuti.verifier;


import java.util.Enumeration;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.aspectj.apache.bcel.classfile.ClassParser;
import org.aspectj.apache.bcel.classfile.ConstantPool;
import org.aspectj.apache.bcel.classfile.JavaClass;
import org.aspectj.apache.bcel.classfile.Method;
import org.aspectj.apache.bcel.generic.ConstantPoolGen;
import org.aspectj.apache.bcel.generic.MethodGen;
import org.junit.Before;

public class InstructionGraphTest
{

	@Before
	public void setUp() throws Exception
	{
	}

	
	/** A driver for testing the class. Creates the graph and calls
     * {@link InstructionGraph#calcStack}. The arguments determine
     * to which methods to apply.
     * 
     * @param args[0] A file name. Can be a classfile, a jar file or a 
     * zip file. If jar or zip, the second and third arguments does not apply.
     * In this case, the output is only the name of the classes and of the 
     * methods in the class. The test is applyied in all the listed 
     * methods. If this is a single class file name, the output will be
     * the complete graph (as presented by {InstructionNode#print}) for
     * each selected method.
     * @param args[1] The name of a method. The test is applyed to all
     * methods in the class that match this name.
     * @param args[2] The signature of a method. Used to select one
     * between several homonymous methods.
     */
		
    public static void main(String args[]) throws Exception {
        boolean all = true;
        String filename = args[0];
        ZipFile jf = null;
 		
        if (filename.endsWith(".jar")) {
            jf = new JarFile(filename);
        } else
        if (filename.endsWith(".zip")) {
            jf = new ZipFile(filename);
        }
 		
        if (jf == null) {
            JavaClass java_class;

            java_class = new ClassParser(filename).parse(); // May throw IOException
            ConstantPoolGen cp = new ConstantPoolGen(java_class.getConstantPool());
            Method[] methods = java_class.getMethods();

            for (int i = 0; i < methods.length; i++) {
                if (args.length >= 2 && (!args[1].equals(methods[i].getName()))) {
                    continue;
                }
                if (args.length >= 3
                        && (!args[2].equals(methods[i].getSignature()))) {
                    continue;
                }
                System.out.println("--------------------------");						 
                System.out.println(methods[i].getName());
                System.out.println(methods[i].getSignature());
                System.out.println("--------------------------");						 
                MethodGen mg = new MethodGen(methods[i], 
                        java_class.getClassName(),
                        cp);

                if (mg.getInstructionList() == null) {
                    continue;
                }
                InstructionGraph g = new InstructionGraph(mg);

                // g.calcReqLocal();
                g.calcStack(all);
                g.print(System.out);
            }
            return;
        }
 			
        Enumeration en = jf.entries();
        ZipEntry ze = null;

        while (en.hasMoreElements()) {
            ze = (ZipEntry) en.nextElement();
            if (!ze.getName().endsWith(".class")) {
                System.out.println("Not a class file: " + ze.getName());
                continue;
            }
 				
            System.out.println("\n\n**************************"); 
            System.out.println(ze.getName()); 
            System.out.println("**************************"); 
 		
            JavaClass java_class;

            java_class = new ClassParser(jf.getInputStream(ze),
                    ze.getName()).parse(); // May throw IOException

            ConstantPoolGen cp = new ConstantPoolGen(java_class.getConstantPool());
            Method[] methods = java_class.getMethods();

            for (int i = 0; i < methods.length; i++) {
                System.out.println("Memory : " + Runtime.getRuntime().freeMemory());
                System.out.println("--------------------------");						 
                System.out.println(methods[i].getName());
                System.out.println("--------------------------");						 
                MethodGen mg = new MethodGen(methods[i], 
                        java_class.getClassName(),
                        cp);

                if (mg.getInstructionList() == null) {
                    continue;
                }
                InstructionGraph g = new InstructionGraph(mg);

                // g.calcReqLocal();
                g.calcStack(all);
                System.out.println("Memory : " + Runtime.getRuntime().freeMemory());
                g = null;
                System.out.println("Collecting garbage...");
                System.out.println();

            }
        }
    }

}
