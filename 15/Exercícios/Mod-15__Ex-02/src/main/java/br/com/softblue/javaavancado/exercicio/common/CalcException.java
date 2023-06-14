package br.com.softblue.javaavancado.exercicio.common;

/**
 * @author Carlos
 *
 */
@SuppressWarnings("serial")
public class CalcException extends Exception {

	/**
	 * @see Exception#Exception()
	 */
	public CalcException() {
	}

	/**
	 * @see Exception#Exception()
	 * @param message
	 * @param cause
	 */
	public CalcException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @see Exception#Exception(String)
	 * @param message
	 */
	public CalcException(String message) {
		super(message);
	}

	/**
	 * @see Exception#Exception(Throwable)
	 * @param cause
	 */
	public CalcException(Throwable cause) {
		super(cause);
	}
}
