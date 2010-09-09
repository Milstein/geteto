package br.jabuti;

import java.io.File;
import java.util.HashMap;
import java.util.Set;

import br.jabuti.project.JabutiProject;
import br.jabuti.project.TestSet;
import br.jabuti.runner.junit.JUnitJabutiCore;

public class AddTestCase {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*
		String tcClass = "vending.DispenserTestCase", 
		   classpath = "/home/aendo/arquivos/QualipsoFolder/tools/JaBUTi/vending/vending_test.jar"; 	//jar file with the testcases
		String prjFile = "/home/aendo/arquivos/QualipsoFolder/tools/JaBUTi/vending/proj.jbt";
	
		classpath += File.pathSeparator + "/home/aendo/arquivos/QualipsoFolder/tools/JaBUTi/vending/file_inst.jar";	//intrumented jar file
		*/

		String tcClass = "vending.DispenserTestCase", 
		   classpath = "/home/aendo/arquivos/jabutiservices/testsamples/file_test.jar"; 	//jar file with the testcases
		String prjFile = "/home/aendo/arquivos/jabutiservices/testsamples/proj.jbt";
	
		classpath += File.pathSeparator + "/home/aendo/arquivos/jabutiservices/testsamples/file_inst.jar";	//intrumented jar file
		
		try {
			HashMap<String, String> hm = JUnitJabutiCore.collect(classpath, tcClass, System.out);
			
			Set<String> testSet = hm.keySet();
			File theFile = new File(prjFile);
			
			JabutiProject jbtProject = JabutiProject.reloadProj(theFile.toString(), true);
			
			/*File f = new File("/home/aendo/arquivos/QualipsoFolder/tools/JaBUTi/vending/vending.jar");
			ZipFile zippedFile = new JarFile(f);
			Program program = new Program(zippedFile, true, null);
			jbtProject.setProg(program);
			*/
			//System.out.println(jbtProject.coverage2TXT(""));
			
			//instrument
			JUnitJabutiCore.instrument(classpath, tcClass, jbtProject.getTraceFileName(), testSet, System.out);
			
			//read trace file
			String traceFileName = jbtProject.getTraceFileName();
			File traceFile = new File(traceFileName);
			
			int tcn = TestSet.getNumberOfTestCases();
            
			if (tcn == 0) {
            //    TestSet.initialize(jbtProject,traceFileName);
            }
            
            TestSet.loadTraceFile(jbtProject);
            
            //update coverage
            TestSet.updateOverallCoverage( jbtProject );
			
            System.out.println(jbtProject.coverage2TXT(""));
			
            // Saving the updated project
			jbtProject.saveProject();
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
