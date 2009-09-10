package br.jabuti.metrics;

import java.util.jar.JarFile;
import java.util.zip.ZipFile;

import org.junit.Before;
import org.junit.Test;

import br.jabuti.TestConfiguration;
import br.jabuti.lookup.Program;

import static org.junit.Assert.*;

public class MetricsJUnitTest44
{
	private Metrics metrics;
	
	@Before
	public void setUp() throws Exception
	{
		String filename = TestConfiguration.SAMPLES_JUNIT_PACKAGE_4_4;
 		Program program = Program.createFromPackage(filename);
		metrics = new Metrics(program);
	}
	
	@Test
	public void testJunit()
	{
		String JUNIT_FRAMEWORK_ASSERT_4_4_CLASSNAME = "junit.framework.Assert";
		double JUNIT_FRAMEWORK_ASSERT_4_4_ANPM = 2.230769230769231;
		double JUNIT_FRAMEWORK_ASSERT_4_4_AMZ_LOCM = 2.641025641025641;
		double JUNIT_FRAMEWORK_ASSERT_4_4_AMZ_SIZE = 9.743589743589743;
		double JUNIT_FRAMEWORK_ASSERT_4_4_CBO = 14.0;
		double JUNIT_FRAMEWORK_ASSERT_4_4_CC_AVG = 1.564102564102564;
		double JUNIT_FRAMEWORK_ASSERT_4_4_CC_MAX = 5.0;
		double JUNIT_FRAMEWORK_ASSERT_4_4_DIT = 1.0;
		double JUNIT_FRAMEWORK_ASSERT_4_4_LCOM = 0.0;
		double JUNIT_FRAMEWORK_ASSERT_4_4_LCOM_2 = 703.0;
		double JUNIT_FRAMEWORK_ASSERT_4_4_LCOM_3 = 703.0;
		double JUNIT_FRAMEWORK_ASSERT_4_4_MNPM = 4.0;
		double JUNIT_FRAMEWORK_ASSERT_4_4_NCM = 38.0;
		double JUNIT_FRAMEWORK_ASSERT_4_4_NCM_2 = 37.0;
		double JUNIT_FRAMEWORK_ASSERT_4_4_NII = 0.0;
		double JUNIT_FRAMEWORK_ASSERT_4_4_NIV = 0.0;
		double JUNIT_FRAMEWORK_ASSERT_4_4_NMAS = 0.0;
		double JUNIT_FRAMEWORK_ASSERT_4_4_NMIS = 0.0;
		double JUNIT_FRAMEWORK_ASSERT_4_4_NMOS = 0.0;
		double JUNIT_FRAMEWORK_ASSERT_4_4_NOC = 2.0;
		double JUNIT_FRAMEWORK_ASSERT_4_4_NPIM = 0;
		double JUNIT_FRAMEWORK_ASSERT_4_4_RFC = 101.0;
		double JUNIT_FRAMEWORK_ASSERT_4_4_SI = 0.0;
		double JUNIT_FRAMEWORK_ASSERT_4_4_WMC_1 = 39.0;
		double JUNIT_FRAMEWORK_ASSERT_4_4_WMC_CC = 61.0;
		double JUNIT_FRAMEWORK_ASSERT_4_4_WMC_LOCM = 103.0;
		double JUNIT_FRAMEWORK_ASSERT_4_4_WMC_SIZE = 380.0;
		  
		assertEquals(JUNIT_FRAMEWORK_ASSERT_4_4_ANPM, metrics.getClassMetric(JUNIT_FRAMEWORK_ASSERT_4_4_CLASSNAME, "anpm"), 0);
		assertEquals(JUNIT_FRAMEWORK_ASSERT_4_4_AMZ_LOCM, metrics.getClassMetric(JUNIT_FRAMEWORK_ASSERT_4_4_CLASSNAME, "amz_locm"), 0);
		assertEquals(JUNIT_FRAMEWORK_ASSERT_4_4_AMZ_SIZE, metrics.getClassMetric(JUNIT_FRAMEWORK_ASSERT_4_4_CLASSNAME, "amz_size"), 0);
		assertEquals(JUNIT_FRAMEWORK_ASSERT_4_4_CBO, metrics.getClassMetric(JUNIT_FRAMEWORK_ASSERT_4_4_CLASSNAME, "cbo"), 0);
		assertEquals(JUNIT_FRAMEWORK_ASSERT_4_4_CC_AVG, metrics.getClassMetric(JUNIT_FRAMEWORK_ASSERT_4_4_CLASSNAME, "cc_avg"), 0);
		assertEquals(JUNIT_FRAMEWORK_ASSERT_4_4_CC_MAX, metrics.getClassMetric(JUNIT_FRAMEWORK_ASSERT_4_4_CLASSNAME, "cc_max"), 0);
		assertEquals(JUNIT_FRAMEWORK_ASSERT_4_4_DIT, metrics.getClassMetric(JUNIT_FRAMEWORK_ASSERT_4_4_CLASSNAME, "dit"), 0);
		assertEquals(JUNIT_FRAMEWORK_ASSERT_4_4_LCOM, metrics.getClassMetric(JUNIT_FRAMEWORK_ASSERT_4_4_CLASSNAME, "lcom"), 0);
		assertEquals(JUNIT_FRAMEWORK_ASSERT_4_4_LCOM_2, metrics.getClassMetric(JUNIT_FRAMEWORK_ASSERT_4_4_CLASSNAME, "lcom_2"), 0);
		assertEquals(JUNIT_FRAMEWORK_ASSERT_4_4_LCOM_3, metrics.getClassMetric(JUNIT_FRAMEWORK_ASSERT_4_4_CLASSNAME, "lcom_3"), 0);
		assertEquals(JUNIT_FRAMEWORK_ASSERT_4_4_MNPM, metrics.getClassMetric(JUNIT_FRAMEWORK_ASSERT_4_4_CLASSNAME, "mnpm"), 0);
		assertEquals(JUNIT_FRAMEWORK_ASSERT_4_4_NCM, metrics.getClassMetric(JUNIT_FRAMEWORK_ASSERT_4_4_CLASSNAME, "ncm"), 0);
		assertEquals(JUNIT_FRAMEWORK_ASSERT_4_4_NCM_2, metrics.getClassMetric(JUNIT_FRAMEWORK_ASSERT_4_4_CLASSNAME, "ncm_2"), 0);
		assertEquals(JUNIT_FRAMEWORK_ASSERT_4_4_NII, metrics.getClassMetric(JUNIT_FRAMEWORK_ASSERT_4_4_CLASSNAME, "nii"), 0);
		assertEquals(JUNIT_FRAMEWORK_ASSERT_4_4_NIV, metrics.getClassMetric(JUNIT_FRAMEWORK_ASSERT_4_4_CLASSNAME, "niv"), 0);
		assertEquals(JUNIT_FRAMEWORK_ASSERT_4_4_NMAS, metrics.getClassMetric(JUNIT_FRAMEWORK_ASSERT_4_4_CLASSNAME, "nmas"), 0);
		assertEquals(JUNIT_FRAMEWORK_ASSERT_4_4_NMIS, metrics.getClassMetric(JUNIT_FRAMEWORK_ASSERT_4_4_CLASSNAME, "nmis"), 0);
		assertEquals(JUNIT_FRAMEWORK_ASSERT_4_4_NMOS, metrics.getClassMetric(JUNIT_FRAMEWORK_ASSERT_4_4_CLASSNAME, "nmos"), 0);
		assertEquals(JUNIT_FRAMEWORK_ASSERT_4_4_NOC, metrics.getClassMetric(JUNIT_FRAMEWORK_ASSERT_4_4_CLASSNAME, "noc"), 0);
		assertEquals(JUNIT_FRAMEWORK_ASSERT_4_4_NPIM, metrics.getClassMetric(JUNIT_FRAMEWORK_ASSERT_4_4_CLASSNAME, "npim"), 0);
		assertEquals(JUNIT_FRAMEWORK_ASSERT_4_4_RFC, metrics.getClassMetric(JUNIT_FRAMEWORK_ASSERT_4_4_CLASSNAME, "rfc"), 0);
		assertEquals(JUNIT_FRAMEWORK_ASSERT_4_4_SI, metrics.getClassMetric(JUNIT_FRAMEWORK_ASSERT_4_4_CLASSNAME, "si"), 0);
		assertEquals(JUNIT_FRAMEWORK_ASSERT_4_4_WMC_1, metrics.getClassMetric(JUNIT_FRAMEWORK_ASSERT_4_4_CLASSNAME, "wmc_1"), 0);
		assertEquals(JUNIT_FRAMEWORK_ASSERT_4_4_WMC_CC, metrics.getClassMetric(JUNIT_FRAMEWORK_ASSERT_4_4_CLASSNAME, "wmc_cc"), 0);
		assertEquals(JUNIT_FRAMEWORK_ASSERT_4_4_WMC_LOCM, metrics.getClassMetric(JUNIT_FRAMEWORK_ASSERT_4_4_CLASSNAME, "wmc_locm"), 0);
		assertEquals(JUNIT_FRAMEWORK_ASSERT_4_4_WMC_SIZE, metrics.getClassMetric(JUNIT_FRAMEWORK_ASSERT_4_4_CLASSNAME, "wmc_size"), 0);
	}
}
