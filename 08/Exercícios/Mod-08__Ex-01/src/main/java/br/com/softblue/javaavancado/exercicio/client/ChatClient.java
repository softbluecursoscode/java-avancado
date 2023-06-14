package br.com.softblue.javaavancado.exercicio.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

import br.com.softblue.javaavancado.exercicio.util.Console;



/**
 * Representa um cliente do chat
 */
public class ChatClient {

	/**
	 * Inicia o cliente do chat
	 * @param server 
	 * @param port
	 * @throws IOException
	 */
	@SuppressWarnings("resource")
	public void start(String server, int port) throws IOException {
		
		System.out.print("Digite o seu apelido: ");
		String nick = Console.readString();
		
		System.out.println("Conectando no servidor " + server + ":" + port + "...");
		Socket socket = new Socket(server, port);
		
		//cria os objetos reader (leitura) e write (escrita) com base na input stream e output stream
		BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		PrintStream writer = new PrintStream(socket.getOutputStream());
		
		//envia para o servidor o texto 'connect' junto com o apelido deste cliente
		writer.println("connect " + nick);
		
		//aguarda o OK do servidor
		String response = reader.readLine();
		
		if(response.equals("OK")) {
			//se o servidor respondeu 'OK', o cliente já pode começar a enviar as mensagens
			System.out.println("O servidor autorizou a conexao. Cliente conectado!");
			
			/*
			 * O objeto MessageListener é uma thread que fica aguardando mensagens recebidas do servidor.
			 * Perceba que a thread principal possui o objetivo de ler uma mensagem do console e enviá-la
			 * ao servidor. Enquanto isso, ao mesmo tempo, o listener tem o objetivo de receber mensagens
			 * enviadas pelo servidor. Usando esta thread é possível fazer as duas coisas ao mesmo tempo.
			 */
			MessageListener listener = new MessageListener(reader);
			listener.start();
			
			while(true) {
				//lê uma mensagem do console
				String message = Console.readString();
				
				//envia esta mensagem ao servidor
				writer.println(message);
				
				//caso a mensagem '[q]' tenha sido enviada, termina o cliente
				if(message.equalsIgnoreCase("[q]")) {
					listener.setStop(true);
					break;
				}
			}
		} else {
			System.out.println("O servidor respondeu: " + response + ". O cliente nao pode continuar");
		}
	}

	public static void main(String[] args) throws Exception {
		//instancia o cliente do chat
		ChatClient server = new ChatClient();
		
		//inicia o cliente, sendo que o servidor e a porta são passados como argumentos de linha de comando
		server.start(args[0], Integer.parseInt(args[1]));
	}
}
