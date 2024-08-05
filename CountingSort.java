public class CountingSort {

	public static void sort(int[] v) {
		
		if (v.length == 0) {
			return;
		}

		int maior = v[0];
		for (int i = 1; i < v.length; i++) {
			if (v[i] > maior) {
				maior = v[i];
			}
		}

		countingSort(v, maior);
	}

	private static int[] countingSort(int[] v, int k) {
		int menor = v[0];
		for (int i = 1; i < v.length; i++) {
			if (v[i] < menor) {
				menor = v[i];
			}
		}
		
		if (menor > 0) {
			menor = 0;
		}

		int[] c = new int[k+1-menor];

		for (int i = 0; i < v.length; i++) {
			c[v[i]-menor] += 1;
		}

		for (int i = 1; i < c.length; i++) {
			c[i] += c[i-1];
		}

		int[] u = new int[v.length];

		for (int i = v.length-1; i >= 0; i--) {
			u[c[v[i]-menor]-1] = v[i];
			c[v[i]-menor] -= 1;
		}

		return u;
	}
	
}
