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
	 * Faz a conexão de um cliente no servidor.
	 * @param clientInfo Informações do cliente que está conectando.
	 * @throws RemoteException
	 * @throws DuplicateNameException Lançada se um cliente com o mesmo nome já estiver conectado ao chat.
	 */
	public void connect(ClientInfo clientInfo) throws RemoteException, DuplicateNameException;
	
	/**
	 * Disconecta o cliente do servidor.
	 * @param clientInfo Informações do cliente que está desconectando.
	 * @throws RemoteException
	 */
	public void disconnect(ClientInfo clientInfo) throws RemoteException;
	
	/**
	 * Envia uma mensagem ao servidor, para ser distribuída para todos os clientes.
	 * @param clientInfo Informações do cliente que está enviando a mensagem.
	 * @param message Mensagem a ser distribuída.
	 * @throws RemoteException
	 */
	public void sendMessage(ClientInfo clientInfo, String message) throws RemoteException;
}
