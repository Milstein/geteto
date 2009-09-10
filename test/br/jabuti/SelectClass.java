package br.jabuti;

import java.io.File;
import java.util.HashSet;
import java.util.Iterator;

import br.jabuti.lookup.Program;
import br.jabuti.probe.desktop.ProberInstrum;
import br.jabuti.project.JabutiProject;
import br.jabuti.project.TestSet;

public class SelectClass {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String classpath = "/home/aendo/arquivos/QualipsoFolder/tools/JaBUTi/vending/";
		String prjFile = classpath + "proj.jbt";
		String outName = classpath + "file_inst.jar";
		String projjar = "/home/aendo/arquivos/QualipsoFolder/tools/JaBUTi/vending/vending.jar";
		HashSet toInstrument = new HashSet();
		try {
			Program program = Program.createFromPackage(projjar);

			String classes[] = program.getCodeClasses();
			for (int i = 0; i < classes.length; i++) {
				toInstrument.add(classes[i]);
			}
			
			JabutiProject jbtProject = new JabutiProject(program);
			jbtProject.setClasspath(classpath);
			jbtProject.setProjectFile( new File( prjFile ) );
			//jbtProject.setMobility( false );
			//jbtProject.setCFGOption(  );
			
			//select instrument files
			Iterator it = toInstrument.iterator();
			while( it.hasNext() ) {
					jbtProject.addInstr( (String) it.next() );
			}	
			
			jbtProject.rebuild();
			
			TestSet.initialize( jbtProject, jbtProject.getTraceFileName() );		
			
			ProberInstrum.instrumentProject(jbtProject, outName);
			
			jbtProject.saveProject();

			System.out.println( jbtProject.coverage2TXT("") );			
			
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}

}
