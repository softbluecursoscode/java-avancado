package br.com.softblue.javaavancado.exercicio;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Representa uma requisi��o HTTP
 */
public class Request {
	/**
	 * Tipo da requisi��o
	 */
	private String type;
	
	/**
	 * Arquivo requisitado
	 */
	private File file;
	
	/**
	 * Vers�o do protocolo
	 */
	private String protocolVersion;

	/**
	 * Construtor.
	 * Realiza o parse da requisi��o HTTP
	 * @param is Input stream do socket
	 * @throws HTTPServerException
	 */
	private Request(InputStream is) throws HTTPServerException {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			
			//l� a primeira linha da request
			String request = reader.readLine();

			//este loop serve apenas para ler todas as outras linhas da requisi��o. Os dados s�o descartados
			while (true) {
				String misc = reader.readLine();
				if (misc == null || misc.length() == 0) {
					break;
				}
			}

			//o m�todo split divide a texto em tokens
			String[] tokens = request.split(" ");

			//obt�m o caminho dos documentos que ser�o entregues via HTTP
			String docRoot = Configuration.DOC_ROOT;

			//atribui os dados da requisi��o aos respectivos atributos
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
