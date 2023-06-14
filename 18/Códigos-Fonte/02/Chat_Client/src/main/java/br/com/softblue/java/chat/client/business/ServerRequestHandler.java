package br.com.softblue.java.chat.client.business;

/**
 * Interface utilizada para receber notifica��es vindas do servidor
 */
public interface ServerRequestHandler {

	/**
	 * O servidor est� sendo finalizado
	 */
	void onServerShutdown();
	
	/**
	 * Uma mensagem do servidor chegou
	 * @param message Mensagem a ser exibida
	 */
	void onMessageReceived(String message);
}
