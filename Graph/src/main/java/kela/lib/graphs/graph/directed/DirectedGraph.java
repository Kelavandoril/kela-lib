package kela.lib.graphs.graph.directed;

import kela.lib.graphs.edge.UnweightedEdge;
import kela.lib.graphs.graph.AbstractGraph;
import kela.lib.graphs.node.GraphNode;

public class DirectedGraph<N extends GraphNode<?>> extends AbstractGraph<N> {
    public DirectedGraph() {
        this(true);
    }
    public DirectedGraph(boolean isSimple) {
        super(isSimple);
    }
    @Override
    public boolean addEdge(N n1, N n2) {
        if (!vertices.contains(n1) || !vertices.contains(n2))
            return false;
        UnweightedEdge<N> edge = new UnweightedEdge<>(n1, n2);
        n1.addAdjacentNode(n2);
        return edges.add(edge);
    }

    @Override
    public boolean removeEdge(N n1, N n2) {
        UnweightedEdge<N> edge = new UnweightedEdge<>(n1, n2);
        if (edges.remove(edge))
            return n1.removeAdjacentNode(n2);
        return false;
    }
}
