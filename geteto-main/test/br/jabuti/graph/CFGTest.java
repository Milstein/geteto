package br.jabuti.graph;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import org.aspectj.apache.bcel.classfile.ClassParser;
import org.aspectj.apache.bcel.classfile.JavaClass;
import org.aspectj.apache.bcel.classfile.Method;
import org.aspectj.apache.bcel.generic.ClassGen;
import org.aspectj.apache.bcel.generic.ConstantPoolGen;
import org.aspectj.apache.bcel.generic.MethodGen;
import org.junit.Before;
import org.junit.Test;

import br.jabuti.TestConfiguration;
import br.jabuti.criteria.AbstractCriterion;
import br.jabuti.graph.datastructure.dug.CFG;
import br.jabuti.graph.datastructure.dug.CFGNode;
import br.jabuti.graph.datastructure.ig.InvalidInstructionException;
import br.jabuti.graph.datastructure.ig.InvalidStackArgument;
import br.jabuti.graph.datastructure.reducetree.RRDominator;
import br.jabuti.graph.datastructure.reducetree.RRLiveDefs;
import br.jabuti.util.Debug;

public class CFGTest
{
	@Before
	public void setUp() throws Exception
	{
	}

	/**
	 * This is a test driver. It takes the name of a class file and creates a
	 * CFG for each of its methods then calls {@link CFG#print} to the standard
	 * output stream.
	 * 
	 * @param args[0]
	 *            The name of the class file
	 * @throws IOException
	 *             In the case it is not possible to analize the requested file
	 * @throws InvalidInstructionException
	 *             If {@link CFG#CFG} throws this exception
	 * @throws InvalidStackArgument
	 *             If {@link CFG#CFG} throws this exception
	 * 
	 */
	@Test
	public void testMain() throws Exception
	{

		JavaClass java_class;

		java_class = new ClassParser(TestConfiguration.SAMPLES_HELLO_WORLD_FILENAME).parse(); // May throw IOException
		ConstantPoolGen cp = new ConstantPoolGen(java_class.getConstantPool());
		Method[] methods = java_class.getMethods();
		ClassGen cg = new ClassGen(java_class);

		for (int i = 0; i < methods.length; i++) {
			System.out.println("\n\n--------------------------");
			System.out.println(methods[i].getName());
			System.out.println("--------------------------");
			MethodGen mg = new MethodGen(methods[i], java_class.getClassName(), cp);
			CFG g = new CFG(mg, cg, CFG.NONE);
			RRDominator rrd = new RRDominator("Dominator");

			g.roundRobinAlgorithm(rrd, true);

			rrd = new RRDominator("IDominator");
			g.roundIRobinAlgorithm(rrd, true);

			RRLiveDefs rral = new RRLiveDefs("Alive definitions", AbstractCriterion.ALL);

			g.roundRobinAlgorithm(rral, true);
			for (int j = 0; j < g.size(); j++) {
				CFGNode gn = (CFGNode) g.get(j);
				System.out.println("\n********* Node " + gn.getLabel());
				HashSet h1 = (HashSet) gn.getUserData("Dominator");
				Iterator it = h1.iterator();

				System.out.println();
				System.out.println("Dominators: ");
				while (it.hasNext()) {
					CFGNode gdom = (CFGNode) it.next();

					System.out.println(gdom.getLabel() + " ");
				}

				h1 = (HashSet) gn.getUserData("IDominator");
				it = h1.iterator();
				System.out.println();
				System.out.println("Inverse Dominators: ");
				while (it.hasNext()) {
					CFGNode gdom = (CFGNode) it.next();

					System.out.println(gdom.getLabel() + " ");
				}
				h1 = (HashSet) gn.getUserData("Alive definitions");
				it = h1.iterator();
				System.out.println();
				System.out.println("Alive definitions: ");
				while (it.hasNext()) {
					Object p = it.next();

					Debug.D("TYPE: " + p.getClass());
					ArrayList pair = (ArrayList) p;
					String def = (String) pair.get(0);
					CFGNode gfcn = (CFGNode) pair.get(1);

					System.out.println(gfcn.getLabel() + " " + def);
				}
			}
		}
	}
}
