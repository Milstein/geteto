package br.jabuti.metrics;

import org.aspectj.apache.bcel.classfile.JavaClass;
import org.aspectj.apache.bcel.classfile.Method;
import org.aspectj.apache.bcel.generic.ClassGen;
import org.aspectj.apache.bcel.generic.ConstantPoolGen;
import org.aspectj.apache.bcel.generic.MethodGen;

import br.jabuti.lookup.Program;
import br.jabuti.lookup.RClass;
import br.jabuti.lookup.RClassCode;

/**
 * Complexidade Ciclom�tica de McCabe; Calculado como o valor
 * maximo da CC para os metodos Construtores s�o considerados.
 */
public class MetricCCMAX extends AbstractMetric
{
	public MetricCCMAX()
	{
		super();
		name = "cc_max";
		description = "Cyclomatic Complexity Metric (CC) - CC_MAX";
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
			theValue = (d > theValue) ? d : theValue;
		}
		return theValue;
	}
}
