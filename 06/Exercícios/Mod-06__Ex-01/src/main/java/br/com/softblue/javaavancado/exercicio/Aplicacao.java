package br.com.softblue.javaavancado.exercicio;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Aplicacao {

	public static void main(String[] args) {
		
		// Lista de �ngulos em graus
		List<Integer> angulosGraus = Arrays.asList(90, 30, 60, 45, 180);
		
		List<Double> angulosRadianos = angulosGraus.stream()
			.map(Math::toRadians) // Mapeia cada �ngulo para radianos. A express�o lambda 'a -> Math.toRadians(a)' tamb�m pode ser usada
			.collect(Collectors.toList()); // Gera a lista de sa�da
		
		// Imprime os �ngulos na tela
		angulosRadianos.forEach(System.out::println);
	}
}
