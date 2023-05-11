import java.time.LocalDate;

public class Livro {

    private static int livrosCadastrados = 0;
    private String titulo, editora, categoria, ISBN;
    private LocalDate dataDevolução;
    private Usuario usuarioResponsavel;
    private Boolean emprestado = false, renovado = false;

    public Boolean getRenovado() {
        return renovado;
    }

    public void setRenovado(Boolean renovado) {
        this.renovado = renovado;
    }

    public LocalDate getDataDevolução() {
        return dataDevolução;
    }
    
    public Usuario getUsuarioResponsavel() {
        return usuarioResponsavel;
    }

    public void setUsuarioResponsavel(Usuario usuarioResponsavel) {
        this.usuarioResponsavel = usuarioResponsavel;
    }

    public Boolean getEmprestado() {
        return emprestado;
    }

    public void setEmprestado(Boolean emprestado) {
        this.emprestado = emprestado;
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

    public Livro(String titulo, String editora, String categoria, String ISBN) throws Exception {
        this.titulo = titulo;
        this.editora = editora;
        this.categoria = categoria;
        if (validarISBN(ISBN))
            this.ISBN = ISBN;
        else
            throw new Exception("ISBN inválido\n");

        Livro.livrosCadastrados++;
    }
    
    public int quantidadeLivros() {
        return livrosCadastrados;
    }

}
