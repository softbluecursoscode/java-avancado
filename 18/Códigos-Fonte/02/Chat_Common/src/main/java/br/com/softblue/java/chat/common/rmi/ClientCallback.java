package br.com.softblue.java.chat.common.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interface de callback. O servidor utiliza objetos de callback para enviar informações aos clientes do chat.
 */
public interface ClientCallback extends Remote {

	/**
	 * Indica que o servidor está enviando uma mensagem ao cliente, que deve ser exibida.
	 * @param message Mensagem enviada pelo servidor
	 * @throws RemoteException
	 */
	public void onIncomingMessage(String message) throws RemoteException;
	
	/**
	 * Indica que o servidor está avisando que está sendo desligado.
	 * @throws RemoteException
	 */
	public void onServerShutdown() throws RemoteException;
}
