/*
Copyright (C) 2006 Auri Vicenzi and Marcio Delamaro

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

package br.jabuti.metrics;

import java.util.Collection;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

import org.aspectj.apache.bcel.classfile.JavaClass;
import org.aspectj.apache.bcel.classfile.Method;
import org.aspectj.apache.bcel.generic.ClassGen;
import org.aspectj.apache.bcel.generic.InstructionList;
import org.aspectj.apache.bcel.generic.LineNumberGen;
import org.aspectj.apache.bcel.generic.MethodGen;

import br.jabuti.graph.datastructure.defuse.CFG;
import br.jabuti.graph.datastructure.defuse.CFGNode;
import br.jabuti.lookup.Program;
import br.jabuti.lookup.RClass;
import br.jabuti.lookup.RClassCode;
import br.jabuti.util.Persistency;
import br.jabuti.verifier.InvalidInstructionException;
import br.jabuti.verifier.InvalidStackArgument;

public abstract class AbstractMetric implements Metric
{
	protected String name;

	protected String description;

	private Hashtable<String, Object> graphTable;
	
	protected final boolean findMethInClass(Program prog, Method m, String className, boolean rec)
	{
		RClass rc = prog.get(className);
		if (rc == null || !(rc instanceof RClassCode)) {
			return false;
		}
		RClassCode rcc = (RClassCode) rc;
		JavaClass theClazz = rcc.getTheClass();
		Method[] methods = theClazz.getMethods();
		int i;
		for (i = 0; i < methods.length; i++) {
			if (methods[i].getName().equals(m.getName())
					&& methods[i].getSignature().equals(m.getSignature())) {
				return true;
			}
		}
		if (rec) {
			return findMethInClass(prog, m, rcc.getSuperClass(), rec);
		}
		return false;
	}

	
	protected final Collection<CFGNode> findDefUse(CFG gfc)
	{
		Set<CFGNode> v = new HashSet<CFGNode>();
		Iterator<CFGNode> i = gfc.iterator();
		while (i.hasNext()) {
			CFGNode gn = i.next();
			v.addAll(gn.definitions.keySet());
			v.addAll(gn.uses.keySet());
		}
		return v;
	}
	
	protected final CFG getCFG(MethodGen mg, ClassGen cg) throws InvalidInstructionException, InvalidStackArgument
	{
		String s = mg.getClassName() + "." + mg.getName() + mg.getSignature();
		Object osg = graphTable.get(s);
		if (osg == null) {
			CFG g = new CFG(mg, cg);
			String sg = Persistency.add(g);
			if (sg != null) {
				graphTable.put(s, sg);
			}else {
				graphTable.put(s, g);
			}
			return g;
		} else if (osg instanceof String) {
			String sg = (String) osg;
			CFG g = null;
			try {
				g = (CFG) Persistency.get(sg);
			} catch (Exception e) {
			}
			return g;
		}
		return (CFG) osg;
	}

	protected final double CC(MethodGen mg, ClassGen cg)
	{
		int nos = 0;
		int arcos = 0;
		try {
			CFG g = getCFG(mg, cg);
			nos = g.size();
			arcos = 0;
			for (int i = 0; i < g.size(); i++) {
				CFGNode gn = (CFGNode) g.get(i);
				arcos += gn.getPrimNext().size();
			}
			arcos += g.getExits().length;
		} catch (Exception e) {
			return -1.0;
		}
		return (double) (arcos - nos + 1);
	}

	protected final double LOCM(MethodGen mg)
	{
		LineNumberGen lng[] = mg.getLineNumbers();
		if (lng == null)
			return 0.0;
		return (double) lng.length;
	}

	protected final double SIZE(MethodGen mg)
	{
		InstructionList il = mg.getInstructionList();
		if (il == null)
			return 0.0;
		return (double) il.getLength();
	}

	public AbstractMetric()
	{
		graphTable = new Hashtable<String, Object>();
	}

	public String getName()
	{
		return name;
	}

	public String getDescription()
	{
		return description;
	}

	public abstract double getResult(Program prog, String className);
}
