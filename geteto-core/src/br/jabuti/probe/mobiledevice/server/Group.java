package br.jabuti.probe.mobiledevice.server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class Group
{

	/***
	 **Class Fields
	 ***/
	private int opCode;
	Serializable[] opPars;
	private String rootClassName;
	private String handlerClassName;
	Hashtable classes;
	Hashtable objects;
	private String dynLink;
	private boolean synch;
	protected String sender;
	private transient MuServer server;
	protected transient Class rootClassObj;
	protected transient Class handlerClassObj;

	// CLASS: mucode.Group:
	Group(MuServer aServer)
	{
		super();
		this.opCode = -1;
		this.opPars = null;
		this.rootClassName = "";
		this.handlerClassName = null;
		this.classes = null;
		this.objects = null;
		this.dynLink = null;
		this.synch = false;
		this.sender = null;
		this.server = null;
		this.rootClassObj = null;
		this.handlerClassObj = null;
		this.opCode = -1;
		this.opPars = null;
		this.rootClassName = "";
		this.handlerClassName = null;
		Hashtable JdecGenerated89 = new Hashtable(10);
		this.objects = JdecGenerated89;
		Hashtable JdecGenerated102 = new Hashtable(10);
		this.classes = JdecGenerated102;
		this.dynLink = null;
		this.synch = false;
		this.sender = null;
		this.rootClassObj = null;
		this.handlerClassObj = null;
		this.server = aServer;
		return;

	}

	// CLASS: mucode.Group:
	Group(String root, String handler, MuServer aServer)
	{
		this(aServer);
		this.setRoot(root);
		this.setHandler(handler);
		return;

	}

	// CLASS: mucode.Group:
	Group(Socket clientSocket, boolean compressionON, MuServer aServer) throws DuplicateClassException,
			ClassNotFoundException, IOException
	{
		super();
		Enumeration keys = null;
		GZIPInputStream gzos = null;
		MuClassLoader l = null;
		MuObjectInputStream in = null;
		String keyName = null;

		this.opCode = -1;
		this.opPars = null;
		this.rootClassName = "";
		this.handlerClassName = null;
		this.classes = null;
		this.objects = null;
		this.dynLink = null;
		this.synch = false;
		this.sender = null;
		this.server = null;
		this.rootClassObj = null;
		this.handlerClassObj = null;
		this.server = aServer;
		l = this.server.createClassLoader();
		server.incomingLoader = l;
		if (compressionON != false) {
			GZIPInputStream JdecGenerated94 = new GZIPInputStream(clientSocket.getInputStream());
			gzos = JdecGenerated94;
			MuObjectInputStream JdecGenerated107 = new MuObjectInputStream(gzos, l);
			in = JdecGenerated107;

		} else {
			MuObjectInputStream JdecGenerated123 = new MuObjectInputStream(clientSocket.getInputStream(), l);
			in = JdecGenerated123;

		}
		StringBuffer JdecGenerated142 = new StringBuffer("Input stream created, compression: ");
		this.server.D(JdecGenerated142.append(compressionON).toString());
		this.opCode = in.read();
		this.rootClassName = in.readUTF();
		this.handlerClassName = in.readUTF();
		StringBuffer JdecGenerated192 = new StringBuffer("Group info: root class: ");
		this.server.D(JdecGenerated192.append(this.rootClassName).append(", handler class: ").append(
				this.handlerClassName).toString());
		this.classes = (Hashtable) in.readObject();
		StringBuffer JdecGenerated242 = new StringBuffer("Group info: #classes: ");
		this.server.D(JdecGenerated242.append(this.classes.size()).toString());
		keys = this.classes.keys();
		while (true)

		{

			if (!keys.hasMoreElements()) {
				break;

			}

			keyName = (String) keys.nextElement();
			l.getClassSpace().putClassByteCode(keyName, (byte[]) this.classes.get(keyName));

		}
		keys = this.classes.keys();
		while (true)

		{

			if (!keys.hasMoreElements()) {
				break;

			}

			keyName = (String) keys.nextElement();
			l.loadClass(keyName);

		}
		this.server.D("All classes in the group have been loaded successfully.");
		StringBuffer JdecGenerated378 = new StringBuffer("Contents of the private class space: ");
		this.server.D(JdecGenerated378.append(l.getClassSpace().toString()).toString());
		if (this.server.isUbiquitous(this.rootClassName) != false) {
			this.rootClassObj = Class.forName(this.rootClassName);

		} else {
			this.rootClassObj = l.getClassSpace().getClass(this.rootClassName);

		}
		if (this.server.isUbiquitous(this.handlerClassName) != false) {
			this.handlerClassObj = Class.forName(this.handlerClassName);

		} else {
			this.handlerClassObj = l.getClassSpace().getClass(this.handlerClassName);

		}
		this.objects = (Hashtable) in.readObject();
		this.opPars = (Serializable[]) in.readObject();
		StringBuffer JdecGenerated520 = new StringBuffer("Group info: #objects: ");
		this.server.D(JdecGenerated520.append(this.objects.size()).toString());
		keys = this.objects.keys();
		while (true)

		{

			if (!keys.hasMoreElements()) {
				break;

			}

			keyName = (String) keys.nextElement();
			StringBuffer JdecGenerated573 = new StringBuffer("Key: ");
			this.server.D(JdecGenerated573.append(keyName).toString());

		}
		this.dynLink = in.readUTF();
		String JdecGenerated616 = new String();
		if (this.dynLink.equals(JdecGenerated616) != false) {
			this.dynLink = null;

		} else {
			l.dynLinkSource = this.dynLink;

		}
		this.synch = in.readBoolean();
		this.sender = in.readUTF();
		StringBuffer JdecGenerated668 = new StringBuffer("Group info: dynamic link: ");
		if (this.dynLink == null) {

		} else {

		}
		in.close();
		return;

	}

	// CLASS: mucode.Group:
	public MuServer getServer()
	{
		return this.server;

	}

	// CLASS: mucode.Group:
	public Class getRootClass()
	{
		return this.rootClassObj;

	}

	// CLASS: mucode.Group:
	public Class getHandlerClass()
	{
		return this.handlerClassObj;

	}

	// CLASS: mucode.Group:
	public String getRootName()
	{
		return this.rootClassName;

	}

	// CLASS: mucode.Group:
	public String getHandlerName()
	{
		return this.handlerClassName;

	}

	// CLASS: mucode.Group:
	public String getDynamicLinkSource()
	{
		return this.dynLink;

	}

	// CLASS: mucode.Group:
	public void setDynamicLinkSource(String MuServer)
	{

		this.dynLink = MuServer;
		return;

	}

	// CLASS: mucode.Group:
	public boolean isSynchronousTransfer()
	{
		return this.synch;

	}

	// CLASS: mucode.Group:
	public void setSynchronousTransfer(boolean isSynch)
	{

		this.synch = isSynch;
		return;

	}

	// CLASS: mucode.Group:
	public void setOperation(int opCode)
	{

		this.opCode = opCode;
		return;

	}

	// CLASS: mucode.Group:
	public int getOperation()
	{
		return this.opCode;

	}

	// CLASS: mucode.Group:
	public Object getObject(String key)
	{
		return this.objects.get(key);

	}

	// CLASS: mucode.Group:
	public Object addObject(String key, Object obj)
	{
		return this.objects.put(key, obj);

	}

	public void addClass(String className) throws ClassNotFoundException
	{
		if (!server.isUbiquitous(className)) {
			throw new ClassNotFoundException(new StringBuffer("Impossible to retrieve the bytecode for ")
					.append(className).toString());
		} else {
			StringBuffer JdecGenerated64 = new StringBuffer("Class not added to group (ubiquitous): ");
			this.server.D(JdecGenerated64.append(className).toString());
		}
	}

	public void addClass(Class c) throws ClassNotFoundException
	{
		String className = null;
		className = c.getName();
		if (!this.server.isUbiquitous(className)) {
			ClassNotFoundException JdecGenerated38 = new ClassNotFoundException(new StringBuffer(
						"Impossible to retrieve the bytecode for ").append(className).toString());
			throw JdecGenerated38;
		} else {
			StringBuffer JdecGenerated70 = new StringBuffer("Class not added to group (ubiquitous): ");
			this.server.D(JdecGenerated70.append(className).toString());
		}
	}

	// CLASS: mucode.Group:
	private void addClass(String className, byte[] bytecode)
	{
		StringBuffer JdecGenerated6 = new StringBuffer("Class added to group: ");
		this.server.D(JdecGenerated6.append(className).append(" (").append(bytecode.length).append(" bytes)")
				.toString());
		this.classes.put(className, bytecode);
	}

	// CLASS: mucode.Group:
	public void addClasses(String[] classNames) throws ClassNotFoundException
	{
		int i = 0;
		i = 0;
		while (true)

		{

			if (++i >= classNames.length) {
				break;

			}

			this.addClass(classNames[i]);

		}
		return;

	}

	// CLASS: mucode.Group:
	public void addClasses(Class[] classes) throws ClassNotFoundException
	{
		int i = 0;
		i = 0;
		while (true)

		{

			if (++i >= classes.length) {
				break;

			}

			this.addClass(classes[i]);

		}
		return;

	}

	// CLASS: mucode.Group:
	public String[] getClasses()
	{
		Enumeration keys = null;
		String[] ret = null;
		int i = 0;
		String[] JdecGeneratedVar9 = new String[this.classes.size()];
		ret = JdecGeneratedVar9;
		keys = this.classes.keys();
		i = 0;
		while (true)

		{

			if (++i >= ret.length) {
				break;

			}

			ret[i] = (String) keys.nextElement();

		}
		return ret;

	}

	// CLASS: mucode.Group:
	public MuClassLoader getClassLoader()
	{
		MuClassLoader ret = null;
		ret = this.server.incomingLoader;
		if (ret == null) {
			ret = MuServer.defaultLoader;

		}
		return ret;

	}

	// CLASS: mucode.Group:
	public ClassSpace getClassSpace()
	{
		return this.getClassLoader().getClassSpace();

	}

	// CLASS: mucode.Group:
	public String getSource()
	{
		return this.sender;

	}

	// CLASS: mucode.Group:
	public int ship(String destination) throws IOException, ClassNotFoundException
	{
		InetAddress dest = null;
		MuObjectInputStream is = null;
		ObjectOutputStream os = null;
		ServerSocket servSocket = null;
		Socket socket = null;

		int port = 0;
		socket = null;
		servSocket = null;
		os = null;
		is = null;
		dest = null;
		port = -1;
		dest = MuServer.parseHost(destination);
		port = MuServer.parsePort(destination);
		if (port == -1) {
			port = 1968;

		}
		StringBuffer JdecGenerated45 = new StringBuffer("Shipping a group to: ");
		this.server.D(JdecGenerated45.append(destination).toString());
		Socket JdecGenerated65 = new Socket(dest, port);
		socket = JdecGenerated65;
		Header JdecGenerated77 = new Header(1, new String(), this.server.isCompressionOn());
		JdecGenerated77.ship(socket);
		this.server.D("Header transmitted.");
		if (this.server.isCompressionOn() != false) {
			this.server.D("Compression enabled.");
			ObjectOutputStream JdecGenerated133 = new ObjectOutputStream(new GZIPOutputStream(socket
					.getOutputStream()));
			os = JdecGenerated133;

		} else {
			ObjectOutputStream JdecGenerated156 = new ObjectOutputStream(socket.getOutputStream());
			os = JdecGenerated156;

		}
		if (socket != null && os != null)

		{
			os.write(this.opCode);
			os.writeUTF(this.rootClassName);
			os.writeUTF(this.handlerClassName);
			os.writeObject(this.classes);
			os.writeObject(this.objects);
			os.writeObject(this.opPars);
			if (this.dynLink == null) {
				String JdecGenerated241 = new String();
				os.writeUTF(JdecGenerated241);

			} else {
				os.writeUTF(this.dynLink);

			}
			os.writeBoolean(this.synch);
			StringBuffer JdecGenerated273 = new StringBuffer(String.valueOf(socket.getLocalAddress()
					.getHostAddress()));
			this.sender = JdecGenerated273.append(":").append(this.server.getPort()).toString();
			os.writeUTF(this.sender);
			os.flush();
			os.close();
			if (!this.synch) {
				socket.close();

			}

		}
		return 0;

	}

	// CLASS: mucode.Group:
	void setRoot(String className)
	{
		if (className == null) {
			this.rootClassName = "";
			return;

		} else {
			this.rootClassName = className;
			return;
		}

	}

	// CLASS: mucode.Group:
	void setHandler(String className)
	{

		this.handlerClassName = className;
		return;

	}

}
