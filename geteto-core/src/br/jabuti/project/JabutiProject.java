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


package br.jabuti.project;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import br.jabuti.criteria.AbstractCriterion;
import br.jabuti.criteria.Criterion;
import br.jabuti.graph.datastructure.dug.CFG;
import br.jabuti.lookup.java.bytecode.Program;
import br.jabuti.util.ToolConstants;

/**
 * This class represents a JaBUTi project.
 * 
 * It groups the information about a given project and stores the dinamic
 * information to be presented in the GUI.
 * 
 * It is basically composed by a set of {@link ClassFile} objects.
 * 
 * @version: 1.0
 * @author: Auri Vincenzi
 */
public class JabutiProject {

	private Program prog;

	private List<String > classpath;
	
	private String junitSrcDir;
	
	private String junitBinDir;
	
	private String junitTestSet;
	
	private String junitJar;

	/**
	 * Set of packages that must be avoided.
	 */
	private Set<String> avoidSet;
	
	/**
	 * Set of classes to be instrumented;
	 */
	private Set<String> instrSet = null;

	/**
	 * Cache of classfiles to be instrumented (the key is the class name). 
	 */
	private Map<String, ClassFile> instrClassTable;

	/*
	 * Checks whether the status of the project has changed
	 */
	private boolean progChanged = false;
	
	private boolean instrChanged = false;
	
	private boolean execChanged = false;
	
	private boolean coverageChanged = false;
			
	private boolean mobility = false;

		/**
	 * The coverage w.r.t the entire program... The sum of the coverage of each
	 * class file
	 */
	private Coverage[] projCoverage;

	/**
	 * Project filename.
	 */
	private File saveName;
	
	/**
	 * Trace filename.
	 */
	private String traceFileName;

	/**
	 * Instrumented project Jar package.
	 */
	private String jarFileName;

	public JabutiProject()
	{
	}

	/**
	 * Creates a empty JaBUTi Project
	 * 
	 * @param cpath
	 *            a {@link String} corresponding to the classpath to run the
	 *            base class.
	 * @throw {@link Exception} in case occurs any problem on identifying the
	 *        other classes necessary to run the base class.
	 */
	public JabutiProject(String cpath) throws Exception
	{
		prog = new Program(true, null, cpath);
		classpath = new ArrayList<String>();
		addPath2Classpath(cpath);

		junitSrcDir = new String();
		junitBinDir = new String();
		junitTestSet = new String();
		junitJar = new String();

		avoidSet = new HashSet();
		instrSet = new HashSet();

		// instrClassTable = new HashMap();
		instrClassTable = new Hashtable();

		progChanged = false;
		instrChanged = false;
		execChanged = false;
		coverageChanged = false;
		mobility = false;

		// Setting the coverage of the project as zero
		projCoverage = new Coverage[Criterion.NUM_CRITERIA];
		for (int i = 0; i < Criterion.NUM_CRITERIA; i++) {
			projCoverage[i] = new Coverage();
		}
	}

	/**
	 * Creates a project from a JaBUTi Project
	 * 
	 * @param prj
	 *            a {@link JabutiProject} object.
	 * @throw {@link Exception} in case occurs any problem on identifying the
	 *        other classes necessary to run the base class.
	 */
	public JabutiProject(JabutiProject prj) throws Exception {
		prog = new Program(true, null, prj.getClasspath());

		junitSrcDir = new String(prj.getJunitSrcDir());
		junitBinDir = new String(prj.getJunitBinDir());
		junitTestSet = new String(prj.getJunitTestSet());
		junitJar = new String(prj.getJUnitJar());
	
		
		avoidSet = (HashSet) prj.avoidSet.clone();
		instrSet = (HashSet) prj.instrSet.clone();

		// instrClassTable = (HashMap) prj.instrClassTable.clone();
		instrClassTable = (Hashtable) prj.instrClassTable.clone();

		progChanged = prj.progChanged;
		instrChanged = prj.instrChanged;
		execChanged = prj.execChanged;
		coverageChanged = prj.coverageChanged;
		mobility = prj.mobility;

		// Setting the coverage of the project as zero
		projCoverage = new Coverage[Criterion.NUM_CRITERIA];
		for (int i = 0; i < Criterion.NUM_CRITERIA; i++) {
			projCoverage[i] = new Coverage(prj.getProjectCoverage(i));
		}
	}

