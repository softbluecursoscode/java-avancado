package br.com.softblue.javaavancado.exercicio;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		
		// Cria a lista
		List<Integer> list = new ArrayList<Integer>();
		list.add(5);
		list.add(7);
		list.add(0);
		list.add(4);
		list.add(8);
		list.add(6);
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(9);
		
		// Faz a ordenação. O Comparator é passado como parâmetro em forma de uma
		// expressão lambda. Como a classe Integer implementa o método compare(), 
		// que ordena em ordem crescente, basta negar o resultado para inverter
		// a ordenação
		list.sort((n1, n2) -> -n1.compareTo(n2));
		
		// Usa o recurso referência a método para imprimir cada um dos itens da lista 
		list.forEach(System.out::println);
	}
}