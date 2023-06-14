package br.com.softblue.bluekeeper.dao;

import java.util.Base64;
import java.util.List;

import br.com.softblue.bluekeeper.model.SenhaServico;
import br.com.softblue.bluekeeper.util.CryptoException;
import br.com.softblue.bluekeeper.util.CryptoUtils;

/**
 * Interface de DAO, para acesso e armazenamento das informa��es da aplica��o
 */
public interface SenhaServicoDAO {
	/**
	 * Chave sim�trica para ser usada na criptografia
	 */
	final byte[] SECRET_KEY = "LDJGOGDLKJFSDYFK".getBytes();

	/**
	 * Retorna todas as senhas de servi�o armazenadas
	 * @return Todas as senhas de servi�o cadastradas
	 */
	List<SenhaServico> load();
	
	/**
	 * Armazena todas as senhas de servi�o passadas como par�metro. Este m�todo substitui
	 * senhas de servi�o j� cadastradas pelas novas fornecidas.
	 * @param senhasServico Senhas de servi�o a serem armazenadas
	 */
	void store(List<SenhaServico> senhasServico);
	
	/**
	 * Retorna senhas de servi�o cujo nome do servi�o ou site ou login contenham o texto
	 * passado como par�metro
	 * @param text Texto para filtrar os resultados da lista
	 * @return Senhas de servi�o filtradas
	 */
	List<SenhaServico> filter(String text);
	
	/**
	 * Gera um novo ID para uma senha de servi�o
	 * @return ID gerado
	 */
	public int generateId();
	
	/**
	 * Criptografa uma senha
	 * @param senha Senha a ser criptografada
	 * @return Senha criptografada
	 */
	default String encrypt(String senha) {
		try {
			// Criptografa
			byte[] encBytes = CryptoUtils.encryptAES(SECRET_KEY, senha.getBytes());
			
			// Converte para codifica��o Base64
			byte[] base64Bytes = Base64.getEncoder().encode(encBytes);
			
			// Retorna como String
			return new String(base64Bytes);

		} catch (Exception e) {
			throw new CryptoException(e);
		}
	}

	/**
	 * Descriptografa um senha
	 * @param senha Senha a ser descriptografada
	 * @return Senha descriptografada
	 */
	default String decrypt(String senha) {
		try {
			// L� os bytes
			byte[] base64Bytes = senha.getBytes();
			
			// Decodifica o padr�o Base64
			byte[] encBytes = Base64.getDecoder().decode(base64Bytes);
			
			// Descriptografa
			byte[] decBytes = CryptoUtils.decryptAES(SECRET_KEY, encBytes);
			
			// Retorna como String
			return new String(decBytes);
		
		} catch (Exception e) {
			throw new CryptoException(e);
		}
	}
}
