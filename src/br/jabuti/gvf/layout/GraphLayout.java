package br.jabuti.gvf.layout;

import java.util.Vector;

public interface GraphLayout
{
	/**
	 * Adds a string to the graph's source (without newline).
	 */
	void add(String line);

	/**
	 * Adds a string to the graph's source (with newline).
	 */
	void addln(String line);

	/**
	 * Adds a newline to the graph's source.
	 */
	void addln();

	/**
	 * Returns a string that is used to start a graph.
	 * @return A string to open a graph.
	 */
	String start_graph();

	/**
	 * Returns a string that is used to end a graph.
	 * @return A string to close a graph.
	 */
	String end_graph();
	
	void layout(Vector vNodes, Vector vLinks);
}
