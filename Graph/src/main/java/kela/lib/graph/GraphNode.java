package kela.lib.graph;

public class GraphNode<T> {
    
    private T data;

    public GraphNode() {}

    public GraphNode(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
