package ueb01;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
/**
 * Collection of binary search methods for duplicate handling in arrays.
 */
public class BinSearch {
    /**
     * Examines whether input is sorted in ascending order according to the compareTo method.
     *
     * @param input The array to determine an ascending order on.
     * @param <T>   The comparable type.
     * @return true, if input sorted in ascending order; false, otherwise.
     */
    private static <T extends Comparable<T>> boolean isSorted(T[] input) {
        for (int i = 0; i < input.length - 1; i++) {
            if (input[i].compareTo(input[i + 1]) > 0) return false;
        }
        return true;
    }

    /**
     * Determines the first occurrence of an element equals to searchElement
     * in an input array sorted in ascending order (potentially featuring duplicates).
     *
     * @param input         The input array sorted in ascending order.
     * @param searchElement The element to search for.
     * @param <T>           The comparable type.
     * @return The first occurrence of an element equals to searchElement.
     */
    public static <T extends Comparable<T>> T findFirst(T[] input, T searchElement)  {

        int start = 0;
        int end = input.length - 1;
        T first = null;

        while (start <= end) {
            int mid = (start + end) / 2;
            //mid ist größer als das searchElement
            if (input[mid].compareTo(searchElement) > 0) {
                end = mid - 1;

            }
            // mid ist kleiner als das searchElement
            else if (input[mid].compareTo(searchElement) < 0) {
                start = mid + 1;
            }
            // mid ist == das searchElement
            else {
                first = input[mid];
                end = mid - 1;
            }
        }
        if (first != null) {
            return first;
        }
        return null;
    }

    /**
     * Determines the last occurrence of an element equals to searchElement
     * in an input array sorted in ascending order (potentially featuring duplicates).
     *
     * @param input         The input array sorted in ascending order.
     * @param searchElement The element to search for.
     * @param <T>           The comparable type.
     * @return The last occurrence of an element equals to searchElement.
     */
    public static <T extends Comparable<T>> T findLast(T[] input, T searchElement) {
        int start = 0;
        int end = input.length - 1;
        T last = null;

        while (start <= end) {
            // mid ist größer als searchElement
            int mid = (start + end) / 2;
            if (input[mid].compareTo(searchElement) > 0) {
                end = mid - 1;
            }

            // mid ist kleiner als searchElement
            else if (input[mid].compareTo(searchElement) < 0) {
                start = mid + 1;
            }

            // mid ist == searchElement
            else {
                last = input[mid];
                start = mid + 1;
            }

        }
        if (last != null) {
            return last;
        }

        return null;
    }

    /**
     * Finds all an elements equals to searchElement
     * in an input array sorted in ascending order (potentially featuring duplicates).
     *
     * @param input         The input array sorted in ascending order.
     * @param searchElement The element to search for.
     * @param <T>           The comparable type.
     * @return An iterator over all elements equal to searchElement.
     */
    public static <T extends Comparable<T>> Iterator<T> findAll(T[] input, T searchElement)  {
        int start = 0;
        int end = input.length - 1;
        T endIndex = null;

        LinkedList<T> list = new LinkedList<>();

        while (start <= end) {
            int mid = (start + end) / 2;
            // mid ist größer als searchElement
            if (input[mid].compareTo(searchElement) > 0) {
                end = mid - 1;
            }
            // mid ist kleiner als searchElement
            else if (input[mid].compareTo(searchElement) < 0) {
                start = mid + 1;
            }
            // mid == searchElement
            else {
                endIndex = input[mid];
                start = mid + 1;
            }

            list.add(endIndex);
        }

        start = 0;
        end = input.length - 1;
        T startIndex = null;

        while (start <= end) {
            int mid = (start + end) / 2;
            //mid ist größer als das searchElement
            if (input[mid].compareTo(searchElement) > 0) {
                end = mid - 1;
            }
            // mid ist kleiner als das searchElement
            else if (input[mid].compareTo(searchElement) < 0) {
                start = mid + 1;
            }
            // mid ist == das searchElement
            else {
                startIndex = input[mid];
                end = mid - 1;
            }
        }

        list.addFirst(startIndex);


        if (list.isEmpty()) {
            return null;
        }

        return list.iterator();
    }

    public static void main(String[] args) {

        int[] arr = new int[]{1, 2, 4, 4, 4, 4, 5, 6};
    }
}