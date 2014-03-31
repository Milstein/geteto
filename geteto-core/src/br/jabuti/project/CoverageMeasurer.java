package br.jabuti.project;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import br.jabuti.criteria.Criterion;
import br.jabuti.probe.desktop.ProbedNode;
import br.jabuti.probe.desktop.TraceReader;
import br.jabuti.probe.mobiledevice.HostTraceReader;

public class CoverageMeasurer
{
	/**
	 * Coverage of the test set considering only the active test cases
	 */
	private Map<Criterion, Coverage> testSetCoverage;

	public CoverageMeasurer()
	{
		testSetCoverage = new HashMap<Criterion, Coverage>();
	}

	private void checkTraceFile(File traceFile, boolean cleanTraceFile)
	{
		if (traceFile == null || !traceFile.exists() || !traceFile.isFile() || !traceFile.canRead()) {
			throw new IllegalArgumentException("Cannot access or read trace file "
							+ traceFile.getAbsolutePath());
		}
		if (cleanTraceFile && !traceFile.canWrite()) {
			throw new IllegalArgumentException("Cannot write to trace file "
							+ traceFile.getAbsolutePath());
		}
	}

	public boolean loadTraceFile(JabutiProject prj, File traceFile)
	{
		return loadTraceFile(prj, traceFile, true);
	}

