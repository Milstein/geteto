package br.jabuti.compiler;

import java.io.File;

public interface Compiler
{
	/**
	 * 
	 * @param files Files to be compiled.
	 * 
	 * @throws IllegalArgumentException Invalid file.
	 * @throws UnsupportedOperationException Cannot compile the requested file.
	 */
	void compile(File... files);
}
