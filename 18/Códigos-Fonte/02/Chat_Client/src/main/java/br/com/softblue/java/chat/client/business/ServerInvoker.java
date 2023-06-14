package br.com.softblue.java.chat.client.business;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import br.com.softblue.java.chat.common.ChatException;
import br.com.softblue.java.chat.common.ClientInfo;
import br.com.softblue.java.chat.common.DuplicateNameException;
import br.com.softblue.java.chat.common.rmi.ClientCallback;
import br.com.softblue.java.chat.common.rmi.ServerOperations;

/**
 * Classe respons�vel pelo acesso ao servidor, chamando m�todos no objeto remoto.
 */
public class ServerInvoker {
	
	/**
	 * URL de lookup para buscar o objeto remoto
	 */
	private String lookupURL;
	
	/**
	 * Vari�vel que guarda a refer�ncia ao objeto remoto 
	 */
	private ServerOperations serverOperations;
	
	/**
	 * Informa��es do cliente 
	 */
	private ClientInfo clientInfo;
	
	/**
	 * Estado atual (conectado ou desconectado) 
	 */
	private boolean connected = false;

	/**
	 * Construtor.
	 * @param server Nome do servidor
	 * @param port Porta utilizada no lookup via RMI
	 * @param name Nome do cliente
	 * @param handler Objeto que processa o que � recebido pelo servidor
	 * @throws ChatException
	 */
	public ServerInvoker(String server, int port, String name, ServerRequestHandler handler) throws ChatException {
		try {
			// Monta a URL de lookup
			lookupURL = "rmi://" + server + ":" + port + "/" + ServerOperations.SERVER_OBJ_NAME;
			
			// Cria o objeto de callback. Este objeto � usado pelo servidor para enviar dados ao cliente.
			ClientCallbackImpl callbackImpl = new ClientCallbackImpl(handler, this);
			
			/*
			 * Como o cliente e o servidor podem estar em computadores diferentes, fornecer o objeto de callback n�o vai funcionar.
			 * Para que o servidor possa chamar o cliente remotamente, ele precisa de um stub do objeto de callback. O c�digo abaixo
			 * obt�m uma refer�ncia ao stub do objeto de callback. � ele quem deve ser fornecido ao servidor.
			 */
			ClientCallback callback = (ClientCallback) UnicastRemoteObject.toStub(callbackImpl);
			
			// Cria o objeto ClientInfo, com as informa��es do cliente
			clientInfo = new ClientInfo(name, callback);
			
		} catch (RemoteException e) {
			throw new ChatException("Erro ao criar o objeto de callback", e);
		}
	}
	
	/**
	 * Faz a conex�o ao servidor
	 * @throws ChatException
	 */
	public void connect() throws ChatException {
		try {
			// Procura pelo objeto remoto no RMI Registry
			serverOperations = (ServerOperations) Naming.lookup(lookupURL);
			
			// Chama o m�todo de conex�o, passando os dados do cliente
			serverOperations.connect(clientInfo);
			
			// Marca o cliente como conectado
			connected = true;
		} catch (DuplicateNameException e) {
			// Se uma DuplicateNameException foi lan�ada, significa que o servidor j� tem um cliente com o mesmo nome conectado a ele.
			// Neste caso a exce��o � relan�ada para quem chamou o m�todo connect().
			throw e;
		} catch (Exception e) {
			throw new ChatException("Erro ao se conectar ao servidor", e);
		}
	}
	
	/**
	 * Desconecta o cliente do servidor
	 * @throws ChatException
	 */
	public void disconnect() throws ChatException {
		try {
			if (connected) {
				// Realiza a desconex�o
				serverOperations.disconnect(clientInfo);
				
				// Marca o cliente como desconectado
				connected = false;
			}
		} catch (RemoteException e) {
			throw new ChatException("Erro ao se desconectar do servidor", e);
		}
	}
	
	/**
	 * Envia uma mensagem ao servidor, que ser� distribu�da entre outros clientes
	 * @param message Mensagem a ser enviada
	 * @throws ChatException
	 */
	public void sendMessage(String message) throws ChatException {
		try {
			// Envia a mensagem ao servidor
			serverOperations.sendMessage(clientInfo, message);
		} catch (RemoteException e) {
			throw new ChatException("Erro ao se desconectar do servidor", e);
		}
	}
	
	/**
	 * Define se o cliente est� conectado ou desconectado
	 * @param connected Estado do cliente (conectado ou desconectado)
	 */
	public void setConnected(boolean connected) {
		this.connected = connected;
	}
}
