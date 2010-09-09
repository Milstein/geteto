package it.eng.spago4q.extractors.qualipso;

import it.eng.spago.error.EMFUserError;
import it.eng.spago4q.extractors.bo.GenericItem;
import it.eng.spago4q.extractors.bo.GenericItemInterface;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import org.apache.axis2.AxisFault;
import org.apache.axis2.Constants;

import br.icmc.usp.jabuti.service.IOExceptionException;
import br.icmc.usp.jabuti.service.InvalidProjectIdFaultException;
import br.icmc.usp.jabuti.service.JaBUTiService1_0Stub;
import br.icmc.usp.jabuti.service.OperationSequenceFaultException;
import br.icmc.usp.jabuti.service.ParserConfigurationExceptionException;
import br.icmc.usp.jabuti.service.SAXExceptionException;
import br.icmc.usp.jabuti.service.JaBUTiService1_0Stub.CoverageCriterionDetails;
import br.icmc.usp.jabuti.service.JaBUTiService1_0Stub.GetCoverageByCriteria;
import br.icmc.usp.jabuti.service.JaBUTiService1_0Stub.GetCoverageByCriteriaResponse;
import br.icmc.usp.jabuti.service.JaBUTiService1_0Stub.GetProjectsResponse;
import br.icmc.usp.jabuti.service.JaBUTiService1_0Stub.JabutiServiceProject;

public class JabutiAnalysisExtractor extends AbstractQualipsoExtractor {

	public static final String SERVICE_URL = "SERVICE_URL";

	@Override
	protected List<GenericItemInterface> extract() throws EMFUserError {
		System.out.println("JabutiAnalysisExtractor.extract()");
		List<GenericItemInterface> toReturn = new ArrayList<GenericItemInterface>();
		
		String url = readDataSourceParameterValue(SERVICE_URL);
		System.out.println("SERVICE_URL = " + url);

		JaBUTiService1_0Stub stub;
		try {
			stub = new JaBUTiService1_0Stub(url);
			System.out.println("stub ok? " + (stub != null));

			stub._getServiceClient().getOptions().setProperty(
					Constants.Configuration.ENABLE_MTOM, Constants.VALUE_TRUE);
			stub._getServiceClient().getOptions().setTimeOutInMilliSeconds(
					4000000);

			GetProjectsResponse response = stub.getProjects();

			JabutiServiceProject[] projects = response.get_return();
			System.out.println("projects.size = " + projects.length);

			for (JabutiServiceProject jbt : projects) {
				System.out.println("project: " + jbt.getProjid());

				GetCoverageByCriteria in2 = new GetCoverageByCriteria();
				in2.setProjectId(jbt.getProjid());
				in2.setIdUserName("user");

				GetCoverageByCriteriaResponse out2 = stub.getCoverageByCriteria(in2);

				CoverageCriterionDetails criterion[] = out2.get_return();
				for (int i = 0; i < criterion.length; i++) {				
					GenericItem genericItem = new GenericItem();

					genericItem.setValue("Metric", criterion[i]
							.getCriterionName());
					genericItem.setValue("Resource", jbt.getName());
					genericItem.setValue("Value", String.valueOf(criterion[i]
							.getCoveragePercentage()));
					toReturn.add(genericItem);
				}
			}
		}

		catch (Exception e) {
			e.printStackTrace();
		} 

		return toReturn;
	}

	@Override
	protected void setUp() {
	}

	@Override
	protected void tearDown() {
	}

}
