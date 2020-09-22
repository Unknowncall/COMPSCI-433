package me.compsci.theory;

import java.util.Arrays; 

public class InversionCounting {

	//private final int mergedArray[];
	private final int array[];
	private final int n;

	public InversionCounting(int[] array, int n) {
		this.array = array;
		//this.mergedArray = new int[n];
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
	
	private int countInversions(int left, int right) {  
		int count = 0; 

		if (left < right) { 
			int mid = (left + right) / 2; 
			count += countInversions(left, mid); 
			count += countInversions(mid + 1, right);
			
			int[] leftArr = Arrays.copyOfRange(array, left, mid + 1); 
			int[] rightArr = Arrays.copyOfRange(array, mid + 1, right + 1); 
			int i = 0, j = 0, k = left; 
			while (i < leftArr.length && j < rightArr.length) { 
				if (leftArr[i] <= rightArr[j]) 
					array[k++] = leftArr[i++]; 
				else { 
					array[k++] = rightArr[j++]; 
					count += (mid + 1) - (left + i); 
				} 
			} 
			while (i < leftArr.length) { 
				array[k++] = leftArr[i++];
			}
			while (j < rightArr.length) {
				array[k++] = rightArr[j++];
			}
		} 

		return count;
	}
}
