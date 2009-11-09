package br.jabuti.metrics;

import org.aspectj.apache.bcel.classfile.JavaClass;
import org.aspectj.apache.bcel.classfile.Method;
import org.aspectj.apache.bcel.generic.ConstantPoolGen;
import org.aspectj.apache.bcel.generic.MethodGen;

import br.jabuti.lookup.java.bytecode.Program;
import br.jabuti.lookup.java.bytecode.RClass;
import br.jabuti.lookup.java.bytecode.RClassCode;

/**
 * <p>N�mero m�ximo de par�metros em um m�todo da classe</p>
 */
public class MetricMNPM extends AbstractMetric
{
	public MetricMNPM()
	{
		super();
		name = "mnpm";
		description = "Maximum Number of Parameters per Method (MNPM)";
	}
	
	@Override
	public double getResult(Program prog, String className)
	{
		RClass rc = prog.get(className);
		if (!(rc instanceof RClassCode))
			return -1.0;
		int max = 0;
		RClassCode rcc = (RClassCode) rc;
		JavaClass theClazz = rcc.getTheClass();
		ConstantPoolGen cp = new ConstantPoolGen(theClazz.getConstantPool());
		Method[] methods = theClazz.getMethods();
		for (int i = 0; i < methods.length; i++) {
			if (methods[i].isAbstract())
				continue;
			MethodGen mg = new MethodGen(methods[i], theClazz.getClassName(), cp);
			String dummy[] = mg.getArgumentNames();
			max = dummy.length > max ? dummy.length : max;
		}
		return (double) max;
	}
}
