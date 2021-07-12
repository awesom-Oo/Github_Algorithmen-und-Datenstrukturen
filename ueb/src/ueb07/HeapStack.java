package ueb07;

import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;

public class HeapStack<E> implements SimpleStack<E>{

    static class Node<E> {

        private E element ;
        private Integer insertion;

        public Node(E element, Integer insertion) {
            this.element = element;
            this.insertion = insertion;
        }
    }


    private PriorityQueue<Node<E>> priorityQueue = new PriorityQueue<>(new Comparator<Node<E>>() {
        @Override
        public int compare(Node<E> o1, Node<E> o2) {
            return o1.insertion.compareTo(o2.insertion);
        }
    });

    private final int maximumCapacity;
    private int capacity;

    public HeapStack(int maxCap) {
        this.maximumCapacity = maxCap;
    }

    /**
     * Removes the top element of this stack and returns it.
     *
     * @return The element on top of this stack.
     */
    @Override
    public E pop() throws NoSuchElementException {
        if (priorityQueue.isEmpty())
            throw new NoSuchElementException();
        capacity--;
        return priorityQueue.poll().element;
    }

    /**
     * Pushes the element on top of this stack.
     *
     * @param element The element to be pushed on top of this stack.
     */
    @Override
    public void push(E element) throws IllegalStateException {
        if (capacity > maximumCapacity)
            throw new IllegalStateException();
        priorityQueue.add(new Node<>(element, capacity++));
    }

    /**
     * Returns the top element of this stack without removing it.
     *
     * @return The element on top of this stack.
     */
    @Override
    public E peek() throws NoSuchElementException {
        if (priorityQueue.isEmpty())
            throw new NoSuchElementException();
        return priorityQueue.peek().element;
    }

    /**
     * Removes all elements from this stack.
     */
    @Override
    public void clear() {
        priorityQueue.clear();
    }

    /**
     * Tests whether this stack is empty.
     *
     * @return true, if this stack does not contain any elements; false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return priorityQueue.isEmpty();
    }

    /**
     * Returns the maximum capacity of this stack.
     *
     * @return The maximum capacity of this stack.
     */
    @Override
    public int capacity() {
        return maximumCapacity;
    }

        public static void main(String[] args) {
        HeapStack<Integer> integerHeapStack = new HeapStack<Integer >(10);

        integerHeapStack.push(5);
        integerHeapStack.push(10);
        integerHeapStack.push(1);
        integerHeapStack.push(3);
        integerHeapStack.push(50);
        integerHeapStack.push(500);
        integerHeapStack.push(60);
        integerHeapStack.push(30);
        integerHeapStack.push(40);




        System.out.println("Test");

        System.out.println(integerHeapStack.peek());
        System.out.println(integerHeapStack.pop());
        System.out.println(integerHeapStack.peek());
        System.out.println(integerHeapStack.pop());
        System.out.println(integerHeapStack.peek());
        System.out.println(integerHeapStack.pop());
        System.out.println(integerHeapStack.peek());
        System.out.println(integerHeapStack.pop());
    }


}

