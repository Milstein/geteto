package br.jabuti.metrics;

import br.jabuti.lookup.java.bytecode.Program;

/**
 * Falta de Coes�o entre os m�todos (lcom + lcom_2).
 */
public class MetricLCOM3 extends MetricLCOM
{
	public MetricLCOM3()
	{
		super();
		name = "lcom_3";
		description = "Lack of Cohesion in Methods (LCOM): LCOM_3 - static or instance methods";

	}
	
	public double getResult(Program prog, String className)
	{
		return lcom(prog, className, false) + lcom(prog, className, true);
	}
}