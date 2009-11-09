package br.jabuti.metrics;

import org.aspectj.apache.bcel.classfile.JavaClass;
import org.aspectj.apache.bcel.classfile.Method;

import br.jabuti.lookup.java.bytecode.Program;
import br.jabuti.lookup.java.bytecode.RClass;
import br.jabuti.lookup.java.bytecode.RClassCode;

/**
 * N�mero de m�todos de inst�ncia p�blicos na classe. M�trica
 * calculada atrav�s da contagem do n�mero de m�todos de inst�ncia p�blicas
 * na classe, incluindo os construtores.
 */
public class MetricNPIM extends AbstractMetric
{
	public MetricNPIM()
	{
		super();
		name = "npim";
		description = "Number of Public Instance Methods in a class (NPIM)";
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
			if (methods[i].isStatic())
				continue;
			if (!methods[i].isPublic())
				continue;
			cont++;
		}
		return (double) cont;
	}

	
}
