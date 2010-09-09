package it.eng.spago4q.extractors.qualipso;

import java.util.ArrayList;

import it.eng.spago.error.EMFUserError;
import it.eng.spago4q.bo.ProjectDetail;
import it.eng.spago4q.dao.ProjectDetailDAOHibImpl;
import it.eng.spago4q.extractors.AbstractExtractor;

public abstract class AbstractQualipsoExtractor extends AbstractExtractor {

	public String getProjectDetail(String projectCode) {
		String toReturn;
		ProjectDetailDAOHibImpl dao = new ProjectDetailDAOHibImpl();
		ProjectDetail projectDetail;
		try {
			projectDetail = dao.loadObjectByCode(projectCode);
			toReturn = projectDetail.getDetail();
		} catch (EMFUserError e) {
			toReturn = null;
		}
		return toReturn;
	}
	
	public ArrayList<ProjectDetail> getProjectList(){
		ArrayList<ProjectDetail> result = null;
		ProjectDetailDAOHibImpl dao = new ProjectDetailDAOHibImpl();
		try {
			result = (ArrayList<ProjectDetail>) dao.loadObjectList("ASC", "ASC");
		} catch (EMFUserError e) {
			result = null;
		}		
		
		return result;
	}
}
