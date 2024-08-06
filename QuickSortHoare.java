import java.util.Arrays;

public class QuickSortHoare {

	public static void sort(int[] v) {
		quickSort(v, 0, v.length-1);	
	}

	private static void quickSort(int[] v, int ini, int fim) {
		if (ini < fim) {
			int index = particionaHoare(v, ini, fim);
			quickSort(v, ini, index-1);
			quickSort(v, index+1, fim);
		}
	}

	private static int particionaHoare(int[] v, int ini, int fim) {
		int indexPivot = medianaDeTres(v, ini, fim);
		swap(v, ini, indexPivot);

		int pivot = v[ini];
		int i = ini+1;
		int j = fim;

		while (i <= j) {
		
			while (i <= j && v[i] <= pivot) {
				i = i + 1;
			}

			while (i <= j && v[j] > pivot) {
				j = j - 1;
			}

			if (i < j) {
				swap(v, i, j);
			}
		}

		swap(v, ini, j);

		return j;
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
                if (sorted[1] == ini) {
			return ini;
		} else if (sorted[1] == fim) {
			return fim;
		} else {
			return (ini + fim)/2;
		}
        }

}

