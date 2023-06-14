package br.com.softblue.java.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

	public static void main(String[] args) {
		
		//ExecutorService e = Executors.newSingleThreadExecutor();
		ExecutorService e = Executors.newFixedThreadPool(2);
		
		for (int i = 1; i <= 5; i++) {
			Task t = new Task(i);
			e.execute(t);
		}
		
		e.shutdown();
	}
}
