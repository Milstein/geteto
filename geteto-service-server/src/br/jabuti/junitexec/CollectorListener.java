package br.jabuti.junitexec;

import java.io.PrintStream;
import java.util.HashMap;

import org.junit.runner.Description;
import org.junit.runner.notification.Failure;

public class CollectorListener extends
		org.junit.internal.TextListener {
	private HashMap<String, String> testSet = new HashMap<String, String>();

	private PrintStream fWriter;
	
	public CollectorListener(PrintStream writer) {
		super(writer);
		this.fWriter= writer;
	}
	
	@Override
	public void testRunStarted(Description description) throws Exception {
		super.testRunStarted(description);
		fWriter.append(JUnitUtil.integratorName + ": Collector Mode\n");
	}

	@Override
	public void testStarted(Description description) {
		super.testStarted(description);
		String tc = JUnitUtil.getTestCaseName(description.getDisplayName());
		// System.out.println("Begin: " + tc);
		testSet.put(tc, JUnitUtil.SUCCESS);
	}

	@Override
	public void testFinished(Description description) throws Exception {
		super.testFinished(description);
		//String tc = getTestCaseName(description.getDisplayName());
		// System.out.println("End: " + tc);
	}

	@Override
	public void testFailure(Failure failure) {
		super.testFailure(failure);
		String tc = JUnitUtil.getTestCaseName(failure.getDescription().getDisplayName());
		testSet.put(tc, JUnitUtil.FAILURE);
	}

	@Override
	public void testIgnored(Description description) {
		super.testIgnored(description);
		String tc = JUnitUtil.getTestCaseName(description.getDisplayName());
		testSet.put(tc, JUnitUtil.IGNORED);
	}

	public HashMap<String, String> getTestSet() {
		return testSet;
	}
}