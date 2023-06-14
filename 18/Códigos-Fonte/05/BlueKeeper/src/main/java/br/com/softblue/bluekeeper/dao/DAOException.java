package br.com.softblue.bluekeeper.dao;

/**
 * Exceção de DAO
 */
public class DAOException extends RuntimeException {

	private static final long serialVersionUID = -4876763771608177904L;

	public DAOException() {
	}

	public DAOException(String message) {
		super(message);
	}

	public DAOException(Throwable cause) {
		super(cause);
	}

	public DAOException(String message, Throwable cause) {
		super(message, cause);
	}

	public DAOException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
