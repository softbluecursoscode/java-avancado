package br.com.softblue.javaavancado.exercicio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

/**
 * Classe inicial da aplica��o
 */
public class Aplicacao {

	/**
	 * M�todo main()
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		
		// Arquivo de sa�da
		Path outputFile = Paths.get("text.txt");
		
		// Obt�m a string que representa a quebra de linha na plataforma
		String lineSeparator = System.getProperty("line.separator");
		
		// Reader que representa o console
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("Digite seu texto =>");
		
		// Writer que representa o arquivo onde os dados ser�o gravados.
		// O arquivo � criado se n�o existir e sempre aberto no modo APPEND (para n�o perder os dados j� existentes)
		try (BufferedWriter writer = Files.newBufferedWriter(outputFile, StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
			
			while (true) {
				// L� a linha digitada no console
				String line = reader.readLine();
				
				// Termina o loop se nada foi digitado
				if (line.isEmpty()) {
					break;
				}
				
				// Grava o que foi digitado no arquivo
				writer.write(line);
				
				// Insere no arquivo uma quebra de linha
				writer.write(lineSeparator);
			}
		}
		
		System.out.println("Dados no arquivo: ");
		
		// L� todos as linhas armazenadas no arquivo
		List<String> lines = Files.readAllLines(outputFile);
		
		// Itera sobre as linhas e mostra no console
		for (String line : lines) {
			System.out.println(line);
		}
	}
}
