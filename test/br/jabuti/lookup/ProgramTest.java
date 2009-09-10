package br.jabuti.lookup;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import br.jabuti.TestConfiguration;

import com.ironiacorp.commons.ArrayUtil;

public class ProgramTest
{
    Program program;

	@Before
	public void setUp() throws Exception
	{
	}

	@Ignore
	public void testBaseClass1() throws Exception
	{
		String baseClass = "";
		program = Program.createFromBaseClass(baseClass);
	}

	@Ignore
	public void testBaseClass2() throws Exception
	{
		String baseClass = "";
		String classPath = "";
		program = Program.createFromBaseClass(classPath, baseClass);
	}
        
	@Test
	public void testZipFile() throws Exception
	{
		String zipFilename = TestConfiguration.SAMPLES_JUNIT_PACKAGE_4_4;
		program = Program.createFromPackage(zipFilename);
	}
	
	@Test
	public void testJarFile() throws Exception
	{
		String jarFilename = TestConfiguration.SAMPLES_JUNIT_PACKAGE_4_4;
		program = Program.createFromPackage(jarFilename);
		System.out.println(program.getClasses().length + " " + ArrayUtil.toString(program.getClasses(), ", "));
		System.out.println(program.getCodeClasses().length + " " + ArrayUtil.toString(program.getCodeClasses(), ", "));
		System.out.println(program.getCodePackages().length + " " + ArrayUtil.toString(program.getCodePackages(), ", "));
		System.out.println(program.getSysClasses().length + " " + ArrayUtil.toString(program.getSysClasses(), ", "));
	}
}
