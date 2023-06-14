package br.com.softblue.javaavancado.exercicio;

public class Aplicacao {

	public static void main(String[] args) {
		
		/*
		 * Cria um objeto Calculadora. A criação do objeto é delegada para o método ObjectCreator.create(), 
		 * que possui a lógica de criar objetos e chamar os métodos anotados com @Init.
		 */
		Calculadora calc = ObjectCreator.create(Calculadora.class);
		
		/*
		 * Soma os valores e o resultado será 30. Veja que o próprio método create() definiu os valores no 
		 * objeto através da chamada automática do método init().
		 */
		int soma = calc.somar();
		
		System.out.println(soma);
	}
}
