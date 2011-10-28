package br.jabuti.probe.mobiledevice.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;

class MuObjectInputStream extends ObjectInputStream
{
	private MuClassLoader loader;

	public MuObjectInputStream(InputStream in, MuClassLoader aLoader) throws IOException
	{
		super(in);
		this.loader = null;
		this.loader = aLoader;
		return;
	}

	public Class resolveClass(ObjectStreamClass v) throws IOException, ClassNotFoundException
	{
		Class c = null;
		c = null;
		try {
			c = loader.loadClass(v.getName(), true);
		} catch (ClassNotFoundException e) {
			c = Class.forName(v.getName());
			if (c == null) {
				throw new ClassNotFoundException();
			}
		}
		return c;
	}

}
