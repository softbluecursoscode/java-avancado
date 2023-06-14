package br.com.softblue.java.concurrent;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class Main {

	public static void main(String[] args) throws Exception {
		
//		Somador1 somador1 = new Somador1(20, 10);
//		
//		ExecutorService service = Executors.newSingleThreadExecutor();
//		
//		service.execute(somador1);
//		
//		while (somador1.isRunning()) {
//			Thread.sleep(1000);
//			System.out.println("Aguardando...");
//		}
//		
//		if (somador1.getException() == null) {
//			int resultado = somador1.getResultado();
//			System.out.println(resultado);
//		} else {
//			System.out.println("ERRO: " + somador1.getException());
//		}
//		
//		service.shutdown();
		
		
		Somador2 somador2 = new Somador2(-20, 10);
		ExecutorService service = Executors.newSingleThreadExecutor();
		
		FutureTask<Integer> task = new FutureTask<>(somador2);
		service.execute(task);
		
		while (!task.isDone()) {
			Thread.sleep(1000);
			System.out.println("Aguardando...");
		}
		
		try {
			int resultado = task.get();
			System.out.println(resultado);
		
		} catch (ExecutionException e) {
			Throwable innerEx = e.getCause();
			System.out.println("Erro: " + innerEx);
		}
		
		service.shutdown();
	}
}
