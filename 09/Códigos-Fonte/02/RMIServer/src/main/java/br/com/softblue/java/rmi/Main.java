package br.com.softblue.java.rmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Main {

	public static void main(String[] args) throws Exception {
		ContaBancaria conta1 = new ContaBancariaImpl();
		//ContaBancaria contaStub1 = (ContaBancaria) UnicastRemoteObject.exportObject(conta1, 0);
		
		ContaBancaria conta2 = new ContaBancariaImpl();
		//ContaBancaria contaStub2 = (ContaBancaria) UnicastRemoteObject.exportObject(conta2, 0);
		
		Registry registry = LocateRegistry.createRegistry(1099);
//		registry.rebind("conta1", contaStub1);
//		registry.rebind("conta2", contaStub2);
		
		registry.rebind("conta1", conta1);
		registry.rebind("conta2", conta2);
		
		System.out.println("Aguardando requisições");
	}
}
