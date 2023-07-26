package iff.poo.projetos.controller;

import iff.poo.projetos.dao.EmprestimoDAO;
import iff.poo.projetos.dao.LivroDAO;
import iff.poo.projetos.dao.UsuarioDAO;

public class EmprestimoController {
    
    EmprestimoDAO emprestimoDAO = new EmprestimoDAO();
    LivroDAO livroDAO = new LivroDAO();
    UsuarioDAO usuarioDAO = new UsuarioDAO();

    /*realiza o emprestimo do primeiro isbn disponivel para o cpf*/
    public void realizarEmprestimo(String cpf, String ISBN) throws Exception {
        if (usuarioDAO.selecionarPorCpf(cpf) == null) {
            throw new Exception("O CPF não está cadastrado");
        }
        if (livroDAO.selecionarPorIsbn(ISBN).isEmpty()) {
            throw new Exception("O ISBN não está cadastrado");
        }
        if (emprestimoDAO.selecionarAtivo(cpf) != null) {
            throw new Exception("O CPF já possui um emprestimo ativo");
        }
        if (livroDAO.selecionarDisponiveis(ISBN).isEmpty()) {
            throw new Exception("Não existe exemplar do livro disponivel para empréstimo");
        }
        emprestimoDAO.realizarEmprestimo(usuarioDAO.selecionarPorCpf(cpf), livroDAO.selecionarDisponiveis(ISBN).get(0));
    }

    /*renova o emprestimo ativo para o cpf*/
    public void renovar(String cpf) throws Exception {
        if (usuarioDAO.selecionarPorCpf(cpf) == null) {
            throw new Exception("O CPF não está cadastrado");
        }
        if (emprestimoDAO.selecionarAtivo(cpf) == null) {
            throw new Exception("O CPF não possue empréstimo a ser renovado");
        }
        if (!emprestimoDAO.selecionarAtivo(cpf).getRenovado()) {
            throw new Exception("O emprestimo já foi renovado uma vez");
        }
        emprestimoDAO.renovar(usuarioDAO.selecionarPorCpf(cpf));
    }
    
    /*entrega o livro do emprestimo ativo para o cpf*/
    public void entregar(String cpf) throws Exception {
        if (usuarioDAO.selecionarPorCpf(cpf) == null) {
            throw new Exception("O CPF não está cadastrado");
        }
        if (emprestimoDAO.selecionarAtivo(cpf) == null) {
            throw new Exception("O usuário não possui emprestimos ativos");
        }
        emprestimoDAO.entregar(usuarioDAO.selecionarPorCpf(cpf));
    }
}
