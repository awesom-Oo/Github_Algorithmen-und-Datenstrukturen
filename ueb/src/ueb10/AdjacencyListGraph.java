package ueb10;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class AdjacencyListGraph<T> implements Graph<T> {

    private Map<T, AdjacencyListNode<T>> nodes = new HashMap<>();

    @Override
    public boolean addNodeElement(T element) {
        AdjacencyListNode<T> node = new AdjacencyListNode<>(element);
        AdjacencyListNode<T> oldNode = nodes.putIfAbsent(node.getElement(), node);
        return oldNode == null;
    }

    @Override
    public boolean removeNodeElement(T element) {
        AdjacencyListNode<T> node = nodes.get(element);
        if(node == null){
            return false;
        }
        nodes.remove(element);
        for (AdjacencyListNode<T> anyNode : nodes.values()) {
            Iterator<AdjacencyListNode<T>> adjacentIterator = anyNode.getAdjacencyList().iterator();
            while(adjacentIterator.hasNext()){
                AdjacencyListNode<T> next = adjacentIterator.next();
                if(next.equals(node)){
                    adjacentIterator.remove();
                }
            }
        }
        return true;
    }

    @Override
    public void addEdge(T from, T to) throws InvalidEdgeException {
        AdjacencyListNode<T> source = nodes.get(from);
        AdjacencyListNode<T> destination = nodes.get(to);

        if (source == null || destination == null) {
            throw new InvalidEdgeException("At least one node does not exist.");
        }
        for (AdjacencyListNode<T> sourceAdjacentNode : source.getAdjacencyList()) {
            if (sourceAdjacentNode.equals(destination)) {
                return;
            }
        }

        source.getAdjacencyList().add(destination);
    }

    @Override
    public boolean removeEdge(T from, T to) throws InvalidNodeException {
        AdjacencyListNode<T> source = nodes.get(from);
        AdjacencyListNode<T> destination = nodes.get(to);

        if (source == null || destination == null) {
            throw new InvalidNodeException("At least one node does not exist.");
        }

        Iterator<AdjacencyListNode<T>> iterator = source.getAdjacencyList().iterator();

        while (iterator.hasNext()) {
            AdjacencyListNode<T> next = iterator.next();
            if (next.equals(destination)) {
                iterator.remove();
                //Note: Assuming no duplicate edges.
                return true;
            }
        }

        return false;
    }

}