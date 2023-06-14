package br.com.softblue.java.concurrent;

public class Carro implements Runnable {

	private int id;
	private Semaforo semaforo;
	
	public Carro(int id, Semaforo semaforo) {
		this.id = id;
		this.semaforo = semaforo;
	}
	
	@Override
	public void run() {
		while(true) {
			semaforo.atravessar(id);
			try {
				Thread.sleep(1000 + (int)(Math.random() * 4000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}	
}
