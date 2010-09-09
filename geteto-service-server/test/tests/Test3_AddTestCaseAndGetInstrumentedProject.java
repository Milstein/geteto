package tests;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.RemoteException;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;

import org.apache.axis2.AxisFault;
import org.apache.axis2.Constants;

import br.icmc.usp.jabuti.service.ClassNotFoundFaultException;
import br.icmc.usp.jabuti.service.InvalidFileFaultException;
import br.icmc.usp.jabuti.service.InvalidProjectIdFaultException;
import br.icmc.usp.jabuti.service.JaBUTiService1_0Stub;
import br.icmc.usp.jabuti.service.OperationSequenceFaultException;
import br.icmc.usp.jabuti.service.JaBUTiService1_0Stub.AddTestCases;
import br.icmc.usp.jabuti.service.JaBUTiService1_0Stub.AddTestCasesResponse;
import br.icmc.usp.jabuti.service.JaBUTiService1_0Stub.GetInstrumentedProject;
import br.icmc.usp.jabuti.service.JaBUTiService1_0Stub.GetInstrumentedProjectResponse;

public class Test3_AddTestCaseAndGetInstrumentedProject {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// 
		String projectid = "4865315496541584";
		
		//String TestSuiteClass = "junit.tests.Test1";
		String TestSuiteClass = "vending.DispenserTestCase";
		//String TestSuiteClass = "org.apache.commons.beanutils.BeanUtilsTestSuite";
		
		//String TestFilePath = "/home/andre/ifiles/doctoral/install/eclipseworkspace/sort_test.jar";
		String TestFilePath = "/home/andre/ifiles/doctoral/install/eclipseworkspace/vending_test.jar";
		//String TestFilePath = "/home/andre/ifiles/doctoral/papers/jabutiService/FinalCaseStudy/beanutils_test.jar";
		
		String TestDir = "/home/andre/ifiles/doctoral/install/TestDir/";
		//String TestDir = "/home/andre/ifiles/doctoral/papers/jabutiService/FinalCaseStudy/";
		
		try {
			JaBUTiService1_0Stub stub = new JaBUTiService1_0Stub("http://syros.eurodyn.com:9999/jabutiprojectSvn/services/JaBUTiService1_0");
			stub._getServiceClient().getOptions().setProperty(Constants.Configuration.ENABLE_MTOM, Constants.VALUE_TRUE);
			stub._getServiceClient().getOptions().setTimeOutInMilliSeconds(4000000);					
			
			AddTestCases in1 = new AddTestCases();
			in1.setProjectId(projectid);
			in1.setIdUserName("andre");
			in1.setTestSuiteClass(TestSuiteClass);
			
			//attach the test file
			File file = new File(TestFilePath);
			FileDataSource fds = new FileDataSource(file);
			DataHandler datahandler = new DataHandler(fds);
			in1.setTestCaseFile(datahandler);
			
			AddTestCasesResponse out1 = stub.addTestCases(in1);
			System.out.println("addTestCases operation");
			System.out.println("ret: " + out1.get_return());
			
			//--------------------------------------------------------
			//getInstrumentedProject
			System.out.println("GetInstrumentedProject operation");
			GetInstrumentedProject in2 = new GetInstrumentedProject();
			in2.setProjectId(projectid);
			in2.setIdUserName("andre");
			
			GetInstrumentedProjectResponse out2 = stub.getInstrumentedProject(in2);

			System.out.println(out2.get_return().getCommandLine());
			datahandler = out2.get_return().getFile();
			FileOutputStream fos = new FileOutputStream(new File(TestDir + "package.jar"));
			datahandler.writeTo(fos);
			fos.flush();
			fos.close();
			
		} 
		catch (AxisFault e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidFileFaultException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundFaultException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidProjectIdFaultException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OperationSequenceFaultException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
