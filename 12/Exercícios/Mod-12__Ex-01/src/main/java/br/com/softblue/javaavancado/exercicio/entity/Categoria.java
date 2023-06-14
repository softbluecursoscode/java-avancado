package br.com.softblue.javaavancado.exercicio.entity;

/**
 * Categoria de um CD
 */
public enum Categoria {

	/**
	 * CD de software, música ou backup
	 */
	SOFTWARE('S'),
	MUSICA('M'),
	BACKUP('B');
	
	/**
	 * Categoria representada por um char
	 */
	private char categoria;
	
	/**
	 * Construtor
	 * @param c Char que representa a categoria
	 */
	Categoria(char c) {
		this.categoria = c;
	}
	
	/**
	 * Retorna o char que representa a categoria como uma String
	 * @see java.lang.Enum#toString()
	 */
	public String toString() {
		return String.valueOf(categoria);
	}
	
	/**
	 * Com base em um char, retorna o enum correspondente
	 * @param c Char que representa a categoria
	 * @return Enum da categoria do CD
	 */
	public static Categoria getCategoria(char c) {
		for (Categoria categoria : values()) {
			if (categoria.toString().charAt(0) == c) {
				return categoria;
			}
		}
		
		// Retorna null se a categoria não foi encontrada
		return null;
	}
}