	public void addPath2Classpath(String path) {
		classpath.add(path);
	}
	
	public String getJUnitJar() {
		return junitJar;
	}

	public void setJUnitJar(String s) {
		junitJar = s;
	}

	public String getJunitTestSet() {
		return junitTestSet;
	}

	public void setJunitTestSet(String s) {
		junitTestSet = s;
	}

	public String getJunitBinDir() {
		return junitBinDir;
	}

	public void setJunitBinDir(String s) {
		junitBinDir = s;
	}

	public String getJunitSrcDir() {
		return junitSrcDir;
	}

	public void setJunitSrcDir(String s) {
		junitSrcDir = s;
	}

	public void saveProject() throws Exception {
		if (getProjectFile() == null)
			return;
		save(getProjectFile(), true);
	}

	/**
	 * This method saves a JabutiProject in a XML file. It first try to save the
	 * project in a TMP file. If successful, the TMP file is renamed to the
	 * correct, JabutiProject file name.
	 */
	public void save(File fileName, boolean force) 
	{
		if (! changed() && ! force) {
			return;
		}
		
		File tmpFile = null;
		Project2XML p2xml = new Project2XML();
		try {
			tmpFile = File.createTempFile("_jbttmp_", null);
			p2xml.project2XML(this, tmpFile);
			fileName.delete();
			if (tmpFile.renameTo(fileName)) {
				setChanged(false);
			}
		} catch (Exception e) {
			ToolConstants.reportException(e, ToolConstants.STDERR );
			throw new RuntimeException("Error saving project data");
		} finally {
			tmpFile.delete();
		}
	}

	static public JabutiProject reloadProj(String fileName, String cp, boolean full) {
		/*
		 * Initialization when using the DOM parser JabutiProject jbtProj =
		 * XML2Project.newProjectInstance( fileName );
		 */
		XML2Project jfs = new XML2Project(cp);
		jfs.parse(fileName, full);

		JabutiProject jbtProj = jfs.getParsedJabutiProject();

		if (jbtProj != null)
			jbtProj.setChanged(false);
		return jbtProj;
	}
	
	static public JabutiProject reloadProj(String fileName, boolean full) {
		return JabutiProject.reloadProj(fileName, null, full);
	}

	public void updateProjectCoverage() {

		/** Restarting current coverage */
		for (int i = 0; i < Criterion.NUM_CRITERIA; i++) {
			projCoverage[i] = new Coverage();
		}

		Iterator it = instrClassTable.values().iterator();

		while (it.hasNext()) {
			ClassFile cf = (ClassFile) it.next();

			cf.updateClassFileCoverage();

			for (int i = 0; i < Criterion.NUM_CRITERIA; i++) {
				Coverage classCoverage = cf.getClassFileCoverage(i);
				int totalCov = projCoverage[i].getNumberOfCovered()
						+ classCoverage.getNumberOfCovered();

				int totalReq = projCoverage[i].getNumberOfRequirements()
						+ classCoverage.getNumberOfRequirements();

				projCoverage[i].setNumberOfCovered(totalCov);
				projCoverage[i].setNumberOfRequirements(totalReq);
			}
		}
	}

	public String toString() {
		String out = new String(ToolConstants.toolName + " Project\n");

		out = out + "Current Coverage:\n";
		out = out + coverage2TXT("\t");

		out = out + "Instrumented Class Files:\n";
		Iterator it = instrClassTable.values().iterator();

		while (it.hasNext()) {
			ClassFile cf = (ClassFile) it.next();
			out = out + cf.toString("\t");
		}

		return out;
	}

