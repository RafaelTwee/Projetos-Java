package iff.poo.projetos.emprestimo;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import iff.poo.projetos.livro.Livro;
import iff.poo.projetos.usuario.Usuario;

public class EmprestimoDAO {
	private Connection connection;

	private String INSERIR = "INSERT INTO Emprestimos (CPF, id Livro, Data Inicial, Data Final) values (?, ?, ?, ?)";

    private String UPDATE_ENTREGA = "UPDATE Emprestimos SET Data Entrega = ? WHERE CPF = ? AND Data Entrega = NULL";
	private String UPDATE_RENOVADO = "UPDATE Emprestimos SET Renovado = ?, Data Entrega = ? WHERE CPF = ? AND Data Entrega = NULL";
    
	private String REMOVER = "DELETE FROM Emprestimos WHERE id = ?";

	private String SELECIONAR_ATIVO = "SELECT * FROM Emprestimos WHERE CPF = ? AND Data Entrega = NULL";
	private String SELECIONAR_ATIVOS = "SELECT * FROM Emprestimos WHERE Data Entrega = NULL";

	private String SELECIONAR_TODOS = "SELECT * FROM Emprestimos";
	private String SELECIONAR_USUARIO = "SELECT * FROM Usuarios WHERE CPF = ?" ;
	private String SELECIONAR_LIVRO = "SELECT * FROM Livros WHERE id = ?" ;

	public EmprestimoDAO(){
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:db-biblioteca.db");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void inserirEmprestimo(String cpf, int idLivro) {
		try {
			PreparedStatement ps = connection.prepareStatement(INSERIR);
			ps.setString(1, cpf);
			ps.setInt(2, idLivro);
            ps.setString(3, LocalDate.now().toString());
			ps.setString(4, LocalDate.now().plusDays(7).toString());
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void inserirEntrega(String cpf) {
		try {
			PreparedStatement ps = connection.prepareStatement(UPDATE_ENTREGA);
			ps.setString(1, LocalDate.now().toString());
			ps.setString(2, cpf);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void renovar(String cpf) {
		try {
			PreparedStatement ps = connection.prepareStatement(UPDATE_RENOVADO);
			ps.setBoolean(1, Boolean.TRUE);
			ps.setString(2, LocalDate.now().plusDays(7).toString());
			ps.setString(3, cpf);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void removerEmprestimo(int id) throws SQLException {
		try {
			PreparedStatement ps = connection.prepareStatement(REMOVER);
			ps.setInt(1, id);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Emprestimo> getAll() {
		List<Emprestimo> emprestimos = new ArrayList<>();
		try {
			PreparedStatement ps = connection.prepareStatement(SELECIONAR_TODOS);
			ResultSet result = ps.executeQuery();
			while (result.next()) {
				Usuario usuario = null;
				Livro livro = null;
				ps = connection.prepareStatement(SELECIONAR_USUARIO);
				ps.setString(1, result.getString("CPF"));
				ResultSet result_usuario = ps.executeQuery();
				if (result_usuario.next()){
					usuario = new Usuario(result_usuario.getString("Nome"), result_usuario.getString("CPF"));
				}
				ps = connection.prepareStatement(SELECIONAR_LIVRO);
				ps.setString(1, result.getString("id Livro"));
				ResultSet result_livro = ps.executeQuery();
				if (result_livro.next()){
					livro = new Livro(result_livro.getString("Nome"), result_livro.getString("CPF"));
				}
				Emprestimo emprestimo = new Emprestimo(usuario , livro, LocalDate.parse(result.getString("Data Incicial")), LocalDate.parse(result.getString("Data Final")));
				emprestimo.setDataDevolução(LocalDate.parse(result.getString("Data Entrega")));
				emprestimo.setRenovado(result.getBoolean("Renovado"));
				emprestimos.add(emprestimo);
			}
			return emprestimos;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return emprestimos;
	}

	public List<Emprestimo> selecionarAtivos() {
		List<Emprestimo> emprestimos = new ArrayList<>();
		try {
			PreparedStatement ps = connection.prepareStatement(SELECIONAR_ATIVOS);
			ResultSet result = ps.executeQuery();
			while (result.next()) {
				Usuario usuario = null;
				Livro livro = null;
				ps = connection.prepareStatement(SELECIONAR_USUARIO);
				ps.setString(1, result.getString("CPF"));
				ResultSet result_usuario = ps.executeQuery();
				if (result_usuario.next()){
					usuario = new Usuario(result_usuario.getString("Nome"), result_usuario.getString("CPF"));
				}
				ps = connection.prepareStatement(SELECIONAR_LIVRO);
				ps.setString(1, result.getString("id Livro"));
				ResultSet result_livro = ps.executeQuery();
				if (result_livro.next()){
					livro = new Livro(result_livro.getString("Nome"), result_livro.getString("CPF"));
				}
				Emprestimo emprestimo = new Emprestimo(usuario , livro, LocalDate.parse(result.getString("Data Incicial")), LocalDate.parse(result.getString("Data Final")));
				emprestimo.setDataDevolução(LocalDate.parse(result.getString("Data Entrega")));
				emprestimo.setRenovado(result.getBoolean("Renovado"));
				emprestimos.add(emprestimo);
			}
			return emprestimos;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return emprestimos;
	}

	public Emprestimo selecionarAtivo(String cpf) {
		try {
			PreparedStatement ps = connection.prepareStatement(SELECIONAR_ATIVO);
			ps.setString(1, cpf);
			ResultSet result = ps.executeQuery();
			if (result.next()) {
				Usuario usuario = null;
				Livro livro = null;
				ps = connection.prepareStatement(SELECIONAR_USUARIO);
				ps.setString(1, result.getString("CPF"));
				ResultSet result_usuario = ps.executeQuery();
				if (result_usuario.next()){
					usuario = new Usuario(result_usuario.getString("Nome"), result_usuario.getString("CPF"));
				}
				ps = connection.prepareStatement(SELECIONAR_LIVRO);
				ps.setString(1, result.getString("id Livro"));
				ResultSet result_livro = ps.executeQuery();
				if (result_livro.next()){
					livro = new Livro(result_livro.getString("Nome"), result_livro.getString("CPF"));
				}
				Emprestimo emprestimo = new Emprestimo(usuario , livro, LocalDate.parse(result.getString("Data Incicial")), LocalDate.parse(result.getString("Data Final")));
				emprestimo.setDataDevolução(LocalDate.parse(result.getString("Data Entrega")));
				emprestimo.setRenovado(result.getBoolean("Renovado"));
				return emprestimo;
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
