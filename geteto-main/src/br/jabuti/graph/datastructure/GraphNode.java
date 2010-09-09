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


package br.jabuti.graph.datastructure;


import java.util.*;


/**
 This is the abstract class that represents a node of a Program
 Graph. A program graph is represented by a subclass of 
 {@link ListGraph}. <BR>

 Objects of this class stores only basic information about a 
 node as adjacent nodes (see the variables below). Other information
 can be added by the extending classes.<BR>

 Most methods here are used by {@link ListGraph} methods. The user is
 advised to use those methods instead of the ones in this class.
 To add an edge, for example, use {@link ListGraph#addPrimEdge} instead
 of {@link GraphNode#addPrimNext}.<BR>

 The class implements <code>Comparator</code> so the subclasses are
 expected to implement <code>compare</code> and <code>equals</code>
 for the graph nodes.

 @version: 0.00001
 @author: Marcio Delamaro

 */

abstract public class GraphNode  implements Comparator, java.io.Serializable 
{
		
    /** Set of nodes to which there exist a primary edge */		
    protected Set<GraphNode> next = new HashSet<GraphNode>(); 

    /** Set of nodes to which there exist a secondary edge */		
    protected Set<GraphNode> secNext = new HashSet<GraphNode>();

    /** Set of nodes from which there exist a primary edge */		
    protected Set<GraphNode> arriving = new HashSet<GraphNode>();

    /** Set of nodes from which there exist a secondary edge */		
    protected Set<GraphNode> secArriving = new HashSet<GraphNode>();

    /** Each node has a number, this is it. It is initialized 
     with -1 */
    protected int number = -1;

    /** This is used to associate an object to the node */
    protected Hashtable objectSet = new Hashtable(0);

    /** Each node can also have a label. It is not initialized 
     (is <code>null</code> by default) */
    protected String label = null;

    /** Auxiliary, use in {@link ListGraph#findDFT} */
    private boolean mark;
	
    /** Just creates an (empty) node */
    public GraphNode() {
        super();
    }
	
    /** Creates from an existing node */
    public GraphNode(GraphNode x) {
        super();
        addPrimNext(x.next);
        addSecNext(x.secNext);
      
        Iterator<GraphNode> i = x.arriving.iterator();
        while (i.hasNext()) {
            GraphNode gn = i.next();
            gn.addPrimNext(this);
        }
        
        i = x.secArriving.iterator();
        while (i.hasNext()) {
            GraphNode gn = i.next();
            gn.addSecNext(this);
        }
		
        objectSet = x.objectSet;
        label = x.label;
    }
	
    /**
     Each subclass knows which are the important information to
     be presented by this method. 
     */
    public String toString() {
        String str = "\n\nNODE: " + getLabel();

        str += "\nChildren: ";
        Set<GraphNode> nodes = getPrimNext();
        Iterator<GraphNode> i = nodes.iterator();
        while (i.hasNext()) {
        	GraphNode nx = i.next();
            str += " " + nx.getLabel();
        }
        
        str += "\nSec Children: ";
        nodes = getSecNext();
        i = nodes.iterator();
        while (i.hasNext()) {
            GraphNode nx = i.next();
            str += " " + nx.getLabel();
        }
        return str;
    }
	
    /**
     Adds an edge from this node to another one, passed as argument.
     If the edge is already present, then it is not included again.

     @param x The destination node

     */
    public void addPrimNext(GraphNode x) {
        if (next.contains(x)) {
            return;
        }
        next.add(x);
        x.arriving.add(this);
    }
	
    /**
     Adds several edges from this node to some other ones, passed as argument.
     Calls {@link GraphNode#addPrimNext(GraphNode)} for each element
     of the argumnt vector.

     @param x The set of destination nodes

     */
    public void addPrimNext(Set<GraphNode> x) {
    	next.addAll(x);
    }

    /**
     Adds a secondary edge from this node to another one, passed as argument.
     If the edge is already present, then it is not included again.

     @param x The destination node

     */
    public void addSecNext(GraphNode x) {
        if (secNext.contains(x)) {
            return;
        }
        secNext.add(x);
        x.secArriving.add(this);
    }
	
