package br.jabuti.junitexec;


import java.io.File;

import javax.swing.filechooser.FileFilter;

public class JarFilter extends FileFilter {

	public boolean accept(File f) {
		if ( f.isDirectory() )
			return true;
		return f.getName().toLowerCase().endsWith(".jar") || f.getName().toLowerCase().endsWith(".zip");
	}

	public String getDescription() {
		return "Accepts \".jar\" or \".zip\" files.";
	}

}
