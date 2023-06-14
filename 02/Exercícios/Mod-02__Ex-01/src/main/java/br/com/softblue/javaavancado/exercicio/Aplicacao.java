package br.com.softblue.javaavancado.exercicio;

public class Aplicacao {

	public static void main(String[] args) {
		
		// Cria um Map onde as chaves são do tipo Integer e os valores Strings
		Map<Integer, String> map = new Map<Integer, String>();
		
		// Insere elementos no Map
		map.put(1, "A");
		map.put(2, "B");
		map.put(3, "C");
		map.put(4, "D");
		map.put(1, "X"); // Muda o valor da chave 1 de "A" para "X"
		
		// Imprime os valores associados às chaves
		System.out.println(map.get(1));
		System.out.println(map.get(2));
		System.out.println(map.get(3));
		System.out.println(map.get(4));
		System.out.println(map.get(5)); // Imprime null porque a chave não existe
	}
}