    /**
     Adds several secondary edges from this node to some other ones, passed as argument.
     Calls {@link GraphNode#addSecNext(GraphNode)} for each element
     of the argumnt vector.

     @param x The set of destination nodes

     */
    public void addSecNext(Set<GraphNode> x) {
    	secNext.addAll(x);
    }

    /**
     Removes an edge from this node to another one, passed as argument.
     If the edge is already present, then it is not included again.

     @param x The destination node

     */
    public void deletePrimNext(GraphNode x) {
        next.remove(x);
        x.arriving.remove(this);
    }
	
    /**
     Removes a secondary edge from this node to another one, passed as argument.
     If the edge is already present, then it is not included again.

     @param x The destination node

     */
    public void deleteSecNext(GraphNode x) {
        secNext.remove(x);
        x.secArriving.remove(this);
    }

    /**
     Returns the set o nodes for which there is a primary edge from this
     node.

     @return The set o nodes for which there is a primary edge from this
     node.
     */
    public Set<GraphNode> getPrimNext() {
        return next;
    }
	
    /**
     Returns the set o nodes for which there is a secondary edge from this
     node.

     @return The set o nodes for which there is a secondary edge from this
     node.
     */
    public Set<GraphNode> getSecNext() {
        return secNext;
    }

    /**
     Returns the set o nodes from which there is a primary edge to this
     node.

     @return The set o nodes from which there is a primary edge to this
     node.
     */
    public Set<GraphNode> getPrimArriving() {
        return arriving;
    }

    /**
     Returns the set o nodes from which there is a secondary edge to this
     node.

     @return The set o nodes from which there is a secondary edge to this
     node.
     */
    public Set<GraphNode> getSecArriving() {
        return secArriving;
    }

    /** Sets a label for this node

     @param x The label to be asigned.
     */
    public void setLabel(String x) {
        label = x;
    }
		
    /**
     Returns the label assigned to this node

     @return The label of this node
     */
    public String getLabel() {
        if (label != null) {
            return label;
        }
        return getNumber() + "";
    }	

    /** Sets the number ot this node

     @param x The number to be asigned.
     */
    public void setNumber(int x) {
        number = x;
    }
	
    /**
     Returns the number assigned to this node

     @return The number of this node
     */
    public int getNumber() {
        return number;
    }	
	
    /**
     Auxiliary method used to "mark" a node. Used, for example
     by the {@link ListGraph#findDFT} method 

     @param x The value to be assigned 
     */
    public void setMark(boolean x) {
        mark = x;
    }
	
    /**
     Auxiliary method used to get a "mark" of a node. Used, for example
     by the {@link ListGraph#findDFT} method 

     @param x The value to be assigned 
     */
    public boolean getMark() {
        return mark;
    }	
	
    /** This method is used to associate a label to a given object.
     * It is supposed to be used by subclasses tah can associate, for
     * example, a set to the graph node without the need to create a
     * new variable (only accessed from the class) to store the
     * object. It is like a <code>setProperty</code> method
     * 
     *
     */
    public void setUserData(String l, Object o) {
        objectSet.put(l, o);
    }

    /** This method is used to retrieve an object that has been
     * associate to a given label by {@link GraphNode#setUserData}.
     *
     */
    public Object getUserData(String l) {
        return objectSet.get(l);
    }

    /** This method is used to remove an object that has been
     * associate to a given label by {@link GraphNode#setUserData}.
     *
     */
    public void removeUserData(String l) {
        objectSet.remove(l);
    }
	
    /** This method is used to set an element in a {@link Collection}
     * the been
     * associate to a given label by {@link GraphNode#setUserData}.
     *
     */
    public void setUserElement(String l, Object o) {
        ((Collection) getUserData(l)).add(o);
    }

    /**
     * This method returns the list of all "user data" registered
     * for this node 
     *
     * @return A list of strings tha represent the labels of user data
     */
    public String[] getUserDataList() {
        String[] s = new String[objectSet.size()];
        Enumeration en = objectSet.keys();

        for (int i = 0; i < s.length; i++) {
            s[i] = (String) en.nextElement();
        }
        return s;
    }
}

