package br.com.softblue.java.chat.common;
import java.io.Serializable;

import br.com.softblue.java.chat.common.rmi.ClientCallback;

/**
 * Representa informa��es de um cliente que se conecta ao servidor de chat. � este objeto que o servidor recebe 
 * quando um cliente se conecta, de forma que o servidor conhe�a as informa��es do cliente.
 * Esta classe implementa a interface {@link Serializable}, pois objetos da classe precisaram trafegar pela 
 * rede do cliente para o servidor.
 * A respeito da implementa��o dos m�todos equals() e hashCode(), dois objetos ClientInfo s�o considerados iguais 
 * se o nome de ambos � o mesmo.
 */
public class ClientInfo implements Serializable {
	private static final long serialVersionUID = 6037334813162395825L;

	/**
	 * Nome do cliente, digitado por ele na interface gr�fica
	 */
	private String name;
	
	/**
	 * Objeto de callback. � uma refer�ncia do cliente, utilizada pelo servidor para notificar o cliente. Um 
	 * exemplo � quando o servidor precisa enviar uma mensagem ao cliente. Ele utiliza este objeto para fazer 
	 * isto. 
	 */
	private ClientCallback callback;

	/**
	 * Construtor
	 * @param name Nome do cliente
	 * @param callback Objeto de callback
	 */
	public ClientInfo(String name, ClientCallback callback) {
		this.name = name;
		this.callback = callback;
	}

	/**
	 * Obt�m o nome do cliente
	 * @return Nome do cliente
	 */
	public String getName() {
		return name;
	}

	/**
	 * Define o nome do cliente
	 * @param name Nome do cliente
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Obt�m o objeto de callback
	 * @return Objeto de callback
	 */
	public ClientCallback getCallback() {
		return callback;
	}

	/**
	 * Define o objeto de callback
	 * @param callback Objeto de callback
	 */
	public void setCallback(ClientCallback callback) {
		this.callback = callback;
	}
	
	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return name;
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClientInfo other = (ClientInfo) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
