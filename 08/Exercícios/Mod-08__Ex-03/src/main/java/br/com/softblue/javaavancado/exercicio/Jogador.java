package br.com.softblue.javaavancado.exercicio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

/**
 * Representa um jogador do jogo da velha
 */
public class Jogador {
	/**
	 * Nome do jogador.
	 */
	private String nome;
	
	/**
	 * S�mbolo usado pelo jogador. Ex: 'X' ou 'O' 
	 */
	private char simbolo;
	
	/**
	 * Canal de entrada de dados
	 */
	private BufferedReader in;
	
	/**
	 * Canal de sa�da de dados
	 */
	private PrintStream out;
	
	/**
	 * Construtor
	 * @param nome Nome
	 * @param simbolo S�mbolo
	 * @param in Canal de entrada
	 * @param out Canal de sa�da
	 */
	public Jogador(String nome, char simbolo, BufferedReader in, PrintStream out) {
		this.nome = nome;
		this.simbolo = simbolo;
		this.in = in;
		this.out = out;
	}

	
	/**
	 * Obt�m o nome do jogador
	 * @return Nome do jogador
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Obt�m o s�mbolo do jogador
	 * @return S�mbolo do jogador
	 */
	public char getSimbolo() {
		return simbolo;
	}
	
	/**
	 * Fecha a conex�o
	 * @throws IOException
	 */
	public void closeConnection() throws IOException {
		in.close();
		out.close();
	}
	
	/**
	 * L� a jogada do jogador do teclado. Deve ser feita no formato 'i,j'.
	 * @return Jogada realizada
	 * @throws JogadaInvalidaException Lan�ada se a jogada for inv�lida.
	 */
	public Jogada obterJogada() throws IOException, JogadaInvalidaException {
		/* Avisa o jogador que ele deve jogar */
		out.println("play");
		
		/* Aguarda o movimento do jogador */
		String jogada = in.readLine();

		return new Jogada(jogada);
	}

	/**
	 * Obt�m o canal de leitura de dados
	 * @return Canal de leitura de dados
	 */
	public BufferedReader getIn() {
		return in;
	}

	/**
	 * Obt�m o canal de escrita de dados
	 * @return Canal de escrita de dados
	 */
	public PrintStream getOut() {
		return out;
	}

	/**
	 * Envia uma mensagem
	 * @param msg Mensagem a ser enviada
	 */
	public void send(String msg) {
		out.println(msg);
	}
}
