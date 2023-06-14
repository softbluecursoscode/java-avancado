package br.com.softblue.bluekeeper.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * Utilit�rios de criptografia
 */
public class CryptoUtils {

	/**
	 * Criptografa texto usando o algoritmo AES
	 * @param keyBytes Bytes da chave sim�trica
	 * @param dataBytes Bytes dos dados
	 * @return Bytes criptografados
	 */
	public static byte[] encryptAES(byte[] keyBytes, byte[] dataBytes) {
		return handleAES(keyBytes, dataBytes, Cipher.ENCRYPT_MODE);
	}
	
	/**
	 * Descriptografa texto usando o algoritmo AES
	 * @param keyBytes Bytes da chave sim�trica
	 * @param dataBytes Bytes dos dados
	 * @return Bytes descriptografados
	 */
	public static byte[] decryptAES(byte[] keyBytes, byte[] dataBytes) {
		return handleAES(keyBytes, dataBytes, Cipher.DECRYPT_MODE);
	}
	
	/**
	 * Criptografa ou descriptografa texto usando o algoritmo AES
	 * @param keyBytes Bytes da chave sim�trica
	 * @param dataBytes Bytes dos dados
	 * @param mode Cipher.ENCRYPT_MODE ou Cipher.DECRYPT_MODE
	 * @return Bytes criptografados ou descriptografados
	 */
	private static byte[] handleAES(byte[] keyBytes, byte[] dataBytes, int mode) {
		// Verifica se os bytes s�o v�lidos
		if (keyBytes == null || keyBytes.length != 16) {
			throw new CryptoException("A chave � inv�lida");
		}
		
		// Verifica se h� dados
		if (dataBytes == null) {
			throw new CryptoException("N�o existem dados");
		}
		
		try {
			// Cria o objeto da chave sim�trica
			SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");
			
			// Obt�m a inst�ncia do AES
			Cipher cipher = Cipher.getInstance("AES");
			
			// Inicializa o algoritmo
			cipher.init(mode, key);
			
			// Executa a opera��o de acordo com o mode escolhido
			return cipher.doFinal(dataBytes);
		
		} catch (Exception e) {
			throw new CryptoException(e);
		}
	}
}
