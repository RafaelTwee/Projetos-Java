package iff.poo.projetos.livro;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

public class LivroDAO {
	private Connection connection;
	private String INSERIR = "INSERT INTO Livros (Titulo, ISBN) values (?, ?)";
	
	private String REMOVER_ISBN = "DELETE FROM Livros WHERE ISBN = ?";
	private String REMOVER_ID = "DELETE FROM Livros WHERE id = ?";
	
	private String SELECIONAR_ISBN = "SELECT * FROM Livros WHERE ISBN = ?";
	private String SELECIONAR_ID = "SELECT * FROM Livros WHERE id = ?";

	private String SELECIONAR_TODOS = "SELECT * FROM Livros";

	public LivroDAO(){
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:db-biblioteca.db");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void inserir(Livro livro){
		try {
			PreparedStatement ps = connection.prepareStatement(INSERIR);
			ps.setString(1, livro.getTitulo());
			ps.setString(2, livro.getIsbn());
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void removerPorIsbn(String isbn) throws SQLException {
		try {
			PreparedStatement ps = connection.prepareStatement(REMOVER_ISBN);
			ps.setString(1, isbn);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("O ISBN não está cadastrado");
		}
	}

	public void removerPorId(String id) throws SQLException {
		try {
			PreparedStatement ps = connection.prepareStatement(REMOVER_ID);
			ps.setString(1, id);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("O ID do livro não existe");
		}
	}

	public List<Livro> getAll(){
		List<Livro> livros = new ArrayList<>();
		try {
			PreparedStatement ps = connection.prepareStatement(SELECIONAR_TODOS);
			ResultSet result = ps.executeQuery();
			while (result.next()) {
				Livro livro = new Livro(result.getString("Titulo"), result.getString("ISBN"));
				livro.setId(result.getInt("id"));
				livros.add(livro);
			}
			return livros;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return livros;
	}

	public List<Livro> selecionarPorIsbn(String isbn) {
		List<Livro> livros = new ArrayList<>();
		try {
			PreparedStatement ps = connection.prepareStatement(SELECIONAR_ISBN);
			ps.setString(1, isbn);
			ResultSet result = ps.executeQuery();
			while (result.next()) {
				Livro livro = new Livro(result.getString("Titulo"), result.getString("ISBN"));
				livro.setId(result.getInt("id"));
				livros.add(livro);
			}
			return livros;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return livros;
	}
	
	public Livro selecionarPorId(int id) {
		try {
			PreparedStatement ps = connection.prepareStatement(SELECIONAR_ID);
			ps.setInt(1, id);
			ResultSet result = ps.executeQuery();
			if (result.next()) {
				Livro livro = new Livro(result.getString("Titulo"), result.getString("ISBN"));
				livro.setId(result.getInt("id"));
				return livro;
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
