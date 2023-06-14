package br.com.softblue.javaavancado.exercicio;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Classe inicial da aplicação
 */
public class Aplicacao {

	/**
	 * Método main().
	 * @param args
	 */
	public static void main(String[] args) {
		
		//instancia um executor para as thread do produtor e do consumidor
		ExecutorService executor = Executors.newFixedThreadPool(2);
		
		//instancia um buffer
		Buffer buffer = new Buffer();
		
		//instancia o produtor, que deve produzir no buffer
		Produtor p = new Produtor(100, buffer);
		
		//inicia a thread do produtor
		executor.execute(p);
		
		//instancia o consumidor, que deve consumir do buffer
		Consumidor c = new Consumidor(100, buffer);
		
		//inicia a thread do consumidor
		executor.execute(c);
		
		//finaliza o pool de threads
		executor.shutdown();
	}
}
