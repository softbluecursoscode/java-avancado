package br.com.softblue.javaavancado.exercicio;

import java.rmi.Naming;
import java.util.List;

/**
 * Classe do cliente. Acessa o objeto remotamente.
 */
public class Client {

	public static void main(String[] args) throws Exception {
		// Faz o lookup do objeto utilizando uma URL
		String url = "rmi://localhost:1099/db";
		Database database = (Database) Naming.lookup(url);

		// Insere alguns registros. Outras opera��es podem ser utilizadas tamb�m.
		database.insertOrUpdate("nome1", "Paulo");
		database.insertOrUpdate("nome2", "Pedro");
		database.insertOrUpdate("nome3", "Maria");
		database.insertOrUpdate("nome4", "Joana");
		
		// Obt�m a lista de registros cadastrados, itera sobre eles e imprime-os no console
		List<String> values = database.getValues();
		
		for (String value : values) {
			System.out.println(value);
		}
	}
}
