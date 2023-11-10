package kela.lib.graph.directed;

import kela.lib.graph.AbstractGraph;
import kela.lib.graph.GraphNode;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DirectedGraph<T> extends AbstractGraph<T> {
    @Override
    public boolean removeEdge(GraphNode<T> v1, GraphNode<T> v2) {
        return edges.removeIf(edge -> edge.get(0).equals(v1) && edge.get(1).equals(v2));
    }

    @Override
    public boolean isAdjacent(GraphNode<T> v1, GraphNode<T> v2) {
        return edges.stream().anyMatch(edge -> edge.get(0).equals(v1) && edge.get(1).equals(v2));
    }

    @Override
    public Set<GraphNode<T>> getNeighbors(GraphNode<T> vertex) {
        Set<GraphNode<T>> neighbors = new HashSet<>();
        for(List<GraphNode<T>> edge : edges) {
            if (edge.get(0).equals(vertex)) {
                neighbors.add(edge.get(1));
            }
        }
        return neighbors;
    }
}
