package kela.lib.graph;

import kela.lib.graph.undirected.UndirectedGraph;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AbstractGraphTest {

    private AbstractGraph<Character> graphUnderTest;

    @BeforeEach
    public void setupEach() {
        graphUnderTest = new UndirectedGraph<>();
    }

    @Test
    public void removeVertex_VertexExistsNoEdges_RemovesVertex() {
        GraphNode<Character> node = new GraphNode<>('A');
        graphUnderTest.addVertex(node);
        assertTrue(graphUnderTest.getVertices().contains(node));
        assertTrue(graphUnderTest.removeVertex(node));
    }

    @Test
    public void removeVertex_VertexExistsWithEdge_RemovesVertexAndEdge() {
        GraphNode<Character> nodeA = new GraphNode<>('A');
        GraphNode<Character> nodeB = new GraphNode<>('B');
        graphUnderTest.addVertex(nodeA, nodeB);
        graphUnderTest.addEdge(nodeA, nodeB);
        assertTrue(graphUnderTest.getVertices().contains(nodeA));
        assertTrue(graphUnderTest.getVertices().contains(nodeB));
        assertFalse(graphUnderTest.getEdges().isEmpty());
        assertTrue(graphUnderTest.removeVertex(nodeA));
        assertFalse(graphUnderTest.getVertices().contains(nodeA));
        assertTrue(graphUnderTest.getVertices().contains(nodeB));
        assertTrue(graphUnderTest.getEdges().isEmpty());
    }

    @Test
    public void removeVertex_VertexDoesNotExist_ReturnsFalse() {
        assertTrue(graphUnderTest.getVertices().isEmpty());
        assertTrue(graphUnderTest.getEdges().isEmpty());
        assertFalse(graphUnderTest.removeVertex(null));
    }

    @Test
    public void addEdge_NullV1_ReturnsFalse() {
        GraphNode<Character> nodeA = new GraphNode<>('A');
        assertFalse(graphUnderTest.addEdge(null, nodeA));
    }

    @Test
    public void addEdge_NullV2_ReturnsFalse() {
        GraphNode<Character> nodeA = new GraphNode<>('A');
        assertFalse(graphUnderTest.addEdge(nodeA, null));
    }

    @Test
    public void addEdge_BothExist_AddsEdge() {
        GraphNode<Character> nodeA = new GraphNode<>('A');
        GraphNode<Character> nodeB = new GraphNode<>('B');
        graphUnderTest.addVertex(nodeA, nodeB);
        assertTrue(graphUnderTest.getEdges().isEmpty());
        assertTrue(graphUnderTest.addEdge(nodeA, nodeB));
        assertFalse(graphUnderTest.getEdges().isEmpty());
    }

    @Test
    public void addEdge_NeitherExist_ReturnsFalse() {
        GraphNode<Character> nodeA = new GraphNode<>('A');
        GraphNode<Character> nodeB = new GraphNode<>('B');
        assertTrue(graphUnderTest.getEdges().isEmpty());
        assertFalse(graphUnderTest.addEdge(nodeA, nodeB));
    }
}
