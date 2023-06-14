package br.com.softblue.javaavancado.exercicio;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * Classe que implementa o mecanismo de busca
 */
public class FileSearch {
	
	/**
	 * Armazena o arquivo encontrado
	 */
	private Path foundFile;

	/**
	 * Faz a busca por um arquivo
	 * @param fileName Arquivo a ser buscado
	 * @param startDir Diret�rio inicial da busca
	 * @return Arquivo encontrado ou null caso n�o exista o arquivo
	 * @throws IOException
	 */
	public Path search(String fileName, String startDir) throws IOException {
		// Cria um objeto Path com base no diret�rio inicial
		Path path = Paths.get(startDir);
		
		// Checa se o diret�rio existe. Se n�o existir, lan�a uma IOException
		if (!Files.exists(path)) {
			throw new IOException("O diret�rio " + path + " n�o existe");
		}
		
		// Instancia o FileVisitor
		SearchVisitor visitor = new SearchVisitor(Paths.get(fileName));
		
		// Inicia a busca
		Files.walkFileTree(path, visitor);
		
		// Retorna o arquivo encontrado
		return foundFile;
	}
	
	/**
	 * Classe que implementa FileVisitor
	 */
	private class SearchVisitor implements FileVisitor<Path> {

		/**
		 * Armazena o arquivo a ser buscado
		 */
		private Path fileToSearch;

		/**
		 * Construtor
		 * @param fileToSearch Arquivo a ser buscado
		 */
		public SearchVisitor(Path fileToSearch) {
			this.fileToSearch = fileToSearch;
		}

		@Override
		public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
			// Antes de visitar o diret�rio, mostra o nome no console e continua o processo
			System.out.println("Procurando em " + dir);
			return FileVisitResult.CONTINUE;
		}

		@Override
		public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
			// Verifica se o arquivo sendo visitado corresponde ao arquivo desejado 
			if (file.getFileName().toString().equalsIgnoreCase(fileToSearch.toString())) {
				
				// Se for, mostra no console, grava a refer�ncia no atributo foundFile e termina o processo
				System.out.println("Arquivo encontrado: " + file);
				foundFile = file;
				return FileVisitResult.TERMINATE;
			}

			// Se n�o for, continua o processo
			return FileVisitResult.CONTINUE;
		}

		@Override
		public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
			// Em caso de falha no acesso ao arquivo, vai para o pr�ximo
			return FileVisitResult.CONTINUE;
		}

		@Override
		public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
			// Depois de visitar um diret�rio, vai para o pr�ximo
			return FileVisitResult.CONTINUE;
		}
	}
}
