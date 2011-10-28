/*
Copyright (C) 2008 Márcio Eduardo Delamaro and Auri Marcelo Rizzo Vincenzi.

This library is free software; you can redistribute it and/or
modify it under the terms of the GNU Lesser General Public
License as published by the Free Software Foundation; either
version 2.1 of the License, or (at your option) any later version.

This library is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public
License along with this library; if not, write to the Free Software
Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
*/

package br.jabuti.metrics.klass;

import br.jabuti.lookup.java.bytecode.Program;

/**
 * Lack of Cohesion in Methods (LCOM_2) that considers only static methods.
 */
public class MetricLCOM2 extends MetricLCOM
{
	public MetricLCOM2()
	{
		super();
		name = "lcom_2";
		description = "Lack of Cohesion in Methods (LCOM_2) - static methods" ;
	}

	@Override
	public double getResult(Program prog, String className)
	{
		return lcom(prog, className, true);
	}
}
