
public class RadixSort {

	private final int[] array;
	private final int n;

	public RadixSort(int array[], int length) {
		this.array = array;
		this.n = length;
	}
	
	public static int getMax(int A[], int n) {
		int max = A[0];
		
		for(int i = 1; i < n; i++) {
			if(A[i] > max) {
				max = A[i];
			}
		}
		
		return max;
	}
	
	private static void countSortOnDigits(int A[], int n, int digits[]) {
		int[] C = new int[10];
		int[] T = new int[n];
		
		for(int i = 0; i <= n - 1; i++) { C[digits[i]]++; }
		for(int i = 1; i <= 9; i++) { C[i] += C[i - 1]; }
		for(int i = n - 1; i >= 0; i--) { 
			T[C[digits[i]] - 1] = A[i];
			C[digits[i]]--;
		}
		for(int i = 0; i < n; i++) { A[i] = T[i]; }
	}

	private static void radixSortNonNeg(int A[], int n) {
		int M = getMax(A, n);
		int[] digits = new int[n];
		int e = 1;
		
		while(M / e > 0) {
			for(int i = 0; i <= n - 1; i++) { digits[i] = (A[i] / e) % 10; }
			countSortOnDigits(A, n, digits);
			e *= 10;
		}
	}

	public void radixSort() { 
		
	}
}
