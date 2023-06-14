package br.com.softblue.javaavancado.exercicio;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * Calculador do PI.
 */
public class Calculador {
	
	/**
	 * N�mero de threads usadas no c�lculo de PI 
	 */
	public static final int NUM_THREADS = 10;
	
	/**
	 * N�mero de itera��es que cada thread usar� para calcular o PI.
	 * Quanto maior o n�mero de itera��es, mais precis�o nas casas decimais � obtida
	 */
	public static final int NUM_ITERACOES = 100000000;

	/**
	 * M�todo main().
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		
		//cria um pool de threads, onde cada thread calcular� uma parcial
		ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);
		
		//lista com as tarefas que ser�o executadas
		List<FutureTask<Double>> tasks = new ArrayList<FutureTask<Double>>();
		
		//armazena o tempo inicial de execu��o, para calcular depois quanto tempo o programa levou
		//para executar
		long initTime = System.currentTimeMillis();
		
		//instancia as threads e as inicia
		for (int i = 0; i < NUM_THREADS; i++) {
			FutureTask<Double> task = new FutureTask<Double>(new CalculadorParcial(i)); 
			tasks.add(task);
			executor.execute(task);
		}
		
		//vari�vel para acumular a soma de todas as parciais
		double soma = 0;
		
		//soma os valores de todas as parciais
		for (FutureTask<Double> task : tasks) {
			soma += task.get();
		}
		
		//finaliza as threads do pool
		executor.shutdown();
		
		//termina de realizar o c�lculo, multiplicando a soma por 4
		double pi = soma * 4;
		
		//obt�m novamente a data/hora do sistema
		long finishTime = System.currentTimeMillis();
		
		//mostra o valor de PI e o tempo que o programa levou para executar
		System.out.println("Valor de PI = " + pi);
		System.out.println("Tempo: " + (finishTime - initTime) + "ms");
	}
}