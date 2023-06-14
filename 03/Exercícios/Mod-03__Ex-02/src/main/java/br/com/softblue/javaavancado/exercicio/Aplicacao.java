package br.com.softblue.javaavancado.exercicio;

import java.util.Calendar;
import java.util.Date;

public class Aplicacao {

	public static void main(String[] args) throws Exception {
		
		// Cria um Calendar para representar a data de nascimento
		Calendar calendar = Calendar.getInstance();
		calendar.set(1980, 7, 10);
		
		// Cria um objeto Pessoa
		Pessoa p = new Pessoa();
		
		// Utiliza Property.set() para definir valores para as propriedades
		// Este método invoca os métodos setters automaticamente
		// Para as propriedades altura e numFilhos, se o 3o. parâmetro (tipo de dado da propriedade) não for
		// passado, o código irá procurar pelos métodos setAltura(Double) e setNumFilhos(Integer), que não 
		// existem. Por isto os objetos Class representantes dos tipos primitivos double e int são fornecidos.
		Property.set(p, "nome", "Pedro");
		Property.set(p, "altura", 1.8, double.class);
		Property.set(p, "dataNascimento", calendar.getTime());
		Property.set(p, "numFilhos", 10, int.class);
		
		// Imprime no console o objeto (invoca o toString());
		System.out.println(p);
		
		// Utiliza Property.get() para retornar os valores das propriedades
		// Este método invoca os métodos getters automaticamente
		String nome = Property.get(p, "nome", String.class);
		double altura = Property.get(p, "altura", double.class);
		Date dataNascimento = Property.get(p, "dataNascimento", Date.class);
		int numFilhos = Property.get(p, "numFilhos", int.class);
		
		// Imprime os dados do objeto no console
		System.out.println(nome);
		System.out.println(altura);
		System.out.println(dataNascimento);
		System.out.println(numFilhos);
	}
}
