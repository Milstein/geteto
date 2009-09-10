package br.jabuti.runner.ws;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarOutputStream;
import java.util.zip.ZipFile;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;


import br.jabuti.criteria.AbstractCriterion;
import br.jabuti.criteria.Criterion;
import br.jabuti.criteria.Requirement;
import br.jabuti.lookup.Program;
import br.jabuti.probe.desktop.ProberInstrum;
import br.jabuti.project.ClassFile;
import br.jabuti.project.ClassMethod;
import br.jabuti.project.Coverage;
import br.jabuti.project.JabutiProject;
import br.jabuti.project.TestSet;
import br.jabuti.runner.junit.JUnitJabutiCore;

public class WsProject {
	
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
		
		String id = String.valueOf(System.nanoTime());
		File projdir = new File(JABUTI_PROJECT_HOME + id);
		projdir.mkdir();
				
		saveFile(file, JABUTI_PROJECT_HOME + id + "/file.jar");
		
		//save project name
		
		ret[0] = id;			//project id			
			
		return ret;
	}
	
	/**
	 * 
	 * @param projectId
	 * @param file
	 */
	public void update(String projectId, File file)
	{
		File projdir = new File(JABUTI_PROJECT_HOME + projectId);
		projdir.mkdir();
				
		saveFile(file, JABUTI_PROJECT_HOME + projectId + "/file.jar");		
	}
	
	public void delete(String projectId)
	{
		File projdir = new File(JABUTI_PROJECT_HOME + projectId);
		VerifingData.removeDir(projdir);
		projdir.delete();
	}
	
	public void clean(String projectId)
	{
		//remove instrumented project
		File instrProj = new File(JABUTI_PROJECT_HOME + projectId+"/file_inst.jar");
		instrProj.delete();
		//remove trace file
		File traceFile = new File(JABUTI_PROJECT_HOME + projectId+"/proj.trc");
		traceFile.delete();
		//remove test cases
		File testCases = new File(JABUTI_PROJECT_HOME + projectId+"/file_test.jar");
		testCases.delete();
		//remove package with instrumented project, testcases and command lines
		File packagefile = new File(JABUTI_PROJECT_HOME + projectId + "/package.jar");
		packagefile.delete();		
	}
	
	/**
	 * 
	 * @param id
	 * @param password
	 * @param classes
	 * @return
	 */
	public String[] selectClassesToInstrument(String projid, String classes[])
	{
		String ret[] = new String[1];
		String classpath = JABUTI_PROJECT_HOME + projid;
		String projfilename = classpath + "/proj.jbt";
		String instrumentedfilename = classpath + "/file_inst.jar";
		String projjarfilename = classpath + "/file.jar";
		
		try {
			Program program = Program.createFromPackage(projjarfilename);

			HashSet toInstrument = getClassesByExpression(program, classes);
			
			JabutiProject jbtProject = new JabutiProject(program);
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
		return ret;
	}
	
	public RequiredElementsDetails[] getAllRequiredElements(String projectId)
	{
		String projfilename = JABUTI_PROJECT_HOME + projectId + "/proj.jbt";
		JabutiProject jbtProject = JabutiProject.reloadProj(projfilename, true);
		
		ArrayList ret = new ArrayList();

		Program program = jbtProject.getProgram();
		String classes[] = program.getCodeClasses();
		for (int i = 0; i < classes.length; i++) {
			ClassFile cf = jbtProject.getClassFile(classes[i]);

			HashMap methods = cf.getMethodsTable();
			Object[] names = methods.keySet().toArray(new String[0]);
			Arrays.sort(names);

			for (int x = 0; x < names.length; x++) {
				String mName = (String) names[x];
				
				RequiredElementsDetails red = new RequiredElementsDetails();
				red.setMethodName(classes[i] + "." + mName);
				ClassMethod cm = cf.getMethod(mName);
				
				br.jabuti.runner.ws.Criterion criteria[] = new br.jabuti.runner.ws.Criterion[8];
				for (int j = 0; j < Criterion.NUM_CRITERIA; j++) {
					criteria[j] = new br.jabuti.runner.ws.Criterion();
					criteria[j].setName(Criterion.names[j][0]);
					
					Criterion criterion = cm.getCriterion(j);
					Object[] requirements = criterion.getRequirements();
					String reqs[] = new String[requirements.length];
					for (int k = 0; k < requirements.length; k++) {
						reqs[k] = requirements[k].toString();
					}		
					criteria[j].setElements(reqs);
				}
				red.setCriterion(criteria);
				ret.add(red);
			}
		}		
		
		RequiredElementsDetails ret2[] = new RequiredElementsDetails[ret.size()];
		for (int i = 0; i < ret2.length; i++) {
			ret2[i] = (RequiredElementsDetails) ret.get(i);
		}
		
		return ret2;
	}
	
	public void addTestCases(String projectId, String testSuiteClass, File file) {
		String testjarfilename = JABUTI_PROJECT_HOME + projectId + "/file_test.jar";
		
		//save test case file
		saveFile(file,  testjarfilename);
		
		//save the TestSuite class name
		//to do
	}
	
	public InstrumentedProjectDetails getInstrumentedProject(String projectId)
	{
		InstrumentedProjectDetails ret = new InstrumentedProjectDetails();
		ret.setCommandLine("java -cp Jabuti-bin-2007-12-19.zip br.jabuti.junitexec.JUnitJabutiCore -trace test.trc -cp package.jar -tcClass pack.TestSuiteClass");
		ret.setFile(createPackageFile(projectId));
		
		return ret;
	}
	
	private DataHandler createPackageFile(String id)
	{
		File packagefile = null;
		//to put the  necessary jabuti files to execute the testcases
		try {
			JarFile projinstfile = new JarFile(JABUTI_PROJECT_HOME + id + "/file_inst.jar");
			JarFile projtestfile = new JarFile(JABUTI_PROJECT_HOME + id + "/file_test.jar");
			packagefile = new File(JABUTI_PROJECT_HOME + id + "/package.jar");

			FileOutputStream fos = new FileOutputStream(packagefile);
			JarOutputStream jarOut = new JarOutputStream(fos);
			
			Enumeration<JarEntry> entries = projinstfile.entries();
			byte buffer[] = new byte[1];
			int bytesLidos;
			while(entries.hasMoreElements())
			{
				JarEntry entry = entries.nextElement();
				if(!entry.isDirectory()) {
					
					JarEntry entry2 = new JarEntry(entry.getName());
					
					jarOut.putNextEntry(entry2);
					InputStream is = projinstfile.getInputStream(entry);
					
					while( (bytesLidos = is.read( buffer )) > 0 ) {
						jarOut.write( buffer, 0, bytesLidos );
					}					
				}
			}
			
			entries = projtestfile.entries();
			while(entries.hasMoreElements())
			{ 
				JarEntry entry = entries.nextElement();
				if(!entry.isDirectory()) {
					
					JarEntry entry2 = new JarEntry(entry.getName()); 
					jarOut.putNextEntry(entry2);
					InputStream is = projtestfile.getInputStream(entry);
					while( (bytesLidos = is.read( buffer )) > 0 ) {
						jarOut.write( buffer, 0, bytesLidos );
					}		
				}
			}
			jarOut.close();
			fos.close();
			
		} 
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		FileDataSource fds = new FileDataSource(packagefile);
		DataHandler datahandler = new DataHandler(fds);
		
		return datahandler;
	}
	
	/**
	 * 
	 * @param id
	 * @param traceFile
	 */
	public void sendTraceFile(String id, File traceFile)
	{
		saveFile(traceFile, JABUTI_PROJECT_HOME + id + "/proj.trc");		
		//load the new coverage
		String projfilename = JABUTI_PROJECT_HOME + id + "/proj.jbt";
		JabutiProject jbtProject = JabutiProject.reloadProj(projfilename, true);	

		TestSet.loadTraceFile(jbtProject);
		TestSet.updateOverallCoverage( jbtProject );		
		
		// Saving the updated project
		try {
			jbtProject.saveProject();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public CoverageCriterionDetails[] getCoverageByCriteria(String projectId)
	{
		String projfile = JABUTI_PROJECT_HOME + projectId + "/proj.jbt";
		JabutiProject jbtProject = JabutiProject.reloadProj(projfile, true);
		
		CoverageCriterionDetails details[] = new CoverageCriterionDetails[Criterion.NUM_CRITERIA];
		for (int i = 0; i < Criterion.NUM_CRITERIA; i++) {
			details[i] = new CoverageCriterionDetails();
			details[i].setCriterionName(AbstractCriterion.getName(i));
			Coverage coverage = jbtProject.getProjectCoverage(i);
			details[i].setNumberOfElements(coverage.getNumberOfRequirements());
			details[i].setNumberOfCoveredElements(coverage.getNumberOfCovered());
			details[i].setCoveragePercentage(coverage.getPercentage());
		}
		return details;
	}
	
	public CoverageDetails[] getCoverageByClasses(String projectId)
	{
		String projfile = JABUTI_PROJECT_HOME + projectId + "/proj.jbt";
		JabutiProject jbtProject = JabutiProject.reloadProj(projfile, true);
		
		HashMap<String, ClassFile> allClasses = jbtProject.getClassFilesTable();
		Object[] classNames = allClasses.keySet().toArray();
		Arrays.sort(classNames);
		CoverageDetails details[] = new CoverageDetails[classNames.length];
		for (int i = 0; i < classNames.length; i++) {
			details[i] = new CoverageDetails();
			String cName = (String) classNames[i];
			ClassFile cf = (ClassFile) allClasses.get(cName);			
			
			details[i].setName(cName);
			
			CoverageCriterionDetails coverDetails[] = new CoverageCriterionDetails[Criterion.NUM_CRITERIA];
			for (int j = 0; j < Criterion.NUM_CRITERIA; j++) {
				coverDetails[j] = new CoverageCriterionDetails();
				Coverage cCov = cf.getClassFileCoverage(j);
				
				coverDetails[j].setCriterionName(AbstractCriterion.getName(j));
				coverDetails[j].setNumberOfElements(cCov.getNumberOfRequirements());
				coverDetails[j].setNumberOfCoveredElements(cCov.getNumberOfCovered());
				coverDetails[j].setCoveragePercentage(cCov.getPercentage());
			}			
			details[i].setCriteria(coverDetails);
		}
		
		return details;
	}
	
	public CoverageDetails[] getCoverageByMethods(String projectId) {
		String projfile = JABUTI_PROJECT_HOME + projectId + "/proj.jbt";
		JabutiProject jbtProject = JabutiProject.reloadProj(projfile, true);
		
		HashMap<String, ClassFile> allClasses = jbtProject.getClassFilesTable();
		Object[] classNames = allClasses.keySet().toArray();
		Arrays.sort(classNames);
		
		ArrayList<CoverageDetails> ret = new ArrayList<CoverageDetails>();
		
		for (int i = 0; i < classNames.length; i++) {
			
			String cName = (String) classNames[i];
			ClassFile cf = (ClassFile) allClasses.get(cName);			
			
			HashMap methods = cf.getMethodsTable();
			Object[] methodNames = methods.keySet().toArray(new String[0]);
			Arrays.sort(methodNames);			
			for (int j = 0; j < methodNames.length; j++) {
				String mName = (String) methodNames[j];
				ClassMethod cm = (ClassMethod) methods.get(mName);				

				CoverageDetails detail = new CoverageDetails();
				detail.setName(cName + "." + mName);

				CoverageCriterionDetails coverDetails[] = new CoverageCriterionDetails[Criterion.NUM_CRITERIA];
				for (int k = 0; k < Criterion.NUM_CRITERIA; k++) {
					coverDetails[k] = new CoverageCriterionDetails();
					Coverage mCov = cm.getClassMethodCoverage(k);
					coverDetails[k].setCriterionName(AbstractCriterion.getName(k));
					coverDetails[k].setNumberOfElements(mCov.getNumberOfRequirements());
					coverDetails[k].setNumberOfCoveredElements(mCov.getNumberOfCovered());
					coverDetails[k].setCoveragePercentage(mCov.getPercentage());
				}			
				
				detail.setCriteria(coverDetails);
				ret.add(detail);
			}			
		}
		
		CoverageDetails details[] = new CoverageDetails[ret.size()];
		for (int i = 0; i < details.length; i++) {
			details[i] = ret.get(i);
		}
		return details;
	}
	
	public Method[] getRequiredElementsByCriterion(String projectId, String criterion) {
		String projfile = JABUTI_PROJECT_HOME + projectId + "/proj.jbt";
		JabutiProject jbtProject = JabutiProject.reloadProj(projfile, true);
		HashMap<String, ClassFile> allClasses = jbtProject.getClassFilesTable();
		Object[] classNames = allClasses.keySet().toArray();
		Arrays.sort(classNames);
		
		int criterionnumber = -1;
		for(int i = 0; i < Criterion.NUM_CRITERIA; i++)
		{
			if(Criterion.names[i][0].equals(criterion)) {
				criterionnumber = i;
				break;
			}
		}
		if(criterionnumber == -1)
		{
			//exception here
			System.out.println("error");
		}
		
		ArrayList<Method> ret = new ArrayList<Method>();

		for (int i = 0; i < classNames.length; i++) {
			
			String cName = (String) classNames[i];
			ClassFile cf = (ClassFile) allClasses.get(cName);			
			
			HashMap methods = cf.getMethodsTable();
			Object[] methodNames = methods.keySet().toArray(new String[0]);
			Arrays.sort(methodNames);			
			for (int j = 0; j < methodNames.length; j++) {
				String mName = (String) methodNames[j];
				ClassMethod cm = (ClassMethod) methods.get(mName);
				
				Method method = new Method();
				method.setName(cName + "." + mName);
				
				Criterion critdetails = cm.getCriterion(criterionnumber);
				Object requirements[] = critdetails.getRequirements();
				String requirements2[] = new String[requirements.length];
				for (int k = 0; k < requirements.length; k++) {
					requirements2[k] = requirements[k].toString();
				}
				
				method.setElements(requirements2);
				ret.add(method);
			}
		}
		
		Method ret1[] = new Method[ret.size()];
		for (int i = 0; i < ret1.length; i++) {
			ret1[i] = ret.get(i);
		}
		
		return ret1;
	}
	
	public MethodDetails[] getAllCoveredAndUncoveredRequiredElements(String projectId) {
		String projfile = JABUTI_PROJECT_HOME + projectId + "/proj.jbt";
		JabutiProject jbtProject = JabutiProject.reloadProj(projfile, true);
		HashMap<String, ClassFile> allClasses = jbtProject.getClassFilesTable();
		Object[] classNames = allClasses.keySet().toArray();
		Arrays.sort(classNames);
				
		ArrayList<MethodDetails> ret = new ArrayList<MethodDetails>();

		for (int i = 0; i < classNames.length; i++) {
			
			String cName = (String) classNames[i];
			ClassFile cf = (ClassFile) allClasses.get(cName);			
			
			HashMap methods = cf.getMethodsTable();
			Object[] methodNames = methods.keySet().toArray(new String[0]);
			Arrays.sort(methodNames);			
			for (int j = 0; j < methodNames.length; j++) {
				String mName = (String) methodNames[j];
				ClassMethod cm = (ClassMethod) methods.get(mName);
				
				MethodDetails methoddetails = new MethodDetails();
				methoddetails.setMethodName(cName + "." + mName);
				CriterionCoveredUncovered criteria[] = new CriterionCoveredUncovered[Criterion.NUM_CRITERIA];
				
				for(int k = 0; k < Criterion.NUM_CRITERIA; k++)
				{
					criteria[k] = new CriterionCoveredUncovered();
					criteria[k].setName(Criterion.names[k][0]);
					
					Criterion critdetails = cm.getCriterion(k);
					Object requirements[] = critdetails.getRequirements();
					Set<Requirement> covered = critdetails.getCoveredRequirements();
					ArrayList<String> coveredelem = new ArrayList<String>();
					ArrayList<String> uncoveredelem = new ArrayList<String>();
					
					for (int l = 0; l < requirements.length; l++) {
						if(covered.contains(requirements[l])) {	//covered
							coveredelem.add(requirements[l].toString());
						}
						else {	//uncovered
							uncoveredelem.add(requirements[l].toString());
						}
						
					}
					String temp[] = new String[coveredelem.size()];
					for (int l = 0; l < temp.length; l++)
						temp[l] = coveredelem.get(l);
					
					criteria[k].setCoveredElements(temp);
					
					temp = new String[uncoveredelem.size()];
					for (int l = 0; l < temp.length; l++)
						temp[l] = uncoveredelem.get(l);

					criteria[k].setUncoveredElements(temp);
				}
				methoddetails.setCriteria(criteria);
				ret.add(methoddetails);
			}
		}
		
		MethodDetails ret1[] = new MethodDetails[ret.size()];
		for (int i = 0; i < ret1.length; i++) {
			ret1[i] = ret.get(i);
		}
		
		return ret1;	
	}
	
	/****************************************************************************************************/
	//old methods
	
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
				hm = JUnitJabutiCore.collect(classpath, testsuiteclassname, System.out);
				Set<String> testSet = hm.keySet();
				JabutiProject jbtProject = JabutiProject.reloadProj(projfilename, true);
				JUnitJabutiCore.instrument(classpath, testsuiteclassname, jbtProject.getTraceFileName(), testSet, System.out);
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
