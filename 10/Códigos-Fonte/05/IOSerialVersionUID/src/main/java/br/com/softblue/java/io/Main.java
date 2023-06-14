package br.com.softblue.java.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Main {

	public static void main(String[] args) throws Exception {
		serialize();
		unserialize();
	}

	private static void serialize() throws Exception {
		Usuario usuario = new Usuario();
		usuario.setId(5);
		usuario.setNome("Pedro");

		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("usuario.bin"))) {
			oos.writeObject(usuario);
		}
	}
	
	private static void unserialize() throws Exception {
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("usuario.bin"))) {
			Usuario usuario = (Usuario) ois.readObject();
			System.out.println(usuario);
		}
	}
}
