package iff.poo.projetos.controller;

import java.sql.SQLException;
import java.util.List;

import iff.poo.projetos.dao.LivroDAO;
import iff.poo.projetos.model.Livro;

public class LivroController {

	LivroDAO livroDAO = new LivroDAO();

	private static Boolean validarISBN(String isbn) {
        String cadeiaVerif = "131313131313";
        int somaProdutos = 0;
        isbn = isbn.replace(".", "").replace("-", "");
        for (int i = 0; i <= isbn.length() - 2; i++)
            somaProdutos += Character.getNumericValue(isbn.charAt(i)) * Character.getNumericValue(cadeiaVerif.charAt(i));
        if (isbn.length() != 13)
            return false;
        for (int i = 0; i <= 12; i++) {
            if (Character.isLetter(Character.getNumericValue(isbn.charAt(i))))
                return false;
        }
        if ((somaProdutos + Character.getNumericValue(isbn.charAt(12))) % 10 == 0)
            return true;
        else
            return false;
    }
	
	public void inserir(Livro livro) throws Exception {
		if (!livroDAO.selecionarPorIsbn(livro.getIsbn()).isEmpty()) {
            if (!livroDAO.selecionarPorIsbn(livro.getIsbn()).get(0).getTitulo().equals(livro.getTitulo())) {
                throw new Exception("O ISBN já está cadastrado com outros dados");
            }
        }
        if (!validarISBN(livro.getIsbn())) {
            throw new Exception("O ISBN não é válido");
        }
		livroDAO.inserir(livro);
	}

    public void removerPorIsbn(String isbn) {
        try {
            livroDAO.removerPorIsbn(isbn);
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    public void removerPorId(String id) {
        try {
            livroDAO.removerPorIsbn(id);
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    public List<Livro> selecionarPorIsbn(String isbn) {
        return livroDAO.selecionarPorIsbn(isbn);
	}

    public Livro selecionarPorId(int id) {
        return livroDAO.selecionarPorId(id);
	}
}
