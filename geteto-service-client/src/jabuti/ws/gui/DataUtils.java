package jabuti.ws.gui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.StringTokenizer;

import br.icmc.usp.jabuti.service.JaBUTiService1_0Stub.JabutiServiceProject;

public class DataUtils {

	static HashMap<String, String> confFile = null;
	private static HashMap<String, String> jbtProjects;
	
	private static HashMap<String, String> getConfFile(){
		if (confFile == null){
			confFile = new HashMap<String, String>();
			jbtProjects = new HashMap<String, String>();
			init();
		}
		return confFile;
	}
	
	
	private static String getValue(String key){
		return confFile.get(key);
	}
	
	
	private static void init(){
		FileReader reader;
		try {
			reader = new FileReader(new File("./jabuti-gui.properties"));
	        BufferedReader in = new BufferedReader(reader);
	            String str;
	            while (in.ready()) {
	                str = in.readLine();               
	                confFile.put(str.replaceAll("=.*",""),str.replaceAll(".*=", ""));
	            }
	            in.close();
		}
		catch (IOException e) {
		}
	}
	
	
	public static String getProjectsDir() { 
		getConfFile();
	    return getValue("JABUTI_PERSISTENCE_HOME");
	}
	
	public static String getEndPoint(){
		getConfFile();
		 return getValue("END_POINT");
	}
	
	public static void loadProjects(){
		getConfFile();
		JabutiServiceProject[] projects = Script.getProjects(getEndPoint());
		
		for(JabutiServiceProject project : projects)
			jbtProjects.put(project.getName(), project.getProjid());
	}
	
	public static Object[] getProjectNames(){
		return jbtProjects.keySet().toArray();
	}
	
	public static String getProjectPath(String name){
		return getProjectsDir() + "/" + jbtProjects.get(name);
	}
	
	public static String getProjectId(String name){
		return jbtProjects.get(name);
	}
}
