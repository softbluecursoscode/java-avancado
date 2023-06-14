package br.com.softblue.javaavancado.exercicio;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Aplicacao {

	public static void main(String[] args) {
		
		// Lista de ângulos em graus
		List<Integer> angulosGraus = Arrays.asList(90, 30, 60, 45, 180);
		
		List<Double> angulosRadianos = angulosGraus.stream()
			.map(Math::toRadians) // Mapeia cada ângulo para radianos. A expressão lambda 'a -> Math.toRadians(a)' também pode ser usada
			.collect(Collectors.toList()); // Gera a lista de saída
		
		// Imprime os ângulos na tela
		angulosRadianos.forEach(System.out::println);
	}
}
