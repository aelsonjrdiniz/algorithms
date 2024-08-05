public class MergeSort {

	public static void sort(int[] v) {
		mergeSort(v, 0, v.length-1);
	}

	private static void mergeSort(int[] v, int ini, int fim) {
		if (ini < fim) {
			int meio = (ini + fim)/2;
			mergeSort(v, ini, meio);
			mergeSort(v, meio+1, fim);
			merge(v, ini, meio, fim);
		}
	}

	private static void merge(int[] v, int ini, int meio, int fim) {
		int i = ini;
		int j = meio+1;
		int k = 0;
		int[] helper = new int[v.length];
		
		for (int c = 0; i < v.length; c++) {
			helper[c] = v[c];
		}

		while (i <= meio & j <= fim) {
			if (helper[i] < helper[j]) {
				v[k++] = helper[i++];
			} else {
				v[k++] = helper[j++];
			}
		}

		while (i <= meio) {
			v[k++] = helper[i++];
		}
	}

}
