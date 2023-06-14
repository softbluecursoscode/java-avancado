package br.com.softblue.java.chat.server.business;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.locks.ReentrantLock;

import br.com.softblue.java.chat.common.ClientInfo;
import br.com.softblue.java.chat.common.DuplicateNameException;
import br.com.softblue.java.chat.common.rmi.ClientCallback;
import br.com.softblue.java.chat.common.rmi.ServerOperations;

/**
 * @see ServerOperations
 */
@SuppressWarnings("exports")
public class ServerOperationsImpl extends UnicastRemoteObject implements ServerOperations {
	private static final long serialVersionUID = 3183188142066459693L;

	/**
	 * Objeto que formata a hora
	 */
	private static final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

	/**
	 * Conjunto de clientes conectados ao servidor 
	 */
	private Set<ClientInfo> clients = new HashSet<>();
	
	/**
	 * Lock para evitar acesso concorrente à lista de clientes
	 */
	private ReentrantLock lock = new ReentrantLock();

	/**
	 * Construtor
	 * @throws RemoteException
	 */
	protected ServerOperationsImpl() throws RemoteException {
	}

	/**
	 * @see curso.java.chat.common.ServerOperations#connect(curso.java.chat.common.ClientInfo)
	 */
	@Override
	public void connect(ClientInfo clientInfo) throws RemoteException, DuplicateNameException {
		// Adiciona o cliente no conjunto de clientes
		boolean added;
		lock.lock();
		try {
			added = clients.add(clientInfo);
		} finally {
			lock.unlock();
		}
		
		// Se ele não pode ser adicionado, é porque já existe outro cliente com o mesmo nome.
		// Neste caso, lança uma DuplicateNameException.
		if (!added) {
			throw new DuplicateNameException("O nome " + clientInfo.getName() + " já está existe no chat");
		}
		
		// Cria a mensagem a ser enviada para os clientes
		String message = clientInfo.getName() + " entrou no chat";
		
		// Formata a mensagem gerada pelo servidor
		String formattedMessage = formatMessageFromServerToClients(message);
		
		// Envia a mensagem para todos
		broadcastMessage(formattedMessage);
	}

	/**
	 * @see curso.java.chat.common.ServerOperations#disconnect(curso.java.chat.common.ClientInfo)
	 */
	@Override
	public void disconnect(ClientInfo clientInfo) throws RemoteException {
		// Remove o cliente do conjunto de clientes
		lock.lock();
		try {
			clients.remove(clientInfo);
		} finally {
			lock.unlock();
		}
		
		// Cria a mensagem a ser enviada para os clientes
		String message = clientInfo.getName() + " saiu do chat";
		
		// Formata a mensagem gerada pelo servidor
		String formattedMessage = formatMessageFromServerToClients(message);
		
		// Envia a mensagem para todos
		broadcastMessage(formattedMessage);
	}
	
	/**
	 * @see curso.java.chat.common.ServerOperations#sendMessage(curso.java.chat.common.ClientInfo, java.lang.String)
	 */
	@Override
	public void sendMessage(ClientInfo clientInfo, String message) throws RemoteException {
		// Formata a mensagem vinda do cliente
		String formattedMessage = formatMessageFromClientToClients(message, clientInfo.getName());
		
		// Envia a mensagem para todos
		broadcastMessage(formattedMessage);
	}
	
	/**
	 * Formata uma mensagem criada pelo servidor e que deve ser enviada aos clientes. Este formato adiciona a 
	 * hora à mensagem.
	 * @param message Mensagem a ser formatada.
	 * @return Mensagem formatada.
	 */
	private String formatMessageFromServerToClients(String message) {
		String formattedMessage = String.format("(%s) %s", sdf.format(new Date()), message);
		return formattedMessage;
	}
	
	/**
	 * Formata uma mensagem recebida por um cliente e que deve ser enviada aos clientes. Este formato adiciona a 
	 * hora e o nome do cliente à mensagem.
	 * @param message Mensagem a ser formatada.
	 * @param clientName Nome do cliente que enviou a mensagem.
	 * @return Mensagem formatada.
	 */
	private String formatMessageFromClientToClients(String message, String clientName) {
		String formattedMessage = String.format("(%s) [%s] %s", sdf.format(new Date()), clientName, message);
		return formattedMessage;
	}
	
	/**
	 * Distribui uma mensagem já formatada para todos os clientes.
	 * @param message Mensagem a ser distribuída. É declarado como final porque senão não poderia ser acessado
	 * de dentro do código da thread
	 */
	private void broadcastMessage(String message) {
		lock.lock();
		
		try {
			if (clients.size() > 0) {
				// Cria um pool de threads
				ExecutorService execService = Executors.newFixedThreadPool(clients.size());	

				try {
					// Itera sobre cada cliente
					clients.forEach(clientInfo -> {
						// Obtém o objeto de callback do cliente
						final ClientCallback callback = clientInfo.getCallback();
						
						// Avisa o cliente utilizando uma nova thread. Isto evita que o servidor tenha que ficar
						// esperando a entrega da mensagem para cada cliente, para continuar enviando para o 
						// próximo
						execService.execute(new FutureTask<>(() -> { 
							// Envia a mensagem para o cliente
							callback.onIncomingMessage(message);
							return null;
						}));
					});
				} finally {
					// Finaliza o pool de threads
					execService.shutdown();
				}
			}
		
		} finally {
			lock.unlock();
		}
	}

	/**
	 * Obtém o conjunto de clientes conectados ao servidor
	 * @return Conjunto de clientes
	 */
	public Set<ClientInfo> getClients() {
		lock.lock();
		try {
			return clients;
		} finally {
			lock.unlock();
		}
	}
}
