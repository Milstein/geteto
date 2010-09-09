package tests;

import org.apache.axis2.AxisFault;
import org.apache.axis2.Constants;

import br.icmc.usp.jabuti.service.JaBUTiService1_0Stub;

public class Config {
	
	public static final String SERVICE_URL = 
		"http://www.labes.icmc.usp.br:8180/jabuti-ws-1.0/services/" +
		"JaBUTiService1_0?wsdl";
	
	public static JaBUTiService1_0Stub getStub() throws AxisFault {		
		JaBUTiService1_0Stub stub = 
			new JaBUTiService1_0Stub(Config.SERVICE_URL);			
		
		stub._getServiceClient().getOptions().setProperty(
				Constants.Configuration.ENABLE_MTOM, Constants.VALUE_TRUE);
		stub._getServiceClient().getOptions().setTimeOutInMilliSeconds(
				4000000);
		
		return stub;
	}

}
