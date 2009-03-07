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
import org.aspectj.apache.bcel.util.ClassPath;

import java.io.*;
import java.util.*;

import br.jabuti.instrumenter.*;
import br.jabuti.lookup.*;
import br.jabuti.project.JabutiProject;

import br.jabuti.util.*;

/**
 * 
 * This is the class that implements the functionality of a JVM code
 * instrumenter. Using such object it is possible to insert JVM code in a given
 * JVM method.
 * 
 * @version: 0.00001
 * @author: Auri Marcelo Rizzo Vincenzi M�rcio Eduardo Delamaro
 * 
 */

public class ProberLoader {
	// Classes da Jabuti adicionadas no programa
	static final private String[] ProberClasses = new String[] {
			"br.jabuti.probe.ProbedNode", "br.jabuti.probe.DefaultProber",
			"br.jabuti.probe.DefaultProberHook" };

	public static void usage() {
		System.out.println(ToolConstants.toolName + " v. "
				+ ToolConstants.toolVersion);
		System.out.println("\nProberLoader usage:");
		System.out.println("-------------------\n");
		System.out
				.println("java probe.ProberLoader [-cp <CLASSPATH>] [-d <DIR>] -p <PROJECT_NAME> <BASE_CLASS> [<PARAMETERS>]\n");
		System.out
				.println("      [-cp <CLASSPATH]        Optional parameter. Specify the class path necessary to run the");
		System.out
				.println("                              base class. This parameter is combined with the current ");
		System.out
				.println("                              CLASSPATH environent variable if any is set.");
		System.out
				.println("      [-d <DIR>]              Optional parameter. Specify the directory where the project");
		System.out
				.println("                              is located. If not specified, the current directory is assumed.");
		System.out
				.println("      -p <PROJECT_NAME>       Specify the name of the project to be used. The");
		System.out
				.println("                              project must be a valid project file (.jbt) generated by");
		System.out.println("                              JaBUTi.");
		System.out
				.println("      <BASE_CLASS>            The class file to be executed.");
		System.out
				.println("      [PARAMENTERS]           Optional parameter. If the base class requires any parameter to be");
		System.out
				.println("                              executed, it have to be specified.");
		System.out.println("\nCopyright (c) 2002-2005\n");
	}

	public static void main(String args[]) throws Throwable {
		String classPath = null;
		String workDir = null;
		String projectName = null;
		String baseClass = null; // The class file to be executed
		String[] parameters = null; // The paramenters used to execute the main
									// file

		JabutiProject project = null;
		Program program = null; // The program to be tested
		HashSet toInstrumenter = null;
		String traceFileName = null; // A given class or compressed file name

		if (args.length > 0) {

			int i = 0;

			while (args[i].startsWith("-")) {
				// -cp: Class path
				if (("-cp".equals(args[i])) && (i < args.length - 1)) {
					i++;
					classPath = args[i];
				} // -d: work directory
				else if (("-d".equals(args[i])) && (i < args.length - 1)) {
					i++;
					workDir = args[i];
				} // -p: project name
				else if (("-p".equals(args[i])) && (i < args.length - 1)) {
					i++;
					projectName = args[i];
				} else {
					System.out
							.println("Error: Unrecognized option: " + args[i]);
					System.exit(0);
				}
				i++;
			}

			if (i >= args.length) {
				System.out.println("Error: Missing base class!!!");
				System.exit(0);
			}
			// Ultimo par�metro... file seguido dos par�metros
			baseClass = args[i++];
			// System.out.println("Loaded: " + clo.loadClass(baseClass));

			parameters = new String[args.length - i];
			for (int j = 0; i < args.length; j++, i++) {
				parameters[j] = args[i];
			}

			// Checking if all essential parameters are not null
			if ((projectName == null) || (baseClass == null)) {
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

			traceFileName = absoluteName.substring(0, absoluteName.length()
					- ToolConstants.traceExtension.length())
					+ ToolConstants.traceExtension;
			// System.setProperty("DEFAULT_PROBER", traceFileName);

			System.out.println("Project Name: " + absoluteName);
			System.out.println("Trace File Name: " + traceFileName);

			try {
				File theFile = new File(absoluteName);

				if (!theFile.isFile()) // verifica se existe
				{
					System.out.println("File " + theFile.getName()
							+ " not found");
					System.exit(0);
				}

				project = JabutiProject.reloadProj(theFile.toString(), false);
				program = project.getProgram();

				toInstrumenter = project.getInstr();
			} catch (Exception e) {
				ToolConstants.reportException(e, ToolConstants.STDERR);
				System.exit(0);
			}

			DefaultProbeInsert dpi = new DefaultProbeInsert(program,
					toInstrumenter);
			Map mp = null;

			try {
				mp = dpi.instrument(project.getCFGOption());
				// substitui os objetos JavaClass por byte[]
				Iterator it0 = mp.keySet().iterator();
				Hashtable ht = new Hashtable();

				while (it0.hasNext()) {
					String clName = (String) it0.next();
					JavaClass jv = (JavaClass) mp.get(clName);
					ht.put(clName, jv.getBytes());
				}

				ClassPath cp = new ClassPath(System
						.getProperty("java.class.path"));
				for (int z1 = 0; z1 < ProberClasses.length; z1++) {
					String clName = ProberClasses[z1];
					byte[] b = cp.getBytes(clName, ".class");
					clName = clName.replace('.', '/');
					ht.put(clName, b);
				}

				mp = ht;

				// Alterado: agora o programa soh procura as classes no caminho
				// especificado
				// no argumento -cp
				/*
				 * if (classPath != null) { classPath = classPath +
				 * System.getProperty("path.separator") +
				 * System.getProperty("java.class.path"); } else { classPath =
				 * System.getProperty("java.class.path"); }
				 */

				if (classPath == null) {
					classPath = ".";
				}

				InstrumentLoader myLoader = new InstrumentLoader(mp, classPath);

				Class xcl = myLoader.loadClass("br.jabuti.probe.DefaultProber");
				Class str = myLoader.loadClass("java.lang.String");

				java.lang.reflect.Method init = xcl.getMethod("init",
						new Class[] { str });
				init.invoke((Object) null, new Object[] { traceFileName });

				//Included to start the trace collection
				java.lang.reflect.Method start = xcl.getMethod("startTrace",
						new Class[] {});
				start.invoke((Object) null, new Object[] {});

				myLoader.runClass(baseClass, parameters);
			} catch (Throwable eu) {
				System.err.println(eu);
				eu.printStackTrace();
			}
		} else {
			usage();
		}
	}
}
