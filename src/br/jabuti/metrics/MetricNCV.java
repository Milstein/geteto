package br.jabuti.metrics;

import org.aspectj.apache.bcel.classfile.Field;
import org.aspectj.apache.bcel.classfile.JavaClass;

import br.jabuti.lookup.Program;
import br.jabuti.lookup.RClass;
import br.jabuti.lookup.RClassCode;

/**
 * N�mero de vari�veis de classe na classe. M�trica calculada
 * atrav�s da contagem do n�mero de vari�veis static na classe.
 */
public class MetricNCV extends AbstractMetric
{
	public MetricNCV()
	{
		super();
		name = "ncv";
		description = "Number of Class Variables in a class (NCV)";
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
			if (!fields[i].isStatic())
				continue;
			cont++;
		}
		return (double) cont;
	}

}