	public String coverage2TXT(String prefix) {
		String out = prefix
				+ AbstractCriterion.getName(Criterion.PRIMARY_NODES) + ": "
				+ getProjectCoverage(Criterion.PRIMARY_NODES).toString() + "\n";
		out = out + prefix
				+ AbstractCriterion.getName(Criterion.SECONDARY_NODES) + ": "
				+ getProjectCoverage(Criterion.SECONDARY_NODES).toString()
				+ "\n";
		out = out + prefix + AbstractCriterion.getName(Criterion.PRIMARY_EDGES)
				+ ": " + getProjectCoverage(Criterion.PRIMARY_EDGES).toString()
				+ "\n";
		out = out + prefix
				+ AbstractCriterion.getName(Criterion.SECONDARY_EDGES) + ": "
				+ getProjectCoverage(Criterion.SECONDARY_EDGES).toString()
				+ "\n";
		out = out + prefix + AbstractCriterion.getName(Criterion.PRIMARY_USES)
				+ ": " + getProjectCoverage(Criterion.PRIMARY_USES).toString()
				+ "\n";
		out = out + prefix
				+ AbstractCriterion.getName(Criterion.SECONDARY_USES) + ": "
				+ getProjectCoverage(Criterion.SECONDARY_USES).toString()
				+ "\n";
		out = out + prefix
				+ AbstractCriterion.getName(Criterion.PRIMARY_POT_USES) + ": "
				+ getProjectCoverage(Criterion.PRIMARY_POT_USES).toString()
				+ "\n";
		out = out + prefix
				+ AbstractCriterion.getName(Criterion.SECONDARY_POT_USES)
				+ ": "
				+ getProjectCoverage(Criterion.SECONDARY_POT_USES).toString()
				+ "\n";
		return out;
	}

	/***************************************************************************
	 * Get and Set Methods implementation
	 **************************************************************************/

	public String getClasspath() {
		return classpath;
	}

	public void setClasspath(String s) {
		if (classpath.equals(s)) {
			return;
		}
		execChanged = true;
		classpath = s;
	}

	public Set<String> getAvoid() {
		return avoidSet;
	}

	public Set<String> getInstr() {
		return instrSet;
	}

	public Program getProgram() {
		if (progChanged) {
			String s = list(avoidSet);

			try {
				prog = new Program(true, s, classpath);
			} catch (Exception e) {
			}
		}
		return prog;
	}


	public boolean isMobility() {
		return (mobility);
	}

	public void setMobility(boolean b) {
		if (mobility == b) {
			return;
		}
		execChanged = true;
		mobility = b;
	}

	public boolean addAvoid(String x) {
		if (avoidSet.contains(x)) {
			return false;
		}
		avoidSet.add(x);
		progChanged = true;
		return true;
	}

	public boolean delAvoid(String x) {
		if (!avoidSet.contains(x)) {
			return false;
		}
		avoidSet.remove(x);
		progChanged = true;
		return true;
	}

	public boolean addInstr(String x) {
		if (instrSet.contains(x)) {
			return false;
		}
		instrSet.add(x);
		instrChanged = true;
		return true;
	}

	public boolean delInstr(String x) {
		if (!instrSet.contains(x)) {
			return false;
		}
		instrSet.remove(x);
		instrChanged = true;
		return true;
	}

	/**
	 * Creates a new ClassFile object of a class to be instrumented
	 */
	public ClassFile createNewClassFileEntry(String cName) {
		ClassFile classF = null;
		if (!instrClassTable.containsKey(cName)) {
			// Creating a new class file object
			// System.out.println( "Creating a new entry for: " + cName );
			classF = new ClassFile(this, cName, getClassId(cName));
			instrClassTable.put(cName, classF);
		} else {
			classF = (ClassFile) instrClassTable.get(cName);
		}
		return classF;
	}

	/**
	 * This method is responsible to generate the complete set of objects
	 * corresponding to this project.
	 */
	public void rebuild() {
		// Verifying which classes are not to be instrumented
		// anymore
		if (instrClassTable.size() > 0) {
			Iterator it = instrClassTable.keySet().iterator();

			while (it.hasNext()) {
				String cName = (String) it.next();
				if (!instrSet.contains(cName)) {
					instrClassTable.remove(cName);
				}
			}
		}

		// Creating new {@link ClassFile} objects if necessary
		Iterator it = instrSet.iterator();
		while (it.hasNext()) {
			String cName = (String) it.next();

			if (!instrClassTable.containsKey(cName)) {
				// Creating a new class file object
				// System.out.println( "Creating a new entry for: " + cName );
				instrClassTable.put(cName, new ClassFile(this, cName,
						getClassId(cName)));
			}
		}

		// Updatting the coverage considering the
		// set of instrumented classes
		updateProjectCoverage();
	}

