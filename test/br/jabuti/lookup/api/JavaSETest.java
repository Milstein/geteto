package br.jabuti.lookup.api;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.jabuti.TestConfiguration;

import org.junit.Before;
import org.junit.Test;

public class JavaSETest
{
	class ResourceFilter implements FilenameFilter
	{
		private Pattern pattern;

		public ResourceFilter(String id)
		{
			pattern = Pattern.compile("jvm" + id + "resources-.*\\.list");
		}

		public boolean accept(File dir, String name)
		{
			Matcher m = pattern.matcher(name);
			return m.matches();
		}
	}

	private File root;

	@Before
	public void setUp() throws Exception
	{
		root = new File(TestConfiguration.SAMPLES_ROOT + "../test/br/jabuti/lookup/api/");
	}

	@Test
	public void testHasJava1_0_x() throws IOException
	{
		JavaSE jp = new JavaSE("sun1_0_x");

		for (String filename : root.list(new ResourceFilter("1_0_x"))) {
			InputStream is = getClass().getResourceAsStream(filename);
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);

			String resource = null;
			while ((resource = br.readLine()) != null) {
				assertTrue(resource, jp.has(resource));
			}
		}
	}

	@Test
	public void testHasJava1_1_x() throws IOException
	{
		JavaSE jp = new JavaSE("sun1_1_x");
		for (String filename : root.list(new ResourceFilter("1_1_x"))) {
			InputStream is = getClass().getResourceAsStream(filename);
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);

			String resource = null;
			while ((resource = br.readLine()) != null) {
				assertTrue(resource, jp.has(resource));
			}
		}
	}

	@Test
	public void testHasJava1_2_x() throws IOException
	{
		JavaSE jp = new JavaSE("sun1_2_x");
		for (String filename : root.list(new ResourceFilter("1_2_x"))) {
			InputStream is = getClass().getResourceAsStream(filename);
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);

			String resource = null;
			while ((resource = br.readLine()) != null) {
				assertTrue(resource, jp.has(resource));
			}
		}
	}

	@Test
	public void testHasJava1_3_x() throws IOException
	{
		JavaSE jp = new JavaSE("sun1_3_x");
		for (String filename : root.list(new ResourceFilter("1_3_x"))) {
			InputStream is = getClass().getResourceAsStream(filename);
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);

			String resource = null;
			while ((resource = br.readLine()) != null) {
				assertTrue(resource, jp.has(resource));
			}
		}
	}

	@Test
	public void testHasJava1_4_x() throws IOException
	{
		JavaSE jp = new JavaSE("sun1_4_x");
		for (String filename : root.list(new ResourceFilter("1_4_x"))) {
			InputStream is = getClass().getResourceAsStream(filename);
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);

			String resource = null;
			while ((resource = br.readLine()) != null) {
				assertTrue(resource, jp.has(resource));
			}
		}
	}

	@Test
	public void testHasJava1_5_x() throws IOException
	{
		JavaSE jp = new JavaSE("sun1_5_x");
		for (String filename : root.list(new ResourceFilter("1_5_x"))) {
			InputStream is = getClass().getResourceAsStream(filename);
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);

			String resource = null;
			while ((resource = br.readLine()) != null) {
				assertTrue(resource, jp.has(resource));
			}
		}
	}

	@Test
	public void testHasJava6_x() throws IOException
	{
		JavaSE jp = new JavaSE("sun6_x");
		for (String filename : root.list(new ResourceFilter("1_6_x"))) {
			InputStream is = getClass().getResourceAsStream(filename);
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);

			String resource = null;
			while ((resource = br.readLine()) != null) {
				assertTrue(resource, jp.has(resource));
			}
		}
	}

}
