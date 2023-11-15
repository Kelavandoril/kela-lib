package kela.lib.graphs.edge;

import kela.lib.graphs.node.Node;

import java.util.Objects;

public class UnweightedEdge<N extends Node<?>> extends AbstractEdge<N>{
    public UnweightedEdge(N n1, N n2) {
        super(n1, n2);
    }
}
