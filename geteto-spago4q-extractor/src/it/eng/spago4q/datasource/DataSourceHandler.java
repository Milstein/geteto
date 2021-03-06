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

public class DataSourceHandler {

	private InterfaceDataSource[] dataSources = { new BugDataSource() };

	public String getDataSourceValue(Integer dataSourceId, String field) {
		String toReturn = null;
		if (dataSourceId != null && dataSourceId < dataSources.length
				&& dataSourceId >= 0) {
			toReturn = dataSources[dataSourceId].getFieldValue(field);
		}
		return toReturn;
	}
}