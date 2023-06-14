package br.com.softblue.java.concurrent;

import java.util.concurrent.atomic.AtomicInteger;

public class Sequencia {

	//private static int currentValue = 0;
	private static AtomicInteger currentValue = new AtomicInteger(0);
	
	public static int next() {
		//currentValue++;
		//return currentValue;
		return currentValue.incrementAndGet();
	}
}
