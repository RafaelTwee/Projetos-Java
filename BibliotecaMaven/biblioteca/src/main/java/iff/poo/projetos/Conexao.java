package iff.poo.projetos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	private static Connection c = null;
	public static Connection getConn() {
		if(c == null){
			try {
				c = DriverManager.getConnection("jdbc:sqlite:D://iff/poo/projetos/BibliotecaMaven/biblioteca/db-biblioteca.db");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
			return c;
		}
}
