package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.Categoria;
import modelo.Produto;

public class ProdutoDao extends ConnectionDao{

	public ProdutoDao() throws Exception {
		super();
	}

	public Integer salva(Produto produto) throws Exception {
		
		String sql = "insert into produto (nome, descricao, categoria_id) values (?, ?, ?)";

		try (PreparedStatement pstmt = getConnection().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
			pstmt.setString(1, produto.getNome());
			pstmt.setString(2, produto.getDescricao());
			pstmt.setInt(3, produto.getCategoria().getId());
			pstmt.executeUpdate();
			ResultSet rs = pstmt.getGeneratedKeys();
			
			Integer id = null;
			if (rs.next()) {
				id = rs.getInt(1);
			}
			
			return id;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("ERRO AO INSERIR PRODUTO: " + e.getMessage());
		}
	}

	public List<Produto> lista() throws Exception {
		
		List<Produto> produtos = new ArrayList<>();
		String sql = "select p.id as p_id, p.nome as p_nome, p.descricao as p_descricao, c.id as c_id, c.nome as c_nome "
				+ "from produto p inner join categoria c on p.categoria_id = c.id";

		try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
			pstmt.executeQuery();
			mapeiaResultadoEmObjeto(produtos, pstmt);
			
			return produtos;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("ERRO AO LISTAR PRODUTOS: " + e.getMessage());
		}
	}

	public List<Produto> busca(Categoria categoria) throws Exception {
		
		List<Produto> produtos = new ArrayList<Produto>();
		String sql = "select p.id as p_id, p.nome as p_nome, p.descricao as p_descricao, c.id as c_id, c.nome as c_nome "
				+ "from produto p inner join categoria c on p.categoria_id = c.id where c.id = ?";

		try (PreparedStatement pstmt = getConnection().prepareStatement(sql)) {
			pstmt.setInt(1, categoria.getId());
			pstmt.executeQuery();
			mapeiaResultadoEmObjeto(produtos, pstmt);
			
			return produtos;	
		} catch (SQLException e) {
			e.printStackTrace();
			throw new Exception("ERRO AO BUSCAR PRODUTOS: " + e.getMessage());
		}
	}

	private void mapeiaResultadoEmObjeto(List<Produto> produtos, PreparedStatement pstmt) throws SQLException {

		try (ResultSet rs = pstmt.getResultSet()) {
			while (rs.next()) {
				Integer pId = rs.getInt("p_id");
				String pNome = rs.getString("p_nome");
				String pDescricao = rs.getString("p_descricao");
				Integer cId = rs.getInt("c_id");
				String cNome = rs.getString("c_nome");
				Produto produto = new Produto(pId, pNome, pDescricao, new Categoria(cId, cNome));
				produtos.add(produto);
			}
		}
	}

}
