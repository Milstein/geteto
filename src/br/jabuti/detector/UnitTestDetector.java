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
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ironiacorp.persistence.JavaBeanUtil;


public class UnitTestDetector
{
	public boolean isTestFile(File baseDir, File file)
	{
		String className = JavaBeanUtil.toString(baseDir, file);
		if (className.startsWith("Test")) {
			return true;
		}
		
		if (className.endsWith("Test")) {
			return true;
		}

		return false;
	}
	
	public boolean isTestFor(String className, String testName)
	{
		Set<String> testNames = new HashSet<String>();
		testNames.add(testName);
	    return (! getTests(className, testNames).isEmpty());
	}
	
	public Set<String> getTests(String className, Set<String> testNames)
	{
	    String TESTNAME_PATTERN_1 = "^Test(.)*" + className + "(.)*$";
	    String TESTNAME_PATTERN_2 = "^(.)*" + className + "(.)*Test$";
	    Set<Pattern> patterns = new HashSet<Pattern>();
	    Set<String> result = new HashSet<String>();
	    
	    patterns.add(Pattern.compile(TESTNAME_PATTERN_1));
	    patterns.add(Pattern.compile(TESTNAME_PATTERN_2));
	    
	    for (String testName : testNames) {
		    for (Pattern p : patterns) {
			    Matcher m = p.matcher(testName);
			    if (m.matches()) {
			    	result.add(testName);
			    }
		    }
	    }
	    
	    return result;
	}
}
