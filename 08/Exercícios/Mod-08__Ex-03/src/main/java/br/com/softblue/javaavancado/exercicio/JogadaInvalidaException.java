package br.com.softblue.javaavancado.exercicio;

/**
 * Exce��o que representa uma jogada inv�lida realizada pelo jogador
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
