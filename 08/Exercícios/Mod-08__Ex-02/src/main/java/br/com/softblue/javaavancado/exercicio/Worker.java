package br.com.softblue.javaavancado.exercicio;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Esta classe é responsável por atender a requisição de um cliente. Executa em uma thread separada.
 */
public class Worker implements Runnable {
	/**
	 * Socket entre o cliente e o servidor
	 */
	private Socket conn;
	
	/**
	 * Construtor
	 * @param conn Socket
	 */
	public Worker(Socket conn) {
		this.conn = conn;
	}

	/**
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		try {
			System.out.println("Thread iniciou atendimento à requisição");
			InputStream is = conn.getInputStream();
			OutputStream os = conn.getOutputStream();
			
			System.out.println("Fazendo parse da requisição...");
			Request request = Request.parseRequest(is);
			
			File file = request.getFile();
			System.out.println("Criando a resposta a ser enviada ao cliente...");
			Response response = Response.createResponse(os);
			
			if(!file.exists() || file.isDirectory()) {
				//se o arquivo não existir ou um diretório for requisitado, o código de retorno é 404
				response.setCode(404);
			} else {		
				response.setCode(200);
				response.setFile(request.getFile());
			}
			
			//envia a resposta
			response.send();
			
			//fecha a conexão com o cliente
			conn.close();
			System.out.println("A conexão foi fechada com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
