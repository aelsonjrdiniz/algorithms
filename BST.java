import java.util.ArrayDeque;
import java.util.Scanner;
import java.lang.Math;
import java.util.Arrays;
import java.util.ArrayList;

public class BST {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		String[] v = sc.nextLine().split(" ");
		
		//String[] u = sc.nextLine().split(" ");
		int num = Integer.parseInt(sc.nextLine());		
		BST tree = new BST();

		for (String s: v) {
			tree.add(Integer.parseInt(s));
		}
		
		String[] s = tree.caminhoPredecessorTST(num).trim().split(" ");
		System.out.println(Arrays.toString(s));
	}

	private Node root;
	private int size;

	public BST() {
		this.size = 0;
		this.root = null;
	}

	public boolean isEmpty() {
		return this.size == 0;
	}
	
	//Questão do TST
	public void removeElementos(String[] v) {
		ArrayList<Integer> lista = new ArrayList<>();
		ArrayList<Integer> aux = new ArrayList<>();

		for (String s: v) {
			lista.add(Integer.parseInt(s));
		}

		while (!lista.isEmpty()) {
			remove(lista.remove(0));
			Node current = this.root;
			preOrdemTST(aux, current);
						
			if (!isEmpty()) System.out.println(aux.toString());
			else System.out.println("null");
			
			aux = new ArrayList<Integer>();	
		}
	}

	//Questão do TST
	public void buscaElemento(int valor) {
		ArrayList<Integer> lista = new ArrayList<>();
		if (isEmpty()) System.out.println(lista.toString());

		buscaElemento(this.root, valor, lista);
		System.out.println(lista.toString());
	}

	private void buscaElemento(Node current, int valor, ArrayList<Integer> lista) {
		if (current == null) return;
		lista.add(current.value);

		if (current.value == valor) return;
		else if (valor < current.value) buscaElemento(current.left, valor, lista);
		else buscaElemento(current.right, valor, lista);
	}

	//Questão do TST
        public String caminhoMin() {
                if (isEmpty()) return "";
                return caminhoMin(this.root);
        }

        private String caminhoMin(Node current) {
                if (current != null) {
                        return " " + current.value + caminhoMin(current.left);
                } else return "";
        }



	//Questão do TST
	public String caminhoMax() {
		if (isEmpty()) return "";
		return caminhoMax(this.root);
	}

	private String caminhoMax(Node current) {
		if (current != null) {
			return " " + current.value + caminhoMax(current.right);
		} else return "";
	}

	//Questão do TST
	public void elementosMaiores(int valor) {
		ArrayList<Integer> lista = new ArrayList<>();
		preOrdemTST(lista, this.root);
		System.out.println(lista.toString());
		System.out.println(encontraMaiores(valor));
	}

	//Questão do TST
	public void elementosMenores(int valor) {
		ArrayList<Integer> lista = new ArrayList<>();
		preOrdemTST(lista, this.root);
		System.out.println(lista.toString());
		System.out.println(encontraMenores(valor));
	}

	private int encontraMenores(int valor) {
		return encontraMenores(this.root, valor);
	}

	private int encontraMenores(Node current, int valor) {
		if (current == null) return 0;
		int qtde = 0;

		if (current.value < valor) {
			qtde = 1;
		}

		return qtde + encontraMenores(current.left, valor) + encontraMenores(current.right, valor);
	}

	private int encontraMaiores(int valor) {
		return encontraMaiores(this.root, valor);
	}

	private int encontraMaiores(Node current, int valor) {
		if (current == null) return 0;
		int qtde = 0;

		if (current.value > valor) {
			qtde = 1;
		}

		return qtde + encontraMaiores(current.left, valor) + encontraMaiores(current.right, valor);
	}

	//Questão do TST
	public void encontraMaisProximo(int valor) {
		Node current = this.root;
		Node answer = this.root;
		
		while (current != null) {
			if (Math.abs(valor - current.value) < Math.abs(valor - answer.value)) answer = current;
			if (valor < current.value) current = current.left;
			else current = current.right;
		}

		ArrayList<Integer> lista = new ArrayList<>();
		preOrdemTST(lista, this.root);
		System.out.println(lista.toString());
		System.out.println(answer.value);

	}

	private void preOrdemTST(ArrayList<Integer> lista, Node current) {
		if (current != null) {
			lista.add(current.value);
			preOrdemTST(lista, current.left);
			preOrdemTST(lista, current.right);
		}
	}

	//Questão do TST
	public String toStringLarguraDireita() {
		String msg = "";
		if (isEmpty()) return msg;
		
		Node aux = this.root;

		ArrayDeque<Node> fila = new ArrayDeque<>();
		fila.addLast(aux);

		while (!fila.isEmpty()) {
			Node current = fila.removeFirst();
			if (current != null) {
				msg += current.value + " ";
				if (current.right != null) fila.add(current.right);
				if (current.left != null) fila.add(current.left);
			}
		}

		return msg;
	}

	//Questão do TST
        public String toStringLarguraEsquerda() {
                String msg = "";
                if (isEmpty()) return msg;

                Node aux = this.root;

                ArrayDeque<Node> fila = new ArrayDeque<>();
                fila.addLast(aux);

                while (!fila.isEmpty()) {
                        Node current = fila.removeFirst();
                        if (current != null) {
                                msg += current.value + " ";
                                if (current.left != null) fila.addLast(current.left);
				if (current.right != null) fila.addLast(current.right);
		       	}
                }

                return msg;
        }

	//Questão do TST
	public String toStringLarguraEsquerdaRec() {
                if (isEmpty()) return "";
                ArrayDeque<Node> fila = new ArrayDeque<>();
                fila.addLast(this.root);
		return toStringLarguraEsquerdaRec(fila);
        }
	
	//Questão do TST
	private String toStringLarguraEsquerdaRec(ArrayDeque<Node> fila) {
		
		if (fila.isEmpty()) return "";
		
		Node current = fila.removeFirst();
		
		if (current != null) 
			if (current.left != null) fila.addLast(current.left);
			if (current.right != null) fila.addLast(current.right);

		return " " + current.value + toStringLarguraEsquerdaRec(fila);
		
		
	}
	
	//Questão do TST
        public String toStringLarguraDireitaRec() {
                if (isEmpty()) return "";
                ArrayDeque<Node> fila = new ArrayDeque<>();
                fila.addLast(this.root);
                return toStringLarguraDireitaRec(fila);
        }

        //Questão do TST
        private String toStringLarguraDireitaRec(ArrayDeque<Node> fila) {
                
		if (fila.isEmpty()) return "";

                Node current = fila.removeFirst();

                if (current != null)
                        if (current.right != null) fila.addLast(current.right);
			if (current.left != null) fila.addLast(current.left);

                return " " + current.value + toStringLarguraDireitaRec(fila);

        }

	// Questão do TST
	public String caminhoSucessor(int value) {

		String msg = "";
		if (isEmpty()) return msg;
		
		Node aux = search(value);
		if (aux == null) return msg;

		msg += aux.value;

		if (aux.right != null) {
			aux = aux.right;
			msg += " " + aux.value;
			while (aux.left != null) {
				aux = aux.left;
				msg += " " + aux.value;
			}	
		} else {
			aux = aux.parent;
			while (aux != null) {
				msg += " " + aux.value;
				if (aux.value > value) break;
				aux = aux.parent;
			} 
		}

		return msg;
	}

	//Questão pra tirar onda com Ewerton
	public String caminhoPredecessorTST(int value) {
		if (isEmpty()) return "";
		
		Node aux = search(value);
		if (aux == null) return "";
		
		String msg = "";
		msg += aux.value;
		
		if (aux.left != null) msg += minimoSubArvore(aux.left);
		else msg += minAboveTST(aux.parent, value);
		return msg;
	}

	private String minimoSubArvore(Node current) {
		if (current == null) return "";
		else return " " + current.value + minimoSubArvore(current.right);
	}

	private String minAboveTST(Node current, int value) {
		if (current == null) return "";
		else if (current.value < value) return " " + current.value;
		else return " " + current.value + minAboveTST(current.parent,value);
	}

	//Questão do TST
	public String caminhoPredecessor(int value) {
		
		String msg = "";
		if (isEmpty()) return msg;

		Node aux = search(value);
		if (aux == null) return msg;

		msg += aux.value;

		if (aux.left != null) {
			aux = aux.left;
			msg += " " + aux.value;
			while (aux.right != null) {
				aux = aux.right;
				msg += " " + aux.value;
			}
		} else {
			aux = aux.parent;
			while (aux != null) {
				msg += " " + aux.value;
				if (aux.value < value) break;
				aux = aux.parent;
			}
		}

		return msg;
	}
	
	//Questão do TST
	public int somaFolhas() {
		if (isEmpty()) return 0;
		else return somaFolhas(this.root);
	}

	private int somaFolhas(Node current) {
		if (current == null) return 0;
		if (current.isLeaf()) return current.value;
		else return somaFolhas(current.left) + somaFolhas(current.right);
	}

	public void add(int v) {
		if (isEmpty()) this.root = new Node(v);
		else add(this.root, v);
		this.size++;
	}

	private void add(Node current, int v) {
		if (v < current.value) {
			if (current.left == null) {
				current.left = new Node(v);
				current.left.parent = current;
			} else add(current.left, v);
		} else {
			if (current.right == null) {
				current.right = new Node(v);
				current.right.parent = current;
			} else add(current.right, v);
		}
	}

	public Node search(int v) {
		if (isEmpty()) return null;
		else return search(this.root, v);
	}

	private Node search(Node current, int v) {
		if (current == null) return null;
		if (current.value == v) return current;
		if (v < current.value) return search(current.left, v);
		else return search(current.right, v);
	}

	public int height() {
		return height(this.root);
	}

	private int height(Node current) {
		if (current == null) return -1;
		else return 1 + Math.max(height(current.left), height(current.right));
	}

	public boolean equals(BST outra) {
		if (outra == null) return false;

		if ((isEmpty() && !outra.isEmpty()) || (!isEmpty() && outra.isEmpty())) return false;

		return equals(this.root, outra.root);
	} 

	private boolean equals(Node n1, Node n2) {
		if (n1 == null && n2 == null) return true;

		if ((n1 == null && n2 != null) || (n1 != null && n2 == null)) return false;

		if (n1.value != n2.value) return false;

		return equals(n1.left, n2.left) && equals(n1.right, n2.right);

	}

	public Node min() {
		if (isEmpty()) return null;
		else return min(this.root);
	}

	private Node min(Node current) {
		if (current.left == null) return current;
		else return min(current.left);
	}

	public Node max() {
		if (isEmpty()) return null;
		else return max(this.root);
	}

	private Node max(Node current) {
		if (current.right == null) return current;
		else return max(current.right);
	}

	public int contaNosInternos() {
		if (isEmpty()) return 0;
		else return contaNosInternos(this.root);
	}

	private int contaNosInternos(Node current) {
		if (current == null || current.isLeaf()) return 0;
		else return 1 + contaNosInternos(current.left) + contaNosInternos(current.right);
	}

	public int contaFolhas() {
		if (isEmpty()) return 0;
		else return contaFolhas(this.root);
	}

	private int contaFolhas(Node current) {
		if (current == null) return 0;
		if (current.isLeaf()) return 1;
		else return contaFolhas(current.left) + contaFolhas(current.right);
	}

	public Node sucessor(int v) {
		if (isEmpty()) return null;
		
		Node aux = search(v);
		if (aux == null) return null;

		return sucessor(aux);
	}

	private Node sucessor(Node current) {
		if (current.right != null) return min(current.right);
		else return minAbove(current.parent, current.value);
	}

	private Node minAbove(Node current, int value) {
		if (current == null) return null;
		if (current.value > value) return current;
		else return minAbove(current.parent, value);
	}

	public Node predecessor(int v) {
		if (isEmpty()) return null;

		Node aux = search(v);
		if (aux == null) return null;

		return predecessor(aux);
	}

	private Node predecessor(Node current) {
		if (current.left != null) return max(current.left);
		else return maxAbove(current.parent, current.value);
	}

	private Node maxAbove(Node current, int value) {
		if (current == null) return null;
		if (current.value < value) return current;
		else return maxAbove(current.parent, value);
	}
		
	public void remove(int value) {
        Node toRemove = search(value);
        if (toRemove != null) {
            remove(toRemove);
            this.size -= 1;
        }
        
    }
	private void remove(Node toRemove) {
        
        if (toRemove.isLeaf()) {
            if (toRemove.value == this.root.value)
                this.root = null;
            else {
                if (toRemove.value < toRemove.parent.value)
                    toRemove.parent.left = null;
                else
                    toRemove.parent.right = null;
            }
        
        } else if (toRemove.hasOnlyLeftChild()) {
            if (toRemove.value == this.root.value)  {
                this.root = toRemove.left;
                this.root.parent = null;
            } else {
                toRemove.left.parent = toRemove.parent;
                if (toRemove.value < toRemove.parent.value)
                    toRemove.parent.left = toRemove.left;
                else
                    toRemove.parent.right = toRemove.left;
            }
        } else if (toRemove.hasOnlyRightChild()) {
            if (toRemove.value == this.root.value) {
                this.root = toRemove.right;
                this.root.parent = null;
            } else {
                toRemove.right.parent = toRemove.parent;
                if (toRemove.value < toRemove.parent.value)
                    toRemove.parent.left = toRemove.right;
                else
                    toRemove.parent.right = toRemove.right;
            }
            
        } else {
            Node sucessor = sucessor(toRemove);
            toRemove.value = sucessor.value;
            remove(sucessor);
        }
            
    }

	public void preOrdem() {
		if (isEmpty()) return;
		preOrdem(this.root);
	}

	private void preOrdem(Node current) {
		if (current != null) {
			System.out.println(current.value);
			preOrdem(current.left);
			preOrdem(current.right);
		}
	}

	public void inOrdem() {
		if (isEmpty()) return;
		inOrdem(this.root);
	}

	private void inOrdem(Node current) {
		if (current != null) {
			inOrdem(current.left);
			System.out.println(current.value);
			inOrdem(current.right);
		}
	}

	public void posOrdem() {
		if (isEmpty()) return;
		posOrdem(this.root);
	}

	private void posOrdem(Node current) {
		if (current != null) {
			posOrdem(current.left);
			posOrdem(current.right);
			System.out.println(current.value);
		}
	}

}

class Node {

	Node parent;
	Node right;
	Node left;
	int value;

	public Node(int value) {
		this.value = value;
	}

	public boolean isLeaf() {
		return this.right == null && this.left == null;
	}

	public boolean hasOnlyLeftChild() {
		return this.right == null && this.left != null;
	}
	
	public boolean hasOnlyRightChild() {
		return this.right != null && this.left == null;
	}

	public boolean hasOneChild() {
		return hasOnlyLeftChild() || hasOnlyRightChild();
	}
}
