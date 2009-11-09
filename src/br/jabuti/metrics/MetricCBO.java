package br.jabuti.metrics;

import java.util.HashSet;

import org.aspectj.apache.bcel.classfile.Constant;
import org.aspectj.apache.bcel.classfile.ConstantCP;
import org.aspectj.apache.bcel.classfile.ConstantPool;
import org.aspectj.apache.bcel.classfile.JavaClass;

import br.jabuti.lookup.java.bytecode.Program;
import br.jabuti.lookup.java.bytecode.RClass;
import br.jabuti.lookup.java.bytecode.RClassCode;

/**
 * Coupling between objects.
 * 
 * There is coupling between two classes when one class uses
 * methods or instance variables of other class.
 * 
 * M�trica calculada atrav�s da contagem do n�mero de classes �s
 * quais uma classe est� acoplada de alguma forma, o que exclui o
 * acoplamento baseado em heran�a. Assim, o valor CBO de uma classe A � o
 * n�mero de classes das quais a classe A utiliza algum m�todo e/ou vari�vel
 * de inst�ncia. </p>
 */
public class MetricCBO extends AbstractMetric
{
	public MetricCBO()
	{
		super();
		name = "cbo";
		description = "Coupling Between Object (CBO)";
	}

	@Override
	public double getResult(Program prog, String className)
	{
		RClass rc = prog.get(className);
		if (!(rc instanceof RClassCode))
			return -1.0;
		RClassCode rcc = (RClassCode) rc;
		JavaClass theClazz = rcc.getTheClass();
		ConstantPool cp = theClazz.getConstantPool();
		Constant[] cts = cp.getConstantPool();
		HashSet<String> hs = new HashSet<String>();
		for (Constant ct : cts) {
			if (ct instanceof ConstantCP) {
				ConstantCP cc = (ConstantCP) ct;
				if (! className.equals(cc.getClass(cp))) {
					hs.add(cc.getClass(cp));
				}
			}
		}
		return hs.size();
	}

	
}


