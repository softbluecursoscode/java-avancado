package br.com.softblue.java.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class TCPClient {

	public static void main(String[] args) throws Exception {
		try (Socket socket = new Socket("localhost", 1050)) {
			System.out.println("Cliente: conectado ao servidor");
			int valor = 5;

			OutputStream socketOut = socket.getOutputStream();
			DataOutputStream dout = new DataOutputStream(socketOut);
			dout.writeInt(valor);
			System.out.println("Cliente: valor " + valor + " enviado para o servidor");

			InputStream socketIn = socket.getInputStream();
			DataInputStream din = new DataInputStream(socketIn);
			int valorIncrementado = din.readInt();

			System.out.println("Cliente: recebido do servidor o valor " + valorIncrementado);
		}
	}
}
