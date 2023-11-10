package kela.lib.graph.undirected;

import kela.lib.graph.AbstractGraph;
import kela.lib.graph.GraphNode;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UndirectedGraph<T> extends AbstractGraph<T> {


    @Override
    public boolean removeEdge(GraphNode<T> v1, GraphNode<T> v2) {
        return edges.removeIf(edge -> edge.contains(v1) && edge.contains(v2));
    }

    @Override
    public boolean isAdjacent(GraphNode<T> v1, GraphNode<T> v2) {
        return edges.stream().anyMatch(edge -> edge.contains(v1) && edge.contains(v2));
    }

    @Override
    public Set<GraphNode<T>> getNeighbors(GraphNode<T> vertex) {
        Set<GraphNode<T>> neighbors = new HashSet<>();
        for (List<GraphNode<T>> edge : edges) {
            if (edge.contains(vertex)) {
                neighbors.addAll(edge.stream().filter(e -> !e.equals(vertex)).toList());
            }
        }
        return neighbors;
    }
}
