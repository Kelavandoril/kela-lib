package kela.lib.graphs.graph.directed;

import kela.lib.graphs.graph.AbstractGraphTest;
import kela.lib.graphs.node.GraphNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DirectedGraphTest extends AbstractGraphTest {

    @Override
    @BeforeEach
    public void setupEach() {
        graphUnderTest = new DirectedGraph<>(true);
        g1 = new GraphNode<>(1);
        g2 = new GraphNode<>(2);
    }

    @Test
    public void addEdge_UniqueEdge_AddsEdge() {
        graphUnderTest.addVertex(g1);
        graphUnderTest.addVertex(g2);
        assertEquals(2, graphUnderTest.getVertices().size());
        assertEquals(0, graphUnderTest.getEdges().size());
        assertTrue(graphUnderTest.addEdge(g1, g2));
        assertEquals(1, graphUnderTest.getEdges().size());
        assertEquals(1, graphUnderTest.getNeighbors(g1).size());
        assertEquals(0, graphUnderTest.getNeighbors(g2).size());
    }

    @Test
    public void addEdge_IdenticalEdges_DoesntAdd() {
        graphUnderTest.addVertex(g1);
        graphUnderTest.addVertex(g2);
        assertEquals(2, graphUnderTest.getVertices().size());
        assertEquals(0, graphUnderTest.getEdges().size());
        assertTrue(graphUnderTest.addEdge(g1, g2));
        assertEquals(1, graphUnderTest.getEdges().size());
        assertEquals(1, graphUnderTest.getNeighbors(g1).size());
        assertEquals(0, graphUnderTest.getNeighbors(g2).size());
        assertFalse(graphUnderTest.addEdge(g1, g2));
        assertEquals(1, graphUnderTest.getEdges().size());
        assertEquals(1, graphUnderTest.getNeighbors(g1).size());
        assertEquals(0, graphUnderTest.getNeighbors(g2).size());
    }

    @Test
    public void addEdge_NodeNotInGraph_ReturnsFalse() {
        graphUnderTest.addVertex(g1);
        assertEquals(1, graphUnderTest.getVertices().size());
        assertEquals(0, graphUnderTest.getEdges().size());
        assertFalse(graphUnderTest.addEdge(g1, g2));
        assertEquals(0, graphUnderTest.getEdges().size());
        assertEquals(0, graphUnderTest.getNeighbors(g1).size());
        assertEquals(0, graphUnderTest.getNeighbors(g2).size());
    }

    @Test
    public void addEdge_NullNode_ReturnsFalse() {
        graphUnderTest.addVertex(g1);
        assertEquals(1, graphUnderTest.getVertices().size());
        assertEquals(0, graphUnderTest.getEdges().size());
        assertFalse(graphUnderTest.addEdge(g1, null));
        assertEquals(0, graphUnderTest.getEdges().size());
        assertFalse(graphUnderTest.addEdge(null, g1));
        assertEquals(0, graphUnderTest.getEdges().size());
        assertFalse(graphUnderTest.addEdge(null, null));
        assertFalse(graphUnderTest.addEdge(g1, null));
    }

    @Test
    public void removeEdge_EdgeExists_RemovesEdge() {
        graphUnderTest.addVertex(g1);
        graphUnderTest.addVertex(g2);
        assertEquals(2, graphUnderTest.getVertices().size());
        assertEquals(0, graphUnderTest.getEdges().size());
        assertTrue(graphUnderTest.addEdge(g1, g2));
        assertEquals(1, graphUnderTest.getEdges().size());
        assertEquals(1, graphUnderTest.getNeighbors(g1).size());
        assertEquals(0, graphUnderTest.getNeighbors(g2).size());
        assertTrue(graphUnderTest.removeEdge(g1, g2));
        assertEquals(0, graphUnderTest.getEdges().size());
        assertEquals(0, graphUnderTest.getNeighbors(g1).size());
        assertEquals(0, graphUnderTest.getNeighbors(g2).size());
    }

    @Test
    public void removeEdge_EdgeDoesNotExist_DoesNotRemoveEdge() {
        GraphNode<Integer> g3 = new GraphNode<>(3);
        graphUnderTest.addVertex(g1);
        graphUnderTest.addVertex(g2);
        graphUnderTest.addVertex(g3);
        assertEquals(3, graphUnderTest.getVertices().size());
        assertEquals(0, graphUnderTest.getEdges().size());
        assertTrue(graphUnderTest.addEdge(g1, g2));
        assertEquals(1, graphUnderTest.getEdges().size());
        assertEquals(1, graphUnderTest.getNeighbors(g1).size());
        assertEquals(0, graphUnderTest.getNeighbors(g2).size());
        assertEquals(0, graphUnderTest.getNeighbors(g3).size());
        assertFalse(graphUnderTest.removeEdge(g1, g3));
        assertEquals(1, graphUnderTest.getEdges().size());
        assertEquals(1, graphUnderTest.getNeighbors(g1).size());
        assertEquals(0, graphUnderTest.getNeighbors(g2).size());
        assertEquals(0, graphUnderTest.getNeighbors(g3).size());
    }

    @Test
    public void removeEdge_NullEdge_ReturnsFalse() {
        graphUnderTest.addVertex(g1);
        assertEquals(1, graphUnderTest.getVertices().size());
        assertEquals(0, graphUnderTest.getEdges().size());
        assertFalse(graphUnderTest.removeEdge(g1, null));
        assertEquals(0, graphUnderTest.getEdges().size());
        assertFalse(graphUnderTest.removeEdge(null, g1));
        assertEquals(0, graphUnderTest.getEdges().size());
        assertFalse(graphUnderTest.removeEdge(null, null));
        assertEquals(0, graphUnderTest.getEdges().size());
    }
}
