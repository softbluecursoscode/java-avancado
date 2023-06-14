package br.com.softblue.javaavancado.exercicio;

import java.nio.file.Path;


/**
 * Classe inicial da aplica��o
 */
public class Aplicacao {

	/**
	 * M�todo main()
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		// Instancia o objeto respons�vel por fazer a busca
		FileSearch search = new FileSearch();
		
		// Efetua a busca
		Path p = search.search("arquivo.txt", "C:\\Temp");
		
		// Mostra no console o caminho do arquivo encontrado
		System.out.println(p);
	}
}
