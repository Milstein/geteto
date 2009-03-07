/*  Copyright 2003  Auri Marcelo Rizzo Vicenzi, Marcio Eduardo Delamaro, 			    Jose Carlos Maldonado

    This file is part of Jabuti.

    Jabuti is free software: you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as 
    published by the Free Software Foundation, either version 3 of the      
    License, or (at your option) any later version.

    Jabuti is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU Lesser General Public License
    along with Jabuti.  If not, see <http://www.gnu.org/licenses/>.
*/


package br.jabuti.probe;


import org.aspectj.apache.bcel.classfile.*;
import org.aspectj.apache.bcel.generic.*;
import org.aspectj.apache.bcel.util.ClassPath;

import java.io.*;
import java.util.*;
import java.util.jar.*;

import br.jabuti.instrumenter.*;
import br.jabuti.lookup.*;
import br.jabuti.project.JabutiProject;

import br.jabuti.util.*;


/**

 This is the class that implements the functionality of a
 JVM code instrumenter. Using such object it is possible
 to insert JVM code in a given JVM method.

 @version: 0.00001
 @author: Marcio Eduardo Delamaro

 */

public class ProberInstrum {
	// Classes da Jabuti adicionadas no jar
	static final private String[] ProberClasses = new String[] {
		"br.jabuti.probe.ProbedNode",
		"br.jabuti.probe.DefaultProber",
		"br.jabuti.probe.DefaultProberHook"
	};
	
    public static void usage() {
        System.out.println(ToolConstants.toolName + " v. " + ToolConstants.toolVersion);
        System.out.println("\nProberInstrum usage:");
        System.out.println("-------------------\n");
        System.out.println("java probe.ProberInstrum [-d <DIR>] -p <PROJECT_NAME> -o <filename> <BASE_CLASS>\n");
        System.out.println("      [-d <DIR>]              Optional parameter. Specify the directory where the project");
        System.out.println("                              is located. If not specified, the current directory is assumed.");
        System.out.println("      -p <PROJECT_NAME>       Specify the name of the project to be used. The");
        System.out.println("                              project must be a valid project file (.jba) generated by");
        System.out.println("                              instrument the base class.");
		System.out.println("      -o <filename>           Specifies the name of the file to write the instrumented classes");
		System.out.println("      -nomain                 Specifies tha the main method in the baseclass is not to be instrumented with initialization code");
		System.out.println("      <BASE_CLASS>            The class file to be executed.");
        System.out.println("\nCopyright (c) 2002-2005\n");
    }

    public static void main(String args[]) throws Throwable {
    	String workDir = null;
        String projectName = null;
        JabutiProject project = null;
		String baseClass = null; // The class file to be executed
        String outName = null;
        boolean noMain = false;

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
                else if (("-nomain".equals(args[i])) && (i < args.length)) {
                    noMain = true;
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
			//System.out.println("Loaded: " + clo.loadClass(baseClass));


            // Checking if all essential parameters are not null
            if ((projectName == null) || (outName == null)) {
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
            } catch (Exception e) {
                ToolConstants.reportException(e, ToolConstants.STDERR);
                System.exit(0);
            }
			
            ProberInstrum.instrumentProject(project, baseClass, outName, noMain);

        } else {
            usage();
        }
    }

    
    public static boolean instrumentProject(JabutiProject project, String baseClass, String outName) 
    {
    	return instrumentProject(project, baseClass, outName, false);
    }


    public static boolean instrumentProject(JabutiProject project, String baseClass, String outName,
    		boolean noMain) 
    {
		Program program = null; // The program to be tested
        HashSet toInstrumenter = null;

        program = project.getProgram();
        toInstrumenter = project.getInstr();

        DefaultProbeInsert dpi = new DefaultProbeInsert(program, toInstrumenter);
        Map mp = null;

        try {
            mp = dpi.instrument(project.getCFGOption() );
            // substitui os objetos JavaClass por byte[]
            JavaClass jv = (JavaClass) mp.get(baseClass);
            if ( jv == null )
            {
            	System.out.println("Base class not found in project: " + baseClass);
            	return false;
            }
            
            String traceFileName = project.getTraceFileName();
            
            traceFileName = traceFileName.substring(traceFileName.lastIndexOf(File.separator) + 1);

			if ( ! noMain )
			{
				jv = instrumentMain(jv, traceFileName);
				mp.put(baseClass, jv);
			}
            
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
  			while (it.hasNext())
  			{
  				String clName = (String) it.next();
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
  				byte[] b = cp.getBytes(clName, ".class");
				clName = clName.replace('.', '/');
  				JarEntry jarEntry = new JarEntry(clName+".class");
  				outJar.putNextEntry(jarEntry);
  				outJar.write(b);
  			}
  			outJar.close();
        } catch (Exception eu) {
            eu.printStackTrace();
            return false;
        }
        return true;
    }
    
	/**
	 *  Instrumenta a classe base, methodo Main
	 */
	private static JavaClass instrumentMain(JavaClass jv, String tracefile) throws Exception {
		ClassGen cg = new ClassGen(jv);				 
		ConstantPoolGen cp = cg.getConstantPool();
  		
		Method[] methods = cg.getMethods();
		int i = 0;
		for (i=0; i < methods.length; i++)
			if (methods[i].getName().equals("main")
				&& methods[i].getSignature().equals("([Ljava/lang/String;)V") 
				)
			{
				break;
			}
		if ( i >= methods.length)
		{
			System.out.println("Method static public main(String[]) not found");
			return jv;
		}
		   	   
		MethodGen mg = new MethodGen(methods[i], 
				cg.getClassName(),
				cp);
                        
		InstructionList il = mg.getInstructionList();
		InstructionHandle last = il.getStart();
//		InstructionHandle pen = last.getPrev(); 
//		il.delete(il.getStart(), pen);
		ASMInstrumenter gi = new ASMInstrumenter(mg, cg, cp);
		gi.insertBefore(last, 
				" ldc \"" + tracefile + "\" " + // empilha o nome do arquivo de trace
				"invokestatic br.jabuti.probe.DefaultProber" + 
				" init \"(Ljava/lang/String;)V\" " + 
				"invokestatic br.jabuti.probe.DefaultProber" + 
				" startTrace \"()V\"" 
				);
		methods[i] = mg.getMethod();
		cg.setConstantPool(cp);
		cg.setMethods(methods);
		return (cg.getJavaClass());

	}
}
