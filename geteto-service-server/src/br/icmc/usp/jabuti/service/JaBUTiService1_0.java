package br.icmc.usp.jabuti.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.axis2.context.ServiceContext;
import org.xml.sax.SAXException;

import br.icmc.usp.jabuti.service.config.TestConfiguration;
import br.icmc.usp.jabuti.service.persistence.Dao;
import br.icmc.usp.jabuti.service.persistence.JabutiServiceProject;
import br.jabuti.webservices.FileValidation;
import br.jabuti.webservices.VerifingData;
import br.jabuti.webservices.WsProject;

/**
 * This class represents the interface of the JaBUTiService.
 *
 */
public class JaBUTiService1_0 {

	Properties props = null;
	
	/*
	 * Method called when the service is deployed
	 */
	public void init(ServiceContext serviceContext) {       
        System.out.println("***********************************init");
        //load the properties file
        try {
	        props = new Properties();
			props.load(getClass().getResourceAsStream("/jabuti.properties"));  
			//test the deployment process
			TestConfiguration.test(props);
        } 
        catch (FileNotFoundException e1) {
        	System.out.println("Error: Properties file not found");
        	e1.printStackTrace();
		}  
		catch(IOException e){
			System.out.println("Error: Properties file IO error");
            e.printStackTrace();  
        }  
		System.out.println("***********************************init");
    }	

	/*
	 * Method called when the service is undeployed
	 */
	public void destroy(ServiceContext serviceContext) {
		System.out.println("***********************************destroy");
	}

