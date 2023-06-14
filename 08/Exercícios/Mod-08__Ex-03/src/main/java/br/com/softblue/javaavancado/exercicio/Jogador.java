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
	 * Símbolo usado pelo jogador. Ex: 'X' ou 'O' 
	 */
	private char simbolo;
	
	/**
	 * Canal de entrada de dados
	 */
	private BufferedReader in;
	
	/**
	 * Canal de saída de dados
	 */
	private PrintStream out;
	
	/**
	 * Construtor
	 * @param nome Nome
	 * @param simbolo Símbolo
	 * @param in Canal de entrada
	 * @param out Canal de saída
	 */
	public Jogador(String nome, char simbolo, BufferedReader in, PrintStream out) {
		this.nome = nome;
		this.simbolo = simbolo;
		this.in = in;
		this.out = out;
	}

	
	/**
	 * Obtém o nome do jogador
	 * @return Nome do jogador
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Obtém o símbolo do jogador
	 * @return Símbolo do jogador
	 */
	public char getSimbolo() {
		return simbolo;
	}
	
	/**
	 * Fecha a conexão
	 * @throws IOException
	 */
	public void closeConnection() throws IOException {
		in.close();
		out.close();
	}
	
	/**
	 * Lê a jogada do jogador do teclado. Deve ser feita no formato 'i,j'.
	 * @return Jogada realizada
	 * @throws JogadaInvalidaException Lançada se a jogada for inválida.
	 */
	public Jogada obterJogada() throws IOException, JogadaInvalidaException {
		/* Avisa o jogador que ele deve jogar */
		out.println("play");
		
		/* Aguarda o movimento do jogador */
		String jogada = in.readLine();

		return new Jogada(jogada);
	}

	/**
	 * Obtém o canal de leitura de dados
	 * @return Canal de leitura de dados
	 */
	public BufferedReader getIn() {
		return in;
	}

	/**
	 * Obtém o canal de escrita de dados
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
