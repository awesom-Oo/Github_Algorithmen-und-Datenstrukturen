package teachings;

import ueb10.Graph;

import java.util.ArrayList;
import java.util.HashMap;

class Edge2<T> {

    Node2<T> src, dest; int weight;

    public Edge2(Node2<T> src, Node2<T> dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }
}

class Node2<T> {
    T val;
    public Node2(T val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return val.toString();
    }
}

class Graph2<T> {

    private ArrayList<ArrayList<Boolean>> adjMatrix = new ArrayList<>();
    private HashMap<T, Integer> node = new HashMap<>();
    private int index=0;
    private int numNodes;

    public Graph2(int numNodes) {
        for (int i = 0; i < numNodes; i++) {
            adjMatrix.add(new ArrayList<>());
            for (int j = 0; j < numNodes; j++)
                adjMatrix.get(i).add(j, false);
        }
    }

    public void addNode(T node) {
        if (this.node.containsKey(node)) return;

        this.node.put(node, index);
        index ++;
    }

    public void addEdge(T src, T dest) throws Graph.InvalidEdgeException {
        if (src == dest) throw new Graph.InvalidEdgeException();

        int srcI = node.get(src);
        int destI = node.get(dest);

        adjMatrix.get(srcI).set(destI, true);
    }

    public void removeNode(T element) {

        for (ArrayList<Boolean> entry : adjMatrix) {
            entry.remove(node.get(element));
        }

        adjMatrix.remove(node.get(element));
        node.remove(element);
        index --;
    }

    public void removeEdge(T element) {

    }

    public static void main(String[] args) throws Graph.InvalidEdgeException {
        Graph2<Integer> graph2 = new Graph2<>(4);

        graph2.addNode(0);
        graph2.addNode(1);
        graph2.addNode(2);
        graph2.addNode(3);

        graph2.addEdge(0, 1);
        graph2.addEdge(1, 2);


        System.out.println("99");

    }
}
