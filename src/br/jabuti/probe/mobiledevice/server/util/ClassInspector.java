package br.jabuti.probe.mobiledevice.server.util;

import java.io.ByteArrayInputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Vector;

import br.jabuti.probe.mobiledevice.server.ClassSpace;
import br.jabuti.probe.mobiledevice.server.MuClassLoader;
import br.jabuti.probe.mobiledevice.server.MuServer;

public class ClassInspector
{

	/***
	 **Class Fields
	 ***/
	private static final byte CONSTANT_Utf8 = 1;
	private static final byte CONSTANT_Integer = 3;
	private static final byte CONSTANT_Float = 4;
	private static final byte CONSTANT_Long = 5;
	private static final byte CONSTANT_Double = 6;
	private static final byte CONSTANT_Class = 7;
	private static final byte CONSTANT_String = 8;
	private static final byte CONSTANT_Fieldref = 9;
	private static final byte CONSTANT_Methodref = 10;
	private static final byte CONSTANT_InterfaceMethodref = 11;
	private static final byte CONSTANT_NameAndType = 12;
	private static final String ERRMSG = "Impossible to retrieve the bytecode for ";

	// CLASS: mucode.util.ClassInspector:
	private ClassInspector()
	{
		super();
		return;

	}

	// CLASS: mucode.util.ClassInspector:
	public static String[] getClassClosure(String className, MuServer server, int closureType)
			throws ClassNotFoundException
	{
		Class c = null;
		c = null;
		c = MuClassLoader.getCurrent().loadClass(className);
		return getClassNames(getClassClosure(c, server, closureType));

	}

	// CLASS: mucode.util.ClassInspector:
	public static Class[] getClassClosure(Class c, MuServer server, int closureType)
			throws ClassNotFoundException
	{
		Class[] s = null;
		try {
			switch (closureType) {

				case 0:
					Class[] JdecGeneratedVar47 = new Class[0];
					s = JdecGeneratedVar47;
					break;

				case 1:
					Class[] JdecGeneratedVar55 = new Class[0];
					s = JdecGeneratedVar55;
					break;

				case 2:
					s = getDeclaredClasses(c, server);
					break;

				case 3:
					s = getReferencedClasses(c, server);
					break;

				case 4:
					s = getDeclaredClassClosure(c, server);
					break;

				case 5:
					s = getFullClassClosure(c, server);
					break;

				default:
					IllegalArgumentException JdecGenerated102 = new IllegalArgumentException(
							"Unknown class closure specifier");
					throw JdecGenerated102;

			}
		} catch (IOException e) {
			ClassNotFoundException JdecGenerated117 = new ClassNotFoundException(new StringBuffer(
					"Impossible to retrieve the bytecode for ").append(c.getName()).toString());
			throw JdecGenerated117;

		}
		return s;

	}

	// CLASS: mucode.util.ClassInspector:
	public static Class[] getDeclaredClasses(Class c, MuServer server)
	{
		Class[] ret = null;
		Constructor[] constructors = null;
		Field[] fields = null;
		Method[] methods = null;
		Vector classNames = null;
		int i = 0;
		fields = (Field[]) null;
		methods = (Method[]) null;
		constructors = (Constructor[]) null;
		ret = (Class[]) null;
		Vector JdecGenerated24 = new Vector(20, 10);
		classNames = JdecGenerated24;
		if (!c.isArray() && !c.isPrimitive())

		{
			processMember(c, server, classNames);
			fields = c.getDeclaredFields();
			if (fields.length != 0) {
				i = 0;
				while (true)

				{

					if (++i >= fields.length) {
						break;

					}

					processMember(fields[i].getType(), server, classNames);

				}

			}
			methods = c.getDeclaredMethods();
			if (methods.length != 0) {
				i = 0;
				while (true)

				{

					if (++i >= methods.length) {
						break;

					}

					processMember(methods[i].getReturnType(), server, classNames);
					processMembers(methods[i].getParameterTypes(), server, classNames);
					processMembers(methods[i].getExceptionTypes(), server, classNames);

				}

			}
			constructors = c.getDeclaredConstructors();
			if (constructors.length != 0) {
				i = 0;
				while (true)

				{

					if (++i >= constructors.length) {
						break;

					}

					processMembers(constructors[i].getParameterTypes(), server, classNames);
					processMembers(constructors[i].getExceptionTypes(), server, classNames);

				}

			}
			processMembers(c.getInterfaces(), server, classNames);

		}
		Class[] JdecGeneratedVar234 = new Class[classNames.size()];
		ret = JdecGeneratedVar234;
		classNames.copyInto(ret);
		return ret;

	}

