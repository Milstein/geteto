package br.jabuti.metrics;

import org.aspectj.apache.bcel.classfile.JavaClass;
import org.aspectj.apache.bcel.classfile.Method;
import org.aspectj.apache.bcel.generic.ConstantPoolGen;
import org.aspectj.apache.bcel.generic.MethodGen;

import br.jabuti.lookup.Program;
import br.jabuti.lookup.RClass;
import br.jabuti.lookup.RClassCode;

/**
 * N�mero de m�todos ponderados por classe. M�trica calculada
 * atrav�s da soma da complexidade de cada m�todo. Usa size (n�mero de
 * instru��es) como m�trica de complexidade por metodo.
 */
public class MetricWMCSIZE extends AbstractMetric
{
	public MetricWMCSIZE()
	{
		super();
		name = "wmc_size";
		description = "Weighted Methods per Class (WMC): WMC_SIZE - metric SIZE (number of instructions)";
	}
	
	@Override
	public double getResult(Program prog, String className)
	{
		double theValue = 0.0;
		RClass rc = prog.get(className);
		if (!(rc instanceof RClassCode))
			return -1.0;
		RClassCode rcc = (RClassCode) rc;
		JavaClass theClazz = rcc.getTheClass();
		ConstantPoolGen cp = new ConstantPoolGen(theClazz.getConstantPool());
		Method[] methods = theClazz.getMethods();
		for (int i = 0; i < methods.length; i++) {
			if (methods[i].isAbstract())
				continue;
			MethodGen mg = new MethodGen(methods[i], theClazz.getClassName(), cp);
			double d = SIZE(mg);
			theValue += d;
		}
		return theValue;
	}

	
}
