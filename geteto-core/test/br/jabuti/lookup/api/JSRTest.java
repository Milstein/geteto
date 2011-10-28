package br.jabuti.lookup.api;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class JSRTest
{
	private static final String JSR105_ID = "105";
	
	private static final String JSR173_ID = "173";
	
	private static final String JSR105_NAME = "XML Digital-Signature APIs";
	
	private BasicJavaProfile jsr105;
	
	private BasicJavaProfile jsr173;
	
	@Before
	public void setUp() throws Exception
	{
		jsr105 = new JSR(JSR105_ID);
		jsr173 = new JSR(JSR173_ID);
	}

	@Test
	public void testGetId()
	{
		assertEquals(JSR105_ID, jsr105.getId());
	}

	@Test
	public void testGetName()
	{
		assertEquals(JSR105_NAME, jsr105.getName());
	}

	@Test(expected=IllegalArgumentException.class)
	public void testJSR()
	{
		new JSR(0);
	}

	@Test
	public void testHas1()
	{
		assertTrue(jsr105.has("javax.xml.crypto.Cipher"));
	}

	@Test
	public void testHas2()
	{
		assertTrue(jsr105.has("javax.xml.crypto"));
	}

	@Test
	public void testHas3()
	{
		assertTrue(jsr105.has("javax.xml.crypto.interfaces.DHKey"));
	}

	@Test
	public void testHas4()
	{
		assertFalse(jsr105.has("javax.x"));
	}

}
