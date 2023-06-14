package br.com.softblue.java.chat.common;

/**
 * Exce��o que representa um nome duplicado (isto �, que j� existe no chat)
 */
public class DuplicateNameException extends ChatException {
	
	private static final long serialVersionUID = -1242126712090813719L;

	/**
	 * @see Exception#Exception(String)
	 * @param message
	 */
	public DuplicateNameException(String message) {
		super(message);
	}
}
