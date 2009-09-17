/*  Copyright 2003  Auri Marcelo Rizzo Vicenzi, Marcio Eduardo Delamaro, 			    Jose Carlos Maldonado

    This file is part of Jabuti.

    Jabuti is free software: you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as 
    published by the Free Software Foundation, either version 3 of the      
    License, or (at your option) any later version.

    Jabuti is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU Lesser General Public License
    along with Jabuti.  If not, see <http://www.gnu.org/licenses/>.
*/


package br.jabuti.device.j2me;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import javax.microedition.io.Connector;
import javax.microedition.io.SocketConnection;
import javax.microedition.io.file.FileConnection;



/** <p>This class implements a class to create a regular file 
 * to store the registered execution sequence. At 
 * itinialization it registers an object to be notified when the 
 * program exits. At that point, that object call 
 * {@link DeviceProber#dump}.
 */
public class DeviceProber  {
    static protected Hashtable threadsAndProbs;
    static private PrintStream fp, fpServer;
	static private boolean useFile;
	static private FileConnection fconn; 
	static private SocketConnection sconn;  
	static private String ID, fileName;
	static private int memThreshold = 0;
	static private boolean keepAlive;
	static private String server;
	static private boolean inicializado;
	static private OutputStream fosServer, fosFile;
	
	static public void init(String fName, String sv,
							 int memTres, boolean kA, String id)
	{
		System.out.println("Init " + Thread.currentThread());
		Runtime rt = Runtime.getRuntime();
		memThreshold = memTres;
		fileName = fName;
		idSent = false;
		long freeMem = rt.freeMemory();
		System.out.println("Free memory: " + freeMem);
		System.out.println("Threshold memory: " + memThreshold);
		threadsAndProbs = new Hashtable();
		ID = id;
		server = sv;
		sconn = null;
		fconn = null;
		fp = null;
		fpServer = null;
		useFile = false; 
		if (fileName != null)
		{
			if ( "__STDOUT__".equals(fileName) )
			{
				fp = System.out;
				useFile = true;
			}
			else
			{
				String s = "file:///" + fileName;
				try {
				     fconn = (FileConnection) Connector.open(s);
				     // If no exception is thrown, then the URI is valid, but the file may or may not exist.
				     if ( fconn.exists() )
				     {
				         fconn.delete();
				     }
				     fconn.create();  // create the file if it doesn't exist
				    fosFile = fconn.openOutputStream();
				    fp = new PrintStream(fosFile);
				    useFile = true;
				 }
				 catch (Exception ioe) {
					 ioe.printStackTrace();
				 }				
			}
		}
		keepAlive = useFile ? false: kA;
		openSocket();
		if ( ! keepAlive ) 
		{
			closeSocket();
		}
		inicializado = true;
	}
	
