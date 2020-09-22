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

    /*
        a[i] > a[j]; where i < j
        If an element is at a lower index and is larger then something at a higher index\
     */
    private int countInversions(int left, int right) { // complete this function
        if (right - left <= 2) {
            if (array[left] > array[right] && left < right) {
                return 1;
            }
            return 0;
        }
        int mid = (left + right) / 2;
        int count = 0;
        count += countInversions(left, mid);
        count += countInversions(mid + 1, right);
        int i = left, j = mid + 1, k = left;
        while (i <= mid && j <= right) {
            if (array[j] < array[i]) {
                mergedArray[k++] = array[j++];
            } else {
                count += mid - i + 1;
                mergedArray[k++] = array[i++];
            }
        }
        while (i <= mid) {
            mergedArray[k++] = array[i++];
        }
        while (j <= right) {
            mergedArray[k++] = array[j++];
        }
        i = left;
        while (i <= right) {
            array[i] = mergedArray[i];
            i++;
        }
        return count;
    }
}
