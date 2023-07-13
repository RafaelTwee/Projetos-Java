package iff.poo.projetos.usuario;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
	private Connection connection;
	private String INSERIR = "INSERT INTO Usuarios (Nome, CPF) values (?, ?)";
	private String REMOVER = "DELETE FROM Usuarios WHERE CPF = ?";
	private String SELECIONAR = "SELECT * FROM Usuarios WHERE CPF = ?";
	private String SELECIONAR_TODOS = "SELECT * FROM Usuarios";

	public UsuarioDAO(){
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:db-biblioteca.db");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void inserir(Usuario usuario){
		try {
			PreparedStatement ps = connection.prepareStatement(INSERIR);
			ps.setString(1, usuario.getNome());
			ps.setString(2, usuario.getCpf());
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void removerPorCpf(String cpf) throws Exception {
		try {
			PreparedStatement ps = connection.prepareStatement(REMOVER);
			ps.setString(1, cpf);
			ps.execute();
		} catch (SQLException e) {
			throw new SQLException("CPF não encontrado");
		}
	}

	public List<Usuario> getAll(){
		List<Usuario> usuarios = new ArrayList<>();
		try {
			PreparedStatement ps = connection.prepareStatement(SELECIONAR_TODOS);
			ResultSet result = ps.executeQuery();
			while (result.next()) {
				Usuario usuario = new Usuario(result.getString("Nome"), result.getString("CPF"));
				usuarios.add(usuario);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usuarios;
	}

	public Usuario selecionarPorCpf(String cpf){
		try {
			PreparedStatement ps = connection.prepareStatement(SELECIONAR);
			ps.setString(1, cpf);
			ResultSet result = ps.executeQuery();
			if (result.next()){
				Usuario usuario = new Usuario(result.getString("Nome"), result.getString("CPF"));
				return usuario;
			}
			else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
