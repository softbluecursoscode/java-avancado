package br.com.softblue.java.concurrent;

public class Leitor implements Runnable {

	private Info info;
	private String nome;
	
	public Leitor(String nome, Info info) {
		this.nome = nome;
		this.info = info;
	}
	
	@Override
	public void run() {
		try {
			for(int i = 0; i < 10; i++) {
				String text = info.getInfo();
				System.out.println(nome + " => leu: " + text);
				Thread.sleep(100);
			}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
