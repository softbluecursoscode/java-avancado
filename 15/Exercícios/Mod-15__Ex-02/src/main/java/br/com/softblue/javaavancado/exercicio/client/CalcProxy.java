package br.com.softblue.javaavancado.exercicio.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import br.com.softblue.javaavancado.exercicio.common.Calc;
import br.com.softblue.javaavancado.exercicio.common.CalcException;
import br.com.softblue.javaavancado.exercicio.common.Request;
import br.com.softblue.javaavancado.exercicio.common.Response;
import br.com.softblue.javaavancado.exercicio.common.Request.Op;

/**
 * Classe que faz o papel de proxy para as chamadas remotas de operações de {@link Calc}
 */
public class CalcProxy implements Calc {

	/**
	 * Socket de comunicação com o servidor 
	 */
	private Socket socket;
	
	/**
	 * Canal de entrada de dados 
	 */
	private DataInputStream dis;
	
	/**
	 * Canal de saída de dados
	 */
	private DataOutputStream dos;
	
	/**
	 * Inicializa a conexão com o servidor
	 * @throws CalcException
	 */
	private void initSocket() throws CalcException {
		try {
			// Cria um socket com o servidor
			socket = new Socket("localhost", 3434);
			
			// Armazena referências aos canais de entrada e saída de dados
			dis = new DataInputStream(socket.getInputStream());
			dos = new DataOutputStream(socket.getOutputStream());
		
		} catch (Exception e) {
			throw new CalcException(e);
		}
	}
	
	/**
	 * Finaliza a conexão com o servidor
	 * @throws CalcException
	 */
	private void closeSocket() throws CalcException {
		try {
			// Fecha o socket
			if (socket != null) {
				socket.close();
			}
		} catch (IOException e) {
			throw new CalcException(e);
		}
	}
	
	/**
	 * Invoca a operação no servidor
	 * @param req Requisição a ser enviada
	 * @return Resposta obtida do servidor
	 * @throws CalcException
	 */
	private Response invoke(Request req) throws CalcException {
		try {
			System.out.println("Req: " + req.toXML());
			
			// Envia para o servidor a requisição no formato XML
			dos.writeUTF(req.toXML());
			
			
			// Lê do servidor a resposta, também no formato XML
			String xmlResponse = dis.readUTF();
			
			System.out.println("Resp: " + xmlResponse);
			
			// Cria um objeto Response com base no XML retornado
			return Response.fromXML(xmlResponse);
		} catch (IOException e) {
			throw new CalcException(e);
		}
	}

	/**
	 * Invoca a operação de soma: inicia a conexão, envia a requisição ao servidor, obtém a resposta
	 * e retorna o resultado final.
	 * @see br.com.softblue.javaavancado.exercicio.common.Calc#somar(double, double)
	 */
	@Override
	public double somar(double n1, double n2) throws CalcException {
		initSocket();
		try {
			Request req = Request.fromData(Op.SOMAR, n1, n2);
			Response resp = invoke(req);
			return resp.getResultado();
		} finally {
			closeSocket();
		}
	}

	/**
	 * Invoca a operação de subtração: inicia a conexão, envia a requisição ao servidor, obtém a resposta
	 * e retorna o resultado final.
	 * @see br.com.softblue.javaavancado.exercicio.common.Calc#subtrair(double, double)
	 */
	@Override
	public double subtrair(double n1, double n2) throws CalcException {
		initSocket();
		try {
			Request req = Request.fromData(Op.SUBTRAIR, n1, n2);
			Response resp = invoke(req);
			return resp.getResultado();
		} finally {
			closeSocket();
		}
	}

	/**
	 * Invoca a operação de multiplicação: inicia a conexão, envia a requisição ao servidor, obtém a resposta
	 * e retorna o resultado final.
	 * @see br.com.softblue.javaavancado.exercicio.common.Calc#multiplicar(double, double)
	 */
	@Override
	public double multiplicar(double n1, double n2) throws CalcException {
		initSocket();
		try {
			Request req = Request.fromData(Op.MULTIPLICAR, n1, n2);
			Response resp = invoke(req);
			return resp.getResultado();
		} finally {
			closeSocket();
		}
	}

	/**
	 * Invoca a operação de divisão: inicia a conexão, envia a requisição ao servidor, obtém a resposta
	 * e retorna o resultado final.
	 * @see br.com.softblue.javaavancado.exercicio.common.Calc#dividir(double, double)
	 */
	@Override
	public double dividir(double n1, double n2) throws CalcException {
		initSocket();
		try {
			Request req = Request.fromData(Op.DIVIDIR, n1, n2);
			Response resp = invoke(req);
			return resp.getResultado();
		} finally {
			closeSocket();
		}
	}
}
