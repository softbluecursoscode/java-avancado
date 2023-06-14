package br.com.softblue.javaavancado.exercicio;

import java.util.concurrent.RecursiveTask;

/* 
 * FiboTask � a classe respons�vel pelo c�lculo de forma paralela.
 * Ela herda de RecursiveTask, pois o c�lculo de cada subtarefa gera um n�mero como resposta (do tipo long).
 */
@SuppressWarnings("serial")
public class FiboTask extends RecursiveTask<Long> {
	
	/* Determina qual n�mero da sequ�ncia esta task vai calcular */
	private int n;
	
	public FiboTask(int n) {
		this.n = n;
	}
	
	/*
	 * O m�todo compute() � chamado quando a tarefa inicia
	 */
	@Override
	protected Long compute() {
		/*
		 * Se o valor de n for menor que 5, o c�lculo � feito diretamente, sem o uso de paralelismo. Criar uma subtarefa para 
		 * fazer um c�lculo muito simples pode provocar uma queda na performance da aplica��o.
		 */
		if (n < 5) {
			return fibonacci(n);
		}
		
		/*
		 * Aqui s�o criadas duas tarefas, onde uma ir� calcular o n�mero da sequ�ncia n - 2 e e outra o n�mero n - 1.
		 */
		FiboTask task1 = new FiboTask(n - 2);
		FiboTask task2 = new FiboTask(n - 1);
		
		/*
		 * O fork() em task1 faz com que essa tarefa passe a ser calculada de forma paralela, possivelmente por outro processador
		 */
		task1.fork();
		
		/*
		 * O resultado da task2 � calculado atrav�s da chamado ao m�todo compute(). Perceba que � a pr�pria tarefa quem faz esse c�lculo,
		 * ao inv�s de delegar outra tarefa para faz�-lo.
		 */
		long task2Result = task2.compute();
		
		/*
		 * O resultado de task1 depende do final da execu��o da subtarefa, que est� executando em paralelo. O m�todo join() provoca 
		 * esta espera no c�lculo.
		 */
		long task1Result = task1.join();
		
		/*
		 * O resultado final � a soma dos resultados das duas tarefas.
		 */
		return task1Result + task2Result;
	}
	
	/*
	 * Este m�todo calcula o valor do n-�simo n�mero da sequ�ncia de Fibonacci de forma recursiva
	 */
	private long fibonacci(int n) {
		if (n <= 1) {
			return n;
		}
		
		return fibonacci(n - 2) + fibonacci(n - 1);
	}
}
