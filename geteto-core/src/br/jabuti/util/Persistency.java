/*
Copyright (C) 2003 Auri Marcelo Rizzo Vicenzi, Marcio Eduardo Delamaro,
Jose Carlos Maldonado

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

import java.util.HashMap;

public class Persistency
{
	private static HashMap<String, Object> data = new HashMap<String, Object>();
	
	private static int cont = 0;
	
	private static synchronized String nextLabel()
	{
		String label = "" + cont;
		cont++;
		return label;
	}

	protected static synchronized void reset()
	{
		data.clear();
		cont = 0;
	}

	public static synchronized String add(Object x)
	{
		String l = nextLabel();
		data.put(l, x);
		return l;
	}

	public static synchronized Object get(String x)
	{
		return data.get(x);
	}
}
