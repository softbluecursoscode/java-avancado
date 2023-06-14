package br.com.softblue.java.chat.client.business;

/**
 * Interface utilizada para receber notificações vindas do servidor
 */
public interface ServerRequestHandler {

	/**
	 * O servidor está sendo finalizado
	 */
	void onServerShutdown();
	
	/**
	 * Uma mensagem do servidor chegou
	 * @param message Mensagem a ser exibida
	 */
	void onMessageReceived(String message);
}
