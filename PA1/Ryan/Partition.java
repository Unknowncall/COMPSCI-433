import java.util.Random;

public class Partition {

	final int array[];
	final int n;

	static Random rand;

	public Partition(int[] array, int n) {
		this.array = array;
		this.n = n;
		rand = new Random(System.currentTimeMillis());
	}

	protected void swap(int x, int y) {
		int temp = array[x];
		array[x] = array[y];
		array[y] = temp;
	}

	protected int generateRandomPivot(int left, int right) {
		int pivotIndex = left + rand.nextInt(right - left + 1);
		return array[pivotIndex];
	}

	protected int generateMedianOf3Pivot(int left, int right) {
		int mid = (left + right) / 2;

		if (array[left] > array[mid])
			swap(left, mid);

		if (array[left] > array[right])
			swap(left, right);

		if (array[mid] > array[right])
			swap(mid, right);

		return array[mid];
	}

	public int partition(int left, int right, int pivot) { 
		int pivotIndex = left;
		int partitionIndex = left - 1;
		
		for(int i = left; i <= right; i++) {
			if(array[i] == pivot) { pivotIndex = i; }
			if(array[i] <= pivot) { partitionIndex++; }
		}
		
		swap(pivotIndex, partitionIndex);
		
		int j = left;
		int k = right;
		
		while(j < k) {
			while(j <= partitionIndex && array[j] <= pivot) { j++; }
			while(k > partitionIndex && array[k] > pivot) { k--; }
			if(j < k) {
				swap(pivotIndex, partitionIndex);
				j++;
				k--;
			}
		}
		
		return partitionIndex;
	}
}