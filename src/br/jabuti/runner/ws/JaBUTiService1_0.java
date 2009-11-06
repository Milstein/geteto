package br.jabuti.runner.ws;

import java.io.File;

import javax.activation.DataHandler;


/**
 * Description of the class JaBUTiService.
 *
 *
 */
public class JaBUTiService1_0 {

	/**
	 * Description of the method createProject.
	 *
	 * @param projectName
	 * @param projectFile
	 * @return projectId
	 */
	public String createProject(String projectName, DataHandler projectFile) 
		throws InvalidFileFault, InvalidNameFault
	{		
		//save the attached file in a temporary directory 
		File f = VerifingData.saveTempFile(projectFile);

		FileValidation fv = new FileValidation();
		if(fv.validateFile(f)) {
		    WsProject control = new WsProject();
		    String ret[] = control.create(projectName, f);
	    	return ret[0];
		}
	    else
	    	throw new InvalidFileFault(fv.getMessage()); 
	}

	/**
	 * Description of the method updateProject.
	 *
	 * @param projectId
	 * @param projectFile
	 * @return message
	 */
	public String updateProject(String projectId, DataHandler projectFile) 
		throws InvalidProjectIdFault, InvalidFileFault
	{
		VerifingData verifingdata = new VerifingData();
		if(verifingdata.existProject(projectId))
		{
			File f = VerifingData.saveTempFile(projectFile);
			FileValidation fv = new FileValidation();
			if(fv.validateFile(f)) {
				WsProject control = new WsProject();
				control.update(projectId, f);
				
				return "project updated.";
			}
			else
				throw new InvalidFileFault(fv.getMessage());
		}
		else
			throw new InvalidProjectIdFault("The project does not exist.");
	}

	/**
	 * Description of the method deleteProject.
	 *
	 * @param projectId
	 * @return message
	 */
	public String deleteProject(String projectId) 
		throws InvalidProjectIdFault
	{
		VerifingData verifingdata = new VerifingData();
		if(verifingdata.existProject(projectId)) {
			WsProject control = new WsProject();
			control.delete(projectId);
			return "The project was removed.";			
		}
		else 
			throw new InvalidProjectIdFault("The project does not exist.");
	}
	
	/**
	 * Description of the method cleanProject.
	 *
	 * @param projectId
	 * @return message
	 */
	public String cleanProject(String projectId) 
		throws InvalidProjectIdFault
	{
		VerifingData verifingdata = new VerifingData();
		if(verifingdata.existProject(projectId)) {
			WsProject control = new WsProject();
			control.clean(projectId);
			return "The project was cleaned.";			
		}
		else 
			throw new InvalidProjectIdFault("The project does not exist.");
	}
	
	/**
	 * Description of the method ignoreClasses.
	 *
	 * @param projectId
	 * @param classes
	 * @return message
	 */
	public String ignoreClasses(String projectId, String[] classes) 
		throws InvalidProjectIdFault, ClassNotFoundFault, InvalidExpressionFault
	{
		//to do
		
		return "ok";
	}	

	/**
	 * Description of the method selectClassesToInstrument.
	 *
	 * @param projectId
	 * @param classes
	 * @return message
	 */
	public String selectClassesToInstrument(String projectId, String[] classes) 
		throws InvalidProjectIdFault, ClassNotFoundFault, InvalidExpressionFault
	{
		VerifingData verifingdata = new VerifingData();
		if(verifingdata.existProject(projectId)) {
			WsProject control = new WsProject();
			control.selectClassesToInstrument(projectId, classes);
			
			return "The classes were instrumented.";			
		}
		else 
			throw new InvalidProjectIdFault("The project does not exist.");
	}

	/**
	 * Description of the method getAllRequiredElements.
	 *
	 * @param projectId
	 * @return methods
	 */
	public RequiredElementsDetails[] getAllRequiredElements(String projectId) 
		throws InvalidProjectIdFault, OperationSequenceFault
	{
		VerifingData verifingdata = new VerifingData();
		if(verifingdata.existProject(projectId)) {
			if(verifingdata.isProjectInstrumented(projectId)) {
				WsProject control = new WsProject();
				return control.getAllRequiredElements(projectId);
			}
			else
				throw new OperationSequenceFault("The project's classes are not instrumented.");
		}
		else 
			throw new InvalidProjectIdFault("The project does not exist.");
	}
	
