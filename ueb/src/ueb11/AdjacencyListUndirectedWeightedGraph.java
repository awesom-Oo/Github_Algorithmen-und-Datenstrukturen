package ueb11;

import java.util.*;


public class AdjacencyListUndirectedWeightedGraph<T> implements UndirectedWeightedGraph<T> {


     class Edge<T>  {
        private int weight;
        private T destNode;

        public Edge(T destNode, int weight) {
            this.destNode = destNode;
            this.weight = weight;
        }

/*        public Edge(T srcNode, T destNode, int weight) {
            this.srcNode = srcNode;
            this.destNode = destNode;
            this.weight = weight;
        }*/

        public int getWeight() { return weight; }

        public T getDestNode() { return destNode; }

/*        @Override
        public String toString() {
            return "{" + srcNode + ", " + destNode + ", " + weight + "} ";
        }*/

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

        //edges.add(new Edge<T>(t1, t2, weight));

    }

    @Override
    public boolean removeEdge(T t1, T t2) throws InvalidNodeException, InvalidEdgeException {
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

/*    public void kruskal() {
        PriorityQueue<Edge<T>> result = new PriorityQueue<>();

        // add all edges to list, sort the edges on weight
        for (int i = 0; i < edges.size(); i++) {
            result.add(edges.get(i));
        }

        int[] parent = new int[nodeCount];

        makeSet(parent);

        ArrayList<Edge<T>> minspantree= new ArrayList<>();

        int index = 0;

        while (index < nodeCount-1) {
            Edge<T> edge = result.remove();

            int xSet = find(parent, edge.srcNode);
            int ySet = find(parent, edge.destNode);
            
            if (xSet != ySet) {
                minspantree.add(edge);
                index += 1;
                union(parent, xSet, ySet);
            }

        }

        System.out.println("min");
        printGraph(minspantree); 
        
    }*/

/*    private void printGraph(ArrayList<Edge<T>> minspantree) {
        for (int i = 0; i < edges.size(); i++) {
            Edge<T> edge = edges.get(i);
            System.out.println("Edge " + i + "source: " + edge.srcNode +
                    "destination " + edge.destNode +
                    "weight: " + edge.weight);
        }
    }*/

/*    private void union(int[] parent, int xSet, int ySet) {

        int xSetParent = find(parent, xSet);
        int ySetParent = find(parent, ySet);

        parent[ySetParent] = xSetParent;
    }

    private int find(int[] parent, T srcNode) {
        if (parent[srcNode] != srcNode) {
            return find(parent, parent[srcNode]);
        }

        return srcNode;
    }*/

/*    private void makeSet(int[] parent) {
        for (int i = 0; i < nodeCount; i++) {
            parent[i] = i;
        }
    }*/

    public static void main(String[] args) throws InvalidEdgeException, InvalidNodeException {
        AdjacencyListUndirectedWeightedGraph<Integer> graph = new AdjacencyListUndirectedWeightedGraph();


        graph.addNodeElement(1);
        graph.addNodeElement(2);
        graph.addNodeElement(3);
        graph.addNodeElement(4);


        graph.addEdge(1, 2, 7);
        graph.addEdge(3, 4, 4);

        graph.removeEdge(1, 2);
        System.out.println("Hello");
    }

}
