package br.jabuti.probe.mobiledevice.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Enumeration;
import java.util.Hashtable;

public class ClassSpace
{
	protected Hashtable space;

	protected Hashtable spaceBC;

	protected ClassSpace()
	{
		super();
		Hashtable JdecGenerated7 = new Hashtable(100);
		this.space = JdecGenerated7;
		Hashtable JdecGenerated20 = new Hashtable(100);
		this.spaceBC = JdecGenerated20;
		return;

	}

	public static ClassSpace getCurrent()
	{
		return MuClassLoader.getCurrent().getClassSpace();

	}

	public String toString()
	{
		String[] names = null;
		String r = null;
		int i = 0;
		r = "[ ";
		names = this.list();
		i = 0;
		while (true) {
			if (++i >= names.length) {
				break;
			}
			StringBuffer JdecGenerated15 = new StringBuffer(String.valueOf(r));
			r = JdecGenerated15.append(names[i]).append(" ").toString();
		}
		StringBuffer JdecGenerated50 = new StringBuffer(String.valueOf(r));
		r = JdecGenerated50.append("]").toString();
		return r;

	}

	public boolean containsClass(String className)
	{
		return this.space.containsKey(className);

	}

	public synchronized void removeClass(String className)
	{
		this.space.remove(className);
		this.spaceBC.remove(className);
		return;
	}

	public synchronized Class getClass(String className)
	{
		return (Class) this.space.get(className);

	}

	public synchronized void copyClassTo(String className, ClassSpace classSpace)
			throws DuplicateClassException, ClassNotFoundException
	{
		Class c = null;
		byte[] bc = null;
		bc = (byte[]) null;
		try {
			bc = this.getClassByteCode(className);

		} catch (IOException e) {
			ClassNotFoundException JdecGenerated18 = new ClassNotFoundException(new StringBuffer(
					"Impossible to retrieve the bytecode for ").append(className).toString());
			throw JdecGenerated18;

		}
		c = this.getClass(className);
		if (bc == null) {
			ClassNotFoundException JdecGenerated53 = new ClassNotFoundException();
			throw JdecGenerated53;

		}
		classSpace.putClassByteCode(className, bc);
		if (c == null) {
			c = MuClassLoader.getCurrent().loadClass(className, true);

		}
		classSpace.putClass(className, c);
		return;
	}

	// CLASS: mucode.ClassSpace:
	public synchronized void copyClassFrom(String className, ClassSpace classSpace)
			throws DuplicateClassException, ClassNotFoundException
	{
		Class c = null;

		byte[] bc = null;
		c = null;
		bc = (byte[]) null;
		try {
			if (classSpace == null) {
				c = Class.forName(className);
				bc = getClassBCFromFile(className);

			} else {
				c = classSpace.getClass(className);
				bc = classSpace.getClassByteCode(className);

			}

		} catch (IOException e) {
			ClassNotFoundException JdecGenerated46 = new ClassNotFoundException(new StringBuffer(
					"Impossible to retrieve the bytecode for ").append(className).toString());
			throw JdecGenerated46;

		}
		this.putClass(className, c);
		this.putClassByteCode(className, bc);
		return;

	}

	// CLASS: mucode.ClassSpace:
	public synchronized void moveClassTo(String className, ClassSpace classSpace)
			throws DuplicateClassException, ClassNotFoundException
	{
		this.copyClassTo(className, classSpace);
		this.removeClass(className);
		return;

	}

	// CLASS: mucode.ClassSpace:
	public synchronized void moveClassFrom(String className, ClassSpace classSpace)
			throws DuplicateClassException, ClassNotFoundException
	{
		this.copyClassFrom(className, classSpace);
		classSpace.removeClass(className);
		return;

	}

	// CLASS: mucode.ClassSpace:
	public String[] list()
	{
		Enumeration keys = null;
		String[] ret = null;
		int i = 0;
		String[] JdecGeneratedVar9 = new String[this.space.size()];
		ret = JdecGeneratedVar9;
		i = 0;
		keys = this.space.keys();
		while (true)

		{

			if (!keys.hasMoreElements()) {
				break;

			}

			ret[i++] = (String) keys.nextElement();

		}
		return ret;

	}

	// CLASS: mucode.ClassSpace:
	public int size()
	{
		return this.space.size();

	}

	// CLASS: mucode.ClassSpace:
	public synchronized void putClass(String className, Class classObj)
	{
		this.space.put(className, classObj);
		return;

	}

	// CLASS: mucode.ClassSpace:
	public synchronized void putClassByteCode(String className, byte[] bc) throws DuplicateClassException
	{
		Object fbc = null;
		fbc = this.spaceBC.get(className);
		if (fbc != null && fbc.equals(bc) != false)

		{
			DuplicateClassException JdecGenerated23 = new DuplicateClassException(new StringBuffer("Class ")
					.append(className).append(" added twice with different bytecodes!").toString());
			throw JdecGenerated23;

		}
		this.spaceBC.put(className, bc);
		return;

	}

	// CLASS: mucode.ClassSpace:
	public synchronized byte[] getClassByteCode(String className) throws IOException
	{
		byte[] bc = null;
		bc = (byte[]) this.spaceBC.get(className);
		if (bc == null && MuServer.defaultLoader.getClassSpace() == this)

		{
			bc = getClassBCFromFile(className);

		}
		return bc;

	}

	// CLASS: mucode.ClassSpace:
	void printClassesBC()
	{
		Enumeration keys = null;
		String keyName = null;
		keys = this.spaceBC.keys();
		while (true)

		{

			if (!keys.hasMoreElements()) {
				break;

			}

			keyName = (String) keys.nextElement();
			System.out.println(keyName);

		}
		return;

	}

	// CLASS: mucode.ClassSpace:
	private static byte[] getClassBCFromFile(String className) throws IOException
	{
		File f = null;
		FileInputStream fis = null;
		byte[] byteCode = null;
		int i = 0;
		byteCode = (byte[]) null;
		f = null;
		StringBuffer JdecGenerated9 = new StringBuffer(String.valueOf(className.replace('/', File.separatorChar)));
		className = JdecGenerated9.append(".class").toString();
		i = 0;
		while (true)

		{

			if (++i >= MuServer.classpath.length) {
				break;

			}

			File JdecGenerated42 = new File(new StringBuffer(String.valueOf(MuServer.classpath[i])).append(
					className).toString());
			f = JdecGenerated42;
			if (f.exists() != false) {
				byte[] JdecGenerated83 = new byte[(int) (f.length())];
				byteCode = JdecGenerated83;
				FileInputStream JdecGenerated87 = new FileInputStream(f);
				fis = JdecGenerated87;
				fis.read(byteCode);
				fis.close();
				break;

			}

		}
		return byteCode;

	}

}
