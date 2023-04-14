public class Data {
    private int dia, mes = 0, ano = -1;
    
    public Data (int dia, int mes, int ano) throws Exception{
        try {
            setDia(dia);
            setMes(mes);
            setAno(ano);
        }
        catch (Exception e){
            throw new Exception(e.getMessage() + String.format("%02d", dia) + "/" + String.format("%02d", mes) +"/"+ String.format("%04d", ano) + ") O objeto não foi criado.");
        }
    }

    //insere o ano e lança uma exceção com base no ano bissexto ou se o ano for inválido
    public void setAno(int ano) throws Exception{
        if (ano >= 0) {
            if (!verificarBissexto(ano) && (this.dia == 29 && this.mes == 2)){
                throw new Exception("Data Inválida (O dia é 29 de fevereiro, mas o ano não é bissexto): (");
            }
            this.ano = ano;
        }
        else {
            throw new Exception("Ano Inválido (");
        }
    }

    //insere o mes, mesmo se o dia n tiver sido definido
    public void setMes(int mes) throws Exception{
        if (this.dia == 0) {
            if (mes >= 1 && mes <= 12) {
                this.mes = mes;
            }
            else {
                throw new Exception("Mês Inválido: (");
            }
        }
        else {
            try {
                verificarDiaMes(this.dia, mes);
                this.mes = mes;
            }
            catch (Exception e){
                throw new Exception(e.getMessage());
            }
        }
    }

    //insere o dia, mesmo se o mes n tiver sido definido
    public void setDia(int dia) throws Exception {
        if (this.mes == 0) {
            if (dia >= 1 && dia <= 31) {
                this.dia = dia;
            }
            else {
                throw new Exception("Dia Inválido: (");
            }
        }
        else {
            try {
                verificarDiaMes(dia, this.mes);
                this.dia = dia;
            }
            catch (Exception e){
                throw new Exception(e.getMessage());
            }
        }
    }

    //verifica se o ano é bissexto e retorna falso caso o ano n tenha sido inserido
    private Boolean verificarBissexto(int ano) {
        if (ano > -1) {
            if ((ano%4 == 0 && ano%100 != 0) || (ano%400 == 0)){
                return true;
            }
            else {
                return false;
            }
        }
        else {
            return true;
        }
    }

    //verifica se o dia e mes são validos entre si, mesmo que o ano n tenha sido inserido
    private Boolean verificarDiaMes(int dia, int mes) throws Exception{
        if (mes >= 1 && mes <= 12){
            if ((dia >= 1 && dia <= 31)) {
                if ((dia == 29) && !verificarBissexto(ano) && (mes == 2)) {
                    throw new Exception("Data Inválida (O dia é 29 de fevereiro, mas o ano não é bissexto): (");
                }
                else if ((dia == 30) && (mes == 2)) {
                    throw new Exception("Data Inválida (O mês não possui 30 dias): (");
                }
                else if (((dia == 31) && ((mes == 2) || (mes == 4) || (mes == 6) || (mes == 9) || (mes == 11)))) {
                    throw new Exception("Data Inválida (O mês não possui 31 dias): (");
                }
                return true;
            }
            else {
                throw new Exception("Dia Inválido: (");
            }
        }
        else { 
            throw new Exception("Mês Inválido: (");
        }
    }

    //imprime a data no formato dd/mm/aaaa
    public String imprimirData() {
        return (String.format("%02d", dia) + "/" + String.format("%02d", mes) +"/"+ String.format("%04d", ano));
    }
    
    public static void main(String[] args) {
        try {
            Data data = new Data(30, 4, 2000);
            System.out.println(data.imprimirData());
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}