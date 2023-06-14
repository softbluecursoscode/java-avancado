package br.com.softblue.javaavancado.exercicio.entity;

/**
 * Representa um CD
 */
public class CD {

	/**
	 * ID
	 */
	private int id;
	
	/**
	 * Nome
	 */
	private String nome;
	
	/**
	 * Categoria 
	 */
	private Categoria categoria;
	
	/**
	 * Conteúdo
	 */
	private String conteudo;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CD [id=" + id + ", nome=" + nome + ", categoria=" + categoria + ", conteudo=" + conteudo + "]";
	}
}
