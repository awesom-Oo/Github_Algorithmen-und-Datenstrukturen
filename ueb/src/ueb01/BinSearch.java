package ueb01;

import java.util.Iterator;

/**
 * Collection of binary search methods for duplicate handling in arrays.
 */
public class BinSearch {

    /**
     * Examines whether input is sorted in ascending order according to the compareTo method.
     * @param input The array to determine an ascending order on.
     * @param <T> The comparable type.
     * @return true, if input sorted in ascending order; false, otherwise.
     */
    private static <T extends Comparable<T>> boolean isSorted(T[] input){
        //TODO: 1.3 a)
        return false;
    }

    /**
     * Determines the first occurrence of an element equals to searchElement
     * in an input array sorted in ascending order (potentially featuring duplicates).
     * @param input The input array sorted in ascending order.
     * @param searchElement The element to search for.
     * @param <T> The comparable type.
     * @return The first occurrence of an element equals to searchElement.
     */
    public static <T extends Comparable<T>> T findFirst(T[] input, T searchElement) {
        //TODO: 1.3 b)
        return null;
    }

    /**
     * Determines the last occurrence of an element equals to searchElement
     * in an input array sorted in ascending order (potentially featuring duplicates).
     * @param input The input array sorted in ascending order.
     * @param searchElement The element to search for.
     * @param <T> The comparable type.
     * @return The last occurrence of an element equals to searchElement.
     */
    public static <T extends Comparable<T>> T findLast(T[] input, T searchElement){
        //TODO: 1.3 c)
        return null;
    }

    /**
     * Finds all an elements equals to searchElement
     * in an input array sorted in ascending order (potentially featuring duplicates).
     * @param input The input array sorted in ascending order.
     * @param searchElement The element to search for.
     * @param <T> The comparable type.
     * @return An iterator over all elements equal to searchElement.
     */
    public static <T extends Comparable<T>> Iterator<T> findAll(T[] input, T searchElement){
        //TODO: 1.3 d)
        return null;
    }

}
