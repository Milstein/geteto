package br.jabuti.webservices;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Properties;

import javax.activation.DataHandler;

import br.icmc.usp.jabuti.service.persistence.Dao;
import br.icmc.usp.jabuti.service.persistence.JabutiServiceProject;

public class VerifingData {
	
	public boolean existProject(String id, Properties p)
	{
		Dao dao = new Dao(p);
		JabutiServiceProject jsp = dao.get(id);
		if(jsp == null)
			return false;
		return true;
	}
	
	public boolean isProjectInstrumented(String id)
	{
		//to do
		
		return true;
	}	
	
	public boolean isThereClassInFile(String classname, File f)
	{
		//to do
		
		return true;
	}
	
	public static File saveTempFile(DataHandler projectFile)
	{
		File f = null;
	    try {
	    	f = File.createTempFile("JabutiServiceTempFile" + String.valueOf(System.nanoTime()), ".tmp");
	    	FileOutputStream fos = new FileOutputStream(f);
	    	projectFile.writeTo(fos);
	    	fos.flush();
	    	fos.close(); 	    	
	    }
	    catch (Exception e) {
			e.printStackTrace();
		}
		return f;
	}
	
	public static void removeDir(File dir) {
        File[] files = dir.listFiles();

        for(int i = 0; i<files.length; i++) {
			if(files[i].isDirectory()) {
				removeDir(files[i]);
			}
			files[i].delete();
        }
}

}
