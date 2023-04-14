public class Singleton {
    private static Singleton singleton = new Singleton();
    public int a = 1;
    private Singleton(){}
    public static Singleton getInstance(){
        return singleton;
    }
    public static void main(String[] args) {
        Singleton objeto = Singleton.getInstance();
        Singleton objeto2 = Singleton.getInstance();
        objeto.a = 2;
        System.out.println(objeto2.a); 
    }
}
