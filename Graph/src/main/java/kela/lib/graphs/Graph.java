package kela.lib.graphs;

import kela.lib.graphs.node.Node;

import java.util.Collection;

public interface Graph<N extends Node<?>>{
    boolean isAdjacent(N n1, N n2);
    Collection<? extends Node<?>> getNeighbors(N n);
    boolean addVertex(N n);
    boolean removeVertex(N n);
    boolean addEdge(N n1, N n2);
    boolean removeEdge(N n1, N n2);
}
