package it.eng.spago4q.extractors.qualipso;

import it.eng.spago.error.EMFUserError;
import it.eng.spago4q.bo.ProjectDetail;
import it.eng.spago4q.extractors.bo.GenericItem;
import it.eng.spago4q.extractors.bo.GenericItemInterface;

import java.io.File;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.axis2.AxisFault;
import org.apache.axis2.Constants;

import br.icmc.usp.jabuti.service.InvalidFileFaultException;
import br.icmc.usp.jabuti.service.InvalidNameFaultException;
import br.icmc.usp.jabuti.service.JaBUTiService1_0Stub;
import br.icmc.usp.jabuti.service.JaBUTiService1_0Stub.CreateProject;
import br.icmc.usp.jabuti.service.JaBUTiService1_0Stub.CreateProjectResponse;
import br.icmc.usp.jabuti.service.JaBUTiService1_0Stub.GetProjectsResponse;
import br.icmc.usp.jabuti.service.JaBUTiService1_0Stub.JabutiServiceProject;

public class JabutiUploadExtractor extends AbstractQualipsoExtractor {
	
	public static final String SERVICE_URL = "SERVICE_URL";
	public static final String EMPTY_FILE = "EMPTY_FILE";

	private List<GenericItemInterface> toReturn;
	
	@Override
	protected List<GenericItemInterface> extract() throws EMFUserError {
		System.out.println("JabutiUploadExtractor.extract()");
		
		toReturn = new ArrayList<GenericItemInterface>();
				
		ArrayList<ProjectDetail> ourProjectDetail = getProjectList();
		
//		ourProjectDetail.add(new ProjectDetail(null,"VendingMachine",null));
		
		JabutiServiceProject[] projects = getProjects();	
		List<String> projectNames = new ArrayList<String>();
		
		if(projects == null)
			projects = new JabutiServiceProject[0];
		
		for (JabutiServiceProject p : projects) {
			System.out.println("name: " + p.getName());
			projectNames.add(p.getName());
		}
		
		System.out.println("project list: " + ourProjectDetail);
		for(ProjectDetail p : ourProjectDetail){
			//don't create the same projects more than once
			if (projectNames.contains(p.getCode())) {
				continue;
			}
			//extractProject(p.getPrjDetailId(),p.getCode(), p.getDetail());
			String id = createProject(p.getCode());
			
			// gerar algo como retorno
			GenericItem item = new GenericItem();
			item.setValue("Resource", p.getCode());
			item.setValue("Metric", "ID");
			item.setValue("Value", id);
			toReturn.add(item);
		}
		
		return toReturn;		
	}
	
	//private void extractProject(int id, String projectCode, String projectDetail){
		
					
//		ProjectMetadata projectMetadata = Spago4qMessageBuilder.getProjectMetadata(projectDetail);
//		Repository repository = Spago4qMessageBuilder.getProjectRepository(projectDetail);
//		
//		String projectUploadMacxim = null;
//		
//		if(projectMetadata != null){	
//			projectUploadMacxim = "Macxim Project Upload request correctly sent.";
//			UploadRequest upProj = new UploadRequest();		
//			upProj.uploadProject(projectMetadata, repository);
//		}
//		else{
//			projectUploadMacxim = "Project not Uploaded cause not MOSST model or java project";
//		}
//		
//		GenericItem genericItem = new GenericItem();
//		genericItem.setValue("Metric", projectUploadMacxim);
//		genericItem.setValue("Resource", projectCode);
//		genericItem.setValue("Value", id+"");
//		
//		toReturn.add(genericItem);
		
