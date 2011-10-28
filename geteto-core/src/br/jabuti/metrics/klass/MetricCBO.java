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

import org.aspectj.apache.bcel.classfile.Constant;
import org.aspectj.apache.bcel.classfile.ConstantCP;
import org.aspectj.apache.bcel.classfile.ConstantPool;
import org.aspectj.apache.bcel.classfile.JavaClass;

import br.jabuti.lookup.java.bytecode.Program;
import br.jabuti.lookup.java.bytecode.RClass;
import br.jabuti.lookup.java.bytecode.RClassCode;
import br.jabuti.metrics.AbstractMetric;

/**
 * Coupling between objects.
 *
 * There is coupling between two classes when one class uses
 * methods or instance variables of other class.
 */
public class MetricCBO extends AbstractMetric
{
	public MetricCBO()
	{
		super();
		name = "cbo";
		description = "Coupling Between Object (CBO)";
	}

	@Override
	public double getResult(Program prog, String className)
	{
		RClass rc = prog.get(className);
		if (!(rc instanceof RClassCode)) {
			return -1.0;
		}
		RClassCode rcc = (RClassCode) rc;
		JavaClass theClazz = rcc.getTheClass();
		ConstantPool cp = theClazz.getConstantPool();
		Constant[] cts = cp.getConstantPool();
		Set<String> hs = new HashSet<String>();
		for (Constant ct : cts) {
			if (ct instanceof ConstantCP) {
				ConstantCP cc = (ConstantCP) ct;
				if (! className.equals(cc.getClass(cp))) {
					hs.add(cc.getClass(cp));
				}
			}
		}
		return hs.size();
	}


}


