package br.com.softblue.javaavancado.exercicio;

/**
 * Exceção criada para indicar exceções da aplicação
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
