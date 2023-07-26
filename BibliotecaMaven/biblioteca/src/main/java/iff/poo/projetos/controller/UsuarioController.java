package iff.poo.projetos.controller;

import iff.poo.projetos.dao.UsuarioDAO;
import iff.poo.projetos.model.Usuario;

public class UsuarioController {

    UsuarioDAO usuarioDAO = new UsuarioDAO();

    private static Boolean validarCpf(String cpf){
        cpf = cpf.replace(".", "").replace("-", "").trim();
        if (cpf.length() != 11) {
            return false;
        }
        for (int i = 0; i <= 10; i++) {
            if (Character.isLetter(Character.getNumericValue(cpf.charAt(i)))) {
                return false;
            }
        }
        if (((calculoPrimeiroDigito(cpf) < 2) && (Character.getNumericValue(cpf.charAt(9)) == 0)) || ((calculoPrimeiroDigito(cpf) >= 2) && Character.getNumericValue(cpf.charAt(9)) == 11 - calculoPrimeiroDigito(cpf))) {
            if ((calculoSegundoDigito(cpf) < 2) && (Character.getNumericValue(cpf.charAt(10)) == 0)) {
                return true;
            }
            else if ((calculoSegundoDigito(cpf) >= 2) && (Character.getNumericValue(cpf.charAt(10)) == 11 - calculoSegundoDigito(cpf))) {
                return true; 
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
    }

    private static int calculoPrimeiroDigito(String cpf){
        int somaDigitos = 0;
        for (int i = 0; i <= 8; i++)
            somaDigitos += Character.getNumericValue(cpf.charAt(i)) * (10 - i);
        return somaDigitos%11;
    }

    private static int calculoSegundoDigito(String cpf){
        int somaDigitos = 0;
        for (int i = 0; i <= 9; i++)
            somaDigitos += Character.getNumericValue(cpf.charAt(i)) * (11 - i);
        return somaDigitos%11;
    }
    
    public void inserir(Usuario usuario) throws Exception {
        
        if (usuarioDAO.selecionarPorCpf(usuario.getCpf()) != null) {
            throw new Exception("O CPF já está cadastrado");
        }
        if (!validarCpf(usuario.getCpf())) {
            throw new Exception("O CPF não é válido");
        }
        usuarioDAO.inserir(usuario);
    }

    public void removerPorCpf(String cpf) throws Exception {
        try {
            usuarioDAO.removerPorCpf(cpf);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("O CPF não está cadastrado");
        }
    }

    public Usuario selecionarPorCpf(String cpf) {
        return usuarioDAO.selecionarPorCpf(cpf);
	}
}
