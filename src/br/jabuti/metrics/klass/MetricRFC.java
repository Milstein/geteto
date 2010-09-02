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

import java.util.HashSet;
import java.util.Set;

import org.aspectj.apache.bcel.classfile.JavaClass;
import org.aspectj.apache.bcel.classfile.Method;
import org.aspectj.apache.bcel.generic.ClassGen;
import org.aspectj.apache.bcel.generic.ConstantPoolGen;
import org.aspectj.apache.bcel.generic.MethodGen;

import br.jabuti.graph.datastructure.GraphCallNode;
import br.jabuti.graph.datastructure.dug.CFG;
import br.jabuti.graph.datastructure.dug.CFGNode;
import br.jabuti.lookup.java.bytecode.Program;
import br.jabuti.lookup.java.bytecode.RClass;
import br.jabuti.lookup.java.bytecode.RClassCode;
import br.jabuti.metrics.AbstractMetric;

// TODO: review this metric implementation. JaBUTi/AJ and plain JaBUTi
// have different results for the same input.
// Extra info: the source code, for this file, is exactly the same. The
// problem must be somewhere else.

/**
 * Responser for a class. It is the sum of the number of class methods and
 * methods directly called by those class methods.
 *
 * It measures the number of methods that can be potentially called
 * by a method for a message received by a class instance.
 */
public class MetricRFC extends AbstractMetric
{
	public MetricRFC()
	{
		super();
		name = "rfc";
		description = "Response for a Class (RFC)";
	}

	@Override
	public double getResult(Program prog, String className)
	{
		RClass rc = prog.get(className);
		if (! (rc instanceof RClassCode)) {
			return -1.0;
		}
		RClassCode rcc = (RClassCode) rc;
		JavaClass theClazz = rcc.getTheClass();
		ConstantPoolGen cp = new ConstantPoolGen(theClazz.getConstantPool());
		ClassGen cg = new ClassGen(theClazz);
		Method[] methods = theClazz.getMethods();
		int theValue = 0;

		for (Method method : methods) {
			if (method.isAbstract()) {
				continue;
			}
			MethodGen mg = new MethodGen(method, theClazz.getClassName(), cp);

			CFG g = null;
			try {
				g = getCFG(mg, cg);
			} catch (Exception e) {
				return -1.0;
			}

			Set<String> hs = new HashSet<String>();
			for (int j = 0; j < g.size(); j++) {
				CFGNode gn = (CFGNode) g.get(j);
				if (!(gn instanceof GraphCallNode)) {
					continue;
				}
				GraphCallNode gcn = (GraphCallNode) gn;
				for (int h = 0; h < gcn.getClasse().length; h++) {
					hs.add(gcn.getClasse()[h] + gcn.getName());
				}
			}
			theValue += hs.size();
			theValue++;
		}
		return (double) theValue;
	}
}