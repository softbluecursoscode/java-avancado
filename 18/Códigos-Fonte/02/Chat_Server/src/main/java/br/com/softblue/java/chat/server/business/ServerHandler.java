package br.com.softblue.java.chat.server.business;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import br.com.softblue.java.chat.common.ChatException;
import br.com.softblue.java.chat.common.ClientInfo;
import br.com.softblue.java.chat.common.rmi.ClientCallback;
import br.com.softblue.java.chat.common.rmi.ServerOperations;

/**
 * Respons�vel pela l�gica de funcionamento do servidor. Implementa opera��es como o in�cio e parada do servidor.
 */
public class ServerHandler {
	/**
	 * Arquivo de configura��o onde est�o definidas as propriedades do servidor
	 */
	private static final String CONFIG_FILE = "server_config.txt";
	
	/**
	 * Constante que representa o nome da propriedade que armazena o n�mero da porta do servidor a ser
	 * utilizada, dentro do arquivo de configura��o
	 */
	private static final String PROP_PORT = "port";
	
	/**
	 * Porta padr�o, quando uma n�o est� definida 
	 */
	private static final int DEFAULT_PORT = 1909;

	/**
	 * Refer�ncia ao {@link ServerOperationsImpl}, que � utilizado pelos clientes para invocarem m�todos remotos
	 * no servidor.
	 */
	private ServerOperationsImpl serverOperations;
	
	/**
	 * RMI Registry
	 */
	private Registry registry;
	
	/**
	 * Propriedades lidas do arquivo de configura��o 
	 */
	private Properties props;
	
	/**
	 * Controla se o servidor est� iniciado ou n�o
	 */
	private boolean started;

	/**
	 * Construtor.
	 * @throws ChatException
	 * @throws IOException
	 */
	public ServerHandler() throws ChatException, IOException {
		// L� os dados do arquivo de configura��o
		props = new Properties();
		Path file = Paths.get(CONFIG_FILE);
		
		if (Files.exists(file)) {
			try (InputStream in = Files.newInputStream(file)) {
				props.load(in);
			}
		} else {
			// Se o arquivo n�o existe, define a porta com o valor padr�o
			props.setProperty(PROP_PORT, String.valueOf(DEFAULT_PORT));
		}
		
		try {
			// Inicia o RMI Registry
			registry = LocateRegistry.createRegistry(getServerPort());
		} catch (RemoteException e) {
			throw new ChatException("Erro ao criar o Registry", e);
		}
	}
	
	/**
	 * Inicia o servidor
	 * @return Porta onde o servidor est� aceitando requisi��es
	 * @throws ChatException
	 */
	public int startServer() throws ChatException {
		try {
			// Cria o objeto remoto
			serverOperations = new ServerOperationsImpl();
			
			// Registra o objeto no RMI Registry
			registry.bind(ServerOperations.SERVER_OBJ_NAME, serverOperations);
			
			// Marca o servidor como iniciado
			started = true;
			
			// Retorna a porta do servidor
			return getServerPort();
			
		} catch (Exception e) {
			throw new ChatException("Erro ao iniciar o servidor", e);
		}
	}
	
	/**
	 * Para o servidor
	 * @throws ChatException
	 */
	public void stopServer() throws ChatException {
		try {
			// Se o servidor n�o est� mais executando, n�o faz nada
			if (!started) {
				return;
			}
			
			// Obt�m o conjunto de clientes conectados ao servidor
			Set<ClientInfo> clients = serverOperations.getClients();
			
			if (clients.size() > 0) {
				// Cria um pool de threads
				ExecutorService execService = Executors.newFixedThreadPool(clients.size());
				
				try {
					// Itera sobre cada cliente
					clients.forEach(clientInfo -> {
						// Obt�m o objeto de callback, que permite se comunicar com o cliente
						ClientCallback callback = clientInfo.getCallback();
						
						// Avisa o cliente utilizando uma nova thread. Isto evita que o servidor tenha que ficar
						// esperando que o cliente processe esta informa��o
						execService.execute(new FutureTask<>(() -> { 
							callback.onServerShutdown();
							return null;
						}));
					});
					
					// Remove todos os clientes da lista de clientes
					clients.clear();
					
				} finally {
					// Finaliza o pool de threads
					execService.shutdown();
				}
			}
			
			// Remove o objeto remoto do RMI Registry
			registry.unbind(ServerOperations.SERVER_OBJ_NAME);
			
			// Marca o servidor como parado
			started = false;
			
		} catch (Exception e) {
			throw new ChatException("Erro ao parar o servidor", e);
		}
	}
	
	/**
	 * Obt�m a porta do servidor
	 * @return
	 * @throws ChatException
	 */
	private int getServerPort() throws ChatException {
		// L� a porta a partir do objeto Properties
		String portStr = props.getProperty(PROP_PORT);
		
		// A porta deve ter sido definida
		if (portStr == null) {
			throw new ChatException("Porta do servidor n�o definida");
		}
		
		try {
			// A porta deve poder ser convertida para um n�mero inteiro
			int port = Integer.parseInt(portStr);
			
			// A porta deve estar num intervalo entre 1 e 65635
			if (port < 1 || port > 65635) {
				throw new ChatException("A porta n�o est� em um intervalo v�lido");
			}
			
			// Retorna a porta lida
			return port;
		} catch (NumberFormatException e) {
			throw new ChatException("A porta n�o � um n�mero v�lido");
		}
	}
}
