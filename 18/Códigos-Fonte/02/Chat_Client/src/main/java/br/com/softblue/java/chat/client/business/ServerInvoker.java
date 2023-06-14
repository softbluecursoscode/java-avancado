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
 * Classe responsável pelo acesso ao servidor, chamando métodos no objeto remoto.
 */
public class ServerInvoker {
	
	/**
	 * URL de lookup para buscar o objeto remoto
	 */
	private String lookupURL;
	
	/**
	 * Variável que guarda a referência ao objeto remoto 
	 */
	private ServerOperations serverOperations;
	
	/**
	 * Informações do cliente 
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
	 * @param handler Objeto que processa o que é recebido pelo servidor
	 * @throws ChatException
	 */
	public ServerInvoker(String server, int port, String name, ServerRequestHandler handler) throws ChatException {
		try {
			// Monta a URL de lookup
			lookupURL = "rmi://" + server + ":" + port + "/" + ServerOperations.SERVER_OBJ_NAME;
			
			// Cria o objeto de callback. Este objeto é usado pelo servidor para enviar dados ao cliente.
			ClientCallbackImpl callbackImpl = new ClientCallbackImpl(handler, this);
			
			/*
			 * Como o cliente e o servidor podem estar em computadores diferentes, fornecer o objeto de callback não vai funcionar.
			 * Para que o servidor possa chamar o cliente remotamente, ele precisa de um stub do objeto de callback. O código abaixo
			 * obtêm uma referência ao stub do objeto de callback. É ele quem deve ser fornecido ao servidor.
			 */
			ClientCallback callback = (ClientCallback) UnicastRemoteObject.toStub(callbackImpl);
			
			// Cria o objeto ClientInfo, com as informações do cliente
			clientInfo = new ClientInfo(name, callback);
			
		} catch (RemoteException e) {
			throw new ChatException("Erro ao criar o objeto de callback", e);
		}
	}
	
	/**
	 * Faz a conexão ao servidor
	 * @throws ChatException
	 */
	public void connect() throws ChatException {
		try {
			// Procura pelo objeto remoto no RMI Registry
			serverOperations = (ServerOperations) Naming.lookup(lookupURL);
			
			// Chama o método de conexão, passando os dados do cliente
			serverOperations.connect(clientInfo);
			
			// Marca o cliente como conectado
			connected = true;
		} catch (DuplicateNameException e) {
			// Se uma DuplicateNameException foi lançada, significa que o servidor já tem um cliente com o mesmo nome conectado a ele.
			// Neste caso a exceção é relançada para quem chamou o método connect().
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
				// Realiza a desconexão
				serverOperations.disconnect(clientInfo);
				
				// Marca o cliente como desconectado
				connected = false;
			}
		} catch (RemoteException e) {
			throw new ChatException("Erro ao se desconectar do servidor", e);
		}
	}
	
	/**
	 * Envia uma mensagem ao servidor, que será distribuída entre outros clientes
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
	 * Define se o cliente está conectado ou desconectado
	 * @param connected Estado do cliente (conectado ou desconectado)
	 */
	public void setConnected(boolean connected) {
		this.connected = connected;
	}
}
