package br.icmc.labes.testing.loader.resource;

import org.junit.Before;

import br.icmc.labes.testing.TestConfiguration;

public class URLResourceTest extends ResourceLoaderTest
{
	@Before
	public void setUp() throws Exception
	{
		rl = new URLResource(TestConfiguration.VALID_URL);
	}
}
