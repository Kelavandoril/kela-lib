package kela.lib.graphs.edge;

import kela.lib.graphs.node.Node;

public interface Edge<N extends Node<?>>{
    N getN1();
    N getN2();
    boolean containsNode(N n);
}
