package teachings;

import java.util.Comparator;
import java.util.List;

public class Sorting<T> {



    public boolean isSortedAscending(List<T> list, Comparator<T> c) {

        for (int i = 0; i < list.size(); i++) {
            if (c.compare(list.get(i-1), list.get(i)) > 0) return false;
        }
        return true;
    }

    public boolean isSortedDescending(List<T> list, Comparator<T> c) {

        for (int i = 1; i < list.size(); i++) {
            if (c.compare(list.get(i-1), list.get(i)) < 0) return false;
        }
        return true;
    }

    public void selectionSort(List<T> list, Comparator<T> c) {
        int min;
        for (int i = 0; i < list.size(); i++) {
            min = i;
            for (int j = i + 1; j < list.size(); j++) {
                if (c.compare(list.get(j), list.get(min) ) < 0) {
                    min = j;
                }
            }
            swap(list, i, min);
        }
    }

    private void swap(List<T> list, int x, int y) {
        list.set(x, list.get(y));
        list.set(y, list.get(x));
    }

    public void insertionSort(List<T> list, Comparator<T> c) {
        T key;
        
        for (int i = 1; i < list.size(); i++) {
            key = list.get(i); int j = i-1;

            for (;j >= 0 && c.compare(key, list.get(j)) < 0; j--) {
                list.set(j + 1, list.get(j));
            }

            list.set(j + 1, key);
        }
    }

    public static void main(String[] args) {

    }

}
