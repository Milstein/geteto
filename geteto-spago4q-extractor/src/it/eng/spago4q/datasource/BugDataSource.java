/**

  Spago4Q - The Business Intelligence Free Platform

  Copyright (C) 2009 Engineering Ingegneria Informatica S.p.A.

  Spago4Q is free software; you can redistribute it and/or
  modify it under the terms of the GNU Lesser General Public
  License as published by the Free Software Foundation; either
  version 2.1 of the License, or (at your option) any later version.

  Spago4Q is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
  Lesser General Public License for more details.

  You should have received a copy of the GNU Lesser General Public
  License along with this library; if not, write to the Free Software
  Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA

**/
package it.eng.spago4q.datasource;

import java.util.Date;
import java.util.Random;

public class BugDataSource implements InterfaceDataSource{
	
	private static String DATE = "DATE";
	private static String PROJECT_NAME = "PROJECT_NAME";
	private static String BUGS_NUMBER = "BUGS_NUMBER";
	
	private String projectName = "PRJ-1";
	
	public String getFieldValue(String field){
		String toReturn = null;
		if(field.equals(DATE))
			toReturn = getDate();
		if(field.equals(PROJECT_NAME))
			toReturn = getProjectName();
		if(field.equals(BUGS_NUMBER))
			toReturn = getBugsNumber();
		return toReturn;
	}
	
	private String getDate(){
		Date now = new Date();
		return now.toString();
	}
	
	private String getBugsNumber(){
		Random rand = new Random();
		Integer bugsNumber = rand.nextInt(1001);
		return bugsNumber.toString();
	}
	
	private String getProjectName(){
		return projectName;
	}
}
