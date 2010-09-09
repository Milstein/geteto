package br.jabuti.graph;


import java.util.*;


public interface RoundRobinExecutor {
	
    public Object calcNewSet(GraphNode theNode, 
            Vector primary, 
            Vector secondary);

    public boolean compareEQ(GraphNode theNode, Object theNewSet);

    public void setNewSet(GraphNode theNode, Object theNewSet);	
	
    public void init(GraphNode theNode, Vector primary, Vector secondary);
}
