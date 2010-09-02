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
import org.aspectj.apache.bcel.generic.ConstantPoolGen;
import org.aspectj.apache.bcel.generic.MethodGen;

import br.jabuti.lookup.java.bytecode.Program;
import br.jabuti.lookup.java.bytecode.RClass;
import br.jabuti.lookup.java.bytecode.RClassCode;
import br.jabuti.metrics.AbstractMetric;

/**
 * Average Number of Parameters per Method. It is the result of the division of
 * parameters of each method by the number of methods of the class.
 */
public class MetricANPM extends AbstractMetric
{
	public MetricANPM()
	{
		super();
		name = "anpm";
		description = "Average Number of Parameters per Method (ANPM)";
	}

	@Override
	public double getResult(Program prog, String className)
	{
		RClass rc = prog.get(className);
		if (! (rc instanceof RClassCode)) {
			return -1.0;
		}

		int countMethods = 0;
		int countParameters = 0;
		RClassCode rcc = (RClassCode) rc;
		JavaClass theClazz = rcc.getTheClass();
		ConstantPoolGen cp = new ConstantPoolGen(theClazz.getConstantPool());
		Method[] methods = theClazz.getMethods();
		for (Method method : methods) {
			if (method.isAbstract()) {
				continue;
			}
			MethodGen mg = new MethodGen(method, theClazz.getClassName(), cp);
			countMethods++;
			countParameters += mg.getArgumentNames().length;
		}
		if (countMethods == 0) {
			return -1.0;
		}
		return (double) countParameters / countMethods;
	}
}