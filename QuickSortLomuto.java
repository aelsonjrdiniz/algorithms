import java.util.Arrays;

public class QuickSortLomuto {

	public static void sort(int[] v) {
		quickSort(v, 0, v.length-1);
	}

	private static void quickSort(int[] v, int ini, int fim) {
		if (ini < fim) {
			int index = particionaLomuto(v, ini, fim);
			quickSort(v, ini, index-1);
			quickSort(v, index+1, fim);
		}
	}
	
	private static int particionaLomuto(int[] v, int ini, int fim) {
		int pivot = medianaDeTres(v, ini, fim);
		int i = ini;
		for (int j = ini+1; j <= fim; j++) {
			if (v[j] <= pivot) {
				swap(v, ++i, j);
			}
		}	
		swap(v, ini, i);
		return i;
	}	
	
	private static void swap(int[] v, int i, int j) {
		int aux = v[i];
		v[i] = v[j];
		v[j] = aux;
	}

	private static int medianaDeTres(int[] v, int ini, int fim) {
		int meio = (ini + fim)/2;
		int[] sorted = new int[] {v[ini], v[meio], v[fim]};
		Arrays.sort(sorted);
		return sorted[1];
	}
}
