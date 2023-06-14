package br.com.softblue.java.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Main {

	public static void main(String[] args) throws Exception {
		
		Info info = new Info();
		int numLeitores = 5;
		int numEscritores = 5;
		
		ExecutorService e = Executors.newFixedThreadPool(numLeitores + numEscritores);
		
		for (int i = 0; i < numLeitores; i++) {
			Leitor r = new Leitor("Leitor " + i, info);
			e.execute(r);
		}
		
		for (int i = 0; i < numEscritores; i++) {
			Escritor w = new Escritor(info);
			e.execute(w);
		}
		
		e.shutdown();
	}
}
