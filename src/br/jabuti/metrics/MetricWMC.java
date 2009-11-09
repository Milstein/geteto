package br.jabuti.metrics;

import org.aspectj.apache.bcel.classfile.JavaClass;
import org.aspectj.apache.bcel.classfile.Method;

import br.jabuti.lookup.java.bytecode.Program;
import br.jabuti.lookup.java.bytecode.RClass;
import br.jabuti.lookup.java.bytecode.RClassCode;

/**
 * N�mero de m�todos ponderados por classe. M�trica calculada
 * atrav�s da soma da complexidade de cada m�todo. Usa 1 como m�trica de
 * complexidade por metodo.
 */
public class MetricWMC extends AbstractMetric
{
	public MetricWMC()
	{
		super();
		name = "wmc_1";
		description = "Weighted Methods per Class (WMC): WMC_1 - metric 1";
	}

	@Override
	public double getResult(Program prog, String className)
	{
		RClass rc = prog.get(className);
		if (!(rc instanceof RClassCode))
			return -1.0;
		RClassCode rcc = (RClassCode) rc;
		JavaClass theClazz = rcc.getTheClass();
		Method[] methods = theClazz.getMethods();
		int theValue = 0;
		for (int i = 0; i < methods.length; i++) {
			if (methods[i].isAbstract())
				continue;
			theValue++;
		}
		return (double) theValue;
	}


}
