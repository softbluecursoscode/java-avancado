package br.com.softblue.javaavancado.exercicio;


/**
 * Classe que lança a aplicação
 */
public class Main {

	/**
	 * Método main().
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		//instancia o HTTP server e o inicia
		HTTPServer server = new HTTPServer();
		server.start();
	}
}
