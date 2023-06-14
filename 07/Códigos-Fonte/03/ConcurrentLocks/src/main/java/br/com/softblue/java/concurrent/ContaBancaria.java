package br.com.softblue.java.concurrent;

import java.util.concurrent.locks.ReentrantLock;

public class ContaBancaria {
	private ReentrantLock lock = new ReentrantLock();
	
	private double saldo;
	
	public void sacar(double valor) {
		lock.lock();
		try {
			this.saldo -= valor;
		} finally {
			lock.unlock();
		}
	}
	
	public void depositar(double valor) {
		lock.lock();
		try {
			this.saldo += valor;
		} finally {
			lock.unlock();
		}
	}
	
	public double getSaldo() {
		return saldo;
	}
}
