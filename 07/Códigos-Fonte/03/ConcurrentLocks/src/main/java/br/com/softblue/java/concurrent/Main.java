package br.com.softblue.java.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

	public static void main(String[] args) {
		
		ExecutorService e = Executors.newFixedThreadPool(2);
		
		ContaBancaria conta = new ContaBancaria();
		
		Cliente c1 = new Cliente(conta);
		Cliente c2 = new Cliente(conta);
		
		e.execute(c1);
		e.execute(c2);
		
		e.shutdown();
		
		while (!e.isTerminated()) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e1) {
			}
		}
		
		System.out.println(conta.getSaldo());
	}
}
