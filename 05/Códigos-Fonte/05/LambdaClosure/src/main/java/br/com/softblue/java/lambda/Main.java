package br.com.softblue.java.lambda;

public class Main {

	public static void main(String[] args) {
		
		Pessoa p = new Pessoa(28);
		double fator = p.calcularFator();
		System.out.println(fator);
	}
}
