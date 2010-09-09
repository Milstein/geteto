package br.jabuti.junitexec;


import java.io.File;

import javax.swing.filechooser.FileFilter;

public class DirFilter extends FileFilter {

	public boolean accept(File f) {
		return f.isDirectory();
	}

	public String getDescription() {
		return "Accepts a directory only.";
	}

}
