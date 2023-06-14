package br.com.softblue.javaavancado.exercicio.dao;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.softblue.javaavancado.exercicio.entity.CD;
import br.com.softblue.javaavancado.exercicio.entity.Categoria;

public class CDDAO extends DAO {

	public void create(CD cd) throws DAOException {
		String sql = "INSERT INTO cd (nome, categoria, conteudo) VALUES (?, ?, ?)";
		
		PreparedStatement stmt = null;
		try {
			stmt = getConnection().prepareStatement(sql);
			
			stmt.setString(1, cd.getNome());
			stmt.setString(2, cd.getCategoria().toString());
			stmt.setString(3, cd.getConteudo());
			
			if (stmt.executeUpdate() < 1) {
				throw new DAOException("O registro não foi inserido");
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					throw new DAOException(e);
				}
			}
			closeConnection();
		}
	}
	
	public void delete(int id) throws DAOException {
		String sql = "DELETE FROM cd WHERE id = ?";
		
		PreparedStatement stmt = null;
		try {
			stmt = getConnection().prepareStatement(sql);
			stmt.setInt(1, id);
			if (stmt.executeUpdate() < 1) {
				throw new DAOException("O registro não foi excluído");
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				throw new DAOException(e);
			}
			closeConnection();
		}
	}
	
	public void update(CD cd) throws DAOException {
		String sql = "UPDATE cd SET nome = ?, categoria = ?, conteudo = ? WHERE id = ?";
		
		PreparedStatement stmt = null;
		try {
			stmt = getConnection().prepareStatement(sql);
			
			stmt.setString(1, cd.getNome());
			stmt.setString(2, cd.getCategoria().toString());
			stmt.setString(3, cd.getConteudo());
			stmt.setInt(4, cd.getId());
			
			if (stmt.executeUpdate() < 1) {
				throw new DAOException("O registro não foi atualizado");
			}
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				throw new DAOException(e);
			}
			closeConnection();
		}
	}
	
	public CD load(int id) throws DAOException {
		String sql = "SELECT id, nome, categoria, conteudo FROM cd WHERE id = ?";
		
		PreparedStatement stmt = null;
		try {
			stmt = getConnection().prepareStatement(sql);
			stmt.setInt(1, id);
			
			ResultSet rs = stmt.executeQuery();
			
			if (!rs.next()) {
				return null;
			}
			
			CD cd = new CD();
			cd.setId(rs.getInt("id"));
			cd.setNome(rs.getString("nome"));
			cd.setCategoria(Categoria.getCategoria(rs.getString("categoria").charAt(0)));
			cd.setConteudo(rs.getString("conteudo"));
			return cd;
			
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				throw new DAOException(e);
			}
			closeConnection();
		}
	}
	
	public List<CD> findCDsByCategoria(Categoria categoria) throws DAOException {
		String sql = "SELECT id, nome, categoria, conteudo FROM cd WHERE categoria = ? ORDER BY nome";
		
		PreparedStatement stmt = null;
		try {
			stmt = getConnection().prepareStatement(sql);
			stmt.setString(1, categoria.toString());
			
			ResultSet rs = stmt.executeQuery();
			
			List<CD> cds = new ArrayList<CD>();
			
			while (rs.next()) {
				CD cd = new CD();
				cd.setId(rs.getInt("id"));
				cd.setNome(rs.getString("nome"));
				cd.setCategoria(Categoria.getCategoria(rs.getString("categoria").charAt(0)));
				cd.setConteudo(rs.getString("conteudo"));
				cds.add(cd);
			}
			return cds;
			
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				throw new DAOException(e);
			}
			closeConnection();
		}
	}
}
