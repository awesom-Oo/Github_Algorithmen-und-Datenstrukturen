package ueb11;

import java.util.Comparator;
import java.util.List;

public class SimpleSorting {

    public static <E> boolean isSorted(List<E> list, Comparator<E> comp) {
        return isSortedAscending(list, comp) || isSortedDescending(list, comp);
    }

    public static <E> boolean isSortedAscending(List<E> list, Comparator<E> comp) {
        for (int i = 1; i < list.size(); i++) {
            if (comp.compare(list.get(i - 1), (list.get(i))) > 0) {
                return false;
            }
        }
        return true;
    }

    public static <E> boolean isSortedDescending(List<E> list, Comparator<E> comp) {
        for (int i = 1; i < list.size(); i++) {
            if (comp.compare(list.get(i - 1), (list.get(i))) < 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Liste wird sortiert zurück gegeben.
     *
     * @param list
     * @param comp
     * @param <E>
     *
     * @return
     */
    public static <E> void selectionsSort(List<E> list, Comparator<E> comp) {
        if (isSorted(list, comp)) {
            return;
        }
        int min;
        for (int i = 0; i < list.size(); i++) {
            min = i;
            for (int j = i + 1; j < list.size(); j++) {
                if (comp.compare(list.get(j), list.get(min) ) < 0) {
                    min = j;
                }
            }
            swap(list, i, min);
        }
    }

    public static <E> void swap(List<E> list, int x, int y) {
        E tmp = list.get(x);
        list.set(x, list.get(y));
        list.set(y, tmp);
    }

    public static <E> void insertionSort(List<E> list, Comparator<E> comp) {
        E x;
        int i;

        for (int j = 1; j < list.size(); j++) {
            x = list.get(j);
            for (i = j - 1; (i >= 0 && comp.compare(x, list.get(i)) < 0); i--) {
                list.set(i + 1, list.get(i));
            }
            list.set(i + 1, x);
        }
    }
}
