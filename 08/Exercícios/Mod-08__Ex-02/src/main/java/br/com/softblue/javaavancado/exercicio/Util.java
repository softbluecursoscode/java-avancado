package br.com.softblue.javaavancado.exercicio;

/**
 * Classe com utilitários
 */
public class Util {

	/**
	 * Retorna o content-type com base no nome do arquivo. O content-type é decidido com base na extensão
	 * do arquivo
	 * @param fileName
	 * @return
	 */
	public static String getContentType(String fileName) {
		if (fileName.endsWith(".html") || fileName.endsWith(".htm")) {
			return "text/html";
		} else if (fileName.endsWith(".gif")) {
			return "image/gif";
		} else if (fileName.endsWith(".png")) {
			return "image/x-png";
		} else if (fileName.endsWith(".jpg") || fileName.endsWith(".jpeg")) {
			return "image/jpeg";
		} else {
			return "text/plain";
		}
	}
}
