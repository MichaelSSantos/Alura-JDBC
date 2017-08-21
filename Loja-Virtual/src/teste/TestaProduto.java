package teste;

import dao.CategoriaDao;
import dao.ProdutoDao;
import modelo.Categoria;
import modelo.Produto;

public class TestaProduto {

	public static void main(String[] args) {
		
		/*try(Connection connection = new ConnectionPool().getConnection()){
			
			ProdutoDao produtoDao = new ProdutoDao(connection);
//			Integer id = produtoDao.salva(new Produto("Mesa", "Mesa de mármore de 4 lugares."));
//			System.out.printf("Produto de código %d foi inserido.\n", id);
			
			for (Categoria categoria : new CategoriaDao(connection).lista()) {
				for (Produto produto : produtoDao.busca(categoria)) {
					System.out.println(produto.toString());
				}
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERRO: " + e.getMessage());
		}*/
		
		try {
			ProdutoDao produtoDao = new ProdutoDao();
			
			Integer id = produtoDao.salva(new Produto("Monitor", "Monitor 22 polegadas", new Categoria(2, null)));
			System.out.printf("Produto de código %d foi inserido.\n", id);
			
			for (Produto produto : produtoDao.lista()) {
				System.out.println(produto.toString());
			}
		
			CategoriaDao categoriaDao = new CategoriaDao();
			for (Categoria categoria : categoriaDao.lista()) {
				System.out.println(categoria.toString());
			}
		
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
	}
	
}
