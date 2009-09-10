package br.jabuti;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;


import br.jabuti.lookup.Program;

import br.jabuti.project.JabutiProject;
import br.jabuti.project.TestSet;
import br.jabuti.runner.junit.JUnitJabutiCore;

public class TestCodes {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String tcClass = "vending.DispenserTestCase", 	//nome da classe testcase
			   tracefile = "/home/aendo/arquivos/QualipsoFolder/tools/JaBUTi/vending/vending.trc", 
			   classpath = "/home/aendo/arquivos/QualipsoFolder/tools/JaBUTi/vending/vending_test.jar"; 
		
		classpath += File.pathSeparator + "/home/aendo/arquivos/QualipsoFolder/tools/JaBUTi/vending/vending_inst.jar";	//caminho que vai procurar a classe
		
		/*String tcClass = "junit.tests.AllTests", 	
		   tracefile = "/home/aendo/arquivos/QualipsoFolder/tools/JaBUTi/junit/junit.trc", 
		   classpath = "/home/aendo/arquivos/QualipsoFolder/tools/JaBUTi/junit/junit_tests.jar"; 
	
		classpath += File.pathSeparator + "/home/aendo/arquivos/QualipsoFolder/tools/JaBUTi/junit/junit_inst.jar";
		*/
		
		try {
			HashMap<String, String> hm = JUnitJabutiCore.collect(classpath, tcClass, System.out);
			
			Iterator<String> it2 = hm.keySet().iterator();
			while (it2.hasNext()) {
				String n = it2.next();
				System.out.println("TC Name: " + n + " STATUS: " + hm.get(n));		
			}
			
			Set<String> testSet = hm.keySet();
			
			JUnitJabutiCore.instrument(classpath, tcClass, tracefile, testSet, System.out);
			
			JabutiProject jpr = new JabutiProject("", "");
			
			jpr.setTraceFileName(tracefile);
			
			Program program = Program.createFromPackage("/home/aendo/arquivos/QualipsoFolder/tools/JaBUTi/vending/vending.jar");
			
			jpr.setProg(program);
			jpr.updateProjectCoverage();
			TestSet.initialize(jpr, tracefile);
			TestSet.loadTraceFile(jpr);
			TestSet.updateOverallCoverage(jpr);
			System.out.println(TestSet.coverage2TXT(""));
			
			
			//read trace
			/*DefaultTraceReader dtr = new DefaultTraceReader(new File(tracefile));

            Hashtable trace = (Hashtable) dtr.getPaths();
            int cont = 0;        	

            while (trace != null) {
                System.out.println("**************************************");
                System.out.println("Path number " + (++cont));
                System.out.println("**************************************");
                Iterator it = trace.keySet().iterator();

                while (it.hasNext()) {
                    ProbedNode pn = (ProbedNode) it.next();

                    System.out.println(pn.toString());
        			
                    String[][] nodes = (String[][]) trace.get(pn);
        			
                    for (int i = 0; i < nodes.length; i++) {
                        System.out.println("Path len: " + nodes[i].length + "\n");
                        for (int j = 0; j < nodes[i].length; j++) {
                            String n = nodes[i][j];

                            System.out.print(" " + n);
                        }
                        System.out.println();
                    }
                    System.out.println();
                }
                trace = (Hashtable) dtr.getPaths();
            }
			*/
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("end");
	}

}
