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
	 * Número de threads usadas no cálculo de PI 
	 */
	public static final int NUM_THREADS = 10;
	
	/**
	 * Número de iterações que cada thread usará para calcular o PI.
	 * Quanto maior o número de iterações, mais precisão nas casas decimais é obtida
	 */
	public static final int NUM_ITERACOES = 100000000;

	/**
	 * Método main().
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		
		//cria um pool de threads, onde cada thread calculará uma parcial
		ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);
		
		//lista com as tarefas que serão executadas
		List<FutureTask<Double>> tasks = new ArrayList<FutureTask<Double>>();
		
		//armazena o tempo inicial de execução, para calcular depois quanto tempo o programa levou
		//para executar
		long initTime = System.currentTimeMillis();
		
		//instancia as threads e as inicia
		for (int i = 0; i < NUM_THREADS; i++) {
			FutureTask<Double> task = new FutureTask<Double>(new CalculadorParcial(i)); 
			tasks.add(task);
			executor.execute(task);
		}
		
		//variável para acumular a soma de todas as parciais
		double soma = 0;
		
		//soma os valores de todas as parciais
		for (FutureTask<Double> task : tasks) {
			soma += task.get();
		}
		
		//finaliza as threads do pool
		executor.shutdown();
		
		//termina de realizar o cálculo, multiplicando a soma por 4
		double pi = soma * 4;
		
		//obtém novamente a data/hora do sistema
		long finishTime = System.currentTimeMillis();
		
		//mostra o valor de PI e o tempo que o programa levou para executar
		System.out.println("Valor de PI = " + pi);
		System.out.println("Tempo: " + (finishTime - initTime) + "ms");
	}
}