package br.com.softblue.javaavancado.exercicio;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Interface com as operações do banco de dados.
 */
public interface Database extends Remote {

	/**
	 * Insere ou atualiza um registro.
	 * @param key Chave a ser inserida/atualizada.
	 * @param value Valor a ser associado à chave
	 * @throws RemoteException
	 */
	public void insertOrUpdate(String key, String value) throws RemoteException;

	/**
	 * Retorna um valor armazenado com base em uma chave.
	 * @param key Chave para procurar
	 * @return Valor associado à chave.
	 * @throws RemoteException
	 */
	public String get(String key) throws RemoteException;

	/**
	 * Exclui uma chave.
	 * @param key Chave para ser excluída
	 * @throws RemoteException
	 */
	public void delete(String key) throws RemoteException;

	/**
	 * Retorna uma lista com todos os valores armazenados no banco de dados
	 * @return
	 * @throws RemoteException
	 */
	public List<String> getValues() throws RemoteException;
}