	// CLASS: mucode.util.ClassInspector:
	public static Class[] getDeclaredClassClosure(Class c, MuServer server) throws ClassNotFoundException,
			IOException
	{
		Class[] ret = null;
		Vector classes = null;

		Vector JdecGenerated2 = new Vector(20, 10);
		classes = JdecGenerated2;
		ret = (Class[]) null;
		getDeclaredClassClosure(c, server, classes);
		Class[] JdecGeneratedVar29 = new Class[classes.size()];
		ret = JdecGeneratedVar29;
		classes.copyInto(ret);
		return ret;

	}

	// CLASS: mucode.util.ClassInspector:
	public static Class[] getReferencedClasses(Class c, MuServer server) throws ClassNotFoundException
	{
		Class[] ret = null;
		String[] s = null;
		Vector retV = null;
		int i = 0;
		s = (String[]) null;
		Class[] JdecGeneratedVar8 = new Class[0];
		ret = JdecGeneratedVar8;
		if (!skip(c, server)) {
			try {
				s = getReferencedClasses(ClassSpace.getCurrent().getClassByteCode(c.getName()));

			} catch (IOException e) {
				ClassNotFoundException JdecGenerated39 = new ClassNotFoundException(new StringBuffer(
						"Impossible to retrieve the bytecode for ").append(c.getName()).toString());
				throw JdecGenerated39;

			}
			Vector JdecGenerated66 = new Vector(s.length, 0);
			retV = JdecGenerated66;
			i = 0;
			while (true)

			{

				if (++i >= s.length) {
					break;

				}

				if (!skip(s[i], server)) {
					retV.addElement(MuClassLoader.getCurrent().loadClass(s[i]));

				}

			}
			Class[] JdecGeneratedVar125 = new Class[retV.size()];
			ret = JdecGeneratedVar125;
			retV.copyInto(ret);

		}
		return ret;

	}

	// CLASS: mucode.util.ClassInspector:
	public static Class[] getFullClassClosure(Class c, MuServer server) throws ClassNotFoundException,
			IOException
	{
		Class[] ret = null;
		Vector classNames = null;
		byte[] bc = null;
		int i = 0;

		Vector JdecGenerated2 = new Vector(20, 10);
		classNames = JdecGenerated2;
		bc = (byte[]) null;
		Class[] JdecGeneratedVar20 = new Class[0];
		ret = JdecGeneratedVar20;
		if (!skip(c, server)) {
			try {
				bc = MuClassLoader.getCurrent().getClassSpace().getClassByteCode(c.getName());

			} catch (IOException ioe) {
				ClassNotFoundException JdecGenerated52 = new ClassNotFoundException(new StringBuffer(
						"Impossible to retrieve the bytecode for ").append(c.getName()).toString());
				throw JdecGenerated52;

			}
			getFullClassClosure(bc, server, classNames);
			Class[] JdecGeneratedVar89 = new Class[classNames.size()];
			ret = JdecGeneratedVar89;
			i = 0;
			while (true)

			{

				if (++i >= ret.length) {
					break;

				}

				ret[i] = MuClassLoader.getCurrent().loadClass((String) classNames.elementAt(i), true);

			}

		}
		return ret;

	}

	// CLASS: mucode.util.ClassInspector:
	public static String[] getClassNames(Class[] classes)
	{
		String[] ret = null;
		int i = 0;
		String[] JdecGeneratedVar4 = new String[classes.length];
		ret = JdecGeneratedVar4;
		i = 0;
		while (true)

		{

			if (++i >= classes.length) {
				break;

			}

			classes[i].getName();
			ret[i] = classes[i].getName();

		}
		return ret;

	}

	// CLASS: mucode.util.ClassInspector:
	public static Class[] filterUbiquitous(Class[] classes, MuServer server)
	{
		Class[] ret = null;
		Vector v = null;
		int i = 0;

		Vector JdecGenerated2 = new Vector(classes.length, 0);
		v = JdecGenerated2;
		i = 0;
		while (true)

		{

			if (++i >= classes.length) {
				break;

			}

			if (!skip(classes[i], server)) {
				v.addElement(classes[i]);

			}

		}
		Class[] JdecGeneratedVar48 = new Class[v.size()];
		ret = JdecGeneratedVar48;
		v.copyInto(ret);
		return ret;

	}

	// CLASS: mucode.util.ClassInspector:
	public static String[] filterUbiquitous(String[] classes, MuServer server)
	{
		String[] ret = null;
		Vector v = null;
		int i = 0;

		Vector JdecGenerated2 = new Vector(classes.length, 0);
		v = JdecGenerated2;
		i = 0;
		while (true)

		{

			if (++i >= classes.length) {
				break;

			}

			if (!skip(classes[i], server)) {
				v.addElement(classes[i]);

			}

		}
		String[] JdecGeneratedVar48 = new String[v.size()];
		ret = JdecGeneratedVar48;
		v.copyInto(ret);
		return ret;

	}

