package br.jabuti.metrics;

import org.junit.Before;

import br.jabuti.TestConfiguration;

public class MetricsFactorial13Test extends MetricsFactorialTest
{
	@Before
	public void setUp() throws Exception
	{
		setMetrics(TestConfiguration.SAMPLES_FACTORIAL_1_3_PACKAGE);
	}
}
