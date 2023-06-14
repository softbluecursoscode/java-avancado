package br.com.softblue.java.chat.client.business;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import br.com.softblue.java.chat.common.rmi.ClientCallback;

/**
 * @see ClientCallback
 */
public class ClientCallbackImpl extends UnicastRemoteObject implements ClientCallback {

	private static final long serialVersionUID = -3193059165332895L;

	/**
	 * Objeto que vai receber as notificações enviadas pelo servidor
	 */
	private ServerRequestHandler handler;
	
	/**
	 * Objeto ServerInvoker, utilizado para invocar os métodos no objeto remoto 
	 */
	private ServerInvoker serverInvoker;
	
	/**
	 * Construtor
	 * @param handler Objeto que processa as requisições do servidor
	 * @param serverInvoker Objeto ServerInvoker
	 * @throws RemoteException
	 */
	protected ClientCallbackImpl(ServerRequestHandler handler, ServerInvoker serverInvoker) throws RemoteException {
		this.handler = handler;
		this.serverInvoker = serverInvoker;
	}

	/**
	 * @see curso.java.chat.common.ClientCallback#onIncomingMessage(java.lang.String)
	 */
	@Override
	public void onIncomingMessage(String message) throws RemoteException {
		// Avisa o handler que uma mensagem chegou
		handler.onMessageReceived(message);
	}

	/**
	 * @see curso.java.chat.common.ClientCallback#onServerShutdown()
	 */
	@Override
	public void onServerShutdown() throws RemoteException {
		// Avisa o handler que o servidor está saindo fora do ar
		handler.onServerShutdown();
		
		// Muda o estado do ServerInvoker para desconectado
		serverInvoker.setConnected(false);
	}
}
