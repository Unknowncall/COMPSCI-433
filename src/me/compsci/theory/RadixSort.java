package me.compsci.theory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RadixSort {

    private final int[] array;
    private final int n;

    public RadixSort(int array[], int length) {
        this.array = array;
        this.n = length;
    }

    public static int getMax(int A[], int n) {
        int max = A[0];

        for (int i = 1; i < n; i++) {
            if (A[i] > max) {
                max = A[i];
            }
        }

        return max;
    }

    public static int getMin(int A[], int n) {
        int min = A[0];

        for (int i = 1; i < n; i++) {
            if (A[i] < min) {
                min = A[i];
            }
        }
        return min;
    }

    private static void countSortOnDigits(int A[], int n, int digits[]) {
        int[] C = new int[10];
        int[] T = new int[n];

        for (int i = 0; i <= n - 1; i++) {
            C[digits[i]]++;
        }
        for (int i = 1; i <= 9; i++) {
            C[i] += C[i - 1];
        }
        for (int i = n - 1; i >= 0; i--) {
            T[C[digits[i]] - 1] = A[i];
            C[digits[i]]--;
        }
        for (int i = 0; i < n; i++) {
            A[i] = T[i];
        }
    }

    private static void radixSortNonNeg(int A[], int n) {
        int M = getMax(A, n);
        int[] digits = new int[n];
        int e = 1;

        while (M / e > 0) {
            for (int i = 0; i <= n - 1; i++) {
                digits[i] = (A[i] / e) % 10;
            }
            countSortOnDigits(A, n, digits);
            e *= 10;
        }
    }

    public void radixSort() {
        int m = getMin(array, n);
        if (m >= 0) {
            radixSortNonNeg(array, n);
            return;
        }

        for (int i = 0; i < n; i++) {
            array[i] -= m;
        }
        radixSortNonNeg(array, n);
        for (int i = 0; i < n; i++) {
            array[i] += m;
        }
    }

    public void radixApproach2() {
        List<Integer> neg = new ArrayList<>();
        List<Integer> nonNeg = new ArrayList<>();
        for (int i : array) {
            if (i < 0) {
                neg.add(i);
            } else {
                nonNeg.add(i);
            }
        }

        int[] positive = new int[nonNeg.size()];
        for (int i = 0; i < nonNeg.size(); i++) {
            positive[i] = nonNeg.get(i);
        }
        int[] negative = new int[neg.size()];
        for (int i = 0; i < neg.size(); i++) {
            negative[i] = neg.get(i) * (-1);
        }

        RadixSort posRadix = new RadixSort(positive, positive.length);
        RadixSort negRadix = new RadixSort(negative, negative.length);

        posRadix.radixSort();
        negRadix.radixSort();

        for (int i = 0; i < negative.length; i++) {
            negative[i] = negative[i] * (-1);
        }
        for (int i = negative.length - 1, count = 0; i > 0; i--) {
            array[count] = negative[i];
            count++;
        }
        for (int i = 0; i < positive.length; i++) {
            array[i + negative.length] = positive[i];
        }
    }
}
