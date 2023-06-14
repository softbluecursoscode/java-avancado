package br.com.softblue.java.concurrent;

public class Cliente implements Runnable {

	private ContaBancaria conta;
	
	public Cliente(ContaBancaria conta) {
		this.conta = conta;
	}

	@Override
	public void run() {
		try {
			for (int i = 0; i < 5; i++) {
				conta.depositar(100);
				Thread.sleep(200);
				conta.sacar(100);
			}
		} catch (InterruptedException e) {
		}
	}
}
