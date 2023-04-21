import java.time.LocalDate;

public class Emprestimo {
    
    public static void realizarEmprestimo(Livro livro, Usuario usuario) {
        livro.emprestado = true;
        usuario.possuiEmprest = true;
        System.out.println("Data para renovação/devolução: " + LocalDate.now().plusDays(7));
    }
}
