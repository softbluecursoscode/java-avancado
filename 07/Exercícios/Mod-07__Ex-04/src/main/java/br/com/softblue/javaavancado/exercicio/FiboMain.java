package br.com.softblue.javaavancado.exercicio;

import java.util.concurrent.ForkJoinPool;

public class FiboMain {

	public static void main(String[] args) {
		
		/*
		 * � criado um pool de threads, que ser�o usadas para realizar o processamento paralelo
		 */
		ForkJoinPool pool = new ForkJoinPool();
		
		/*
		 * � feito uma repeti��o de 0 a 29, para gerar os 30 primeiros n�meros da sequ�ncia de Fibonacci
		 */
		for (int n = 0; n < 30; n++) {
			/*
			 * Cria um objeto FiboTask para calcular o valor da sequ�ncia para o valor n
			 */
			FiboTask task = new FiboTask(n);
			
			/*
			 * Inicia a execu��o da tarefa. Este m�todo aguarda o recebimento do resultado final
			 */
			long fibo = pool.invoke(task);
			
			/*
			 * Imprime o resultado
			 */
			System.out.print(fibo + " ");
		}
	}
}
