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
import br.icmc.usp.jabuti.service.OperationSequenceFaultException;
import br.icmc.usp.jabuti.service.JaBUTiService1_0Stub.CoverageCriterionDetails;
import br.icmc.usp.jabuti.service.JaBUTiService1_0Stub.GetCoverageByCriteria;
import br.icmc.usp.jabuti.service.JaBUTiService1_0Stub.GetCoverageByCriteriaResponse;
import br.icmc.usp.jabuti.service.JaBUTiService1_0Stub.SendTraceFile;
import br.icmc.usp.jabuti.service.JaBUTiService1_0Stub.SendTraceFileResponse;

public class Test4_SendTraceAndGetCoverage {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// 
		
		String projectid = "4865315496541584";
		String TestDir = "/home/andre/ifiles/doctoral/install/TestDir/";
		//String TestDir = "/home/andre/ifiles/doctoral/papers/jabutiService/FinalCaseStudy/";
		
		try {
			JaBUTiService1_0Stub stub = new JaBUTiService1_0Stub("http://syros.eurodyn.com:9999/jabutiprojectSvn/services/JaBUTiService1_0");
			stub._getServiceClient().getOptions().setProperty(Constants.Configuration.ENABLE_MTOM, Constants.VALUE_TRUE);
			stub._getServiceClient().getOptions().setTimeOutInMilliSeconds(4000000);					
			
			SendTraceFile in1 = new SendTraceFile();
			in1.setProjectId(projectid);
			in1.setIdUserName("andre");

			//attach trace file
			FileDataSource fds = new FileDataSource(new File(TestDir + "test.trc"));
			DataHandler datahandler = new DataHandler(fds);
			in1.setTracefile(datahandler);			
			
			SendTraceFileResponse out1 = stub.sendTraceFile(in1);

			System.out.println("SendTraceFile operation");
			System.out.println("msg: " + out1.get_return());
			
			//getCoverageByCriterion
			GetCoverageByCriteria in2 = new GetCoverageByCriteria();
			in2.setProjectId(projectid);
			in2.setIdUserName("andre");
			
			GetCoverageByCriteriaResponse out2 = stub.getCoverageByCriteria(in2);
			
			System.out.println("GetCoverageByCriteria operation");
			CoverageCriterionDetails criterion[] = out2.get_return();
			for (int i = 0; i < criterion.length; i++) {
				System.out.println(criterion[i].getCriterionName());
				System.out.println(criterion[i].getNumberOfCoveredElements() + " of " + criterion[i].getNumberOfElements() + " (" + criterion[i].getCoveragePercentage() + "%)");
			}			
			
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
		} catch (InvalidProjectIdFaultException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OperationSequenceFaultException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
