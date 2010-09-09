package tests;


import java.io.File;
import java.rmi.RemoteException;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;

import org.apache.axis2.AxisFault;
import org.apache.axis2.Constants;

import br.icmc.usp.jabuti.service.InvalidFileFaultException;
import br.icmc.usp.jabuti.service.InvalidNameFaultException;
import br.icmc.usp.jabuti.service.JaBUTiService1_0Stub;
import br.icmc.usp.jabuti.service.JaBUTiService1_0Stub.CreateProject;
import br.icmc.usp.jabuti.service.JaBUTiService1_0Stub.CreateProjectResponse;

public class Test1_CreateProject {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//
		try { 
			JaBUTiService1_0Stub stub = new JaBUTiService1_0Stub(Config.SERVICE_URL);
			
			stub._getServiceClient().getOptions().setProperty(Constants.Configuration.ENABLE_MTOM, Constants.VALUE_TRUE);
			stub._getServiceClient().getOptions().setTimeOutInMilliSeconds(4000000);			
			CreateProject input = new CreateProject();
			input.setProjectName("Vending");
			input.setIdUserName("user");
			//File file = new File("/home/andre/ifiles/doctoral/install/eclipseworkspace/sort.jar");
			File file = new File("/home/andre/ifiles/doctoral/install/eclipseworkspace/vending.jar");
			//File file = new File("/home/andre/ifiles/doctoral/papers/jabutiService/FinalCaseStudy/beanutils.jar");
			
			FileDataSource fds = new FileDataSource(file);
			DataHandler datahandler = new DataHandler(fds);
			input.setProjectFile(datahandler);
			CreateProjectResponse output;
			String projId = "";

			output = stub.createProject(input);
			projId = output.get_return();
			System.out.println("project id: " + projId);
			
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

}