	/**
	 * Description of the method getRequiredElementsByCriterion.
	 *
	 * @param projectId
	 * @param criterion
	 * @return methods
	 */
	public Method[] getRequiredElementsByCriterion(String projectId, String criterion) 
		throws InvalidProjectIdFault, OperationSequenceFault, InvalidCriterionFault
	{
		WsProject control = new WsProject();
		return control.getRequiredElementsByCriterion(projectId, criterion);
	}

	/**
	 * Description of the method addTestCases.
	 *
	 * @param projectId
	 * @param testSuiteClass
	 * @param testCaseFile
	 * @return message
	 */
	public String addTestCases(String projectId, String testSuiteClass, DataHandler testCaseFile) 
		throws InvalidFileFault, InvalidProjectIdFault, ClassNotFoundFault, OperationSequenceFault
	{
		VerifingData verifingdata = new VerifingData();
		if(verifingdata.existProject(projectId))
		{
			File f = VerifingData.saveTempFile(testCaseFile);
			FileValidation fv = new FileValidation();
			if(fv.validateFile(f)) {
				if(verifingdata.isThereClassInFile(testSuiteClass, f)) {
					WsProject control = new WsProject();
					control.addTestCases(projectId, testSuiteClass, f);
					return "Test Case file was added.";
				}
				else
					throw new ClassNotFoundFault("Class " + testSuiteClass + " was not found in testcase file.");
			}
			else
				throw new InvalidFileFault(fv.getMessage());
		}
		else
			throw new InvalidProjectIdFault("The project does not exist.");	
	}

	/**
	 * Description of the method getInstrumentedProject.
	 *
	 * @param projectId
	 * @return project
	 */
	public InstrumentedProjectDetails getInstrumentedProject(String projectId) 
		throws InvalidProjectIdFault, OperationSequenceFault
	{
		VerifingData verifingdata = new VerifingData();
		if(verifingdata.existProject(projectId)) {
			if(verifingdata.isProjectInstrumented(projectId)) {
				WsProject control = new WsProject();
				return control.getInstrumentedProject(projectId);
			}
			else
				throw new OperationSequenceFault("The project's classes are not instrumented.");	
				//test cases are not added
				//to do
		}
		else 
			throw new InvalidProjectIdFault("The project does not exist.");
	}
	
	/**
	 * Description of the method sendTraceFile.
	 *
	 * @param projectId
	 * @param tracefile
	 * @return message
	 */
	public String sendTraceFile(String projectId, DataHandler tracefile) 
		throws InvalidProjectIdFault, OperationSequenceFault, InvalidFileFault
	{
		VerifingData verifingdata = new VerifingData();
		if(verifingdata.existProject(projectId)) {
			if(verifingdata.isProjectInstrumented(projectId)) {
				WsProject control = new WsProject();
				
				File f = VerifingData.saveTempFile(tracefile);
				control.sendTraceFile(projectId, f);
				
				return "ok";
			}
			else
				throw new OperationSequenceFault("The project's classes are not instrumented.");	
				//test cases are not added
				//to do
		}
		else 
			throw new InvalidProjectIdFault("The project does not exist.");
	}	
	
	/**
	 * Description of the method getCoverageByCriteria.
	 *
	 * @param projectId
	 * @return criteria
	 */
	public CoverageCriterionDetails[] getCoverageByCriteria(String projectId) 
		throws InvalidProjectIdFault, OperationSequenceFault
	{
		WsProject control = new WsProject();
		return control.getCoverageByCriteria(projectId);
	}

	/**
	 * Description of the method getCoverageByClasses.
	 *
	 * @param projectId
	 * @return classCoverage
	 */
	public CoverageDetails[] getCoverageByClasses(String projectId) 
		throws InvalidProjectIdFault, OperationSequenceFault
	{
		WsProject control = new WsProject();
		return control.getCoverageByClasses(projectId);
	}

	/**
	 * Description of the method getCoverageByMethods.
	 *
	 * @param projectId
	 * @return methodCoverage
	 */
	public CoverageDetails[] getCoverageByMethods(String projectId) 
		throws InvalidProjectIdFault, OperationSequenceFault
	{
		WsProject control = new WsProject();
		return control.getCoverageByMethods(projectId);
	}

	/**
	 * Description of the method getAllCoveredAndUncoveredRequiredElements.
	 *
	 * @param projectId
	 * @return methodCoverage
	 */
	public MethodDetails[] getAllCoveredAndUncoveredRequiredElements(String projectId) 
		throws InvalidProjectIdFault, OperationSequenceFault
	{
		WsProject control = new WsProject();
		return control.getAllCoveredAndUncoveredRequiredElements(projectId);
	}
}