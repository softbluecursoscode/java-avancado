package br.com.softblue.java.reflection;

public class Televisao {

	private boolean ligada;
	private int canal;
	
	public Televisao() {
		System.out.println("Televisão criada");
	}
	
	public void ligar() {
		ligada = true;
		System.out.println("Televisão ligada");
	}
	
	public void desligar() {
		ligada = false;
		System.out.println("Televisão desligada");
	}
	
	public void mudarCanal(int novoCanal) {
		canal = novoCanal;
		System.out.println("Canal mudado para " + novoCanal);
	}
	
	public boolean isLigada() {
		return ligada;
	}
	
	public int getCanal() {
		return canal;
	}
}
