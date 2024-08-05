public class SelectionSort {

	public static void sort(int[] v) {
	
		for (int i = 0; i < v.length -1; i++) {
			int indexMenor = encontraMenor(v, i);
			swap(v, indexMenor, i);
		}
	}

	private static int encontraMenor(int[] v, int pos) {
		int indexMenor = pos;
		for (int j = pos + 1; j < v.length; j++) {
			if (v[j] < v[indexMenor]) {
				indexMenor = j;
			}
		}
		return indexMenor;
	}

	private static void swap(int[] v, int id, int i) {
		int aux = v[id];
		v[id] = v[i];
		v[i] = aux;
	}

}
