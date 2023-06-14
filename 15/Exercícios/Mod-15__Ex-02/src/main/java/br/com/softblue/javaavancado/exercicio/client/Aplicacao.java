package br.com.softblue.javaavancado.exercicio.client;

import br.com.softblue.javaavancado.exercicio.common.Calc;



/**
 * Classe inicial da aplica��o
 */
public class Aplicacao {

	/**
	 * M�todo main()
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		/*
		 * Cria o objeto utilizado para chamar os m�todos. Perceba que CalcProxy implementa a interface Calc,
		 * e encapsula toda a l�gica de acesso remoto. Do ponto de vista do cliente, esta abordagem permite que 
		 * a chamada seja feita como uma chamada em um objeto local
		 */
		Calc calc = new CalcProxy();
		
		/*
		 * Chama alguns m�todos de Calc. Internamente, as chamadas s�o feitas remotamente
		 */
		System.out.println(calc.somar(10, 20));
		System.out.println(calc.subtrair(50, 10));
		System.out.println(calc.multiplicar(10, 2));
		System.out.println(calc.dividir(100, 10));
	}
}
