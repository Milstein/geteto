package br.jabuti.graph;


import org.aspectj.apache.bcel.classfile.ClassParser;
import org.aspectj.apache.bcel.classfile.JavaClass;
import org.aspectj.apache.bcel.classfile.Method;
import org.aspectj.apache.bcel.generic.ClassGen;
import org.aspectj.apache.bcel.generic.ConstantPoolGen;
import org.aspectj.apache.bcel.generic.MethodGen;
import org.junit.Before;

import br.jabuti.criteria.AbstractCriterion;
import br.jabuti.graph.datastructure.dug.CFG;
import br.jabuti.graph.datastructure.reducetree.DominatorTree;
import br.jabuti.graph.datastructure.reducetree.DominatorTreeNode;
import br.jabuti.graph.datastructure.reducetree.RRDominator;
import br.jabuti.graph.datastructure.reducetree.RRLiveDefs;

public class DominatorTreeTest
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
			if (args.length > 1 && !args[1].equals(methods[i].getName())) {
				continue;
			}
			System.out.println("\n\n--------------------------");
			System.out.println(methods[i].getName());
			System.out.println("--------------------------");
			MethodGen mg = new MethodGen(methods[i], java_class.getClassName(), cp);
			CFG g = new CFG(mg, cg);

			// g.computeDefUse();
			RRDominator rrd = new RRDominator("Dominator");

			g.roundRobinAlgorithm(rrd, true);

			rrd = new RRDominator("IDominator");
			g.roundIRobinAlgorithm(rrd, true);

			RRLiveDefs rral = new RRLiveDefs("Alive definitions", AbstractCriterion.ALL);

			g.roundRobinAlgorithm(rral, true);

			System.out.println("\n\nPre dominator TREE ***************");
			DominatorTree dtDom = new DominatorTree(g, "Dominator");

			dtDom.setDefaultNumbering();
			//dtDom.print(System.out);

			System.out.println("\n\nPos dominator TREE ***************");
			DominatorTree dtIDom = new DominatorTree(g, "IDominator");

			dtIDom.setDefaultNumbering();
			//dtIDom.print(System.out);

			dtDom.merge(dtIDom);
			System.out.println("\n\nMerged dominator TREE ***************");
			//dtDom.print(System.out);

			DominatorTree bbDom = (DominatorTree) DominatorTree.reduceSCC(dtDom, false);

			bbDom.setEntryNode(bbDom.getReduceNodeOf(dtDom.getFirstEntryNode()));
			bbDom.setDefaultNumbering();
			System.out.println("\n\nBasic Block Dominator TREE ***************");
			//bbDom.print(System.out);

			System.out.println("\n\nFinal Basic Block Dominator TREE ***************");
			bbDom.removeComposite(false);
			//bbDom.print(System.out);

			System.out.println("\n\nPesos dos nos ***************");
			for (int z1 = 0; z1 < bbDom.size(); z1++) {
				DominatorTreeNode dtn = (DominatorTreeNode) bbDom.get(z1);

				System.out.println(dtn);
				System.out.println("\nPeso: " + bbDom.getWeigth(dtn));
			}
		}
	}

}
