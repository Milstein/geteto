package br.jabuti.graph.datastructure;

import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import br.jabuti.graph.datastructure.reducetree.RoundRobinExecutor;

public interface Graph
{
	/**
	 * Marks a node as an entry node. The graph can have several entry nodes. The method does not
	 * add the node to the graph, it should be added before calling this method
	 * 
	 * @param node The node that will be an entry.
	 */
	void setEntryNode(GraphNode node);

	/**
	 * Marks a node as an exit node. The graph can have several exit nodes. The method does not add
	 * the node to the graph, it should be added before calling this method
	 * 
	 * @param node The node that will be the exit.
	 */
	void setExitNode(GraphNode node);

	/**
	 * Removes an node as entry.
	 * 
	 * @param node - The node to be removed from the entry set
	 */
	void removeEntryNode(GraphNode node);

	/**
	 * Removes all entries.
	 */
	void removeEntryNodes();

	/**
	 * Removes an node as exit.
	 * 
	 * @param node - The node to be removed from the exit set
	 */
	void removeExitNode(GraphNode node);

	/**
	 * Removes all exit nodes.
	 */
	void removeExitNodes();

	/**
	 * Returns the first entry node of the graph. The one that was first set using {@link #setEntry}.
	 * 
	 * @return The first entry node of this graph. <code>null</code> if none has been set.
	 */
	GraphNode getFirstEntryNode();

	/**
	 * Returns the complete set of entry nodes for this graph.
	 * 
	 * @return An array with each of the entry nodes.
	 */
	Set<GraphNode> getEntryNodes();

	/**
	 * Returns the first exit node of the graph. The one that was first set using {@link #setExit}.
	 * 
	 * @return The exit node of this graph. <code>null</code> if none has been set.
	 */
	GraphNode getFirstExitNode();

	/**
	 * Returns the complete set of exit nodes for this graph.
	 * 
	 * @return An array with each of the entry nodes.
	 */

	Set<GraphNode> getExitNodes();

	/**
	 * Checks whether a node is an exit node.
	 * 
	 * @param node the node to be checked
	 * @return true if the node is an exit node
	 */
	boolean isExitNode(GraphNode node);

	/**
	 * Checks whether a node is an entry node.
	 * 
	 * @param node the node to be checked
	 * @return true if the node is an entry node
	 */
	boolean isEntryNode(GraphNode node);

	/**
	 * Finds the set of nodes without successors and then set them as exit nodes.
	 * 
	 * @param useSecondaryEdges - Indicates whether secondary edges should be used to find exit nodes.
	 */
	void computeExit(boolean useSecondaryEdges);

	/**
	 * Adds an directed edge between two nodes
	 * 
	 * @param src The source node
	 * @param y The destination node
	 */
	void addPrimaryEdge(GraphNode src, GraphNode dest);

	/**
	 * Adds an directed secondary edge between two nodes
	 * 
	 * @param src The source node
	 * @param dest The destination node
	 */
	void addSecondaryEdge(GraphNode src, GraphNode dest);


	/**
	 * Remove an directed edge between two nodes
	 * 
	 * @param src The source node
	 * @param y The destination node
	 */
	void removePrimaryEdge(GraphNode src, GraphNode dest);

	/**
	 * Adds an directed secondary edge between two nodes
	 * 
	 * @param src The source node
	 * @param y The destination node
	 */
	void removeSecondaryEdge(GraphNode src, GraphNode dest);

	/**
	 * Returns the set of nodes for which there exist edges from a given node.
	 * 
	 * @param node The source node.
	 * @param useSecondaryEdges Indicates whether or no to consider secondary edges two.
	 * @return The set of nodes for which the node of interest has an edge (or a secondary edge).
	 */
	Vector getLeavingNodes(GraphNode node, boolean useSecondaryEdges);

	/**
	 * Returns the set of nodes for which there exist primary edges from a given node.
	 * 
	 * @param node The source node.
	 * @return The set of primary nodes for which the node of interest has an edge.
	 */
	Vector getLeavingNodesByPrimaryEdge(GraphNode node);

