package br.com.softblue.javaavancado.exercicio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * Gerencia o arquivo de configura��es
 */
public class ConfigFile {
	public static final String PROP_NOME = "nome";
	public static final String PROP_IDADE = "idade";
	public static final String PROP_SEXO = "sexo";
	public static final String PROP_ESPORTES = "esportes";
	
	/**
	 * Arquivo de configura��o
	 */
	private static final Path CONFIG_FILE = Paths.get("config.txt");

	/**
	 * Objeto Properties, que armazena as propriedades
	 */
	private static Properties props = new Properties();

	static {
		try {
			if (!Files.exists(CONFIG_FILE)) {
				// Cria um novo arquivo, caso ele n�o exista
				Files.createFile(CONFIG_FILE);
			
			} else {
				try (InputStream in = Files.newInputStream(CONFIG_FILE)) {
					// Carrega o conte�do do arquivo para o Properties
					props.load(in);
				}
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * O construtor � privado para evitar a instancia��o de objetos da classe.
	 */
	private ConfigFile() {
	}
	
	/**
	 * Informa se exitem propriedades cadastradas
	 */
	public static boolean hasProperties() {
		return props.size() > 0;
	}

	/**
	 * Retorna o valor de uma propriedade
	 * @param name Nome da propriedade
	 * @return Valor da propriedade ou null se ela n�o existir.
	 */
	public static String getProperty(String name) {
		return props.getProperty(name);
	}

	/**
	 * Define o valor de uma propriedade
	 * @param name Nome da propriedade
	 * @param value Valor da propriedade
	 */
	public static void setProperty(String name, String value) {
		props.setProperty(name, value);
	}

	/**
	 * Salva as propriedades em arquivo
	 */
	public static void saveProperties() {
		try (OutputStream out = Files.newOutputStream(CONFIG_FILE)) {
			// Armazena o conte�do do Properties no arquivo (sobrescreve o arquivo atual)
			props.store(out, null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
