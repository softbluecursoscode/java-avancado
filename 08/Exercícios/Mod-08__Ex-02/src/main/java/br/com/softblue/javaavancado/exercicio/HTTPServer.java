package br.com.softblue.javaavancado.exercicio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Representa o servidor HTTP. A especifica��o oficial do protocolo HTTP pode ser encontrada em 
 * http://www.ietf.org/rfc/rfc2616.txt (RFC 2616). A ideia deste exemplo � implementar apenas um subconjunto 
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
				System.out.println("Aguardando requisi��es...");
				
				//aguarda requisi��es dos clientes
				Socket conn = socket.accept();
				System.out.println("Conex�o estabelecida. Criando thread para atender a requisi��o do cliente...");
				
				/*
				 * O cliente � atendido por uma inst�ncia de Worker, que � uma thread. A implementa��o � 
				 * feita desta forma para que o servidor possa atender v�rias requisi��es simultaneamente.
				 * Enquanto uma thread espec�fica atende a requisi��o do cliente, o servidor volta a aguardar
				 * novas requisi��es. 
				 */
				Worker w = new Worker(conn);
				new Thread(w).start();
			}
		} catch (IOException e) {
			throw new HTTPServerException(e);
		}
	}
}
