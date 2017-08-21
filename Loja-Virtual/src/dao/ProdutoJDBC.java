package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProdutoJDBC {
	
	public void excluir() {

		try (Connection connection = new ConnectionPool().getConnection()){
			connection.setAutoCommit(false);
			
			String sql = "delete from produto where id > 3";
			
			try(PreparedStatement pstmt = connection.prepareStatement(sql)){
				pstmt.execute();
				int count = pstmt.getUpdateCount();
				System.out.printf("%d linhas atualizadas.", count);
				
				connection.commit();
			} catch(SQLException e) {
				connection.rollback();
				System.out.println("ERRO NA TRANSAÇÃO: " + e.getMessage());
				System.out.println("Rollback efetuado.");
			}
			
		} catch(SQLException e) {
			System.out.println("ERRO AO ABRIR CONEXÃO: " + e.getMessage());
		}
	}

	public void inserir() {

		String nome = "Smartphone Samsung Galaxy S7";
		String descricao = "Edição plus";
		String sql = "insert into produto (nome, descricao) values (?, ?)";

		try (Connection connection = new ConnectionPool().getConnection()) {
			/*
			 * Cada pstmt.execute() representa uma transação no banco de dados, que executa
			 * commit ao final da transação. Outro execute() realiza outro commit. Se
			 * quisermos manter uma única transação para todas as operações, temos que
			 * definir autoCommit como false.
			 */
			connection.setAutoCommit(false);

			try (PreparedStatement pstmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
				
				pstmt.setString(1, nome);
				pstmt.setString(2, descricao);
				pstmt.executeUpdate();
				ResultSet rs = pstmt.getGeneratedKeys();

				if (nome.equals("Smartphone Samsung Galaxy S7"))
					throw new IllegalArgumentException("Problema ocorrido.");

				/*
				 * A transação passa a ser atômica, ou toda a operação é executada ou nada é
				 * executado.
				 */
				connection.commit();

				while (rs.next()) {
					System.out.printf("Código gerado: %d", rs.getInt(1));
				}

				rs.close();
			} catch (Exception e) {
				connection.rollback();
				System.out.println("ERRO: " + e.getMessage());
				System.out.println("Rollback efetuado.");
			}
		} catch(SQLException e) {
			System.out.println("ERRO AO ABRIR CONEXÃO: " + e.getMessage());
		}
	}

	public void listar() {

		try (Connection connection = new ConnectionPool().getConnection()){
			
			String sql = "select * from produto";
			
			try(PreparedStatement pstmt = connection.prepareStatement(sql)){
				try(ResultSet rs = pstmt.executeQuery()){
					while (rs.next()) {
						System.out.printf("Id: %d, Nome: %s, Descrição: %s\n", rs.getInt("id"), rs.getString("nome"),
								rs.getString("descricao"));
					}
				}
			} catch(SQLException e) {
				System.out.println("ERRO NA TRANSAÇÃO: " + e.getMessage());
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("ERRO AO ABRIR CONEXÃO: " + e.getMessage());
		}

	}

	public static void main(String[] args) {
		ProdutoJDBC produtoDao = new ProdutoJDBC();
		produtoDao.listar();
		
	}

}
