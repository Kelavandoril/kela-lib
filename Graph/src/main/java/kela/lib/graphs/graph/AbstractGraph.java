package kela.lib.graphs.graph;

import kela.lib.graphs.Graph;
import kela.lib.graphs.edge.Edge;
import kela.lib.graphs.node.GraphNode;
import kela.lib.graphs.node.Node;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

public abstract class AbstractGraph<N extends GraphNode<?>> implements Graph<N> {
    protected Collection<N> vertices;
    protected Collection<Edge<N>> edges;

    public AbstractGraph() {
        this(true);
    }

    public AbstractGraph(boolean isSimple) {
        this.vertices = new HashSet<>();
        edges = isSimple ? new HashSet<>() : new ArrayList<>();
    }

    @Override
    public boolean addVertex(N n) {
        return n != null && vertices.add(n);
    }

    public boolean removeVertex(N n) {
        if (n == null || !vertices.contains(n))
            return false;
        vertices.forEach(v -> v.getAdjacentNodes().removeIf(adjNode -> adjNode.equals(n)));
        return vertices.remove(n) &&
                (edges.stream().noneMatch(edge -> edge.containsNode(n)) || edges.removeIf(edge -> edge.containsNode(n)));
    }

    @Override
    public boolean isAdjacent(N n1, N n2) {
        return getNeighbors(n1).contains(n2);
    }

    @Override
    public Collection<? extends Node<?>> getNeighbors(N n) {
        return vertices.contains(n) ? n.getAdjacentNodes() : Collections.emptySet();
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

    public boolean containsVertex(N vertex) {
        return vertices.contains(vertex);
    }

    public int getOrder() {
        return vertices.size();
    }

    public int getSize() {
        return edges.size();
    }

    public int getVertexDegree(N n) { return getNeighbors(n).size(); }

    public Collection<N> getVertices() {
        return vertices;
    }

    public Collection<Edge<N>> getEdges() {
        return edges;
    }
}