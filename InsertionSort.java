public class InsertionSort {

	public static void sort(int[] v) {
	
		for (int i = 1; i < v.length; i++) {
			insereOrdenado(v, i);
		}
	}

	private static void insereOrdenado(int[] v, int i) {
		while (i >= 1 && v[i] < v[i-1]) {
			swap(v, i, i-1);
			i--;
		}
	}

	private static void swap(int[] v, int i, int j) {
		int aux = v[i];
		v[i] = v[j];
		v[j] = aux;
	}
}
