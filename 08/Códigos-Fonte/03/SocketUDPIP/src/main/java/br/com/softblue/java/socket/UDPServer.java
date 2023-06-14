package br.com.softblue.java.socket;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPServer {

	public static void main(String[] args) throws Exception {
		try (DatagramSocket socket = new DatagramSocket(2500)) {
			byte[] buffer = new byte[1024];

			DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
			System.out.println("Servidor: aguardando recebimento de datagrama...");
			socket.receive(packet);

			byte[] clientData = packet.getData();
			String clientDataStr = new String(clientData, 0, packet.getLength());

			System.out.println("Servidor: dado recebido do cliente foi " + clientDataStr);
		}
	}
}
