package br.com.softblue.java.nio2;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

public class SearchFile implements FileVisitor<Path> {

	private String fileName;
	
	public SearchFile(String fileName) {;
		this.fileName = fileName;
	}

	@Override
	public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
		System.out.println("Entrando no diretório " + dir);
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
		System.out.println("Acessando o arquivo " + file);
		
		if (file.getFileName().toString().contains(fileName)) {
			System.out.println("Arquivo encontrado em " + file);
			return FileVisitResult.TERMINATE;
		}
		
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
		System.out.println("Falha ao acessar arquivo " + file + ": " + exc);
		return FileVisitResult.CONTINUE;
	}

	@Override
	public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
		System.out.println("Saindo do diretório " + dir);
		return FileVisitResult.CONTINUE;
	}
}
