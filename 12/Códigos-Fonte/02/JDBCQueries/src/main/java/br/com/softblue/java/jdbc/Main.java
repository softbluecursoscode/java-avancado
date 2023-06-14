package br.com.softblue.java.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Main {

	public static void main(String[] args) throws Exception {

		String[] nomesProdutos = { "Feijão", "Batata", "Cebola", "Tomate" };

		String url = "jdbc:mysql://localhost/softblue?useSSL=false&serverTimezone=UTC";

		try (Connection conn = DriverManager.getConnection(url, "root", "root")) {
			System.out.println("Conectou no banco de dados");

			// String sql = "INSERT INTO PRODUTO (NOME) VALUES ('Arroz')";
			//
			// try (Statement stmt = conn.createStatement()) {
			// stmt.executeUpdate(sql);
			// System.out.println("Produto inserido");
			// }

			try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO PRODUTO (NOME) VALUES (?)")) {
				for (String nomeProduto : nomesProdutos) {
					stmt.setString(1, nomeProduto);
					stmt.executeUpdate();
				}
			}
		}
	}
}
