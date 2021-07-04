package ueb11;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdjacencyListUndirectedGraphTest {

    @org.junit.jupiter.api.Test
    void addNodeElement() {
        AdjacencyListUndirectedWeightedGraph<Integer> graph = new AdjacencyListUndirectedWeightedGraph<Integer>();
        graph.addNodeElement(1);
        graph.addNodeElement(2);
        graph.addNodeElement(3);
        graph.addNodeElement(4);
        graph.addNodeElement(5);
        graph.addNodeElement(6);
        graph.addNodeElement(7);
        graph.addNodeElement(8);
        graph.addNodeElement(9);
    }

    @org.junit.jupiter.api.Test
    void removeNodeElement() {
        AdjacencyListUndirectedWeightedGraph<Integer> graph = new AdjacencyListUndirectedWeightedGraph<>();
        graph.addNodeElement(1);
        graph.addNodeElement(2);
        graph.addNodeElement(3);
        graph.addNodeElement(4);
        graph.addNodeElement(5);
        graph.addNodeElement(6);
        graph.addNodeElement(7);
        graph.addNodeElement(8);
        graph.addNodeElement(9);

        graph.removeNodeElement(3);
        graph.removeNodeElement(4);
        graph.removeNodeElement(8);


    }

    @org.junit.jupiter.api.Test
    void addEdge() {
        AdjacencyListUndirectedWeightedGraph<Integer> graph = new AdjacencyListUndirectedWeightedGraph<>();
        graph.addNodeElement(1);
        graph.addNodeElement(2);
        graph.addNodeElement(3);
        graph.addNodeElement(4);
        graph.addNodeElement(5);
        graph.addNodeElement(6);
        graph.addNodeElement(7);
        graph.addNodeElement(8);
        graph.addNodeElement(9);

        try {
            graph.addEdge(1, 4, 12);
            graph.addEdge(1, 5, 2);
            graph.addEdge(1, 6, 53);
            graph.addEdge(1, 7, 44);
        } catch (UndirectedWeightedGraph.InvalidEdgeException ignored) {
            assertTrue(false);
        }

        System.out.println(graph);
    }

    @org.junit.jupiter.api.Test
    void removeEdge() {
        AdjacencyListUndirectedWeightedGraph<Integer> graph = new AdjacencyListUndirectedWeightedGraph<>();
        graph.addNodeElement(1);
        graph.addNodeElement(2);
        graph.addNodeElement(3);
        graph.addNodeElement(4);
        graph.addNodeElement(5);
        graph.addNodeElement(6);
        graph.addNodeElement(7);
        graph.addNodeElement(8);
        graph.addNodeElement(9);

        try {
            graph.addEdge(1, 4, 12);
            graph.addEdge(1, 5, 2);
            graph.addEdge(1, 6, 53);
            graph.addEdge(1, 7, 44);
        } catch (UndirectedWeightedGraph.InvalidEdgeException ignored) {
            assertTrue(false);
        }

        System.out.println(graph);

        try {
            graph.removeEdge(1, 4);
        } catch (UndirectedWeightedGraph.InvalidNodeException e) {
            e.printStackTrace();
        }

        assertThrows(UndirectedWeightedGraph.InvalidNodeException.class , () -> { graph.removeEdge(-1, 1);});

        System.out.println(graph);




    }

    @Test
    void kruskal() {
        AdjacencyListUndirectedWeightedGraph<Integer> graph = new AdjacencyListUndirectedWeightedGraph<>();
        graph.addNodeElement(1);
        graph.addNodeElement(2);
        graph.addNodeElement(3);
        graph.addNodeElement(4);
        graph.addNodeElement(5);
        graph.addNodeElement(6);
        graph.addNodeElement(7);
        graph.addNodeElement(8);


        try {
            graph.addEdge(1, 4, 12);
            graph.addEdge(1, 3, 2);
            graph.addEdge(1, 2, 53);
            graph.addEdge(1, 5, 44);
            graph.addEdge(6, 5, 12);
            graph.addEdge(2, 3, 1);
            graph.addEdge(5, 4, 4);
            graph.addEdge(2, 4, 3);
            graph.addEdge(2, 7, 45);
            graph.addEdge(8, 7, 4);
        } catch (UndirectedWeightedGraph.InvalidEdgeException ignored) {
            assertTrue(false);
        }
        graph.kruskal();
    }
}
