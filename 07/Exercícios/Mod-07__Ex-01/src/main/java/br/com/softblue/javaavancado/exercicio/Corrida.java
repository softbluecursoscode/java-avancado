package br.com.softblue.javaavancado.exercicio;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Classe que representa a corrida. Possui o método main() que inicia o programa.
 */
public class Corrida {

	/**
	 * Quantidade de sapos que participarão da corrida
	 */
	private static final int QTDE_SAPOS = 5;
	
	/**
	 * Distância total a ser percorrida por cada sapo
	 */
	private static final int DISTANCIA_TOTAL = 500;
	
	/**
	 * Método main()
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Cria um pool de threads, onde cada uma representará um sapo
		ExecutorService executor = Executors.newFixedThreadPool(QTDE_SAPOS);
		
		//Inicia a execução de cada thread (sapo)
		for (int i = 0; i < QTDE_SAPOS; i++) {
			executor.execute(new Sapo("Sapo_" + (i + 1), DISTANCIA_TOTAL));
		}
		
		//Finaliza o pool. O método shutdown() não interrompe as threads que já estão executando
		executor.shutdown();
	}
}
