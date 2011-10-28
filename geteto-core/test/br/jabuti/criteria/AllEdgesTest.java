package br.jabuti.criteria;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Iterator;

import org.aspectj.apache.bcel.classfile.ClassParser;
import org.aspectj.apache.bcel.classfile.JavaClass;
import org.aspectj.apache.bcel.generic.ClassGen;
import org.aspectj.apache.bcel.generic.ConstantPoolGen;
import org.aspectj.apache.bcel.generic.MethodGen;
import org.junit.Before;
import org.junit.Test;

import br.jabuti.graph.datastructure.dug.CFG;

public class AllEdgesTest
{

	@Before
	public void setUp() throws Exception
	{
	}

	/*
	@Test
	public void testMain() throws Exception
	{
		String className = "";
		JavaClass java_class = new ClassParser(className).parse();
		ConstantPoolGen cp = new ConstantPoolGen(java_class.getConstantPool());
		Method[] methods = java_class.getMethods();
		ClassGen cg = new ClassGen(java_class);

		for (int i = 0; i < methods.length; i++) {
			System.out.println("\n\n--------------------------");
			System.out.println(methods[i].getName());
			System.out.println("--------------------------");
			MethodGen mg = new MethodGen(methods[i], java_class.getClassName(), cp);
			CFG g = new CFG(mg, cg);

			g.print(System.out);
			AllEdges an = new AllEdges(g, AllEdges.ALL);

			System.out.println("Number of Requirements: " + an.getNumberOfRequirements());

			System.out.println("Number of Possible Requirements: " + an.getNumberOfPossibleRequirements());

			Object[] req = an.getRequirements();

			System.out.println("Requirements: ");
			for (int j = 0; j < req.length; j++) {
				Edge gn = (Edge) req[j];

				System.out.print(gn);

				System.out.print(" active: ");
				if (an.isActive(req[j])) {
					System.out.print("true");
				} else {
					System.out.print("false");
				}

				System.out.print(" covered: ");
				if (an.isCovered(req[j])) {
					System.out.print("true");
				} else {
					System.out.print("false");
				}

				System.out.print(" feasible: ");
				if (an.isFeasible(req[j])) {
					System.out.print("true");
				} else {
					System.out.print("false");
				}
				System.out.println();
			}
			an.addPath(an.changePath(g, new String[] { "0", "2", "4", "9", "12" }), "path 1");
			an.addPath(an.changePath(g, new String[] { "0", "1", "3", "4", "11" }), "path 3");
			an.addPath(an.changePath(g, new String[] { "0", "2", "7", "9", "10" }), "path 2");

			int[] cv = an.getCoverage();
			System.out.println();

			System.out.println("Number of Possible Covered Requirements: " + an.getNumberOfPossibleCovered());

			HashSet hs = an.getCoveredRequirements();
			if (hs.isEmpty())
				System.out.println("No covered requirement.");
			else
				System.out.println("There are covered requirements.");

			hs = an.getPossibleCoveredRequirements();
			if (hs.isEmpty())
				System.out.println("No possible covered requirement.");
			else
				System.out.println("There are possible covered requirements.");

			System.out.println("Covered: ");
			for (int j = 0; j < cv.length; j++) {
				System.out.print(cv[j] + "  ");
			}

			System.out.println();

			hs = an.getCoveredRequirementsByPath("path 1");
			System.out.println("Covered requirements by Path 1");
			Iterator it = hs.iterator();
			while (it.hasNext()) {
				System.out.print(it.next() + " ");
			}

			// Marcando todos os requisitos como inativos e reativando-os
			for (int j = 0; j < req.length; j++) {
				an.setInactive(req[j]);
			}
			hs = an.getInactiveRequirements();
			for (int j = 0; j < req.length; j++) {
				an.setActive(req[j]);
			}

			// Marcando todos os requisitos como infeasible e reativando-os
			for (int j = 0; j < req.length; j++) {
				an.setInfeasible(req[j]);
			}
			hs = an.getInfeasibleRequirements();
			for (int j = 0; j < req.length; j++) {
				an.setFeasible(req[j]);
			}

			// Obtendo os requisitos por meio de seus r�tulos
			for (int j = 0; j < req.length; j++) {
				Object o = an.getRequirementByLabel(req[j].toString());
			}

			// Removendo um caso de teste
			an.removePath("path 2");

			// Removendo todos os casos de teste
			an.removeAllPaths();
		}
	}
	*/

}
