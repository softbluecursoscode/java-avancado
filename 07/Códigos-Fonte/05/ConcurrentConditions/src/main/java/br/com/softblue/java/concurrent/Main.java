package br.com.softblue.java.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

	private static final int NUM_CARROS = 5;

	public static void main(String[] args) {

		ExecutorService e = Executors.newFixedThreadPool(NUM_CARROS + 1);

		Semaforo semaforo = new Semaforo();
		e.execute(semaforo);

		for (int i = 1; i <= NUM_CARROS; i++) {
			Carro carro = new Carro(i, semaforo);
			e.execute(carro);
		}

		e.shutdown();
	}
}
