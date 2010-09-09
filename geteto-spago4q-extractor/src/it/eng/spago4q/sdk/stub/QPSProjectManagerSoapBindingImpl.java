/**
 * QPSProjectManagerSoapBindingImpl.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package it.eng.spago4q.sdk.stub;

import it.eng.spago4q.bo.ProjectDetail;
import it.eng.spago4q.dao.ProjectDetailDAOHibImpl;

public class QPSProjectManagerSoapBindingImpl implements
		it.eng.spago4q.sdk.stub.QPSProjectManager {
	public boolean addProject(java.lang.String in0, java.lang.String in1)
			throws java.rmi.RemoteException {
		ProjectDetailDAOHibImpl dao = new ProjectDetailDAOHibImpl();
		boolean toReturn;
		try {
			ProjectDetail toModify = dao.loadObjectByCode(in0);
			if (toModify == null) {
				ProjectDetail projectDetail = new ProjectDetail();
				projectDetail.setCode(in0);
				projectDetail.setDetail(in1);
				Integer id = dao.insertObject(projectDetail);
				toReturn = (id != null && id > 0);
			} else {
				toModify.setDetail(in1);
				dao.modifyObject(toModify);
				toReturn = true;
			}
		} catch (Exception e) {
			toReturn = false;
		}
		return toReturn;
	}

	public boolean deleteProject(java.lang.String in0)
			throws java.rmi.RemoteException {
		boolean toReturn;
		ProjectDetailDAOHibImpl dao = new ProjectDetailDAOHibImpl();
		try {
			ProjectDetail toDelete = dao.loadObjectByCode(in0);
			if (toDelete != null) {
				toReturn = dao.deleteObject(toDelete.getPrjDetailId());
			} else {
				toReturn = false;
			}
		} catch (Exception e) {
			toReturn = false;
		}
		return toReturn;
	}

}
