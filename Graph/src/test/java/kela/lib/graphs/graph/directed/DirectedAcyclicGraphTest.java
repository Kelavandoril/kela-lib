package kela.lib.graphs.graph.directed;

import kela.lib.graphs.graph.AbstractGraphTest;
import kela.lib.graphs.node.GraphNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DirectedAcyclicGraphTest extends AbstractGraphTest {

    @Override
    @BeforeEach
    public void setupEach() {
        graphUnderTest = new DirectedAcyclicGraph<>();
        g1 = new GraphNode<>(1);
        g2 = new GraphNode<>(2);
    }

    @Test
    public void addEdge_AddingNonCyclicEdge_ReturnsTrue() {
        GraphNode<Integer> g3 = new GraphNode<>(3);
        graphUnderTest.addVertex(g1);
        graphUnderTest.addVertex(g2);
        graphUnderTest.addVertex(g3);
        assertEquals(3, graphUnderTest.getOrder());
        assertEquals(0, graphUnderTest.getSize());
        assertTrue(graphUnderTest.addEdge(g1, g2));
        assertTrue(graphUnderTest.addEdge(g2, g3));
        assertEquals(2, graphUnderTest.getSize());
        assertTrue(graphUnderTest.addEdge(g1, g3));
        assertEquals(3, graphUnderTest.getSize());
    }

    @Test
    public void addEdge_AddingCyclicEdge_ReturnsFalse() {
        GraphNode<Integer> g3 = new GraphNode<>(3);
        graphUnderTest.addVertex(g1);
        graphUnderTest.addVertex(g2);
        graphUnderTest.addVertex(g3);
        assertEquals(3, graphUnderTest.getOrder());
        assertEquals(0, graphUnderTest.getSize());
        assertTrue(graphUnderTest.addEdge(g1, g2));
        assertTrue(graphUnderTest.addEdge(g2, g3));
        assertEquals(2, graphUnderTest.getSize());
        assertFalse(graphUnderTest.addEdge(g3, g1));
        assertEquals(2, graphUnderTest.getSize());
    }

    @Test
    public void addEdge_NullNodes_ReturnsFalse() {
        graphUnderTest.addVertex(g1);
        graphUnderTest.addVertex(g2);
        assertEquals(2, graphUnderTest.getOrder());
        assertEquals(0, graphUnderTest.getSize());
        assertFalse(graphUnderTest.addEdge(g1, null));
        assertEquals(0, graphUnderTest.getSize());
        assertFalse(graphUnderTest.addEdge(null, g1));
        assertEquals(0, graphUnderTest.getSize());
        assertFalse(graphUnderTest.addEdge(null, null));
        assertEquals(0, graphUnderTest.getSize());
    }

    @Test
    public void addEdge_NodeNotInGraph_ReturnsFalse() {
        graphUnderTest.addVertex(g1);
        assertEquals(1, graphUnderTest.getOrder());
        assertEquals(0, graphUnderTest.getSize());
        assertFalse(graphUnderTest.addEdge(g1, g2));
        assertEquals(0, graphUnderTest.getSize());
    }
}
