package br.com.softblue.javaavancado.exercicio;

/**
 * Classe que representa uma calculadora capaz de somar dois números
 */
public class Calculadora {

	/**
	 * Valor 1
	 */
	private int n1;
	
	/**
	 * Valor 2 
	 */
	private int n2;
	
	/**
	 * Método de inicialização do objeto, que será chamado logo após o objeto ser construído.
	 * Se runOnInstantiation for definido como false, o método não será chamado.
	 * O nome do método é indiferente. O importante é a presença de @Init.
	 */
	@Init(runOnInstantiation = true)
	public void init() {
		this.n1 = 10;
		this.n2 = 20;
	}
	
	/**
	 * Soma os dois números
	 * @return Soma dos dois números
	 */
	public int somar() {
		return n1 + n2;
	}
}
