package br.jabuti.webservices;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.jar.JarFile;
import java.util.zip.ZipFile;

import br.jabuti.criteria.AbstractCriterion;
import br.jabuti.criteria.Criterion;
import br.jabuti.junitexec.JUnitJabutiCore;
import br.jabuti.lookup.Program;
import br.jabuti.probe.ProberInstrum;
import br.jabuti.project.Coverage;
import br.jabuti.project.JabutiProject;
import br.jabuti.project.TestSet;

public class S_Project {
	
	final static String JABUTI_PROJECT_HOME = "/home/andre/arquivos/jabutiservices/";
	final static String JUNIT_FILE = "/home/andre/arquivos/QualipsoFolder/Eclipse/commonlib/junit-4.4.jar";
	public final static String JABUTI_FILE = "/home/andre/arquivos/QualipsoFolder/Eclipse/commonlib/jabuticlasses.jar";
	//public final static String JABUTISERVICE_HOME = "/home/andre/arquivos/QualipsoFolder/Eclipse/workspace/jabutiprojectSvn/build";
	//public final static String JABUTISERVICE_HOME = "/home/andre/ifiles/doctoral/install/eclipseworkspace/jabutiprojectSvn/build";
	//public final static String JABUTITOMCAT_HOME = "/home/andre/arquivos/QualipsoFolder/Eclipse/workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/jabutiprojectSvn/WEB-INF/classes";
	//public final static String JABUTITOMCAT_HOME = "/home/andre/arquivos/QualipsoFolder/Eclipse/workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/jabutiprojectSvn/WEB-INF/classes";
	
	/**
	 * 
	 * @param projectname
	 * @param file
	 * @return
	 */
	public String[] create(String projectname, File file)
	{
		String ret[] = new String[2];
		
		String id = String.valueOf(System.currentTimeMillis());
		File projdir = new File(S_Project.JABUTI_PROJECT_HOME + id);
		projdir.mkdir();
				
		saveFile(file, S_Project.JABUTI_PROJECT_HOME + id + "/file.jar");
		
		ret[0] = id;			//project id			
		ret[1] = projectname;	//password
		return ret;
	}
	
	/**
	 * 
	 * @param id
	 * @param password
	 * @param classes
	 * @return
	 */
	public String[] selectClassesToInstrument(String id, String password, String classes[])
	{
		String ret[] = new String[1];
		if(verify(id, password))
		{
			String classpath = S_Project.JABUTI_PROJECT_HOME + id;
			String projfilename = classpath + "/proj.jbt";
			String instrumentedfilename = classpath + "/file_inst.jar";
			String projjarfilename = classpath + "/file.jar";
			
			try {
				ZipFile zippedFile = new JarFile(projjarfilename);
				Program program = new Program(zippedFile, true, null);

				HashSet toInstrument = getClassesByExpression(program, classes);
				
				JabutiProject jbtProject = new JabutiProject(program, classpath);
				jbtProject.setProjectFile( new File(projfilename) );
				
				//select instrument files
				Iterator it = toInstrument.iterator();
				while(it.hasNext())
					jbtProject.addInstr((String) it.next());
				
				jbtProject.rebuild();
				TestSet.initialize( jbtProject, jbtProject.getTraceFileName() );		
				ProberInstrum.instrumentProject(jbtProject, instrumentedfilename);
				jbtProject.saveProject();
				ret[0] = "Classes are successfully instrumented.";
			}
			catch(Exception ex) {
				ex.printStackTrace();
				ret[0] = "An error happened during the instrumentation process.";
			}
		}
		else
			ret[0] = "Invalid id and password. No classes are instrumented.";
		
		return ret;
	}
	
	/**
	 * 
	 * @param id
	 * @param password
	 * @param testfile
	 * @param testsuiteclassname
	 * @return
	 */
	public String[] selectTestCases(String id, String password, File testfile, String testsuiteclassname)
	{
		String ret[] = new String[1];
		if(verify(id, password)) {
			String testjarfilename = S_Project.JABUTI_PROJECT_HOME + id + "/file_test.jar";
			String instrumentedfilename = S_Project.JABUTI_PROJECT_HOME + id + "/file_inst.jar";			
			String classpath = testjarfilename + File.pathSeparator + instrumentedfilename;
			//It is necessary to include the external jar files used in the project.
			classpath += File.pathSeparator + "/home/aendo/arquivos/QualipsoFolder/Eclipse/commonlib/commons-email-1.1/commons-email-1.1.jar";
			String projfilename = S_Project.JABUTI_PROJECT_HOME + id + "/proj.jbt";
			
			//save test case file
			saveFile(testfile,  testjarfilename);

			//run test cases
			HashMap<String, String> hm = null;
			try {
				hm = JUnitJabutiCore.runCollecting(classpath, testsuiteclassname, System.out);
				Set<String> testSet = hm.keySet();
				JabutiProject jbtProject = JabutiProject.reloadProj(projfilename, true);
				JUnitJabutiCore.runInstrumenting(classpath, testsuiteclassname, jbtProject.getTraceFileName(), testSet, System.out);
				TestSet.loadTraceFile(jbtProject);
				TestSet.updateOverallCoverage( jbtProject );
				System.out.println(jbtProject.coverage2TXT(""));

				// Saving the updated project
				jbtProject.saveProject();
				ret[0] = "Test cases are successfully included and executed.";
			} 
			catch (Exception e) {
				e.printStackTrace();
				ret[0] = "An error was found during the test cases execution.";
			}
		}
		else
			ret[0] = "Invalid id and password. No test cases were added.";
		
		return ret;
	}
	
