package br.icmc.labes.testing.loader.resource;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;

import br.icmc.labes.testing.TestConfiguration;

@SuppressWarnings("unused")
public class URLResourceInitTest
{
	public void testURLResourceValidURL()
	{
		URLResource ur = new URLResource(TestConfiguration.VALID_URL);
	}

	public void testURLResourceValidString() throws MalformedURLException
	{
		URLResource ur = new URLResource(new URL(TestConfiguration.VALID_URL));
	}

	
	@Test(expected=IllegalArgumentException.class)
	public void testURLResourceInvalidString()
	{
		URLResource ur = new URLResource(TestConfiguration.INVALID_URL);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testURLResourceNotFoundString() throws MalformedURLException
	{
		URLResource ur = new URLResource(TestConfiguration.NOT_FOUND_URL);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testURLResourceNotFoundURL() throws MalformedURLException
	{
		URLResource ur = new URLResource(new URL(TestConfiguration.NOT_FOUND_URL));
	}

	
}
