package br.com.softblue.javaavancado.exercicio;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Date;

/**
 * Representa uma resposta HTTP
 */
public class Response {
	/**
	 * Código do retorno
	 */
	private int code;

	/**
	 * Content-type da resposta. Indica o tipo de arquivo que está sendo enviado
	 * para o cliente.
	 */
	private String contentType;

	/**
	 * Arquivo enviado ao cliente
	 */
	private File file;

	private PrintStream ps;
	private OutputStream os;

	/**
	 * Construtor
	 * 
	 * @param os
	 *            Output stream do socket
	 */
	private Response(OutputStream os) {
		this.os = os;
		ps = new PrintStream(os);
	}

	/**
	 * Cria uma resposta HTTP
	 * 
	 * @param os
	 * @return
	 */
	public static Response createResponse(OutputStream os) {
		return new Response(os);
	}

	/**
	 * Atribui um código de retorno
	 * 
	 * @param code
	 *            Código de retorno
	 */
	public void setCode(int code) {
		this.code = code;
	}

	/**
	 * Atribui um arquivo de retorno
	 * 
	 * @param file
	 *            Arquivo de retorno
	 */
	public void setFile(File file) {
		this.file = file;
	}

	/**
	 * Envia a resposta HTTP ao cliente
	 * 
	 * @throws HTTPServerException
	 */
	public void send() throws HTTPServerException {
		try {
			System.out.println("Envio de resposta ao cliente...");
			
			// começa a montagem da resposta HTTP. O formato desta resposta pode
			// ser vista na RFC 2616
			ps.print("HTTP/1.0 " + code);

			System.out.println("Enviando código " + code + "...");

			// considera apenas dois códigos de resposta: 200 e 404
			if (code == 200) {
				this.contentType = Util.getContentType(file.getName());
				ps.print(" OK");
				ps.print("\r\n");
				ps.print("Content-Type: " + Util.getContentType(file.getName())
						+ "\r\n");
				System.out.println("Content-type '" + contentType + "'");
			} else if (code == 404) {
				ps.print("Not Found");
				ps.print("\r\n");
			}

			ps.print("Date: " + new Date() + "\r\n");
			ps.print("Server: " + Configuration.SERVER_NAME + "\r\n\r\n");

			// o código abaixo é usado para escrever o conteúdo do arquivo na
			// output stream do socket
			if (file != null) {
				System.out.println("Enviando arquivo '" + file.getPath() + "'...");
				try (FileInputStream fis = new FileInputStream(file)) {
					byte[] buffer = new byte[1024];
					while (fis.available() > 0) {
						os.write(buffer, 0, fis.read(buffer));
					}

					os.flush();
				}
			}

			System.out.println("A resposta foi enviada ao cliente com sucesso!");

		} catch (IOException e) {
			throw new HTTPServerException(e);
		}
	}

	/**
	 * Retorna o código de retorno
	 * 
	 * @return Código de retorno
	 */
	public int getCode() {
		return code;
	}

	/**
	 * Retorna o content-type
	 * 
	 * @return Content-type
	 */
	public String getContentType() {
		return contentType;
	}

	/**
	 * Retorna o arquivo que será enviado via HTTP
	 * 
	 * @return Arquivo
	 */
	public File getFile() {
		return file;
	}
}
