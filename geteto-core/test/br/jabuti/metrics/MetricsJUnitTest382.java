package br.jabuti.metrics;

import org.junit.Before;
import org.junit.Test;

import br.jabuti.TestConfiguration;
import br.jabuti.lookup.Program;
import br.jabuti.metrics.klass.Metrics;

import static org.junit.Assert.*;

public class MetricsJUnitTest382
{
	private Metrics metrics;
	
	@Before
	public void setUp() throws Exception
	{
		String filename = TestConfiguration.SAMPLES_JUNIT_PACKAGE_3_8_2;
		Program program = Program.createFromPackage(filename);
		metrics = new Metrics(program);
	}
	
	@Test
	public void testJunit()
	{
		String JUNIT_FRAMEWORK_ASSERT_3_8_2_CLASSNAME = "junit.framework.Assert";
		double JUNIT_FRAMEWORK_ASSERT_3_8_2_ANPM = 2.230769230769231;
		double JUNIT_FRAMEWORK_ASSERT_3_8_2_AMZ_LOCM = 2.641025641025641;
		double JUNIT_FRAMEWORK_ASSERT_3_8_2_AMZ_SIZE = 9.743589743589743;
		double JUNIT_FRAMEWORK_ASSERT_3_8_2_CBO = 14.0;
		double JUNIT_FRAMEWORK_ASSERT_3_8_2_CC_AVG = 1.564102564102564;
		double JUNIT_FRAMEWORK_ASSERT_3_8_2_CC_MAX = 5.0;
		double JUNIT_FRAMEWORK_ASSERT_3_8_2_DIT = 1.0;
		double JUNIT_FRAMEWORK_ASSERT_3_8_2_LCOM = 0.0;
		double JUNIT_FRAMEWORK_ASSERT_3_8_2_LCOM_2 = 703.0;
		double JUNIT_FRAMEWORK_ASSERT_3_8_2_LCOM_3 = 703.0;
		double JUNIT_FRAMEWORK_ASSERT_3_8_2_MNPM = 4.0;
		double JUNIT_FRAMEWORK_ASSERT_3_8_2_NCM = 38.0;
		double JUNIT_FRAMEWORK_ASSERT_3_8_2_NCM_2 = 37.0;
		double JUNIT_FRAMEWORK_ASSERT_3_8_2_NII = 0.0;
		double JUNIT_FRAMEWORK_ASSERT_3_8_2_NIV = 0.0;
		double JUNIT_FRAMEWORK_ASSERT_3_8_2_NMAS = 0.0;
		double JUNIT_FRAMEWORK_ASSERT_3_8_2_NMIS = 0.0;
		double JUNIT_FRAMEWORK_ASSERT_3_8_2_NMOS = 0.0;
		double JUNIT_FRAMEWORK_ASSERT_3_8_2_NOC = 2.0;
		double JUNIT_FRAMEWORK_ASSERT_3_8_2_NPIM = 0;
		double JUNIT_FRAMEWORK_ASSERT_3_8_2_RFC = 101.0;
		double JUNIT_FRAMEWORK_ASSERT_3_8_2_SI = 0.0;
		double JUNIT_FRAMEWORK_ASSERT_3_8_2_WMC_1 = 39.0;
		double JUNIT_FRAMEWORK_ASSERT_3_8_2_WMC_CC = 61.0;
		double JUNIT_FRAMEWORK_ASSERT_3_8_2_WMC_LOCM = 103.0;
		double JUNIT_FRAMEWORK_ASSERT_3_8_2_WMC_SIZE = 380.0;
		  
		assertEquals(JUNIT_FRAMEWORK_ASSERT_3_8_2_ANPM, metrics.getClassMetric(JUNIT_FRAMEWORK_ASSERT_3_8_2_CLASSNAME, "anpm"), 0);
		assertEquals(JUNIT_FRAMEWORK_ASSERT_3_8_2_AMZ_LOCM, metrics.getClassMetric(JUNIT_FRAMEWORK_ASSERT_3_8_2_CLASSNAME, "amz_locm"), 0);
		assertEquals(JUNIT_FRAMEWORK_ASSERT_3_8_2_AMZ_SIZE, metrics.getClassMetric(JUNIT_FRAMEWORK_ASSERT_3_8_2_CLASSNAME, "amz_size"), 0);
		assertEquals(JUNIT_FRAMEWORK_ASSERT_3_8_2_CBO, metrics.getClassMetric(JUNIT_FRAMEWORK_ASSERT_3_8_2_CLASSNAME, "cbo"), 0);
		assertEquals(JUNIT_FRAMEWORK_ASSERT_3_8_2_CC_AVG, metrics.getClassMetric(JUNIT_FRAMEWORK_ASSERT_3_8_2_CLASSNAME, "cc_avg"), 0);
		assertEquals(JUNIT_FRAMEWORK_ASSERT_3_8_2_CC_MAX, metrics.getClassMetric(JUNIT_FRAMEWORK_ASSERT_3_8_2_CLASSNAME, "cc_max"), 0);
		assertEquals(JUNIT_FRAMEWORK_ASSERT_3_8_2_DIT, metrics.getClassMetric(JUNIT_FRAMEWORK_ASSERT_3_8_2_CLASSNAME, "dit"), 0);
		assertEquals(JUNIT_FRAMEWORK_ASSERT_3_8_2_LCOM, metrics.getClassMetric(JUNIT_FRAMEWORK_ASSERT_3_8_2_CLASSNAME, "lcom"), 0);
		assertEquals(JUNIT_FRAMEWORK_ASSERT_3_8_2_LCOM_2, metrics.getClassMetric(JUNIT_FRAMEWORK_ASSERT_3_8_2_CLASSNAME, "lcom_2"), 0);
		assertEquals(JUNIT_FRAMEWORK_ASSERT_3_8_2_LCOM_3, metrics.getClassMetric(JUNIT_FRAMEWORK_ASSERT_3_8_2_CLASSNAME, "lcom_3"), 0);
		assertEquals(JUNIT_FRAMEWORK_ASSERT_3_8_2_MNPM, metrics.getClassMetric(JUNIT_FRAMEWORK_ASSERT_3_8_2_CLASSNAME, "mnpm"), 0);
		assertEquals(JUNIT_FRAMEWORK_ASSERT_3_8_2_NCM, metrics.getClassMetric(JUNIT_FRAMEWORK_ASSERT_3_8_2_CLASSNAME, "ncm"), 0);
		assertEquals(JUNIT_FRAMEWORK_ASSERT_3_8_2_NCM_2, metrics.getClassMetric(JUNIT_FRAMEWORK_ASSERT_3_8_2_CLASSNAME, "ncm_2"), 0);
		assertEquals(JUNIT_FRAMEWORK_ASSERT_3_8_2_NII, metrics.getClassMetric(JUNIT_FRAMEWORK_ASSERT_3_8_2_CLASSNAME, "nii"), 0);
		assertEquals(JUNIT_FRAMEWORK_ASSERT_3_8_2_NIV, metrics.getClassMetric(JUNIT_FRAMEWORK_ASSERT_3_8_2_CLASSNAME, "niv"), 0);
		assertEquals(JUNIT_FRAMEWORK_ASSERT_3_8_2_NMAS, metrics.getClassMetric(JUNIT_FRAMEWORK_ASSERT_3_8_2_CLASSNAME, "nmas"), 0);
		assertEquals(JUNIT_FRAMEWORK_ASSERT_3_8_2_NMIS, metrics.getClassMetric(JUNIT_FRAMEWORK_ASSERT_3_8_2_CLASSNAME, "nmis"), 0);
		assertEquals(JUNIT_FRAMEWORK_ASSERT_3_8_2_NMOS, metrics.getClassMetric(JUNIT_FRAMEWORK_ASSERT_3_8_2_CLASSNAME, "nmos"), 0);
		assertEquals(JUNIT_FRAMEWORK_ASSERT_3_8_2_NOC, metrics.getClassMetric(JUNIT_FRAMEWORK_ASSERT_3_8_2_CLASSNAME, "noc"), 0);
		assertEquals(JUNIT_FRAMEWORK_ASSERT_3_8_2_NPIM, metrics.getClassMetric(JUNIT_FRAMEWORK_ASSERT_3_8_2_CLASSNAME, "npim"), 0);
		assertEquals(JUNIT_FRAMEWORK_ASSERT_3_8_2_RFC, metrics.getClassMetric(JUNIT_FRAMEWORK_ASSERT_3_8_2_CLASSNAME, "rfc"), 0);
		assertEquals(JUNIT_FRAMEWORK_ASSERT_3_8_2_SI, metrics.getClassMetric(JUNIT_FRAMEWORK_ASSERT_3_8_2_CLASSNAME, "si"), 0);
		assertEquals(JUNIT_FRAMEWORK_ASSERT_3_8_2_WMC_1, metrics.getClassMetric(JUNIT_FRAMEWORK_ASSERT_3_8_2_CLASSNAME, "wmc_1"), 0);
		assertEquals(JUNIT_FRAMEWORK_ASSERT_3_8_2_WMC_CC, metrics.getClassMetric(JUNIT_FRAMEWORK_ASSERT_3_8_2_CLASSNAME, "wmc_cc"), 0);
		assertEquals(JUNIT_FRAMEWORK_ASSERT_3_8_2_WMC_LOCM, metrics.getClassMetric(JUNIT_FRAMEWORK_ASSERT_3_8_2_CLASSNAME, "wmc_locm"), 0);
		assertEquals(JUNIT_FRAMEWORK_ASSERT_3_8_2_WMC_SIZE, metrics.getClassMetric(JUNIT_FRAMEWORK_ASSERT_3_8_2_CLASSNAME, "wmc_size"), 0);
	}
}
