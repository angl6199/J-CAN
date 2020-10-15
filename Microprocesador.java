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
    
    public void ejecutarProceso(Proceso p) { //process
        // calcula tiempos, actualiza tiempo total de micro, guarda tiempo inicial y final de proceso, añade proceso a terminados
        p.setTiempoInicial(this.duracion);
        int tiempoProceso = 0; //pTime
        if (!esVacio){
            tiempoProceso += tcc;
            p.setTcc(tcc);
        }
        tiempoProceso = tiempoProceso + p.getTe() + this.calcularTvc(p) + p.getNumBloq()*tb;
        p.setTiempoTotal(tiempoProceso);
        p.setTvc(this.calcularTvc(p));
        p.setNumBloq(p.getNumBloq()*tb);
        p.setTiempoFinal(p.getTiempoInicial() + tiempoProceso);

        this.duracion += tiempoProceso;
        this.completados.add(p);
        this.setEsVacio(false);

        
    }

    public void esperar(int tiempoEspera) { //wait  y timeToWait
        // si la listaTerminados no está vacía, checar si el último de la listaTerminados es un hueco.
        // si sí es hueco, le sumamos a su tiempo final el tiempo para el siguiente lote.
        // si sí está vacía, o no es hueco, entonces creamos el hueco.
        if (ultimoEsSalto()) {
            Proceso ultimo = this.getCompletados().get(this.getCompletados().size() - 1);
            ultimo.setTiempoTotal(ultimo.getTiempoTotal() + (tiempoEspera - this.duracion));
            ultimo.setTiempoFinal(ultimo.getTiempoTotal());
            this.duracion += tiempoEspera - this.duracion;
        } else {
            this.setEsVacio(true);
            Proceso salto = new Proceso("Salto", tiempoEspera - this.duracion, 0,0);
            salto.setTvc(0);
            salto.setNumBloq(0);
            salto.setTiempoTotal(salto.getTe());
            salto.setTiempoInicial(this.duracion);
            salto.setTiempoFinal(this.duracion + salto.getTe());
            this.duracion += salto.getTe();
            this.completados.add(salto);
            System.out.println("Salto de " + salto.getTe() + "ms en  Micro: " + this.id + ", duración = " + this.getDuracion());
        }
    }

    public boolean ultimoEsSalto(){
        if (!this.getCompletados().isEmpty()) {
            return (completados.get(completados.size()-1).getNombre() == "Salto");
        } else {
            return false;
        }
    }

    public int calcularTvc(Proceso p) { //calculateTVC
        if (p.getTe()%quantum == 0){       // no sobra tiempo. n-1 cambios.
            return ((p.getTe()/quantum - 1)*tcc);
        }
        return (p.getTe()/quantum)*tcc;
    }

 

    @Override
    public String toString() {
        return "Micro{" +
                "id=" + id +
                ", duración=" + duracion +
                '}';
    }



    
    
}