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


package br.jabuti.ui.cli;



import java.io.*;
import br.jabuti.project.*;
import br.jabuti.util.*;


/**

 This application program print a table containing all classes and
 methods under testing in a given testing project.
 
 Example of use:
 	To obtain such a table it is necessary to invoke this application program, 
 	specifying the name of the testing project after the parameter -P. In the
 	example, we are using a project named "test.jtb".
 	
 	$ java -cp ".;lib\BCEL.jar;lib\jviewsall.jar" cmdtool.PrintClassMethodTable -P test.jbt
 	
 	The generated output is a table like this:
 	
	1       samples.Test    0       <init>()V
	1       samples.Test    1       main([Ljava/lang/String;)V
	1       samples.Test    2       testPrint(Ljava/lang/String;)V 
	
	where:
		1 column is the class_id;
		2 second column is the class_name;
		3 column is the method_id, inside a given class;
		4 column is the method name.
		
 
 @version: 0.00001
 @author: Auri Marcelo Rizzo Vincenzi
*/
public class PrintClassMethodTable {
    public static void usage() {
        System.out.println(ToolConstants.toolName + " v. " + ToolConstants.toolVersion);
        System.out.println("\nPrintClassMethodTable usage:");
        System.out.println("-------------------\n");
        System.out.println("java cmdtool.PrintClassMethodTable [-d <DIR>] -p <PROJECT_NAME>\n");
        System.out.println("      [-d <DIR>]              Optional parameter. Specify the directory where the project");
        System.out.println("                              is located. If not specified, the current directory is assumed.");
        System.out.println("      -p <PROJECT_NAME>       Specify the name of the project to be used. The");
        System.out.println("                              project must be a valid project file (.jba) generated by");
        System.out.println("                              instrument the base class.");
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
                } 
                // -p: project name
                else if (("-p".equals(args[i])) && (i < args.length - 1)) {
                    i++;
                    projectName = args[i];
                } 
                // Invlid option
                else {
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
 				
                // For each class
                String[] classFileNames = project.getAllClassFileNames();

                for (i = 0; i < classFileNames.length; i++) {
                    ClassFile cf = project.getClassFile(classFileNames[i]);

                    // For each method
                    String[] methodsNames = cf.getAllMethodsNames();
					if ( methodsNames != null ) {
	                    for (int j = 0; j < methodsNames.length; j++) {
	                        ClassMethod cm = cf.getMethod(methodsNames[j]);
	
	                        // Printing class information
	                        System.out.print(cf.getClassId() + "\t" + classFileNames[i] + "\t");
	                        // Printing method information
	                        System.out.print(cm.getMethodId() + "\t" + methodsNames[j] + "\n");
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