	/**
	 * Description of the method createProject.
	 *
	 * @param projectName
	 * @param projectFile
	 * @return projectId
	 */
	public String createProject(String IdUserName, String projectName, DataHandler projectFile) 
		throws InvalidFileFault, InvalidNameFault
	{		
		//save the attached file in a temporary directory 
		File f = VerifingData.saveTempFile(projectFile);

		FileValidation fv = new FileValidation();
		if(fv.validateFile(f)) {
		    WsProject control = new WsProject(props);
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
	public String updateProject(String IdUserName, String projectId, DataHandler projectFile) 
		throws InvalidProjectIdFault, InvalidFileFault
	{
		VerifingData verifingdata = new VerifingData();
		if(verifingdata.existProject(projectId, props))
		{
			File f = VerifingData.saveTempFile(projectFile);
			FileValidation fv = new FileValidation();
			if(fv.validateFile(f)) {
				WsProject control = new WsProject(props);
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
	public String deleteProject(String IdUserName, String projectId) 
		throws InvalidProjectIdFault
	{
		VerifingData verifingdata = new VerifingData();
		if(verifingdata.existProject(projectId, props)) {
			WsProject control = new WsProject(props);
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
	public String cleanProject(String IdUserName, String projectId) 
		throws InvalidProjectIdFault
	{
		VerifingData verifingdata = new VerifingData();
		if(verifingdata.existProject(projectId, props)) {
			WsProject control = new WsProject(props);
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
	public String ignoreClasses(String IdUserName, String projectId, String[] classes) 
		throws InvalidProjectIdFault, ClassNotFoundFault, InvalidExpressionFault
	{
		VerifingData verifingdata = new VerifingData();
		if(verifingdata.existProject(projectId, props)) {
			WsProject control = new WsProject(props);
			control.ignoreClasses(projectId, classes);
			return "the ignored classes were recorded.";
		}
		else 
			throw new InvalidProjectIdFault("The project does not exist.");
	}	

	/**
	 * Description of the method selectClassesToInstrument.
	 *
	 * @param projectId
	 * @param classes
	 * @return message
	 */
	public String selectClassesToInstrument(String IdUserName, String projectId, String[] classes) 
		throws InvalidProjectIdFault, ClassNotFoundFault, InvalidExpressionFault
	{
		VerifingData verifingdata = new VerifingData();
		if(verifingdata.existProject(projectId, props)) {
			WsProject control = new WsProject(props);
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
	public RequiredElementsDetails[] getAllRequiredElements(String IdUserName, String projectId) 
		throws InvalidProjectIdFault, OperationSequenceFault
	{
		VerifingData verifingdata = new VerifingData();
		if(verifingdata.existProject(projectId, props)) {
			if(verifingdata.isProjectInstrumented(projectId)) {
				WsProject control = new WsProject(props);
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
	public Method[] getRequiredElementsByCriterion(String IdUserName, String projectId, String criterion) 
		throws InvalidProjectIdFault, OperationSequenceFault, InvalidCriterionFault
	{
		WsProject control = new WsProject(props);
		return control.getRequiredElementsByCriterion(projectId, criterion);
	}
	/**
	 * Description of the method getGraph.
	 *
	 * @param projectId
	 * @param classes
	 * @return graphImages
	 */
	public GraphDetails[] getGraph(String IdUserName, String projectId, String[] classes) 
		throws InvalidProjectIdFault, OperationSequenceFault, ClassNotFoundFault, InvalidExpressionFault
	{
		WsProject control = new WsProject(props);
		return control.getGraph(projectId, classes);
	}

	/**
	 * Description of the method addTestCases.
	 *
	 * @param projectId
	 * @param testSuiteClass
	 * @param testCaseFile
	 * @return message
	 */
	public String addTestCases(String IdUserName, String projectId, String testSuiteClass, DataHandler testCaseFile) 
		throws InvalidFileFault, InvalidProjectIdFault, ClassNotFoundFault, OperationSequenceFault
	{
		VerifingData verifingdata = new VerifingData();
		if(verifingdata.existProject(projectId, props))
		{
			File f = VerifingData.saveTempFile(testCaseFile);
			FileValidation fv = new FileValidation();
			if(fv.validateFile(f)) {
				if(verifingdata.isThereClassInFile(testSuiteClass, f)) {
					WsProject control = new WsProject(props);
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
	public InstrumentedProjectDetails getInstrumentedProject(String IdUserName, String projectId) 
		throws InvalidProjectIdFault, OperationSequenceFault
	{
		VerifingData verifingdata = new VerifingData();
		if(verifingdata.existProject(projectId, props)) {
			if(verifingdata.isProjectInstrumented(projectId)) {
				WsProject control = new WsProject(props);
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
	public String sendTraceFile(String IdUserName, String projectId, DataHandler tracefile) 
		throws InvalidProjectIdFault, OperationSequenceFault, InvalidFileFault
	{
		VerifingData verifingdata = new VerifingData();
		if(verifingdata.existProject(projectId, props)) {
			if(verifingdata.isProjectInstrumented(projectId)) {
				WsProject control = new WsProject(props);
				
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
	
	public String sendSpagoXML(String projectId, DataHandler xmlfile) 
	throws InvalidProjectIdFault, InvalidFileFault {
		VerifingData verifingdata = new VerifingData();
		if(verifingdata.existProject(projectId, props)) {			
			WsProject control = new WsProject(props);

			File f = VerifingData.saveTempFile(xmlfile);
			control.sendSpagoXML(projectId, f);

			return "ok";			
		}
		else 
			throw new InvalidProjectIdFault("The project does not exist.");
	}
	
	/**
	 * Description of the method getCoverageByCriteria.
	 *
	 * @param projectId
	 * @return criteria
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 */
	public CoverageCriterionDetails[] getCoverageByCriteria(String IdUserName, String projectId) 
		throws InvalidProjectIdFault, OperationSequenceFault, ParserConfigurationException, SAXException, IOException
	{
		WsProject control = new WsProject(props);
		return control.getCoverageByCriteriaXML(projectId);
	}

	/**
	 * Description of the method getCoverageByClasses.
	 *
	 * @param projectId
	 * @return classCoverage
	 */
	public CoverageDetails[] getCoverageByClasses(String IdUserName, String projectId) 
		throws InvalidProjectIdFault, OperationSequenceFault
	{
		WsProject control = new WsProject(props);
		return control.getCoverageByClasses(projectId);
	}

	/**
	 * Description of the method getCoverageByMethods.
	 *
	 * @param projectId
	 * @return methodCoverage
	 */
	public CoverageDetails[] getCoverageByMethods(String IdUserName, String projectId) 
		throws InvalidProjectIdFault, OperationSequenceFault
	{
		WsProject control = new WsProject(props);
		return control.getCoverageByMethods(projectId);
	}

	/**
	 * Description of the method getAllCoveredAndUncoveredRequiredElements.
	 *
	 * @param projectId
	 * @return methodCoverage
	 */
	public MethodDetails[] getAllCoveredAndUncoveredRequiredElements(String IdUserName, String projectId) 
		throws InvalidProjectIdFault, OperationSequenceFault
	{
		WsProject control = new WsProject(props);
		return control.getAllCoveredAndUncoveredRequiredElements(projectId);
	}
	
	/**
	 * Description of the method getMetrics.
	 * 
	 * @param IdUserName
	 * @param projectId
	 * @param classes
	 * @return
	 * @throws InvalidProjectIdFault
	 * @throws ClassNotFoundFault
	 * @throws InvalidExpressionFault
	 */
	public MetricResClass[] getMetrics(String IdUserName, String projectId, String[] classes) 
	throws InvalidProjectIdFault, ClassNotFoundFault, InvalidExpressionFault
	{
		WsProject control = new WsProject(props);
		return control.getMetrics(projectId, classes);
	}
	
	
	/**
	 * Description of the method getProjects.
	 *
	 * @return projects
	 */
	public JabutiServiceProject[] getProjects()	
	{		
		return new Dao(props).getProjects(); 
	}
}