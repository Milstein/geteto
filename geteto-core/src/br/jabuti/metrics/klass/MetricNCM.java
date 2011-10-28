/*
Copyright (C) 2008 Márcio Eduardo Delamaro and Auri Marcelo Rizzo Vincenzi.

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

import org.aspectj.apache.bcel.classfile.JavaClass;
import org.aspectj.apache.bcel.classfile.Method;

import br.jabuti.lookup.java.bytecode.Program;
import br.jabuti.lookup.java.bytecode.RClass;
import br.jabuti.lookup.java.bytecode.RClassCode;
import br.jabuti.metrics.AbstractMetric;

/**
 * Number of class methods (static methods) in a class.
 */
public class MetricNCM extends AbstractMetric
{
	public MetricNCM()
	{
		super();
		name = "ncm";
		description = "Number of Class Methods in a class (NCM 1)";
	}

	@Override
	public double getResult(Program prog, String className)
	{
		RClass rc = prog.get(className);
		if (!(rc instanceof RClassCode)) {
			return -1.0;
		}
		int cont = 0;
		RClassCode rcc = (RClassCode) rc;
		JavaClass theClazz = rcc.getTheClass();
		Method[] methods = theClazz.getMethods();
		for (int i = 0; i < methods.length; i++) {
			if (! methods[i].isStatic()) {
				continue;
			}
			cont++;
		}
		return (double) cont;
	}
}