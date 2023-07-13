package iff.poo.projetos.livro;

import java.sql.SQLException;
import java.util.List;

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
		if (livroDAO.selecionarPorIsbn(livro.getIsbn()).isEmpty()) {
			if (validarISBN(livro.getIsbn())) {
				livroDAO.inserir(livro);
			}
			else {
				throw new Exception("O ISBN não é válido");
			}
		}
		else {
			throw new Exception("O ISBN já está cadastrado");
		}
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