	public boolean loadTraceFile(JabutiProject prj, File traceFile, boolean cleanTraceFile)
	{
		checkTraceFile(traceFile, cleanTraceFile);

		List<TestCase> newTCs = new ArrayList<TestCase>();
		TraceReader dtr = null;
		try {
			dtr = new HostTraceReader(traceFile);
			Map<ProbedNode, String[][]> trace = dtr.getPaths();
			int cont = 1;
			while (trace != null) {
				TestCase tc = createNewTestCase(prj);
				tc.setAlias(dtr.getName());
				tc.addTestCaseFromTRC(prj, trace);
				newTCs.add(tc);
				trace = dtr.getPaths();
				cont++;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		Iterator<TestCase> i = newTCs.iterator();
		while (i.hasNext()) {
			TestCase tc = i.next();
			activateTestCase(prj, tc.getLabel());
		}
		updateOverallCoverage(prj);

		if (cleanTraceFile) {
			return clearTraceFile(traceFile);
		}
		return true;
	}

	public boolean loadAndCutTraceFile(JabutiProject prj, File traceFile)
	{
		return loadAndCutTraceFile(prj, traceFile, true);
	}

	public boolean loadAndCutTraceFile(JabutiProject prj, File traceFile, boolean cleanTraceFile)
	{
		checkTraceFile(traceFile, cleanTraceFile);

		List<TestCase> voidTestCases = new ArrayList<TestCase>();
		TraceReader dtr = null;
		try {
			dtr = new HostTraceReader(traceFile);
			Map<ProbedNode, String[][]> trace = dtr.getPaths();
			while (trace != null) {
				Map<Criterion, Coverage> coverageBeforeCut = new HashMap<Criterion, Coverage>(
								testSetCoverage);
				TestCase tc = createNewTestCase(prj);
				tc.setAlias(dtr.getName());
				tc.addTestCaseFromTRC(prj, trace);

				activateTestCase(prj, tc.getLabel());
				updateOverallCoverage(prj);

				Iterator<Criterion> i = testSetCoverage.keySet().iterator();
				boolean coverageChanged = false;
				while (i.hasNext()) {
					Criterion criterion = i.next();
					if (!coverageBeforeCut.containsKey(criterion)) {
						coverageChanged = true;
						break;
					}
					if (!coverageBeforeCut.get(criterion).equals(testSetCoverage.get(criterion))) {
						coverageChanged = true;
						break;
					}
				}

				if (!coverageChanged) {
					voidTestCases.add(tc);
				}

				trace = dtr.getPaths();
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		Iterator<TestCase> i = voidTestCases.iterator();
		while (i.hasNext()) {
			TestCase tc = i.next();
			removeTestCase(prj, tc.getLabel());
		}

		updateOverallCoverage(prj);

		if (cleanTraceFile) {
			return clearTraceFile(traceFile);
		}

		return true;
	}

	private boolean clearTraceFile(File file)
	{
		try {
			RandomAccessFile raf = new RandomAccessFile(file, "rw");
			raf.setLength(0);
			raf.close();
		} catch (IOException e) {
			return false;
		}

		return true;
	}

	/**
	 * Make an existent test case active
	 */
	public void activateTestCase(JabutiProject prj, String label)
	{
		if (!isActive(label)) {
			// System.out.println("Ativando caso de teste: " + label);
			if (isDeleted(label))
				undeleteTestCase(prj, label);
			activeTestCases.add(label);

			/* Enable the test case on the entire project */
			Hashtable classes = prj.getClassFilesTable();
			Iterator it = classes.values().iterator();

			while (it.hasNext()) {
				ClassFile cf = (ClassFile) it.next();

				HashMap methods = cf.getMethodsTable();
				Iterator mthIt = methods.values().iterator();

				while (mthIt.hasNext()) {
					ClassMethod cm = (ClassMethod) mthIt.next();
					Criterion criterion = null;
					for (int i = 0; i < Criterion.NUM_CRITERIA; i++) {
						criterion = cm.getCriterion(i);
						criterion.enablePath(label);
					}
				}
			}
		}
	}

	/**
	 * Make an existent test case desactive
	 */
	public void desactivateTestCase(JabutiProject prj, String label)
	{
		if (isActive(label)) {
			// System.out.println("Desativando caso de teste: " + label);
			activeTestCases.remove(label);

			/* Disabling the test case on the entire project */
			Hashtable classes = prj.getClassFilesTable();
			Iterator it = classes.values().iterator();

			while (it.hasNext()) {
				ClassFile cf = (ClassFile) it.next();

				HashMap methods = cf.getMethodsTable();
				Iterator mthIt = methods.values().iterator();

				while (mthIt.hasNext()) {
					ClassMethod cm = (ClassMethod) mthIt.next();
					Criterion criterion = null;
					for (int i = 0; i < Criterion.NUM_CRITERIA; i++) {
						criterion = cm.getCriterion(i);
						criterion.disablePath(label);
					}
				}
			}
		}
	}

	/**
	 * This method permanently remove a given test case
	 */
	public void removeTestCase(JabutiProject prj, String label) {
               if (!TestSet.isActive(label))
                       return;
               activeTestCases.remove(label);
               testCaseTable.remove(label);

               /* Permanently remove a given test case on the entire project */
               Hashtable classes = prj.getClassFilesTable();
               Iterator it = classes.values().iterator();

               while (it.hasNext()) {
                       ClassFile cf = (ClassFile) it.next();

                       HashMap methods = cf.getMethodsTable();
                       Iterator mthIt = methods.values().iterator();

                       while (mthIt.hasNext()) {
                               ClassMethod cm = (ClassMethod) mthIt.next();
                               Criterion criterion = null;
                               for (int i = 0; i < Criterion.NUM_CRITERIA; i++) {
                                       criterion = cm.getCriterion(i);
                                       criterion.removePath(label);
                              }
                       }
               }

	public void removeTestCase(String label)
	{
		testCases.remove(label);
	}

	/**
	 * This method permanently remove all test cases
	 */
	public void removeTestCases(JabutiProject prj)
	{
		if (TestSet.getNumberOfDeletedTestCases() > 0) {
			Object[] tcLabels = TestSet.getDeletedSet().toArray();
			for (int j = 0; j < tcLabels.length; j++) {
				TestSet.removeTestCase(prj, (String) tcLabels[j]);
			}
		}
	}

	/**
	 * Returns the coverage w.r.t. all effective test cases in this test set.
	 */
	public Coverage getTestSetCoverage(int c)
	{
		if ((c >= 0) && (c < Criterion.NUM_CRITERIA)) {
			return testSetCoverage[c];
		} else {
			return null;
		}
	}

	public void updateTestSetCoverage(JabutiProject prj)
	{
		for (int i = 0; i < Criterion.NUM_CRITERIA; i++) {
			testSetCoverage[i] = prj.getProjectCoverage(i);
		}

		Iterator it = testCaseTable.values().iterator();

		while (it.hasNext()) {
			TestCase tc = (TestCase) it.next();

			tc.updateTestCaseCoverage(prj);
		}
	}

	public void updateOverallCoverage(JabutiProject prj)
	{
		// System.out.println("Atualizando cobertura total!!!");
		// After loaded all test cases:
		// 1) Update the coverage w.r.t the entire
		// project; and
		prj.updateProjectCoverage();
		// 2) Update the coverage w.r.t each test case
		//
		// Should be in this ordem since to update the
		// coverage of each test case, the coverage of
		// a given method should be computed.
		TestSet.updateTestSetCoverage(prj);
	}

}
