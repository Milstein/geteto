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

import org.junit.Test;

import br.icmc.labes.testing.TestConfiguration;
import br.icmc.labes.testing.loader.resource.FilesystemResource;

@SuppressWarnings("unused")
public class FilesystemResourceInitTest
{
	@Test(expected=IllegalArgumentException.class)
	public void testInvalidDirInexistent()
	{
		FilesystemResource fr = new FilesystemResource(TestConfiguration.INVALID_DIR_INEXISTENT);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testInvalidDirNotDir()
	{
		FilesystemResource fr = new FilesystemResource(TestConfiguration.INVALID_DIR_NOTDIR);
	}
	
	@Test
	public void testValidDir()
	{
		FilesystemResource fr = new FilesystemResource(TestConfiguration.VALID_DIR);
	}

}
