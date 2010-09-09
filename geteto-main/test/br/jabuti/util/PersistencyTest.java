/*
Copyright (C) 2008 Marco Aurélio Graciotto Silva

This file is part of Jabuti.

Jabuti is free software: you can redistribute it and/or modify
it under the terms of the GNU Lesser General Public License as 
published by the Free Software Foundation, either version 3 of the      
License, or (at your option) any later version.

Jabuti is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU Lesser General Public License
along with Jabuti.  If not, see <http://www.gnu.org/licenses/>.
*/


package br.jabuti.util;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PersistencyTest
{
	private static final String[] data = {"a", "b", "c", "d"};
	
	@Before
	public void setUp() throws Exception
	{
		Persistency.reset();
	}

	@Test
	public void testAdd()
	{
		List<String> labels = new ArrayList<String>();
		for (String s : data) {
			labels.add(Persistency.add(s));
		}

		assertEquals("a", Persistency.get("0"));
		assertEquals("b", Persistency.get("1"));
		assertEquals("c", Persistency.get("2"));
		assertEquals("d", Persistency.get("3"));
	}

	
	@Test
	public void testAddInitAdd()
	{
		List<String> labels = new ArrayList<String>();
		for (String s : data) {
			labels.add(Persistency.add(s));
		}

		assertEquals("a", Persistency.get("0"));
		assertEquals("b", Persistency.get("1"));
		assertEquals("c", Persistency.get("2"));
		assertEquals("d", Persistency.get("3"));
		

		Persistency.reset();
		labels.clear();
		for (String s : data) {
			labels.add(Persistency.add(s));
		}

		assertEquals("a", Persistency.get("0"));
		assertEquals("b", Persistency.get("1"));
		assertEquals("c", Persistency.get("2"));
		assertEquals("d", Persistency.get("3"));
	}
}
