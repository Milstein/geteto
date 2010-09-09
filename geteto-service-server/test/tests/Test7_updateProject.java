package tests;

import java.io.File;
import java.rmi.RemoteException;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;

import org.apache.axis2.AxisFault;
import org.apache.axis2.Constants;

import br.icmc.usp.jabuti.service.InvalidFileFaultException;
import br.icmc.usp.jabuti.service.InvalidProjectIdFaultException;
import br.icmc.usp.jabuti.service.JaBUTiService1_0Stub;
import br.icmc.usp.jabuti.service.JaBUTiService1_0Stub.UpdateProject;
import br.icmc.usp.jabuti.service.JaBUTiService1_0Stub.UpdateProjectResponse;

public class Test7_updateProject {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//
		try { 
			JaBUTiService1_0Stub stub = 
				new JaBUTiService1_0Stub(
						"http://localhost:8080/jabutiprojectSvn/services/JaBUTiService1_0");
			//JaBUTiService1_0Stub stub = new JaBUTiService1_0Stub("http://syros.eurodyn.com:9999/jabutiprojectSvn/services/JaBUTiService1_0");
			
			stub._getServiceClient().getOptions().setProperty(Constants.Configuration.ENABLE_MTOM, Constants.VALUE_TRUE);
			stub._getServiceClient().getOptions().setTimeOutInMilliSeconds(4000000);			
			UpdateProject input = new UpdateProject();
			input.setProjectId("5783742984094");
			input.setIdUserName("user");
			//File file = new File("/home/andre/ifiles/doctoral/install/eclipseworkspace/sort.jar");
			File file = new File("E:\\workspace\\Jabuti-workspace\\Vending\\vending.jar");
			//File file = new File("/home/andre/ifiles/doctoral/papers/jabutiService/FinalCaseStudy/beanutils.jar");
			
			FileDataSource fds = new FileDataSource(file);
			DataHandler datahandler = new DataHandler(fds);
			input.setProjectFile(datahandler);
			UpdateProjectResponse output;
			String projId = "";

			output = stub.updateProject(input);
			projId = output.get_return();
			System.out.println("response: " + projId);
			
		} catch (AxisFault e) {
			//
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidProjectIdFaultException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidFileFaultException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
