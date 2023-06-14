package br.com.softblue.java.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

	public static void main(String[] args) {
		int numGeradores = 3;
		
		ExecutorService service = Executors.newFixedThreadPool(numGeradores);
		
		for (int i = 0; i < numGeradores; i++) {
			Gerador g = new Gerador();
			service.execute(g);
		}
		
		service.shutdown();
	}
}
