package br.jabuti.metrics;

import org.aspectj.apache.bcel.classfile.JavaClass;
import org.aspectj.apache.bcel.classfile.Method;

import br.jabuti.lookup.Program;
import br.jabuti.lookup.RClass;
import br.jabuti.lookup.RClassCode;

/**
 * N�mero de m�todos sobrescritos na subclasse. M�trica
 * calculada atrav�s da contagem do n�mero de m�todos definidos na subclasse
 * com a mesma assinatura de um m�todo na sua superclasse. Construtores e
 * inicializadores est�ticos N�O s�o considerados.
 */
public class MetricNMOS extends AbstractMetric
{
	public MetricNMOS()
	{
		super();
		name = "nmos";
		description = "Number of Methods Overridden by a Subclass (NMOS)";
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
		rc = prog.get(rcc.getSuperClass());
		if (!(rc instanceof RClassCode))
			return 0.0;
		Method[] methods = theClazz.getMethods();
		for (int i = 0; i < methods.length; i++) {
			if (methods[i].getName().endsWith("init>"))
				continue;
			if (methods[i].isAbstract())
				continue;
			if (findMethInClass(prog, methods[i], rcc.getSuperClass(), true))
				cont++;
		}
		return (double) cont;
	}

	
}