	// CLASS: mucode.util.ClassInspector:
	public static boolean isPrimitive(String className)
	{
		if (!className.equals("boolean") && !className.equals("byte") && !className.equals("char")
				&& !className.equals("short") && !className.equals("int") && !className.equals("long")
				&& !className.equals("float") && !className.equals("double") && !className.equals("void"))

		{
			return false;

		}
		return true;

	}

	// CLASS: mucode.util.ClassInspector:
	private static void filterMember(Class c, MuServer server, Vector v)
	{
		if (!skip(c.getName(), server) && !v.contains(c.getName()))

		{
			v.addElement(c.getName());
			return;
		}

	}

	// CLASS: mucode.util.ClassInspector:
	private static void filterMembers(Class[] c, MuServer server, Vector v)
	{
		int i = 0;
		if (c.length != 0) {
			i = 0;
			while (true)

			{

				if (++i >= c.length) {
					break;

				}

				processMember(c[i], server, v);

				return;
			}

		}

	}

	// CLASS: mucode.util.ClassInspector:
	private static void processMember(Class c, MuServer server, Vector v)
	{
		if (c.isArray() != false) {
			c = c.getComponentType();

		}
		if (!skip(c.getName(), server) && !v.contains(c))

		{
			v.addElement(c);
			return;
		}

	}

	// CLASS: mucode.util.ClassInspector:
	private static void processMembers(Class[] c, MuServer server, Vector v)
	{
		int i = 0;
		if (c.length != 0) {
			i = 0;
			while (true)

			{

				if (++i >= c.length) {
					break;

				}

				processMember(c[i], server, v);

				return;
			}

		}

	}

	// CLASS: mucode.util.ClassInspector:
	private static void getFullClassClosure(byte[] bytecode, MuServer server, Vector classNames)
			throws ClassNotFoundException, IOException
	{
		String[] cur = null;
		byte[] bc = null;
		int i = 0;
		cur = (String[]) null;
		bc = (byte[]) null;
		cur = getReferencedClasses(bytecode);
		i = 0;
		while (true)

		{

			if (++i >= cur.length) {
				break;

			}

			if (!classNames.contains(cur[i]) && !skip(cur[i], server))

			{
				classNames.addElement(cur[i]);
				try {
					bc = MuClassLoader.getCurrent().getClassSpace().getClassByteCode(cur[i]);

				} catch (IOException ioe) {
					ClassNotFoundException JdecGenerated74 = new ClassNotFoundException(new StringBuffer(
							"Impossible to retrieve the bytecode for ").append(cur[i]).toString());
					throw JdecGenerated74;

				}
				getFullClassClosure(bc, server, classNames);

			} else {
				break;
			}

		}
		return;

	}

	private static String[] getReferencedClasses(byte[] bytecode) throws IOException
	{
	    DataInput in = new DataInputStream(new ByteArrayInputStream(bytecode));
	    String [] strings= null;
	    String s= null;
	    String[] cn= null;
	    Vector classIndexes = new Vector();
	    Vector classNames = new Vector();
	    int constantPoolCount= 0;
	    int i= 0;
	    int index= 0;
	    int magic= 0;
	    int tag= 0;

	    magic=in.readInt();
	    if(magic != -889275714)
	    {
	    	throw new IOException("Invalid bytecode: no magic number");
	    }
	    in.readUnsignedShort();
	    in.readUnsignedShort();
	    constantPoolCount = in.readUnsignedShort();
	    strings = new String[constantPoolCount];
	    i = 1;
	    while (true) {        
            if(++i >= constantPoolCount) {
            	break;
            }
            tag = in.readUnsignedByte();
            switch(tag) {
            	case 1:
            		strings[i] = in.readUTF();
            		break;
      
            	case 7:
            		Integer JdecGenerated189 = new Integer(in.readUnsignedShort());
            		classIndexes.addElement(JdecGenerated189);
            		break;
      
            	case 5:
            	case 6:
            		i = i + 1;
            		in.readInt();
            		
            	case 3:
            	case 4:
            		in.readInt();
            		break;
      
            	case 9: 
            	case 10: 
            	case 11: 
            	case 12:
            		in.readUnsignedShort();
      
            	case 8:
            		in.readUnsignedShort();
            		break;
      
            	case 2: 
            	default:
            		throw new IOException(new StringBuffer("Invalid bytecode: unknown constant pool tag (").append(tag).append(")").toString());
            }
            i = 0;
            while (true) {
              if (++i >= classIndexes.size()) {
            	  break;
              }

              index = ((Integer)classIndexes.elementAt(i)).intValue();
              if (index > strings.length || strings[index]==null) {
            	  throw new IOException("Invalid bytecode: bad class index");
              }
              
              // char 47, char 46
              s = strings[index].replace('/', '.');
              if(!s.startsWith("["))
              {
                classNames.addElement(s);
        
              }
            }
       
            }
            cn = new String[classNames.size()];
            classNames.copyInto(cn);
            return cn;
	    }

