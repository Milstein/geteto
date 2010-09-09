package jabuti.ws.gui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.swing.JOptionPane;

import org.apache.axis2.AxisFault;
import org.apache.axis2.Constants;

import br.icmc.usp.jabuti.service.InvalidFileFaultException;
import br.icmc.usp.jabuti.service.InvalidProjectIdFaultException;
import br.icmc.usp.jabuti.service.JaBUTiService1_0Stub;
import br.icmc.usp.jabuti.service.JaBUTiService1_0Stub.AddTestCases;
import br.icmc.usp.jabuti.service.JaBUTiService1_0Stub.AddTestCasesResponse;
import br.icmc.usp.jabuti.service.JaBUTiService1_0Stub.CoverageCriterionDetails;
import br.icmc.usp.jabuti.service.JaBUTiService1_0Stub.CoverageDetails;
import br.icmc.usp.jabuti.service.JaBUTiService1_0Stub.CreateProject;
import br.icmc.usp.jabuti.service.JaBUTiService1_0Stub.CreateProjectResponse;
import br.icmc.usp.jabuti.service.JaBUTiService1_0Stub.CriterionCoveredUncovered;
import br.icmc.usp.jabuti.service.JaBUTiService1_0Stub.DeleteProject;
import br.icmc.usp.jabuti.service.JaBUTiService1_0Stub.DeleteProjectResponse;
import br.icmc.usp.jabuti.service.JaBUTiService1_0Stub.GetAllCoveredAndUncoveredRequiredElements;
import br.icmc.usp.jabuti.service.JaBUTiService1_0Stub.GetAllCoveredAndUncoveredRequiredElementsResponse;
import br.icmc.usp.jabuti.service.JaBUTiService1_0Stub.GetCoverageByClasses;
import br.icmc.usp.jabuti.service.JaBUTiService1_0Stub.GetCoverageByClassesResponse;
import br.icmc.usp.jabuti.service.JaBUTiService1_0Stub.GetCoverageByCriteria;
import br.icmc.usp.jabuti.service.JaBUTiService1_0Stub.GetCoverageByCriteriaResponse;
import br.icmc.usp.jabuti.service.JaBUTiService1_0Stub.GetCoverageByMethods;
import br.icmc.usp.jabuti.service.JaBUTiService1_0Stub.GetCoverageByMethodsResponse;
import br.icmc.usp.jabuti.service.JaBUTiService1_0Stub.GetInstrumentedProject;
import br.icmc.usp.jabuti.service.JaBUTiService1_0Stub.GetInstrumentedProjectResponse;
import br.icmc.usp.jabuti.service.JaBUTiService1_0Stub.GetMetrics;
import br.icmc.usp.jabuti.service.JaBUTiService1_0Stub.GetMetricsResponse;
import br.icmc.usp.jabuti.service.JaBUTiService1_0Stub.GetProjectsResponse;
import br.icmc.usp.jabuti.service.JaBUTiService1_0Stub.JabutiServiceProject;
import br.icmc.usp.jabuti.service.JaBUTiService1_0Stub.MethodDetails;
import br.icmc.usp.jabuti.service.JaBUTiService1_0Stub.MetricResClass;
import br.icmc.usp.jabuti.service.JaBUTiService1_0Stub.OoMetric;
import br.icmc.usp.jabuti.service.JaBUTiService1_0Stub.SelectClassesToInstrument;
import br.icmc.usp.jabuti.service.JaBUTiService1_0Stub.SelectClassesToInstrumentResponse;
import br.icmc.usp.jabuti.service.JaBUTiService1_0Stub.SendSpagoXML;
import br.icmc.usp.jabuti.service.JaBUTiService1_0Stub.SendSpagoXMLResponse;
import br.icmc.usp.jabuti.service.JaBUTiService1_0Stub.SendTraceFile;
import br.icmc.usp.jabuti.service.JaBUTiService1_0Stub.SendTraceFileResponse;
import br.icmc.usp.jabuti.service.JaBUTiService1_0Stub.UpdateProject;
import br.icmc.usp.jabuti.service.JaBUTiService1_0Stub.UpdateProjectResponse;


public class Script {

	/**
	 * @param args
	 */

