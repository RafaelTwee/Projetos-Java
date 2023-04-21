public class Livro {
    private static int livrosCadastrados = 0;

    private String titulo, editora, categoria, ISBN;

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

    public Boolean emprestado, renovado;

    public static Boolean validarISBN(String isbn) {
        if (isbn.length() != 13)
            return false;
        for (int i = 0; i <= 12; i++) {
            if (Character.isLetter(Character.getNumericValue(isbn.charAt(i))))
                return false;
        }
        isbn = isbn.replace(".", "").replace("-", "");
        if (Character.getNumericValue(isbn.charAt(12)) == digitoVerificador(isbn)) 
            return true;
        else
            return false;
    }
    
    private static int digitoVerificador(String isbn) {
        String cadeiaVerif = "131313131313";
        int somaDigitos = 0;
        for (int i = 0; i <= 11; i++)
            somaDigitos += Character.getNumericValue(isbn.charAt(i)) * Character.getNumericValue(cadeiaVerif.charAt(i));
        if (somaDigitos % 10 == 0)
            return 0;
        else
            return 10 - somaDigitos % 10;
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
