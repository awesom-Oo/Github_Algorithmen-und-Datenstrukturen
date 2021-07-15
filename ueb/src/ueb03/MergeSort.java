package ueb03;

class MergeSort {

    public static int swapCount;

    public static int sortAndCount(int[] array) {
        if(array.length <= 1) {
            return -1;
        }
        int mid = array.length / 2;

        int[] leftArray = new int[mid];
        int[] rightArray;

        if(array.length % 2 == 0) {
            rightArray = new int[mid];
        } else {
            rightArray = new int[mid + 1];
        }

        for (int i = 0; i < mid; i++) {
            leftArray[i] = array[i];
        }
        for (int j = 0; j < rightArray.length; j++) {
            rightArray[j] = array[mid+j];
        }
        sortAndCount(leftArray);
        sortAndCount(rightArray);


        return merge(leftArray, rightArray);
    }

    public static int merge(int[] leftArray, int[] rightArray) {
        int[] resultArray = new int[leftArray.length + rightArray.length];

        int leftPointer, rightPointer, resultPointer;
        leftPointer = rightPointer = resultPointer = 0;

        while (leftPointer < leftArray.length || rightPointer < rightArray.length) {
            if (leftPointer < leftArray.length && rightPointer < rightArray.length) {
                if (leftArray[leftPointer] <= rightArray[rightPointer]) {
                    resultArray[resultPointer++] = leftArray[leftPointer++];
                } else {
                    resultArray[resultPointer++] = rightArray[rightPointer++];
                    swapCount += (leftArray.length - leftPointer);
                }

            }
            else if(leftPointer < leftArray.length) {
                resultArray[resultPointer++] = leftArray[leftPointer++];
            }
            else if(rightPointer < rightArray.length) {
                resultArray[resultPointer++] = rightArray[rightPointer++];
            }
        }
        return swapCount;
    }

    public static void main(String args[]) {
        int[] array = { 20, 1, 4, 20, 7 };

    }

}