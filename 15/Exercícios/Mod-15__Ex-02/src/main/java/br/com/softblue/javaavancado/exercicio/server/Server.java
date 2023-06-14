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
 * Servidor que aguarda requisições remotas de clientes
 */
public class Server {
	
	/**
	 * Objeto Calc, utilizado para executar as operações no servidor
	 */
	private Calc calc;

	/**
	 * Método main()
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

		// Fica em loop, aguardando requisições
		while (true) {
			// Aguarda a requisição de um cliente
			Socket clientSocket = serverSocket.accept();

			// Obtém os canais de leitura e escrita do socket
			DataInputStream dis = new DataInputStream(clientSocket.getInputStream());
			DataOutputStream dos = new DataOutputStream(clientSocket.getOutputStream());
			
			// Lê a requisição do cliente, que virá no formato XML
			String xmlRequest = dis.readUTF();

			// Cria um objeto Request a partir do XML
			Request req = Request.fromXML(xmlRequest);
			
			// Invoca a operação desejada, o que gera um objeto Response como resultado
			Response resp = invoke(req);
			
			// Converte o objeto Response para o formato XML
			String xmlResponse = resp.toXML();
			
			// Envia a resposta para o cliente, também no formato XML
			dos.writeUTF(xmlResponse);
		}
	}
	
	/**
	 * Invoca uma operação, que é definida com base no dados da requisição.
	 * @param req Requisição do cliente
	 * @return Objeto Response, que representa a resposta gerada
	 * @throws CalcException
	 */
	private Response invoke(Request req) throws CalcException {
		double resultado;
		
		// Com base na operação solicitada, chama o método correspondente de CalcImpl
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
		
		// Cria o objeto Response contendo o resultado da operação
		return Response.fromData(resultado);
	}
}
