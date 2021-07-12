package teachings;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

class KthSmallestElement
{
    // Function to find the k'th smallest element in an
    // array using max-heap
    public static int findKthSmallest(List<Integer> A, int k)
    {
        // create a max-heap using the `PriorityQueue` class and
        // insert the first `k` array elements into the heap
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        pq.addAll(A.subList(0, k));

        // do for remaining array elements
        for (int i = k; i < A.size(); i++)
        {
            // if the current element is less than the root of the heap
            if (A.get(i) < pq.peek())
            {
                // replace root with the current element
                pq.poll();
                pq.add(A.get(i));
            }
        }

        // return the root of max-heap
        return pq.peek();
    }

    public static void main(String[] args)
    {
        List<Integer> A = Arrays.asList(7, 4, 6, 3, 9, 1);
        int k = 3;

        System.out.println("k'th smallest array element is " + findKthSmallest(A, k));
    }
}
