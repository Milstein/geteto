package br.jabuti.metrics;

import java.util.jar.JarFile;
import java.util.zip.ZipFile;

import org.junit.Test;

import br.jabuti.TestConfiguration;
import br.jabuti.lookup.Program;
import br.jabuti.metrics.klass.Metrics;


import static org.junit.Assert.*;

public abstract class MetricsFactorialTest
{
	protected Metrics metrics;

	private static final String BR_JABUTI_SAMPLES_FACTORIAL_CLASSNAME = "br.jabuti.samples.Factorial";
	private static final double BR_JABUTI_SAMPLES_FACTORIAL_ANPM = 0.5;
	private static final double BR_JABUTI_SAMPLES_FACTORIAL_AMZ_LOCM = 7.0;
	private static final double BR_JABUTI_SAMPLES_FACTORIAL_AMZ_SIZE = 30.0;
	private static final double BR_JABUTI_SAMPLES_FACTORIAL_CBO = 4.0;
	private static final double BR_JABUTI_SAMPLES_FACTORIAL_CC_AVG = 2.5;
	private static final double BR_JABUTI_SAMPLES_FACTORIAL_CC_MAX = 3.0;
	private static final double BR_JABUTI_SAMPLES_FACTORIAL_DIT = 1.0;
	private static final double BR_JABUTI_SAMPLES_FACTORIAL_LCOM = 1.0;
	private static final double BR_JABUTI_SAMPLES_FACTORIAL_LCOM_2 = 0.0;
	private static final double BR_JABUTI_SAMPLES_FACTORIAL_LCOM_3 = 1.0;
	private static final double BR_JABUTI_SAMPLES_FACTORIAL_MNPM = 1.0;
	private static final double BR_JABUTI_SAMPLES_FACTORIAL_NCM = 0.0;
	private static final double BR_JABUTI_SAMPLES_FACTORIAL_NCM_2 = 0.0;
	private static final double BR_JABUTI_SAMPLES_FACTORIAL_NCV = 0.0;
	private static final double BR_JABUTI_SAMPLES_FACTORIAL_NII = 0.0;
	private static final double BR_JABUTI_SAMPLES_FACTORIAL_NIV = 2.0;
	private static final double BR_JABUTI_SAMPLES_FACTORIAL_NMAS = 0.0;
	private static final double BR_JABUTI_SAMPLES_FACTORIAL_NMIS = 0.0;
	private static final double BR_JABUTI_SAMPLES_FACTORIAL_NMOS = 0.0;
	private static final double BR_JABUTI_SAMPLES_FACTORIAL_NOC = 0.0;
	private static final double BR_JABUTI_SAMPLES_FACTORIAL_NPIM = 0.0;
	private static final double BR_JABUTI_SAMPLES_FACTORIAL_RFC = 10.0; // 4.0
	private static final double BR_JABUTI_SAMPLES_FACTORIAL_SI = 0.0;
	private static final double BR_JABUTI_SAMPLES_FACTORIAL_WMC_1 = 2.0;
	private static final double BR_JABUTI_SAMPLES_FACTORIAL_WMC_CC = 5.0;
	private static final double BR_JABUTI_SAMPLES_FACTORIAL_WMC_LOCM = 14.0;
	private static final double BR_JABUTI_SAMPLES_FACTORIAL_WMC_SIZE = 60.0;
	
	protected void setMetrics(String packageFilename) throws Exception
	{
		String filename = TestConfiguration.SAMPLES_FACTORIAL_1_1_PACKAGE;
		Program program = Program.createFromPackage(filename);
		metrics = new Metrics(program);
	}
	@Test
	public void testFactorialANPM()
	{
		assertEquals(BR_JABUTI_SAMPLES_FACTORIAL_ANPM, metrics.getClassMetric(BR_JABUTI_SAMPLES_FACTORIAL_CLASSNAME, "anpm"), 0);
	}
	
	@Test
	public void testFactorialAMZ_LOCM()
	{
		assertEquals(BR_JABUTI_SAMPLES_FACTORIAL_AMZ_LOCM, metrics.getClassMetric(BR_JABUTI_SAMPLES_FACTORIAL_CLASSNAME, "amz_locm"), 0);
	}
	
	@Test
	public void testFactorialAMZ_SIZE()
	{
		assertEquals(BR_JABUTI_SAMPLES_FACTORIAL_AMZ_SIZE, metrics.getClassMetric(BR_JABUTI_SAMPLES_FACTORIAL_CLASSNAME, "amz_size"), 0);
	}
	
	@Test
	public void testFactorialCBO()
	{
		assertEquals(BR_JABUTI_SAMPLES_FACTORIAL_CBO, metrics.getClassMetric(BR_JABUTI_SAMPLES_FACTORIAL_CLASSNAME, "cbo"), 0);
	}
	
	@Test
	public void testFactorialCC_AVG()
	{
		assertEquals(BR_JABUTI_SAMPLES_FACTORIAL_CC_AVG, metrics.getClassMetric(BR_JABUTI_SAMPLES_FACTORIAL_CLASSNAME, "cc_avg"), 0);
	}
	
	@Test
	public void testFactorialCC_MAX()
	{
		assertEquals(BR_JABUTI_SAMPLES_FACTORIAL_CC_MAX, metrics.getClassMetric(BR_JABUTI_SAMPLES_FACTORIAL_CLASSNAME, "cc_max"), 0);
	}
	
	@Test
	public void testFactorialDIT()
	{
		assertEquals(BR_JABUTI_SAMPLES_FACTORIAL_DIT, metrics.getClassMetric(BR_JABUTI_SAMPLES_FACTORIAL_CLASSNAME, "dit"), 0);
	}
	
