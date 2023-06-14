package br.com.softblue.javaavancado.exercicio;

public class Papel {
	public enum Cor {
		Branco, Preto, Azul, Amarelo
	}

	private int id;
	private Cor cor;

	public Papel(int id, Cor cor) {
		super();
		this.id = id;
		this.cor = cor;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Cor getCor() {
		return cor;
	}

	public void setCor(Cor cor) {
		this.cor = cor;
	}

}
