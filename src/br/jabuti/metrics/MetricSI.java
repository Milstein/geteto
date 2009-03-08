package br.jabuti.metrics;

import org.aspectj.apache.bcel.classfile.JavaClass;
import org.aspectj.apache.bcel.classfile.Method;

import br.jabuti.lookup.Program;
import br.jabuti.lookup.RClass;
import br.jabuti.lookup.RClassCode;

/**
 * �ndice de Especializa��o. M�trica calculada atrav�s da
 * divis�o entre o resultado da multiplica��o de NMOS e DIT (m�trica de CK)
 * pelo n�mero total de m�todos.
 */
public class MetricSI extends AbstractMetric
{
	public MetricSI()
	{
		super();
		name = "si";
		description = "Specialization Index (SI)";
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
		Method[] methods = theClazz.getMethods();
		for (int i = 0; i < methods.length; i++) {
			if (methods[i].isAbstract())
				continue;
			cont++;
		}
		if (cont == 0)
			return -1;
		Metric nmos = Metrics.getMetric("nmos");
		Metric dit = Metrics.getMetric("dit");
		return (nmos.getResult(prog, className) * dit.getResult(prog, className)) / cont;
	}

	
}
