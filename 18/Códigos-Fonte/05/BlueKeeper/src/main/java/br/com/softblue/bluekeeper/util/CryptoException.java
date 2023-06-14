package br.com.softblue.bluekeeper.util;

/**
 * Exceção de criptografia
 */
public class CryptoException extends RuntimeException {

	private static final long serialVersionUID = -7793449660311398355L;

	public CryptoException() {
	}

	public CryptoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public CryptoException(String message, Throwable cause) {
		super(message, cause);
	}

	public CryptoException(String message) {
		super(message);
	}

	public CryptoException(Throwable cause) {
		super(cause);
	}
}
