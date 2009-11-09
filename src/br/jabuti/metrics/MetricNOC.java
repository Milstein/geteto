package br.jabuti.metrics;

import br.jabuti.lookup.java.bytecode.Program;
import br.jabuti.lookup.java.bytecode.RClass;

/**
 * Implements the NOC - number of children.
 */
public class MetricNOC extends AbstractMetric
{
	public MetricNOC()
	{
		super();
		name = "noc";
		description = "Number of Children (NOC)";

	}
	
	@Override
	public double getResult(Program prog, String className)
	{
		RClass rc = prog.get(className);
		if (rc == null) {
			return -1.0;
		}
		return (double) (rc.getSubClasses().length);
	}
}
