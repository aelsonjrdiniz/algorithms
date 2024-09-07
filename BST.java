import java.util.ArrayDeque;
import java.util.Scanner;
import java.lang.Math;
import java.util.Arrays;

public class BST {
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		String[] v = sc.nextLine().split(" ");

		BST tree = new BST();
		for (String s: v) {
			tree.add(Integer.parseInt(s));
		}
		
		System.out.println(tree.toStringLarguraEsquerdaRec().trim());	
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
		if (isEmpty()) return;
		
		Node aux = search(value);
		if (aux == null) return;
		
		remove(aux);
	}

	private void remove(Node current) {
		if (current.isLeaf()) removeFolha(current);
		else if (current.hasOneChild()) removeNo(current);
		else {
			Node sucessor = sucessor(current);
			current.value = sucessor.value;
			remove(sucessor);
		}
	}

	private void removeFolha(Node node) {
		if (node.value == this.root.value) this.root = null;
		else if (node.value < node.parent.value) node.parent.left = null;
		else node.parent.right = null;
	}

	private void removeNo(Node node) {
		if (node.hasOnlyLeftChild()) {
			if (node.value == this.root.value) {
				this.root = node.left;
				this.root.parent = null;
			} else {
				node.left.parent = node.parent;
				if (node.value < node.parent.value) {
					node.parent.left = node.left;
				} else {
					node.parent.right = node.left;
				}
			}
		} else {
			if (node.value == this.root.value) {
				this.root = node.right;
				this.root.parent = null;
			} else {
				node.right.parent = node.parent;
				if (node.value < node.parent.value) {
					node.parent.left = node.right;
				} else {
					node.parent.right = node.right;
				}
			}
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
