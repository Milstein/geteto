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
import java.util.*;

import br.jabuti.criteria.Criterion;
import br.jabuti.util.*;
import br.jabuti.project.*;


/**

 This application program reports the coverage and the covered elements
 for each test case and each method w.r.t. all criterion.

 @version: 0.00001
 @author: Auri Marcelo Rizzo Vincenzi

*/
public class ListCoveredRequirements {
    public static void usage() {
        System.out.println(ToolConstants.toolName + " v. " + ToolConstants.toolVersion);
        System.out.println("\nListCoveredRequirements usage:");
        System.out.println("-------------------\n");
        System.out.println("java br.jabuti.cmdtool.ListCoveredRequirements [-d <DIR>] -p <PROJECT_NAME>\n");
        System.out.println("      [-d <DIR>]              Optional parameter. Specify the directory where the Project");
        System.out.println("                              is located. If not specified, the current directory is assumed.");
        System.out.println("      -p <PROJECT_NAME>       Specify the name of the Project to be used. The");
        System.out.println("                              Project must be a valid Project file (.jba) generated by");
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

                System.out.println(project.toString());
		                        
                // For each class
                String[] classFileNames = project.getAllClassFileNames();

                for (i = 0; i < classFileNames.length; i++) {
                    System.out.println("\nEvaluating ClassFile: " + classFileNames[i]);
                	
                    ClassFile cf = project.getClassFile(classFileNames[i]);
                
                    // For each method
                    String[] methodsNames = cf.getAllMethodsNames();

					if ( methodsNames == null )
						continue;

                    for (int j = 0; j < methodsNames.length; j++) {
                        System.out.println("\nEvaluating Method: " + methodsNames[j]);
                        ClassMethod cm = cf.getMethod(methodsNames[j]);
                		
                        // For each test case...
                        Object[] tcNames = TestSet.getTestCaseLabels();

                        Arrays.sort(tcNames);
                		
                        for (int k = 0; k < tcNames.length; k++) {
                            String tcLabel = (String) tcNames[k];

                            System.out.println("\n\nCovered Requirements by Test Case: " + tcLabel);
                			
                            HashSet req = new HashSet();

                            // COVERERD REQUIREMENTS FOR ALL-PRIMARY-NODES
                                System.out.println("\nCovered Primary-Nodes");
                                req = cm.getCoveredRequirementsByTestCase(Criterion.PRIMARY_NODES, tcLabel);
                                Iterator it = req.iterator();

                                while (it.hasNext()) {
                                    System.out.print(it.next() + " ");
                                }

                            // COVERERD REQUIREMENTS FOR ALL-SECONDARY-NODES
                                System.out.println("\nCovered Secondary-Nodes");
                                req = cm.getCoveredRequirementsByTestCase(Criterion.SECONDARY_NODES, tcLabel);
                                it = req.iterator();

                                while (it.hasNext()) {
                                    System.out.print(it.next() + " ");
                                }
                                
                            // COVERERD REQUIREMENTS FOR ALL-PRIMARY-EDGES
                                System.out.println("\nCovered Primary-Edges");
                                req = cm.getCoveredRequirementsByTestCase(Criterion.PRIMARY_EDGES, tcLabel);
                                it = req.iterator();

                                while (it.hasNext()) {
                                    System.out.print(it.next() + " ");
                                }

                            // COVERERD REQUIREMENTS FOR ALL-SECONDARY-EDGES
                                System.out.println("\nCovered Secondary-Edges");
                                req = cm.getCoveredRequirementsByTestCase(Criterion.SECONDARY_EDGES, tcLabel);
                                it = req.iterator();

                                while (it.hasNext()) {
                                    System.out.print(it.next() + " ");
                                }
                            
                            // COVERERD REQUIREMENTS FOR ALL-PRIMARY-USES
                                System.out.println("\nCovered Primary-Uses");
                                req = cm.getCoveredRequirementsByTestCase(Criterion.PRIMARY_USES, tcLabel);
                                it = req.iterator();

                                while (it.hasNext()) {
                                    System.out.print(it.next() + " ");
                                }
                                
                            // COVERERD REQUIREMENTS FOR ALL-SECONDARY-USES
                                System.out.println("\nCovered Secondary-Uses");
                                req = cm.getCoveredRequirementsByTestCase(Criterion.SECONDARY_USES, tcLabel);
                                it = req.iterator();

                                while (it.hasNext()) {
                                    System.out.print(it.next() + " ");
                                }

                            // COVERERD REQUIREMENTS FOR ALL-PRIMARY-POT-USES
                                System.out.println("\nCovered Primary-Potential-Uses");
                                req = cm.getCoveredRequirementsByTestCase(Criterion.PRIMARY_POT_USES, tcLabel);
                                it = req.iterator();

                                while (it.hasNext()) {
                                    System.out.print(it.next() + " ");
                                }
                                
                            // COVERERD REQUIREMENTS FOR ALL-SECONDARY-POT-USES
                                System.out.println("\nCovered Secondary-Potential-Uses");
                                req = cm.getCoveredRequirementsByTestCase(Criterion.SECONDARY_POT_USES, tcLabel);
                                it = req.iterator();

                                while (it.hasNext()) {
                                    System.out.print(it.next() + " ");
                                }
                        }
                    }
                }
            } catch (Exception e) {
                ToolConstants.reportException(e, ToolConstants.STDERR );
                System.exit(0);
            }
        } else {
            usage();
        }
    }
}
