package br.com.softblue.javaavancado.exercicio.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import br.com.softblue.javaavancado.exercicio.common.Calc;
import br.com.softblue.javaavancado.exercicio.common.CalcException;
import br.com.softblue.javaavancado.exercicio.common.Request;
import br.com.softblue.javaavancado.exercicio.common.Response;

/**
 * Servidor que aguarda requisi��es remotas de clientes
 */
public class Server {
	
	/**
	 * Objeto Calc, utilizado para executar as opera��es no servidor
	 */
	private Calc calc;

	/**
	 * M�todo main()
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		new Server().start();
	}

	/**
	 * Inicia o servidor
	 * @throws Exception
	 */
	@SuppressWarnings("resource")
	public void start() throws Exception {
		// Abre um socket na porta 3434
		ServerSocket serverSocket = new ServerSocket(3434);
		
		// Cria um objeto CalcImpl
		calc = new CalcImpl();

		// Fica em loop, aguardando requisi��es
		while (true) {
			// Aguarda a requisi��o de um cliente
			Socket clientSocket = serverSocket.accept();

			// Obt�m os canais de leitura e escrita do socket
			DataInputStream dis = new DataInputStream(clientSocket.getInputStream());
			DataOutputStream dos = new DataOutputStream(clientSocket.getOutputStream());
			
			// L� a requisi��o do cliente, que vir� no formato XML
			String xmlRequest = dis.readUTF();

			// Cria um objeto Request a partir do XML
			Request req = Request.fromXML(xmlRequest);
			
			// Invoca a opera��o desejada, o que gera um objeto Response como resultado
			Response resp = invoke(req);
			
			// Converte o objeto Response para o formato XML
			String xmlResponse = resp.toXML();
			
			// Envia a resposta para o cliente, tamb�m no formato XML
			dos.writeUTF(xmlResponse);
		}
	}
	
	/**
	 * Invoca uma opera��o, que � definida com base no dados da requisi��o.
	 * @param req Requisi��o do cliente
	 * @return Objeto Response, que representa a resposta gerada
	 * @throws CalcException
	 */
	private Response invoke(Request req) throws CalcException {
		double resultado;
		
		// Com base na opera��o solicitada, chama o m�todo correspondente de CalcImpl
		switch (req.getOperacao()) {
		case SOMAR:
			resultado = calc.somar(req.getN1(), req.getN2());
			break;
		case SUBTRAIR:
			resultado = calc.subtrair(req.getN1(), req.getN2());
			break;
		case MULTIPLICAR:
			resultado = calc.multiplicar(req.getN1(), req.getN2());
			break;
		case DIVIDIR:
			resultado = calc.dividir(req.getN1(), req.getN2());
			break;
		default:
			return null;
		}
		
		// Cria o objeto Response contendo o resultado da opera��o
		return Response.fromData(resultado);
	}
}
