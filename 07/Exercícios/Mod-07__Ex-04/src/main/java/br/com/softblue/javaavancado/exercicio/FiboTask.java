package br.com.softblue.javaavancado.exercicio;

import java.util.concurrent.RecursiveTask;

/* 
 * FiboTask é a classe responsável pelo cálculo de forma paralela.
 * Ela herda de RecursiveTask, pois o cálculo de cada subtarefa gera um número como resposta (do tipo long).
 */
@SuppressWarnings("serial")
public class FiboTask extends RecursiveTask<Long> {
	
	/* Determina qual número da sequência esta task vai calcular */
	private int n;
	
	public FiboTask(int n) {
		this.n = n;
	}
	
	/*
	 * O método compute() é chamado quando a tarefa inicia
	 */
	@Override
	protected Long compute() {
		/*
		 * Se o valor de n for menor que 5, o cálculo é feito diretamente, sem o uso de paralelismo. Criar uma subtarefa para 
		 * fazer um cálculo muito simples pode provocar uma queda na performance da aplicação.
		 */
		if (n < 5) {
			return fibonacci(n);
		}
		
		/*
		 * Aqui são criadas duas tarefas, onde uma irá calcular o número da sequência n - 2 e e outra o número n - 1.
		 */
		FiboTask task1 = new FiboTask(n - 2);
		FiboTask task2 = new FiboTask(n - 1);
		
		/*
		 * O fork() em task1 faz com que essa tarefa passe a ser calculada de forma paralela, possivelmente por outro processador
		 */
		task1.fork();
		
		/*
		 * O resultado da task2 é calculado através da chamado ao método compute(). Perceba que é a própria tarefa quem faz esse cálculo,
		 * ao invés de delegar outra tarefa para fazê-lo.
		 */
		long task2Result = task2.compute();
		
		/*
		 * O resultado de task1 depende do final da execução da subtarefa, que está executando em paralelo. O método join() provoca 
		 * esta espera no cálculo.
		 */
		long task1Result = task1.join();
		
		/*
		 * O resultado final é a soma dos resultados das duas tarefas.
		 */
		return task1Result + task2Result;
	}
	
	/*
	 * Este método calcula o valor do n-ésimo número da sequência de Fibonacci de forma recursiva
	 */
	private long fibonacci(int n) {
		if (n <= 1) {
			return n;
		}
		
		return fibonacci(n - 2) + fibonacci(n - 1);
	}
}
