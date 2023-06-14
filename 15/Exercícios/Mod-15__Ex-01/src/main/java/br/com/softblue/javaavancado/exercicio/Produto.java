package br.com.softblue.javaavancado.exercicio;

/**
 * Representa um produto
 */
public class Produto {

	/**
	 * Nome
	 */
	private String nome;
	
	/**
	 * Categoria
	 */
	private int categoria;
	
	/**
	 * Valor
	 */
	private double valor;
	
	/**
	 * Vendido ou não
	 */
	private boolean vendido;
	
	/**
	 * Toda classe que vai ter objetos criados via reflexão precisa ter um construtor padrão
	 * (sem argumentos)
	 */
	public Produto() {
		
	}
	
	/**
	 * Construtor que recebe os dados do produto
	 * @param nome
	 * @param categoria
	 * @param valor
	 * @param vendido
	 */
	public Produto(String nome, int categoria, double valor, boolean vendido) {
		this.nome = nome;
		this.categoria = categoria;
		this.valor = valor;
		this.vendido = vendido;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Produto [nome=" + nome + ", categoria=" + categoria + ", valor=" + valor + ", vendido=" + vendido + "]";
	}
}