	private String list(HashSet x) {
		String ret = "";
		Iterator it = x.iterator();

		while (it.hasNext()) {
			String s = (String) it.next();

			ret += s + " ";
		}
		return ret;
	}

	public boolean changed() {
		return instrChanged || progChanged || execChanged || coverageChanged;
	}

	public void execChanges() {
		execChanged = true;
	}

	public void coverageChanges() {
		coverageChanged = true;
	}

	public void coverageUpdated() {
		coverageChanged = false;
	}

	public boolean coverageChanged() {
		return coverageChanged;
	}

	private void setChanged(boolean b) {
		instrChanged = progChanged = execChanged = coverageChanged = b;
	}

	/**
	 * This method returns the uniq identifier of a given class name in the
	 * current project.
	 * 
	 * @return -1 when the class name is not found, or a positive integer number
	 *         when the class is found
	 */
	public int getClassId(String className) {
		// Getting the vector with all user classes...
		String[] userClass = prog.getCodeClasses();

		int classId = -1;

		// Finding the classId for this particular class
		for (int i = 0; (i < userClass.length) && (classId == -1); i++) {
			if (className.equals(userClass[i])) {
				classId = i;
			}
		}
		return classId;
	}

	/*
	 * public HashMap getClassFilesTable() { return instrClassTable; }
	 */

	public Hashtable getClassFilesTable() {
		return instrClassTable;
	}

	public String[] getAllClassFileNames() {
		if (instrClassTable.size() > 0) {
			return (String[]) instrClassTable.keySet().toArray(new String[0]);
		} else {
			return null;
		}
	}

	public ClassFile getClassFile(String name) {

		if (name != null && instrClassTable.containsKey(name)) {
			return (ClassFile) instrClassTable.get(name);
		} else {
			return null;
		}
	}

	public Coverage getProjectCoverage(int c) {
		if ((c >= 0) && (c < Criterion.NUM_CRITERIA)) {
			return projCoverage[c];
		} else {
			return null;
		}
	}

	public int getNumberOfClasses() {
		return instrClassTable.size();
	}

	// The file representing the current active project
	public File getProjectFile() {
		return saveName;
	}

	// The name of the file representing the current active project
	public String getProjectFileName() {
		if (saveName != null)
			return saveName.toString();
		return null;
	}

	// The name of the file representing the trace file of the current active
	// project
	public String getTraceFileName() {
		return traceFileName;
	}

	// The name of the file representing the trace file of the current active
	// project
	public String getInstrumentedJarFileName() {
		return jarFileName;
	}

	// Chanege the current project file and it respective trace file
	public void setProjectFile(File pf) {
		saveName = pf;
		traceFileName = null;
		jarFileName = null;
		if (saveName != null) {
			String pclass = saveName.toString();
			String baseName = pclass.substring(0, pclass.length()
					- ToolConstants.traceExtension.length());
			traceFileName = baseName + ToolConstants.traceExtension;
			jarFileName = baseName + ToolConstants.instExtension;
		}
	}

	public String getProjectResource(String item)
			throws ClassNotFoundException, IOException {

		StringTokenizer st = new StringTokenizer(getClasspath(),
				File.pathSeparator);

		URL[] arrUrl = new URL[st.countTokens()];
		int i = 0;
		for (; st.hasMoreTokens(); i++) {
			arrUrl[i] = new File(st.nextToken()).toURI().toURL();
		}
		ClassLoader cLoader = new URLClassLoader(arrUrl);
		Class clazz = Class.forName(item, false, cLoader);

		java.security.ProtectionDomain pd = clazz.getProtectionDomain();
		if (pd == null)
			return null;
		java.security.CodeSource cs = pd.getCodeSource();
		if (cs == null)
			return null;
		java.net.URL url = cs.getLocation();
		if (url == null)
			return null;
		java.io.File f = new File(url.getFile());

		return f.getCanonicalPath();
	}

	public void closeProject()
	{
		// TODO Auto-generated method stub
		
	}
}
