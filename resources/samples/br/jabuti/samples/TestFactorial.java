package br.jabuti.samples;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestFactorial
{
	Factorial f1;
	Factorial f2;

	@Before
	public void setUp()
	{
		f1 = new Factorial();
		f2 = new Factorial();
	}

	@Test
	public void testCompute()
	{
		assertEquals(2, f1.compute(2));
		assertEquals(1, f1.compute(-1));
	}

	@Test
	public void testComputa()
	{
		assertEquals(2, f1.compute(2));
		assertEquals(1, f1.compute(-1));
	}

	@Test
	public void testComputo()
	{
		assertEquals(2, f1.compute(2));
		assertEquals(1, f1.compute(-1));
	}

	@Test
	public void testEqual()
	{
		Factorial f1 = new Factorial();
		Factorial f2 = new Factorial();
		assertEquals(f1.compute(0), f2.compute(-1));
	}
}