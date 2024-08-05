public class BubbleSort {

	public static void sort(int[] v) {
		
		boolean troca = true;
		int i = v.length - 1;
		while (troca) {
			troca = false;
			troca = bubbleStep(v, i);
			i--;
		}

	}

	private static boolean bubbleStep(int[] v, int fim) {
		boolean troca = false;
		for (int i = 0; i < fim; i++) {
			if (v[i] > v[i+1]) {
				swap(v, i, i+1);
				troca = true;
			}
		}
		return troca;
	}

	private static void swap(int[] v, int i, int j) {
		int aux = v[i];
		v[i] = v[j];
		v[j] = aux;
	}

}
