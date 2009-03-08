package br.jabuti.metrics;

import br.jabuti.lookup.Program;

/**
 * Falta de Coes�o entre os m�todos. O mesmo que LCOM por�m s�
 * os metodos est�ticos s�o considerados.
 */
public class MetricLCOM2 extends MetricLCOM
{
	public MetricLCOM2()
	{
		super();
		name = "lcom_2";
		description = "Lack of Cohesion in Methods (LCOM): LCOM_2 - only static methods" ;
	}
	
	public double getResult(Program prog, String className)
	{
		return lcom(prog, className, true);
	}
}
