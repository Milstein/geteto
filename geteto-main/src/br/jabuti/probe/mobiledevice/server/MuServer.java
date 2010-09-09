package br.jabuti.probe.mobiledevice.server;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.Vector;

public class MuServer extends Thread
{
	final static String ERRMSG = "Impossible to retrieve the bytecode for ";

	private static final String builtinUbiPackages = "java.* javax.* br.jabuti.probe.mobiledevice.server.*";
	
	protected static Properties defaultProperties;
	
	public Properties properties;
	
	private int port;
	
	private long timeout;
	
	private boolean compressionON;
	
	private boolean errorsON;
	
	private boolean debugON;
	
	private boolean messagesON;
	
	protected MuClassLoader incomingLoader;
	
	protected static MuClassLoader defaultLoader;
	
	protected ClassSpace sharedSpace;
	
	protected static Hashtable loaders;
	
	private Thread serverThread;
	
	private Vector ubiquitousPackagePrefixes;
	
	private Vector ubiquitousPackages;
	
	private Vector ubiquitousClasses;
	
	static String[] classpath;

	public MuServer()
	{
		super();
		Properties JdecGenerated7 = new Properties(MuServer.defaultProperties);
		this.properties = JdecGenerated7;
		this.port = 1968;
		this.timeout = 30000L;
		this.compressionON = false;
		this.errorsON = true;
		this.debugON = false;
		this.messagesON = true;
		this.incomingLoader = null;
		this.sharedSpace = MuServer.defaultLoader.getClassSpace();
		this.serverThread = null;
		Vector JdecGenerated75 = new Vector(11, 1);
		this.ubiquitousPackagePrefixes = JdecGenerated75;
		Vector JdecGenerated89 = new Vector(11, 1);
		this.ubiquitousPackages = JdecGenerated89;
		Vector JdecGenerated103 = new Vector(11, 1);
		this.ubiquitousClasses = JdecGenerated103;
		this.ubiquitousPackagePrefixes.addElement("java.");
		this.ubiquitousPackagePrefixes.addElement("javax.");
		this.ubiquitousPackagePrefixes.addElement("sun.");
		this.ubiquitousPackagePrefixes.addElement("mucode.");
		this.loadPropertyVars();
		return;

	}

	static // [Static Initializer]
	{
		StringTokenizer st = null;
		Vector cp = null;

		Properties JdecGenerated2 = new Properties();
		MuServer.defaultProperties = JdecGenerated2;
		MuServer.defaultProperties.put("messages", String.valueOf(true));
		MuServer.defaultProperties.put("errors", String.valueOf(true));
		MuServer.defaultProperties.put("debug", String.valueOf(false));
		MuServer.defaultProperties.put("compression", String.valueOf(false));
		MuServer.defaultProperties.put("port", String.valueOf(1968));
		MuServer.defaultProperties.put("timeout", String.valueOf(30000L));
		MuServer.defaultProperties.put("ubiclasses", "");
		MuServer.defaultProperties.put("ubipackages", "java.* javax.* mucode.*");
		MuClassLoader JdecGenerated116 = new MuClassLoader(null);
		MuServer.defaultLoader = JdecGenerated116;
		Hashtable JdecGenerated127 = new Hashtable(100);
		MuServer.loaders = JdecGenerated127;
		Vector JdecGenerated139 = new Vector();
		cp = JdecGenerated139;
		StringTokenizer JdecGenerated147 = new StringTokenizer(System.getProperty("java.class.path"), System
				.getProperty("path.separator"));
		st = JdecGenerated147;
		while (true)

		{

			if (!st.hasMoreTokens()) {
				break;

			}

			StringBuffer JdecGenerated169 = new StringBuffer(String.valueOf(st.nextToken()));
			cp.addElement(JdecGenerated169.append(File.separatorChar).toString());

		}
		String[] JdecGeneratedVar206 = new String[cp.size()];
		classpath = JdecGeneratedVar206;
		cp.copyInto(classpath);

	}

	// CLASS: mucode.MuServer:
	public void setProperty(String key, String value)
	{
		this.properties.put(key, value);
		this.loadPropertyVars();
		return;

	}

	// CLASS: mucode.MuServer:
	public void loadProperties(String filename) throws FileNotFoundException, IOException
	{

		FileInputStream JdecGenerated6 = new FileInputStream(new File(filename));
		this.properties.load(JdecGenerated6);
		this.properties.list(System.out);
		this.loadPropertyVars();
		return;

	}

