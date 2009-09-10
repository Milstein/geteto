package br.jabuti.lookup.api;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;


public class JavaSE extends BasicJavaProfile
{
	private static final String FILENAME_PREFIX = "jvm-";

	private List<JavaProfile> providedJSRs;
	
	public JavaSE(String id)
	{
		super(id);
	}

	@Override
	protected String getFilePrefix()
	{
		return FILENAME_PREFIX;
	}
	
	protected void loadPatterns()
	{
		super.loadPatterns();
		
		name = props.getProperty("name");

		String[] packages = props.getProperty("proprietary_packages").split(",");
		for (String pkg : packages) {
			pkg = pkg.trim();
			String re = "^" + Pattern.quote(pkg) + ".*";
			Pattern p = Pattern.compile(re);
			patterns.add(p);
		}
		
		String[] jsrs = props.getProperty("jsrs").split(",");
		providedJSRs = new ArrayList<JavaProfile>();
		for (String jsrId : jsrs) {
			JSR jsr = new JSR(jsrId);
			providedJSRs.add(jsr);
		}
	}

	@Override
	public boolean has(String className)
	{
		for (JavaProfile jp : providedJSRs) {
			if (jp.has(className)) {
				return true;
			}
		}
		
		return super.has(className);
	}
	
}
