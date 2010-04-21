/*  Copyright 2003  Auri Marcelo Rizzo Vicenzi, Marcio Eduardo Delamaro, 			    Jose Carlos Maldonado

    This file is part of Jabuti.

    Jabuti is free software: you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as 
    published by the Free Software Foundation, either version 3 of the      
    License, or (at your option) any later version.

    Jabuti is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU Lesser General Public License
    along with Jabuti.  If not, see <http://www.gnu.org/licenses/>.
 */

package br.jabuti.graph.datastructure.dug;

import java.util.Collections;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

import org.aspectj.apache.bcel.generic.ClassGen;
import org.aspectj.apache.bcel.generic.ConstantPoolGen;
import org.aspectj.apache.bcel.generic.GotoInstruction;
import org.aspectj.apache.bcel.generic.Instruction;
import org.aspectj.apache.bcel.generic.InstructionHandle;
import org.aspectj.apache.bcel.generic.InvokeInstruction;
import org.aspectj.apache.bcel.generic.JsrInstruction;
import org.aspectj.apache.bcel.generic.MethodGen;
import org.aspectj.apache.bcel.generic.RET;

import br.jabuti.graph.datastructure.GraphNode;
import br.jabuti.graph.datastructure.ListGraph;
import br.jabuti.graph.datastructure.ig.InstructionGraph;
import br.jabuti.graph.datastructure.ig.InstructionNode;
import br.jabuti.graph.datastructure.ig.InvalidInstructionException;
import br.jabuti.graph.datastructure.ig.InvalidStackArgument;

/**
 * This class extends {@link ListGraph} and implements a Program Graph where each node is a block of
 * JVM instructions.<BR>
 * The using a {@link MethodGen} object, a {@link InstructionGraph} is contructed and the transormed
 * in a CFG. The diference between then is that in the first each JVM instruction is a node of the
 * graph. In this class, blocks of instructions are joint in a single node (a {@link CFGNode}).<BR>
 * The instructions in a block are always executed together but may not be contiguos in the JVM
 * code. It is the case of a goto instruction that will be in the same block of the target
 * instruction. If this is a problem, future versions should change this feature
 * 
 * @version: 0.00001
 * @author: Marcio Delamaro
 * 
 * 
 */
public class CFG extends ListGraph
{

	/**
	 * Added to jdk1.5.0_04 compiler
	 */
	private static final long serialVersionUID = 7376073759505678473L;

	/** Auxiliar var */
	transient private Hashtable htable;

	/** Constants to use on CFG creation configuration */
	static final public int NONE = 0, NO_CALL_NODE = 1;

	/** Determines some configurations on CFG configuration */
	private int config;

	/**
	 * Construct a CFG for a given method passed as argument.
	 * 
	 * @param mg The method for which the CFG will be created.
	 * @throws InvalidInstructionException If {@link CFG#createFromCode} throws this exception
	 * @throws InvalidStackArgument If {@link CFG#createFromCode} throws this exception
	 */
	public CFG(MethodGen mg, ClassGen cl) throws InvalidInstructionException, InvalidStackArgument
	{
		super();
		createFromCode(mg, cl, mg.getConstantPool());
		computeDefUse();
		releaseInstructionGraph();
	}

	/**
	 * This method is called by this class' constructor to create the CFG
	 * 
	 * @param mg The method for which the CFG will be created.
	 * @throws InvalidInstructionException If {@link InstructionGraph#calcStack} throws this
	 *         exception
	 * @throws InvalidStackArgument If {@link InstructionGraph#calcStack} throws this exception
	 */
	private void createFromCode(MethodGen mg, ClassGen clazz, ConstantPoolGen cp)
					throws InvalidInstructionException, InvalidStackArgument
	{
		InstructionGraph ig = new InstructionGraph(mg);
		if (ig.size() == 0) {
			return;
		}
		ig.calcStack(true);
		htable = new Hashtable();
		setEntryNode(newNodeTo((InstructionNode) ig.getFirstEntryNode(), clazz, cp, ig));
		cleanUp(mg); // remove nodes with lonly goto's
		Collections.sort(this, new CFGNode());

		computeExit(true);
		for (int i = 0; i < size(); i++) {
			CFGNode gfn = (CFGNode) elementAt(i);

			gfn.setNumber(gfn.getStart());
		}

		// computa labels
		boolean changed;

		do {
			changed = false;
			for (int i = 0; i < size(); i++) {
				CFGNode gfn = (CFGNode) elementAt(i);
				CFGNode edom = gfn.getEntryDom();

				if (edom != null) {
					if (gfn.prefix == null || !gfn.prefix.equals(edom.getLabel())) {
						gfn.prefix = edom.getLabel();
						changed = true;
					}
				}
			}
		} while (changed);
	}

