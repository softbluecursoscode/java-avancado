package br.com.softblue.java.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ContaBancaria extends Remote {
	void sacar(double valor) throws RemoteException;
	
	void depositar(double valor) throws RemoteException;
	
	void transferir(double valor, ContaBancaria outraConta) throws RemoteException;
	
	double getSaldo() throws RemoteException;
}
