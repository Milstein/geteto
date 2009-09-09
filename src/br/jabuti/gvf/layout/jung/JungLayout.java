package br.jabuti.gvf.layout.jung;

import java.awt.geom.Point2D;
import java.util.Iterator;
import java.util.Vector;


import edu.uci.ics.jung.graph.DirectedGraph;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.graph.util.EdgeType;

import br.jabuti.gvf.GVFLink;
import br.jabuti.gvf.GVFNode;
import br.jabuti.gvf.layout.GraphLayout;

public class JungLayout implements GraphLayout
{
	private DirectedGraph<GVFNode, String> graph;

	private static int i = 0;
	
	private GVFNode rootNode = null;
	
	public JungLayout()
	{
		graph = new DirectedSparseGraph<GVFNode, String>();
	}
	
	public void addEdge(GVFNode src, GVFNode dest)
	{
		graph.addEdge("Node " + i++, src, dest, EdgeType.DIRECTED);
	}

	@Override
	public void addNode(GVFNode node)
	{
		System.out.println("[node] " + node.toString());
		if (rootNode == null) {
			if ("0".equals(node.getId())) {
				rootNode = node;
			}
		}
		graph.addVertex(node);
	}

	@Override
	public void end_graph()
	{
	}

	@Override
	public void layout(Vector vNodes, Vector vLinks)
	{
		HierachicalGraphLayout<GVFNode, String> layout = new HierachicalGraphLayout<GVFNode, String>(graph, rootNode);
		layout.initialize();

		Iterator i = vNodes.iterator();
		while (i.hasNext()) {
			GVFNode node = (GVFNode) i.next();
			Point2D point = layout.transform(node);
			node.moveTo((int) point.getX(), (int) point.getY());
		}
		
		i = vLinks.iterator();
		while (i.hasNext()) {
			GVFLink link = (GVFLink) i.next();
			GVFNode src = link.getSourceNode();
			GVFNode dest = link.getDestinationNode();
			Point2D srcPoint = layout.transform(src);
			Point2D destPoint = layout.transform(dest);
			link.addPoint((int) ((srcPoint.getX() + destPoint.getX()) / 2), (int) ((srcPoint.getY() + destPoint.getY()) / 2));
		}
	}

	@Override
	public void start_graph()
	{
	}

}
