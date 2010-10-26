/*
This program is free software; you can redistribute it and/or
modify it under the terms of the GNU General Public License
as published by the Free Software Foundation; either version 2
of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, write to the Free Software
Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.

Copyright (C) 2008 Marco Aurélio Graciotto Silva <magsilva@gmail.com>
*/

package br.icmc.labes.testing;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;

import org.junit.Test;
import static org.junit.Assert.*;

import br.icmc.labes.testing.loader.clazz.JarClassLoader;
import br.icmc.labes.testing.loader.clazz.URLClassLoader;

public class ClassloadersTest
{
	@Test
	public void testWithResourceName() throws Exception
	{
		JarClassLoader jc = new JarClassLoader();
		jc.add("test-jcl.jar");
		jc.add("./test-classes");
		Object testObj = jc.loadClass("xeus.jcl.test.Test").newInstance();
		testObj.getClass().getDeclaredMethod("sayHello", null).invoke(testObj, null);
	}

	public void testWithClassFolder() throws Exception
	{
		JarClassLoader jc = new JarClassLoader();
		jc.add("./test-classes");
		Object testObj = jc.loadClass("xeus.jcl.test.Test").newInstance();
		assertNotNull(testObj);
		testObj.getClass().getDeclaredMethod("sayHello", null).invoke(testObj, null);
	}

	public void testUnloading() throws Exception
	{
		JarClassLoader jc = new JarClassLoader();
		jc.add("./test-classes");
		Object testObj = null;
		jc.loadClass("xeus.jcl.test.Test");
		jc.unloadClass("xeus.jcl.test.Test");
		try {
			testObj = jc.loadClass("xeus.jcl.test.Test").newInstance();
		} catch (ClassNotFoundException cnfe) {
			testObj = null;
		}
		assertNull(testObj);
	}

	public static void main(String[] args) throws Exception
	{
		URLClassLoader cl = new URLClassLoader();
		cl.add("/tmp");
		Class<?> clazz = cl.loadClass("Hello");
		Class[] argTypes = { String[].class };
        Method targetMethod = clazz.getMethod("main", argTypes);
        
        // create argument array
        String[] argValues = new String[0];
        Object[] argsArray = { argValues };
        targetMethod.invoke(null, argsArray);
        
        
        
		URLClassLoader cl2 = new URLClassLoader();
		cl2.add("/tmp/2");
		Class<?> clazz2 = cl2.loadClass("Hello");
		Class[] argTypes2 = { String[].class };
        Method targetMethod2 = clazz2.getMethod("main", argTypes2);
        
        // create argument array
        String[] argValues2 = new String[0];
        Object[] argsArray2 = { argValues2 };
        targetMethod2.invoke(null, argsArray2);

	}
}
