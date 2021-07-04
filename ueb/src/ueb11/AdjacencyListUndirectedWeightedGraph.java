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
        nodeCount += 1;
        return true;
    }

    @Override
    public boolean removeNodeElement(T element) {
        if (!this.adjList.containsKey(element)) {
            return false;
        }

        this.adjList.remove(element);
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

        adjList.get(t1).add(new Edge<T>(t2, weight));
        adjList.get(t2).add(new Edge<T>(t1, weight));

        edges.add(new Edge<T>(t1, t2, weight));

    }

    @Override
    public boolean removeEdge(T t1, T t2) throws InvalidNodeException {
        if (!this.adjList.containsKey(t1) || !this.adjList.containsKey(t2)) {
            throw new InvalidNodeException("Edge does not exist");
        }

        for (Map.Entry<T, ArrayList<Edge<T>>> entry : adjList.entrySet()) {
            Iterator<Edge<T>> arrayListIterator = entry.getValue().iterator();

            while (arrayListIterator.hasNext()) {
                Edge<T> arrayListEntry = arrayListIterator.next();
                if (arrayListEntry.destNode.equals(t2) || arrayListEntry.destNode.equals(t1)) {
                    arrayListIterator.remove();
                }

            }
        }
        return true;
    }

    public void kruskal() {
        PriorityQueue<Edge<T>> result = new PriorityQueue<>(edges.size(), Comparator.comparingInt(o -> o.weight));

        // add all edges to list, sort the edges on weight
        result.addAll(edges);

        HashMap<T, ArrayList<T>> parentnode = new HashMap<>();
        makeSet(parentnode);
        ArrayList<Edge<T>> minSpanTree= new ArrayList<>();

        int index = 0;

        while (index < nodeCount-1) {
            Edge<T> edge = result.remove();

            T xSet = find(parentnode, edge.srcNode);
            T ySet = find(parentnode, edge.destNode);

            if (xSet != ySet) {
                minSpanTree.add(edge);
                index += 1;
                union(parentnode, xSet, ySet);
            }

        }

        printGraph(minSpanTree);
    }


    private void union(HashMap<T, ArrayList<T>> parentnode, T xSet, T ySet) {

        for (Map.Entry<T, ArrayList<T>> entry : parentnode.entrySet()) {
            if (entry.getKey().equals(xSet) || entry.getKey().equals(ySet)) {
                ArrayList<T> value = entry.getValue();

                if (parentnode.get(xSet).size() >= parentnode.get(ySet).size()) {
                    value.add(ySet);
                    parentnode.remove(ySet);
                    break;
                }

                if (entry.getKey().equals(ySet)) {
                    value.add(xSet);
                    parentnode.remove(xSet);
                    break;
                }
            }
        }
    }


    private T find(HashMap<T, ArrayList<T>> parentnodes, T srcNode) {

        for (Map.Entry<T, ArrayList<T>> entry : parentnodes.entrySet()) {
            T key = entry.getKey();
            if (key.equals(srcNode)) {
                return key;

            } else {
                for (T value : entry.getValue()) {
                    if (value.equals(srcNode)) {
                        return key;
                    }
                }
            }
        }
        return null;
    }

    private HashMap<T, ArrayList<T>> makeSet(HashMap<T, ArrayList<T>> parentnodes) {

        for (Map.Entry<T, ArrayList<Edge<T>>> entry: adjList.entrySet()) {
            T key = entry.getKey();
            parentnodes.put(key, new ArrayList<>());
        }

        return parentnodes;
    }

    private void printGraph(ArrayList<Edge<T>> minspantree) {
        for (int i = 0; i < minspantree.size(); i++) {
            Edge<T> edge = minspantree.get(i);
            System.out.println("Edge " + i + " source: " + edge.srcNode +
                    " destination " + edge.destNode +
                    " weight: " + edge.weight);
        }
    }

    public static void main(String[] args) throws InvalidEdgeException {
        AdjacencyListUndirectedWeightedGraph<Integer> graph = new AdjacencyListUndirectedWeightedGraph();


        graph.addNodeElement(0);
        graph.addNodeElement(1);
        graph.addNodeElement(2);
        graph.addNodeElement(3);
        graph.addNodeElement(4);
        graph.addNodeElement(5);
        //graph.addNodeElement(9);


        graph.addEdge(0, 1, 4);
        graph.addEdge(0, 2, 3);
        graph.addEdge(1, 2, 1);
        graph.addEdge(1, 3, 2);
        graph.addEdge(2, 3, 4);
        graph.addEdge(3, 4, 2);
        graph.addEdge(4, 5, 6);
        //graph.addEdge(5, 9, 12);


        graph.kruskal();

    }

}
