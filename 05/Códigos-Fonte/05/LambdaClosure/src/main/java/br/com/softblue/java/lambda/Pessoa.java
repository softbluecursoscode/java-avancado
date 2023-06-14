package br.com.softblue.java.lambda;

public class Pessoa {

	private int idade;

	public Pessoa(int idade) {
		this.idade = idade;
	}

	public int getIdade() {
		return idade;
	}
	
	public void setIdade(int idade) {
		this.idade = idade;
	}

	public double calcularFator() {
		double ajuste = 0.2;
		
		FactorCalculator calc = () -> idade * 10 / 2 * ajuste;
		
		return calc.calculate();
	}
}
