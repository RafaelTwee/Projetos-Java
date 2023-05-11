import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
        ArrayList<Livro> livros = new ArrayList<Livro>();
        String op1, op2, op3;
        Scanner input = new Scanner(System.in);
        do {
            System.out.print("\033[H\033[2J");
            System.out.println("Selecione a opção desejada:\n1 - Livros\n2 - Usuários\n3 - Emprestimos\n4 - Sair");
            op1 = input.nextLine();
            paginainicial:
            switch (op1) {
                case "1":
                System.out.print("\033[H\033[2J");
                System.out.println("Selecione a opção desejada:\n1 - Cadastrar\n2 - Consultar\n3 - Voltar ao início");
                op2 = input.nextLine();
                switch (op2) {
                    case "1":
                    System.out.print("\033[H\033[2J");
                    System.out.println("Digite o nome do livro: ");
                    String nome = input.nextLine();
                    System.out.println("Digite o nome da editora: ");
                    String edit = input.nextLine();
                    System.out.println("Digite o nome da categoria: ");
                    String cat = input.nextLine();
                    System.out.println("Digite o ISBN do livro: ");
                    String isbn = input.nextLine();
                    for (Livro li : livros){
                        if (li.getISBN().equals(isbn.trim().replace(".", "").replace("-", ""))) {
                            if (li.getCategoria().equals(cat) && li.getEditora().equals(edit) && li.getTitulo().equals(nome)) {}
                            else {
                                System.out.print("\033[H\033[2J");
                                System.out.println("Esse ISBN já está cadastrado com nome/categoria/editora diferente. Pressione enter para continuar.");
                                input.nextLine();
                                break paginainicial;
                            }
                        }
                    }
                    
                    try {
                        Livro l = new Livro(nome, edit, cat, isbn);
                        livros.add(l);
                        System.out.println("Livro cadastrado. Pressione enter para continuar.");
                        input.nextLine();
                        break paginainicial;
                    }
                    catch (Exception e) {
                        System.out.println(e.getMessage() + "Pressione enter para continuar.");
                        input.nextLine();
                    }
                    break;

                    case "2":
                    System.out.print("\033[H\033[2J");
                    System.out.println("Deseja consultar pelo:\n1 - Nome\n2 - ISBN\n3 - Categoria\n4 - Editora\n5 - Voltar ao início");
                    op3 = input.nextLine();
                    switch (op3) {
                        case "1":
                            System.out.println("Digite o nome do livro:");
                            String nomeBusca = input.nextLine();
                            for (Livro li : livros) {
                                if (li.getTitulo().equalsIgnoreCase(nomeBusca.trim()))
                                    System.out.println(li.getTitulo() + " - " + li.getCategoria() + " - " + li.getEditora() + " - " +li.getISBN());
                            }
                            System.out.println("Pressione enter para continuar.");
                            input.nextLine();
                            break;
                        case "2":
                            System.out.println("Digite o ISBN do livro: \n");
                            String isbnBusca = input.nextLine();
                            for (Livro li : livros) {
                                if (li.getISBN().equals(isbnBusca.trim().replace(".", "").replace("-", "")))
                                    System.out.println(li.getTitulo() + " - " + li.getCategoria() + " - " + li.getEditora() + " - " +li.getISBN());
                            }
                            System.out.println("Pressione enter para continuar.");
                            input.nextLine();
                            break;
                        case "3":
                            System.out.println("Digite a categoria de livros: ");
                            String catBusca = input.nextLine();
                            for (Livro li : livros) {
                                if (li.getCategoria().equalsIgnoreCase(catBusca.trim()))
                                    System.out.println(li.getTitulo() + " - " + li.getCategoria() + " - " + li.getEditora() + " - " +li.getISBN());
                            }
                            System.out.println("Pressione enter para continuar.");
                            input.nextLine();
                            break;
                        case "4":
                            System.out.println("Digite a categoria de livros: ");
                            String ediBusca = input.nextLine();
                            for (Livro li : livros) {
                                if (li.getEditora().equalsIgnoreCase(ediBusca.trim()))
                                    System.out.println(li.getTitulo() + " - " + li.getCategoria() + " - " + li.getEditora() + " - " +li.getISBN());
                            }
                            System.out.println("Pressione enter para continuar.");
                            input.nextLine();
                            break;  
                        case "5":
                            break;  
                        default:
                            break;
                        }
                }
                break;
                case "2":
                System.out.print("\033[H\033[2J");
                System.out.println("Selecione a opção desejada:\n1 - Cadastrar\n2 - Consultar\n3 - Voltar ao início");
                op2 = input.nextLine();
                switch (op2) {
                    case "1":
                        System.out.print("\033[H\033[2J");
                        System.out.println("Digite o nome do usuário: ");
                        String nome = input.nextLine();
                        System.out.println("Digite o CPF do usuário: ");
                        String cpf = input.nextLine();
                        for (Usuario us : usuarios) {
                            if (us.getCpf().equals(cpf.trim().replace(".", "").replace("-", ""))){
                            System.out.print("\033[H\033[2J");
                            System.out.println("Esse CPF já está cadastrado. Pressione enter para continuar.");
                            input.nextLine();
                            break paginainicial;
                            }
                        }
                        try {
                            Usuario u = new Usuario(nome, cpf);
                            usuarios.add(u);
                            System.out.println("Usuário cadastrado. Pressione enter para continuar.");
                            input.nextLine();
                            break paginainicial;
                        }
                        catch (Exception e) {
                            System.out.println(e.getMessage() + "Pressione enter para continuar.");
                            input.nextLine();
                        }
                        break;
                    case "2":
                    System.out.print("\033[H\033[2J");
                    System.out.println("Deseja consultar pelo:\n1 - Nome\n2 - CPF\n3 - Voltar ao início");
                    op3 = input.nextLine();
                    switch (op3) {
                        case "1":
                            System.out.println("Digite o nome do usuário: ");
                            String nomeBusca = input.nextLine();
                            for (Usuario us : usuarios) {
                                if (us.getNome().toLowerCase().equals(nomeBusca.toLowerCase().trim()))
                                    System.out.println(us.getNome() + " - " + us.getCpf());
                            }

                            System.out.println("Pressione enter para continuar.");
                            input.nextLine();
                            break;
                        case "2":
                            System.out.println("Digite o CPF do usuário: ");
                            String cpfBusca = input.nextLine();
                            System.out.println();
                            for (Usuario us : usuarios) {
                                if (us.getCpf().equals(cpfBusca.trim().replace(".", "").replace("-", "")))
                                    System.out.println(us.getNome() + " - " + us.getCpf() + " - ");
                            }
                            System.out.println("Pressione enter para contiuar.");
                            input.nextLine();
                            break;
                        case "3":
                            break;  
                        default:
                            break;
                        }
                        break;
                    default:
                    break;
                }
                break;
                case "3":
                System.out.print("\033[H\033[2J");
                System.out.println("Você deseja:\n1 - Realizar Emprestimo\n2 - Consultar Emprestimos\n3 - Renovar\n4 - Devolver\n5 - Voltar ao início");
                op2 = input.nextLine();
                switch (op2) {
                    case "1":
                        System.out.print("\033[H\033[2J");
                        System.out.println("Digite o CPF do usuário: ");
                        String cpfBusca = input.nextLine();
                        Usuario u = Emprestimo.encontrarUsuario(cpfBusca, usuarios);
                        if (u == null){
                            System.out.println("Usuário não encontrado. Pressione enter para continuar.");
                            input.nextLine();
                            break paginainicial;
                        }
                        System.out.println("Digite o ISBN do livro: ");
                        String isbnBusca = input.nextLine();
                        Livro l = Emprestimo.encontrarLivro(isbnBusca, livros);
                        if (l == null){
                            System.out.println("Livro não encontrado. Pressione enter para continuar.");
                            input.nextLine();
                            break paginainicial;
                        }
                        try {
                            Emprestimo.realizarEmprestimo(l, u);
                            System.out.println("Emprestimo realizado. Pressione enter para continuar.");
                            input.nextLine();
                            break paginainicial;
                        }
                        catch (Exception e) {
                            System.out.println(e.getMessage() + "Pressione enter para continuar.");
                            input.nextLine();
                        }
                        break;
                    case "2":
                        System.out.print("\033[H\033[2J");
                        System.out.println("Você deseja consultar emprestimos:\n1 - Atrasados\n2 - Ativos\n3 - Concluidos\n4 - Voltar ao início");
                        op3 = input.nextLine();
                            switch (op3) {
                                case "1":                                    
                                    System.out.print("\033[H\033[2J");
                                    System.out.println("Emprestimos atrasados.");
                                    for (Livro li : livros){
                                        if (li.getEmprestado() && li.getDataDevolução().isBefore(LocalDate.now()))
                                            System.out.println("Usuário responsável: " + li.getUsuarioResponsavel().getNome() + " - Data de devolução: " + li.getDataDevolução() + " - Livro: " + li.getTitulo());
                                    }
                                    System.out.println("Pressione enter para continuar.");
                                    input.nextLine();
                                    break;
                                case "2":

                                    System.out.print("\033[H\033[2J");
                                    System.out.println("Emprestimos ativos.");
                                    for (Livro li : livros){
                                        if (li.getEmprestado() && li.getDataDevolução().isAfter(LocalDate.now()))
                                            System.out.println("Usuário responsável: " + li.getUsuarioResponsavel().getNome() + " - Data de devolução: " + li.getDataDevolução() + " - Livro: " + li.getTitulo());
                                    }
                                    System.out.println("Pressione enter para continuar.");
                                    input.nextLine();
                                    break;
                                case "3":
                                    System.out.print("\033[H\033[2J");
                                    System.out.println("Emprestimos concluídos.");
                                    for (String emp : Emprestimo.empretimosConcluidos)
                                        System.out.println(emp);
                                    System.out.println("Pressione enter para continuar.");
                                    input.nextLine();
                                    break;
                                case "4":
                                    break;
                                default:
                                    break;
                            }
                        break;
                    case "3":
                        System.out.print("\033[H\033[2J");
                        System.out.println("Digite o CPF do usuário: ");
                        String cpf = input.nextLine();
                        u = Emprestimo.encontrarUsuario(cpf, usuarios);
                        if (u == null){
                            System.out.println("Usuário não encontrado. Pressione enter para continuar.");
                            input.nextLine();
                            break paginainicial;
                        }
                        System.out.println("Digite o ISBN do livro a ser renovado: ");
                        String isbn = input.nextLine();
                        l = Emprestimo.encontrarLivro(isbn, livros);
                        if (l == null){
                            System.out.println("Livro não encontrado. Pressione enter para continuar.");
                            input.nextLine();
                            break paginainicial;
                        }
                        try {
                            Emprestimo.renovar(l, u);
                            System.out.println("Livro renovado. Pressione enter para continuar.");
                            input.nextLine();
                            break paginainicial;
                        }
                        catch (Exception e) {
                            System.out.println(e.getMessage() + ". Pressione enter para continuar.");
                            input.nextLine();
                        }   
                        break;
                    case "4":
                        System.out.print("\033[H\033[2J");
                        System.out.println("Digite o CPF do usuário: ");
                        cpf = input.nextLine();
                        u = Emprestimo.encontrarUsuario(cpf, usuarios);
                        if (u == null){
                            System.out.println("Usuário não encontrado. Pressione enter para continuar.");
                            input.nextLine();
                            break paginainicial;
                        }
                        System.out.println("Digite o ISBN do livro a ser devolvido: ");
                        isbn = input.nextLine();
                        l = Emprestimo.encontrarLivro(isbn, livros);
                        if (l == null){
                            System.out.println("Livro não encontrado. Pressione enter para continuar.");
                            input.nextLine();
                            break paginainicial;
                        }
                        try {
                            Emprestimo.devolver(l, u);
                            System.out.println("Livro devolvido. Pressione enter para continuar.");
                            input.nextLine();
                            break paginainicial;
                        }
                        catch (Exception e) {
                            System.out.println(e.getMessage() + "Pressione enter para continuar.");
                            input.nextLine();
                        }
                        break;
                    case "5":
                        break;
                    default:
                        break;
                }
                break;
                case "4":
                break;
                default:
                break;
            }
        } while (!op1.equals("4"));
        System.out.print("\033[H\033[2J");
        System.out.println("Programa Finalizado");
        input.close();
    }

}
