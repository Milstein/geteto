package br.jabuti.metrics;

import org.aspectj.apache.bcel.classfile.JavaClass;
import org.aspectj.apache.bcel.classfile.Method;
import org.aspectj.apache.bcel.generic.ConstantPoolGen;
import org.aspectj.apache.bcel.generic.MethodGen;

import br.jabuti.lookup.Program;
import br.jabuti.lookup.RClass;
import br.jabuti.lookup.RClassCode;

/**
 * <p>Tamanho m�dio do m�todo </p> <p>M�trica calculada atrav�s da divis�o
 * entre a soma do numero de instrucoes dos m�todos da classe pelo n�mero de
 * m�todos na classe (soma dos m�todos inst�ncia e classe). Construtores
 * inclusive </p>
 */
public class MetricAMZSIZE extends AbstractMetric
{
	public MetricAMZSIZE()
	{
		super();
		name = "amz_size";
		description = "Average Method Size (AMZ_SIZE) - Number of Bytecode Instructions";
	}

	@Override
	public double getResult(Program prog, String className)
	{
		double theValue = 0.0;
		RClass rc = prog.get(className);
		if (!(rc instanceof RClassCode)) {
			return -1.0;
		}
		int cont = 0;
		RClassCode rcc = (RClassCode) rc;
		JavaClass theClazz = rcc.getTheClass();
		ConstantPoolGen cp = new ConstantPoolGen(theClazz.getConstantPool());
		Method[] methods = theClazz.getMethods();
		for (Method method : methods) {
			if (method.isAbstract()) {
				continue;
			}
			MethodGen mg = new MethodGen(method, theClazz.getClassName(), cp);
			double d = SIZE(mg);
			theValue += d;
			cont++;
		}
		if (cont == 0) {
			return -1.0;
		}
		return theValue / cont;
	}
}
