import java.util.ArrayList;

public class Microprocesador implements Comparable<Microprocesador>{
    private int id;
    private int duracion; // tiempoTotal
    private int tb; // block
    private int tcc; // context
    private int quantum;
    private boolean esVacio; //isEmpty
    private ArrayList<Proceso> completados = new ArrayList<Proceso>(); // terminados

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
    
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDuracion() {
        return this.duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public int getTb() {
        return this.tb;
    }

    public void setTb(int tb) {
        this.tb = tb;
    }

    public int getTcc() {
        return this.tcc;
    }

    public void setTcc(int tcc) {
        this.tcc = tcc;
    }

    public int getQuantum() {
        return this.quantum;
    }

    public void setQuantum(int quantum) {
        this.quantum = quantum;
    }

    public boolean isEsVacio() {
        return this.esVacio;
    }

    public void setEsVacio(boolean esVacio) {
        this.esVacio = esVacio;
    }

    public ArrayList<Proceso> getCompletados() {
        return this.completados;
    }

    public void setCompletados(ArrayList<Proceso> completados) {
        this.completados = completados;
    }
    
    

    
}