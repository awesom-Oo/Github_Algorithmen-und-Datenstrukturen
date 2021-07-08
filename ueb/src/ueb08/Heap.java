package ueb08;

import java.util.*;

/**
 * This class represents a binary heap
 *
 * @param <T> The heap's element type
 */

public class Heap<T> {
    public static final Comparator<Integer> COMPARATOR = Integer::compareTo;
    /**
     * Constant: The initial capacity, if not set
     */
    public static final int DEFAULT_CAPACITY = 16;

    /**
     * Holds the elements of this heap
     */
    private T[] elements;

    /**
     * The number of elements stored in this heap
     */
    private int number;

    /**
     * The element comparator
     */
    private final Comparator<T> comparator;


    /**
     * Creates and returns a new Heap, by using the Comparable interface for the Comparator
     */
    public  <E extends Comparable<? super E>> Heap<E> fromComparable() {
        return new Heap<>(Comparable::compareTo);
    }

    /**
     * @param comparator Comparator for elements
     */
    @SuppressWarnings("unchecked")
    public Heap(Comparator<T> comparator) {
        this.number = 0;
        this.elements = (T[]) new Object[DEFAULT_CAPACITY];
        this.comparator = comparator;
    }

    /**
     * Creates a heap from the given array. This method assumes, that the given
     * array is completely filled with elements
     *
     * @param fromArray  the values to fill this heap with
     * @param comparator Comparator for elements
     */
    @SuppressWarnings("unchecked")
    public Heap(T[] fromArray, Comparator<T> comparator) {
        this.number = fromArray.length;
        this.comparator = comparator;
        // Compute the array length as the next power of 2 greater than the number of
        // elements
        int internalSize = Integer.highestOneBit(fromArray.length) << 1;
        this.elements = (T[]) new Object[internalSize];
        System.arraycopy(fromArray, 0, this.elements, 1, fromArray.length);

        for (int start = number/2; start >= 0; start--) {
            downheap(start);
        }
    }

    /**
     * @return the number of elements in this heap
     */
    public int size() {
        return this.number;
    }

    /**
     * @return true, if this heap contains no elements, false otherwise
     */
    public boolean isEmpty() {
        return this.number == 0;
    }

    /**
     * Retrieves the top element of this heap, but does not remove it
     *
     * @return the top element of this heap
     */
    public T peek() {
        if (this.number == 0)
            throw new NoSuchElementException("Heap is empty");

        return this.elements[1];
    }

    /**
     * Adds the given elemet to this heap and restores the heap property
     *
     * @param element the element to add
     */
    public void add(T element) {
        if (++this.number >= this.elements.length)
            this.resize();

        this.elements[this.number] = element;
        this.upheap(this.number);
    }

    /**
     * Removes the top element of this heap (if any)
     */
    public void remove() {
        if (this.number <= 0)
            throw new NoSuchElementException("Heap is empty");

        this.elements[1] = this.elements[this.number--];
        this.downheap(1);
    }

    /**
     * Doubles the capacity of the storage array
     */
    @SuppressWarnings("unchecked")
    private void resize() {
        T[] freshElements = (T[]) new Object[this.elements.length << 1];
        System.arraycopy(this.elements, 0, freshElements, 0, this.elements.length);
        this.elements = freshElements;
    }

    /**
     * Bubbles the element at the given position up the heap into its correct position
     *
     * @param index the start position
     */
    private void upheap(int index) {
        if (index == 1)
            return;

        if (this.comparator.compare(this.elements[index], this.elements[index / 2]) < 0) {
            this.elements[0] = this.elements[index];
            this.elements[index] = this.elements[index / 2];
            this.elements[index / 2] = this.elements[0];
            this.upheap(index / 2);
        }
    }

    /**
     * Bubbles the element at the given position down the heap into its correct position
     *
     * @param index the start position
     */
    private void downheap(int index) {
        if (!((2 * index > this.number || this.comparator.compare(this.elements[index], this.elements[2 * index]) <= 0) &&
                (2 * index + 1 > this.number || this.comparator.compare(this.elements[index], this.elements[2 * index + 1]) <= 0)))
        {
            int i = 2 * index; // Try left child first
            if (i + 1 <= this.number && this.comparator.compare(this.elements[i], this.elements[i + 1]) > 0)
                i++; // Right child smaller than left

            // Swap and continue
            this.elements[0] = this.elements[index];
            this.elements[index] = this.elements[i];
            this.elements[i] = this.elements[0];
            this.downheap(i);
        }
    }

    /**
     * Finds the k-th smallest element using naive heap construction
     *
     * @param elements
     * @param k
     * @return k smallest element.
     */
    private static Integer findKSmallestNaive(Integer[] elements, int k) {
        if (k <= 0)
            throw new IllegalArgumentException("k must be >= 1");

        Heap<Integer> temp = new Heap<>(new Comparator<>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });

        Arrays.stream(elements).forEach(temp::add);

        while (--k > 0) {
            temp.remove();
        }

        return temp.peek();
    }
    /**
     * Finds the k-th smallest element using efficient heap construction
     *
     * @param elems
     * @param k
     * @return k smallest element.
     */
    private static Integer findKSmallestHeapify(Integer[] elems, int k) {
        if (k <= 0)
            throw new IllegalArgumentException("k must be >= 1");

        Heap<Integer> temp = new Heap<>(elems, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        });

        while (--k > 0) {
            temp.remove();
        }

        return temp.peek();
    }
    /**
     * Finds the k-th smalles element using a max heap
     *
     * @param elems
     * @param k
     * @return k smallest element.
     */
    private static Integer findKSmallestMaxHeap(Integer[] elems, int k) {
        if (k <= 0)
            throw new IllegalArgumentException("k must be >= 1");

        Heap<Integer> temp = new Heap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });

        Arrays.stream(elems, 0, k).forEach(temp::add);

        for (int i = k; i < elems.length; i++) {
            temp.add(elems[i]);
            temp.remove();
        }

        return temp.peek();
    }

    public static void main(String[] args) {

        Integer[] integers1 = {8, 7, 6, 5, 4, 3, 2, 1};
        Integer[] integers2 = {2, 6, 1, 4, 12, 9};

        Heap heap = new Heap(integers1, COMPARATOR);

        Integer s = findKSmallestNaive(integers2, 3);
        Integer t = findKSmallestHeapify(integers2, 3);
        Integer q = findKSmallestMaxHeap(integers2, 1);
        System.out.println(s);
        System.out.println(t);
        System.out.println(q);


        final int NUM_VALUES = 100_000_000;

        System.out.println("Building array.");
        Random rand = new Random(System.nanoTime());
        Integer[] values = new Integer[NUM_VALUES];

        for (int i = 0; i < NUM_VALUES; i++)
            values[i] = rand.nextInt(NUM_VALUES * 20);

        for (int k = 1; k < 10; k++) {
            System.out.println("k = " + k + ":");
            // Naive
            {
                long time = -System.currentTimeMillis();
                Integer v = findKSmallestNaive(values, k);
                time += System.currentTimeMillis();
                System.out.println("Naive (" + v + "): " + time);
            }

            // Heapify
            {
                long time = -System.currentTimeMillis();
                Integer v = findKSmallestHeapify(values, k);
                time += System.currentTimeMillis();
                System.out.println("Heapify (" + v + "): " + time);
            }

            // max heap
            {
                long time = -System.currentTimeMillis();
                Integer v = findKSmallestMaxHeap(values, k);
                time += System.currentTimeMillis();
                System.out.println("MaxHeap (" + v + "): " + time);
            }
        }
    }
}
