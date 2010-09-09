/*
 * Created on 18/10/2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package br.jabuti.criteria;

public class Node extends Requirement {

	   String from;
		
	    public Node(String f) {
	        from = f;
	    }
		
	    public String toString() {
	        return new String(from);
	    }
		
	    public String getLabel() { 
	        return from; 
	    } 

	    public boolean equals(Object y) {
	        if (!(y instanceof Node)) {
	            return false;
	        }
	        Node x = (Node) y;

					
	        return from.equals(x.from);
	    }			
					
	    public int hashCode() {
	        return from.hashCode();
	    }

		/**
		 * Compare two nodes objects. 
		 */
	    public int compareTo(Object other) {
	    	if ( other instanceof Node ) {
	    		if ( this.equals( other ) )
	    			return 0;
	    		else
	    			return this.from.compareTo(((Node)other).from);
	    	}
	    	else
	    		return 0;
	    }	
}
