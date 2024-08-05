import java.util.Scanner;
import java.util.Arrays;

public class QuickSelection {

	public static void main(String[] args) {
	
		Scanner sc = new Scanner(System.in);
		String[] numeros = sc.nextLine().split(" ");
		int k = Integer.parseInt(sc.nextLine());
		int[] v = converteArray(numeros);
		System.out.println(v[quickSelection(v, k)]);
	}

	private static int[] converteArray(String[] v) {
		int[] u = new int[v.length];
		for (int i = 0; i < v.length; i++) {
			u[i] = Integer.parseInt(v[i]);
		}
		return u;
	}

	private static int quickSelection(int[] v, int k) {
		return quickSelection(v, k, 0, v.length-1);
	}

	private static int quickSelection(int[] v, int k, int ini, int fim) {
		if (ini < fim) {
			int index = particiona(v, ini, fim);
			if (index + 1 == k) {
				return index;
			} else if (index + 1 > k) {
				return quickSelection(v, k, ini, index-1);
			} else {
				return quickSelection(v, k, index+1, fim);
			}
		} else {
			return ini;
		}
	}

	private static int particiona(int[] v, int ini, int fim) {
		int indexPivot = medianaDeTres(v, ini, fim);
		swap(v, ini, indexPivot);
		int pivot = v[ini];
		int i = ini;
		for (int j = ini + 1; j <= fim; j++) {
			if (v[j] <= pivot) {
				swap(v, ++i, j);
			}
		}
		swap(v, i, ini);
		return i;
	}

	private static void swap(int[] v, int i, int j) {
		int aux = v[i];
		v[i] = v[j];
		v[j] = aux;
	}

	private static int medianaDeTres(int[] v, int ini, int fim) {
		int[] u = new int[]{v[ini], v[fim], v[(ini+fim)/2]};
		Arrays.sort(u);
		if (u[1] == ini) {
			return ini;
		} else if (u[1] == fim) {
			return fim;
		} else {
			return (ini+fim)/2;
		}
	}

}
