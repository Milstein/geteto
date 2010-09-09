package it.eng.spago4q.extractors.qualipso;

import it.eng.spago.error.EMFUserError;
import it.eng.spago4q.bo.ProjectDetail;
import it.eng.spago4q.extractors.bo.GenericItem;
import it.eng.spago4q.extractors.bo.GenericItemInterface;

import java.util.ArrayList;
import java.util.List;

public class JabutiResourcesCheckExtractor extends AbstractQualipsoExtractor {

	private List<GenericItemInterface> toReturn;
	
	//Compare the Macxim uploaded projects with
	//this included in Spago4Q.
	@Override
	protected List<GenericItemInterface> extract() throws EMFUserError {
		
		toReturn = new ArrayList<GenericItemInterface>();
		
//		System.out.println("Get Project List");
//		
//		
//		UploadProject upProj = new UploadProject();
//		String macximProjectList = upProj.getProjectList();
//		System.out.println(macximProjectList);
//		
//		System.out.println("Get Project List");
		return toReturn;		
	}
	
	@Override
	protected void setUp() {

	}

	@Override
	protected void tearDown() {
	}
	
	@Override
	public String operationTest(Integer operationId) throws EMFUserError {
		
		String toReturn = "";
		setOperationParameter(operationId);
		String project = readOperationParameterValue("PROJECT");
		
		if(project != null && !(project.trim().equals(""))){
			String detail = getProjectDetail(project);
			
			toReturn ="<b>" + project +"</b><br>";
			toReturn += "<textarea>"+ detail +"</textarea>";
		}
		return toReturn;
	}
}