	public static void run(String projectID, String user, String endpoint, String program, 
			String testCases, String testSuite, String testDir, String libraries[], boolean reports[], String commandLine) {
		System.out.println("Script.run");
		System.out.println("projectId = " + projectID + ", user = " + user + ", endpoint = " + endpoint);
		System.out.println("program = " + program + ", testCases = " + testCases);
		System.out.println("testSuite = " + testSuite + ", testDir = " + testDir);
		System.out.println("libraries.length = " + libraries.length + ", reports.length = " + reports.length);
		System.out.println("commandLine = " + commandLine);
		try { 
			//JaBUTiService1_0Stub stub = new JaBUTiService1_0Stub("www.labes.icmc.usp.br:9999/jabutiprojectSvn/services/JaBUTiService1_0");
			JaBUTiService1_0Stub stub = new JaBUTiService1_0Stub(endpoint);
			stub._getServiceClient().getOptions().setProperty(Constants.Configuration.ENABLE_MTOM, Constants.VALUE_TRUE);
			stub._getServiceClient().getOptions().setTimeOutInMilliSeconds(4000000);

			/*			CreateProject input = new CreateProject();
			input.setProjectName(projectName);
			input.setIdUserName(user);

			File file = new File(program);						
			FileDataSource fds = new FileDataSource(file);
			DataHandler datahandler = new DataHandler(fds);
			input.setProjectFile(datahandler);
			CreateProjectResponse output;
			String projectid="";

			output = stub.createProject(input);
			projectid = output.get_return();
			//projectid="2333096738024153";
			System.out.println("project id: " + projectid);
			 */
//			projectid = projectID;
//			
//			System.out.println("UpdateProject operation");
			
//			UpdateProject input = new UpdateProject();
//			input.setProjectId(projectID);
//			input.setIdUserName("user");
//
//			File file = new File(program);						
//			FileDataSource fds = new FileDataSource(file);
//			DataHandler datahandler = new DataHandler(fds);
//			input.setProjectFile(datahandler);
//			
//			UpdateProjectResponse output;
//			output = stub.updateProject(input);

//			System.out.println("return: " + output.get_return());
			
			update(projectID, endpoint, program);

			//getRequiredElements			
//			if (reports[4]==true) {
//				System.out.println("GetRequiredElements operation");
//				
//				GetAllRequiredElements in2 = new GetAllRequiredElements();
//				in2.setProjectId(projectid);
//				in2.setIdUserName("user");
//				
//				GetAllRequiredElementsResponse out2 = stub.getAllRequiredElements(in2);
//				RequiredElementsDetails details[] = out2.get_return();
//				
//				String required[][] = new String[details.length+1][9];
//
//				required[0][0]="method name";
//				Criterion c[]=details[0].getCriterion();
//				for (int i=0; i<c.length;i++)
//					required[0][i+1]=c[i].getName();
//				for (int i = 0; i < details.length; i++) {
//					required[i+1][0]=details[i].getMethodName();
//					c = details[i].getCriterion(); 
//					for (int j = 0; j < c.length; j++) {
//						required[i+1][j+1]="";					
//						String elems[] = c[j].getElements();
//						if(elems != null)
//							for (int j2 = 0; j2 < elems.length; j2++) 
//							{
//								required[i+1][j+1]+=elems[j2]+=",";
//							}
//						//System.out.println();
//					}
//				}		
//
//				for (int i=0; i<required.length; i++)
//					for (int j=0; j<9; j++)
//					{
//						if (required[i][j]!=null)
//						{
//							required[i][j]=required[i][j].replace("<", "&lt;");
//							required[i][j]=required[i][j].replace(">", "&gt;");
//						}
//					}
//
//				generateHTMLPage("Required Elements",required,testDir+"required.html" );	        
//				Frm_Result required_result = new Frm_Result(null,false, "Required Elements",testDir+"required.html");			
//				required_result.setVisible(true);
//
//				generateXML("RequiredElements",required,testDir+"required.xml");
//			}

			addTestCases(endpoint, testCases, testSuite, projectID);

			//--------------------------------------------------------
			//getInstrumentedProject
			
			getInstrumentedProject(endpoint, testDir, projectID);
			
			/*
			String execString = "java -cp "+TestDir+"Jabuti-bin-2007-12-19.zip";
			for (int i=0; i<libraries.length; i++)
				execString=execString+":"+libraries[i];
			execString+=" br.jabuti.junitexec.JUnitJabutiCore -trace "+TestDir+"test.trc -cp ";
			TestSuiteClass = TestSuiteClass.replace(".class", "");			
			execString=execString+TestDir+"package.jar -tcClass "+ TestSuiteClass; 
			System.out.println(execString);
			 */

			//			String execString = "java -cp " + TestDir + "package.jar";			
			//			for (int i=0; i<libraries.length; i++) {
			//				execString = execString + ":" + libraries[i];
			//			}
			//			execString += ";/home/besson/Jabuti_1_0_3-bin.jar";
			//			execString+=" br.jabuti.junitexec.JUnitJabutiCore -trace "+TestDir+"test.trc -cp ";
			//			TestSuiteClass = TestSuiteClass.replace(".class", "");			
			//			execString=execString+TestDir+"package.jar -tcClass "+ TestSuiteClass; 
			//			System.out.println(execString);			

			runCommandLine(commandLine);

//			t1.interrupt();
//			t2.interrupt();

			sendTraceFile(endpoint, projectID, testDir);
			
		} catch (AxisFault e) {
			//
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void generateHTMLPage(String title,String data[][], String name)
	{
		PrintWriter fileHTML;
		try {
			fileHTML = new PrintWriter(new FileOutputStream(name));
			//saida.println();
			fileHTML.println("<html>");
			fileHTML.println("<title> " + title + "</title>");
			fileHTML.println("<body>");

			fileHTML.println("<table border=\"1\">");

			for (int i=0; i<data.length; i++)
			{
				fileHTML.println("<tr>");
				for (int j=0; j<data[0].length;j++)
				{
					fileHTML.println("<td>"+data[i][j]+"</td>");
				}
				fileHTML.println("</tr>");
			}
			fileHTML.println("</table>");
			fileHTML.println("</body>");
			fileHTML.println("</html>");
			fileHTML.close();
		} catch (FileNotFoundException ex) {
			Logger.getLogger(JFrm_RequiredElements.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public static void generateXML(String root,String data[][], String name)
	{
		PrintWriter fileXML;
		try {
			int j;
			fileXML = new PrintWriter(new FileOutputStream(name));
			//saida.println();
			fileXML.println("<?xml version='1.0'?>");
			fileXML.println("<" + root + ">");

			for (int i=1; i<data.length; i++)
			{

				for (j=0; j<data[0].length;j++)
				{
					if (j==0)
						fileXML.println("   <"+data[0][j] +" name="+data[i][j]+">");
					else fileXML.println("      <"+data[0][j] +">"+data[i][j]+"</"+data[0][j] +">");
				}
				fileXML.println("   </"+data[0][0]+">");               
			}
			fileXML.println("</" + root + ">");
			fileXML.close();
		} catch (FileNotFoundException ex) {
			Logger.getLogger(JFrm_RequiredElements.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

	public static void create(String projectName, String user, String endpoint, String program, String[] classes) {
		try { 
			//JaBUTiService1_0Stub stub = new JaBUTiService1_0Stub("www.labes.icmc.usp.br:9999/jabutiprojectSvn/services/JaBUTiService1_0");
			JaBUTiService1_0Stub stub = new JaBUTiService1_0Stub(endpoint);
			stub._getServiceClient().getOptions().setProperty(Constants.Configuration.ENABLE_MTOM, Constants.VALUE_TRUE);
			stub._getServiceClient().getOptions().setTimeOutInMilliSeconds(4000000);

			CreateProject input = new CreateProject();
			input.setProjectName(projectName);
			input.setIdUserName(user);

			File file = new File(program);						
			FileDataSource fds = new FileDataSource(file);
			DataHandler datahandler = new DataHandler(fds);
			input.setProjectFile(datahandler);
			CreateProjectResponse output;			

			output = stub.createProject(input);
			String projectid = output.get_return();
			//projectid="2333096738024153";
			System.out.println("project id: " + projectid);

			SelectClassesToInstrument in1 = new SelectClassesToInstrument();
			in1.setProjectId(projectid);
			classes = new String[1];
			classes[0] = "*";
			in1.setClasses(classes);
			in1.setIdUserName(user);
			SelectClassesToInstrumentResponse out1 = stub.selectClassesToInstrument(in1);
			System.out.println("SelectClassesToInstrument operation");
			System.out.println("ret: " + out1.get_return());
		}
		catch (Exception e) {			
			logger.log(Level.SEVERE, e.getMessage(), e);
		}
	}

	public static void update(String projectId, String endpoint, String program) {
		System.out.println("Script.update(" + projectId + ", " + endpoint + ", " + program + ")");
		try { 
			JaBUTiService1_0Stub stub = new JaBUTiService1_0Stub(endpoint);
			stub._getServiceClient().getOptions().setProperty(Constants.Configuration.ENABLE_MTOM, Constants.VALUE_TRUE);
			stub._getServiceClient().getOptions().setTimeOutInMilliSeconds(4000000);

			UpdateProject input = new UpdateProject();
			input.setProjectId(projectId);
			input.setIdUserName("besson");

			File file = new File(program);						
			FileDataSource fds = new FileDataSource(file);
			DataHandler datahandler = new DataHandler(fds);
			input.setProjectFile(datahandler);
			UpdateProjectResponse output;			

			output = stub.updateProject(input);

			System.out.println("ret: " + output.get_return());

			selectClassesToInstrument(projectId, endpoint);
		}
		catch (Exception e) {			
			logger.log(Level.SEVERE, e.getMessage(), e);
		}
	}
	
	public static void selectClassesToInstrument(String projectId, String endpoint) {
		System.out.println("Script.selectClassesToInstrument(" + projectId + ")");
		try {
			JaBUTiService1_0Stub stub = new JaBUTiService1_0Stub(endpoint);
			stub._getServiceClient().getOptions().setProperty(Constants.Configuration.ENABLE_MTOM, Constants.VALUE_TRUE);
			stub._getServiceClient().getOptions().setTimeOutInMilliSeconds(4000000);

			SelectClassesToInstrument in = new SelectClassesToInstrument();

			in.setProjectId(projectId);

			String[] classes = new String[1];
			classes[0] = "*";
			in.setClasses(classes);

			in.setIdUserName("user");

			SelectClassesToInstrumentResponse out = stub.selectClassesToInstrument(in);

			System.out.println("ret: " + out.get_return());
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
		}
	}

	public static void addTestCases(String endpoint, String testCases, String testSuite, String projectid) {
		System.out.println("Script.addTestCases(" + endpoint + "," + testCases + ", " + testSuite + ", " + projectid + ")");
		try {
			JaBUTiService1_0Stub stub = new JaBUTiService1_0Stub(endpoint);
			stub._getServiceClient().getOptions().setProperty(Constants.Configuration.ENABLE_MTOM, Constants.VALUE_TRUE);
			stub._getServiceClient().getOptions().setTimeOutInMilliSeconds(4000000);

			String TestSuiteClass = testSuite;	
			String TestFilePath = testCases;	

			AddTestCases in = new AddTestCases();
			in.setProjectId(projectid);
			in.setIdUserName("user");
			in.setTestSuiteClass(TestSuiteClass);

			//attach the test file
			File file = new File(TestFilePath);
			FileDataSource fds = new FileDataSource(file);
			DataHandler datahandler = new DataHandler(fds);
			in.setTestCaseFile(datahandler);

			AddTestCasesResponse out = stub.addTestCases(in);
			
			System.out.println("ret: " + out.get_return());
		}
		catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
		}
	}

	public static JabutiServiceProject[] getProjects(String endpoint) {
		try {			
			JaBUTiService1_0Stub stub = new JaBUTiService1_0Stub(endpoint);
			stub._getServiceClient().getOptions().setProperty(
					Constants.Configuration.ENABLE_MTOM, Constants.VALUE_TRUE);
			stub._getServiceClient().getOptions().setTimeOutInMilliSeconds(4000000);

			GetProjectsResponse response = stub.getProjects();

			System.out.println("getProjects operation");
			System.out.println("ret: " + response.get_return());
			
			JabutiServiceProject[] ret = new JabutiServiceProject[0];
			if (response.get_return() != null) {
				ret = response.get_return();
			}

			return ret;
		}
		catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			javax.swing.JOptionPane.showMessageDialog(null, "Unable to access JaBUTi Web Service", "Operation Error", javax.swing.JOptionPane.ERROR_MESSAGE);
			return null;
		}
	}
	
	public static void deleteProject(String endpoint, String id) {
		try { 
			System.out.println("Endpoint: " + endpoint);
			System.out.println("Trying to delete project: " + id);
			JaBUTiService1_0Stub stub = new JaBUTiService1_0Stub(endpoint);
			DeleteProject input = new DeleteProject();
			input.setIdUserName("user"); // doesn't matter
			input.setProjectId(id);
			DeleteProjectResponse output = stub.deleteProject(input);
			System.out.println("Response: " + output.get_return());
		} catch (AxisFault e) {			
			e.printStackTrace();
		} catch (RemoteException e) {			
			e.printStackTrace();
		} catch (InvalidProjectIdFaultException e) {
			e.printStackTrace();
		}
	}
	
	public static void getReports(String endpoint, String id, String testDir, boolean[] reports) {
		if (!testDir.endsWith("\\") && !testDir.endsWith("/")) {
			testDir += "/";
		}
		try {
			String user = "user";
			System.out.println("Endpoint: " + endpoint);
			System.out.println("Trying to get reports from: " + id);
			JaBUTiService1_0Stub stub = new JaBUTiService1_0Stub(endpoint);
			if (reports[0]==true)
			{
				GetAllCoveredAndUncoveredRequiredElements gcue = new GetAllCoveredAndUncoveredRequiredElements();
				gcue.setIdUserName(user);
				gcue.setProjectId(id);

				GetAllCoveredAndUncoveredRequiredElementsResponse gcuer = stub.getAllCoveredAndUncoveredRequiredElements(gcue);
				MethodDetails mdts[] = gcuer.get_return();

				String elements[][]=new String[mdts.length*9][3];
				for (int i=0; i<mdts.length*9; i++)
					for (int j=0; j<3; j++)
						elements[i][j]="";




				int line=0;
				for (int i=0; i<mdts.length; i++)
				{				
					elements[line][0]=mdts[i].getMethodName();
					elements[line][1]="Covered Elements";
					elements[line][2]="Uncovered Elements";					

					elements[line+1][0]="All-Nodes-ei";
					elements[line+2][0]="All-Nodes-ed";
					elements[line+3][0]="All-Edges-ei";
					elements[line+4][0]="All-Edges-ed";
					elements[line+5][0]="All-Uses-ei";
					elements[line+6][0]="All-Uses-ed";
					elements[line+7][0]="All-Pot-Uses-ei";
					elements[line+8][0]="All-Pot-Uses-ed";

					CriterionCoveredUncovered ccucv[]=mdts[i].getCriteria();									
					for (int j=0; j<ccucv.length; j++)
					{
						line++;
						String cve[]=ccucv[j].getCoveredElements();
						elements[line][1]="";						
						elements[line][2]="";											
						if (cve!=null)
							for (int k=0; k<cve.length; k++)
								elements[line][1]+=cve[k]+" ";	

						cve=ccucv[j].getUncoveredElements();
						if (cve!=null)
							for (int k=0; k<cve.length; k++)
								elements[line][2]+=cve[k]+" ";	

					}
					line++;																			
				}

				for (int i=0; i<elements.length; i++)
					for (int j=0; j<3; j++)
					{
						elements[i][j]=elements[i][j].replace("<", "&lt;");
						elements[i][j]=elements[i][j].replace(">", "&gt;");						
					}

				generateHTMLPage("Covered and Uncovered Elements",elements,testDir+"coveredUncovered.html");
				Frm_Result coveredUncovered_result = new Frm_Result(null,false, "Covered and Uncovered Elements",testDir+"coveredUncovered.html");			
				coveredUncovered_result.setVisible(true);

				generateXML("CoveredAndUncovered",elements,testDir+"coveredUncovered.xml");
			}

			//getCoverageByClasses			
			if (reports[1]==true)
			{
				System.out.println("getting coverageByClasses report...");
				GetCoverageByClasses gcbc = new GetCoverageByClasses();
				gcbc.setIdUserName(user);
				gcbc.setProjectId(id);

				GetCoverageByClassesResponse gcbcr = stub.getCoverageByClasses(gcbc);
				CoverageDetails coverageClasses[]= gcbcr.get_return();
				System.out.println("coverageClasses ok? " + (coverageClasses != null));
				
				String cvrClasses[][]=new String[coverageClasses.length+1][9];

				for (int i=0; i<cvrClasses.length; i++)
					for (int j=0; j<9; j++)
						cvrClasses[i][j]="";

				cvrClasses[0][0]="Class";
				cvrClasses[0][1]="All-Nodes-ei";
				cvrClasses[0][2]="All-Nodes-ed";
				cvrClasses[0][3]="All-Edges-ei";
				cvrClasses[0][4]="All-Edges-ed";
				cvrClasses[0][5]="All-Uses-ei";
				cvrClasses[0][6]="All-Uses-ed";
				cvrClasses[0][7]="All-Pot-Uses-ei";
				cvrClasses[0][8]="All-Pot-Uses-ed";	



				for (int i = 0; i < coverageClasses.length; i++) 
				{
					cvrClasses[i+1][0]=coverageClasses[i].getName();
					CoverageCriterionDetails criterion[] = coverageClasses[i].getCriteria();
					for (int j=0; j<criterion.length; j++)
					{
						cvrClasses[i+1][j+1]=String.valueOf(criterion[j].getNumberOfCoveredElements()) + " of " +
						String.valueOf(criterion[j].getNumberOfElements()) + "("+String.valueOf(criterion[j].getCoveragePercentage())+")";									
					}
				}
				for (int i=0; i<cvrClasses.length; i++)
					for (int j=0; j<9; j++)
					{
						cvrClasses[i][j]=cvrClasses[i][j].replace("<", "&lt;");
						cvrClasses[i][j]=cvrClasses[i][j].replace(">", "&gt;");
					}

				generateHTMLPage("Coverage By Class",cvrClasses,testDir+"coverageByClass.html");
				Frm_Result coverageClasses_result = new Frm_Result(null,false, "Coverage by class",testDir+"coverageByClass.html");			
				coverageClasses_result.setVisible(true);

				generateXML("ClassCoverage",cvrClasses,testDir+"coverageByClass.xml");

			}


			//getCoverageByCriterion
			if (reports[2]==true)
			{
				GetCoverageByCriteria in6 = new GetCoverageByCriteria();
				in6.setProjectId(id);
				in6.setIdUserName(user);			
				GetCoverageByCriteriaResponse out6 = stub.getCoverageByCriteria(in6);			

				System.out.println("GetCoverageByCriteria operation");
				CoverageCriterionDetails criterion[] = out6.get_return();

				String coverageCriteria[][]=new String[criterion.length+1][4];

				for (int i=0; i<coverageCriteria.length; i++)
					for (int j=0; j<4; j++)
						coverageCriteria[i][j]="";

				coverageCriteria[0][0]="Criterion";
				coverageCriteria[0][1]="Number of Elements";
				coverageCriteria[0][2]="Number of Covered Elements";
				coverageCriteria[0][3]="Percentage";


				for (int i = 0; i < criterion.length; i++) {
					coverageCriteria[i+1][0]=criterion[i].getCriterionName();
					coverageCriteria[i+1][1]=String.valueOf(criterion[i].getNumberOfElements());
					coverageCriteria[i+1][2]=String.valueOf(criterion[i].getNumberOfCoveredElements());
					coverageCriteria[i+1][3]=String.valueOf(criterion[i].getCoveragePercentage());								
				}			

				for (int i=0; i<coverageCriteria.length; i++)
					for (int j=0; j<4; j++)
					{
						coverageCriteria[i][j]=coverageCriteria[i][j].replace("<", "&lt;");
						coverageCriteria[i][j]=coverageCriteria[i][j].replace(">", "&gt;");
					}
				generateHTMLPage("Coverage By Criterion",coverageCriteria,testDir+"coverageCriteria.html");
				Frm_Result coverageCriteria_result = new Frm_Result(null,false, "Coverage by criterion",testDir+"coverageCriteria.html");			
				coverageCriteria_result.setVisible(true);

				generateXML("CriterionCoverage",coverageCriteria,testDir+"coverageCriteria.xml");
			}

			//getCoverageByMethod			
			if (reports[3]==true)
			{
				GetCoverageByMethods gcbm = new GetCoverageByMethods();
				gcbm.setIdUserName(user);
				gcbm.setProjectId(id);
				System.out.println("Coverage by method");
				GetCoverageByMethodsResponse gcbmr = stub.getCoverageByMethods(gcbm);
				CoverageDetails coverageMethods[]=gcbmr.get_return();

				String cvrMethods[][]=new String[coverageMethods.length+1][9];
				for (int i=0; i<cvrMethods.length; i++)
					for (int j=0; j<9; j++)
						cvrMethods[i][j]="";
				cvrMethods[0][0]="Method";
				cvrMethods[0][1]="All-Nodes-ei";
				cvrMethods[0][2]="All-Nodes-ed";
				cvrMethods[0][3]="All-Edges-ei";
				cvrMethods[0][4]="All-Edges-ed";
				cvrMethods[0][5]="All-Uses-ei";
				cvrMethods[0][6]="All-Uses-ed";
				cvrMethods[0][7]="All-Pot-Uses-ei";
				cvrMethods[0][8]="All-Pot-Uses-ed";	

				for (int i = 0; i < coverageMethods.length; i++) 
				{
					cvrMethods[i+1][0]=coverageMethods[i].getName();
					CoverageCriterionDetails criterion[] = coverageMethods[i].getCriteria();
					for (int j=0; j<criterion.length; j++)
					{
						cvrMethods[i+1][j+1]=String.valueOf(criterion[j].getNumberOfCoveredElements()) + " of " +
						String.valueOf(criterion[j].getNumberOfElements()) + "("+String.valueOf(criterion[j].getCoveragePercentage())+")";									
					}
				}

				for (int i=0; i<cvrMethods.length; i++)
					for (int j=0; j<9; j++)
					{
						cvrMethods[i][j]=cvrMethods[i][j].replace("<", "&lt;");
						cvrMethods[i][j]=cvrMethods[i][j].replace(">", "&gt;");
					}

				generateHTMLPage("Coverage by Method",cvrMethods,testDir+"coverageByMethod.html");
				Frm_Result coverageMethods_result = new Frm_Result(null,false, "Coverage by method",testDir+"coverageByMethod.html");			
				coverageMethods_result.setVisible(true);

				generateXML("MethodCoverage",cvrMethods,testDir+"coverageByMethod.xml");
			}

			//getMetrics
			if (reports[5]==true)
			{
				GetMetrics gm = new GetMetrics();
				String cl[] = new String[1];
				cl[0]="*";
				gm.setClasses(cl);
				gm.setIdUserName(user);
				gm.setProjectId(id);

				GetMetricsResponse gmr = stub.getMetrics(gm);
				MetricResClass mrc[]= gmr.get_return();

				int qtdeMetricas = mrc[0].getMetrics().length;
				String elements[][]=new String[mrc.length+1][qtdeMetricas+1];
				for (int i=0; i<mrc.length+1; i++)
					for (int j=0; j<qtdeMetricas+1; j++)
						elements[i][j]="";

				elements[0][0]="Classes";
				for (int i=0; i<qtdeMetricas; i++)
					elements[0][i+1]=mrc[0].getMetrics()[i].getName();					

				for (int i=0; i<mrc.length; i++)
				{
					elements[i+1][0]=mrc[i].getName();
					OoMetric om[] = mrc[i].getMetrics();
					for (int j=0; j<om.length; j++)
					{
						elements[i+1][j+1]=String.valueOf(om[j].getValue());					                  
					}
				}

				generateHTMLPage("OO Metrics",elements,testDir+"metrics.html");
				Frm_Result metrics_result = new Frm_Result(null,false, "OO Metrics",testDir+"metrics.html");			
				metrics_result.setVisible(true);

				generateXML("Metrics",elements,testDir+"metrics.xml");
			}

			//getGraphs
			if (reports[6]==true)
			{

			}

		} catch (Exception e) {			
			e.printStackTrace();
		}
	}
	
	public static void getInstrumentedProject(String endpoint, String TestDir, String projectId) {
		System.out.println("Script.getInstrumentedProject(" + endpoint + ", " + TestDir + ", " + projectId + ")");

		GetInstrumentedProject in = new GetInstrumentedProject();
		in.setProjectId(projectId);
		in.setIdUserName("user");

		try {
			JaBUTiService1_0Stub stub = getStub(endpoint);
			GetInstrumentedProjectResponse out = stub.getInstrumentedProject(in);

			DataHandler datahandler = out.get_return().getFile();
			FileOutputStream fos = new FileOutputStream(new File(TestDir + "package.jar"));
			datahandler.writeTo(fos);
			fos.flush();
			fos.close();
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
		}
	}
	
	private static JaBUTiService1_0Stub getStub(String endpoint) throws AxisFault {
		JaBUTiService1_0Stub stub = new JaBUTiService1_0Stub(endpoint);
		stub._getServiceClient().getOptions().setProperty(
				Constants.Configuration.ENABLE_MTOM, Constants.VALUE_TRUE);
		stub._getServiceClient().getOptions().setTimeOutInMilliSeconds(4000000);
		return stub;
	}
	
	private static void runCommandLine(String commandLine) throws IOException, InterruptedException {
		System.out.println("Script.runCommandLine");
		System.out.println(commandLine);
		final Process p = Runtime.getRuntime().exec(commandLine);

		Thread t1 = new Thread() {
			@Override
			public void run() {
				BufferedReader is = new BufferedReader(
						new InputStreamReader(p.getInputStream()));
				try {
					String sline;
					while ((sline = is.readLine()) != null)
						System.out.println(sline);
				} catch (IOException e) {
					// ...
				}
			}
		};
		t1.start();

		Thread t2 = new Thread() {
			@Override
			public void run() {
				BufferedReader is = new BufferedReader(
						new InputStreamReader(p.getErrorStream()));
				try {
					String sline;
					while ((sline = is.readLine()) != null)
						System.out.println(sline);
				} catch (IOException e) {
					// ...
				}
			}
		};
		t2.start();

		p.waitFor();
	}
	
	public static void sendTraceFile(String endpoint, String projectId, String testDir) {
		System.out.println("Script.sendTraceFile(" + endpoint + ", " + projectId + ", " + testDir + ")");			
		try {
			SendTraceFile in = new SendTraceFile();
			in.setProjectId(projectId);
			in.setIdUserName("user");
			//String TestDir = testDir;
			//attach trace file
			FileDataSource fds = new FileDataSource(new File(testDir + "test.trc"));
			DataHandler datahandler = new DataHandler(fds);
			in.setTracefile(datahandler);		

			JaBUTiService1_0Stub stub = getStub(endpoint);
			
			SendTraceFileResponse out = stub.sendTraceFile(in);
			
			System.out.println("msg: " + out.get_return());
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
		}
	}
	
	
	public static void sendXmlFile(String endpoint, String projectId, String xmlFile){
		try {
			SendSpagoXML in = new SendSpagoXML();
			in.setProjectId(projectId);
			
			FileDataSource fds = new FileDataSource(new File(xmlFile));
			DataHandler datahandler = new DataHandler(fds);
			in.setXmlfile(datahandler);
			JaBUTiService1_0Stub stub = getStub(endpoint);
			SendSpagoXMLResponse out = stub.sendSpagoXML(in);	
			JOptionPane.showMessageDialog(null, "File sent", "Success", JOptionPane.INFORMATION_MESSAGE);
			} catch (RemoteException e) {
				JOptionPane.showMessageDialog(null, "File not sent", "Error", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			} catch (InvalidFileFaultException e) {
				JOptionPane.showMessageDialog(null, "File not sent", "Error", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			} catch (InvalidProjectIdFaultException e) {
				JOptionPane.showMessageDialog(null, "File not sent", "Error", JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
			}
	}

	static Logger logger = Logger.getLogger(Script.class.getSimpleName());

}
