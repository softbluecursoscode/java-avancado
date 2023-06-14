package br.com.softblue.bluekeeper.dao;

/**
 * Factory para criação de objetos DAO
 */
public class DAOFactory {

	/**
	 * Instancia uma classe que implementa a interface SenhaServicoDAO. A classe a ser implementada
	 * está definida em um arquivo de configurações
	 * @return Instância criada
	 */
	public static SenhaServicoDAO getSenhaServicoDAO() {
		try {
			// Obtém o nome da classe a ser instanciada
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
