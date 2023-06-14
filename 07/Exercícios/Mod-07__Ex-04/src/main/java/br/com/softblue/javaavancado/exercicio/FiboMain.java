package br.com.softblue.javaavancado.exercicio;

import java.util.concurrent.ForkJoinPool;

public class FiboMain {

	public static void main(String[] args) {
		
		/*
		 * É criado um pool de threads, que serão usadas para realizar o processamento paralelo
		 */
		ForkJoinPool pool = new ForkJoinPool();
		
		/*
		 * É feito uma repetição de 0 a 29, para gerar os 30 primeiros números da sequência de Fibonacci
		 */
		for (int n = 0; n < 30; n++) {
			/*
			 * Cria um objeto FiboTask para calcular o valor da sequência para o valor n
			 */
			FiboTask task = new FiboTask(n);
			
			/*
			 * Inicia a execução da tarefa. Este método aguarda o recebimento do resultado final
			 */
			long fibo = pool.invoke(task);
			
			/*
			 * Imprime o resultado
			 */
			System.out.print(fibo + " ");
		}
	}
}
