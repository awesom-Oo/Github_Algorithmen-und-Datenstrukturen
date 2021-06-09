package ueb07;

import java.util.NoSuchElementException;
import java.util.PriorityQueue;

public class HeapStack<E> implements SimpleStack<E> {

    private PriorityQueue<Node<E>> priorityQueue;
    private int capacity;
    private int count=-1;

    static class Node <E> implements Comparable<Node>{
        int key; E value;

        Node (int key, E value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public int compareTo(Node o) {
            return o.key - key;
        }
    }

    public HeapStack (int capacity) {
        this.capacity = capacity;
        priorityQueue = new PriorityQueue<>(capacity);
    }

    @Override
    public E pop() throws NoSuchElementException {
        if (!priorityQueue.isEmpty()) {
            count -= 1;
            return priorityQueue.poll().value;
        }
        else throw new NoSuchElementException("Queue is empty");
    }



    @Override
    public void push(E element) throws IllegalStateException {
        if (count != capacity) {
            count++;
            priorityQueue.add(new Node<>(count, element));
        }
        else throw new IllegalStateException("Stack full");
    }

    @Override
    public E peek() throws NoSuchElementException {
        if (priorityQueue.isEmpty())
            throw new NoSuchElementException("Queue is empty");

        return priorityQueue.peek().value;
    }

    @Override
    public void clear() {
        priorityQueue.clear();
    }

    @Override
    public boolean isEmpty() {
        return priorityQueue.isEmpty();
    }

    @Override
    public int capacity() {
        return capacity;
    }

    public static void main(String[] args) {
        HeapStack<Integer> integerHeapStack = new HeapStack<Integer >(6);

        integerHeapStack.push(2);
        integerHeapStack.push(16);
        integerHeapStack.push(9);
        integerHeapStack.push(12);

        System.out.println(integerHeapStack.peek());
        System.out.println(integerHeapStack.pop());
        System.out.println(integerHeapStack.peek());
        System.out.println(integerHeapStack.pop());
        System.out.println(integerHeapStack.peek());

    }

}
