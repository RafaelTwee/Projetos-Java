
public class Usuario {
    private static int UsuariosCadastrados = 0;
    private Livro livroEmprestado;
    private Boolean possuiEmprest = false;

    public Livro getLivroEmprestado() {
        return livroEmprestado;
    }

    public void setLivroEmprestado(Livro livroEmprestado) {
        this.livroEmprestado = livroEmprestado;
    }

    public Boolean getPossuiEmprest() {
        return possuiEmprest;
    }

    public void setPossuiEmprest(Boolean possuiEmprest) {
        this.possuiEmprest = possuiEmprest;
    }

    private String cpf, nome;

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public Usuario(String nome, String cpf) throws Exception {
        this.nome = nome;
        if (validarCpf(cpf))
                this.cpf = cpf.replace(".", "").replace("-", "").trim();
        else
            throw new Exception("CPF inválido. ");
        UsuariosCadastrados++;
    }

    public static Boolean validarCpf(String cpf){
        cpf = cpf.replace(".", "").replace("-", "").trim();
        if (cpf.length() != 11)
            return false;
        for (int i = 0; i <= 10; i++) {
            if (Character.isLetter(Character.getNumericValue(cpf.charAt(i))))
                return false;
        }
        if (((calculoPrimeiroDigito(cpf) < 2) && (Character.getNumericValue(cpf.charAt(9)) == 0)) || ((calculoPrimeiroDigito(cpf) >= 2) && Character.getNumericValue(cpf.charAt(9)) == 11 - calculoPrimeiroDigito(cpf))) {
            if ((calculoSegundoDigito(cpf) < 2) && (Character.getNumericValue(cpf.charAt(10)) == 0))
                return true;
            else if ((calculoSegundoDigito(cpf) >= 2) && (Character.getNumericValue(cpf.charAt(10)) == 11 - calculoSegundoDigito(cpf)))
                return true;
            else
                return false;
        }
        else
            return false;
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
    public int getUsuariosCadastrados(){
        return UsuariosCadastrados;
    }
}

