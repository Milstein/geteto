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



package br.jabuti.graph.datastructure.reducetree;


import java.util.*;
import org.aspectj.apache.bcel.classfile.*;
import org.aspectj.apache.bcel.generic.*;

import br.jabuti.graph.datastructure.Graph;
import br.jabuti.graph.datastructure.ListGraph;
import br.jabuti.graph.datastructure.GraphNode;
import br.jabuti.graph.datastructure.dug.CFG;


/**
 This is a class used to construct a pre- or pos-dominator
 tree from a CFG. 

 @version: 0.00001
 @author: Marcio Delamaro

 */
public class DominatorTree extends ReduceGraph {
	
    /**
	 * Added to jdk1.5.0_04 compiler
	 */
	private static final long serialVersionUID = 8555812394502893222L;

	/** Creates a pre- or a pos-dominator tree for a
     * {@link ListGraph}. The set of dominator must have been creted
     * before calling this constructor.
     * @param g - the {@link ListGraph} to be analyzed
     * @param label - the label to access the dominator set of each
     * node in the original graph */
    public DominatorTree(ListGraph g, String label) {
        super();
        for (int i = 0; i < g.size(); i++) {
            GraphNode gn = (GraphNode) g.elementAt(i);
            DominatorTreeNode rn = new DominatorTreeNode(gn);

            add(rn); // inclui no no grafo
            if (g.isEntryNode(gn)) {
                setEntryNode(rn);
            }
        }
        for (int i = 0; i < g.size(); i++) {
            GraphNode gn = (GraphNode) g.elementAt(i);
            ReduceNode rn = getReduceNodeOf(gn);
            HashSet domHS = (HashSet) gn.getUserData(label);
            Iterator it = domHS.iterator();

            while (it.hasNext()) {
                GraphNode donmGN = (GraphNode) it.next();

                if (donmGN != gn) {
                    addPrimaryEdge(getReduceNodeOf(donmGN), rn);
                }
            }
        }
        removeComposite(false);
        computeExit(true);
    }
 	 	
    DominatorTree() {
        super();
    }

    public void merge(DominatorTree g) {
        for (int j = 0; j < size(); j++) {
            DominatorTreeNode rn = (DominatorTreeNode) elementAt(j);
            GraphNode gn = rn.getOriginalNode();
            ReduceNode rnOther = g.getReduceNodeOf(gn);
            Set<GraphNode> next = rnOther.getPrimNext();
            Iterator<GraphNode> i = next.iterator();
            while (i.hasNext()) {
                DominatorTreeNode rnNextOther = (DominatorTreeNode) i.next();
                GraphNode gnOther = rnNextOther.getOriginalNode();
                ReduceNode rnNext = getReduceNodeOf(gnOther);
                addPrimaryEdge(rn, rnNext); 
            }
        }
        removeExitNodes();
    }
	
    public void markCovered(GraphNode x) {
        DominatorTreeNode rn = (DominatorTreeNode) getReduceNodeOf(x);

        if (rn == null) {
            return;
        }
        rn.setCovered(true);
    }

    public int getWeigth(DominatorTreeNode rn) {
        return getWeigth(rn, new HashSet());
    }
	
    private int getWeigth(DominatorTreeNode rn, HashSet hs) {
        if (rn == null) {
            return 0;
        }
        if (rn.getCovered()) {
            return 0;
        }
        if (hs.contains(rn)) {
            return 0;
        }
        int k = rn.getOriginalNodes().length;

        hs.add(rn);
        Set<GraphNode> ar = rn.getPrimArriving();
        Iterator<GraphNode> i = ar.iterator();
        while (i.hasNext()) {
            DominatorTreeNode dtn = (DominatorTreeNode) i.next();
            k += getWeigth(dtn, hs);
        }
        return k;
    }

    /** This method creates a new Graph, where each node is a 
     * strongly connected component of a given graph
     *
     * @param g - the graph to be reduced
     * @param sec - if secondary edges should be used 
     * @return the reduced graph
     **/

    static public ReduceGraph reduceSCC(Graph g, boolean sec) {
        Set v[] = g.computeSCC(sec);

        return reduce(v, g, sec);
    }

    /** Given an array of sets of {@link GraphNode}'s, creates 
     * a new {@link ReduceGraph} where each set correspond to 
     * a node.
     *
     * @param v - the array of sets. Each set corresponds to a 
     * node in the new graph.
     * @param g - the graph to be reduced
     * @param sec - if secondary edges should be considered
     * @return the reduced graph.
     */
    static public ReduceGraph reduce(Set hs[], Graph g, boolean sec) {
        DominatorTree rd = new DominatorTree();

        for (int i = 0; i < hs.length; i++) {
            DominatorTreeNode rn = new DominatorTreeNode(hs[i]);

            rd.add(rn);
        }
        // rd.setDefaultNumbering();
        for (int i = 0; i < rd.size(); i++) {
            DominatorTreeNode rn = (DominatorTreeNode) rd.elementAt(i);
            GraphNode inNodes[] = rn.getOriginalNodes();

            for (int j = 0; j < inNodes.length; j++) {
                Set<GraphNode> v = g.getLeavingNodes(inNodes[j], sec);
                Iterator<GraphNode> k = v.iterator();
                while (k.hasNext()) {
                    GraphNode gnex = (GraphNode) k.next();
                    ReduceNode rdNex = rd.getReduceNodeOf(gnex);
                    if (rdNex != rn) {
                        rd.addPrimaryEdge(rn, rdNex);
                    }
                }
            }
        }
        return rd;
    }
}

