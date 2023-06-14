package br.com.softblue.javaavancado.exercicio;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Classe do servidor. Disponibiliza o objeto remotamente.
 */
public class Server {

	public static void main(String[] args) throws Exception {
		// Cria o objeto que será disponibilizado remotamente
		Database database = new DatabaseImpl();
		
		// Inicia o RMI Registry na porta 1099
		Registry registry = LocateRegistry.createRegistry(1099);
		
		// Registra o objeto no RMI Registry
		registry.bind("db", database);
		
		System.out.println("Servidor iniciado");
	}
}
