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

import br.jabuti.lookup.java.bytecode.Program;
import br.jabuti.lookup.java.bytecode.RClass;
import br.jabuti.lookup.java.bytecode.RClassCode;
import br.jabuti.metrics.AbstractMetric;

/**
 * Implements the DIT - depth in tree. It is the longest path from the root
 * class to the current class. if the class implements an interface, the path
 * through the interface is also considered.interface is also considered.
 *
 * The current implementation does not use information of system classes and
 * classes not included (i.e., ignored) in the test project.
 */
public class MetricDIT extends AbstractMetric
{
	public MetricDIT()
	{
		super();
		name = "dit";
		description = "Depth of inheritance Tree (DIT)";
	}

	// TODO: Consider also system classes.
	@Override
	public double getResult(Program prog, String className)
	{
		RClass tc = prog.get(className);
		if (tc == null) {
			return -1.0;
		}
		if (!(tc instanceof RClassCode)) {
			return 0.0;
		}
		RClassCode theClass = (RClassCode) tc;
		String inter[] = theClass.getInterfaces();
		double theValue = 0;
		for (int i = 0; inter != null && i < inter.length; i++) {
			int k = prog.levelOf(inter[i]);
			theValue = k > theValue ? k : theValue;
		}
		double d = (double) prog.levelOf(className);
		return d > theValue ? d : theValue;
	}
}
