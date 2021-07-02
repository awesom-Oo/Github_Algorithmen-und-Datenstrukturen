package ueb11;

import java.sql.Array;
import java.util.*;

public class AdjacencyListUndirectedWeightedGraph<T> implements UndirectedWeightedGraph<T> {


    class ListNode<T> {
        private int weight;
        private T destNode;

        public ListNode(T destNode, int weight) {
            this.destNode = destNode;
            this.weight = weight;
        }

        public int getWeight() { return weight; }

        public T getDestNode() { return destNode; }
    }


    private HashMap<T, ArrayList<ListNode<T>>> adjList;

    public AdjacencyListUndirectedWeightedGraph() {
        this.adjList = new HashMap<>();
    }

    @Override
    public boolean addNodeElement(T element) {
        if (this.adjList.containsKey(element)) {
            return false;
        }

        this.adjList.put(element, new ArrayList<>());
        return true;
    }

    @Override
    public boolean removeNodeElement(T element) {
        if (!this.adjList.containsKey(element)) {
            return false;
        }

        this.adjList.remove(element);

        for (T t: this.getAllVertices()) {
            this.adjList.get(t).remove(element);
        }

        return true;
    }

    private Iterable<T> getAllVertices() {
        return this.adjList.keySet();
    }

    @Override
    public void addEdge(T t1, T t2, int weight) throws InvalidEdgeException {
        if(!this.adjList.containsKey(t1) || !this.adjList.containsKey(t2)) {
            throw new InvalidEdgeException("Source-edge or destination-edge does not exist");
        }

        adjList.get(t1).add(new ListNode<T>(t2, weight));
        adjList.get(t2).add(new ListNode<T>(t1, weight));

    }

    @Override
    public boolean removeEdge(T t1, T t2) throws InvalidNodeException {
        if (!this.adjList.containsKey(t1) || !this.adjList.containsKey(t2)) {
            throw new InvalidNodeException("Edge does not exist");
        }

        this.adjList.get(t1).remove(t2);
        this.adjList.get(t2).remove(t1);
        return true;
    }

    public static void main(String[] args) throws InvalidEdgeException {
        AdjacencyListUndirectedWeightedGraph<Integer> graph = new AdjacencyListUndirectedWeightedGraph();


        graph.addEdge( 1,  2,  3);
        graph.addEdge( 1,  3,  7);
        graph.addEdge( 2,  4,  6);
        graph.addEdge( 3,  4,  9);

        System.out.println("Hello");
    }
}
