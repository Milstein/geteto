package br.jabuti.metrics;

import org.aspectj.apache.bcel.classfile.JavaClass;
import org.aspectj.apache.bcel.classfile.Method;
import org.aspectj.apache.bcel.generic.ConstantPoolGen;
import org.aspectj.apache.bcel.generic.MethodGen;

import br.jabuti.lookup.java.bytecode.Program;
import br.jabuti.lookup.java.bytecode.RClass;
import br.jabuti.lookup.java.bytecode.RClassCode;

/**
 * Average Number of Parameters per Method. Métrica calculada através da divis�o
 * entre a soma do n�mero de linhas de c�digo dos m�todos da classe pelo n�mero
 * de m�todos na classe (soma dos m�todos inst�ncia e classe).
 */
public class MetricANPM extends AbstractMetric
{
	public MetricANPM()
	{
		super();
		name = "anpm";
		description = "Average Number of Parameters per Method (ANPM)";
	}

	@Override
	public double getResult(Program prog, String className)
	{
		RClass rc = prog.get(className);
		if (! (rc instanceof RClassCode)) {
			return -1.0;
		}
		
		int countMethods = 0;
		int countParameters = 0;
		RClassCode rcc = (RClassCode) rc;
		JavaClass theClazz = rcc.getTheClass();
		ConstantPoolGen cp = new ConstantPoolGen(theClazz.getConstantPool());
		Method[] methods = theClazz.getMethods();
		for (Method method : methods) {
			if (method.isAbstract()) {
				continue;
			}
			MethodGen mg = new MethodGen(method, theClazz.getClassName(), cp);
			countMethods++;
			countParameters += mg.getArgumentNames().length;
		}
		if (countMethods == 0) {
			return -1.0;
		}
		return (double) countParameters / countMethods;
	}

}