	/**
	 * <p>
	 * This is a recursive method that constructs the CFG. It takes a {@link InstructionNode} and
	 * creates a block for it. Then take the successors of this instruction node and <BR>
	 * </p>
	 * <UL>
	 * <LI>include it in the same block; or
	 * <LI>call itself whith the successor what will create a new block.
	 * </UL>
	 * <p>
	 * A new block will be create if 1) the current instruction has more than one successor; 2) the
	 * current instruction is a JSR or RET instruction; 3) the successor has more than one
	 * predecessor (primary or secondary); 4) the successor does not share the same set of exception
	 * handlers of the current instruction.
	 * </p>
	 * 
	 * @param x The instruction node to be inserted in a block of the CFG
	 * @return The {@link CFGNode} where the instruction has been inserted
	 */
	private CFGNode newNodeTo(InstructionNode x, ClassGen clazz, ConstantPoolGen cp,
					InstructionGraph ig)
	{

		// se x jah foi inserido em algum noh, retorna aquele noh
		if (htable.containsKey(x)) {
			return (CFGNode) htable.get(x);
		}

		// senao, cria um novo no e insere a instrucao x
		CFGNode currNode = new CFGNode();

		add(currNode);
		boolean spl;
		Instruction ins;

		// pega para o no sendo inserido qual eh seu pto de entrada
		// caso esteja numa subrotina (JSR)
		InstructionNode edom = x.getDomEntry();

		if (edom != null) {
			currNode.setEntryDom((CFGNode) htable.get(edom));
		}

		do {
			currNode.add(x);
			htable.put(x, currNode);

			Set<GraphNode> nx = ig.getLeavingNodesByPrimaryEdge(x);
			Set<GraphNode> nxex = ig.getLeavingNodesBySecondaryEdge(x);

			// se instrucao eh JSR ou RET, entao divide
			ins = x.ih.getInstruction();

			/*
			 * boolean isSuperCall = false;
			 * 
			 * // Verifica se eh uma chamada ao super... if ( ins instanceof INVOKESPECIAL ) {
			 * INVOKESPECIAL ix = (INVOKESPECIAL) ins; String name = ix.getMethodName(cp);
			 * 
			 * if (name.equals("<init>") ) { // pega a classe ao qual o metodo chamado pertence
			 * String callCl = ix.getClassName(cp); // pega a superclasse ao qual o metodo corrente
			 * pertence String superCl = clazz.getSuperclassName(); if ( callCl.equals(superCl) ||
			 * callCl.equals(clazz.getClassName()) ) { isSuperCall = true; } } }
			 * 
			 * O trecho acima foi substituido pela instrucao abaixo. Na construcao do
			 * InstructionGraph, jah eh calculado se um no � um super ou nao.
			 */

			boolean isSuperCall = x.isSuper;

			
			/* TODO: Why the NO_CALL_NODE?
			spl = (ins instanceof JsrInstruction) || (ins instanceof GotoInstruction)
							|| (ins instanceof RET)
							|| ((config & NO_CALL_NODE) == 0 && ins instanceof InvokeInstruction)
							|| isSuperCall;
			*/
			
			spl = (ins instanceof JsrInstruction) || (ins instanceof GotoInstruction)
							|| (ins instanceof RET)
							|| (ins instanceof InvokeInstruction)
							|| isSuperCall;

			// se numero de sucessores > 1, entao divide
			spl |= nx.size() != 1;
			
			Iterator<GraphNode> i = nx.iterator(); 
			while (i.hasNext() && !spl) {
				GraphNode node = i.next();

				// se algum sucessor tem mais de 1 predecessor, seja
				// normal ou atraves de excessao, divide
				InstructionNode in = (InstructionNode) node;
				Set<GraphNode> ar = ig.getArrivingNodes(in, true);

				spl = ar.size() > 1;

				// ou se o sucessor tem um numero diferente de tratadores
				// de excessao, tbem divide
				Set<GraphNode> q = ig.getLeavingNodesBySecondaryEdge(in);

				spl |= nxex.size() != q.size();

				// ou se o sucessor nao tem os mesmos tratadores
				// de excessao, tbem divide
				Iterator<GraphNode> j = q.iterator();
				while (j.hasNext() && !spl) {
					spl |= !nxex.contains(j.next());
				}
			}
			if (!spl) { // nao divide, inclui o sucessor de x no bloco
				// corrente
				x = (InstructionNode) nx.iterator().next();
			} else {
				if (isSuperCall) {
					CFGNode cNode = new CFGSuperNode(currNode, x, cp);

					cNode.setEntryDom(currNode.getEntryDom());
					removeNode(currNode);
					add(cNode);
					for (int f = 0; f < cNode.instructions.size(); f++) {
						InstructionNode inx = (InstructionNode) cNode.instructions.elementAt(f);

						htable.put(inx, cNode);
					}
					currNode = cNode;
// TODO: Why NO_CALL_NODE?
//				} else if ((config & NO_CALL_NODE) == 0 && ins instanceof InvokeInstruction) {
				} else if (ins instanceof InvokeInstruction) {
					CFGNode cNode = new CFGCallNode(currNode, x, cp);

					cNode.setEntryDom(currNode.getEntryDom());
					removeNode(currNode);
					add(cNode);
					for (int f = 0; f < cNode.instructions.size(); f++) {
						InstructionNode inx = (InstructionNode) cNode.instructions.elementAt(f);

						htable.put(inx, cNode);
					}
					currNode = cNode;
				}
				
				Iterator<GraphNode> j = nx.iterator();
				while (j.hasNext()) {
					addPrimaryEdge(currNode, newNodeTo((InstructionNode) j.next(), clazz, cp, ig));
				}
				
				j = nxex.iterator();
				while (j.hasNext()) {
					addSecondaryEdge(currNode, newNodeTo((InstructionNode) j.next(), clazz, cp, ig));
				}
			}
		} while (!spl);
		return currNode;
	}

