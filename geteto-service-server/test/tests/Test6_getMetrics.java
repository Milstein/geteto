package tests;

import java.rmi.RemoteException;

import org.apache.axis2.AxisFault;
import org.apache.axis2.Constants;

import br.icmc.usp.jabuti.service.ClassNotFoundFaultException;
import br.icmc.usp.jabuti.service.InvalidExpressionFaultException;
import br.icmc.usp.jabuti.service.InvalidProjectIdFaultException;
import br.icmc.usp.jabuti.service.JaBUTiService1_0Stub;
import br.icmc.usp.jabuti.service.JaBUTiService1_0Stub.GetMetrics;
import br.icmc.usp.jabuti.service.JaBUTiService1_0Stub.GetMetricsResponse;
import br.icmc.usp.jabuti.service.JaBUTiService1_0Stub.MetricResClass;
import br.icmc.usp.jabuti.service.JaBUTiService1_0Stub.OoMetric;

public class Test6_getMetrics {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try { 
			JaBUTiService1_0Stub stub = new JaBUTiService1_0Stub("http://localhost:8080/jabutiprojectSvn/services/JaBUTiService1_0");
			
			stub._getServiceClient().getOptions().setProperty(Constants.Configuration.ENABLE_MTOM, Constants.VALUE_TRUE);
			stub._getServiceClient().getOptions().setTimeOutInMilliSeconds(4000000);			
			
			GetMetrics input = new GetMetrics();
			input.setProjectId("27950000206133");
			input.setIdUserName("andre");
			String classes[] = new String[1];
			classes[0] = "*";
			input.setClasses(classes);
			
			GetMetricsResponse output = stub.getMetrics(input);
			
			System.out.println("project id: " + output.get_return().length);
			MetricResClass c[] = output.get_return();
			for (int i = 0; i < c.length; i++) {
				System.out.println(c[i].getName());
				OoMetric ms[] = c[i].getMetrics();
				for (int j = 0; j < ms.length; j++) {
					System.out.println("--" + ms[j].getName() + ": " + ms[j].getValue());
				}
			}
			
			
		} catch (AxisFault e) {
			//
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
		}
	}

}
