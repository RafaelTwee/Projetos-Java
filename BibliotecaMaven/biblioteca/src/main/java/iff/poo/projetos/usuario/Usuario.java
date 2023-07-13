package iff.poo.projetos.usuario;

public class Usuario {
    private String cpf, nome;

    public Usuario(String nome, String cpf){
        this.nome = nome;
        this.cpf = cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getCpf() {
        return cpf;
    }
    public String getNome() {
        return nome;
    }
}
