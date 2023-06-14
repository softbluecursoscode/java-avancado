package br.com.softblue.javaavancado.exercicio;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Classe que representa a corrida. Possui o m�todo main() que inicia o programa.
 */
public class Corrida {

	/**
	 * Quantidade de sapos que participar�o da corrida
	 */
	private static final int QTDE_SAPOS = 5;
	
	/**
	 * Dist�ncia total a ser percorrida por cada sapo
	 */
	private static final int DISTANCIA_TOTAL = 500;
	
	/**
	 * M�todo main()
	 * @param args
	 */
	public static void main(String[] args) {
		
		//Cria um pool de threads, onde cada uma representar� um sapo
		ExecutorService executor = Executors.newFixedThreadPool(QTDE_SAPOS);
		
		//Inicia a execu��o de cada thread (sapo)
		for (int i = 0; i < QTDE_SAPOS; i++) {
			executor.execute(new Sapo("Sapo_" + (i + 1), DISTANCIA_TOTAL));
		}
		
		//Finaliza o pool. O m�todo shutdown() n�o interrompe as threads que j� est�o executando
		executor.shutdown();
	}
}
