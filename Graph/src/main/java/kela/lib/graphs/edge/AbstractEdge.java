package kela.lib.graphs.edge;

import kela.lib.graphs.node.Node;

import java.util.Objects;

public abstract class AbstractEdge<N extends Node<?>> implements Edge<N> {
    protected N n1;
    protected N n2;

    public AbstractEdge(N n1, N n2) {
        this.n1 = n1;
        this.n2 = n2;
    }

    @Override
    public N getN1() {
        return n1;
    }

    @Override
    public N getN2() {
        return n2;
    }

    public boolean containsNode(N n) {
        return n.equals(n1) || n.equals(n2);
    }

    public boolean equals(Object o) {
        if (!(o instanceof AbstractEdge<?> other))
            return false;
        return other.n1.equals(this.n1) && other.n2.equals(this.n2);
    }
    public int hashCode() {
        return Objects.hash(n1, n2);
    }
    public String toString() {
        return "<"+n1+", "+n2+">";
    }
}
