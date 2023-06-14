package br.com.softblue.java.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Main {

	public static void main(String[] args) throws Exception {

		String url = "jdbc:mysql://localhost/softblue?useSSL=false&serverTimezone=UTC";

		try (Connection conn = DriverManager.getConnection(url, "root", "root")) {
			System.out.println("Conectou no banco de dados");

			String sql = "INSERT INTO PRODUTO (NOME) VALUES (?)";

			try (PreparedStatement stmt = conn.prepareStatement(sql)) {
				for (int i = 1; i <= 1000; i++) {
					stmt.setString(1, "Produto " + i);
					stmt.addBatch();
				}
				
				stmt.executeBatch();
			}
		}
	}
}
