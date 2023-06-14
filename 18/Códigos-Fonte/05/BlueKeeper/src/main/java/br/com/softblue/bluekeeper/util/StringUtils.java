package br.com.softblue.bluekeeper.util;

public class StringUtils {

	/**
	 * Verifica se uma String está vazia ou é composta só por espaços em branco
	 * @param str
	 * @return
	 */
	public static final boolean isEmpty(String str) {
		if (str == null) {
			return true;
		}
		
		return str.trim().length() == 0;
	}
	
	/**
	 * Retorna o separador de linha do S.O.
	 * @return
	 */
	public static String newLine() {
		return System.getProperty("line.separator");
	}
}
