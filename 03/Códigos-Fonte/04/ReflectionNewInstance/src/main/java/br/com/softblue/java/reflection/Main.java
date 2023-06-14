package br.com.softblue.java.reflection;

import java.io.File;
import java.util.Scanner;

public class Main {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {
		
		//Cafe bebida = new Cafe();
		//Cha bebida = new Cha();
		//bebida.preparar();
		
		String className;
		try (Scanner scanner = new Scanner(new File("class_name.txt"))) {
		 className = scanner.nextLine();
		}
		
		Class<Bebida> bebidaClass = (Class<Bebida>) Class.forName(className);
		Bebida bebida = bebidaClass.getDeclaredConstructor().newInstance();
		bebida.preparar();
	}
}
