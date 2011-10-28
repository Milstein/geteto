package br.jabuti.lookup.java.api;


// http://en.wikipedia.org/wiki/Java_Community_Process
public class JSR extends BasicJavaProfile 
{
	private static final String FILENAME_PREFIX = "jsr";
	
	public JSR(int id)
	{
		this(new Integer(id).toString());
	}
	
	public JSR(String id)
	{
		super(id.trim());
	}

	@Override
	protected String getFilePrefix()
	{
		return FILENAME_PREFIX;
	}
}
