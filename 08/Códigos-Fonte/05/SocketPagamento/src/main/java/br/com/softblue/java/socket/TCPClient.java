package br.com.softblue.java.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class TCPClient {

	public static void main(String[] args) throws Exception {
		try (Socket socket = new Socket("localhost", 2323)) {
			System.out.println("Cliente conectado ao servidor");

			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			int qtde = 2;
			
			out.println("PAGAMENTO");
			out.println(qtde);
			
			for (int i = 0; i < qtde; i++) {
				String status = enviarPagamento("José da Silva", "3215665389488674", YearMonth.of(2035, 11), 1, 345.67, in, out);
				
				if (status == null) {
					System.out.println("Pagamento feito com sucesso!");
				} else {
					System.out.println("Erro: " + status);
				}
			}
		}
	}
	
	private static String enviarPagamento(String nome, String numeroCartao, YearMonth dataValidadeCartao, int parcelas, double valor, BufferedReader in, PrintWriter out) throws IOException {
		out.println(String.format("%s;%s;%s;%d;%s", nome, numeroCartao, dataValidadeCartao.format(DateTimeFormatter.ofPattern("MM/yyyy")), parcelas, String.valueOf(valor)));
		
		String status = in.readLine();

		if (status.equals("OK")) {
			return null;
		} else {
			return status;
		}
	}
}
