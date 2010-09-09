package tests;

import java.rmi.RemoteException;

import org.apache.axis2.AxisFault;
import org.apache.axis2.Constants;

import br.icmc.usp.jabuti.service.JaBUTiService1_0Stub;
import br.icmc.usp.jabuti.service.JaBUTiService1_0Stub.GetProjectsResponse;
import br.icmc.usp.jabuti.service.JaBUTiService1_0Stub.JabutiServiceProject;

public class Test8_getProjects {
	
	public static void main (String[] args) {
		//
		try { 
			JaBUTiService1_0Stub stub = 
				new JaBUTiService1_0Stub(
						"http://localhost:8080/jabutiprojectSvn/services/JaBUTiService1_0");			
			
			stub._getServiceClient().getOptions().setProperty(Constants.Configuration.ENABLE_MTOM, Constants.VALUE_TRUE);
			stub._getServiceClient().getOptions().setTimeOutInMilliSeconds(4000000);			
			
			GetProjectsResponse output = stub.getProjects();
			for (JabutiServiceProject jsp : output.get_return()) {
				System.out.println(jsp.getName() + ", " + jsp.getProjid());
			}
			
		} catch (AxisFault e) {
			//
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

}
