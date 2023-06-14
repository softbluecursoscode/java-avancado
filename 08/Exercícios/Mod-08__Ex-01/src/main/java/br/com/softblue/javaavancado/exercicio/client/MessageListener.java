package br.com.softblue.javaavancado.exercicio.client;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Aguarda mensagens vindas do servidor
 */
public class MessageListener extends Thread {

	/**
	 * O reader do socket, usado para ler o que chega do servidor
	 */
	private BufferedReader reader;
	
	/**
	 * Indica se a thread deve parar ou não 
	 */
	private boolean stop;

	/**
	 * Construtor
	 * @param reader
	 */
	public MessageListener(BufferedReader reader) {
		this.reader = reader;
	}

	@Override
	public void run() {
		try {
			while (!stop) {
				/*
				 * Este loop executa enquanto stop == true. Ele simplesmente aguarda uma mensagem vinda do 
				 * servidor. Quando a mensagem chega, ela é impressa no console do cliente.
				 */
				String text = reader.readLine();
				if (text != null) {
					System.out.println(text);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Indica se a thread deve parar ou não
	 * @param stop Booleano indicando se a thread deve parar
	 */
	public void setStop(boolean stop) {
		this.stop = stop;
	}

}
