package br.jabuti.metrics;

import org.aspectj.apache.bcel.classfile.JavaClass;
import org.aspectj.apache.bcel.classfile.Method;
import org.aspectj.apache.bcel.generic.ClassGen;
import org.aspectj.apache.bcel.generic.ConstantPoolGen;
import org.aspectj.apache.bcel.generic.MethodGen;

import br.jabuti.lookup.java.bytecode.Program;
import br.jabuti.lookup.java.bytecode.RClass;
import br.jabuti.lookup.java.bytecode.RClassCode;

/**
 * N�mero de m�todos ponderados por classe. M�trica calculada
 * atrav�s da soma da complexidade de cada m�todo. Usa CC (McCabe) como
 * m�trica de complexidade por metodo Construtores s�o considerados.
 */
public class MetricWMCCC extends AbstractMetric
{
	public MetricWMCCC()
	{
		super();
		name = "wmc_cc";
		description = "Weighted Methods per Class (WMC): WMC_CC - metric CC";
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
		ClassGen cg = new ClassGen(theClazz);
		Method[] methods = theClazz.getMethods();
		for (int i = 0; i < methods.length; i++) {
			if (methods[i].isAbstract())
				continue;
			MethodGen mg = new MethodGen(methods[i], theClazz.getClassName(), cp);
			double d = CC(mg, cg);

			if (d < 0.0)
				return -1;
			theValue += d;
		}
		return theValue;
	}

	
}
