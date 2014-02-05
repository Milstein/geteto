package br.jabuti.graph.layout;

import java.util.Vector;

import br.jabuti.graph.view.gvf.GVFNode;

public interface GraphLayout
{
	/**
	 * Adds a string to the graph's source (without newline).
	 */
	void addNode(GVFNode node);
	
	void addEdge(GVFNode src, GVFNode dest);
	
	/**
	 * Returns a string that is used to start a graph.
	 * @return A string to open a graph.
	 */
	void start_graph();

	/**
	 * Returns a string that is used to end a graph.
	 * @return A string to close a graph.
	 */
	void end_graph();
	
	void layout(Vector vNodes, Vector vLinks);
}
