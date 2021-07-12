package teachings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

class Edge1<T> {
    Node1<T> src, dest; int weight;

    public Edge1(Node1<T> src, Node1<T> dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }
}

class Node1<T> {
    T val;
    public Node1(T val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return val.toString();
    }
}

class Graph1<T> {

    private Map<Node1<T>, LinkedList<Node1<T>>> adjList;

    public Graph1() {

        adjList = new HashMap<>();
    }

    public void addNode(Node1<T> val) {
        adjList.put(val, new LinkedList<>());
    }

    public void addEdge(Edge1<T> edge) {
        if (!adjList.containsKey(edge.src)) addNode(edge.src);

        if (!adjList.containsKey(edge.dest)) addNode(edge.dest);

        adjList.get(edge.src).add(edge.dest);
        adjList.get(edge.dest).add(edge.src);
    }
    
    public int getNodeCount() {
        return adjList.keySet().size();
    }
    
    public int getEdgeCount() {
        int count=0;

        for (Node1<T> node: adjList.keySet()) {
            count += adjList.get(node).size();
        }

        return count /= 2;
    }

    public boolean containsNode(Node1<T> node) {
        return adjList.containsKey(node);
    }

    public boolean containsEdge(Edge2<T> edge) {
        return adjList.get(edge.src).contains(edge.dest);
    }

    public void removeNode(Node1<T> node) {
        adjList.remove(node);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (Node1<T> node : adjList.keySet()) {
            stringBuilder.append(node.val.toString() + " <-> ");
            for (Node1<T> node1 : adjList.get(node)) {
                stringBuilder.append(node1.toString() + " ");
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        Graph1<Integer> graph = new Graph1<>();

        graph.addEdge(new Edge1<>(new Node1<Integer>(0), new Node1<>(1), 9));
        graph.addEdge(new Edge1<>(new Node1<Integer>(1), new Node1<>(2), 7));
        graph.addEdge(new Edge1<>(new Node1<Integer>(1), new Node1<>(3), 5));
        graph.addEdge(new Edge1<>(new Node1<Integer>(2), new Node1<>(3), 3));



        System.out.println(graph.toString());

    }

}
