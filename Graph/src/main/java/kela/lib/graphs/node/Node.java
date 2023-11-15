package kela.lib.graphs.node;

import java.util.Collection;

public interface Node<T>{

    T getData();
    void setData(T data);
    void addAdjacentNode(Node<?> n);
    boolean removeAdjacentNode(Node<?> n);
    Collection<? extends Node<?>> getAdjacentNodes();
}
