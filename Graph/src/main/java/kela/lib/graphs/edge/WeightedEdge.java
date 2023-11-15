package kela.lib.graphs.edge;

import kela.lib.graphs.node.Node;

import java.util.Objects;

public class WeightedEdge<N extends Node<?>> extends AbstractEdge<N>{
    private int weight;

    public WeightedEdge(N n1, N n2) {
        this(n1, n2, 0);
    }

    public WeightedEdge(N n1, N n2, int weight) {
        super(n1, n2);
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof WeightedEdge<?> other))
            return false;
        return super.equals(other) && other.weight == this.weight;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), weight);
    }

    @Override
    public String toString() {
        return "["+weight+","+super.toString()+"]";
    }
}
