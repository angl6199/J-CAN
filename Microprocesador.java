import java.util.ArrayList;

public class Microprocesador implements Comparable<Microprocesador>{
    private int id;
    private int duracion; //tiempoTotal
    private int tb; //block
    private int tcc; //context
    private int quantum;
    private boolean esVacio;
    private ArrayList<Proceso> completados = new ArrayList<Proceso>(); //terminados
    
    public Microprocesador(int id, int tcc, int quantum, int tb) {
        this.id = id;
        this.duracion = 0;
        this.tcc = tcc;
        this.quantum = quantum;
        this.tb = tb;
        this.esVacio = true;
    }

    
    @Override
    public int compareTo(Microprocesador o) {
        if (this.duracion>o.duracion){
            return 1;
        }
        if (this.duracion<o.duracion){
            return -1;
        }
        return 0;
    }

    
}