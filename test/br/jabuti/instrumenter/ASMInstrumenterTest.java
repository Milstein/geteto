package br.jabuti.instrumenter;


import org.aspectj.apache.bcel.Repository;
import org.aspectj.apache.bcel.classfile.ClassParser;
import org.aspectj.apache.bcel.classfile.JavaClass;
import org.aspectj.apache.bcel.classfile.Method;
import org.aspectj.apache.bcel.generic.ClassGen;
import org.aspectj.apache.bcel.generic.ConstantPoolGen;
import org.aspectj.apache.bcel.generic.MethodGen;
import org.junit.Before;

public class ASMInstrumenterTest
{

	@Before
	public void setUp() throws Exception
	{
	}

    /**
     * This is a test driver. It takes a class name on args[0], inserts
     * some instructions in several points of each method in the class
     * and then dump the instrumented class to "new_"<original_name> 
     * file
     */
    public static void main(String args[]) throws Exception {
        // o melhor eh chamar com java ... ASMInstrumenter samples\arquivo.class
        // assim ele vai criar um novo arquivo new_samples\arquivo.class que
        // se pode testar
		
        JavaClass java_class;

        if ((java_class = Repository.lookupClass(args[0])) == null) {
            java_class = new ClassParser(args[0]).parse();
        } // May throw IOException

        ClassGen cg = new ClassGen(java_class);				 
        ConstantPoolGen cp = cg.getConstantPool();
        Method[] methods = cg.getMethods();

        for (int i = 0; i < methods.length; i++) {
            try {
                System.out.println("\n\n--------------------------");						 
                System.out.println(methods[i].getName());
                System.out.println("--------------------------");		
                MethodGen mg = new MethodGen(methods[i], 
                        cg.getClassName(),
                        cp);
                ASMInstrumenter gi = new ASMInstrumenter(mg, cg, cp);
                int nvars = mg.getMaxLocals() + 10;
  			
                String s = "GETSTATIC java.lang.System out \"Ljava/io/PrintStream;\"  "
                        + "astore " + nvars + " ";
                String s2 = "aload " + nvars + " "; 
                String s3 = "LDC \"Entrando no metodo " + mg.getName() + "\" ";
                String s4 = "LDC \"Saindo do metodo " + mg.getName() + "\\n \" ";
                String s5 = "invokevirtual java.io.PrintStream println "
                        + "\"(Ljava/lang/Object;)V\" ";

                gi.insertBefore(mg.getInstructionList().getStart(), s + s2 + s3 + s5);
                gi.insertBefore(mg.getInstructionList().getEnd(), s2 + s4 + s5);
                methods[i] = mg.getMethod();
            } catch (ParseException e) {
                System.err.println("Parser error " + e.getMessage());
            }
        }
        cg.setMethods(methods);
        java_class = cg.getJavaClass();
        java_class.dump("new_" + args[0]);
    }
}
