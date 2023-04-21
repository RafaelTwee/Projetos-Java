public class App {
    public static void main(String[] args) {
        Livro l1 = new Livro("Pequeno Principe", "Cleofas", "Infantil", "9780306406157");
        Usuario u1 = new Usuario("Rafael Silva", "15805444798");

        Emprestimo.realizarEmprestimo(l1, u1);
        System.out.println("O título do livro é: " + l1.getTitulo());
    }
}