	/**
	 * 
	 * @param id
	 * @param password
	 * @return
	 */
	public CoverageDetails[] getCoverageDetaisByProject(String id, String password)
	{
		if(verify(id, password))
		{
			String projfile = S_Project.JABUTI_PROJECT_HOME + id + "/proj.jbt";
			JabutiProject jbtProject = JabutiProject.reloadProj(projfile, true);
			CoverageDetails ret[] = new CoverageDetails[Criterion.NUM_CRITERIA];
			for (int i = 0; i < Criterion.NUM_CRITERIA; i++) {
				ret[i] = new CoverageDetails();
				ret[i].setCriterionname(AbstractCriterion.getName(i));
				Coverage c = jbtProject.getProjectCoverage(i);
				ret[i].setNumberOfelements(c.getNumberOfRequirements());
				ret[i].setNumberOfcoveredelements(c.getNumberOfCovered());
				ret[i].setCoveragepercentage(c.getPercentage());
			}
			return ret;
		}
		else
			return null;
	}
	
	/**
	 * 
	 * @param id
	 * @param password
	 * @return
	 */
	private boolean verify(String id, String password)
	{
		if(!id.equals("")) {
			File projdir = new File(S_Project.JABUTI_PROJECT_HOME + id);
			return projdir.exists();
		}
		return false;
	}
	
	/**
	 * 
	 * @param prog
	 * @param classes
	 * @return
	 */
	private HashSet getClassesByExpression(Program prog, String classes[]) throws Exception
	{
		HashSet set = new HashSet();
		if(classes.length <= 0)
			throw new Exception("No class especified.");
		
		String c[] = prog.getCodeClasses();
		
		for (int i = 0; i < classes.length; i++) {			
			if(classes[i].equals("*") && i == 0) {
				for (int j = 0; j < c.length; j++) {
					set.add(c[j]);
				}
				break;
			}
			else if(!AuxiliaryOperations.isValidClassString(classes[i])) {
				throw new Exception("Invalid package or class name.");
			}
			else if(classes[i].endsWith(".*")) {		//package
				if(containsPackage(c, classes[i]))
				{
					String pack = classes[i].substring(0, classes[i].length() - 2);
					for (int j = 0; j < c.length; j++) {
						if(c[j].startsWith(pack))
							set.add(c[j]);
					}
				}
				else
					throw new Exception("A package does not exist in the project.");
			}
			else {		//a class
				if(containsClass(c, classes[i]))
					set.add(classes[i]);
				else
					throw new Exception("A class does not exist in the project.");
			}
		}
		
		//just print
		for (Iterator iterator = set.iterator(); iterator.hasNext();) {
			String object = (String) iterator.next();
			System.out.println(object);
		}
		
		return set;
	}
	
	private boolean containsClass(String classes[], String c)
	{
		for (int i = 0; i < classes.length; i++) {
			if(c.equals(classes[i]))
				return true;
		}
		return false;
	}

	private boolean containsPackage(String classes[], String pack)
	{
		pack = pack.substring(0, pack.length() - 2);
		for (int i = 0; i < classes.length; i++) {
			if(classes[i].startsWith(pack))
				return true;
		}
		return false;
	}

	
	/**
	 * 
	 * @param file
	 * @param filename
	 */
	private void saveFile(File file, String filename) {
		File cfile = new File(filename);		
		//copy jar file to the right directory
		try {
			InputStream in = new FileInputStream(file);
	        OutputStream out = new FileOutputStream(cfile);   
	        byte[] buf = new byte[1024];
	        int len;
	        while ((len = in.read(buf)) > 0) {
	            out.write(buf, 0, len);
	        }
	        in.close();
	        out.close();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}	
}
