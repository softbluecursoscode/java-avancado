package br.com.softblue.javaavancado.exercicio;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Aplicacao {

	public static void main(String[] args) {
	
		List<String> numeros = new ArrayList<>();
		numeros.add("5");
		numeros.add("31");
		numeros.add("22");
		numeros.add("14");
		numeros.add("9");
		numeros.add("30");
		numeros.add("18");
		
		int soma1 = numeros.stream()
			.mapToInt(Integer::parseInt)
			.sum();
		System.out.println(soma1);
		
		int soma2 = numeros.stream()
			.map(n -> Integer.parseInt(n))
			.collect(Collectors.summingInt(n -> n));
		System.out.println(soma2);
	}
}
