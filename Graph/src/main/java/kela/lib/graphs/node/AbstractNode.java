package kela.lib.graphs.node;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

public class AbstractNode<T> implements Node<T>{
    private T data;
    protected Collection<Node<?>> adjacentNodes;
    private long creationTime;

    public AbstractNode(T data) {
        this.creationTime = System.currentTimeMillis();
        this.data = data;
    }

    @Override
    public T getData() {
        return data;
    }

    @Override
    public void setData(T data) {
        this.data = data;
    }

    @Override
    public void addAdjacentNode(Node<?> n) {
        adjacentNodes.add(n);
    }

    @Override
    public boolean removeAdjacentNode(Node<?> n) {
        return adjacentNodes.contains(n) && adjacentNodes.removeIf(v -> v.equals(n));
    }

    @Override
    public Collection<? extends Node<?>> getAdjacentNodes() {
        return adjacentNodes;
    }

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof AbstractNode<?> other))
            return false;

        return other.data.equals(this.data) && other.adjacentNodes.equals(this.adjacentNodes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data, creationTime);
    }

    @Override
    public String toString() {
        return data.toString();
    }
}
