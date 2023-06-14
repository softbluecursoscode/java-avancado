package br.com.softblue.java.concurrent;

public class Gerador implements Runnable {

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			int valor = Sequencia.next();
			System.out.println(valor);
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
			}
		}
	}
}
