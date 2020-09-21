package me.compsci.theory;

import java.util.Arrays;

public class InversionCounting {

    private final int mergedArray[];
    private final int array[];
    private final int n;

    public InversionCounting(int[] array, int n) {
        this.array = array;
        this.mergedArray = new int[n];
        this.n = n;
    }

    public int bruteForce() {
        int count = 0;
        for (int i = 0; i < n; i++)
            for (int j = i + 1; j < n; j++)
                if (array[i] > array[j])
                    count++;
        return count;
    }

    public int countInversions() {
        return countInversions(0, n - 1);
    }

    private int countInversions(int left, int right) { // complete this function
        int count = 0;
        if (right - left < 2) {
            InsertionSort.insertionSort(array, left, right);
            return count;
        }
        int mid = (left + right) / 2;
        count += countInversions(left, mid);
        count += countInversions(mid + 1, right);
        int i = left, j = mid + 1, k = left;
        while (i <= mid && j <= right) {
            if (array[j] < array[i]) {
                mergedArray[k++] = array[j++];
                count += (mid - i);
            } else {
                mergedArray[k++] = array[i++];
                count++;
            }
        }
        return count;
    }
}
