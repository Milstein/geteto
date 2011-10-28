package br.jabuti.lookup;

import java.util.jar.JarFile;
import java.util.zip.ZipFile;

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
	
	/**
     *     <p>A test driver. Can be called as: <BR></p>
     *     <p>
     *     java program.Program classname [avoid-name-list]
     *     </p>
     *     or
     *     <p>
     *     java program.Program zipfilename
     *     </p>
     *      
     *     <p>
     *     In both cases the system classes are not included in the 
     *     program structure
     *     </p>
     */
	/*
    static public void main(String args[]) throws Exception {
        Program p = null;

        if (args.length >= 3) {
            p = new Program(args[0], true, args[2], args[1]);
        } else {
            ZipFile zippedFile = null;

            if (args[0].endsWith(".jar")) {
                zippedFile = new JarFile(args[0]);
            } else if (args[0].endsWith(".zip")) {
                zippedFile = new ZipFile(args[0]);
            }

            if (zippedFile == null) {
                p = new Program(args[0]);
            } else {
                p = new Program(zippedFile, true, null);
            }
        }
        p.print();
    }
    */
}
