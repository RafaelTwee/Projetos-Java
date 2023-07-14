package iff.poo.projetos.emprestimo;

import iff.poo.projetos.livro.Livro;
import iff.poo.projetos.livro.LivroDAO;
import iff.poo.projetos.usuario.Usuario;
import iff.poo.projetos.usuario.UsuarioDAO;

public class EmprestimoController {
    
    EmprestimoDAO emprestimoDAO = new EmprestimoDAO();
    LivroDAO livroDAO = new LivroDAO();
    UsuarioDAO usuarioDAO = new UsuarioDAO();

    public void realizarEmprestimo(String cpf, String ISBN) throws Exception {
        for (Usuario u : usuarioDAO.getAll()) {
            if (u.getCpf().equals(cpf)) {
                for (Livro l : livroDAO.getAll()) {
                    if (l.getIsbn().equals(ISBN)){
                        if (emprestimoDAO.selecionarAtivo(cpf) == null) {
                            if (!(livroDAO.selecionarDisponiveis(ISBN).isEmpty())) {
                                emprestimoDAO.realizarEmprestimo(u, l);
                                return;
                            }
                            else {
                                throw new Exception("Não existe exemplar do livro disponivel para empréstimo");
                            }
                        }
                        else {
                            throw new Exception("O CPF já possui um emprestimo ativo");
                        }
                    }
                    else {
                        throw new Exception("O ISBN não está cadastrado");
                    }
                }
            }
            else {
                throw new Exception("O CPF não está cadastrado");
            }
        }

    }

    public void renovar(String cpf) throws Exception {
        if (emprestimoDAO.selecionarAtivo(cpf) != null) {
            if (emprestimoDAO.selecionarAtivo(cpf).getRenovado()) {
                throw new Exception("O emprestimo já foi renovado uma vez");
            }
            else {
                emprestimoDAO.renovar(usuarioDAO.selecionarPorCpf(cpf));
            }
        }
        else {
            throw new Exception("O CPF não possue empréstimo a ser renovado");
        }
    }

    public void entregar(String cpf) throws Exception {
        if (usuarioDAO.selecionarPorCpf(cpf) != null) {
            if (emprestimoDAO.selecionarAtivo(cpf) != null) {
                emprestimoDAO.entregar(usuarioDAO.selecionarPorCpf(cpf));
            }
            else {
                throw new Exception("O usuário não possui emprestimos ativos");
            }
        }
        else { 
            throw new Exception("O CPF não está cadastrado");
        }
    }
}
