/**
 * Copyright (C) 2007 Auri Marcelo Rizzo Vincenzi
 */

package br.jabuti.driver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * This utility class read data from the command line prompt.
 */
public class CLIReader
{
	/**
	 * Read an integer from the command line prompt.
	 */
	public int readInteger() {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int num = 0;
		try {
			String line = in.readLine();
			num = Integer.parseInt(line);
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
		
		return num;
	}

	/**
	 * Read a string from the command line prompt.
	 */
	public String readString() {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String line = new String();
		try {
			line = in.readLine();
		} catch (IOException e) {
			throw new IllegalArgumentException(e);
		}
		return line;
	}

}