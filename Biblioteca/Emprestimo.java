import java.time.LocalDate;
import java.util.ArrayList;

public abstract class Emprestimo {

    public static ArrayList<String> empretimosConcluidos = new ArrayList<String>();

    public static Usuario encontrarUsuario(String cpf, ArrayList<Usuario> listaus){
        for (Usuario us : listaus) {
            if (us.getCpf().equals(cpf))
                return us;
        }
        return null;
    }

    public static Livro encontrarLivro(String isbn, ArrayList<Livro> listali){
        for (Livro li : listali) {
            if (li.getISBN().equals(isbn))
                return li;
        }
        return null;
    }
    
    public static void realizarEmprestimo(Livro livro, Usuario usuario) throws Exception {
        if (livro.getEmprestado())
            throw new Exception("O livro já foi emprestado e será devolvido no dia: " + livro.getDataDevolução() + ". ");
        else if (usuario.getPossuiEmprest())
            throw new Exception("O usuário já possui um livro emprestado que será devolvido no dia: " + usuario.getLivroEmprestado().getDataDevolução() + ". ");
        else {
            usuario.setPossuiEmprest(true);
            usuario.setLivroEmprestado(livro);
            livro.setEmprestado(true);
            livro.setDataDevolução(LocalDate.now().plusDays(7));
            livro.setUsuarioResponsavel(usuario);
        }
    }
    public static void renovar(Livro livro, Usuario usuario) throws Exception {
        if (livro.getRenovado())
            throw new Exception("O livro não pode ser renovado novamente. ");
        else if (!usuario.getLivroEmprestado().equals(livro)){
            throw new Exception("O livro não foi emprestado. ");
        }
        else {
            livro.setDataDevolução(LocalDate.now().plusDays(7));
            livro.setRenovado(true);
        }
    }
    public static void devolver(Livro livro, Usuario usuario) throws Exception{
        if (livro.getEmprestado() && usuario.getLivroEmprestado().equals(livro)) {
            usuario.setLivroEmprestado(null);
            usuario.setPossuiEmprest(false);
            livro.setEmprestado(false);
            livro.setRenovado(false);
            livro.setUsuarioResponsavel(null);
            if (LocalDate.now().isBefore(livro.getDataDevolução()))
                empretimosConcluidos.add("Usuário responsável: " + usuario.getNome() + " - Data de devolução: " + LocalDate.now() + " - Livro: " + livro.getTitulo());
            else
                empretimosConcluidos.add("Usuário responsável: " + usuario.getNome() + " - Data de devolução: " + LocalDate.now() + " - Livro: " + livro.getTitulo() + " (Atrasado)");

        }
        else
            throw new Exception("O livro não pertence ao usuário. ");
    }
}
