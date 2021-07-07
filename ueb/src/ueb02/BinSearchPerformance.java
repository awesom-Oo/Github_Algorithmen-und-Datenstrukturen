package ueb02;
import java.util.Arrays;
import java.util.Random;

/**
 * Template for Solution of Task 2.5
 */
public class BinSearchPerformance {
    /**
     * Binary search implementation from the lecture
     * @param arr sorted int array
     * @param low lowest index of search space
     * @param high highest index of search space
     * @param searchElement element to search for
     * @return true if arr contains searchElement within the given bounds
     */

    private static int contains2Depth = 0;
    private static int contains3Depth = 0;


    static boolean contains2(int[] arr, int low, int high, int searchElement) {
        if (low > high) {
            return false;
        }
        int mid = low + (high - low) / 2;
        if (arr[mid] == searchElement) {
            return true;
        }
        if (arr[mid] < searchElement) {
            contains2Depth++;
            return contains2(arr, mid + 1, high, searchElement);
        }
        else {
            contains2Depth++;
            return contains2(arr, low, mid - 1, searchElement);
        }
    }

    /**
     * Alternative search algorithm
     * @param arr sorted int array
     * @param low lowest index of search space
     * @param high highest index of sear0ch space
     * @param searchElement element to search for
     * @return true if arr contains searchElement within the given bounds
     */
    static boolean contains3(int[] arr, int low, int high, int searchElement) {
        int index;
        if (low < high && searchElement >= arr[low] && searchElement <= arr[high]) {
            index = low + ((searchElement-arr[low])*(high-low)) / (arr[high]-arr[low]);
            if (arr[index] == searchElement) {
                return true;
            }
            if (arr[index] < searchElement) {
                contains3Depth++;
                return contains3(arr, index+1, high, searchElement);
            }
            if (arr[index] > searchElement) {
                contains3Depth++;
                return contains3(arr, low, index-1, searchElement);
            }
        }
        return false;
    }

    /**
     * Creates a sorted array of random positive ints
     * @param size the number of elements to generate
     * @return a random array of size random positive elements
     */
    static int[] createRandomArraySorted(int size){
        Random random = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Math.abs(random.nextInt(100001));
        }
        for (int j = 0; j < arr.length; j++) {
            for (int k = j+1; k < arr.length; k++) {
                if (arr[j] > arr[k]) {
                    int temp = arr[j];
                    arr[j] = arr[k];
                    arr[k] = temp;
                }
            }
        }
        return arr;
    }

    /**
     * Task 2.5 b) Compare the two methods contains2 and contain3 against each other
     * @param args can be ignored
     */
    public static void main(String[] args) {
        int[] sizes = new int[]{1234, 10001, 19999, 6969, 4224, 12924, 18819, 7291, 29000, 18239};
        for (int number : sizes) {
            Random randomIndex = new Random();
            int[] array = createRandomArraySorted(18239);
            long startTime = System.nanoTime();
            contains3(array, 0, array.length - 1, array[randomIndex.nextInt(array.length)]);
            long endtime = System.nanoTime();
            long executionTimeContains3 = endtime - startTime;

            startTime = System.nanoTime();
            contains2(array, 0, array.length - 1, array[randomIndex.nextInt(array.length)]);
            endtime = System.nanoTime();
            long executionTimeContains2 = endtime - startTime;

            System.out.println("Arraysize " + number);
            System.out.println("Contain2 Time: " + executionTimeContains2 + " ns");
            System.out.println("Contain2 Depth: " + contains2Depth);

            System.out.println("Contain3 Time: " + executionTimeContains3 + " ns");
            System.out.println("Contain3 Depth: " + contains3Depth);

            System.out.println("---");

            contains3Depth = 0;
            contains2Depth = 0;
        }

    }
}