	static private void openSocket()
	{
		Thread t = new Thread() {
		
		public void run()
		{
			System.out.println("Open socket: "+ Thread.currentThread());
			if ( server != null )
			{
				String s = "socket://" + server;
				System.out.println("Connection to: " + s);
				try {
				     sconn = (SocketConnection) Connector.open(s);
				     System.out.println("Success ");
				     fosServer = sconn.openOutputStream();
				     if ( ! useFile )
				     {
					     fpServer = new PrintStream(fosServer);
				    	 fp = fpServer;
				     }
				 }
				 catch (Exception ioe) {
				 	System.out.println("Fail to open connection.");
					 ioe.printStackTrace();
				 }
			}
		}
		};
		t.start();
		int k = 0; boolean goon = true;
		while (goon && t.isAlive())
		{
//			System.out.println("Tentando " + k);
			try {
				Thread.sleep(200);
				if ( k >= 100 )
				{
					System.out.println("Could not open socket connection to " + server);
//					t.interrupt();
					goon = false;
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
				break;
			}
			k++;
		}
			
/*		while (t.isAlive())
			try {
			Thread.yield();
			t.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} */
   	 	sendID();
	}
	
	static private void closeSocket()
	{
		if ( sconn != null )
		{
			if ( ! useFile ) fp.close();
			try {
				fosServer.close();
				sconn.close();
			} catch (IOException e) {
				;
			}
			if ( ! useFile) 
				idSent = false;
		}
	}
	
	/** This method sends the program id to the server
	 * 
	 *
	 */
	
	static private boolean idSent = false;
	static private void sendID()
	{
		System.out.println("Chamado Sent ID");

		if ( fp != null && ! idSent)
		{
			fp.println("Id: " + ID);
			System.out.println("Sent ID");
		}
		idSent = true;
	}
	
	/** This method should be called when the test is suspended.
	 *  It closes the connections and send the data to the server,
	 *  if necessary
	 */
	static public void finish()
	{
		byte[] b = new byte[512];
		if ( ! keepAlive )
		{
			openSocket();
		}
		dump();
		endTcase();
		if ( useFile && sconn != null )
		{ // fp se refere a um arquivo.
			System.out.println("Comecar a escrever para server.");
			try {				
				InputStream is = fconn.openInputStream();
		//		OutputStream os = sconn.openOutputStream();
				int k;
				do
				{
					k = is.read(b, 0, b.length);
					System.out.println("Leu " + k);
					if ( k > 0 )
						fosServer.write(b, 0, k);
					System.out.println("Escreveu " + k);
				} while ( k >= 0 );
				fp.close();
				is.close();
				fosFile.close();
				fosServer.close();
//				fconn.delete();
				fconn.close();
				sconn.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		else
		{
			try {
				if (fp != null && fp != System.out)
					fp.close();
				if (sconn != null )
					sconn.close();
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
		}
		fp = null;
		inicializado = false;
	}

	/** This method should be called when the test is ended.
	 *  It sends a string indicating the end of the test case
	 *  It closes the connections and send the data to the server,
	 *  if necessary
	 */
	static public void endTcase()
	{
		if ( fp != null )
			fp.println("EndTestCase " + ID);
	}
	
    /** This method registers the execution of a given node */
    static synchronized public void probe(Object o, 
    							String clazz, 
    							int metodo, 
    							long nest,
    							Object n) 
    	{    		
    	if ( ! inicializado ) return;
    	if ( memThreshold > 0 ) // threshold == 0 ==> armazena sempre
    	{
    		long freeMem = Runtime.getRuntime().freeMemory();
    		System.out.println("free: " + freeMem);
    		System.out.println("threshold: " + memThreshold + " " + (freeMem <= memThreshold ));
    		if ( freeMem <= memThreshold )
    		{
    			if ( ! keepAlive ) openSocket();
    			dump();
    			if ( ! keepAlive ) closeSocket();
    		}
    	}
        Runnable tr = Thread.currentThread();
        String s = o == null ? "STATIC" : 
        						o.getClass().getName() + System.identityHashCode(o);
        ProbedNode pb = new ProbedNode(tr.toString(), s,
                clazz, metodo, "");
        Vector probedNodes;

        if (threadsAndProbs.containsKey(pb)) {
            probedNodes = (Vector) threadsAndProbs.get(pb);
        } else {
            probedNodes = new Vector();
            threadsAndProbs.put(pb, probedNodes);
        }
        probedNodes.addElement(nest + ":" + n);
        //System.out.println("Probed: " + threadsAndProbs.size());
    }
	
    /** This method registers the execution of a given node 
     of an static method */
    static public void probe(String clazz, int metodo, long nest, Object n) {
        probe(null, clazz, metodo, nest, n);
    }

    /** This method stores (for example, sending to a file) the 
     * registered execution up to that point */
    static synchronized public void dump() {
        if (fp == null) {
            return;
        }
        System.out.println("Dump: " + threadsAndProbs.size() + " " + fp);
        Enumeration en = threadsAndProbs.keys();

        while (en.hasMoreElements()) {
            ProbedNode tr = (ProbedNode) en.nextElement();

            dumpNodes(tr, (Vector) threadsAndProbs.get(tr));
        }
        // write a delimiter 
        fp.flush(); // Inseri um flush para descarregar o buffer.
		
        threadsAndProbs = new Hashtable();
    }


    synchronized static void dumpNodes(ProbedNode pbdNode, Vector probedNodes) {
        try {
            fp.println(pbdNode.threadCode);
//            System.out.println(pbdNode.threadCode);
            fp.println(pbdNode.objectCode);
  //          System.out.println(pbdNode.objectCode);
            fp.println(pbdNode.clazz);
    //        System.out.println(pbdNode.clazz);
            fp.println(pbdNode.metodo);
      //      System.out.println(pbdNode.metodo);
            Enumeration li = probedNodes.elements();

            while (li.hasMoreElements()) {
                Object o = li.nextElement();

                fp.println(o);
            }
            fp.println("-1");
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }
    
    static private long nestlevel = 0;
    
    static synchronized public long getNest()
    {
    	return nestlevel++;
    }

	
}

