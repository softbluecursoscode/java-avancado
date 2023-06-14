package br.com.softblue.bluekeeper.dao;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * Lê os dados do arquivo de configuração dao.properties
 */
public class DAOProperties {

	/**
	 * Armazena as propriedades do arquivo
	 */
	private static Properties props = new Properties();
	
	static {
		try {
			// Obtém o caminho do arquivo de configuração
			Path path = Paths.get(DAOProperties.class.getResource("/dao.properties").toURI());
			
			// Carrega os dados para o objeto Properties
			try (InputStream in = Files.newInputStream(path)) {
				props.load(in);
			}
		
		} catch (URISyntaxException | IOException e) {
			throw new ExceptionInInitializerError(e);
		}
	}
	
	/**
	 * Retorna o nome da classe DAO que será usada pela aplicação
	 * @return
	 */
	public static String getDAOClassName() {
		return props.getProperty("dao.class");
	}
	
	/**
	 * Retorna o nome do servidor onde está o banco dados
	 * @return
	 */
	public static String getDBDAOServerName() {
		return props.getProperty("dao.db.serverName");
	}
	
	/**
	 * Retorna a porta para acessar o banco de dados
	 * @return
	 */
	public static int getDBDAOPort() {
		return Integer.parseInt(props.getProperty("dao.db.port"));
	}
	
	/**
	 * Retorna o nome do banco de dados
	 * @return
	 */
	public static String getDBDAODBName() {
		return props.getProperty("dao.db.dbName");
	}
	
	/**
	 * Retorna o nome do usuário para acessar o banco de dados
	 * @return
	 */
	public static String getDBDAOUserName() {
		return props.getProperty("dao.db.userName");
	}
	
	/**
	 * Retorna a senha para acessar o banco de dados
	 * @return
	 */
	public static String getDBDAOPassword() {
		return props.getProperty("dao.db.password");
	}
}
