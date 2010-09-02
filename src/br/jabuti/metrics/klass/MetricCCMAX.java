/*
Copyright (C) 2006 Márcio Eduardo Delamaro and Auri Marcelo Rizzo Vincenzi.

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
import org.aspectj.apache.bcel.generic.ClassGen;
import org.aspectj.apache.bcel.generic.ConstantPoolGen;
import org.aspectj.apache.bcel.generic.MethodGen;

import br.jabuti.lookup.java.bytecode.Program;
import br.jabuti.lookup.java.bytecode.RClass;
import br.jabuti.lookup.java.bytecode.RClassCode;
import br.jabuti.metrics.AbstractMetric;

/**
 * Cyclomatic Complexity Metric (CC_MAX). Considers the maximum value of
 * the complexity metric of	the methods of a class.
 */
public class MetricCCMAX extends AbstractMetric
{
	public MetricCCMAX()
	{
		super();
		name = "cc_max";
		description = "Cyclomatic Complexity Metric (CC_MAX)";
	}

	@Override
	public double getResult(Program prog, String className)
	{
		double theValue = 0.0;
		RClass rc = prog.get(className);
		if (!(rc instanceof RClassCode)) {
			return -1.0;
		}
		RClassCode rcc = (RClassCode) rc;
		JavaClass theClazz = rcc.getTheClass();
		ConstantPoolGen cp = new ConstantPoolGen(theClazz.getConstantPool());
		ClassGen cg = new ClassGen(theClazz);
		Method[] methods = theClazz.getMethods();
		for (int i = 0; i < methods.length; i++) {
			if (methods[i].isAbstract()) {
				continue;
			}
			MethodGen mg = new MethodGen(methods[i], theClazz.getClassName(), cp);
			double d = getCyclomaticComplexity(mg, cg);

			if (d < 0.0) {
				return -1;
			}
			theValue = (d > theValue) ? d : theValue;
		}
		return theValue;
	}
}
