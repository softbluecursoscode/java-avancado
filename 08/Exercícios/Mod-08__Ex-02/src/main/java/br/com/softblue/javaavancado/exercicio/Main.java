package br.com.softblue.javaavancado.exercicio;


/**
 * Classe que lan�a a aplica��o
 */
public class Main {

	/**
	 * M�todo main().
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		//instancia o HTTP server e o inicia
		HTTPServer server = new HTTPServer();
		server.start();
	}
}
