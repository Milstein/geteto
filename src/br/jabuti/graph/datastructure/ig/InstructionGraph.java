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

package br.jabuti.graph.datastructure.ig;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import org.aspectj.apache.bcel.generic.CodeExceptionGen;
import org.aspectj.apache.bcel.generic.ConstantPoolGen;
import org.aspectj.apache.bcel.generic.InstructionHandle;
import org.aspectj.apache.bcel.generic.InstructionList;
import org.aspectj.apache.bcel.generic.JsrInstruction;
import org.aspectj.apache.bcel.generic.MethodGen;
import org.aspectj.apache.bcel.generic.RET;
import org.aspectj.apache.bcel.generic.Type;
import org.omg.CORBA.DynAnyPackage.Invalid;

import br.jabuti.graph.datastructure.GraphNode;
import br.jabuti.graph.datastructure.ListGraph;
import br.jabuti.graph.datastructure.reducetree.RRDominator;
import br.jabuti.graph.datastructure.reducetree.RRReqLocal;
import br.jabuti.graph.datastructure.reducetree.RoundRobinExecutor;
import br.jabuti.util.InstructCtrl;

/**
 * <p>
 * This class represents a program graph where each node is a single instruction of the JVM
 * bytecode. Besides building the graph, this class has methods to collect information for the
 * instructions. The information collected is described in the {@link InstructionNode} class and
 * include all the stack and local variable configurations when the instruction is reached, which
 * instructions filled the stack spots, etc <br>
 * </p>
 * 
 * <p>
 * A few features about the graph. First, the exception handlers, i.e, the first
 * {@link InstructionNode} of an exeception handler is linked to all the instructions inside its
 * hadled block. This linking is done using the "secondary edge" of the graph (See the
 * {@link ListGraph} class for explanation).
 * </p>
 * 
 * <p>
 * The code of a in-method subroutine is replicated for each invocation. So, for example, the
 * following code
 * 
 * <PRE>
 *     NOP
 *     JSR L1
 *     NOP
 *     JSR L1
 *     RETURN
 * L1:
 *     NOP
 *     RET
 * </PRE>
 * 
 * would be represented by the following graph:<BR>
 * 
 * <PRE>
 *                           NODE       |     NEXT
 *                          ------------+-----------
 *                          (1) NOP     |  (2)
 *                          (2) JSR     |  (6)
 *                          (3) NOP     |  (4)
 *                          (4) JSR     |  (8)
 *                          (5) RETURN  |   -
 *                          (6) NOP     |  (7)
 *                          (7) RET     |  (3)
 *                          (8) NOP     |  (9)
 *                          (9) RET     |  (5)
 * </PRE>
 * 
 * <BR>
 * I think this is the best solution because it avoids many problems to calculated stack a local
 * variable configurations. In addition, at the end we probably want to relate an instruction node
 * to a point in the source code and it is always possible to related several instruction nodes to
 * the same source piece.<BR>
 * Anyway, if you do not like this approach, the class has the method
 * {@link InstructionGraph#mergeJSR} that, after the graph is built, will join the nodes that
 * correspond to the same instruction (like nodes 7 - 9 and 6 - 8 above) and fix the pointers to
 * reflect this change.
 * </p>
 * 
 * <p>
 * All the instructions of the method will be in the graph. Even the unreachable ones. And it is not
 * uncommon to find unreachable code in Java classes. For example, in the code bellow, instruction
 * is unreacheable
 * 
 * <PRE>
 * 1       NOP
 * 2       JSR L1
 * 3       GOTO L2
 *         ....
 *   L1
 * 4       NOP
 * 5       ALOAD 10
 * 6       ATHROW
 * </PRE>
 * 
 * </p>
 * 
 * <p>
 * The graph has a single entry node: the first instruction of the method. The exit node is node
 * identified (since in reality several exit nodes may be present due to throw intructions).
 * </p>
 * 
 * @see ListGraph
 * @see InstructionNode
 */
public class InstructionGraph extends ListGraph
{
	private static final long serialVersionUID = -6272077012414454220L;

	private static final Type EXCEPTION_TYPE = Type.getReturnType("()Ljava.lang.Exception;");

	/**
	 * The method that this graph represents.
	 */
	private MethodGen method;

	// Organize the instructions, so that it's thereafter easy to find a given instruction.
	private Map<InstructionHandle, InstructionNode> verify = new HashMap<InstructionHandle, InstructionNode>(); 

