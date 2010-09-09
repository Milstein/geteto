package br.jabuti.criteria;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.aspectj.apache.bcel.classfile.ClassParser;
import org.aspectj.apache.bcel.classfile.JavaClass;
import org.aspectj.apache.bcel.classfile.Method;
import org.aspectj.apache.bcel.generic.ClassGen;
import org.aspectj.apache.bcel.generic.ConstantPoolGen;
import org.aspectj.apache.bcel.generic.MethodGen;
import org.junit.Before;

import br.jabuti.graph.datastructure.dug.CFG;

public class AllNodesTest
{

	@Before
	public void setUp() throws Exception
	{
	}

	public static void main(String args[]) throws Exception
	{
		JavaClass java_class;
		java_class = new ClassParser(args[0]).parse(); // May throw IOException
		ConstantPoolGen cp = new ConstantPoolGen(java_class.getConstantPool());

		ClassGen cg = new ClassGen(java_class);

		Method[] methods = java_class.getMethods();
		for (int i = 0; i < methods.length; i++) {
			System.out.println("\n\n--------------------------");
			System.out.println(methods[i].getName());
			System.out.println("--------------------------");
			MethodGen mg = new MethodGen(methods[i], java_class.getClassName(), cp);

			CFG g = new CFG(mg, cg);

			AllNodesEI an = new AllNodesEI(g);

			System.out.println("Number of Requirements: " + an.getNumberOfRequirements());

			System.out.println("Number of Possible Requirements: " + an.getNumberOfPossibleRequirements());

			Object[] reqs = an.getRequirements();
			System.out.println("Requirements: ");

			for (int j = 0; j < reqs.length; j++) {
				Requirement req = (Requirement) reqs[j];
				System.out.print(req);
				System.out.print(" active: ");
				if (an.isActive(req)) {
					System.out.print("true");
				} else {
					System.out.print("false");
				}

				System.out.print(" covered: ");
				if (an.isCovered(req)) {
					System.out.print("true");
				} else {
					System.out.print("false");
				}

				System.out.print(" feasible: ");
				if (an.isFeasible(req)) {
					System.out.print("true");
				} else {
					System.out.print("false");
				}
				System.out.println();
			}
			System.out.println();

			an.addPath(new String[] { "0", "4" }, "path 1");
			an.addPath(new String[] { "0", "48", "207" }, "path 3");
			an.addPath(new String[] { "0", "76", "207" }, "path 2");

			int[] cv = an.getCoverage();
			System.out.println();

			System.out.println("Number of Possible Covered Requirements: " + an.getNumberOfPossibleCovered());

			Set hs = an.getCoveredRequirements();
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

			hs = an.getCoveredRequirements("path 1");
			System.out.println("Covered requirements by Path 1");
			Iterator it = hs.iterator();
			while (it.hasNext()) {
				System.out.print(it.next() + " ");
			}

			// Marcando todos os requisitos como inativos e reativando-os
			for (int j = 0; j < reqs.length; j++) {
				an.setInactive((Requirement) reqs[j]);
			}
			hs = an.getInactiveRequirements();
			for (int j = 0; j < reqs.length; j++) {
				an.setActive((Requirement) reqs[j]);
			}

			// Marcando todos os requisitos como infeasible e reativando-os
			for (int j = 0; j < reqs.length; j++) {
				an.setInfeasible((Requirement) reqs[j]);
			}
			hs = an.getInfeasibleRequirements();
			for (int j = 0; j < reqs.length; j++) {
				an.setFeasible((Requirement) reqs[j]);
			}

			// Obtendo os requisitos por meio de seus r�tulos
			/*
			 * for (int j = 0; j < req.length; j++) { Object o =
			 * an.getRequirementByLabel( req[j].toString() ); }
			 */

			// Removendo um caso de teste
			an.removePath("path 2");

			// Removendo todos os casos de teste
			an.removeAllPaths();
		}
	}

}
