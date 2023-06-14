package br.com.softblue.bluekeeper.dao;

import java.util.Base64;
import java.util.List;

import br.com.softblue.bluekeeper.model.SenhaServico;
import br.com.softblue.bluekeeper.util.CryptoException;
import br.com.softblue.bluekeeper.util.CryptoUtils;

/**
 * Interface de DAO, para acesso e armazenamento das informações da aplicação
 */
public interface SenhaServicoDAO {
	/**
	 * Chave simétrica para ser usada na criptografia
	 */
	final byte[] SECRET_KEY = "LDJGOGDLKJFSDYFK".getBytes();

	/**
	 * Retorna todas as senhas de serviço armazenadas
	 * @return Todas as senhas de serviço cadastradas
	 */
	List<SenhaServico> load();
	
	/**
	 * Armazena todas as senhas de serviço passadas como parâmetro. Este método substitui
	 * senhas de serviço já cadastradas pelas novas fornecidas.
	 * @param senhasServico Senhas de serviço a serem armazenadas
	 */
	void store(List<SenhaServico> senhasServico);
	
	/**
	 * Retorna senhas de serviço cujo nome do serviço ou site ou login contenham o texto
	 * passado como parâmetro
	 * @param text Texto para filtrar os resultados da lista
	 * @return Senhas de serviço filtradas
	 */
	List<SenhaServico> filter(String text);
	
	/**
	 * Gera um novo ID para uma senha de serviço
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
			
			// Converte para codificação Base64
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
			// Lê os bytes
			byte[] base64Bytes = senha.getBytes();
			
			// Decodifica o padrão Base64
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
