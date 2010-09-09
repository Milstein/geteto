package br.jabuti.criteria;

/** 
 * A single edge used by the all-edges criterion
 *
 *
 * @version: 1.0
 * @author: Marcio Delamaro  
 * @author: Auri Vincenzi  
 *
 **/
public class Edge extends Requirement {
	
    String from;
    String to;
	
    public Edge(String f, String t) {
        from = f;
        to = t;
    }
	
    public String toString() {
        return "(" + from + "," + to + ")";
    }
	
    public String getFrom() { 
        return from; 
    } 

    public String getTo() { 
        return to; 
    } 

    public boolean equals(Object y) {
        if (!(y instanceof Edge)) {
            return false;
        }
        Edge x = (Edge) y;

        if (!from.equals(x.from)) {
            return false;
        }
				
        return to.equals(x.to);
    }			
				
    public int hashCode() {
        return from.hashCode() + to.hashCode();
    }

	/**
	 * Compare two edges objects. 
	 */
    public int compareTo(Object other) {
    	if ( other instanceof Edge ) {
    		if ( this.equals( other ) )
    			return 0;
    		else
    			if (!from.equals(((Edge)other).from))
    				return this.from.compareTo(((Edge)other).from);
    			else
    				return this.to.compareTo(((Edge)other).to);
    	}
    	else
    		return 0;
    }
}
