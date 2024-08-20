import java.util.Scanner;
import java.util.Objects;

public class TabelaHash {

	private Aluno[] tabela;
	private int size;

	public TabelaHash() {
		this.tabela = new Aluno[20];
	}	

	public void clear() {
		this.tabela = new Aluno[this.tabela.length];
	}

	public boolean isEmpty() {
		for (int i = 0; i < this.tabela.length; i++) {
			if (this.tabela[i] != null) {
				return false;
			}
		}
		return true;
	}

	public boolean containsValue(Aluno a) {
		for (int i = 0; i < this.tabela.length; i++) {
			if (this.tabela[i] != null && a.equals(this.tabela[i])) {
				return true;
			}
		}
		return false;
	}

	public boolean containsKey(int k) {
		int hash;
		int sondagem = 0;

		while (sondagem < this.tabela.length) {

                        hash = (hash(k) + sondagem) % this.tabela.length;

                        if (this.tabela[hash] == null) {
				return false;
			}
			
			if (!this.tabela[hash].foiDeletado()
					&& this.tabela[hash].checaMatricula(k)) {
                                return true;
                        }

                        sondagem++;
                }
		
		return false;

	}
	
	public TabelaHash(int capacidade) {
		this.tabela = new Aluno[capacidade];
	}

	private int hash(int k) {
		double c = 0.63274838;
		int hash = (int) (this.tabela.length * ((k * c) % 1));
		return hash;
	}

	public void put(Aluno a, int k) {
		int hash;
		int sondagem = 0;
		
		if ( (this.size / this.tabela.length) >= 0.75) {
			resize();
		}
		while (sondagem < this.tabela.length) {
			
			hash = (hash(k) + sondagem) % this.tabela.length;
			
			if (this.tabela[hash] == null || this.tabela[hash].foiDeletado() || 
					this.tabela[hash].checaMatricula(k)) {
				this.tabela[hash] = a;
				this.size++;
				return;
			}
			sondagem++;
		}
		
	}

	public void remove(int k) {
		int hash;
		int sondagem = 0;

		while (sondagem < this.tabela.length) {
			
			hash = (hash(k) + sondagem) % this.tabela.length;

			if (this.tabela[hash].checaMatricula(k)) {
				this.tabela[hash].seDeleta();
				this.size--;
				return;
			} else if (this.tabela[hash] == null) {
				return;	
			}
			sondagem++;
		}
	}	

	public Aluno get(int k) {
		int hash;
		int sondagem = 0;

		while (sondagem < this.tabela.length) {
			
			hash = (hash(k) + sondagem) % this.tabela.length;

			if (this.tabela[hash] == null) {
				return null;
			} else if (this.tabela[hash].checaMatricula(k)) {
				return this.tabela[hash];
			}

			sondagem++;
		}

		return null;
	}

	private void resize() {
		TabelaHash novaTabela = new TabelaHash(this.tabela.length * 2);
		rehash(novaTabela);
		this.tabela = novaTabela.getTabela();
	}

	private void rehash(TabelaHash t) {
		for (int i = 0; i < this.tabela.length; i++) {
			if (this.tabela[i] != null && !this.tabela[i].foiDeletado()) {
				t.put(this.tabela[i], this.tabela[i].getMatricula());
			}
		}
	}

	public Aluno[] getTabela() {
		return this.tabela.clone();
	}

	public int size() {
		return this.size;
	}
}

class Aluno {

	private int matricula;
	private String nome;

	public Aluno(int matricula, String nome) {
		this.matricula = matricula;
		this.nome = nome;
	}

	public boolean checaMatricula(int valor) {
		return this.matricula == valor;
	}

	public String getNome() {
		return this.nome;
	}

	public void seDeleta() {
		this.nome = "DELETED";
	}

	public boolean foiDeletado() {
		return this.nome.equals("DELETED");
	}

	public int getMatricula() {
		return this.matricula;
	}

	@Override
	public int hashCode() {
		return Objects.hash(matricula);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aluno other = (Aluno) obj;
		return Objects.equals(matricula, other.getMatricula());
	}
}
