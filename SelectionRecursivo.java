import java.util.Arrays;
import java.util.Scanner;

public class SelectionRecursivo {

	public static void selectionSort(int[] v, int i) {
		if (i < v.length-1) {
			int idMenor = encontraMenor(v, i+1, i);
			swap(v, i, idMenor);
			System.out.println(Arrays.toString(v));
			selectionSort(v, i+1);
		}
	}
	
	public static int encontraMenor(int[] v, int i, int idMenor) {
		if (i >= v.length) {
			return idMenor;
		} else {
			if (v[i] < v[idMenor]) {
				idMenor = i;
			}
			return encontraMenor(v, i+1, idMenor);
		}
	}

	public static void swap(int[] v, int i, int j) {
		int aux = v[i];
		v[i] = v[j];
		v[j] = aux;
	}
}
