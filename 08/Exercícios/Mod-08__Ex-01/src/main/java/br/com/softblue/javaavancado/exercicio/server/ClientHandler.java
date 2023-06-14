package br.com.softblue.javaavancado.exercicio.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Trata as requisi��es de clientes feitas ao servidor
 */
public class ClientHandler extends Thread {
	
	/**
	 * Lista de clientes conectados ao chat.
	 * Esta lista precisa ser armazenada para que os clientes sejam notificados quando uma mensagem for
	 * enviada. Esta lista � �nica para todos os objetos ClientHandler, uma vez que este atributo � est�tico.
	 */
	private static List<ClientHandler> clients = new ArrayList<ClientHandler>();

	/**
	 * Socket aberto com o cliente
	 */
	private Socket clientSocket;
	
	/**
	 * Usado para ler os dados enviados pelo cliente
	 */
	private BufferedReader reader;
	
	/**
	 * Usado para enviar dados ao cliente
	 */
	private PrintStream writer;
	
	/**
	 * Apelido do cliente
	 */
	private String nick;
	
	/**
	 * Formatador de hora
	 */
	private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

	/**
	 * Construtor
	 * @param clientSocket Socket de conex�o com o cliente
	 * @throws IOException
	 */
	public ClientHandler(Socket clientSocket) throws IOException {
		this.clientSocket = clientSocket;
		
		/*
		 * Usando a input stream e output stream do socket, os objetos BufferedReader (leitura) e 
		 * PrintStream (escrita) s�o criados
		 */
		reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		writer = new PrintStream(clientSocket.getOutputStream());
	}

	/**
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		try {
			System.out.println("Cliente tentando se conectar...");

			//o servidor l� a primeira mensagem enviada pelo cliente
			String message = reader.readLine();

			if (message.startsWith("connect")) {
				//se a mensagem come�ar com 'connect', o apelido est� localizado na sequ�ncia
				nick = message.substring(message.indexOf(" ") + 1);

				//depois de ler o apelido, o servidor envia um 'OK' para o cliente
				writer.println("OK");

				System.out.println("Cliente " + nick + " conectado!");

				//todos os usu�rios conectados no chat recebem a mensagem de que um novo cliente conectou
				sendAll("[" + nick + " entrou no chat!]");

				//este cliente � adicionado � lista de clientes
				addClient();

				/*
				 * As pr�ximas mensagens vindas do cliente s�o as mensagens que ser�o distribu�das a todos
				 * os usu�rios do chat. No momento em que o cliente enviar o texto '[q]', ele ser� desconectado
				 * do chat
				 */
				while (true) {
					String text = reader.readLine();

					//se o texto for '[q]', sai do chat
					if (text.equalsIgnoreCase("[q]")) {
						break;
					}

					//processa a mensagem
					text = processMessage(text);
					
					//envia a mensagem procesada a todos os clientes
					sendAll(text);
				}
			}

		} catch (IOException e) {
			System.out.println("O cliente " + nick + "desconectou do chat");
		} finally {
			/*
			 * Se ocorrer alguma exce��o ou se o cliente desejou sair do chat, todos os usu�rios conectados
			 * ser�o avisados que o cliente saiu e ele ser� removido da lista de clientes. Seu socket ser�
			 * fechado tamb�m.
			 */
			sendAll("[" + nick + " saiu do chat!]");
			removeClient();
			
			try {
				clientSocket.close();
			} catch (IOException e) {
			}
		}
	}

	/**
	 * Processa a mensagem, o que significa por o hor�rio e o nick do usu�rio
	 * @param message Mensagem a ser processada
	 * @return Mensagem processada
	 */
	private String processMessage(String message) {
		String time = sdf.format(new Date());

		return "[" + time + " " + nick + "] " + message;
	}

	/**
	 * Envia a mensagem a todos os usu�rios conectados no chat.
	 * @param message Mensagem a ser enviada
	 */
	public static void sendAll(String message) {
		/*
		 * Este c�digo itera sobre os clientes conectados e envia a eles a mensagem. Este bloco � sincronizado
		 * porque o atributo 'clients' � compartilhado entre diversas threads.
		 * Todos os c�digos que acessam o atributo 'clients' s�o sincronizados pelo pr�prio atributo como
		 * monitor. Isto garante que o atributo seja acessado apenas por uma thread por vez, evitando 
		 * situa��es como o envio de mensagens a clientes que j� sa�ram do chat ou at� adicionar um cliente
		 * enquanto mensagens s�o enviadas a outros clientes.
		 */
		synchronized (clients) {
			for (ClientHandler client : clients) {
				client.writer.println(message);
			}
		}
	}
	
	/**
	 * Adiciona um cliente ao chat.
	 */
	private void addClient() {
		synchronized (clients) {
			clients.add(this);
		}
	}
	
	/**
	 * Remove um cliente do chat
	 */
	private void removeClient() {
		synchronized (clients) {
			clients.remove(this);
		}
	}
}
