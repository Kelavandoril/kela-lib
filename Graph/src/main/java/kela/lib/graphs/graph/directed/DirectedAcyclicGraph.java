package kela.lib.graphs.graph.directed;

import kela.lib.graphs.edge.UnweightedEdge;
import kela.lib.graphs.node.GraphNode;
import kela.lib.graphs.node.Node;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class DirectedAcyclicGraph<N extends GraphNode<?>> extends DirectedGraph<N> {
    public DirectedAcyclicGraph() {
        this(true);
    }

    public DirectedAcyclicGraph(boolean isSimple) {
        super(isSimple);
    }

    @Override
    public boolean addEdge(N n1, N n2) {
        return super.addEdge(n1, n2) && !(hasCycle(n1) && removeEdge(n1, n2));
    }

    private boolean hasCycle(N n) {
        Set<Node<?>> greyNodes = new HashSet<>(); // Grey
        Set<Node<?>> whiteNodes = new HashSet<>(vertices); // White
        Stack<Node<?>> nodeStack = new Stack<>();
        nodeStack.push(n);
        while (!nodeStack.isEmpty()) {
            Node<?> node = nodeStack.peek();
            if (!greyNodes.contains(node)) {
                whiteNodes.remove(node);
                greyNodes.add(node);
                for (Node<?> neighbor : node.getAdjacentNodes()) {
                    if (whiteNodes.contains(neighbor))
                        nodeStack.add(neighbor);
                    else if (greyNodes.contains(neighbor))
                        return true;
                }
            } else {
                nodeStack.pop();
                greyNodes.remove(node);
            }
        }
        return false;
    }
}
