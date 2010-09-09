package br.icmc.usp.jabuti.service;

public class MetricResClass {
	public String name;
	public OoMetric metrics[];
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public OoMetric[] getMetrics() {
		return metrics;
	}
	public void setMetrics(OoMetric[] metrics) {
		this.metrics = metrics;
	}
}
