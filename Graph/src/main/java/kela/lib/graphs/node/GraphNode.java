package kela.lib.graphs.node;

import java.util.HashSet;

public class GraphNode<T> extends AbstractNode<T>{
    public GraphNode(T data) {
        super(data);
        adjacentNodes = new HashSet<>();
    }
}
