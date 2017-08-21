package modelo;

public class Produto {

	private Integer id;
	private String nome;
	private String descricao;
	private Categoria categoria;

	public Produto(String nome, String descricao, Categoria categoria) {
		this.nome = nome;
		this.descricao = descricao;
		this.categoria = categoria;
	}

	public Produto(Integer id, String nome, String descricao, Categoria categoria) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.categoria = categoria;
	}

	@Override
	public String toString() {
		return String.format("Produto: Id: %d, Nome: %s, Descrição: %s, Categoria: %s", 
				this.id, this.nome, this.descricao, this.categoria.getNome());
	}

	public Integer getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public Categoria getCategoria() {
		return categoria;
	}

}
