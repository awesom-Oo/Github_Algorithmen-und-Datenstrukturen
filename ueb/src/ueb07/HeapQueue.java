package ueb07;


import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;

public class HeapQueue<E> implements SimpleFIFOQueue<E> {

    private static class Node<E> {
        private E element ;
        private Integer insertion;

        public Node(E data, Integer prio) {
            this.element = data;
            this.insertion = prio;
        }

    }


    private PriorityQueue<Node<E>> priorityQueue = new PriorityQueue<>(new Comparator<Node<E>>() {
        @Override
        public int compare(Node<E> o1, Node<E> o2) {
            return o1.insertion.compareTo(o2.insertion);
        }
    });

    private final int maximumCapacity;
    private boolean overFLowQueueActive=false;
    private PriorityQueue<Node<E>> overFlowQueue;

    private int capacity=0;
    private int allInserts=0;

    public HeapQueue(int maximumCapacity) {
        this.maximumCapacity = maximumCapacity;
    }

    @Override
    public E poll() throws NoSuchElementException {
        switchQueues();

        if (priorityQueue.isEmpty())
            throw new NoSuchElementException();

        capacity--;
        return priorityQueue.poll().element;
    }

    private void switchQueues() {
        if (priorityQueue.isEmpty() && overFLowQueueActive) {
            priorityQueue = overFlowQueue;
            overFlowQueue = null;
            overFLowQueueActive = false;
        }
    }

    @Override
    public void add(E element) throws IllegalStateException {
        if (capacity > maximumCapacity)
            throw new IllegalStateException();

        capacity++;
        if (overFLowQueueActive)
            overFlowQueue.add(new Node<>(element, allInserts--));
        else
            priorityQueue.add(new Node<>(element, allInserts++));

        if (!overFLowQueueActive && allInserts == Integer.MIN_VALUE) {
            overFLowQueueActive = true;
            allInserts = 0;
            overFlowQueue = new PriorityQueue<Node<E>>((Comparator<Node<E>>)
                    (o1, o2) -> Integer.compare(o2.insertion, o1.insertion));
        }
    }

    @Override
    public E peek() throws NoSuchElementException {
        switchQueues();

        if (priorityQueue.isEmpty())
            throw new NoSuchElementException();

        return priorityQueue.peek().element;
    }

    @Override
    public void clear() {
        priorityQueue.clear();
    }

    @Override
    public boolean isEmpty() {
        switchQueues();
        return priorityQueue.isEmpty();
    }

    @Override
    public int capacity() {
        return maximumCapacity;
    }




    public static void main(String[] args) {
        HeapQueue<Integer> integerHeapQueue = new HeapQueue<>(10);

        integerHeapQueue.add(5);
        integerHeapQueue.add(10);
        integerHeapQueue.add(1);
        integerHeapQueue.add(3);
        integerHeapQueue.add(50);
        integerHeapQueue.add(500);
        integerHeapQueue.add(60);
        integerHeapQueue.add(30);
        integerHeapQueue.add(40);




        System.out.println("Test");

        System.out.println(integerHeapQueue.peek());
        System.out.println(integerHeapQueue.poll());
        System.out.println(integerHeapQueue.peek());
        System.out.println(integerHeapQueue.poll());
        System.out.println(integerHeapQueue.peek());
        System.out.println(integerHeapQueue.poll());
        System.out.println(integerHeapQueue.peek());
        System.out.println(integerHeapQueue.poll());
    }
}