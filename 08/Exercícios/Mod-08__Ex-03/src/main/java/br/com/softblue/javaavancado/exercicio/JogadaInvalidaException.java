package br.com.softblue.javaavancado.exercicio;

/**
 * Exceção que representa uma jogada inválida realizada pelo jogador
 */
@SuppressWarnings("serial")
public class JogadaInvalidaException extends Exception {

	/**
	 * @see Exception#Exception(String)
	 */
	public JogadaInvalidaException(String message) {
		super(message);
	}
}
