package br.jabuti.runner.ws;

import java.io.File;
import java.io.FileOutputStream;

import javax.activation.DataHandler;

public class VerifingData {
	public boolean existProject(String id)
	{
		//to do
		
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
