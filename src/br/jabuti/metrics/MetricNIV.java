package br.jabuti.metrics;

import org.aspectj.apache.bcel.classfile.Field;
import org.aspectj.apache.bcel.classfile.JavaClass;

import br.jabuti.lookup.java.bytecode.Program;
import br.jabuti.lookup.java.bytecode.RClass;
import br.jabuti.lookup.java.bytecode.RClassCode;

/**
 * N�mero de vari�veis de inst�ncia na classe. M�trica calculada
 * atrav�s da contagem do n�mero de vari�veis de inst�ncia na classe, o que
 * inclui as vari�veis public, private e protected dispon�veis para as
 * inst�ncias.
 */
public class MetricNIV extends AbstractMetric
{
	public MetricNIV()
	{
		super();
		name = "niv";
		description = "Number of Instance Variables in a class (NIV)";
	}
	
	@Override
	public double getResult(Program prog, String className)
	{
		RClass rc = prog.get(className);
		if (!(rc instanceof RClassCode))
			return -1.0;
		int cont = 0;
		RClassCode rcc = (RClassCode) rc;
		JavaClass theClazz = rcc.getTheClass();
		Field[] fields = theClazz.getFields();
		for (int i = 0; i < fields.length; i++) {
			if (fields[i].isStatic())
				continue;
			cont++;
		}
		return (double) cont;
	}



	
}