	private Map<InstructionHandle, InstructionHandle> jumpsAndEntries = new HashMap<InstructionHandle, InstructionHandle>();

	
	/**
	 * Constructor that creates a graph from a method code.
	 * 
	 * @param method The method for which the graph will be created
	 */
	public InstructionGraph(MethodGen method)
	{
		this.method = method;
		analyseMethod();
	}

	@Override
	public synchronized boolean add(Object node)
	{
		InstructionHandle ih = (InstructionHandle) node;
		InstructionNode in = new InstructionNode(ih);
		verify.put(ih, in);
		return super.add(in);
	}

	/**
	 * Analyse the method, extract the instruction nodes and build the graph.
	 */
	private void analyseMethod()
	{
		//  Instructions that form the method
		InstructionList il = method.getInstructionList();
		if (il == null) {
			return; 
		} 
	
		Iterator<InstructionHandle> ihi = il.iterator();
		while (ihi.hasNext()) {
			InstructionHandle ih = ihi.next();
			add(ih);
		}
		
		findEdges();
	}
	
	private void findEdges()
	{
		CodeExceptionGen[] ceg = method.getExceptionHandlers();

		// Find the primary and secondary edges for each instruction node
		Iterator<InstructionNode> ini = verify.values().iterator();
		while (ini.hasNext()) {
			// Include edges from each node to its exception handlers
			InstructionNode in = ini.next();
			for (CodeExceptionGen c : ceg) {
				int init = c.getStartPC().getPosition();
				int end = c.getEndPC().getPosition();
				if (in.ih.getPosition() >= init && in.ih.getPosition() <= end) {
					addSecondaryEdge(in, verify.get(c.getHandlerPC()));
				}
			}

			// Find the primary edges
			InstructionHandle[] nx = InstructCtrl.nextToExec(in.ih);
			if (in.ih.getInstruction() instanceof JsrInstruction) {
				// Coloca o proximo fisico como proximo no grafo
				InstructionHandle nih = in.ih.getNext();
				addPrimaryEdge(in, (GraphNode) verify.get(nih));
				// mapeamento JSR -- Entrada
				jumpsAndEntries.put(in.ih, nx[0]);
			} else {
				for (int j = 0; j < nx.length; j++) {
					addPrimaryEdge(in, verify.get(nx[j]));
				}
			}
		}
	}
	
