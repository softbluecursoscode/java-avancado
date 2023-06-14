package br.com.softblue.javaavancado.exercicio;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Aplicacao {

	// Atributo que gera os IDs para os objetos Papel
	private static int currentId = 1;
	
	public static void main(String[] args) {
		// Lista de cores (Strings)
		List<String> cores = Arrays.asList("Azul", "Branco", "Preto", "Preto", "Amarelo", "Azul");
		
		List<Papel> papeis = cores.stream()
			.map(c -> new Papel(currentId++, Papel.Cor.valueOf(c))) // Mapeia uma String para um nobo objeto Papel 
			.collect(Collectors.toList()); // Converte para lista
		
		// Exibe os elementos da lista
		papeis.forEach(p -> System.out.println(p.getId() + " -> " + p.getCor()));
	}
}
