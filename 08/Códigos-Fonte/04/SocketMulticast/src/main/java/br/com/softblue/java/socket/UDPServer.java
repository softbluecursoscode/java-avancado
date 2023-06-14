package br.com.softblue.java.socket;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class UDPServer {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws Exception {
		InetAddress addr = InetAddress.getByName("228.5.6.7");
		
		try (MulticastSocket socket = new MulticastSocket(2500)) {
			socket.joinGroup(addr);
			byte[] buffer = new byte[1024];

			DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
			System.out.println("Servidor: aguardando recebimento de datagrama...");
			socket.receive(packet);

			byte[] clientData = packet.getData();
			String clientDataStr = new String(clientData, 0, packet.getLength());

			System.out.println("Servidor: dado recebido do cliente foi " + clientDataStr);
			
			socket.leaveGroup(addr);
		}
	}
}
