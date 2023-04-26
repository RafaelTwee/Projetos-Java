import java.time.LocalDate;

public class Emprestimo {
    
    public static void realizarEmprestimo(Livro livro, Usuario usuario) {
        if (livro.emprestado) {
            System.out.println("O livro já foi emprestado e será devolvido no dia: " + livro.getDataDevolução());
        }
        else if (usuario.possuiEmprest) {
            System.out.println("O usuário já possui um livro emprestado que será devolvido no dia: " + usuario.livroEmprestado.getDataDevolução());
        }
        else {
            livro.emprestado = true;
            usuario.possuiEmprest = true;
            livro.setDataDevolução(LocalDate.now().plusDays(7));
            usuario.livroEmprestado = livro;
        }

        System.out.println("Data para renovação/devolução: " + livro.getDataDevolução());
    }
}
