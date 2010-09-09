/*  Copyright 2003  Auri Marcelo Rizzo Vicenzi, Marcio Eduardo Delamaro, 			    Jose Carlos Maldonado

    This file is part of Jabuti.

    Jabuti is free software: you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    Jabuti is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU Lesser General Public License
    along with Jabuti.  If not, see <http://www.gnu.org/licenses/>.
*/


package br.jabuti.project;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * This class is responsible do deal with the test set. it reads a trace file
 * and stores all the test cases into test cases objects.
 *
 * It can also report the coverage of test set, considering only the active test
 * cases.
 *
 * @version: 1.0
 * @author: Auri Vincenzi
 * @author: Marcio Delamaro
 * @author: Tatiana Sugeta
 *
 */
public class TestSet
{
	private Map<String, TestCase> testCases;

	private AtomicInteger testCaseId;

	public TestSet()
	{
		testCases = new HashMap<String, TestCase>();
		testCaseId = new AtomicInteger(0);
	}

	/**
	 * This method permanently remove a given test case
	 */
	public void removeTestCase(String label) {
		testCases.remove(label);
	}

	private Set<TestCase> getSubSet(TestCaseResult result) {
		Set<TestCase> subSet = new HashSet<TestCase>();
		Iterator<TestCase> i = testCases.values().iterator();
		while (i.hasNext()) {
			TestCase tc = i.next();
			if (tc.getResult().equals(result)) {
				subSet.add(tc);
			}
		}
		return subSet;
	}

	private Set<TestCase> getSubSet(TestCaseStatus status) {
		Set<TestCase> subSet = new HashSet<TestCase>();
		Iterator<TestCase> i = testCases.values().iterator();
		while (i.hasNext()) {
			TestCase tc = i.next();
			if (tc.getStatus().equals(status)) {
				subSet.add(tc);
			}
		}
		return subSet;
	}


	public Set<TestCase> getFailSet() {
		return getSubSet(TestCaseResult.FAILURE);
	}

	public Set<TestCase> getSuccessSet() {
		return getSubSet(TestCaseResult.SUCCESSFUL);
	}

	/**
	 * This method returns all active test cases w.r.t. a given method method
	 */
	public Set<TestCase> getActiveTestCases() {
		return getSubSet(TestCaseStatus.ENABLED);
	}

	/**
	 * Get a specific test case object
	 */
	public TestCase getTestCase(String label) {
		return testCases.get(label);
	}

	/**
	 * Add a single test case
	 */
	private void addTestCase(TestCase tc) {
		if (tc == null) {
			throw new IllegalArgumentException(new NullPointerException());
		}

		testCases.put(tc.getLabel(), tc);
	}

	/**
	 * Creates a new TestCase instance and adds it to the current test set
	 * @param alias
	 */
	public TestCase createEmptyTestCase(JabutiProject prj, String label, String alias) {
		TestCase tc = new TestCase(prj, label, alias);
		addTestCase(tc);
		return tc;
	}

	/**
	 * Creates a new TestCase instance and adds it to the current test set
	 */
	public TestCase createNewTestCase(JabutiProject prj) {
		TestCase tc = new TestCase(prj, Integer.toString(newTestCaseLabel()), "");
		addTestCase(tc);
		return tc;
	}

	/**
	 * Returns the number of read test cases from the trace file
	 */
	public int getNumberOfTestCases() {
		return testCases.size();
	}

	/**
	 * Returns the number of active test cases
	 */
	public int getNumberOfActiveTestCases() {
		return getActiveTestCases().size();
	}

	/*
	 * Generates a new Test Case Label based on the current value of test case
	 * identifier. The first valid test case label starts from 1.
	 */
	private int newTestCaseLabel() {
		return testCaseId.incrementAndGet();
	}
}
