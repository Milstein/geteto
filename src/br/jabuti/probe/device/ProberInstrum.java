package br.jabuti.probe.device;


import org.aspectj.apache.bcel.classfile.*;
import org.aspectj.apache.bcel.util.ClassPath;

import java.io.*;
import java.util.*;
import java.util.jar.*;

import br.jabuti.lookup.*;
import br.jabuti.lookup.java.bytecode.Program;
import br.jabuti.project.JabutiProject;

import br.jabuti.util.*;


/**

 This is the class that implements the functionality of a
 JVM code instrumenter. Using such object it is possible
 to insert JVM code in a given JVM method.

 @version: 0.00001
 @author: M�rcio Eduardo Delamaro

 */

public class ProberInstrum {
	// Classes da Jabuti adicionadas no jar
	static final private String[] ProberClasses = new String[] {
		"br.jabuti.probe.device.j2me.ProbedNode",
		"br.jabuti.probe.device.j2me.DeviceProber",
		"br.jabuti.probe.device.j2me.DeviceProber$1"
	};
	
    public static void usage() {
        System.out.println(ToolConstants.toolName + " v. " + ToolConstants.toolVersion);
        System.out.println("\nProberInstrum usage:");
        System.out.println("-------------------\n");
        System.out.println("java probe.ProberInstrum [-d <DIR>] [-nokeepalive] -p <PROJECT_NAME> -o <filename> -name <midletID> [-h <hostaddress>] [-mem <value>] [-temp <filename>] <BASE_CLASS>\n");
        System.out.println("      [-d <DIR>]              Optional parameter. Specify the directory where the project");
        System.out.println("                              is located. If not specified, the current directory is assumed.");
        System.out.println("      -p <PROJECT_NAME>       Specify the name of the project to be used. The");
        System.out.println("                              project must be a valid project file (.jba) generated by");
        System.out.println("                              instrument the base class.");
		System.out.println("      -o <filename>           Specifies the name of the file to write the instrumented classes");
        System.out.println("                              instrument the base class.");
        System.out.println("      -name <midletID>        Specifies the name of the midlet. This name will be ");
        System.out.println("                              used to identify the project to the server");
        System.out.println("      [-h <hostaddress>]      Specifies the address of the server that will receive the trace data");
        System.out.println("                              The format is hostname:port");
        System.out.println("      [-nokeepalive]          Specifies that connection with the desktop should be open only" +
        				   "                              when needed. Otherwise, it is kept open from the beggining to the end " +
        				   "                              of test case execution. Optional");
        System.out.println("      [-temp <filename>]      Specifies the name of a temporary file to be use on the device");
        System.out.println("                              to store trace data. Optional");
        System.out.println("      [-mem <value>]          Specifies the threshold of free memory to use. Optional");
		System.out.println("      <BASE_CLASS>            The class of the MIDLET.");
        System.out.println("\nCopyright (c) 2005\n");
    }

