package kela.lib.graph;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public abstract class AbstractGraph<T> {

    protected Set<GraphNode<T>> vertices;

    protected List<List<GraphNode<T>>> edges;

    public AbstractGraph() {
        this.vertices = new HashSet<>();
        this.edges = new LinkedList<>();
    }

    public AbstractGraph(GraphNode<T>... vertices) {
        this.vertices = new HashSet<>(List.of(vertices));
    }

    public boolean addVertex(GraphNode<T>... vertex) {
        return vertices.addAll(List.of(vertex));
    }

    public boolean removeVertex(GraphNode<T> vertex) {
        return vertices.remove(vertex) && (edges.stream().noneMatch(edge -> edge.contains(vertex)) || edges.removeIf(edge -> edge.contains(vertex)));
    }

    public boolean addEdge(GraphNode<T> v1, GraphNode<T> v2) {
        if (vertices.contains(v1) && vertices.contains(v2)) {
            LinkedList<GraphNode<T>> edge = new LinkedList<>();
            edge.add(v1);
            edge.add(v2);
            edges.add(edge);
            return true;
        }
        return false;
    }

    public boolean isEmpty() {
        return vertices.isEmpty() && edges.isEmpty();
    }

    public boolean isTrivial() {
        return vertices.size() == 1 && edges.isEmpty();
    }

    public boolean isEdgeless() {
        return !vertices.isEmpty() && edges.isEmpty();
    }

    public boolean containsVertex(GraphNode<T> vertex) {
        return vertices.contains(vertex);
    }

    public int getVertexCount() {
        return vertices.size();
    }

    public int getEdgeCount() {
        return edges.size();
    }

    public abstract boolean removeEdge(GraphNode<T> v1, GraphNode<T> v2);

    public abstract boolean isAdjacent(GraphNode<T> v1, GraphNode<T> v2);

    public abstract Set<GraphNode<T>> getNeighbors(GraphNode<T> vertex);

    public Set<GraphNode<T>> getVertices() {
        return vertices;
    }

    public void setVertices(Set<GraphNode<T>> vertices) {
        this.vertices = vertices;
    }

    public List<List<GraphNode<T>>> getEdges() {
        return edges;
    }

    public void setEdges(List<List<GraphNode<T>>> edges) {
        this.edges = edges;
    }
}