	private void findDominators()
	{
		CodeExceptionGen[] ceg = method.getExceptionHandlers();
		int lastsize = 0;
		int cursize = 0;
		int cont;
		
		Set<InstructionNode> entryNodes = new HashSet<InstructionNode>();
		InstructionList il = method.getInstructionList();
		entryNodes.add(verify.get(il.getStart()));
		Iterator<InstructionNode> ini = entryNodes.iterator();
		
		// Calculate the dominant instruction nodes
		Map<InstructionNode, Set> dominantEntries = new HashMap();
		while (ini.hasNext()) {
			InstructionNode entr = ini.next();
			setEntryNode(entr);

			// Calcula os dominators
			RRDominator rrd = new RRDominator("Dominator");
			roundRobinAlgorithm(rrd, true);
			Set edm = new HashSet();
			dominantEntries.put(entr, edm);
			for (int j = 0; j < size(); j++) {
				InstructionNode in = (InstructionNode) elementAt(j);
				Set dom = (HashSet) in.getUserData("Dominator");
				if (dom != null && dom.contains(entr)) {
					edm.add(in);
					if (in.ih.getInstruction() instanceof JsrInstruction) {
						entryNodes.add(verify.get(jumpsAndEntries.get(in.ih)));
					}
				}
			}
		}
		
		
		Iterator<InstructionNode> eini = entryNodes.iterator();
		InstructionNode entryNode = eini.next();

		
		Set<InstructionNode> dominantsForEntryNode = dominantEntries.get(entryNode);
		Iterator<InstructionNode> idfen = dominantsForEntryNode.iterator();
		while (idfen.hasNext()) {
			InstructionNode isJmp = idfen.next();
			if (! (isJmp.ih.getInstruction() instanceof JsrInstruction)) {
				continue;
			}
			
			InstructionNode retTarget = (InstructionNode) getLeavingNodesByPrimaryEdge(isJmp).elementAt(0);

				removePrimaryEdge(isJmp, retTarget);
				InstructionNode retNode = null;

				cont++;
				// achou JSR. deve duplicar o seu destino
				// entr eh o no destino do JSP
				InstructionHandle ihEntr = (InstructionHandle) jumpsAndEntries.get(isJmp.ih);
				InstructionNode entr = (InstructionNode) verify.get(ihEntr);
				// entrSet eh o conjunto de nos dominados pelo no de entrada
				HashSet entrSet = (HashSet) dominantEntries.get(entr);
				Iterator in = entrSet.iterator();
				Hashtable auxVerify = new Hashtable();

				while (in.hasNext()) { // duplica cada elemento no conjunto
					InstructionNode inSet = (InstructionNode) in.next();
					InstructionNode newNode = new InstructionNode(inSet.ih);

					// insere no grapho
					add(newNode);

					// seta qual eh o jsr correspondente
					newNode.setDomEntry(isJmp);

					aux.add(newNode);
					auxVerify.put(inSet, newNode);
					if (newNode.ih.getInstruction() instanceof RET) {
						retNode = newNode;
					}
				}

				// agora precisa fazer as ligacoes
				in = entrSet.iterator();
				while (in.hasNext()) {
					InstructionNode inSet = (InstructionNode) in.next();
					InstructionNode newNode = (InstructionNode) auxVerify.get(inSet);
					GraphNode[] nx = (GraphNode[]) getLeavingNodesByPrimaryEdge(inSet).toArray(
									new GraphNode[0]);

					for (int j = 0; j < nx.length; j++) {
						InstructionNode nxin = (InstructionNode) auxVerify.get(nx[j]);

						addPrimaryEdge(newNode, nxin);
					}
					nx = (GraphNode[]) getLeavingNodesBySecondaryEdge(inSet).toArray(
									new GraphNode[0]);
					for (int j = 0; j < nx.length; j++) {
						InstructionNode nxin = (InstructionNode) auxVerify.get(nx[j]);

						// se nxin eh null significa que o no nao estah no mesmo
						// conjunto. Para corrigir isso no final eh feito
						// tratamento especial (ver *** )
						if (nxin != null) {
							addSecondaryEdge(newNode, nxin);
						}
					}
				}
				// liga agora o JSR e o RET, se existir
				addPrimaryEdge(isJmp, (InstructionNode) auxVerify.get(entr));
				if (retNode != null) {
					addPrimaryEdge(retNode, retTarget);
				}
			}
		} while (cont > 0);

		// Faz interseccao com vetor aux
		while (eini.hasNext()) {
			InstructionNode next = eini.next();
			HashSet edm = (HashSet) dominantEntries.get(next);
			Iterator it = edm.iterator();

			while (it.hasNext()) {
				InstructionNode in = (InstructionNode) it.next();
				removeNode(in);
			}
		}

		removeEntryNodes();
		setEntryNode(entryNode);

		// acha Depth firs tree
		GraphNode[] dft = findDFTNodes(true);

