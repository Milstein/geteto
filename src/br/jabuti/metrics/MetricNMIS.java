package br.jabuti.metrics;

import org.aspectj.apache.bcel.classfile.JavaClass;
import org.aspectj.apache.bcel.classfile.Method;

import br.jabuti.lookup.Program;
import br.jabuti.lookup.RClass;
import br.jabuti.lookup.RClassCode;

/**
 * N�mero de m�todos herdados pela subclasse. M�trica calculada
 * atrav�s da contagem do n�mero de m�todos herdados pela subclasse de suas
 * superclasses. Construtores e inicializadores est�ticos N�O s�o
 * considerados.
 */
public class MetricNMIS extends AbstractMetric
{
	public MetricNMIS()
	{
		super();
		name = "nmis";
		description = "Number of Methods Inherited by a Subclass (NMIS)";
	}

	@Override
	public double getResult(Program prog, String className)
	{
		RClass rc = prog.get(className);
		if (!(rc instanceof RClassCode))
			return -1.0;
		int cont = 0;
		RClassCode rcc = (RClassCode) rc;
		rc = prog.get(rcc.getSuperClass());
		if (!(rc instanceof RClassCode))
			return 0.0;
		JavaClass supertheClazz = rcc.getTheClass();
		Method[] methods = supertheClazz.getMethods();
		for (int i = 0; i < methods.length; i++) {
			if (methods[i].getName().endsWith("init>"))
				continue;
			if (methods[i].isAbstract())
				continue;
			if (! findMethInClass(prog, methods[i], className, false)) {
				cont++;
			}
		}
		return (double) cont + getResult(prog, rcc.getSuperClass());
	}

	
}
