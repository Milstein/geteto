package br.jabuti.metrics;

import org.aspectj.apache.bcel.classfile.JavaClass;
import org.aspectj.apache.bcel.classfile.Method;
import org.aspectj.apache.bcel.generic.ConstantPoolGen;
import org.aspectj.apache.bcel.generic.MethodGen;

import br.jabuti.lookup.java.bytecode.Program;
import br.jabuti.lookup.java.bytecode.RClass;
import br.jabuti.lookup.java.bytecode.RClassCode;

/**
 * <p>Tamanho m�dio do m�todo </p> <p>M�trica calculada atrav�s da divis�o
 * entre a soma do n�mero de linhas de c�digo dos m�todos da classe pelo
 * n�mero de m�todos na classe (soma dos m�todos inst�ncia e classe).
 * Construtores inclusive </p>
 */
public class MetricAMZLOCM extends AbstractMetric
{
	public MetricAMZLOCM()
	{
		super();
		name = "amz_locm";
		description = "Average Method Size (AMZ_LOCM)";
	}
	
	@Override
	public double getResult(Program prog, String className)
	{
		double theValue = 0.0;
		RClass rc = prog.get(className);
		if (!(rc instanceof RClassCode))
			return -1.0;
		int cont = 0;
		RClassCode rcc = (RClassCode) rc;
		JavaClass theClazz = rcc.getTheClass();
		ConstantPoolGen cp = new ConstantPoolGen(theClazz.getConstantPool());
		Method[] methods = theClazz.getMethods();
		for (int i = 0; i < methods.length; i++) {
			if (methods[i].isAbstract())
				continue;
			MethodGen mg = new MethodGen(methods[i], theClazz.getClassName(), cp);
			double d = LOCM(mg);
			theValue += d;
			cont++;
		}
		if (cont == 0)
			return -1.0;
		return theValue / cont;
	}
}
