package br.jabuti.metrics;

import org.aspectj.apache.bcel.classfile.JavaClass;

import br.jabuti.lookup.Program;
import br.jabuti.lookup.RClass;
import br.jabuti.lookup.RClassCode;

/**
 * <p>n�mero de interfaces implementadas pela classe </p>
 */
public class MetricNII extends AbstractMetric
{
	public MetricNII()
	{
		super();
		name = "nii";
		description = "Number of Interfaces Implemented (NII)";
	}
	
	@Override
	public double getResult(Program prog, String className)
	{
		RClass rc = prog.get(className);
		if (!(rc instanceof RClassCode))
			return -1.0;
		RClassCode rcc = (RClassCode) rc;
		JavaClass theClazz = rcc.getTheClass();
		String[] dummy = theClazz.getInterfaceNames();
		if (dummy == null)
			return 0.0;
		return (double) dummy.length;
	}


	
}
