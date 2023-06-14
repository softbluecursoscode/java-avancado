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
 * Classe inicial da aplicação
 */
public class Aplicacao {

	/**
	 * Método main()
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		
		// Arquivo de saída
		Path outputFile = Paths.get("text.txt");
		
		// Obtém a string que representa a quebra de linha na plataforma
		String lineSeparator = System.getProperty("line.separator");
		
		// Reader que representa o console
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("Digite seu texto =>");
		
		// Writer que representa o arquivo onde os dados serão gravados.
		// O arquivo é criado se não existir e sempre aberto no modo APPEND (para não perder os dados já existentes)
		try (BufferedWriter writer = Files.newBufferedWriter(outputFile, StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
			
			while (true) {
				// Lê a linha digitada no console
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
		
		// Lê todos as linhas armazenadas no arquivo
		List<String> lines = Files.readAllLines(outputFile);
		
		// Itera sobre as linhas e mostra no console
		for (String line : lines) {
			System.out.println(line);
		}
	}
}
