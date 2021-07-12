package ueb10;

/**
 * Interface of a Graph
 *
 * @param <T> the type to store in the nodes of the graph
 */
public interface Graph<T> {

    /**
     * add a node to the graph with the given element
     *
     * @param element the element which should be represented in the graph as a node
     * @return true, if the element was added; false, if the element already existed.
     */
    boolean addNodeElement(T element);

    /**
     * remove the node with the given element from the graph
     *
     * @param element the element to remove
     * @return true, if the element was removed; false, if the element did not exist.
     */
    boolean removeNodeElement(T element);

    /**
     * add a new edge to the graph
     *
     * @param from the source element of the edge
     * @param to the destination element of the edge
     * @throws InvalidEdgeException if one of the two elements is not part of the graph
     */
    void addEdge(T from, T to) throws InvalidEdgeException;

    /**
     * removes edge with given source and destination from graph
     *
     * @param from the source element of the edge
     * @param to the destination element of the edge
     * @throws InvalidNodeException if one of the two elements is not part of the graph
     * @return true, if the edge was removed; false, if the edge did not exist.
     */
    boolean removeEdge(T from, T to) throws InvalidNodeException;

    final class InvalidEdgeException extends Exception {

        public InvalidEdgeException() {
        }

        public InvalidEdgeException(String message) {
            super(message);
        }
    }

    final class InvalidNodeException extends Exception {

        public InvalidNodeException() {
        }

        public InvalidNodeException(String message) {
            super(message);
        }
    }

}
