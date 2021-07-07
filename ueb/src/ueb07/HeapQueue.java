/*
package ueb07;


import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;

public class HeapQueue<E> implements SimpleFIFOQueue<E>{


    private PriorityQueue<Node<E>> queue;
    private final int maxSize;
    private int currentSize;
    private int cPrio;

    private class Node<E> {

        private E data;
        private int prio;

        public Node(E data, int prio) {
            this.data = data;
            this.prio = prio;
        }

        public Integer getPrio() {
            return prio;
        }
    }

    public HeapQueue(int maxSize) {
        queue = new PriorityQueue<Node<E>>(new Comparator<Node<E>>() {
            @Override
            public int compare(Node<E> o1, Node<E> o2) {
                return o1.getPrio().compareTo(o2.getPrio());
            }
        });
        this.maxSize = maxSize;
        currentSize = 0;
        cPrio = Integer.MIN_VALUE;
    }


    */
/**
     * Removes the first element in the queue and returns it.
     *
     * @return The element which, among all elements currently in this queue, was added first.
     *//*

    @Override
    public E poll() throws NoSuchElementException {
        if (currentSize == 0)
            throw new NoSuchElementException();
        currentSize--;
        return queue.remove().data;
    }

    */
/**
     * Adds the element to this queue.
     *
     * @param element The element to be added to this queue.
     *//*

    @Override
    public void add(E element) throws IllegalStateException {
        if (currentSize >= maxSize)
            throw new IllegalStateException();
        if (cPrio == Integer.MAX_VALUE) {
            PriorityQueue<Node<E>> tmp = new PriorityQueue();
            int i = 0;
            for (; i < currentSize; i++) {
                tmp.add(new Node<>(queue.remove().data , Integer.MIN_VALUE + i));
            }
            cPrio = Integer.MIN_VALUE + i + 1;
        }
        queue.add(new Node<>(element, cPrio));
        cPrio++;
        currentSize++;
    }

    */
/**
     * Returns the top element of this stack without removing it.
     *
     * @return The element on top of this stack.
     *//*

    @Override
    public E peek() throws NoSuchElementException {
        if (currentSize == 0)
            throw new NoSuchElementException();
        return queue.peek().data;
    }

    */
/**
     * Removes all elements from this stack.
     *//*

    @Override
    public void clear() {
        currentSize = 0;
        cPrio = Integer.MIN_VALUE;
        queue = new PriorityQueue<>();
    }

    */
/**
     * Tests whether this stack is empty.
     *
     * @return true, if this stack does not contain any elements; false otherwise.
     *//*

    @Override
    public boolean isEmpty() {
        return currentSize == 0;
    }

    */
/**
     * Returns the maximum capacity of this stack.
     *
     * @return The maximum capacity of this stack.
     *//*

    @Override
    public int capacity() {
        return maxSize;
    }
}
*/
