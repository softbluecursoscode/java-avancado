package br.com.softblue.javaavancado.exercicio;

public class Aplicacao {

	public static void main(String[] args) {
		
		/*
		 * Cria um objeto Calculadora. A cria��o do objeto � delegada para o m�todo ObjectCreator.create(), 
		 * que possui a l�gica de criar objetos e chamar os m�todos anotados com @Init.
		 */
		Calculadora calc = ObjectCreator.create(Calculadora.class);
		
		/*
		 * Soma os valores e o resultado ser� 30. Veja que o pr�prio m�todo create() definiu os valores no 
		 * objeto atrav�s da chamada autom�tica do m�todo init().
		 */
		int soma = calc.somar();
		
		System.out.println(soma);
	}
}
