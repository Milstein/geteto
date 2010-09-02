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

import org.aspectj.apache.bcel.classfile.JavaClass;
import org.aspectj.apache.bcel.classfile.Method;

import br.jabuti.lookup.java.bytecode.Program;
import br.jabuti.lookup.java.bytecode.RClass;
import br.jabuti.lookup.java.bytecode.RClassCode;
import br.jabuti.metrics.AbstractMetric;

/**
 * Number of methods overridden by a subclass. It counts the number of
 * methods defined in a subclass that has the same signature of the method
 * implemented in the superclass. Constructors and static initializers
 * are not taken into account.
 */
public class MetricNMOS extends AbstractMetric
{
	public MetricNMOS()
	{
		super();
		name = "nmos";
		description = "Number of Methods Overridden by a Subclass (NMOS)";
	}

	@Override
	public double getResult(Program prog, String className)
	{
		RClass rc = prog.get(className);
		if (!(rc instanceof RClassCode)) {
			return -1.0;
		}
		int cont = 0;
		RClassCode theRClazz = (RClassCode) rc;
		JavaClass theClazz = theRClazz.getTheClass();

		RClass superClazz = prog.get(theRClazz.getSuperClass());
		if (!(superClazz instanceof RClassCode)) {
			return 0.0;
		}

		Method[] methods = theClazz.getMethods();
		for (int i = 0; i < methods.length; i++) {
			if (methods[i].getName().endsWith("init>")) {
				continue;
			}
			if (methods[i].isAbstract()) {
				continue;
			}
			if (findMethodInClass(prog, methods[i], theRClazz.getSuperClass(), true)) {
				cont++;
			}
		}
		return (double) cont;
	}
}