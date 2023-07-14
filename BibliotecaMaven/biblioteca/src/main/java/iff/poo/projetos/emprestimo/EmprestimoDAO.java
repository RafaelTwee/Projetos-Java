package iff.poo.projetos.emprestimo;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import iff.poo.projetos.Conexao;
import iff.poo.projetos.livro.Livro;
import iff.poo.projetos.livro.LivroDAO;
import iff.poo.projetos.usuario.Usuario;
import iff.poo.projetos.usuario.UsuarioDAO;

public class EmprestimoDAO {
	private LivroDAO livroDAO = new LivroDAO();
	private UsuarioDAO usuarioDAO = new UsuarioDAO();
	
	private Connection connection = Conexao.getConn();

	private String INSERIR = "INSERT INTO Emprestimos (CPF, id_Livro, Data_Inicial, Data_Final, Renovado) values (?, ?, ?, ?, ?)";

    private String UPDATE_ENTREGA = "UPDATE Emprestimos SET Data_Entrega = ? WHERE CPF = ? AND Data_Entrega IS NULL";
	private String UPDATE_RENOVADO = "UPDATE Emprestimos SET Renovado = ?, Data_Entrega = ? WHERE CPF = ? AND Data_Entrega IS NULL";
    
	private String REMOVER = "DELETE FROM Emprestimos WHERE id = ?";

	private String SELECIONAR_ATIVO = "SELECT * FROM Emprestimos WHERE CPF = ? AND Data_Entrega IS NULL";
	private String SELECIONAR_ATIVOS = "SELECT * FROM Emprestimos WHERE Data_Entrega IS NULL";

	private String SELECIONAR_TODOS = "SELECT * FROM Emprestimos";

	public void realizarEmprestimo(Usuario usuario, Livro livro) {
		try {
			PreparedStatement ps = connection.prepareStatement(INSERIR);
			ps.setString(1, usuario.getCpf());
			ps.setInt(2, livro.getId());
            ps.setString(3, LocalDate.now().toString());
			ps.setString(4, LocalDate.now().plusDays(7).toString());
			ps.setBoolean(5, Boolean.FALSE);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void entregar(Usuario usuario) {
		try {
			PreparedStatement ps = connection.prepareStatement(UPDATE_ENTREGA);
			ps.setString(1, LocalDate.now().toString());
			ps.setString(2, usuario.getCpf());
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void renovar(Usuario usuario) {
		try {
			PreparedStatement ps = connection.prepareStatement(UPDATE_RENOVADO);
			ps.setBoolean(1, Boolean.TRUE);
			ps.setString(2, LocalDate.now().plusDays(7).toString());
			ps.setString(3, usuario.getCpf());
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
				Emprestimo emprestimo = new Emprestimo(usuarioDAO.selecionarPorCpf(result.getString("CPF")), livroDAO.selecionarPorId(result.getInt("id_Livro")), LocalDate.parse(result.getString("Data_Inicial")), LocalDate.parse(result.getString("Data_Final")));
				emprestimo.setDataDevolucao(LocalDate.parse(result.getString("Data_Inicial")));
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
				Emprestimo emprestimo = new Emprestimo(usuarioDAO.selecionarPorCpf(result.getString("CPF")), livroDAO.selecionarPorId(result.getInt("id_Livro")), LocalDate.parse(result.getString("Data_Inicial")), LocalDate.parse(result.getString("Data_Final")));
				emprestimo.setDataDevolucao(LocalDate.parse(result.getString("Data_Inicial")));
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
		Emprestimo emprestimo;
		try {
			PreparedStatement ps = connection.prepareStatement(SELECIONAR_ATIVO);
			ps.setString(1, cpf);
			ResultSet result = ps.executeQuery();
			if (result.next()) {
				emprestimo = new Emprestimo(usuarioDAO.selecionarPorCpf(result.getString("CPF")), livroDAO.selecionarPorId(result.getInt("id_Livro")), LocalDate.parse(result.getString("Data_Inicial")), LocalDate.parse(result.getString("Data_Final")));
				emprestimo.setDataDevolucao(LocalDate.parse(result.getString("Data_Inicial")));
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
