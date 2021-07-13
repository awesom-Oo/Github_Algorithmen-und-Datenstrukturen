package ueb11;

import java.util.*;


public class AdjacencyListUndirectedWeightedGraph<T> implements UndirectedWeightedGraph<T> {

    static class Edge<T>  {
        private int weight;
        private T srcNode, destNode;

        public Edge(T destNode, int weight) {
            this.destNode = destNode;
            this.weight = weight;
        }

        public Edge(T srcNode, T destNode, int weight) {
            this.srcNode = srcNode;
            this.destNode = destNode;
            this.weight = weight;
        }

        public int getWeight() { return weight; }

        public T getDestNode() { return destNode; }

        @Override
        public String toString() {
            return "{" + srcNode + ", " + destNode + ", " + weight + "} ";
        }

    }


    private HashMap<T, ArrayList<Edge<T>>> adjList;
    ArrayList<Edge<T>> edges = new ArrayList<>();
    private int nodeCount;

    public AdjacencyListUndirectedWeightedGraph() {
        this.adjList = new HashMap<>();
    }

    @Override
    public boolean addNodeElement(T element) {
        if (this.adjList.containsKey(element)) {
            return false;
        }

        this.adjList.put(element, new ArrayList<>());
        nodeCount ++;
        return true;
    }

    @Override
    public boolean removeNodeElement(T element) {
        if (!this.adjList.containsKey(element)) {
            return false;
        }

        this.adjList.remove(element);
        nodeCount --;
        return true;
    }

    private Iterable<T> getAllNodes() {
        return this.adjList.keySet();
    }

    @Override
    public void addEdge(T t1, T t2, int weight) throws InvalidEdgeException {
        if(!this.adjList.containsKey(t1) || !this.adjList.containsKey(t2)) {
            throw new InvalidEdgeException("Source-edge or destination-edge does not exist");
        }

        adjList.get(t1).add(new Edge<T>(t2, weight));
        adjList.get(t2).add(new Edge<T>(t1, weight));

        edges.add(new Edge<T>(t1, t2, weight));
    }

    @Override
    public boolean removeEdge(T t1, T t2) throws InvalidNodeException {
        if (!this.adjList.containsKey(t1) || !this.adjList.containsKey(t2)) {
            throw new InvalidNodeException("Edge does not exist");
        }

        Iterator<Edge<T>> t1Iter = adjList.get(t1).iterator();
        Iterator<Edge<T>> t2Iter = adjList.get(t2).iterator();

        while (t1Iter.hasNext()) {
            Edge<T> t1Entry = t1Iter.next();
            if (t1Entry.destNode.equals(t2))
                adjList.get(t1).remove(t1Entry);
        }

        while (t2Iter.hasNext()) {
            Edge<T> t2Entry = t1Iter.next();
            if (t2Entry.destNode.equals(t2))
                adjList.get(t2).remove(t2Entry);
        }

        this.edges.removeIf(entry -> entry.srcNode == t1 && entry.destNode == t2);

        return true;
    }

    public ArrayList<Edge<T>> kruskal() {
        ArrayList<Edge<T>> result = new ArrayList<>();

        Map<T, T> parent = new HashMap<>();

        makeSet(parent);

        int index=0;

        Collections.sort(edges, Comparator.comparingInt(e -> e.weight));

        while (result.size() != nodeCount-1) {
            Edge<T> nextEdge = edges.get(index++);

            T x = find(parent, nextEdge.srcNode);
            T y = find(parent, nextEdge.destNode);

            if (!x.equals(y)) {
                result.add(nextEdge);
                union(parent, x, y);
            }
        }

        return result;
    }

    private void union(Map<T,T> parent, T x, T y) {
        T node = find(parent, x);
        T newParent = find(parent, y);

        parent.put(node, newParent);

    }

    private T find(Map<T, T> parent, T node) {
        if (parent.get(node).equals(node)) return node;

        return find(parent, parent.get(node));
    }

    private void makeSet(Map<T,T> parent) {
        for (T entry : adjList.keySet()) {
            parent.put(entry, entry);
        }
    }


    public static void main(String[] args) throws InvalidEdgeException, InvalidNodeException {
        AdjacencyListUndirectedWeightedGraph<Integer> graph = new AdjacencyListUndirectedWeightedGraph();


        graph.addNodeElement(0);
        graph.addNodeElement(1);
        graph.addNodeElement(2);
        graph.addNodeElement(3);
        graph.addNodeElement(4);
        graph.addNodeElement(5);
        graph.addNodeElement(6);


        graph.addEdge(0, 1, 7);
        graph.addEdge(1, 2, 8);
        graph.addEdge(0, 3, 5);
        graph.addEdge(1, 3, 9);
        graph.addEdge(1, 4, 7);
        graph.addEdge(2, 4, 5);
        graph.addEdge(3, 4, 15);
        graph.addEdge(3, 5, 6);
        graph.addEdge(4, 5, 8);
        graph.addEdge(4, 6, 9);
        graph.addEdge(5, 6, 11);

        System.out.println(graph.kruskal());

    }

}
