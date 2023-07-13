package iff.poo.projetos.emprestimo;

import java.time.LocalDate;

import iff.poo.projetos.livro.Livro;
import iff.poo.projetos.usuario.Usuario;

public class Emprestiamo {
    
    private Usuario usuarioResponsavel;
    private Livro livroEmprestado;
    private LocalDate dataInicio, dataFinal, dataDevolução;
    private Boolean renovado = Boolean.FALSE;

    public Emprestimo(Usuario usuarioResponsavel, Livro livroEmprestado, LocalDate dataInicio, LocalDate dataFinal) {
        this.usuarioResponsavel = usuarioResponsavel;
        this.livroEmprestado = livroEmprestado;
        this.dataInicio = dataInicio;
        this.dataFinal = dataFinal;
    }
    
    public Usuario getUsuarioResponsavel() {
        return usuarioResponsavel;
    }
    public void setUsuarioResponsavel(Usuario usuarioResponsavel) {
        this.usuarioResponsavel = usuarioResponsavel;
    }
    public Livro getLivroEmprestado() {
        return livroEmprestado;
    }
    public void setLivroEmprestado(Livro livroEmprestado) {
        this.livroEmprestado = livroEmprestado;
    }
    public LocalDate getDataInicio() {
        return dataInicio;
    }
    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }
    public LocalDate getDataFinal() {
        return dataFinal;
    }
    public void setDataFinal(LocalDate dataFinal) {
        this.dataFinal = dataFinal;
    }
    public LocalDate getDataDevolução() {
        return dataDevolução;
    }
    public void setDataDevolução(LocalDate dataDevolução) {
        this.dataDevolução = dataDevolução;
    }
    public Boolean getRenovado() {
        return renovado;
    }
    public void setRenovado(Boolean renovado) {
        this.renovado = renovado;
    }

}
