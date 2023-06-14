package br.com.softblue.javaavancado.exercicio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class Server {

	private int port;
	private List<Jogador> jogadores = new ArrayList<Jogador>();
	private Tabuleiro tabuleiro = new Tabuleiro();
	
	public Server(int port) {
		this.port = port;
	}
	
	public void iniciar() throws IOException {
		
		/* Abre o servidor e aguarda as conexões */
		ServerSocket serverSocket = new ServerSocket(port);
		System.out.println("Servidor aberto na porta " + port + ". Aguardando os jogadores...");
		
		int numJogadores = 0;
		char simbolo = 'X';
		
		while(numJogadores < 2) {
			Socket clientSocket = serverSocket.accept();
			
			/* Obtém as streams de entrada e saída */
			BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			PrintStream out = new PrintStream(clientSocket.getOutputStream());
			
			/* Recebe o nome do primeiro jogador que conectou */
			String nome = in.readLine();
			
			System.out.println("Jogador '" + nome + "' conectado!");
			jogadores.add(new Jogador(nome, simbolo, in, out));
			
			simbolo = 'O';
			numJogadores++;
		}
		
		/* Como os jogadores já se conectaram, avisa os jogadores que a partida será iniciada */
		sendAll("start");
		
		boolean finalizado = false;
		int indexJogadorAtual = 0;
		Jogador vencedor = null;

		while (!finalizado) {
			/* Envia o estado do tabuleiro aos jogadores */
			String matriz = tabuleiro.getMatrizAsString();
			sendAll("matriz " + matriz);
			
			/* Obtém o jogador atual */
			Jogador jogador = jogadores.get(indexJogadorAtual);

			/* Efetua uma jogada */
			boolean sequenciaEncontrada = false;
			boolean jogadaValida = false;
			
			while(!jogadaValida) {
				try {
					Jogada jogada = jogador.obterJogada();
					sequenciaEncontrada = tabuleiro.efetuarJogada(jogada, jogador.getSimbolo());
					jogadaValida = true;
				} catch (JogadaInvalidaException e) {
					jogador.send("error " + e.getMessage());
				}
			}

			if (sequenciaEncontrada) {
				vencedor = jogador;
				finalizado = true;
			} else if (tabuleiro.isCompleto()) {
				finalizado = true;
			}

			/* Muda para o próximo jogador */
			indexJogadorAtual = (indexJogadorAtual + 1) % jogadores.size();
		}
		
		/* O jogo acabou */
		if (vencedor == null) {
			/* O jogo terminou empatado. Avisar os jogadores */
			sendAll("draw");
		} else {
			/* O jogo teve um vencedor. Avisar os jogadores */
			sendAll("win " + vencedor.getNome());
		}
		
		System.out.println("A partida terminou!");
		
		/* Fechar as conexões */
		for(Jogador jogador : jogadores) {
			jogador.closeConnection();
		}
		
		serverSocket.close();
	}
	
	private void sendAll(String msg) {
		for(Jogador jogador : jogadores) {
			jogador.send(msg);
		}
	}
	
	public static void main(String[] args) throws Exception {
		if(args.length != 1) {
			System.out.println("Argumento: <porta>\n");
			return;
		}
		
		Server server = new Server(Integer.parseInt(args[0]));
		server.iniciar();
	}
}
