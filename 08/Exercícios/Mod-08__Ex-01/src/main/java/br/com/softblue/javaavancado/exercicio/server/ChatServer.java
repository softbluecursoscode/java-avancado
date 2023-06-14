package br.com.softblue.javaavancado.exercicio.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * Servidor do chat
 */
public class ChatServer {

	/**
	 * Inicia a execu��o do servidor do chat
	 * @param port Porta onde o servidor ficar� ouvindo
	 * @throws IOException
	 */
	@SuppressWarnings("resource")
	public void start(int port) throws IOException {
		
		ServerSocket serverSocket = new ServerSocket(port);
		
		System.out.println("Servidor de Chat aberto na porta " + port);
		System.out.println("Aguardando requisicoes...");
		
		while(true) {
			//o accept() bloqueia at� que uma requisi��o de um cliente seja recebida
			Socket clientSocket = serverSocket.accept();
		
			/*
			 * Ao receber uma requisi��o, ela � passada para um objeto da classe ClientHandler. Este objeto
			 * � na verdade uma thread. A programa��o � feita desta forma para permitir que m�ltiplas 
			 * conex�es simult�neas ao servidor sejam tratadas: enquando o objeto 'processor' processa a 
			 * requisi��o, o servidor pode voltar para o accept rapidamente, aguardando a pr�xima requisi��o.
			 */
			ClientHandler processor = new ClientHandler(clientSocket);
			processor.start();
		}
	}

	/**
	 * M�todo main().
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		//instancia o servidor e o inicia na porta 5000
		ChatServer server = new ChatServer();
		server.start(5000);
	}
}
