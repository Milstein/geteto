package br.jabuti.metrics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.aspectj.apache.bcel.classfile.JavaClass;
import org.aspectj.apache.bcel.classfile.Method;
import org.aspectj.apache.bcel.generic.ClassGen;
import org.aspectj.apache.bcel.generic.ConstantPoolGen;
import org.aspectj.apache.bcel.generic.MethodGen;

import br.jabuti.graph.datastructure.dug.CFG;
import br.jabuti.lookup.java.bytecode.Program;
import br.jabuti.lookup.java.bytecode.RClass;
import br.jabuti.lookup.java.bytecode.RClassCode;
import br.jabuti.util.Debug;

/**
 * Falta de Coes�o entre os m�todos. M�trica calculada atrav�s da
 * contagem do n�mero de pares de m�todos na classe que n�o compartilham
 * vari�veis de inst�ncia menos o n�mero de pares de m�todos que compartilham
 * vari�veis de inst�ncia. Quando o resultado � negativo, a m�trica recebe o
 * valor zero. Os m�todos est�ticos n�o s�o considerados na contagem, uma vez
 * que s� as vari�veis de inst�ncia s�o tomadas. Construtores s�o considerados.
 */
public class MetricLCOM extends AbstractMetric
{
	protected double lcom(Program prog, String className, boolean isStatic)
	{
		RClass rc = prog.get(className);
		if (!(rc instanceof RClassCode)) {
			return -1.0;
		}

		ArrayList dmethod = new ArrayList();
		RClassCode rcc = (RClassCode) rc;
		JavaClass theClazz = rcc.getTheClass();
		ConstantPoolGen cp = new ConstantPoolGen(theClazz.getConstantPool());
		ClassGen cg = new ClassGen(theClazz);
		Method[] methods = theClazz.getMethods();
		for (int i = 0; i < methods.length; i++) {
			if (methods[i].isAbstract()) {
				continue;
			}
			MethodGen mg = new MethodGen(methods[i], theClazz.getClassName(), cp);

			// static methods are not considered
			if (mg.isStatic() != isStatic) {
				continue;
			}
			CFG g = null;
			try {
				g = getCFG(mg, cg);
			} catch (Exception e) {
				return -1.0;
			}
			Collection defUses = findDefUse(g);
			// keeps only the non static accesses
			Iterator it = null;
			for (it = defUses.iterator(); it.hasNext();) {
				String s = (String) it.next();
				if (!s.startsWith("L@0."))
					it.remove();
			}
			dmethod.add(defUses);
		}
		int doShare = 0, notShare = 0;
		// now compare the methods
		for (int i = 0; i < dmethod.size() - 1; i++) {
			Collection v = (Collection) dmethod.get(i);
			r1: for (int j = i + 1; j < dmethod.size(); j++) {
				Collection v1 = (Collection) dmethod.get(j);
				for (Iterator it1 = v.iterator(); it1.hasNext();) {
					String s = (String) it1.next();
					if (v1.contains(s)) {
						Debug.D("Methods " + i + " and " + j + " share " + s);
						doShare++;
						continue r1;
					}
				}
				Debug.D("Methods " + i + " and " + j + " do not share ");
				notShare++;
			}
		}
		double lcom = (double) notShare - doShare;
		return lcom >= 0 ? lcom : 0.0;
	}

	public MetricLCOM()
	{
		super();
		name = "lcom";
		description = "Lack of Cohesion in Methods (LCOM)";
	}

	@Override
	public double getResult(Program prog, String className)
	{
		return lcom(prog, className, false);
	}

}
