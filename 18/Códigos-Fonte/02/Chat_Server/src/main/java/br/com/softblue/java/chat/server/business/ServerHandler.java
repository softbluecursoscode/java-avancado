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
 * Responsável pela lógica de funcionamento do servidor. Implementa operações como o início e parada do servidor.
 */
public class ServerHandler {
	/**
	 * Arquivo de configuração onde estão definidas as propriedades do servidor
	 */
	private static final String CONFIG_FILE = "server_config.txt";
	
	/**
	 * Constante que representa o nome da propriedade que armazena o número da porta do servidor a ser
	 * utilizada, dentro do arquivo de configuração
	 */
	private static final String PROP_PORT = "port";
	
	/**
	 * Porta padrão, quando uma não está definida 
	 */
	private static final int DEFAULT_PORT = 1909;

	/**
	 * Referência ao {@link ServerOperationsImpl}, que é utilizado pelos clientes para invocarem métodos remotos
	 * no servidor.
	 */
	private ServerOperationsImpl serverOperations;
	
	/**
	 * RMI Registry
	 */
	private Registry registry;
	
	/**
	 * Propriedades lidas do arquivo de configuração 
	 */
	private Properties props;
	
	/**
	 * Controla se o servidor está iniciado ou não
	 */
	private boolean started;

	/**
	 * Construtor.
	 * @throws ChatException
	 * @throws IOException
	 */
	public ServerHandler() throws ChatException, IOException {
		// Lê os dados do arquivo de configuração
		props = new Properties();
		Path file = Paths.get(CONFIG_FILE);
		
		if (Files.exists(file)) {
			try (InputStream in = Files.newInputStream(file)) {
				props.load(in);
			}
		} else {
			// Se o arquivo não existe, define a porta com o valor padrão
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
	 * @return Porta onde o servidor está aceitando requisições
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
			// Se o servidor não está mais executando, não faz nada
			if (!started) {
				return;
			}
			
			// Obtém o conjunto de clientes conectados ao servidor
			Set<ClientInfo> clients = serverOperations.getClients();
			
			if (clients.size() > 0) {
				// Cria um pool de threads
				ExecutorService execService = Executors.newFixedThreadPool(clients.size());
				
				try {
					// Itera sobre cada cliente
					clients.forEach(clientInfo -> {
						// Obtém o objeto de callback, que permite se comunicar com o cliente
						ClientCallback callback = clientInfo.getCallback();
						
						// Avisa o cliente utilizando uma nova thread. Isto evita que o servidor tenha que ficar
						// esperando que o cliente processe esta informação
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
	 * Obtém a porta do servidor
	 * @return
	 * @throws ChatException
	 */
	private int getServerPort() throws ChatException {
		// Lê a porta a partir do objeto Properties
		String portStr = props.getProperty(PROP_PORT);
		
		// A porta deve ter sido definida
		if (portStr == null) {
			throw new ChatException("Porta do servidor não definida");
		}
		
		try {
			// A porta deve poder ser convertida para um número inteiro
			int port = Integer.parseInt(portStr);
			
			// A porta deve estar num intervalo entre 1 e 65635
			if (port < 1 || port > 65635) {
				throw new ChatException("A porta não está em um intervalo válido");
			}
			
			// Retorna a porta lida
			return port;
		} catch (NumberFormatException e) {
			throw new ChatException("A porta não é um número válido");
		}
	}
}
