/*
This program is free software; you can redistribute it and/or
modify it under the terms of the GNU General Public License
as published by the Free Software Foundation; either version 2
of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, write to the Free Software
Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.

Copyright (C) 2008 Marco Aurélio Graciotto Silva <magsilva@gmail.com>
*/

package br.icmc.labes.testing.loader.resource;

import static org.junit.Assert.*;

import org.junit.Test;

import br.icmc.labes.testing.TestConfiguration;
import br.icmc.labes.testing.loader.resource.ResourceLoader;

public abstract class ResourceLoaderTest
{
	protected ResourceLoader rl;
	
	@Test
	public void testGetResourceValid()
	{
		@SuppressWarnings("unused")
		byte[] resource = rl.getResource(TestConfiguration.VALID_RESOURCE1);
	}

	@Test()
	public void testGetResourceInvalid()
	{
		byte[] resource = rl.getResource(TestConfiguration.INVALID_RESOURCE);
		assertNull(resource);
	}

	@Test()
	public void testGetResourceNotFound()
	{
		byte[] resource = rl.getResource(TestConfiguration.NOTFOUND_RESOURCE);
		assertNull(resource);
	}
}
