package teachings;

import java.util.*;
import java.util.stream.IntStream;

class Edge {
    int src, dest, weight;

    public Edge(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }
}

class Kruskal {

    private Map<Integer, Integer> parent = new HashMap<>();

    public List<Edge> kruskal(List<Edge> edges, int n) {
        
        Collections.sort(edges, Comparator.comparingInt(e -> e.weight));

        List<Edge> tree = new ArrayList<>();

        makeSet(n);

        int index=0;

        while (tree.size() != n-1) {

            Edge nextEdge = edges.get(index++);

            int x = find(nextEdge.src);
            int y = find(nextEdge.dest);

            if (x != y) {
                tree.add(nextEdge);
                union(x, y);
            }
        }

        return tree;
    }

    private void union(int x, int y) {

        int a = find(x);
        int b = find(y);

        parent.put(x, y);
    }

    private int find(int element) {

        if (parent.get(element) == element) return element;

        return find(parent.get(element));
    }

    private void makeSet(int n) {
        for (int i = 0; i < n; i++) parent.put(i, i);
    }

    public static void main(String[] args) {
        List<Edge> edges = Arrays.asList(
                new Edge(0, 1, 7), new Edge(1, 2, 8),
                new Edge(0, 3, 5), new Edge(1, 3, 9),
                new Edge(1, 4, 7), new Edge(2, 4, 5),
                new Edge(3, 4, 15), new Edge(3, 5, 6),
                new Edge(4, 5, 8), new Edge(4, 6, 9),
                new Edge(5, 6, 11)
        );

        int n = 7;

        Kruskal kruskal = new Kruskal();
        List<Edge> tree = kruskal.kruskal(edges, n);
    }
}
