package br.jabuti.probe.device;


import br.jabuti.verifier.*;
import org.aspectj.apache.bcel.classfile.*;
import java.util.*;

import br.jabuti.graph.datastructure.ig.InvalidInstructionException;
import br.jabuti.graph.datastructure.ig.InvalidStackArgument;
import br.jabuti.lookup.*;
import br.jabuti.lookup.java.bytecode.Program;
import br.jabuti.probe.desktop.DefaultProbeInsert;

 
/** This class is designed to insert probes on each 
 * node of certain classes in a given program
 */ 
public class DeviceProbeInsert extends DefaultProbeInsert {
	static public final String 
	     JABUTI_J2ME_INSTR_ATTRIBUTE = "JaBUTi J2ME Instrumented";

	private String baseClass;
	private String server; // nome do servidor destino
	private String fileName; // nome do arquivo
	private String id;
	private long thr;

	private boolean keepAlive;
	
    /** The constructor.
     * @param p - The {@link br.jabuti.lookup.java.bytecode.Program structure} that represents
     * the program to be instrumented
     * @param c - The list of classes to be instrumented. Each element
     * is a string with the complete name of the class
     * @param b - name of the midlet class
     * @param s - address of the destination server
     * @param f - name of the temporary file to use
     * @param memTreshold 
     * @param id - identification of the MIDLET
     */
    public DeviceProbeInsert(Program p, Collection c, String b,
    						String f, String s, long memTreshold, 
							boolean kA, String id) {
    	super(p,c);
    	baseClass = b;
    	server = s;
    	fileName = f;
    	this.id = id;
    	thr = memTreshold;
    	keepAlive = kA;
    }
	
    public String getProbeClass() {
        return "br.jabuti.probe.device.j2me.DeviceProber";
    }
	
    public Map instrument() throws InvalidInstructionException,
    											InvalidStackArgument 
    {
		
		Map mp = super.instrument();
		
		Hashtable hs = new Hashtable();
		
		
		Iterator it = mp.keySet().iterator();		
		// para cada classe:
		while (it.hasNext()) 
		{
			String className = (String) it.next();
			JavaClass jv = (JavaClass) mp.get(className);
			        	
			// verifica se esta classe jah foi instrumentada por
			// esse instrumentador
		    Attribute[] atr = jv.getAttributes();
		    boolean achou = false;
		    for (int j = 0;  j < atr.length && ! achou; j++)
		    {
		    	if ( atr[j] instanceof Unknown )
		    	{
		    		Unknown uatr = (Unknown) atr[j];
		    		byte[] b = uatr.getBytes();
		    		achou = Arrays.equals(b, 
		    				JABUTI_J2ME_INSTR_ATTRIBUTE.getBytes());
		    	}
		    }
		    
			// se estah em inst eh pra instrumentar a classe
		    if ( (! achou) && className.equals(baseClass)) 
		    {
		    	// chama a rotina que faz a instrumentacao
		        jv = doMethodInstrument(jv);
				hs.put(className, jv);
		    } else { // coloca o codigo sem instrumentar
		        hs.put(className, jv);
		    }
		}
		return hs;
	}

    protected JavaClass doMethodInstrument(JavaClass java_class)
    {
    	/* o trecho abaixo � inserido no inicio do metodo startApp 
    	   corresponde a:

    	 	HostProber.init(fileName, server, );
    	 */
    	 	
    	String before  = 
    		( fileName == null ? " aconst_null " : "ldc \"" + fileName + "\" ") +
    		( server == null ? " aconst_null " : "ldc \"" + server + "\" ") +
    		"ldc " + thr + " " +
			"ldc " + (keepAlive ? 1: 0) + 
    		"ldc \"" + id + "\" " +
	   		"invokestatic " + getProbeClass() + " init " +
					        "\"(Ljava/lang/String;" + 
					        "Ljava/lang/String;IZ" + 
					        "Ljava/lang/String;)V\"";
	   	String after =	"nop";
    	java_class = wrapMethod(java_class, "startApp", "()V", before, after);
    	
    	before  = "nop";
	   	after = 
	   		"invokestatic " + getProbeClass() + " finish " +
	        "\"()V\"";
    	java_class = wrapMethod(java_class, "pauseApp", "()V", before, after);
    	
    	before  = "nop";
	   	after = 
	   		"invokestatic " + getProbeClass() + " finish " +
	        "\"()V\"";
    	java_class = wrapMethod(java_class, "destroyApp", "(Z)V", before, after);

    	return java_class;
    }
    
}