	/**
	 * This method removes unconditional branch instructions that are alone in a node
	 */
	private void cleanUp(MethodGen meth)
	{
		for (int i = size() - 1; i >= 0; i--) {
			CFGNode gfcn = (CFGNode) elementAt(i);

			InstructionHandle start = br.jabuti.util.InstructCtrl.findInstruction(meth, gfcn
							.getStart());
			InstructionHandle end = br.jabuti.util.InstructCtrl
							.findInstruction(meth, gfcn.getEnd());
			Instruction is = start.getInstruction(), ie = end.getInstruction();
			if (is == ie) {
				if (is instanceof GotoInstruction)
					jumpOverNode(gfcn, false); // removes gfcn but set next
			}
		}
	}

	/**
	 * This method gets rid of the {@link InstructionGraph} that has been used to build this object.
	 * Once all the useful information has been collected and abstracted to the block level in this
	 * object, the original instruction graph can be disposed. This may be necessary because that
	 * graph is very demanding in terms of memory consumption.
	 * 
	 */
	public void releaseInstructionGraph()
	{
		for (int i = 0; i < size(); i++) {
			CFGNode gn = (CFGNode) elementAt(i);

			gn.releaseInstructions();
		}
	}

	/**
	 * This metod computes the definitions and uses for each graph node
	 */
	public void computeDefUse()
	{
		for (int i = 0; i < size(); i++) {
			CFGNode gn = (CFGNode) elementAt(i);

			gn.computeDefUse();
		}
	}
}
