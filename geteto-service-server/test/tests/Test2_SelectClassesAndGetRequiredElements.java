package tests;

import java.rmi.RemoteException;

import org.apache.axis2.AxisFault;
import org.apache.axis2.Constants;

import br.icmc.usp.jabuti.service.ClassNotFoundFaultException;
import br.icmc.usp.jabuti.service.InvalidExpressionFaultException;
import br.icmc.usp.jabuti.service.InvalidProjectIdFaultException;
import br.icmc.usp.jabuti.service.JaBUTiService1_0Stub;
import br.icmc.usp.jabuti.service.OperationSequenceFaultException;
import br.icmc.usp.jabuti.service.JaBUTiService1_0Stub.Criterion;
import br.icmc.usp.jabuti.service.JaBUTiService1_0Stub.GetAllRequiredElements;
import br.icmc.usp.jabuti.service.JaBUTiService1_0Stub.GetAllRequiredElementsResponse;
import br.icmc.usp.jabuti.service.JaBUTiService1_0Stub.RequiredElementsDetails;
import br.icmc.usp.jabuti.service.JaBUTiService1_0Stub.SelectClassesToInstrument;
import br.icmc.usp.jabuti.service.JaBUTiService1_0Stub.SelectClassesToInstrumentResponse;

public class Test2_SelectClassesAndGetRequiredElements {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// 
		String projectid = "520761549527";
		System.out.println("init");
		try {
			//JaBUTiService1_0Stub stub = new JaBUTiService1_0Stub("http://syros.eurodyn.com:9999/jabutiprojectSvn/services/JaBUTiService1_0");
			JaBUTiService1_0Stub stub = new JaBUTiService1_0Stub("http://localhost:8080/jabutiprojectSvn/services/JaBUTiService1_0");
			stub._getServiceClient().getOptions().setProperty(Constants.Configuration.ENABLE_MTOM, Constants.VALUE_TRUE);
			stub._getServiceClient().getOptions().setTimeOutInMilliSeconds(4000000);			
			SelectClassesToInstrument in1 = new SelectClassesToInstrument();
			in1.setProjectId(projectid);
			String classes[] = new String[1];
			classes[0] = "*";
			in1.setClasses(classes);
			in1.setIdUserName("andre");
			SelectClassesToInstrumentResponse out1 = stub.selectClassesToInstrument(in1);
			System.out.println("SelectClassesToInstrument operation");
			System.out.println("ret: " + out1.get_return());
			
			//getRequiredElements
			System.out.println("");
			System.out.println("GetAllRequiredElements operation");
			GetAllRequiredElements in2 = new GetAllRequiredElements();
			in2.setProjectId(projectid);
			in2.setIdUserName("andre");
			GetAllRequiredElementsResponse out2 = stub.getAllRequiredElements(in2);
			RequiredElementsDetails details[] = out2.get_return();
			for (int i = 0; i < details.length; i++) {
				System.out.println("--method: " + details[i].getMethodName());
				Criterion c[] = details[i].getCriterion();
				for (int j = 0; j < c.length; j++) {
					System.out.println("----Criterion: " + c[j].getName());
					System.out.print("----Elements: ");
					String elems[] = c[j].getElements();
					if(elems != null)
						for (int j2 = 0; j2 < elems.length; j2++) {
							System.out.print(elems[j2] + ", ");
						}
					System.out.println();
				}
			}			
		
		} catch (AxisFault e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidExpressionFaultException e) {
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
		}
		System.out.println("finish");

	}

}
