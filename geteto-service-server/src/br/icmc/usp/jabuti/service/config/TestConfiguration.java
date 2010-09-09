package br.icmc.usp.jabuti.service.config;

import java.io.File;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import org.apache.bcel.util.ClassPath;

public class TestConfiguration {
	
	public static void test(Properties props) {
		
		testDatabase(props.getProperty("db.url"), props.getProperty("db.user"), props.getProperty("db.password"));
		
		testPersistenceDir(props.getProperty("JABUTI_PERSISTENCE_HOME"));
		
		testTomcatProject(props.getProperty("JABUTI_TOMCAT_PROJECT_HOME"));
	}
	
	private static void testDatabase(String url, String user, String password) {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection CONN = DriverManager.getConnection(url + "?" + "user=" + user + "&password=" + password);            
            
            Statement st = CONN.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM project");
            
            rs.close();
            st.close();
            CONN.close();
            
            System.out.println("Database connection: OK");
        } 
        catch (Exception ex) {
        	System.out.println("Error: Database connection");
        	ex.printStackTrace();
        }
		
	}
	
	private static void testPersistenceDir(String dir) {
		if(!dir.endsWith("/"))
			dir += "/";
		
		File directory = new File(dir);
		if(!directory.isDirectory()) {
			System.out.println("Error: Persistence directory does not exist");
		}
		else
			try {
				File file = new File(dir + "test.txt");
				PrintWriter pw = new PrintWriter(file);
				pw.write("testing...");
				pw.close();
				
				file.delete();
				
				System.out.println("Persistence directory: OK");
			}
			catch(Exception ex) {
	        	System.out.println("Error: Persistence directory - no permission to read/write");
	        	ex.printStackTrace();			
			}
	}
	
	private static void testTomcatProject(String dir) {
		if(!dir.endsWith("/"))
			dir += "/";
		
		try {
			String projTomcatPath = dir + "WEB-INF/classes";
			ClassPath cp = new ClassPath(projTomcatPath);			
			byte[] b = cp.getBytes("br.jabuti.probe.ProbedNode", ".class");			
			if(b != null)
				System.out.println("Tomcat project directory: OK");
			else
				System.out.println("Error: Tomcat project directory - It can't load the classes");
		}
		catch(Exception ex) {
        	System.out.println("Error: Tomcat project directory");
        	ex.printStackTrace();						
		}
	}	
}