	// CLASS: mucode.MuServer:
	protected void loadPropertyVars()
	{
		String packagename = null;
		StringTokenizer st = null;

		this.debugON = Boolean.valueOf(this.properties.getProperty("debug")).booleanValue();
		this.messagesON = Boolean.valueOf(this.properties.getProperty("messages")).booleanValue();
		this.errorsON = Boolean.valueOf(this.properties.getProperty("errors")).booleanValue();
		this.compressionON = Boolean.valueOf(this.properties.getProperty("compression")).booleanValue();
		this.port = Integer.valueOf(this.properties.getProperty("port")).intValue();
		this.timeout = Long.valueOf(this.properties.getProperty("timeout")).intValue();
		StringTokenizer JdecGenerated117 = new StringTokenizer(this.properties.getProperty("ubiclasses"), " ");
		st = JdecGenerated117;
		while (true)

		{

			if (!st.hasMoreTokens()) {
				break;

			}

			this.ubiquitousClasses.addElement(st.nextToken());

		}
		StringTokenizer JdecGenerated158 = new StringTokenizer(this.properties.getProperty("ubipackages"),
				" ");
		st = JdecGenerated158;
		packagename = null;
		while (true)

		{

			if (!st.hasMoreTokens()) {
				break;

			}

			packagename = st.nextToken();
			if (packagename.endsWith(".*") != false) {
				this.ubiquitousPackagePrefixes.addElement(packagename
						.substring(0, (packagename.length() - 1)));

			} else {
				this.ubiquitousPackages.addElement(packagename);

			}

		}
		return;

	}

	// CLASS: mucode.MuServer:
	public int getPort()
	{
		return this.port;

	}

	// CLASS: mucode.MuServer:
	public long getTimeout()
	{
		return this.timeout;

	}

	// CLASS: mucode.MuServer:
	public boolean isCompressionOn()
	{
		return this.compressionON;

	}

	// CLASS: mucode.MuServer:
	public boolean isDebugOn()
	{
		return this.debugON;

	}

	// CLASS: mucode.MuServer:
	public boolean isErrorsOn()
	{
		return this.errorsON;

	}

	// CLASS: mucode.MuServer:
	public boolean isMessagesOn()
	{
		return this.messagesON;

	}

	// CLASS: mucode.MuServer:
	public void addUbiquitousPackage(String packageName)
	{

		StringBuffer JdecGenerated5 = new StringBuffer(String.valueOf(this.properties
				.getProperty("ubipackages")));
		this.setProperty("ubipackages", JdecGenerated5.append(" ").append(packageName).toString());
		insertUbiquitousPackage(packageName);
		return;

	}

	// CLASS: mucode.MuServer:
	private void insertUbiquitousPackage(String packageName)
	{
		if (packageName.endsWith(".*") != false) {
			this.ubiquitousPackagePrefixes.addElement(packageName.substring(0, (packageName.length() - 1)));
			return;

		} else {
			StringBuffer JdecGenerated37 = new StringBuffer(String.valueOf(packageName));
			this.ubiquitousPackages.addElement(JdecGenerated37.append(".").toString());
			return;
		}

	}

	// CLASS: mucode.MuServer:
	public void addUbiquitousClass(String className)
	{

		StringBuffer JdecGenerated5 = new StringBuffer(String.valueOf(this.properties
				.getProperty("ubiclasses")));
		this.setProperty("ubiclasses", JdecGenerated5.append(" ").append(className).toString());
		this.ubiquitousClasses.addElement(className);
		return;

	}

	public boolean isUbiquitous(String className)
	{
		Enumeration e= null;
		String root= null;
		boolean inUClasses= false;
		boolean inUPackagePrefixes= false;
		boolean inUPackages= false;
		inUPackagePrefixes=false;
		inUPackages=false;
		inUClasses=false;
		e=null;
		root=null;
		e =this.ubiquitousPackagePrefixes.elements();
		while(true) {
			root =(String)e.nextElement();
			inUPackagePrefixes=className.startsWith(root);
			if(inUPackagePrefixes!=false || !e.hasMoreElements()) {
				break;
			}
			
			if(inUPackagePrefixes==false) {
				e = this.ubiquitousPackages.elements();
				while (true) {
					root =(String) e.nextElement();
					inUPackages = (className.startsWith(root) != false && className.indexOf(".", root.length()) == -1);
					if (inUPackages!=false || !e.hasMoreElements()) {
						break;
					}
				}
			}
			
			if (! inUPackagePrefixes &&  ! inUPackages) {
				e = this.ubiquitousClasses.elements();
				while (true) {
					root =(String) e.nextElement();
					inUClasses = className.equals(root);
					if (inUClasses != false) {
						break;
					}
					if (! inUClasses) {
						if(!e.hasMoreElements())
							break;
					}
				}
			}
       
			if (! inUPackagePrefixes && ! inUPackages  && ! inUClasses) {
				return false;
			}
		}
		
		return true;
	}

