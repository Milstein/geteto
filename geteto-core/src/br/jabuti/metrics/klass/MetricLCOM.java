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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.aspectj.apache.bcel.classfile.JavaClass;
import org.aspectj.apache.bcel.classfile.Method;
import org.aspectj.apache.bcel.generic.ClassGen;
import org.aspectj.apache.bcel.generic.ConstantPoolGen;
import org.aspectj.apache.bcel.generic.MethodGen;

import br.jabuti.graph.datastructure.dug.CFG;
import br.jabuti.graph.datastructure.dug.CFGNode;
import br.jabuti.lookup.java.bytecode.Program;
import br.jabuti.lookup.java.bytecode.RClass;
import br.jabuti.lookup.java.bytecode.RClassCode;
import br.jabuti.metrics.AbstractMetric;
import br.jabuti.util.Debug;

/**
 * Lack of Cohesion in Methods. It is the count of pairs of	instance methods
 * that do not share instance variables minus the count of pairs of
 * instance methods that share instance variables. If the result is
 * negative, the final result is zero.
 */
public class MetricLCOM extends AbstractMetric
{
	public MetricLCOM()
	{
		super();
		name = "lcom";
		description = "Lack of Cohesion in Methods (LCOM)";
	}

	// TODO: Reimplement this
	protected double lcom(Program prog, String className, boolean isStatic)
	{
		RClass rc = prog.get(className);
		if (!(rc instanceof RClassCode)) {
			return -1.0;
		}

		List<Collection<CFGNode>> dmethod = new ArrayList<Collection<CFGNode>>();
		RClassCode rcc = (RClassCode) rc;
		JavaClass theClazz = rcc.getTheClass();
		ConstantPoolGen cp = new ConstantPoolGen(theClazz.getConstantPool());
		ClassGen cg = new ClassGen(theClazz);
		Method[] methods = theClazz.getMethods();
		for (int i = 0; i < methods.length; i++) {
			if (methods[i].isAbstract()) {
				continue;
			}
			if (methods[i].isStatic()) {
				continue;
			}
			MethodGen mg = new MethodGen(methods[i], theClazz.getClassName(), cp);

			CFG g = null;
			try {
				g = getCFG(mg, cg);
			} catch (Exception e) {
				return -1.0;
			}
			Collection<CFGNode> defUses = findDefUse(g);
			// keeps only the non static accesses
			for (Iterator<CFGNode> it = defUses.iterator(); it.hasNext();) {
				CFGNode node = it.next();
				String s = it.toString();
				if (! s.startsWith("L@0.")) {
					it.remove();
				}
			}
			dmethod.add(defUses);
		}
		int doShare = 0, notShare = 0;
		// now compare the methods
		for (int i = 0; i < dmethod.size() - 1; i++) {
			Collection v = (Collection) dmethod.get(i);
			r1: for (int j = i + 1; j < dmethod.size(); j++) {
				Collection v1 = (Collection) dmethod.get(j);
				for (Iterator it1 = v.iterator(); it1.hasNext();) {
					String s = (String) it1.next();
					if (v1.contains(s)) {
						Debug.D("Methods " + i + " and " + j + " share " + s);
						doShare++;
						continue r1;
					}
				}
				Debug.D("Methods " + i + " and " + j + " do not share ");
				notShare++;
			}
		}
		double lcom = (double) notShare - doShare;
		return lcom >= 0 ? lcom : 0.0;
	}

	@Override
	public double getResult(Program prog, String className)
	{
		return lcom(prog, className, false);
	}
}