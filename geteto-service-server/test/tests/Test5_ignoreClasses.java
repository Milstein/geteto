package tests;

import java.io.File;
import java.rmi.RemoteException;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;

import junit.framework.TestCase;

import org.apache.axis2.AxisFault;
import org.apache.axis2.Constants;

import br.icmc.usp.jabuti.service.InvalidFileFaultException;
import br.icmc.usp.jabuti.service.InvalidNameFaultException;
import br.icmc.usp.jabuti.service.JaBUTiService1_0Stub;
import br.icmc.usp.jabuti.service.JaBUTiService1_0Stub.CreateProject;
import br.icmc.usp.jabuti.service.JaBUTiService1_0Stub.CreateProjectResponse;
import br.icmc.usp.jabuti.service.JaBUTiService1_0Stub.IgnoreClasses;
import br.icmc.usp.jabuti.service.JaBUTiService1_0Stub.IgnoreClassesResponse;
import br.icmc.usp.jabuti.service.JaBUTiService1_0Stub.SelectClassesToInstrument;
import br.icmc.usp.jabuti.service.JaBUTiService1_0Stub.SelectClassesToInstrumentResponse;

public class Test5_ignoreClasses extends TestCase {
	
	private static String projectid;
	
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		
		try {
			JaBUTiService1_0Stub stub = new JaBUTiService1_0Stub();
			stub._getServiceClient().getOptions().setProperty(Constants.Configuration.ENABLE_MTOM, Constants.VALUE_TRUE);
			stub._getServiceClient().getOptions().setTimeOutInMilliSeconds(4000000);			
			CreateProject input = new CreateProject();
			input.setProjectName("Junit");
			File file = new File("/home/andre/ifiles/doctoral/install/eclipseworkspace/junit.jar");
			
			FileDataSource fds = new FileDataSource(file);
			DataHandler datahandler = new DataHandler(fds);
			input.setProjectFile(datahandler);
			CreateProjectResponse output;

			output = stub.createProject(input);
			projectid = output.get_return();
			System.out.println("new Test Case");
			System.out.println("project id: " + projectid);
			
		} catch (AxisFault e) {
			//
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidNameFaultException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidFileFaultException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	public void testSendStrings1()
	{
		try {
			JaBUTiService1_0Stub stub = new JaBUTiService1_0Stub();
			stub._getServiceClient().getOptions().setProperty(Constants.Configuration.ENABLE_MTOM, Constants.VALUE_TRUE);
			stub._getServiceClient().getOptions().setTimeOutInMilliSeconds(4000000);			

			IgnoreClasses input = new IgnoreClasses();
			input.setProjectId(projectid);
			String classes[] = new String[3];
			classes[0] = "junit.runner.*";
			classes[1] = "junit.swingui.*";
			classes[2] = "junit.textui.*";
			input.setClasses(classes);
			IgnoreClassesResponse output = stub.ignoreClasses(input);
			assertEquals("the ignored classes were recorded.", output.get_return());
			
		} catch (Exception e) {
			//
			e.printStackTrace();
			fail();
		}		
	}

	public void testIgnoringAndSelecting()
	{
		try {
			JaBUTiService1_0Stub stub = new JaBUTiService1_0Stub();
			stub._getServiceClient().getOptions().setProperty(Constants.Configuration.ENABLE_MTOM, Constants.VALUE_TRUE);
			stub._getServiceClient().getOptions().setTimeOutInMilliSeconds(4000000);			

			IgnoreClasses input1 = new IgnoreClasses();
			input1.setProjectId(projectid);
			String classes[] = new String[3];
			classes[0] = "junit.runner.*";
			classes[1] = "junit.swingui.*";
			classes[2] = "junit.textui.*";
			input1.setClasses(classes);
			IgnoreClassesResponse output1 = stub.ignoreClasses(input1);
			assertEquals("the ignored classes were recorded.", output1.get_return());
			
			SelectClassesToInstrument input2 = new SelectClassesToInstrument();
			input2.setProjectId(projectid);
			classes = new String[1];
			classes[0] = "*";
			input2.setClasses(classes);
			SelectClassesToInstrumentResponse output2 = stub.selectClassesToInstrument(input2);
			assertEquals("The classes were instrumented.", output2.get_return());
			
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}		
	}

	public void testNotIgnoringAndSelecting()
	{
		try {
			JaBUTiService1_0Stub stub = new JaBUTiService1_0Stub();
			stub._getServiceClient().getOptions().setProperty(Constants.Configuration.ENABLE_MTOM, Constants.VALUE_TRUE);
			stub._getServiceClient().getOptions().setTimeOutInMilliSeconds(4000000);
			
			String classes[];
			
			SelectClassesToInstrument input2 = new SelectClassesToInstrument();
			input2.setProjectId(projectid);
			classes = new String[3];
			classes[0] = "junit.runner.*";
			classes[1] = "junit.swingui.*";
			classes[2] = "junit.textui.*";
			input2.setClasses(classes);
			SelectClassesToInstrumentResponse output2 = stub.selectClassesToInstrument(input2);
			assertEquals("The classes were instrumented.", output2.get_return());
			
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}		
	}
}
