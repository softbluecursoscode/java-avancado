package br.com.softblue.javaavancado.exercicio;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Representa uma requisição HTTP
 */
public class Request {
	/**
	 * Tipo da requisição
	 */
	private String type;
	
	/**
	 * Arquivo requisitado
	 */
	private File file;
	
	/**
	 * Versão do protocolo
	 */
	private String protocolVersion;

	/**
	 * Construtor.
	 * Realiza o parse da requisição HTTP
	 * @param is Input stream do socket
	 * @throws HTTPServerException
	 */
	private Request(InputStream is) throws HTTPServerException {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			
			//lê a primeira linha da request
			String request = reader.readLine();

			//este loop serve apenas para ler todas as outras linhas da requisição. Os dados são descartados
			while (true) {
				String misc = reader.readLine();
				if (misc == null || misc.length() == 0) {
					break;
				}
			}

			//o método split divide a texto em tokens
			String[] tokens = request.split(" ");

			//obtém o caminho dos documentos que serão entregues via HTTP
			String docRoot = Configuration.DOC_ROOT;

			//atribui os dados da requisição aos respectivos atributos
			this.type = tokens[0];
			this.file = new File(docRoot + "/" + tokens[1]);
			this.protocolVersion = tokens[2];

			System.out.println("Request recebida: " + request);
		} catch (IOException e) {
			throw new HTTPServerException(e);
		}
	}

	public static Request parseRequest(InputStream is) throws HTTPServerException {
		return new Request(is);
	}

	public String getType() {
		return type;
	}

	public File getFile() {
		return file;
	}

	public String getProtocolVersion() {
		return protocolVersion;
	}

}
