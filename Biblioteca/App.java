public class App {
    public static void main(String[] args) {
        Livro l1 = new Livro("Pequeno Principe", "Companhia das Letras", "Infantil", "0000000000000");
        Usuario u1 = new Usuario("Rafael Silva", "15805444798");
        Emprestimo.realizarEmprestimo(l1, u1);
        u1.getLivroEmprestado();
    }
}
