package br.com.softblue.java.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Main {

	public static void main(String[] args) throws Exception {

		String url = "jdbc:mysql://localhost/softblue?useSSL=false&serverTimezone=UTC";

		try (Connection conn = DriverManager.getConnection(url, "root", "root")) {
			System.out.println("Conectou no banco de dados");

			String sql1 = "INSERT INTO PRODUTO (ID, NOME) VALUES (?, ?)";
			String sql2 = "INSERT INTO ITEM (PRODUTO_ID, PRECO) VALUES (?, ?)";

			try (PreparedStatement stmt1 = conn.prepareStatement(sql1);
				 PreparedStatement stmt2 = conn.prepareStatement(sql2)) {
				
				conn.setAutoCommit(false);
				
				try {
					stmt1.setInt(1, 1);
					stmt1.setString(2, "Feijão");
					stmt1.executeUpdate();
					
					stmt2.setInt(1, 1);
					stmt2.setDouble(2, 3.5);
					stmt2.executeUpdate();
					
					stmt2.setInt(1, 1);
					stmt2.setDouble(2, 4.7);
					stmt2.executeUpdate();
					
					conn.commit();
				
				} catch (Exception e) {
					conn.rollback();
					throw e;
				}
			}
		}
	}
}
