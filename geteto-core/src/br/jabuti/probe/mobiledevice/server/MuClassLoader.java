package br.jabuti.probe.mobiledevice.server;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Hashtable;

public class MuClassLoader extends ClassLoader
{
	protected ClassSpace classSpace;

	protected MuServer server;
	
	protected String dynLinkSource;

	protected MuClassLoader(MuServer aServer)
	{
		super();
		ClassSpace JdecGenerated7 = new ClassSpace();
		this.classSpace = JdecGenerated7;
		this.server = null;
		this.dynLinkSource = null;
		this.server = aServer;
		return;
	}

	MuServer getServer()
	{
		return this.server;
	}

	public ClassSpace getClassSpace()
	{
		return this.classSpace;
	}

	public static MuClassLoader getCurrent()
	{
		ClassLoader ld = null;
		MuClassLoader l = null;

		l = (MuClassLoader) MuServer.loaders.get(Thread.currentThread());
		if (l == null) {
			ld = Thread.currentThread().getClass().getClassLoader();
			if ((ld instanceof MuClassLoader) != true) {
				l = MuServer.defaultLoader;

			} else {
				l = (MuClassLoader) ld;

			}
		}
		return l;
	}

	public static MuClassLoader getClassLoader(Class c)
	{
		ClassLoader loader = null;
		MuClassLoader ret = null;

		loader = c.getClassLoader();
		if ((loader instanceof MuClassLoader) != true) {
			ret = MuServer.defaultLoader;
		} else {
			ret = (MuClassLoader) loader;
		}
		return ret;
	}
	
	public synchronized Class loadClass(String name, boolean resolve) throws ClassNotFoundException
	{
		Class c= null;
		ClassNotFoundException Var_12= null;
		DataInputStream is= null;
		InetAddress dest= null;
		Socket socket= null;
		String id= null;
        
		byte[] byteCode= null;
		int length= 0;
		int port= 0;
		byteCode=(byte [])null;
    
		if (server == null || server.isUbiquitous(name) != false) {
			c = getParent().loadClass(name);
			if (c == null) {
				throw new ClassNotFoundException();
			}
		} else {
			c = classSpace.getClass(name);
			if (c!=  null) {
				StringBuffer JdecGenerated72 = new StringBuffer(String.valueOf(name));
			} else {
				try {
					byteCode =this.classSpace.getClassByteCode(name);
				} catch (IOException ex) {
					throw new ClassNotFoundException();
				}
        	
				if (byteCode != null) {
					c = defineClass(name, byteCode, 0, byteCode.length);
					classSpace.putClass(name, c);
				} else {
					c = server.sharedSpace.getClass(name);
					if (c == null) {
						if (dynLinkSource !=  null) {
							is = null;
							socket = null;
							try {
								dest = MuServer.parseHost(this.dynLinkSource);
								port = MuServer.parsePort(this.dynLinkSource);
								if (port == -1) {
									port = 1968;
								}
								socket = new Socket(dest, port);
								Header header = new Header(2, name, server.isCompressionOn());
								header.ship(socket);
								is = new DataInputStream(socket.getInputStream());
								length = is.readInt();
								if (length == -1) {
									throw new ClassNotFoundException(new StringBuffer("The bytecode for ").append(name).append(" cannot be retrieved").toString());
								}
        					
								byteCode = new byte[length];;
								is.readFully(byteCode);
								if (byteCode!=  null) {
									c = defineClass(name, byteCode, 0, byteCode.length);
									classSpace.putClass(name, c);
								} else {
									throw new ClassNotFoundException();
								}
							} catch (IOException e) {
								throw new ClassNotFoundException(e.toString());
							} finally {
								try {
									is.close();
									socket.close();
								} catch(IOException e) {
								}
							}
						}
					} else {
						throw new ClassNotFoundException();
					}
				}
			}
			if (resolve != false) {
				resolveClass(c);
			}
		}
		return c;
	}

}