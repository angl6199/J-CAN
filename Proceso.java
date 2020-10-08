public class Proceso {

    private String nombre; //id
    private int te; //exeTime
    private int tcc =0;  
    private int numBloq; //tb
    private int tiempoEntrada; //entryTime 
    private int tvc;
    private int tiempoTotal; //total
    private int tiempoInicial; //Tinicial
    private int tiempoFinal;

    public Proceso(String nombre, int te, int tiempoEntrada) {
        this.nombre = nombre;
        this.te = te;
        this.tiempoEntrada = tiempoEntrada;
    }
    
   
}