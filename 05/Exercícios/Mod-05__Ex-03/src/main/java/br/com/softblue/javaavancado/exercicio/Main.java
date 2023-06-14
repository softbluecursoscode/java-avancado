package br.com.softblue.javaavancado.exercicio;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Main {

	public static void main(String[] args) {
		// Criação da lista de string
		List<String> list1 = new ArrayList<>();
		list1.add("abc");
		list1.add("def");
		list1.add("ghi");
		
		// Converte os elementos para letras maiúsculas
		List<String> newList1 = transform(list1, s -> s.toUpperCase());
		System.out.println("List 1: " + newList1);
		
		// Criação da lista de números decimais
		List<Double> list2 = new ArrayList<>();
		list2.add(32.54);
		list2.add(9.7);
		list2.add(21.35);
		
		// Arredonda para baixo os elementos
		List<Double> newList2 = transform(list2, n -> Math.floor(n));
		System.out.println("List 2: " + newList2);
	}
	
	// Aplica a transformação usando um Function
	private static <T> List<T> transform(List<T> list, Function<T, T> function) {
		List<T> newList = new ArrayList<>(list.size());
		
		for (T i : list) {
			newList.add(function.apply(i));
		}
		
		return newList;
	}
}