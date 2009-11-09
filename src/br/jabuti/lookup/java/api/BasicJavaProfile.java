package br.jabuti.lookup.java.api;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class BasicJavaProfile implements JavaProfile
{
	protected String id;
	
	protected String name;
	
	protected String description;
	
	protected List<Pattern> patterns;
	
	protected Properties props;
	
	public BasicJavaProfile(String id)
	{
		this.id = id;
		this.patterns = new ArrayList<Pattern>();
		
		props = new Properties();
		InputStream in = getClass().getResourceAsStream(getFilePrefix() + id + ".properties");
		if (in == null) {
			throw new IllegalArgumentException("Unknown profile");
		}
		try {
			props.load(in);
		} catch (IOException e) {
			throw new IllegalArgumentException("Error loading data", e);
		}
		
		loadPatterns();
	}
	
	protected void loadPatterns()
	{
		name = props.getProperty("name");

		String[] packages = props.getProperty("packages").split(",");
		for (String pkg : packages) {
			pkg = pkg.trim();
			String re = "^" + Pattern.quote(pkg) + ".*";
			Pattern p = Pattern.compile(re);
			this.patterns.add(p);
		}
	}

	public String getId()
	{
		return id;
	}

	public String getName()
	{
		return name;
	}

	public String getDescription()
	{
		return description;
	}

	public boolean has(String className)
	{
		for (Pattern p : patterns) {
			Matcher m = p.matcher(className);
			if (m.matches()) {
				return true;
			}
		}
		
		return false;
	}

	protected abstract String getFilePrefix();
}
