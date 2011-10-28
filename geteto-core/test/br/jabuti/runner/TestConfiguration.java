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

package br.icmc.labes.testing;

public final class TestConfiguration
{
	public static String INVALID_DIR_INEXISTENT = "/abaafkljsadjklsda";
	
	public static String INVALID_DIR_NOTDIR = "/Projects-ICMC/TestingClassloader/samples/br/icmc/labes/testing/Test.txt";
	
	public static String VALID_DIR = "/home/magsilva/Projects-ICMC/TestingClassloader/samples";
	
	public static String VALID_URL = "http://localhost/~magsilva/samples/";

	public static String INVALID_URL = "dsftrweqhttp://localhost/~magsilva/samplvafafdes/";

	public static String NOT_FOUND_URL = "http://localhost/~magsilva/samplvafafdes/";

	public static String INVALID_RESOURCE = "br.icmc.labes.testing.t";

	public static String NOTFOUND_RESOURCE = "agsdafasdjslaj.txt";

	public static String VALID_RESOURCE1 = "br.icmc.labes.testing.Test.txt";

	public static String VALID_RESOURCE2 = "br.icmc.labes.testing.ABC";
	
	public static String VALID_RESOURCE_MAIN_CLASS = "br.icmc.labes.testing.Dummy";
	
	public static String[] VALID_RESOURCE_CLASSPATH = { "." };
	
	public static String INVALID_CLASSPATH_ATTRIBUTE = "FDKLSAJSADLKJ";
	
	public static String VALID_RESOURCE_FILENAME1 = "br/icmc/labes/testing/Test.txt";

	public static String VALID_RESOURCE_FILENAME2 = "br/icmc/labes/testing/ABC";
	
	public static String VALID_JAR_PACKAGE = "/home/magsilva/Projects-ICMC/TestingClassloader/samples/samples.jar";
}
