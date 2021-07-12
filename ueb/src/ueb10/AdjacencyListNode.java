package ueb10;

import java.util.LinkedList;
import java.util.List;

/**
 * This class represents a node
 *
 * @param <T>
 */
public class AdjacencyListNode<T> {

    private T element;

    private List<AdjacencyListNode<T>> adjacencyList = new LinkedList<AdjacencyListNode<T>>();

    public AdjacencyListNode(T element) {
        super();
        this.element = element;
    }

    public List<AdjacencyListNode<T>> getAdjacencyList() {
        return adjacencyList;
    }

    public T getElement() {
        return element;
    }

    public void setElement(T element) {
        this.element = element;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AdjacencyListNode<?> that = (AdjacencyListNode<?>) o;

        return element.equals(that.element);
    }

    @Override
    public int hashCode() {
        return element.hashCode();
    }
}
