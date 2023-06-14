package br.com.softblue.java.chat.common;

/**
 * Exceção genérica da aplicação de chat
 */
public class ChatException extends Exception {

	private static final long serialVersionUID = 8215541578320933119L;

	/**
	 * @see Exception#Exception(String, Throwable)
	 * @param message
	 * @param cause
	 */
	public ChatException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @see Exception#Exception(String)
	 * @param message
	 */
	public ChatException(String message) {
		super(message);
	}
}
