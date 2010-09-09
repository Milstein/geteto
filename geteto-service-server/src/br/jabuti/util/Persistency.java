package br.jabuti.util;

import java.io.*;
import java.util.zip.*;
import java.util.*;

public class Persistency
{
	private static String persistencyLabel = "JabutiPersistency";
	private static File zFile = null;
	private static int cont = 0;
	
	static synchronized public void init(String path)
		throws ZipException, IOException
	{
		if ( zFile == null)
		{
			zFile = new File(path);
			zFile.mkdir();
			zFile.deleteOnExit();
			cont = 0;
		}
	} 
	
	static synchronized public void init()
		throws ZipException, IOException
	{
		if ( zFile == null)
		{
			zFile = File.createTempFile(persistencyLabel, "");
			zFile.delete();
			zFile.mkdir();
			cont = 0;
			zFile.deleteOnExit();
			System.out.println(zFile);
		}
	}
	

	static synchronized public String add(Object x)
	{
		try
		{
	        String l = nextLabel();
			File f = new File(zFile, l);
			f.deleteOnExit();
	        FileOutputStream fos = new FileOutputStream(f);
	        ObjectOutputStream oos = new ObjectOutputStream(fos);
	        oos.writeObject(x);
	        oos.close();
	        return l;
	     }
	     catch (Exception e)
	     {
	     	return null;
	     }
    }
    
    static synchronized public Object get(String x)
		throws FileNotFoundException, IOException, ClassNotFoundException
    {
		File f = new File(zFile, x);
        FileInputStream fis = new FileInputStream(f);
        ObjectInputStream ois = new ObjectInputStream(fis);
        return ois.readObject();
    }
    	

	private static String nextLabel()
	{
		return persistencyLabel + (cont++);
	}
	
	static synchronized public void close()
		throws ZipException, IOException
	{
		if ( zFile != null)
		{
//			zFile.delete();
			zFile = null;
		}
	}
	
	
	
	static public void main(String[] args)
		throws Exception
	{
		init();
		Vector v = new Vector();
		v.add(add(new Integer(10)));
		v.add(add(new Integer(11)));
		v.add(add(new Integer(13)));
		v.add(add(new Integer(14)));
		v.add(add(new Integer(15)));
		v.add(add(new Integer(16)));
		
		for (int i = 0;i < v.size(); i++)
		{
			Object o = get((String)v.elementAt(i));
			System.out.println(o + " " + o.getClass());
		}
	}
}