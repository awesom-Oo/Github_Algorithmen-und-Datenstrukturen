package teachings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

// A class for implementing the priority queue
public class PriorityQueue
{
    // vector to store heap elements
    private ArrayList<Integer> A;

    // constructor: use the default initial capacity of a vector
    public PriorityQueue() {
        A = new ArrayList<>();
    }

    // constructor: set a custom initial capacity for vector
    public PriorityQueue(int capacity) {
        A = new ArrayList<>(capacity);
    }

    // return parent of `A[i]`
    private int parent(int i)
    {
        // if `i` is already a root node
        if (i == 0) {
            return 0;
        }

        return (i - 1) / 2;
    }

    // return left child of `A[i]`
    private int LEFT(int i) {
        return (2*i + 1);
    }

    // return right child of `A[i]`
    private int RIGHT(int i) {
        return (2*i + 2);
    }

    // swap values at two indexes
    void swap(int x, int y)
    {
        // swap with a child having greater value
        Integer tempX = A.get(x);
        Integer tempY = A.get(y);

        A.set(y, tempX);
        A.set(x, tempY);


    }

    // Recursive heapify-down procedure. Here, the node at index `i`
    // and its two direct children violate the heap property
    private void heapify_down(int i)
    {
        // get left and right child of node at index `i`
        int left = LEFT(i);
        int right = RIGHT(i);

        int largest = i;

        // compare `A[i]` with its left and right child
        // and find the largest value
        if (left < size() && A.get(left) > A.get(i)) {
            largest = left;
        }

        if (right < size() && A.get(right) > A.get(largest)) {
            largest = right;
        }

        if (largest != i)
        {
            // swap with a child having greater value
            swap(i, largest);

            // call heapify-down on the child
            heapify_down(largest);
        }
    }

    // Recursive heapify-up procedure
    private void heapify_up(int i)
    {
        // check if the node at index `i` and its parent violates
        // the heap property
        if (i > 0 && A.get(parent(i)) < A.get(i))
        {
            // swap the two if heap property is violated
            swap(i, parent(i));

            Integer parent = parent(i);

            // call heapify-up on the parent
            heapify_up(parent(i));
        }
    }

    // return size of the heap
    public int size() {
        return A.size();
    }

    // check if the heap is empty or not
    public Boolean isEmpty() {
        return A.isEmpty();
    }

    // insert a specified key into the heap
    public void add(Integer key)
    {
        // insert a new element at the end of the vector
        A.add(key);

        // get element index and call heapify-up procedure
        int index = size() - 1;
        heapify_up(index);
    }

    // Function to remove and return an element with the highest priority
    // (present at the root). It returns null if the queue is empty
    public Integer poll()
    {
        try {
            // if the heap is empty, throw an exception
            if (size() == 0) {
                throw new Exception("Index is out of range (Heap underflow)");
            }

            // element with the highest priority
            int root = A.get(0);	// or A.get(0);

            // replace the root of the heap with the last element of the vector
            int last = A.get(size()-1);

            A.set(0, last);
            A.remove(size() - 1);

            // call heapify-down on the root node
            heapify_down(0);

            // return root element
            return root;
        }
        // catch and print the exception
        catch (Exception ex)
        {
            System.out.println(ex);
            return null;
        }
    }

    // Function to return an element with the highest priority
    // (present at the root). It returns null if the queue is empty
    public Integer peek()
    {
        try {
            // if the heap has no elements, throw an exception
            if (size() == 0) {
                throw new Exception("Index out of range (Heap underflow)");
            }

            // otherwise, return the top (first) element
            return A.get(0);	// or A.get(0);
        }
        // catch the exception and print it, and return null
        catch (Exception ex)
        {
            System.out.println(ex);
            return null;
        }
    }

    // Function to remove all elements from the priority queue
    public void clear()
    {
        A.clear();


    }

    // Returns true if the queue contains the specified element
    public Boolean contains(Integer i) {
        return A.contains(i);
    }

    // Returns an array containing all elements in the queue
    public Integer[] toArray() {
        return A.toArray(new Integer[size()]);
    }

    public int findKthSmallestMin(int k) {

        while (k-- > 0) {
            this.poll();
        }

        return this.peek();
    }

    public int findKthSmallestMax(int k) {

        PriorityQueue temp = new PriorityQueue();

        for (int i = 0; i < k; i++) {
            temp.add(A.get(i));
        }

        for (int j = k; j < A.size(); j++) {

            if (temp.peek() > A.get(j)) {
                temp.poll();
                temp.add(A.get(j));
            }
        }
        return temp.peek();
    }
}

class Main
{
    public static void main (String[] args)
    {
        // create a priority queue with an initial capacity of 10.
        // The value of an element decides the priority of it.
        PriorityQueue pq = new PriorityQueue(10);

//        System.out.println("Priority queue size is " + pq.size());
//        // insert three integers
//        pq.add(3);
//        pq.add(2);
//        pq.add(15);
//
//        // print priority queue size
//        System.out.println("Priority queue size is " + pq.size());
//
//        // search 2 in priority queue
//        Integer searchKey = 2;
//
//        if (pq.contains(searchKey)) {
//            System.out.println("Priority queue contains " + searchKey + "\n");
//        }
//
//        // empty queue
//        pq.clear();
//
//        if (pq.isEmpty()) {
//            System.out.println("The queue is empty");
//        }
//
//        System.out.println("\nCalling remove operation on an empty heap");
//        System.out.println("The element with the highest priority is " + pq.poll());
//
//        System.out.println("\nCalling peek operation on an empty heap");
//        System.out.println("The element with the highest priority is " + pq.peek() +
//                System.lineSeparator());

        // again insert three integers
        pq.add(3);
        pq.add(2);
        pq.add(15);
        pq.add(5);
        pq.add(4);
        pq.add(45);

        System.out.println("69");

        int kthSmallestMax = pq.findKthSmallestMax(3);
        int kthSmallestMin = pq.findKthSmallestMin(3);

        System.out.println(kthSmallestMax);
        System.out.println(kthSmallestMin);

        // construct an array containing all elements present in the queue
//        Integer[] I = pq.toArray();
//        System.out.println("Printing array: " + Arrays.toString(I));
//
//        System.out.println("\nThe element with the highest priority is " + pq.poll());
//        System.out.println("The element with the highest priority is " + pq.peek());
    }
}