    public static void main(String args[]) throws Throwable {
    	String workDir = null;
        String projectName = null;
        JabutiProject project = null;
		String baseClass = null; // The class file to be executed
		Program program = null; // The program to be tested
        HashSet toInstrumenter = null;
        String outName = null; // Name of the output file
        String server = null; // Address of the server
        String tempFile = null; // Name of temporary file
        String midletName = null;
        int memTreshold = 0;
        boolean keepAlive = true;

        if (args.length > 0) {

            int i = 0;
			
            while (args[i].startsWith("-")) {
                // -CP: Class path
				if (("-d".equals(args[i])) && (i < args.length - 1)) {
                    i++;
                    workDir = args[i];
                } // -P: project name
                else if (("-p".equals(args[i])) && (i < args.length - 1)) {
                    i++;
                    projectName = args[i];
                } 
                else if (("-o".equals(args[i])) && (i < args.length - 1)) {
                    i++;
                    outName = args[i];
                } 
                else if (("-name".equals(args[i])) && (i < args.length - 1)) {
                    i++;
                    midletName = args[i];
                }
                else if (("-h".equals(args[i])) && (i < args.length - 1)) {
                    i++;
                    server = args[i];
                } 
                else if (("-temp".equals(args[i])) && (i < args.length - 1)) {
                    i++;
                    tempFile = args[i];
                } 
                else if (("-nokeepalive".equals(args[i])) && (i < args.length - 1)) {
                	keepAlive = false;
                } 
                else if (("-mem".equals(args[i])) && (i < args.length - 1)) {
                    i++;
                    int f = 1;
                    
                    StringBuffer s = new StringBuffer(args[i]);
                    if (args[i].endsWith("M") || args[i].endsWith("m") )
                    {
                    	f = 1024 * 1024;
                    	s = s.delete(s.length()-1, s.length());
                    }
                    else
                    if (args[i].endsWith("K") || args[i].endsWith("k") )
                    {
                    	f = 1024;
                    	s = s.delete(s.length()-1, s.length());
                    } 
                    memTreshold = f * Integer.parseInt(s.toString()); 
                } 
                else {
                    System.out.println("Error: Unrecognized option: " + args[i]);
                    System.exit(0);
                }
                i++;
            }

			if (i >= args.length) {
				System.out.println("Error: Missing base class!!!");
				System.exit(0);
			}
			// Ultimo parametro... file seguido dos parametros
			baseClass = args[i++];


            // Checking if all essential parameters are not null
            if (projectName == null || outName == null || 
            		midletName == null ) 
            {
                System.out.println("Error: Missing parameter!!!");
                usage();
                System.exit(0);
            }
			
            // Creating the absolute path to a given project
            String absoluteName = null;

            if (workDir != null) {
                absoluteName = workDir + File.separator + projectName;
            } else {
                absoluteName = projectName;
            }
				
            try {
                File theFile = new File(absoluteName);

                if (!theFile.isFile()) // verifica se existe
                {
                    System.out.println("File " + theFile.getName() + " not found");
                    System.exit(0);
                }
	          	
                project = JabutiProject.reloadProj(theFile.toString(), false );
                program = project.getProgram();
				
                toInstrumenter = project.getInstr();
            } catch (Exception e) {
                ToolConstants.reportException(e, ToolConstants.STDERR);
                System.exit(0);
            }
				                  
			DeviceProbeInsert dpi = 
            	new DeviceProbeInsert(program, toInstrumenter, baseClass, 
            			tempFile, server, memTreshold, 
						keepAlive, midletName);
            Map mp = null;

            try {
                mp = dpi.instrument();
                // substitui os objetos JavaClass por byte[]
                JavaClass jv;
                
       			Iterator it0 = mp.keySet().iterator();
       			Hashtable ht = new Hashtable();
       			
      			while (it0.hasNext())
      			{
      				String clName = (String) it0.next();
      				jv = (JavaClass) mp.get(clName);
      				ht.put(clName, jv.getBytes());
      			}

				mp = ht;
                
                File outFile = new File(outName);
      			FileOutputStream fos = new FileOutputStream(outFile);
      			JarOutputStream outJar = new JarOutputStream(fos);
      			Iterator it = mp.keySet().iterator();
      			System.out.println();
      			System.out.println("----------------------------------------");
      			System.out.println("Files to insert:");
      			while (it.hasNext())
      			{
      				String clName = (String) it.next();
          			System.out.println("\t" + clName);
      				byte[] b = (byte[]) mp.get(clName);
					clName = clName.replace('.', '/');
      				JarEntry jarEntry = new JarEntry(clName+".class");
      				outJar.putNextEntry(jarEntry);
      				outJar.write(b);
      			}
      			ClassPath cp = new ClassPath(System.getProperty("java.class.path"));
      			for (int z1 = 0; z1 < ProberClasses.length; z1++)
      			{
      				String clName = ProberClasses[z1];
          			System.out.println("\t" + clName);
      				byte[] b = cp.getBytes(clName, ".class");
					clName = clName.replace('.', '/');
      				JarEntry jarEntry = new JarEntry(clName+".class");
      				outJar.putNextEntry(jarEntry);
      				outJar.write(b);
      			}
      			System.out.println("----------------------------------------");
      			outJar.close();
            } catch (Exception eu) {
                System.err.println(eu);
                eu.printStackTrace();
            }

        } else {
            usage();
        }
    }


}
