import java.util.Scanner;
import java.util.Arrays;

public class InsertionRecursivo {

	public static void insertionRecursivo(int[] v) {
		insertionRecursivo(v, 1);
	}
	
	public static void insertionRecursivo(int[] v, int id) {
		if (id < v.length) {
			insereOrdenado(v, id);
			System.out.println(Arrays.toString(v));
			insertionRecursivo(v, id+1);
		}
	}

	public static void insereOrdenado(int[] v, int i) {
		if (i > 0 && v[i] < v[i-1]) {
			swap(v, i, i-1);
			insereOrdenado(v, i-1);
		}
	}

	public static void swap(int[] v, int i, int j) {
		int aux = v[i];
		v[i] = v[j];
		v[j] = aux;
	}

	public static int[] converteArray(String[] v) {
		int[] u = new int[v.length];
		for (int i = 0; i < v.length; i++) {
			u[i] = Integer.parseInt(v[i]);
		}
		return u;
	}

}