	/**
	 * Returns the set of nodes for which there exist secondary edges from a given node.
	 * 
	 * @param node The source node.
	 * @return The set of secondary nodes for which the node of interest has an edge.
	 */
	Vector getLeavingNodesBySecondaryEdge(GraphNode node);

	/**
	 * Returns the set of nodes for which there exist edges to a given node.
	 * 
	 * @param node The destination node.
	 * @param useSecondaryEdges Indicates whether or no to consider secondary edges two.
	 * @return The set of nodes from which the node of interest has an edge (or a secondary edge).
	 */
	Vector getArrivingNodes(GraphNode node, boolean useSecondaryEdges);

	/**
	 * Returns the set of primary nodes for which there exist edges to a given node.
	 * 
	 * @param node The destination node.
	 * @return The set of nodes from which the node of interest has a primary edge.
	 */
	Vector getArrivingNodesByPrimaryEdge(GraphNode node);

	/**
	 * Returns the set of secondary nodes for which there exist edges to a given node.
	 * 
	 * @param node The destination node.
	 * @return The set of nodes from which the node of interest has a secondary edge.
	 */
	Vector getArrivingNodesBySecondaryEdge(GraphNode node);

	/**
	 * Removes a node from the graph. Deals with all the details of such operation as removing edges
	 * entering and exiting the node, removing from the set of entry nodes and unseting the exit
	 * node (if those are the cases).
	 * 
	 * @param node The node to be removed
	 */
	void removeNode(GraphNode node);

	/**
	 * This method removes a node but makes the links from its previous and next nodes.
	 * 
	 * @param node The node to remove
	 * @param useSecondaryEdges If it should consider also the secondary links
	 */
	void jumpOverNode(GraphNode node, boolean useSecondaryEdges);

	/**
	 * This method implements the framework for the "round robin" algorithm.
	 * 
	 * @param executor This object will take care of computing the new set for a given node
	 * @param reverse If true, the depth first tree is used in the opposite order, i.e., from 0 up
	 *        (the normal order is from the top element down).
	 */
	void roundRobinAlgorithm(RoundRobinExecutor executor, boolean reverse);

	/**
	 * This method implements the framework for the "round robin" algorithm.
	 * 
	 * @param executor This object will take care of computing the new set for a given node
	 * @param reverse If true, the depth first tree is used in the opposite order, i.e., from 0 up
	 *        (the normal order is from the top element down).
	 */
	void roundIRobinAlgorithm(RoundRobinExecutor executor, boolean reverse);

	/**
	 * Construct a Depth First Tree sequence of the nodes.
	 * 
	 * @param useSecondaryEdges Whether or not to use secondary edges when calculating "next" nodes.
	 */
	GraphNode[] findDFTNodes(boolean useSecondaryEdges);

	/**
	 * Construct an Inverse Depth First Tree sequence of the nodes.
	 * 
	 * @param useSecondaryEdges Whether or not to use secondary edges when calculating "next" nodes.
	 */
	GraphNode[] findIDFTNodes(boolean useSecondaryEdges);

	/**
	 * Construct an Inverse Depth First Tree sequence of the nodes, from a given node
	 * 
	 * @param useSecondaryEdges Whether or not to use secondary edges when calculating "next" nodes.
	 * @param node The node from where to start
	 */
	GraphNode[] findIDFT(boolean useSecondaryEdges, GraphNode node);


	/**
	 * Computes a simple paths from one node to another
	 * 
	 * @param src - the source node
	 * @param dest - the destination node
	 * @param useSecondaryEdges - indicates whether secondary edges can be used in the paths
	 * 
	 * @return - an array of {@link GraphNode}'s. If one of the nodes is not part of this graph,
	 *         returns null. If there is no such path, returns an array of size 0.
	 */
	GraphNode[] computeSimplePath(GraphNode src, GraphNode dest, boolean useSecondaryEdges);

	/**
	 * Compute the set of Strongly Connected Components of this graph.
	 * 
	 * @return An array where each element corresponds ta a SC component
	 * @param sec - indicates if secondary edges should be considered
	 */
	Set[] computeSCC(boolean sec);

	//Final Basic Block Dominator TREE
	void removeComposite(boolean sec);

	void setDefaultNumbering();
}
