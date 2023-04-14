public class Horario {
    
    private int hora, minuto, segundo;
    
    public Horario (int hora, int minuto, int segundo) throws Exception{

        try {
            setHora(hora);
            setMinuto(minuto);
            setSegundo(segundo);
        }
        catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    //insere a hora
    public void setHora(int hora) throws Exception {
        if (hora > 0 && hora < 24) {
            this.hora = hora;
        }
        else {
            throw new Exception("Hora inválida");
        }
    }
    
    //insere o minuto
    public void setMinuto(int minuto) throws Exception {
        if (59 < minuto || minuto < 0) {
            throw new Exception("Minuto inválido");
        }
        else {
            this.minuto = minuto;
        }
    }
    
    //insere o segundo
    public void setSegundo(int segundo) throws Exception {
        if (59 < segundo || segundo < 0) {
            throw new Exception("Segundo inválido");
        }
        else {
            this.segundo = segundo;}
    }
    
    //retorna a string do horario atual
    public String horarioAtual() {
        return (String.format("%02d", hora) + ":" + String.format("%02d", minuto) +":"+ String.format("%02d", segundo));
    }

    public static void main(String[] args) {
        try {
            Horario hora = new Horario(22, 9, 8);
            System.out.println(hora.horarioAtual());
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}