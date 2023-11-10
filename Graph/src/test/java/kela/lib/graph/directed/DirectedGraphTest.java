package kela.lib.graph.directed;

import kela.lib.graph.GraphNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DirectedGraphTest {

    private DirectedGraph<Character> graphUnderTest;

    @BeforeEach
    public void setupEach() {
        graphUnderTest = new DirectedGraph<>();
    }

    @Test
    public void removeEdge_NeitherExist_ReturnsFalse() {
        GraphNode<Character> nodeA = new GraphNode<>('A');
        GraphNode<Character> nodeB = new GraphNode<>('B');
        assertFalse(graphUnderTest.removeEdge(nodeA, nodeB));
    }

    @Test
    public void removeEdge_OneExists_ReturnsFalse() {
        GraphNode<Character> nodeA = new GraphNode<>('A');
        GraphNode<Character> nodeB = new GraphNode<>('B');
        GraphNode<Character> nodeC = new GraphNode<>('C');
        graphUnderTest.addVertex(nodeA, nodeB);
        graphUnderTest.addEdge(nodeA, nodeB);
        Assertions.assertFalse(graphUnderTest.getEdges().isEmpty());
        assertFalse(graphUnderTest.removeEdge(nodeA, nodeC));
    }

    @Test
    public void removeEdge_OneNull_ReturnsFalse() {
        GraphNode<Character> nodeA = new GraphNode<>('A');
        graphUnderTest.addVertex(nodeA);
        assertFalse(graphUnderTest.removeEdge(nodeA, null));
    }

    @Test
    public void removeEdge_BothExistOutOfOrder_ReturnsFalse() {
        GraphNode<Character> nodeA = new GraphNode<>('A');
        GraphNode<Character> nodeB = new GraphNode<>('B');
        graphUnderTest.addVertex(nodeA, nodeB);
        graphUnderTest.addEdge(nodeA, nodeB);
        Assertions.assertFalse(graphUnderTest.getEdges().isEmpty());
        assertFalse(graphUnderTest.removeEdge(nodeB, nodeA));
    }

    @Test
    public void removeEdge_BothExistInOrder_ReturnsTrue() {
        GraphNode<Character> nodeA = new GraphNode<>('A');
        GraphNode<Character> nodeB = new GraphNode<>('B');
        graphUnderTest.addVertex(nodeA, nodeB);
        graphUnderTest.addEdge(nodeA, nodeB);
        Assertions.assertFalse(graphUnderTest.getEdges().isEmpty());
        assertTrue(graphUnderTest.removeEdge(nodeA, nodeB));
    }

    @Test
    public void removeEdge_MultiEdge_ReturnsTrue() {
        GraphNode<Character> nodeA = new GraphNode<>('A');
        GraphNode<Character> nodeB = new GraphNode<>('B');
        graphUnderTest.addVertex(nodeA, nodeB);
        graphUnderTest.addEdge(nodeA, nodeB);
        graphUnderTest.addEdge(nodeA, nodeB);
        Assertions.assertEquals(2, graphUnderTest.getEdges().size());
        assertTrue(graphUnderTest.removeEdge(nodeA, nodeB));
        Assertions.assertTrue(graphUnderTest.getEdges().isEmpty());
    }

    @Test
    public void isAdjacent_NoEdge_ReturnsFalse() {
        GraphNode<Character> nodeA = new GraphNode<>('A');
        GraphNode<Character> nodeB = new GraphNode<>('B');
        GraphNode<Character> nodeC = new GraphNode<>('C');
        graphUnderTest.addVertex(nodeA, nodeB, nodeC);
        graphUnderTest.addEdge(nodeA, nodeC);
        Assertions.assertFalse(graphUnderTest.getEdges().isEmpty());
        assertFalse(graphUnderTest.isAdjacent(nodeA, nodeB));
    }

    @Test
    public void isAdjacent_OneEdgeOutOfOrder_ReturnsFalse() {
        GraphNode<Character> nodeA = new GraphNode<>('A');
        GraphNode<Character> nodeB = new GraphNode<>('B');
        graphUnderTest.addVertex(nodeA, nodeB);
        graphUnderTest.addEdge(nodeA, nodeB);
        Assertions.assertFalse(graphUnderTest.getEdges().isEmpty());
        assertFalse(graphUnderTest.isAdjacent(nodeB, nodeA));
    }

    @Test
    public void isAdjacent_OneEdgeInOrder_ReturnsTrue() {
        GraphNode<Character> nodeA = new GraphNode<>('A');
        GraphNode<Character> nodeB = new GraphNode<>('B');
        graphUnderTest.addVertex(nodeA, nodeB);
        graphUnderTest.addEdge(nodeA, nodeB);
        Assertions.assertFalse(graphUnderTest.getEdges().isEmpty());
        assertTrue(graphUnderTest.isAdjacent(nodeA, nodeB));
    }

    @Test
    public void isAdjacent_MultiEdge_ReturnsTrue() {
        GraphNode<Character> nodeA = new GraphNode<>('A');
        GraphNode<Character> nodeB = new GraphNode<>('B');
        graphUnderTest.addVertex(nodeA, nodeB);
        graphUnderTest.addEdge(nodeA, nodeB);
        graphUnderTest.addEdge(nodeA, nodeB);
        Assertions.assertEquals(2, graphUnderTest.getEdges().size());
        assertTrue(graphUnderTest.isAdjacent(nodeA, nodeB));
    }

    @Test
    public void isAdjacent_OneNull_ReturnsFalse() {
        GraphNode<Character> nodeA = new GraphNode<>('A');
        graphUnderTest.addVertex(nodeA);
        assertFalse(graphUnderTest.isAdjacent(nodeA, null));
    }

    @Test
    public void getNeighbors_NeighborsOutOfOrder_ReturnsEmptySet() {
        GraphNode<Character> nodeA = new GraphNode<>('A');
        GraphNode<Character> nodeB = new GraphNode<>('B');
        GraphNode<Character> nodeC = new GraphNode<>('C');
        GraphNode<Character> nodeD = new GraphNode<>('D');
        graphUnderTest.addVertex(nodeA, nodeB, nodeC, nodeD);
        graphUnderTest.addEdge(nodeB, nodeA);
        graphUnderTest.addEdge(nodeC, nodeA);
        graphUnderTest.addEdge(nodeB, nodeD);
        graphUnderTest.addEdge(nodeD, nodeC);
        Set<GraphNode<Character>> goldenNeighborsSet = Set.of(nodeB, nodeC);
        Assertions.assertEquals(4, graphUnderTest.getEdges().size());
        assertNotEquals(goldenNeighborsSet, graphUnderTest.getNeighbors(nodeA));
    }

    @Test
    public void getNeighbors_NeighborsInOrder_ReturnsProperSet() {
        GraphNode<Character> nodeA = new GraphNode<>('A');
        GraphNode<Character> nodeB = new GraphNode<>('B');
        GraphNode<Character> nodeC = new GraphNode<>('C');
        GraphNode<Character> nodeD = new GraphNode<>('D');
        graphUnderTest.addVertex(nodeA, nodeB, nodeC, nodeD);
        graphUnderTest.addEdge(nodeA, nodeB);
        graphUnderTest.addEdge(nodeA, nodeC);
        graphUnderTest.addEdge(nodeB, nodeD);
        graphUnderTest.addEdge(nodeD, nodeC);
        Set<GraphNode<Character>> goldenNeighborsSet = Set.of(nodeB, nodeC);
        Assertions.assertEquals(4, graphUnderTest.getEdges().size());
        assertEquals(goldenNeighborsSet, graphUnderTest.getNeighbors(nodeA));
    }

    @Test
    public void getNeighbors_NoNeighbors_ReturnsEmptySet() {
        GraphNode<Character> nodeA = new GraphNode<>('A');
        GraphNode<Character> nodeB = new GraphNode<>('B');
        GraphNode<Character> nodeC = new GraphNode<>('C');
        GraphNode<Character> nodeD = new GraphNode<>('D');
        graphUnderTest.addVertex(nodeA, nodeB, nodeC, nodeD);
        graphUnderTest.addEdge(nodeB, nodeB);
        graphUnderTest.addEdge(nodeB, nodeC);
        graphUnderTest.addEdge(nodeB, nodeD);
        graphUnderTest.addEdge(nodeD, nodeC);
        Set<GraphNode<Character>> goldenNeighborsSet = new HashSet<>();
        Assertions.assertEquals(4, graphUnderTest.getEdges().size());
        assertEquals(goldenNeighborsSet, graphUnderTest.getNeighbors(nodeA));
    }
}
