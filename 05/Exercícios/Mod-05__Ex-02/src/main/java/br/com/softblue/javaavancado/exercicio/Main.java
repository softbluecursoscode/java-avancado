package br.com.softblue.javaavancado.exercicio;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Main {

	public static void main(String[] args) {
		// Criação da lista de números
		List<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		
		// Multiplica cada elemento por 2
		List<Integer> newList1 = transform(list, x -> x * 2);
		System.out.println("List 1: " + newList1);
		
		// Muda o sinal de cada elemento
		List<Integer> newList2 = transform(list, x -> -x);
		System.out.println("List 2: " + newList2);
	}
	
	// Aplica a transformação usando um Function
	private static List<Integer> transform(List<Integer> list, Function<Integer, Integer> function) {
		List<Integer> newList = new ArrayList<>(list.size());
		
		for (Integer i : list) {
			newList.add(function.apply(i));
		}
		
		return newList;
	}
}