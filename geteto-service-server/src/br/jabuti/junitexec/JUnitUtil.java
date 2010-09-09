package br.jabuti.junitexec;

public class JUnitUtil {
	public static final String SUCCESS = "S";
	public static final String FAILURE = "E";
	public static final String IGNORED = "I";
	
	public static final String traceMark = "T";
	public static final String integratorName = "JUnit/JaBUTi Integrator";
	
	static String getTestCaseName(String s) {
		return s.substring(0, s.indexOf('('));
	}	
}