package me.compsci.theory;

import me.compsci.theory.InsertionSort;
import me.compsci.theory.Partition;

public class QuickSort extends Partition {

	public QuickSort(int[] array, int n) {
		super(array, n);
	}

	public void quicksortMedianOf3() {
		quicksortMedianOf3(0, n - 1);
	}

	public void quicksortRandom() {
		quicksortRandom(0, n - 1);
	}

	private void quicksortMedianOf3(int left, int right) {
		if(right - left < 31) {
			InsertionSort.insertionSort(array, left, right);
			return;
		}
		
		int pivot = generateMedianOf3Pivot(left, right);
		int partitionIndex = partition(left, right, pivot);
		quicksortMedianOf3(left, partitionIndex - 1);
		quicksortMedianOf3(partitionIndex + 1, right);
	}

	private void quicksortRandom(int left, int right) {
		if(right - left < 31) {
			InsertionSort.insertionSort(array, left, right);
			return;
		}
		
		int pivot = generateRandomPivot(left, right);
		int partitionIndex = partition(left, right, pivot);
		quicksortRandom(left, partitionIndex - 1);
		quicksortRandom(partitionIndex + 1, right);
	}
}
