package br.com.softblue.javaavancado.exercicio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Gerenciador de prefer�ncias de aplica��es utilizando arquivos de configura��o.
 */
public class Config {

	/**
	 * Objeto que vai armazenar as chaves e valores
	 */
	private Properties props;
	
	/**
	 * Arquivo de configura��o associado
	 */
	private File file;
	
	/**
	 * Recebe uma String com um caminho de arquivo. Se n�o existir, ele ser� criado.
	 * @param file Arquivo de configura��o
	 * @throws IOException
	 */
	public Config(String file) throws IOException {
		this(new File(file));
	}
	
	/**
	 * Recebe um objeto File que representa um arquivo. Se n�o existir, ele ser� criado.
	 * @param file Arquivo de configura��o
	 * @throws IOException
	 */
	public Config(File file) throws IOException {
		// File n�o pode ser null
		if (file == null) {
			throw new IllegalArgumentException("File cannot be null");
		}
		
		this.file = file;
		this.props = new Properties();
		
		if (file.exists()) {
			// Se o arquivo existe, l� seu conte�do para o Properties
			FileInputStream fis = null;
			try {
				fis = new FileInputStream(file);
				props.load(fis);
			
			} finally {
				fis.close();
			}
		
		} else {
			// Se o arquivo n�o existe, cria
			file.createNewFile();
		}
	}
	
	/**
	 * Retorna uma propriedade do tipo String
	 * @param name nome
	 * @return Valor da propriedade
	 */
	public String getPropertyAsString(String name) {
		return props.getProperty(name);
	}
	
	/**
	 * Retorna uma propriedade do tipo Integer
	 * @param name nome
	 * @return Valor da propriedade
	 */
	public Integer getPropertyAsInteger(String name) {
		String prop = props.getProperty(name);
		
		if (prop == null) {
			return null;
		}
		
		return Integer.valueOf(prop);
	}
	
	/**
	 * Retorna uma propriedade do tipo Boolean
	 * @param name nome
	 * @return Valor da propriedade
	 */
	public Boolean getPropertyAsBoolean(String name) {
		String prop = props.getProperty(name);
		
		if (prop == null) {
			return null;
		}
		
		return Boolean.valueOf(prop);
	}
	
	/**
	 * Define uma propriedade do tipo String
	 * @param name Nome da propriedade
	 * @param value Valor associado � propriedade
	 * @throws IOException
	 */
	public void setProperty(String name, String value) throws IOException {
		// Este m�todo � chamado pelos outros m�todos do tipo setProperty*()
		
		props.setProperty(name, value);
		
		// Depois de inserir a propriedade no Properties, salva o Properties no arquivo
		FileOutputStream fos = null;
		fos = new FileOutputStream(file);
		props.store(fos, null);
	}
	
	/**
	 * Define uma propriedade do tipo Integer
	 * @param name Nome da propriedade
	 * @param value Valor associado � propriedade
	 * @throws IOException
	 */
	public void setProperty(String name, Integer value) throws IOException {
		setProperty(name, value.toString());
	}
	
	/**
	 * Define uma propriedade do tipo Double
	 * @param name Nome da propriedade
	 * @param value Valor associado � propriedade
	 * @throws IOException
	 */
	public void setProperty(String name, Double value) throws IOException {
		setProperty(name, value.toString());
	}
	
	/**
	 * Define uma propriedade do tipo Boolean
	 * @param name Nome da propriedade
	 * @param value Valor associado � propriedade
	 * @throws IOException
	 */
	public void setProperty(String name, Boolean value) throws IOException {
		setProperty(name, value.toString());
	}
}