	// CLASS: mucode.util.ClassInspector:
	private static void getDeclaredClassClosure(Class classObj, MuServer server, Vector classes)
			throws ClassNotFoundException
	{
		Class c = null;
		Class e = null;
		Class f = null;
		Class father = null;
		Class p = null;
		Class retPar = null;
		Class[] exceptions = null;
		Class[] interfaces = null;
		Class[] pars = null;
		Constructor[] constructors = null;
		Field[] fields = null;
		Method[] methods = null;
		int i = 0;
		int j = 0;
		int k = 0;
		fields = (Field[]) null;
		methods = (Method[]) null;
		constructors = (Constructor[]) null;
		exceptions = (Class[]) null;
		pars = (Class[]) null;
		interfaces = (Class[]) null;
		retPar = null;
		c = null;
		f = null;
		p = null;
		e = null;
		father = null;
		c = classObj.getComponentType();
		if (c == null) {
			c = classObj;

		}
		if ((skip(c, server) != false || !classes.contains(c)))

		{
			classes.addElement(c);

		}
		if (!c.isInterface()) {
			father = c.getSuperclass();
			if (!skip(father, server)) {
				getDeclaredClassClosure(father, server, classes);

			}

		}
		fields = c.getDeclaredFields();
		if (fields.length != 0) {
			i = 0;
			while (true)

			{

				if (++i >= fields.length) {
					break;

				}

				f = fields[i].getType();
				if (!skip(f, server)) {
					getDeclaredClassClosure(f, server, classes);

				}

			}

		}
		methods = c.getDeclaredMethods();
		if (methods.length != 0) {
			i = 0;
			while (true)

			{

				if (++i >= methods.length) {
					break;

				}

				retPar = methods[i].getReturnType();
				if (!skip(retPar, server)) {
					getDeclaredClassClosure(retPar, server, classes);

				}
				pars = methods[i].getParameterTypes();
				j = 0;
				while (true)

				{

					if (++j >= pars.length) {
						break;

					}

					p = pars[j];
					if (!skip(p, server)) {
						getDeclaredClassClosure(p, server, classes);

					}

				}
				exceptions = methods[i].getExceptionTypes();
				k = 0;
				while (true)

				{

					if (++k >= exceptions.length) {
						break;

					}

					e = exceptions[k];
					if (!skip(e, server)) {
						getDeclaredClassClosure(e, server, classes);

					}

				}

			}

		}
		constructors = c.getDeclaredConstructors();
		if (constructors.length != 0) {
			i = 0;
			while (true)

			{

				if (++i >= constructors.length) {
					break;

				}

				pars = constructors[i].getParameterTypes();
				j = 0;
				while (true)

				{

					if (++j >= pars.length) {
						break;

					}

					p = pars[j];
					if (!skip(p, server)) {
						getDeclaredClassClosure(p, server, classes);

					}

				}
				exceptions = constructors[i].getExceptionTypes();
				k = 0;
				while (true)

				{

					if (++k >= exceptions.length) {
						break;

					}

					e = exceptions[k];
					if (!skip(e, server)) {
						getDeclaredClassClosure(e, server, classes);

					}

				}

			}

		}
		interfaces = c.getInterfaces();
		if (interfaces.length != 0) {
			i = 0;
			while (true)

			{

				if (++i >= interfaces.length) {
					break;

				}

				if (!skip(interfaces[i], server)) {
					getDeclaredClassClosure(interfaces[i], server, classes);

				}

				return;
			}

		}

	}

	// CLASS: mucode.util.ClassInspector:
	private static boolean skip(String className, MuServer server)
	{
		if (!server.isUbiquitous(className) && !isPrimitive(className))

		{
			return false;

		}
		return true;

	}

	// CLASS: mucode.util.ClassInspector:
	private static boolean skip(Class classObj, MuServer server)
	{
		if (!server.isUbiquitous(classObj.getName()) && !classObj.isPrimitive())

		{
			return false;

		}
		return true;

	}

}
