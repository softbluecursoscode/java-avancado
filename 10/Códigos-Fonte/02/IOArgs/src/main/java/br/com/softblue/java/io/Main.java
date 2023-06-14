package br.com.softblue.java.io;

public class Main {

	public static void main(String[] args) {
		System.out.println("Qtde de argumentos: " + args.length);
		
		for (String arg : args) {
			System.out.println("=> " + arg);
		}
	}
}
