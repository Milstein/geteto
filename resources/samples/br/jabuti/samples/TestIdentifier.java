package br.jabuti.samples;

import org.junit.Test;

import static org.junit.Assert.*;

public class TestIdentifier
{
	@Test
	public void testVerify1()
	{
		assertTrue(Identifier.verify("m"));
	}

	@Test
	public void testVerify2()
	{
		assertTrue(Identifier.verify("ndfeas"));
	}

	@Test
	public void testVerify3()
	{
		assertFalse(Identifier.verify("abcdefg"));
	}

	@Test
	public void testVerify4()
	{
		assertFalse(Identifier.verify("1aB"));
	}

	@Test
	public void testVerify5()
	{
		assertFalse(Identifier.verify("abc-d"));
	}

	@Test
	public void testVerify6()
	{
		assertFalse(Identifier.verify(""));
	}
}
