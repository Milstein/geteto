package br.jabuti.lookup;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import br.jabuti.TestConfiguration;

public class ClassClosureTest
{
	ClassClosure cc;
	Set<String> classesToAvoid;
	
	@Before
	public void setUp() throws Exception
	{
		classesToAvoid = new HashSet<String>();
		classesToAvoid.add("java.lang.String");
	}

	@Test
	public void testInterestedClasses()
	{
		cc = new ClassClosure(TestConfiguration.SAMPLES_ROOT + File.pathSeparator + TestConfiguration.JRE_LIB);
		Set<String> interestedClasses = cc.getClosure(TestConfiguration.SAMPLES_HELLO_WORLD_CLASSNAME, false, null);
	}

	@Test
	public void testInterestedClassesExcludeSystemClasses()
	{
		cc = new ClassClosure(TestConfiguration.SAMPLES_ROOT);
		Set<String> interestedClasses = cc.getClosure(TestConfiguration.SAMPLES_HELLO_WORLD_CLASSNAME, true, null);
	}

	@Test
	public void testInterestedClassesExcludeOtherClasses()
	{
		cc = new ClassClosure(TestConfiguration.SAMPLES_ROOT + File.pathSeparator + TestConfiguration.JRE_LIB);
		Set<String> interestedClasses = cc.getClosure(TestConfiguration.SAMPLES_HELLO_WORLD_CLASSNAME, false, classesToAvoid);
	}

	@Test
	public void testInterestedClassesExcludeSystemAndOtherClasses()
	{
		cc = new ClassClosure(TestConfiguration.SAMPLES_ROOT);
		Set<String> interestedClasses = cc.getClosure(TestConfiguration.SAMPLES_HELLO_WORLD_CLASSNAME, true, classesToAvoid);
	}

}
