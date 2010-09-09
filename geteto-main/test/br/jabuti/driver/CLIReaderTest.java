package br.jabuti.driver;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CLIReaderTest
{
	private CLIReader cli;
	
	@Before
	public void setUp() throws Exception
	{
		cli = new CLIReader();
	}

	@Test
	public void testReadInteger()
	{
		System.out.print("Type the integer '12': ");
		int i = cli.readInteger();
		assertEquals(12, i);
	}

	@Test
	public void testReadStringWithoutSpace()
	{
		System.out.print("Type the string 'abc': ");
		String str = cli.readString();
		assertEquals("abc", str);
	}

	@Test
	public void testReadStringWithSpace()
	{
		System.out.print("Type the string 'a b c': ");
		String str = cli.readString();
		assertEquals("a b c", str);
	}
}
