package br.com.softblue.javaavancado.exercicio.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAO {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/cddb?useSSL=false&&serverTimezone=UTC";
	private static final String DB_USER = "root";
	private static final String DB_PWD = "sa";

	private Connection conn;

	protected Connection getConnection() throws DAOException {
		try {
			if (conn == null) {
				Class.forName("com.mysql.jdbc.Driver");
				this.conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
			}

			return conn;
		} catch (ClassNotFoundException e) {
			throw new DAOException(e);
		} catch (SQLException e) {
			throw new DAOException();
		}
	}

	protected void closeConnection() throws DAOException {
		try {
			if (conn != null) {
				conn.close();
				conn = null;
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}
}
