package br.com.softblue.bluekeeper.dao;

/**
 * Factory para cria��o de objetos DAO
 */
public class DAOFactory {

	/**
	 * Instancia uma classe que implementa a interface SenhaServicoDAO. A classe a ser implementada
	 * est� definida em um arquivo de configura��es
	 * @return Inst�ncia criada
	 */
	public static SenhaServicoDAO getSenhaServicoDAO() {
		try {
			// Obt�m o nome da classe a ser instanciada
			String daoClass = DAOProperties.getDAOClassName();
			
			// Instancia o objeto e retorna
			return (SenhaServicoDAO) Class.forName(daoClass).getDeclaredConstructor().newInstance();
		
		} catch (ReflectiveOperationException e) {
			// Se ocorreu algum erro, mostra a stacktrace e retorna null
			e.printStackTrace();
			return null;
		}
	}
}
