package ueb03;

import java.util.NoSuchElementException;

public interface COLAQuery<E extends Comparable<E>>  {

    /**
     * Searches the element with a bottom up algorithm, i.e.,
     * first looking in the largest array, then the second largest, ...,
     * and lastly in the smallest array.
     *
     * @param element The key of the element
     * @throws NoSuchElementException if the key if key is not present
     * @return the instance of this element in the COLA structure;
     */
    E searchElementBottomUp(E element) throws NoSuchElementException;

    /**
     * Searches the element with a top down algorithm, i.e.,
     * first looking in the smallest array, then the second smallest, ...,
     * and lastly in the largest array.
     *
     * @param element The element of the element
     * @throws NoSuchElementException if the element if element is not present
     * @return the instance of this element in the COLA structure;
     */
    E searchElementTopDown(E element) throws NoSuchElementException;
}