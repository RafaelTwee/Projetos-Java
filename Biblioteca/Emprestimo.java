import java.time.LocalDate;

public class Emprestimo {
    
    public static void realizarEmprestimo(Livro livro, Usuario usuario) {
        if (livro.getEmprestado()) {
            System.out.println("O livro já foi emprestado e será devolvido no dia: " + livro.getDataDevolução());
        }
        else if (usuario.getPossuiEmprest()) {
            System.out.println("O usuário já possui um livro emprestado que será devolvido no dia: " + usuario.getLivroEmprestado().getDataDevolução());
        }
        else {
            usuario.setPossuiEmprest(true);
            usuario.setLivroEmprestado(livro);
            livro.setEmprestado(true);
            livro.setDataDevolução(LocalDate.now().plusDays(7));
            System.out.println("Livro emprestado. Data para renovação/devolução: " + livro.getDataDevolução());
        }
    }
    public static void renovar(Livro livro) {
        if (livro.getRenovado()) {
            System.out.println("O livro não pode ser renovado novamente.");
        }
        else {
            livro.setDataDevolução(LocalDate.now().plusDays(7));
            livro.setRenovado(true);
        }
    }
    public static void devolver(Livro livro, Usuario usuario) {
        if (livro.getEmprestado() && (livro == usuario.getLivroEmprestado())) {
            usuario.setLivroEmprestado(null);
            usuario.setPossuiEmprest(false);
            livro.setEmprestado(false);
            livro.setRenovado(false);
        }
        else {
            System.out.println("O livro não pertence ao usuário.");
        }
    }
}
