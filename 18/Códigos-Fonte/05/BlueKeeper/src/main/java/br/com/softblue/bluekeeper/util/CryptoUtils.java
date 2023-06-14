package br.com.softblue.bluekeeper.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * Utilitários de criptografia
 */
public class CryptoUtils {

	/**
	 * Criptografa texto usando o algoritmo AES
	 * @param keyBytes Bytes da chave simétrica
	 * @param dataBytes Bytes dos dados
	 * @return Bytes criptografados
	 */
	public static byte[] encryptAES(byte[] keyBytes, byte[] dataBytes) {
		return handleAES(keyBytes, dataBytes, Cipher.ENCRYPT_MODE);
	}
	
	/**
	 * Descriptografa texto usando o algoritmo AES
	 * @param keyBytes Bytes da chave simétrica
	 * @param dataBytes Bytes dos dados
	 * @return Bytes descriptografados
	 */
	public static byte[] decryptAES(byte[] keyBytes, byte[] dataBytes) {
		return handleAES(keyBytes, dataBytes, Cipher.DECRYPT_MODE);
	}
	
	/**
	 * Criptografa ou descriptografa texto usando o algoritmo AES
	 * @param keyBytes Bytes da chave simétrica
	 * @param dataBytes Bytes dos dados
	 * @param mode Cipher.ENCRYPT_MODE ou Cipher.DECRYPT_MODE
	 * @return Bytes criptografados ou descriptografados
	 */
	private static byte[] handleAES(byte[] keyBytes, byte[] dataBytes, int mode) {
		// Verifica se os bytes são válidos
		if (keyBytes == null || keyBytes.length != 16) {
			throw new CryptoException("A chave é inválida");
		}
		
		// Verifica se há dados
		if (dataBytes == null) {
			throw new CryptoException("Não existem dados");
		}
		
		try {
			// Cria o objeto da chave simétrica
			SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");
			
			// Obtém a instância do AES
			Cipher cipher = Cipher.getInstance("AES");
			
			// Inicializa o algoritmo
			cipher.init(mode, key);
			
			// Executa a operação de acordo com o mode escolhido
			return cipher.doFinal(dataBytes);
		
		} catch (Exception e) {
			throw new CryptoException(e);
		}
	}
}
