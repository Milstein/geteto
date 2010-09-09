package br.jabuti.cmdtool;


import org.apache.bcel.generic.*;

import java.io.*;
import java.util.*;

import br.jabuti.util.*;
import br.jabuti.graph.*;
import br.jabuti.lookup.*;
import br.jabuti.project.*;


/**
 Given a project file, a class number (<I>cId</I>),
 method number (<I>mId</I>) and a block number (<I>bId</I>), this 
 application program reports all information w.r.t. each 
 variable defined in this block. The information is presented 
 in a six-columns table:

 <UL>
 <LI><I>Column 1</I> - the index of the variable into the LocalVariableTable
 <LI><I>Column 2</I> - the index of the local variable
 <LI><I>Column 3</I> - the bytecode name of the variable
 <LI><I>Column 4</I> - the real name of the variable when avaliable (<B>*</B>)
 <LI><I>Column 5</I> - the bytecode offset where the definition occurs
 <LI><I>Column 6</I> - the corresponding source code line
 </UL>

 (<B>*</B>)Observe that the real names of each variable is only avaliable if the
 Classfile was compiled with -g option. 

	<P>
 	For example, by running the command below
<BR> 	
	$ java -cp ".;lib\BCEL.jar;lib\jviewsall.jar" cmdtool.PrintDefinedVariablesTable -P fact.jbt 0 1 0
<BR>
	The resultant output is:
<BR>	
	0       0       L@0     this    0       16
<BR>	
	1       1       L@1     x       0       16 	

 @version: 0.00001
 @author: Auri Marcelo Rizzo Vincenzi
 */
public class PrintDefinedVariablesTable {
    public static void usage() {
        System.out.println(ToolConstants.toolName + " v. " + ToolConstants.toolVersion);
        System.out.println("\nPrintDefinedVariablesTable usage:");
        System.out.println("-------------------\n");
        System.out.println("java cmdtool.PrintDefinedVariablesTable [-d <DIR>] -p <PROJECT_NAME> <class_id> <method_id> <block_id>\n");
        System.out.println("      [-d <DIR>]              Optional parameter. Specify the directory where the project");
        System.out.println("                              is located. If not specified, the current directory is assumed.");
        System.out.println("      -p <PROJECT_NAME>       Specify the name of the project to be used. The");
        System.out.println("                              project must be a valid project file (.jba) generated by");
        System.out.println("                              instrument the base class.");
        System.out.println("      <class_id>              An integer number that represents a given class");
        System.out.println("      <method_id>             An integer number that represents a given method of that class_id");
        System.out.println("      <block_id>              An integer number that represents a given cfg block of that method_id");
        System.out.println("\nCopyright (c) 2002\n");
    }

    public static void main(String args[]) throws Throwable {
        String workDir = null;
        String projectName = null;
						
        JabutiProject project = null;
        if (args.length > 0) {

            int i = 0;
			
            while ((i < args.length) && (args[i].startsWith("-"))) {
				// -d: work directory
                if (("-d".equals(args[i])) && (i < args.length - 1)) {
                    i++;
                    workDir = args[i];
                } // -p: project name
                else if (("-p".equals(args[i])) && (i < args.length - 1)) {
                    i++;
                    projectName = args[i];
                } else {
                    System.out.println("Error: Unrecognized option: " + args[i]);
                    System.exit(0);
                }
                i++;
            }

            // Checking if all essential parameters are not null
            if (projectName == null) {
                System.out.println("Error: Missing parameter!!!");
                usage();
                System.exit(0);
            }
			
            // Getting the classId 
            if (i >= args.length) {
                System.out.println("Error: Missing parameter!!!");
                usage();
                System.exit(0);
            }
			
            int classId = Integer.parseInt(args[i]);

            i++;
			
            // Gettin the methodId
            if (i >= args.length) {
                System.out.println("Error: Missing parameter!!!");
                usage();
                System.exit(0);
            }
			
            int methodId = Integer.parseInt(args[i]);

            i++;

            int blockId = -1;

            // Gettin the blockId
            if (i < args.length) {
                blockId = Integer.parseInt(args[i]);
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
	          	
                project = JabutiProject.reloadProj( theFile.toString(), true );
 				
                // Getting the class code
                Program prog = project.getProgram();
                String classFileName = prog.getCodeClass(classId);

                ClassFile cf = project.getClassFile(classFileName);
                ClassMethod cm = cf.getMethod(methodId);
          		
                LocalVariableGen[] localVar = cm.getLocalVariables();
          		
                Hashtable systemVar = new Hashtable();
                int countSysVar = localVar.length;

                CFG cfg = cm.getCFG();
                GraphNode[] fdt = cfg.findDFT(true);
                GraphNode theNode = null;
          		
                for (i = 0; i < fdt.length; i++) {
                    GraphNode gn = fdt[i];
            		
                    if (gn.getNumber() == blockId) {
                        theNode = gn;
                    }

                    Hashtable definitions = ((CFGNode) gn).getDefinitions();

                    if (definitions.size() > 0) {
                        Enumeration it = definitions.keys();

                        while (it.hasMoreElements()) {
                            String s = (String) it.nextElement();

                            if (((!s.startsWith("L@"))
                                            && (!systemVar.containsKey(s)))
                                    || (s.startsWith("L@")
                                            && (s.indexOf(".") > 0)
                                            && (!systemVar.containsKey(s)))
                                    ) {
                                systemVar.put(s, new Integer(countSysVar++));
                            }
                        }
                    }
                }
            	
                Hashtable definitions;
            	
                if (theNode != null && blockId != -1) {
                    definitions = ((CFGNode) theNode).getDefinitions();
                    String realName = new String();
					
                    if (definitions.size() > 0) {
                        Enumeration it = definitions.keys();

                        while (it.hasMoreElements()) {
                            String s = (String) it.nextElement();
                            int byteOffset = ((Integer) definitions.get(s)).intValue();
                            int srcLine = cm.bytecodeOffset2SourceLine(byteOffset);
					   		
                            int localVarIndex = -1;
                            int tabVarIndex = -1;
							
                            realName = new String(" - ");
				   										
                            if (s.startsWith("L@") && (s.indexOf(".") < 0)) {
                                String numVar = s.substring(2, s.length());

                                localVarIndex = Integer.parseInt(numVar);
                                tabVarIndex = localVarIndex;
								
                                int currentDiff = -1;				   								   		

                                for (i = 0; i < localVar.length; i++) {
                                    if (localVar[i].getIndex() == localVarIndex) {
                                        if ((byteOffset
                                                <= localVar[i].getEnd().getPosition())) {
                                            int diff = Math.abs(localVar[i].getStart().getPosition() - byteOffset);

                                            if ((currentDiff == -1)
                                                    || (diff <= currentDiff)) {
                                                realName = localVar[i].getName();
                                                currentDiff = diff;
                                                tabVarIndex = i;
                                            }
                                        }
                                    }
                                }
                            } else {
                                realName = s;
                                localVarIndex = ((Integer) systemVar.get(s)).intValue();
                                tabVarIndex = localVarIndex;
                            }
				        	
                            System.out.println(tabVarIndex + "\t" + localVarIndex + "\t" + s + "\t" + realName + "\t" + byteOffset + "\t" + srcLine);
                        }
                    }
                }
            } catch (Exception e) {
                ToolConstants.reportException(e, ToolConstants.STDERR);
                System.exit(0);
            }
        } else {
            usage();
        }
    }
}
