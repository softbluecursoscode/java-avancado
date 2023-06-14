package br.com.softblue.java.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Main {

	public static void main(String[] args) throws Exception {

		String url = "jdbc:mysql://localhost/softblue?useSSL=false&serverTimezone=UTC";

		try (Connection conn = DriverManager.getConnection(url, "root", "root")) {
			System.out.println("Conectou no banco de dados");

			String sql = "SELECT ID, NOME FROM PRODUTO";

			try (PreparedStatement stmt = conn.prepareStatement(sql)) {
				try (ResultSet rs = stmt.executeQuery()) {
					while (rs.next()) {
						int id = rs.getInt("ID");
						String nome = rs.getString("NOME");

						System.out.println("ID = " + id);
						System.out.println("Nome = " + nome);
						System.out.println("=========================");
					}
				}
			}
		}
	}
}
