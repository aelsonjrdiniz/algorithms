public class RadixSort {

	public static int[] sort(int[] v) {
	
		if (v.length == 0) {
			return v;
		}	

		int[] u = new int[v.length];

		for (int i = 0; i < v.length; i++) {
			u[i] = v[i];
		}

		int num = v[0];
		int digitos = 0;
		String word = num + "";

		for (int i = 0; i < word.length(); i++) {
			digitos += 1;
		}

		for (int i = digitos; i >= 1; i--) {
			u = countingSort(u, i-1);
		}

		return u;
	}

	private static int[] countingSort(int[] v, int pos) {
		int[] c = new int[10];
		int digito = 0;

		for (int i = 0; i < v.length; i++) {
			digito = pegaDigito(v, i, pos);
			c[digito] += 1;
		}

		for (int i = 1; i < c.length; i++) {
			c[i] += c[i-1];
		}
		
		int[] u = new int[v.length];

		for (int i = v.length-1; i >= 0; i--) {
			digito = pegaDigito(v, i, pos);
			u[c[digito]-1] = v[i];
			c[digito] -= 1;	
		}

		return u;
	}

	private static int pegaDigito(int[] v, int i, int pos) {
		String elemento = v[i] + "";
                char num = elemento.charAt(pos);
                int digito = Integer.parseInt(String.valueOf(num)); 
		return digito;
	}

}
