package br.com.softblue.java.nio2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

	public static void main(String[] args) throws IOException {
		
		Path dir = Paths.get("C:\\Temp\\Curso");
		
//		try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir, "*.tx?")) {
//			for (Path path : stream) {
//				System.out.println(path);
//			}
//		}
		
//		try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir, p -> Files.size(p) > 100)) {
//			for (Path path : stream) {
//				System.out.println(path);
//			}
//		}
	
		SearchFile searchFile = new SearchFile("txt");
		Files.walkFileTree(dir, searchFile);
	}
}
