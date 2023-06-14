package br.com.softblue.javaavancado.exercicio;

/**
 * Exce��o criada para indicar exce��es da aplica��o
 * @author Carlos
 */
@SuppressWarnings("serial")
public class HTTPServerException extends Exception {

	public HTTPServerException() {
	}

	public HTTPServerException(String message, Throwable t) {
		super(message, t);
	}

	public HTTPServerException(String message) {
		super(message);
	}

	public HTTPServerException(Throwable t) {
		super(t);
	}

}
