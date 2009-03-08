/*
Copyright (C) 2006 Auri Vicenzi and Marcio Delamaro

This library is free software; you can redistribute it and/or
modify it under the terms of the GNU Lesser General Public
License as published by the Free Software Foundation; either
version 2.1 of the License, or (at your option) any later version.

This library is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
Lesser General Public License for more details.

You should have received a copy of the GNU Lesser General Public
License along with this library; if not, write to the Free Software
Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
*/

package br.jabuti.metrics;

import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;

import br.jabuti.lookup.Program;

/**
 * This classe implemnts a set o OO metrics, calculated on a Program structure.
 * Each class is represented in the structure by a
 * {@link br.jabuti.lookup.RClassCode} and will have its set of metrics.
 * 
 * If desired, a subset of the {@link br.jabuti.lookup.RClassCode} can also be
 * used as the set of classes where the metrics will be applied, for example,
 * only the class files that are under testing.
 */
public class Metrics
{
	public static final Metric[] metrics = {
		new MetricANPM(),
		new MetricAMZLOCM(),
		new MetricAMZSIZE(),
		new MetricCBO(),
		new MetricCCAVG(),
		new MetricCCMAX(),
		new MetricDIT(),
		new MetricLCOM(),
		new MetricLCOM2(),
		new MetricLCOM3(),
		new MetricMNPM(),
		new MetricNCM(),
		new MetricNCM2(),
		new MetricNCV(),
		new MetricNII(),
		new MetricNIV(),
		new MetricNMAS(),
		new MetricNMIS(),
		new MetricNMOS(),
		new MetricNOC(),
		new MetricNPIM(),
		new MetricRFC(),
		new MetricSI(),
		new MetricWMC(),
		new MetricWMCCC(),
		new MetricWMCLOCM(),
		new MetricWMCSIZE()
	};
	
	private Hashtable<String, Hashtable<String, Double>> classTable;
	
	private Program prog;
	
	/**
	 * This method calculates the metrics w.r.t. all user defined classes in a
	 * given project.
	 */
	public Metrics(Program prog)
	{
		this(prog, prog.getCodeClasses());
	}

	/**
	 * This method calculates the metrics only w.r.t. the classes that were
	 * choosen to be instrumented.
	 */
	public Metrics(Program prog, String[] classes)
	{
		this.prog = prog;
		
		this.classTable = new Hashtable<String, Hashtable<String, Double>>();
		for (String clazz : classes) {
			classTable.put(clazz, computeMetrics(clazz));
		}
	}
	
	public static Metric getMetric(String name)
	{
		for (Metric m : metrics) {
			if (m.getName().equals(name)) {
				return m;
			}
		}
		return null;
	}

	public Hashtable<String, Double> computeMetrics(String className)
	{
		Hashtable<String, Double> hs = new Hashtable<String, Double>();
		for (Metric m : metrics) {
			Double fs = null; // calcula a metrica
			fs = m.getResult(prog, className);
			hs.put(m.getName(), fs);
		}
		return hs;
	}

	public void list()
	{
		Enumeration<String> en = classTable.keys();
		while (en.hasMoreElements()) {
			String className = en.nextElement();
			Hashtable<String, Double> hs = classTable.get(className);
			System.out.println("Metrics for " + className);
			Enumeration<String> en2 = hs.keys();
			while (en2.hasMoreElements()) {
				String metricName = (String) en2.nextElement();
				Double mtValue = (Double) hs.get(metricName);
				System.out.println("\t" + metricName + ": " + mtValue);
			}
		}
	}

	/**
	 * This method returns the set of computed metrics for a given class.
	 */
	@Deprecated
	public Double[] getClassMetrics(String cName)
	{
		ArrayList<Double> values = null;

		if (classTable.containsKey(cName)) {
			Hashtable<String, Double> hs = classTable.get(cName);

			values = new ArrayList<Double>(metrics.length);
			for (Metric m : metrics) {
				String metricName = m.getName();
				values.add(hs.get(metricName));
			}
		}
		return values.toArray(new Double[0]);
	}
	
	public Double getClassMetric(String cName, String metricName)
	{
		if (classTable.containsKey(cName)) {
			Hashtable<String, Double> hs = classTable.get(cName);
			return hs.get(metricName);
		}
		return 0.0;
	}
}
