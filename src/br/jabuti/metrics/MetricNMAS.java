package br.jabuti.metrics;

import org.aspectj.apache.bcel.classfile.JavaClass;
import org.aspectj.apache.bcel.classfile.Method;

import br.jabuti.lookup.java.bytecode.Program;
import br.jabuti.lookup.java.bytecode.RClass;
import br.jabuti.lookup.java.bytecode.RClassCode;

/**
 * N�mero de m�todos adicionados pela subclasse. M�trica
 * calculada atrav�s da contagem do n�mero de novos m�todos adicionados pela
 * classe. Construtores e inicializadores est�ticos N�O s�o considerados.
 */
public class MetricNMAS extends AbstractMetric
{
	public MetricNMAS()
	{
		super();
		name = "nmas";
		description = "Number of Methods Added by a Subclass (NMAS)";
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
			if (! findMethInClass(prog, methods[i], rcc.getSuperClass(), true))
				cont++;
		}
		return (double) cont;
	}

	
}
