/*
Copyright (C) 2009 Marco Aurélio Graciotto Silva <magsilva@icmc.usp.br>

This library is free software; you can redistribute it and/or
modify it under the terms of the GNU Lesser General Public
License as published by the Free Software Foundation; either
version 2.1 of the License, or (at your option) any later version.

This library is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public
License along with this library; if not, write to the Free Software
Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
*/

package br.jabuti.detector;

import java.io.File;
import java.util.Iterator;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.ironiacorp.persistence.JavaBeanUtil;



public class Matcher
{
	private Set<File> sourceDirs;
	
	private Set<File> testDirs; 
	
	private Map<String, Set<String>> map;
	
	private Set<String> classes;
	
	private Set<String> tests;
	
	private UnitTestDetector detector;
	
	public Matcher()
	{
		sourceDirs = new HashSet<File>();
		testDirs = new HashSet<File>();
		map = new HashMap<String, Set<String>>();
		detector = new UnitTestDetector();
		
		classes = new HashSet<String>();
		tests = new HashSet<String>();
	}
	
	public void addSourceDirectory(String dirName)
	{
		addSourceDirectory(new File(dirName));
	}

	public void addSourceDirectory(File dir)
	{
		if (dir == null || ! dir.exists() || ! dir.isDirectory()) {
			throw new IllegalArgumentException("Invalid directory");
		}
		
		sourceDirs.add(dir);
	}

	public void addTestDirectory(String dirName)
	{
		addTestDirectory(new File(dirName));
	}
	
	public void addTestDirectory(File dir)
	{
		if (dir == null || ! dir.exists() || ! dir.isDirectory()) {
			throw new IllegalArgumentException("Invalid directory");
		}
		
		testDirs.add(dir);
	}

	public void update()
	{
		updateSource();
		updateTest();
		updateMapping();
	}
	
	public void updateSource()
	{
		Iterator<File> i = sourceDirs.iterator();
		while(i.hasNext()) {
			File dir = i.next();
			updateSource(dir, dir);
		}	
	}
	
	public void updateSource(File baseDir, File dir)
	{
		File[] files = dir.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				updateSource(baseDir, dir);
			} else {
				String className = JavaBeanUtil.toString(baseDir, file);
				classes.add(className);
			}
		}
	}
	
	private void updateTest()
	{
		Iterator<File> i = testDirs.iterator();
		while(i.hasNext()) {
			File dir = i.next();
			updateTest(dir, dir);
		}
	}
	
	public void updateTest(File baseDir, File dir)
	{
		File[] files = dir.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				updateSource(baseDir, file);
			} else {
				if (detector.isTestFile(baseDir, file)) {
					String className = JavaBeanUtil.toString(baseDir, file);
					tests.add(className);
				}
			}
		}
	}
	
	public void updateMapping()
	{
		for (String className : classes) {
			Set<String> result = detector.getTests(className, tests);
			map.put(className, result);
		}
	}

	public Set<String> getTestsFor(String className)
	{
		return map.get(className);
	}
}
