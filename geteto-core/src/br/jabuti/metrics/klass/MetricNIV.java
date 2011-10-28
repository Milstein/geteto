/*
Copyright (C) 2008 Márcio Eduardo Delamaro and Auri Marcelo Rizzo Vincenzi.>

This library is free software; you can redistribute it and/or
modify it under the terms of the GNU Lesser General Public
License as published by the Free Software Foundation; either
version 2.1 of the License, or (at your option) any later version.

This library is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public
License along with this library; if not, write to the Free Software
Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
*/


package br.jabuti.metrics.klass;

import org.aspectj.apache.bcel.classfile.Field;
import org.aspectj.apache.bcel.classfile.JavaClass;

import br.jabuti.lookup.java.bytecode.Program;
import br.jabuti.lookup.java.bytecode.RClass;
import br.jabuti.lookup.java.bytecode.RClassCode;
import br.jabuti.metrics.AbstractMetric;

/**
 * Number of instance variables in a class (public, protected, and
 * private variables).
 */
public class MetricNIV extends AbstractMetric
{
	public MetricNIV()
	{
		super();
		name = "niv";
		description = "Number of Instance Variables in a class (NIV)";
	}

	@Override
	public double getResult(Program prog, String className)
	{
		RClass rc = prog.get(className);
		if (!(rc instanceof RClassCode))
			return -1.0;
		int cont = 0;
		RClassCode rcc = (RClassCode) rc;
		JavaClass theClazz = rcc.getTheClass();
		Field[] fields = theClazz.getFields();
		for (int i = 0; i < fields.length; i++) {
			if (fields[i].isStatic())
				continue;
			cont++;
		}
		return (double) cont;
	}
}