	@Test
	public void testFactorialLCOM()
	{
		assertEquals(BR_JABUTI_SAMPLES_FACTORIAL_LCOM, metrics.getClassMetric(BR_JABUTI_SAMPLES_FACTORIAL_CLASSNAME, "lcom"), 0);
	}
	
	@Test
	public void testFactorialLCOM_2()
	{
		assertEquals(BR_JABUTI_SAMPLES_FACTORIAL_LCOM_2, metrics.getClassMetric(BR_JABUTI_SAMPLES_FACTORIAL_CLASSNAME, "lcom_2"), 0);
	}
	
	@Test
	public void testFactorialLCOM_3()
	{
		assertEquals(BR_JABUTI_SAMPLES_FACTORIAL_LCOM_3, metrics.getClassMetric(BR_JABUTI_SAMPLES_FACTORIAL_CLASSNAME, "lcom_3"), 0);
	}
	
	@Test
	public void testFactorialMNPM()
	{
		assertEquals(BR_JABUTI_SAMPLES_FACTORIAL_MNPM, metrics.getClassMetric(BR_JABUTI_SAMPLES_FACTORIAL_CLASSNAME, "mnpm"), 0);
	}
	
	@Test
	public void testFactorialNCM()
	{
		assertEquals(BR_JABUTI_SAMPLES_FACTORIAL_NCM, metrics.getClassMetric(BR_JABUTI_SAMPLES_FACTORIAL_CLASSNAME, "ncm"), 0);
	}
	
	@Test
	public void testFactorialNCM_2()
	{
		assertEquals(BR_JABUTI_SAMPLES_FACTORIAL_NCM_2, metrics.getClassMetric(BR_JABUTI_SAMPLES_FACTORIAL_CLASSNAME, "ncm_2"), 0);
	}
	
	@Test
	public void testFactorialNII()
	{
		assertEquals(BR_JABUTI_SAMPLES_FACTORIAL_NII, metrics.getClassMetric(BR_JABUTI_SAMPLES_FACTORIAL_CLASSNAME, "nii"), 0);
	}
			
	@Test
	public void testFactorialNIV()
	{
		assertEquals(BR_JABUTI_SAMPLES_FACTORIAL_NIV, metrics.getClassMetric(BR_JABUTI_SAMPLES_FACTORIAL_CLASSNAME, "niv"), 0);
	}

	@Test
	public void testFactorialNMAS()
	{
		assertEquals(BR_JABUTI_SAMPLES_FACTORIAL_NMAS, metrics.getClassMetric(BR_JABUTI_SAMPLES_FACTORIAL_CLASSNAME, "nmas"), 0);
	}
	
	@Test
	public void testFactorialNMIS()
	{
		assertEquals(BR_JABUTI_SAMPLES_FACTORIAL_NMIS, metrics.getClassMetric(BR_JABUTI_SAMPLES_FACTORIAL_CLASSNAME, "nmis"), 0);
	}
	
	@Test
	public void testFactorialNMOS()
	{
		assertEquals(BR_JABUTI_SAMPLES_FACTORIAL_NMOS, metrics.getClassMetric(BR_JABUTI_SAMPLES_FACTORIAL_CLASSNAME, "nmos"), 0);
	}
	
	@Test
	public void testFactorialNOC()
	{
		assertEquals(BR_JABUTI_SAMPLES_FACTORIAL_NOC, metrics.getClassMetric(BR_JABUTI_SAMPLES_FACTORIAL_CLASSNAME, "noc"), 0);
	}
	
	@Test
	public void testFactorialNPIM()
	{
		assertEquals(BR_JABUTI_SAMPLES_FACTORIAL_NPIM, metrics.getClassMetric(BR_JABUTI_SAMPLES_FACTORIAL_CLASSNAME, "npim"), 0);
	}
	
	@Test
	public void testFactorialRFC()
	{
		assertEquals(BR_JABUTI_SAMPLES_FACTORIAL_RFC, metrics.getClassMetric(BR_JABUTI_SAMPLES_FACTORIAL_CLASSNAME, "rfc"), 0);
	}
	
	@Test
	public void testFactorialSI()
	{
		assertEquals(BR_JABUTI_SAMPLES_FACTORIAL_SI, metrics.getClassMetric(BR_JABUTI_SAMPLES_FACTORIAL_CLASSNAME, "si"), 0);
	}
	
	@Test
	public void testFactorialWMC_1()
	{
		assertEquals(BR_JABUTI_SAMPLES_FACTORIAL_WMC_1, metrics.getClassMetric(BR_JABUTI_SAMPLES_FACTORIAL_CLASSNAME, "wmc_1"), 0);
	}
	
	@Test
	public void testFactorialWMC_CC()
	{
		assertEquals(BR_JABUTI_SAMPLES_FACTORIAL_WMC_CC, metrics.getClassMetric(BR_JABUTI_SAMPLES_FACTORIAL_CLASSNAME, "wmc_cc"), 0);
	}
	
	@Test
	public void testFactorialWMC_LOCM()
	{
		assertEquals(BR_JABUTI_SAMPLES_FACTORIAL_WMC_LOCM, metrics.getClassMetric(BR_JABUTI_SAMPLES_FACTORIAL_CLASSNAME, "wmc_locm"), 0);
	}
	
	@Test
	public void testFactorialWMC_SIZE()
	{
		assertEquals(BR_JABUTI_SAMPLES_FACTORIAL_WMC_SIZE, metrics.getClassMetric(BR_JABUTI_SAMPLES_FACTORIAL_CLASSNAME, "wmc_size"), 0);
	}

}
