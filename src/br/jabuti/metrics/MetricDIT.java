package br.jabuti.metrics;

import br.jabuti.lookup.Program;
import br.jabuti.lookup.RClass;
import br.jabuti.lookup.RClassCode;

/**
 * Implements the DIT - depth in tree. Considers only the part of the program
 * inside the inrterest limits. It means that classes "avoided" in the program
 * structure are not counted. The longest path is considered in the computation.
 * It means, if the class implements an interface, the path through the
 * interface is also considered.
 */
public class MetricDIT extends AbstractMetric
{
	public MetricDIT()
	{
		super();
		name = "dit";
		description = "Depth of Inheritance Tree (DIT)";
	}
	
	@Override
	public double getResult(Program prog, String className)
	{
		RClass tc = prog.get(className);
		if (tc == null)
			return -1.0;
		if (!(tc instanceof RClassCode))
			return 0.0;
		RClassCode theClass = (RClassCode) tc;
		String inter[] = theClass.getInterfaces();
		double theValue = 0;
		for (int i = 0; inter != null && i < inter.length; i++) {
			int k = prog.levelOf(inter[i]);
			theValue = k > theValue ? k : theValue;
		}
		double d = (double) prog.levelOf(className);
		return d > theValue ? d : theValue;
	}
}
