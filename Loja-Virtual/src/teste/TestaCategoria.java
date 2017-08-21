package teste;

import java.sql.Connection;

import dao.CategoriaDao;
import dao.ConnectionPool;
import modelo.Categoria;

public class TestaCategoria {

	public static void main(String[] args) {
		
		/*try(Connection con = new ConnectionPool().getConnection()){
		
			CategoriaDao categoriaDao = new CategoriaDao(con);
			
			for (Categoria categoria : categoriaDao.lista()) {
				System.out.println(categoria.toString());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERRO: " + e.getMessage());
		}*/
	}
	
}
