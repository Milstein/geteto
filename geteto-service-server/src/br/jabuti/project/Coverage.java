package br.jabuti.project;



/**
 *
 * This class stores general information about coverage.
 * Basically the number of requirements e the ones that were
 * covered.
 *
 * @version: 1.0
 * @author: Auri Vincenzi
 * @author: Marcio Delamaro
 * @author: Tatiana Sugeta
 *
 */
public class Coverage {
	
    /** stores the information about coverage */
    private int numberOfRequirements;

    private int numberOfCovered;
	
    public Coverage() {
        numberOfCovered = numberOfRequirements = 0;
    }

    public Coverage(int cov, int total) {
        numberOfCovered = cov;
        numberOfRequirements = total;
    }

    public Coverage( Coverage c ) {
        this( c.numberOfCovered, c.numberOfRequirements );
    }
	
    /***********************************************************/
    
    /* Get and Set Methods implementation                      */
    
    /***********************************************************/
	
    public int getNumberOfRequirements() {
        return numberOfRequirements;
    }

    public void setNumberOfRequirements(int n) {
        numberOfRequirements = n;
    }

    public int getNumberOfCovered() {
        return numberOfCovered;
    }

    public void setNumberOfCovered(int n) {
        numberOfCovered = n;
    }

    public float getPercentage() {
        try {
            return (getNumberOfCovered() * 100) / getNumberOfRequirements();
        } catch (ArithmeticException ae) {
            return 0.0f;
        }
    }
	
    public String toString() {
        return new String( getNumberOfCovered() + " of " + getNumberOfRequirements() );
    }
    
    public boolean equals(Object o)
    {
    	if (! (o instanceof Coverage))
    		return false;
    	Coverage cv = (Coverage) o;
    	return cv.getNumberOfCovered() == getNumberOfCovered() &&
		       cv.getNumberOfRequirements() == getNumberOfRequirements();
    }
    
}
