package br.com.softblue.javaavancado.exercicio;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
public class DatabaseImpl extends UnicastRemoteObject implements Database {
	
	/**
	 * Dados do banco de dados, que ficam armazenados em um map
	 */
	private Map<String, String> data = new HashMap<String, String>();

	protected DatabaseImpl() throws RemoteException {
	}
	
	/**
	 * @see br.com.softblue.javaavancado.exercicio.Database#insertOrUpdate(java.lang.String, java.lang.String)
	 */
	@Override
	public void insertOrUpdate(String key, String value) {
		data.put(key, value);
		System.out.println("Dado inserido/atualizado");
	}
	
	/**
	 * @see br.com.softblue.javaavancado.exercicio.Database#get(java.lang.String)
	 */
	@Override
	public String get(String key) {
		return data.get(key);
	}
	
	/**
	 * @see br.com.softblue.javaavancado.exercicio.Database#delete(java.lang.String)
	 */
	@Override
	public void delete(String key) {
		data.remove(key);
		System.out.println("Dado removido");
	}

	/**
	 * @see br.com.softblue.javaavancado.exercicio.Database#getValues()
	 */
	@Override
	public List<String> getValues() throws RemoteException {
		return new ArrayList<String>(data.values());
	}
}