	// CLASS: mucode.MuServer:
	public Group createGroup(String root, String handler)
	{
		if (handler == null) {
			IllegalArgumentException JdecGenerated6 = new IllegalArgumentException(
					"The handler class of a group cannot be null");
			throw JdecGenerated6;

		}
		Group JdecGenerated17 = new Group(root, handler, this);
		return JdecGenerated17;

	}

	// CLASS: mucode.MuServer:
	public ClassSpace getSharedClassSpace()
	{
		return this.sharedSpace;

	}

	// CLASS: mucode.MuServer:
	public ClassSpace getPrivateClassSpace()
	{
		return ClassSpace.getCurrent();

	}

	// CLASS: mucode.MuServer:
	public void boot()
	{
		this.setName("MuServer");
		this.start();
		return;

	}

	// CLASS: mucode.MuServer:
	public void shutDown()
	{
		this.stop();
		return;

	}

	// CLASS: mucode.MuServer:
	public static final MuServer getServer()
	{
		MuClassLoader l = null;
		MuServer r = null;
		r = null;
		l = MuClassLoader.getCurrent();
		if (l != null) {
			r = l.getServer();

		}
		return r;

	}

	// CLASS: mucode.MuServer:
	public static final MuServer getServer(Thread t)
	{
		MuClassLoader l = null;
		MuServer r = null;
		r = null;
		l = (MuClassLoader) MuServer.loaders.get(t);
		if (l != null) {
			r = l.getServer();

		}
		return r;

	}

	// CLASS: mucode.MuServer:
	public static final MuServer getServer(Object obj)
	{
		ClassLoader classLoader = null;
		MuServer ret = null;
		classLoader = obj.getClass().getClassLoader();
		if ((classLoader instanceof MuClassLoader) != false) {
			ret = ((MuClassLoader) classLoader).getServer();

		}
		return ret;
	}

	public void run()
	{
		DataOutputStream os = null;
		Group group = null;
		GroupHandler handler = null;
		Header header = null;
		ServerSocket servSocket = null;
		Socket clientSocket = null;
		Thread t = null;
		boolean problem = false;
		byte[] requestedBC = null;
		long start= 0L;

		try {
			servSocket = new ServerSocket(port);
		} catch (IOException  e) {
			StringBuffer msg = new StringBuffer("Cannot listen on port ");
			Err(msg.append(this.port).append(". Halting.").toString());
			System.exit(0);
		}
		
		StringBuffer msg = new StringBuffer("MuServer activated on port ");
		M(msg.append(this.port).toString());
		while (true) {
			try {
				clientSocket = servSocket.accept();
				D("Connection accepted. Reconstructing the header...");
				start = System.currentTimeMillis();
				header = new Header(clientSocket);
			} catch (IOException ioe) {
				msg = new StringBuffer("Accept failed on port ");
				Err(msg.append(this.port).toString(), ioe);
			}
			
			switch (header.dataType) {
				case 2:
					msg = new StringBuffer("The connection contains a request for dynamic linking of class: ");
					D(msg.append(header.className).toString());
					os = null;
					try {
						os = new DataOutputStream(clientSocket.getOutputStream());
						msg = new StringBuffer("Contents of shared class space: ");
						D(msg.append(getSharedClassSpace().toString()).toString());
						requestedBC = getSharedClassSpace().getClassByteCode(header.className);
						if (requestedBC== null) {
							msg = new StringBuffer("Bytecode for ");
							this.D(msg.append(header.className).append(" doesn\'t exist").toString());
							os.write(-1);
						} else {
							os.writeInt(requestedBC.length);
							os.write(requestedBC);
							msg = new StringBuffer("Bytecode for ");
							D(msg.append(header.className).append(" (").append(requestedBC.length).append(" bytes) sent.").toString());
						}
					} catch(IOException ioe2) {
						this.Err("I/O errors during remote dynamic linking.", ioe2);
					} finally {
						try {
							os.flush();
							os.close();
						} catch(IOException ioe3) {
						}
					}
					break;
				case 1:
					try {
						msg = new StringBuffer("The connection contains a group. Reconstructing ... ");
						D(msg.append(header.className).toString());
						group = new Group(clientSocket,header.compressedStream, this);
						if (! group.isSynchronousTransfer()) {
							clientSocket.close();
						}
					} catch (IOException ioe2) {
						Err("I/O errors during group deserialization.", ioe2);
						problem = true;
					} catch (ClassNotFoundException cnfe) {
						Err("Problems during class loading.", cnfe);
						problem = true;
					} catch(DuplicateClassException dce) {
						Err("The group is not allowed to overwrite a class in the class space.", dce);
						problem = true;
					}
					
					if (! problem) {
						try {
							handler = (GroupHandler) group.getHandlerClass().newInstance();
							D("Handler created.");
						} catch (IllegalAccessException iae) {
							Err("The handler class must public!.", iae);
						} catch (InstantiationException ie) {
							Err("The handler class cannot be an interface or an abstract class.", ie);
						}
						
						try {
							D("Unpacking the group.");
							t = handler.unpack(group);
							this.D("Group unpacked.");
						} catch (MuCodeException mce) {
							msg = new StringBuffer("Problems during the unpack() of a group: ");
							Err(msg.append(mce).toString(), mce);
						}
						
						if (t != null) {
							if (! MuServer.loaders.containsKey(t)) {
								MuServer.loaders.put(t,this.incomingLoader);
							}
							t.start();
							msg = new StringBuffer("Time to activate the group: ");
							D(msg.append((System.currentTimeMillis()-start)).toString());
							msg = new StringBuffer("Group thread (");
							D(msg.append(t.getName()).append("spawned.").toString());
						}
					}
					break;
					
				default:
					msg = new StringBuffer("Illegal data in group header:");
					Err(msg.append(header.dataType).toString());
					incomingLoader = null;
					cleanup();
					Thread.yield();
			}
		}
	}

