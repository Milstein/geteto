package tests;

import java.rmi.RemoteException;

import org.apache.axis2.AxisFault;

import br.icmc.usp.jabuti.service.InvalidProjectIdFaultException;
import br.icmc.usp.jabuti.service.JaBUTiService1_0Stub;
import br.icmc.usp.jabuti.service.JaBUTiService1_0Stub.DeleteProject;
import br.icmc.usp.jabuti.service.JaBUTiService1_0Stub.DeleteProjectResponse;

public class Test9_deleteProject {
	
	public static void main (String[] args) {
		//
		try { 
			JaBUTiService1_0Stub stub = Config.getStub();
			DeleteProject input = new DeleteProject();
			input.setIdUserName("user"); // doesn't matter
			input.setProjectId("1554529212574341");
			DeleteProjectResponse output = stub.deleteProject(input);
			System.out.println(output.get_return());
		} catch (AxisFault e) {			
			e.printStackTrace();
		} catch (RemoteException e) {			
			e.printStackTrace();
		} catch (InvalidProjectIdFaultException e) {
			e.printStackTrace();
		}
	}

}
