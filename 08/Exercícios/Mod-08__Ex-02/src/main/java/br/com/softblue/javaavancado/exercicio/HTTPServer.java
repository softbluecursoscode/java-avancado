package br.com.softblue.javaavancado.exercicio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Representa o servidor HTTP. A especificação oficial do protocolo HTTP pode ser encontrada em 
 * http://www.ietf.org/rfc/rfc2616.txt (RFC 2616). A ideia deste exemplo é implementar apenas um subconjunto 
 * do protocolo de forma bem simplificada.
 */
public class HTTPServer {
	
	/**
	 * Inicia o servidor HTTP
	 * @throws HTTPServerException
	 */
	@SuppressWarnings("resource")
	public void start() throws HTTPServerException {
		System.out.println("Iniciando servidor...");
		try {
			//cria um socket em uma determinada porta
			ServerSocket socket = new ServerSocket(Configuration.PORT);
			
			System.out.println("Servidor aberto na porta " + Configuration.PORT);
			
			while(true) {
				System.out.println("Aguardando requisições...");
				
				//aguarda requisições dos clientes
				Socket conn = socket.accept();
				System.out.println("Conexão estabelecida. Criando thread para atender a requisição do cliente...");
				
				/*
				 * O cliente é atendido por uma instância de Worker, que é uma thread. A implementação é 
				 * feita desta forma para que o servidor possa atender várias requisições simultaneamente.
				 * Enquanto uma thread específica atende a requisição do cliente, o servidor volta a aguardar
				 * novas requisições. 
				 */
				Worker w = new Worker(conn);
				new Thread(w).start();
			}
		} catch (IOException e) {
			throw new HTTPServerException(e);
		}
	}
}