		for (int i = 0; i < dft.length; i++) {
			// include edges from each node to its exception handlers
			InstructionNode vf = (InstructionNode) dft[i];

			nextException: for (int j = 0; j < ceg.length; j++) {
				int init, fim;

				init = ceg[j].getStartPC().getPosition();
				fim = ceg[j].getEndPC().getPosition();
				InstructionHandle handler = ceg[j].getHandlerPC();

				if (vf.ih.getPosition() >= init && vf.ih.getPosition() <= fim) {
					Vector vnx = getLeavingNodesBySecondaryEdge(vf);

					for (int k = 0; k < vnx.size(); k++) {
						InstructionNode nx = (InstructionNode) vnx.elementAt(k);

						if (nx.ih == handler) {
							continue nextException;
						}
					}
					// procura quem trata da interrup��o
					for (int k = i - 1; k >= 0; k--) {
						InstructionNode dftk = (InstructionNode) dft[k];

						if (dftk.ih.getPosition() >= init && dftk.ih.getPosition() <= fim) {
							Vector vx = getLeavingNodesBySecondaryEdge(dft[k]);
							InstructionNode hdl = null;

							for (int z1 = 0; z1 < vx.size(); z1++) {
								hdl = (InstructionNode) vx.elementAt(z1);
								if (hdl.ih == handler) {
									break;
								}
							}
							vf.addSecNext(hdl);
							break;
						}
					}
				}

			}
		}
	}

	/**
	 * <P>
	 * This is the important method in this class. Once the graph has been created, it will fill the
	 * instruction nodes with some information, as described in {@link InstructionNode}.
	 * <p>
	 * <p>
	 * The algorithm is similar to that presented by Sun to compute the stack to the instruction.
	 * There is local array where the "changed" instructions are inserted. The program removes an
	 * instruction from it and compute for all its successors the stack and local variable
	 * configuration. If they changed for a given successor x, then x is added to the changed array.
	 * Exception handlers (i.e., secondary edges) are also used to get the successor set.
	 * </p>
	 * <p>
	 * Initialy the entry node and all the exception handlers are inserted in the changed array. In
	 * addition, the array is consulted using a stack policy, except for the initial insertion of
	 * the exception handlers that are placed at the bottom of the stack. This because the handlers
	 * are changed very often. And then pl;acing them at the bottom might avoid removing/inserting
	 * it in the array many times
	 * </p>
	 * 
	 * @param all Indicates if all the possible configurations should be computed or not. Computing
	 *        all the configurations means that all the possible configurations that arrive at a
	 *        struction will be store. The "not all" option means that a new configuration will be
	 *        merged with the old one and only one is stored
	 * 
	 * @throws InvalidInstructionException If the method finds an node with a non valid JVM
	 *         instruction
	 * @throws InvalidStackArgument If e struction is reached by two configuration of the stack with
	 *         differen sizes
	 */

	public void calcStack(boolean all) throws InvalidInstructionException, InvalidStackArgument
	{
		if (method.getInstructionList() == null) {
			return;
		}
		Vector changed = new Vector();
		ConstantPoolGen cp = method.getConstantPool();

		calcReqLocal();
		// pega o tipo das variaveis locais dos argumentos
		Type[] pars = method.getArgumentTypes();
		int h = method.getMaxLocals();
		VMLocal locals = new VMLocal(h);

		// pega a instrucao inicial
		InstructionNode start = (InstructionNode) getFirstEntryNode();

		// se nao eh static tem que colocar "this" em local[0]
		int l = 0;

		if (!method.isStatic()) {
			String s = method.getClassName();

			s = "()L" + s.replace('.', '/') + ";";
			Type t = Type.getReturnType(s);

			locals.add(t, l++);
			start.defLocalAdd(InstructionNode.STR_LOCAL + 0);
		}

		// coloca or argumentos nas outras "locals"
		for (int j = 0; j < pars.length; l += pars[j].getSize(), j++) {
			locals.add(pars[j], l);
			start.defLocalAdd(InstructionNode.STR_LOCAL + l);
		}

		// calcula o stack para a instrucao inicial
		if (all) {
			start.initAllStack(new VMStack(method.getMaxStack()), locals, cp);
		} else {
			start.initStack(new VMStack(method.getMaxStack()), locals, cp);
		}
		changed.add(start);

		// calcula o stack e locais para cada exception handler
		CodeExceptionGen[] ceg = method.getExceptionHandlers();
		Hashtable hs = new Hashtable();

		for (int i = 0; i < ceg.length; i++) {
			Type tex = ceg[i].getCatchType();

			if (tex == null) {
				tex = EXCEPTION_TYPE;
			}
			hs.put(ceg[i].getHandlerPC(), tex);
		}

		locals = new VMLocal(h);
		VMStack exst = new VMStack(method.getMaxStack());

		for (int i = 0; i < size(); i++) {
			InstructionNode vef = (InstructionNode) elementAt(i);
			Type tex = (Type) hs.get(vef.ih);

			if (tex == null) {
				continue;
			}
			exst.push(new VMStackElement(tex, "", null));

			if (all) {
				vef.initAllStack(exst);
			} else {
				vef.initStack(exst, locals, cp);
			}
			changed.add(vef); // insere no fim embora "changed" seja
			// usado como pilha
			exst.pop(); // esvazia a pilha p/ o proximo
		}

		while (!changed.isEmpty()) {
			InstructionNode curr = (InstructionNode) changed.remove(0);

			curr.setChanged(false);
			// atualiza cada sucessor
			Vector nx = getLeavingNodesByPrimaryEdge(curr);

			for (int i = 0; i < nx.size(); i++) {
				InstructionNode fx = (InstructionNode) nx.elementAt(i);
				boolean b = fx.changed();

				if (all) {
					fx.calcAllStack(curr, cp);
				} else {
					fx.calcStack(curr, cp);
				}
				if ((!b) && fx.changed()) {
					changed.insertElementAt(fx, 0);
				} else {
					fx.setChanged(b);
				}
			}

			// atualiza cada tratador de excessoes
			Vector ex = getLeavingNodesBySecondaryEdge(curr);

			for (int i = 0; i < ex.size(); i++) {
				InstructionNode exi = (InstructionNode) ex.elementAt(i);
				boolean b = exi.changed();

				if (all) {
					exi.calcAllLocal(curr, cp);
				} else {
					exi.calcLocal(curr, cp);
				}

				if ((!b) && exi.changed()) {
					changed.insertElementAt(exi, 0);
				} else {
					exi.setChanged(b);
				}
			}
			curr.removeNextStackLocal();
		}
		// now merge the subrotines
		// mergeJSR();
	}

	/**
	 * <p>
	 * This method will join nodes that represent the same instruction in a single node. Such nodes
	 * are derived from im-method subroutines, i.e., pieces of code reached through a JSR
	 * instruction.
	 * <p>
	 * <p>
	 * The repeated nodes are deleted from the graph.
	 * 
	 * @throws Invalid StackArgument Should not throw...
	 */
	public void mergeJSR() throws InvalidStackArgument
	{
		Hashtable aux = new Hashtable();

		for (int i = size() - 1; i >= 0; i--) { // comeca do fim pois os nos podem ser deletados

			InstructionNode vf = (InstructionNode) elementAt(i);
			InstructionHandle ih = vf.ih;

			if (!aux.containsKey(ih)) { // instrucao ainda nao esta na tabela
				aux.put(ih, vf);
			} else { // instrucao jah esta na tabela, faz merge
				InstructionNode vf0 = (InstructionNode) aux.get(ih);

				if (vf0.isUnreacheable()) {
					InstructionNode t = vf;

					vf = vf0;
					vf0 = t;
					aux.put(ih, vf0);
				}

				if (!(vf0.isUnreacheable() || vf.isUnreacheable())) {
					vf0.merge(vf.getStack(), vf.getLocals());
				}

				// adiciona predecessores de um ao outro
				Vector arr = getArrivingNodesByPrimaryEdge(vf);

				for (int j = 0; j < arr.size(); j++) {
					InstructionNode jsr = (InstructionNode) arr.elementAt(j);

					addPrimaryEdge(jsr, vf0);
				}

				// adiciona sucessores de um ao outro
				arr = getLeavingNodesByPrimaryEdge(vf);
				for (int j = 0; j < arr.size(); j++) {
					InstructionNode jsr = (InstructionNode) arr.elementAt(j);

					addPrimaryEdge(vf0, jsr);
				}
				// tira o noh repetido do grafo
				removeNode(vf);
			}
		}
	}

	/**
	 * <p>
	 * This method is used to calculated a set named "required locals". This set indicates which
	 * loocal variables reach a given instruction and must be forwarded because they will be used in
	 * a successor instruction.
	 * </p>
	 * <p>
	 * The local variables are kind of dangerous if we are trying to keep track of all possible
	 * configurations for one instruction. On the other hand, in most cases not all the variables
	 * that change from one configuration to another are really significant. Lets take for example
	 * the code bellow
	 * </p>
	 * 
	 * <pre>
	 *          1   ILOAD    10
	 *          2   NOP
	 *          3   ILOAD    5
	 *          4   ISTORE   4
	 * </pre>
	 * <p>
	 * Suppose the method uses a set of 12 variables and that in a certain point we have a
	 * configuration Y for the instruction 1 and found a different configuration can reach that same
	 * instruction. Well, if the new configuration has a different variable number 5, this
	 * configuration should be stored because this new configuration must be passed to instruction
	 * 2, stored there and from there passed to instruction 3, where it is an argument for the
	 * instruction. On the other hand, if the new configuration that reaches instruction 1 has a
	 * change only in local variable 4, it can be discarded, because there is no use on forwarding
	 * this new configuration to instructions 2, 3, 4 or any subsequent instruction because in
	 * instruction 4 the value on variable 4 will be overwriten.
	 * </p>
	 * The programmer does not need to call this method. It is called inside
	 * {@link InstructionGraph#calcStack}.
	 */
	public void calcReqLocal()
	{
		RoundRobinExecutor rre = new RRReqLocal(InstructionNode.reqLocalLabel);
		roundRobinAlgorithm(rre, false);
	}
}