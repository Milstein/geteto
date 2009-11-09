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
 * <p>Complexidade Ciclom�tica de McCabe </p> <p>Calculado como o valor
 * m�dio da CC para os metodos Construtores s�o considerados. </p>
 */
public class MetricCCAVG extends AbstractMetric
{
	public MetricCCAVG()
	{
		super();
		name = "cc_avg";
		description = "Cyclomatic Complexity Metric (CC) - CC_AVG";
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
		int k = 0;
		for (int i = 0; i < methods.length; i++) {
			if (methods[i].isAbstract())
				continue;
			MethodGen mg = new MethodGen(methods[i], theClazz.getClassName(), cp);
			double d = CC(mg, cg);

			if (d < 0.0)
				return -1;
			theValue += d;
			k++;
		}
		return k > 0 ? theValue / k : 0.0;
	}
}
