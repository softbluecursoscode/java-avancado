package br.com.softblue.java.chat.client.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Gerencia o arquivo de configura��o do cliente
 */
public class ClientConfigFile {
	/**
	 * Nome do arquivo de configura��o
	 */
	private static final String CONFIG_FILE = "client_config.txt";
	
	/**
	 * Nome da propriedade que define o nome do servidor
	 */
	private static final String PROP_SERVER = "server";
	
	/**
	 * Nome da propriedade que define a porta do RMI Registry 
	 */
	private static final String PROP_PORT = "port";
	
	/**
	 * Nome padr�o do servidor
	 */
	private static final String DEFAULT_SERVER = "localhost";
	
	/**
	 * Porta padr�o do RMI Registry 
	 */
	private static final int DEFAULT_PORT = 1909;
	
	/**
	 * Objeto Properties com as propriedades do servidor
	 */
	private static Properties props;
	
	static {
		// Carrega as informa��es do arquivo para o objeto Properties
		
		props = new Properties();
		
		File file = new File(CONFIG_FILE);
		
		if (file.exists()) {
			FileInputStream fis = null;
			try {
				fis = new FileInputStream(file);
				props.load(fis);
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (fis != null) {
					try {
						fis.close();
					} catch (IOException e) {
					}
				}
			}
		
		} else {
			// Se o arquivo n�o existir, define valores padr�o para o servidor e a porta
			setServer(DEFAULT_SERVER);
			setPort(DEFAULT_PORT);
		}
	}
	
	/**
	 * Construtor privado.
	 * @throws IOException
	 */
	private ClientConfigFile() throws IOException {
	}
	
	/**
	 * Define o servidor
	 * @param server Servidor
	 */
	public static void setServer(String server) {
		props.setProperty(PROP_SERVER, server);
	}
	
	/**
	 * Define a porta
	 * @param port Porta
	 */
	public static void setPort(int port) {
		props.setProperty(PROP_PORT, String.valueOf(port));
	}
	
	/**
	 * Obt�m o servidor
	 * @return Servidor
	 */
	public static String getServer() {
		return props.getProperty(PROP_SERVER);
	}
	
	/**
	 * Obt�m a porta
	 * @return Porta
	 */
	public static int getPort() {
		return Integer.parseInt(props.getProperty(PROP_PORT));
	}
	
	/**
	 * Salva as informa��es no arquivo de configura��o.
	 * @throws IOException
	 */
	public static void save() throws IOException {
		FileOutputStream fos = null;
		
		try {
			fos = new FileOutputStream(CONFIG_FILE);
			props.store(fos, null);
		} finally {
			if (fos != null) {
				fos.close();
			}
		}
	}
}
