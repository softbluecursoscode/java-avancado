package br.com.softblue.javaavancado.exercicio.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * Servidor do chat
 */
public class ChatServer {

	/**
	 * Inicia a execução do servidor do chat
	 * @param port Porta onde o servidor ficará ouvindo
	 * @throws IOException
	 */
	@SuppressWarnings("resource")
	public void start(int port) throws IOException {
		
		ServerSocket serverSocket = new ServerSocket(port);
		
		System.out.println("Servidor de Chat aberto na porta " + port);
		System.out.println("Aguardando requisicoes...");
		
		while(true) {
			//o accept() bloqueia até que uma requisição de um cliente seja recebida
			Socket clientSocket = serverSocket.accept();
		
			/*
			 * Ao receber uma requisição, ela é passada para um objeto da classe ClientHandler. Este objeto
			 * é na verdade uma thread. A programação é feita desta forma para permitir que múltiplas 
			 * conexões simultâneas ao servidor sejam tratadas: enquando o objeto 'processor' processa a 
			 * requisição, o servidor pode voltar para o accept rapidamente, aguardando a próxima requisição.
			 */
			ClientHandler processor = new ClientHandler(clientSocket);
			processor.start();
		}
	}

	/**
	 * Método main().
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		//instancia o servidor e o inicia na porta 5000
		ChatServer server = new ChatServer();
		server.start(5000);
	}
}