	protected void cleanup()
	{
		Enumeration keys = null;
		Thread keyObj = null;
		keys = MuServer.loaders.keys();
		while (true)

		{

			if (!keys.hasMoreElements()) {
				break;

			}

			keyObj = (Thread) keys.nextElement();
			if (!keyObj.isAlive()) {
				MuServer.loaders.remove(keyObj);
				StringBuffer JdecGenerated38 = new StringBuffer("Garbage collected a class loader: ");
				this.D(JdecGenerated38.append(MuServer.loaders.size()).append(" left.").toString());

			}

		}
		return;

	}

	// CLASS: mucode.MuServer:
	void M(String msg)
	{
		if (this.messagesON != false) {
			StringBuffer JdecGenerated12 = new StringBuffer("mucode: ");
			System.out.println(JdecGenerated12.append(msg).toString());
			return;
		}

	}

	// CLASS: mucode.MuServer:
	void D(String msg)
	{
		if (this.debugON != false) {
			this.M(msg);
			return;
		}

	}

	// CLASS: mucode.MuServer:
	void Err(String msg)
	{
		if (this.errorsON != false) {
			StringBuffer JdecGenerated12 = new StringBuffer("mucode: ");
			System.err.println(JdecGenerated12.append(msg).toString());
			return;
		}

	}

	// CLASS: mucode.MuServer:
	void D(String msg, Throwable e)
	{
		this.D(msg);
		e.printStackTrace();
		return;

	}

	// CLASS: mucode.MuServer:
	void Err(String msg, Throwable e)
	{
		this.Err(msg);
		if (this.debugON != false) {
			e.printStackTrace();
			return;
		}

	}

	// CLASS: mucode.MuServer:
	static InetAddress parseHost(String destination) throws UnknownHostException
	{
		String dest = null;
		StringTokenizer st = null;

		StringTokenizer JdecGenerated2 = new StringTokenizer(destination, ":");
		st = JdecGenerated2;
		dest = null;
		if (st.countTokens() == 1) {
			dest = destination;

		} else {
			dest = (String) st.nextElement();

		}
		return InetAddress.getByName(dest);

	}

	// CLASS: mucode.MuServer:
	static int parsePort(String destination) throws UnknownHostException
	{
		String dummy = null;
		StringTokenizer st = null;
		int port = 0;

		StringTokenizer JdecGenerated2 = new StringTokenizer(destination, ":");
		st = JdecGenerated2;
		port = -1;
		if (st.countTokens() > 1) {
			dummy = (String) st.nextElement();
			port = Integer.valueOf((String) st.nextElement()).intValue();

		}
		return port;

	}

	// CLASS: mucode.MuServer:
	protected MuClassLoader createClassLoader()
	{

		MuClassLoader JdecGenerated3 = new MuClassLoader(this);
		this.incomingLoader = JdecGenerated3;
		return this.incomingLoader;

	}

}
