package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.Categoria;

public class CategoriaDao extends ConnectionDao {
	
	public CategoriaDao() throws Exception {
		super();
	}

	public List<Categoria> lista() throws Exception {

		List<Categoria> categorias = new ArrayList<>();
		String sql = "select * from categoria";
		
		try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
			pstmt.executeQuery();
			
			try (ResultSet rs = pstmt.getResultSet()) {
				while (rs.next()) {
					Integer id = rs.getInt(1);
					String nome = rs.getString(2);
					Categoria categoria = new Categoria(id, nome);
					categorias.add(categoria);
				}
			}
			
			return categorias;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("ERRO AO LISTAR CATEGORIAS: " + e.getMessage());
		}

	}

}
