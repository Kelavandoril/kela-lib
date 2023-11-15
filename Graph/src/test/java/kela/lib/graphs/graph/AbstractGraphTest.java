package kela.lib.graphs.graph;

import kela.lib.graphs.graph.undirected.UndirectedGraph;
import kela.lib.graphs.node.GraphNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class AbstractGraphTest {
    protected AbstractGraph<GraphNode<Integer>> graphUnderTest;
    protected GraphNode<Integer> g1;
    protected GraphNode<Integer> g2;

    @BeforeEach
    public void setupEach() {
        graphUnderTest = new UndirectedGraph<>(true);
        g1 = new GraphNode<>(1);
        g2 = new GraphNode<>(2);
    }

    @Test
    public void isSimple_PassInFalse_DuplicateEdgesAllowed() {
        graphUnderTest = new UndirectedGraph<>(false);
        graphUnderTest.addVertex(g1);
        graphUnderTest.addVertex(g2);
        graphUnderTest.addEdge(g1, g2);
        graphUnderTest.addEdge(g1, g2);
        assertEquals(2, graphUnderTest.getSize());
        assertEquals(2, graphUnderTest.getOrder());
    }

    @Test
    public void isSimple_PassInTrue_DuplicateEdgesProhibited() {
        graphUnderTest = new UndirectedGraph<>(true);
        graphUnderTest.addVertex(g1);
        graphUnderTest.addVertex(g2);
        graphUnderTest.addEdge(g1, g2);
        graphUnderTest.addEdge(g1, g2);
        assertEquals(1, graphUnderTest.getSize());
        assertEquals(2, graphUnderTest.getOrder());
    }

    @Test
    public void addVertex_ValidNode_VertexAdded() {
        assertEquals(0, graphUnderTest.getOrder());
        assertTrue(graphUnderTest.addVertex(g1));
        assertEquals(1, graphUnderTest.getOrder());
        assertEquals(g1, graphUnderTest.getVertices().stream().findFirst().get());
    }

    @Test
    public void addVertex_NullNode_NotAdded() {
        assertEquals(0, graphUnderTest.getOrder());
        assertFalse(graphUnderTest.addVertex(null));
        assertEquals(0, graphUnderTest.getOrder());
    }

    @Test
    public void removeVertex_ValidNode_VertexRemoved() {
        graphUnderTest.addVertex(g1);
        graphUnderTest.addVertex(g2);
        graphUnderTest.addEdge(g1, g2);
        assertEquals(2, graphUnderTest.getOrder());
        assertEquals(1, graphUnderTest.getSize());
        assertTrue(graphUnderTest.removeVertex(g1));
        assertEquals(1, graphUnderTest.getOrder());
        assertEquals(g2, graphUnderTest.getVertices().stream().findFirst().get());
        assertEquals(0, graphUnderTest.getSize());
        assertTrue(g2.getAdjacentNodes().isEmpty());
    }

    @Test
    public void removeVertex_ValidButNotInGraph_ReturnsFalse() {
        GraphNode<Integer> g3 = new GraphNode<>(3);
        graphUnderTest.addVertex(g1);
        graphUnderTest.addVertex(g2);
        graphUnderTest.addEdge(g1, g2);
        assertEquals(2, graphUnderTest.getOrder());
        assertEquals(1, graphUnderTest.getSize());
        assertFalse(graphUnderTest.removeVertex(g3));
        assertEquals(2, graphUnderTest.getOrder());
        assertEquals(1, graphUnderTest.getSize());
    }

    @Test
    public void removeVertex_NullNode_ReturnsFalse() {
        graphUnderTest.addVertex(g1);
        graphUnderTest.addVertex(g2);
        graphUnderTest.addEdge(g1, g2);
        assertEquals(2, graphUnderTest.getOrder());
        assertEquals(1, graphUnderTest.getSize());
        assertFalse(graphUnderTest.removeVertex(null));
        assertEquals(2, graphUnderTest.getOrder());
        assertEquals(1, graphUnderTest.getSize());
    }

    @Test
    public void isAdjacent_AdjNode_ReturnsTrue() {
        graphUnderTest.addVertex(g1);
        graphUnderTest.addVertex(g2);
        graphUnderTest.addEdge(g1, g2);
        assertTrue(graphUnderTest.isAdjacent(g1, g2));
    }

    @Test
    public void isAdjacent_NonAdjNode_ReturnsFalse() {
        GraphNode<Integer> g3 = new GraphNode<>(3);
        graphUnderTest.addVertex(g1);
        graphUnderTest.addVertex(g2);
        graphUnderTest.addVertex(g3);
        graphUnderTest.addEdge(g1, g2);
        assertFalse(graphUnderTest.isAdjacent(g1, g3));
    }

    @Test
    public void isAdjacent_NullNode_ReturnsFalse() {
        assertFalse(graphUnderTest.isAdjacent(null, g1));
        assertFalse(graphUnderTest.isAdjacent(g1, null));
        assertFalse(graphUnderTest.isAdjacent(null, null));
    }

    @Test
    public void getNeighbors_NodesAreAdjacent_ReturnsProperNeighbors() {
        GraphNode<Integer> g3 = new GraphNode<>(3);
        graphUnderTest.addVertex(g1);
        graphUnderTest.addVertex(g2);
        graphUnderTest.addVertex(g3);
        graphUnderTest.addEdge(g1, g2);
        graphUnderTest.addEdge(g1, g3);
        assertTrue(graphUnderTest.isAdjacent(g1, g2));
        assertTrue(graphUnderTest.isAdjacent(g1, g3));
        assertEquals(Set.of(g2, g3), graphUnderTest.getNeighbors(g1));
    }

    @Test
    public void getNeighbors_NodesAreNotAdjacent_ReturnsEmptyList() {
        GraphNode<Integer> g3 = new GraphNode<>(3);
        graphUnderTest.addVertex(g1);
        graphUnderTest.addVertex(g2);
        graphUnderTest.addVertex(g3);
        graphUnderTest.addEdge(g2, g3);
        assertFalse(graphUnderTest.isAdjacent(g1, g2));
        assertFalse(graphUnderTest.isAdjacent(g1, g3));
        assertEquals(Set.of(), graphUnderTest.getNeighbors(g1));
    }

    @Test
    public void getNeighbors_NodeNotInGraph_ExcludesThatNode() {
        GraphNode<Integer> g3 = new GraphNode<>(3);
        graphUnderTest.addVertex(g1);
        graphUnderTest.addVertex(g2);
        graphUnderTest.addEdge(g1, g2);
        assertEquals(Set.of(), graphUnderTest.getNeighbors(g3));
    }

    @Test
    public void getNeighbors_PassInNull_ReturnsEmptyCollection() {
        Collection<?> returnStructure = graphUnderTest.getNeighbors(null);
        assertNotNull(returnStructure);
        assertTrue(returnStructure.isEmpty());
    }
}