	private String createProject (String name){
		System.out.println("JabutiUploadExtractor.createProject: " + name);
		try {
			String url = readDataSourceParameterValue(SERVICE_URL);
			JaBUTiService1_0Stub stub = new JaBUTiService1_0Stub(url);
			System.out.println("SERVICE_URL = " + url);
			
			stub._getServiceClient().getOptions().setProperty(
					Constants.Configuration.ENABLE_MTOM, Constants.VALUE_TRUE);
			stub._getServiceClient().getOptions()
					.setTimeOutInMilliSeconds(4000000);			
			CreateProject input = new CreateProject();
			input.setProjectName(name);
			input.setIdUserName("user");
			
			String emptyFile = readDataSourceParameterValue(EMPTY_FILE);
			File file = new File(emptyFile);
			System.out.println("EMPTY_FILE = " + emptyFile);
			
			FileDataSource fds = new FileDataSource(file);
			DataHandler datahandler = new DataHandler(fds);
			input.setProjectFile(datahandler);
			CreateProjectResponse output;
			String projId = "";

			output = stub.createProject(input);
			projId = output.get_return();
			System.out.println("Project ID: " + projId);
			return projId;			
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
		return "";
	}
	
	private JabutiServiceProject[] getProjects() {
		System.out.println("JabutiUploadExtractor.getProjectList");
		try {
			String url = readDataSourceParameterValue(SERVICE_URL);
			JaBUTiService1_0Stub stub = new JaBUTiService1_0Stub(url);
			System.out.println("SERVICE_URL = " + url);
			
			stub._getServiceClient().getOptions().setProperty(
					Constants.Configuration.ENABLE_MTOM, Constants.VALUE_TRUE);
			stub._getServiceClient().getOptions()
					.setTimeOutInMilliSeconds(4000000);
			
			GetProjectsResponse output;
			output = stub.getProjects();
			
			return output.get_return();
			
		} catch (AxisFault e) {
			//
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new JabutiServiceProject[0];
	}
	
	private void sendMail(String id, String adminEmail) {
		String from = "jabutiservice@gmail.com";
//		incluir um par�metro no extrator para o email do admin
		String[] recipients = {
				adminEmail
		};
		
		String subject = "[JabutiService] Project Created";
		
//		Melhorar a mensagem... :D
		String message = 
			"Project created; ID = " + id;
		
		String smtpHost = "smtp.gmail.com";
		int smtpPort = 465;
		
		String username = "jabutiservice@gmail.com";
//		JPasswordField passwordField = new JPasswordField(10);  
//		passwordField.setEchoChar('*');
//		JOptionPane.showMessageDialog(null, passwordField);
//		String password = new String(passwordField.getPassword());
		String password = "J4but1.S3rv1c3";

		boolean debug = false;

		//Set the host smtp address
		Properties props = new Properties();
		props.put("mail.transport.protocol", "smtps");
		props.put("mail.smtps.host", smtpHost);
		props.put("mail.smtps.auth", "true");       

//		create some properties and get the default Session
//		Authenticator auth = new Authenticator() {
//			public PasswordAuthentication getPasswordAuthentication() {
//				String username = "rmmartins@gmail.com";
//				JPasswordField passwordField = new JPasswordField(10);  
//				passwordField.setEchoChar('*');
//				JOptionPane.showMessageDialog(null, passwordField);
//				String password = new String(passwordField.getPassword());
//				return new PasswordAuthentication(username, password);
//			}
//		};
		
		Session session = Session.getDefaultInstance(props);
		session.setDebug(debug);
		
		try {
			Transport transport = session.getTransport();

			// create a message
			Message msg = new MimeMessage(session);		

			// Setting the Subject and Content Type
			msg.setSubject(subject);
			msg.setContent(message, "text/plain");

			// set the from and to address
			InternetAddress addressFrom = new InternetAddress(from);
			msg.setFrom(addressFrom);

			InternetAddress[] addressTo = new InternetAddress[recipients.length]; 
			for (int i = 0; i < recipients.length; i++)
			{
				addressTo[i] = new InternetAddress(recipients[i]);
			}
			msg.setRecipients(Message.RecipientType.TO, addressTo);

			transport.connect(smtpHost, smtpPort, username, password);
			transport.sendMessage(msg,
					msg.getRecipients(Message.RecipientType.TO));
			transport.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void setUp() {

	}

	@Override
	protected void tearDown() {
	}
	
	/*
	@Override
	public String operationTest(Integer operationId) throws EMFUserError {
		
		String toReturn = "";
		setOperationParameter(operationId);
		String project = readOperationParameterValue("PROJECT");
		
		if(project != null && !(project.trim().equals(""))){
			String detail = getProjectDetail(project);
			
			toReturn ="<b>" + project +"</b><br>";
			toReturn += "<textarea>"+ detail +"</textarea>";
		}
		return toReturn;
	}*/
	
}
