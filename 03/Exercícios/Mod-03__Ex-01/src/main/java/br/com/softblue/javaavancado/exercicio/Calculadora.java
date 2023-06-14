package br.com.softblue.javaavancado.exercicio;

/**
 * Classe que representa uma calculadora capaz de somar dois n�meros
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
	 * M�todo de inicializa��o do objeto, que ser� chamado logo ap�s o objeto ser constru�do.
	 * Se runOnInstantiation for definido como false, o m�todo n�o ser� chamado.
	 * O nome do m�todo � indiferente. O importante � a presen�a de @Init.
	 */
	@Init(runOnInstantiation = true)
	public void init() {
		this.n1 = 10;
		this.n2 = 20;
	}
	
	/**
	 * Soma os dois n�meros
	 * @return Soma dos dois n�meros
	 */
	public int somar() {
		return n1 + n2;
	}
}
