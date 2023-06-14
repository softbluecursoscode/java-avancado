package br.com.softblue.java.chat.common.rmi;
import java.rmi.Remote;
import java.rmi.RemoteException;

import br.com.softblue.java.chat.common.ClientInfo;
import br.com.softblue.java.chat.common.DuplicateNameException;

/**
 * Interface utilizada pelos clientes para se comunicarem com o servidor.
 */
public interface ServerOperations extends Remote {
	
	/**
	 * Constante que indica o nome do objeto remoto do servidor registrado no RMI Registry.
	 */
	public static final String SERVER_OBJ_NAME = "chatServer";

	/**
	 * Faz a conex�o de um cliente no servidor.
	 * @param clientInfo Informa��es do cliente que est� conectando.
	 * @throws RemoteException
	 * @throws DuplicateNameException Lan�ada se um cliente com o mesmo nome j� estiver conectado ao chat.
	 */
	public void connect(ClientInfo clientInfo) throws RemoteException, DuplicateNameException;
	
	/**
	 * Disconecta o cliente do servidor.
	 * @param clientInfo Informa��es do cliente que est� desconectando.
	 * @throws RemoteException
	 */
	public void disconnect(ClientInfo clientInfo) throws RemoteException;
	
	/**
	 * Envia uma mensagem ao servidor, para ser distribu�da para todos os clientes.
	 * @param clientInfo Informa��es do cliente que est� enviando a mensagem.
	 * @param message Mensagem a ser distribu�da.
	 * @throws RemoteException
	 */
	public void sendMessage(ClientInfo clientInfo, String message) throws RemoteException;
}
