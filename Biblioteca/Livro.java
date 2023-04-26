import java.time.LocalDate;

public class Livro {
    private static int livrosCadastrados = 0;

    private String titulo, editora, categoria, ISBN;
    private LocalDate dataDevolução;
    public Usuario usuarioResponsavel;
    public Boolean emprestado, renovado = false;

    public LocalDate getDataDevolução() {
        return dataDevolução;
    }

    public void setDataDevolução(LocalDate dataDevolução) {
        this.dataDevolução = dataDevolução;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getEditora() {
        return editora;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getISBN() {
        return ISBN;
    }

    public static Boolean validarISBN(String isbn) {
        if (isbn.length() != 13)
            return false;
        for (int i = 0; i <= 12; i++) {
            if (Character.isLetter(Character.getNumericValue(isbn.charAt(i))))
                return false;
        }
        isbn = isbn.replace(".", "").replace("-", "");
        if ((somaProdutos(isbn) + Character.getNumericValue(isbn.charAt(12))) % 10 == 0)
            return true;
        else
            return false;
    }
    
    private static int somaProdutos(String isbn) {
        String cadeiaVerif = "131313131313";
        int somaProdutos = 0;
        for (int i = 0; i <= isbn.length() - 2; i++)
            somaProdutos += Character.getNumericValue(isbn.charAt(i)) * Character.getNumericValue(cadeiaVerif.charAt(i));
        return somaProdutos;
    }

    public Livro(String titulo, String editora, String categoria, String ISBN) {
        this.titulo = titulo;
        this.editora = editora;
        this.categoria = categoria;
        try {
            if (validarISBN(ISBN))
                this.ISBN = ISBN;
            else
                throw new Exception("ISBN inválido");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Livro.livrosCadastrados++;
    }
    
    public int quantidadeLivros() {
        return livrosCadastrados;
    }

}
