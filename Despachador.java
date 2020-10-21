import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class Despachador {
    private ArrayList<Proceso> listaProcesos = new ArrayList<>(); // lista simple con los procesos
    private Queue<Proceso> colaProcesos = new LinkedList<Proceso>(); // cola para que entren y salgan procesos.
    private int cambioContexto; // c
    private int quantum; // q
    private int bloqueo; // b
    private ArrayList<Microprocesador> listaMicros = new ArrayList<Microprocesador>();

    public Despachador(ArrayList<Proceso> listaProcesos, int nMicros, int cambioContexto, int quantum, int bloqueo) {
        this.listaProcesos = listaProcesos;
        this.cambioContexto = cambioContexto;
        this.quantum = quantum;
        this.bloqueo = bloqueo;
        
        for (int i = 0; i < nMicros; i++) {
            Microprocesador m = new Microprocesador(i+1, cambioContexto, quantum, bloqueo);
            listaMicros.add(m);
        }
        /*
        for(Microprocesador m : listaMicros){
            System.out.println(m);
        }
        */

    }

    public void iniciarDespacho() { //startDispatch
        /* Este proceso simula lo que teníamos anteriormente afuera en Main.
        * A partir de una lista de procesos ordenada por tiempos de entrada,
        * Los va metiendo a la cola y despachando. Usa una variable startTime para
        * detectar cuando pasamos al siguiente tiempo de entrada.
        * Añade a la cola uno a uno de los procesos, cuando hay un cambio de tiempo de lote,
        * los despacha y crea los huecos hasta dicho tiempo.*/

        int tiempoInicio = 0;
        for (int i=0; i<listaProcesos.size(); i++) {
            // ciclo que recorre todos los procesos de la lista simple
            if (listaProcesos.get(i).getTiempoEntrada() != tiempoInicio) {
                this.despachar();
                this.detectarSaltos( listaProcesos.get(i).getTiempoEntrada() );
            }
            colaProcesos.add(listaProcesos.get(i));     // en cada vuelta agrega el proceso a la cola.
        }
        // al final del ciclo queda un proceso por despachar en la cola.
        this.despachar();
        this.eliminarSalto();
    }
    public void  despachar() {
        // Discriminar por tiempos de micro,
        while (colaProcesos.size()!=0){
            // debe leer los tiempos de todos los micros y escoger el de menor tiempo, cuidando prioridad de id.
            // el de menor tiempo se le asigna y procesa el primer proceso en la cola.
            //System.out.println("Proceso " + colaProcesos.peek().getNombre() + " a:" + this.seleccionarMicro());
            this.seleccionarMicro().ejecutarProceso(colaProcesos.remove());
        }
        // en teoría ya acabamos los procesos en este punto. Se quedan no-vacíos por ahora.
        //System.out.println("Se terminó de procesar la tanda. ");
    }

    public void detectarSaltos(int nuevoInicio) { //nextStartTime
        for (Microprocesador m: listaMicros) {
            if (m.getDuracion()<nuevoInicio) {
                m.esperar(nuevoInicio);
            }       // aquellos que no cumplen la condición tienen tiempo de uso igual o mayor al nextStartTime. No se les hace nada.
        }
    }

    public void eliminarSalto() {
        for (Microprocesador micro : listaMicros) {
            Proceso ultimo = micro.getCompletados().get(micro.getCompletados().size()-1);
            if(ultimo.getNombre() == "Salto") {
                micro.setDuracion(micro.getDuracion() - (ultimo).getTe());
                micro.getCompletados().remove(micro.getCompletados().size()-1);
            }
        }
    }


    public Microprocesador seleccionarMicro(){
        Microprocesador min = Collections.min(listaMicros);
        return min;
    }

    public void imprimirTablas(){
        GUI it = new GUI(listaMicros);
        it.crearGUI(listaMicros);
        /*
        for (Microprocesador m: listaMicros){
            System.out.println("Micro: " + m.getId());
            System.out.println("Proceso\t TCC\t TE \t TVC\t TB \t TT \t TI \t TF");
            for (Proceso p : m.getCompletados()){
                if (p.getNombre()!="Salto"){
                    System.out.format("%3s%8d%8d%8d%8d%8d%8d%8d%n", p.getNombre(), p.getTcc(), p.getTe(), p.getTvc(), p.getNumBloq(),
                    p.getTiempoTotal(), p.getTiempoInicial(), p.getTiempoFinal());
                }else{
                    System.out.format("%3s%8d%8d%8d%8d%8d%8d%8d%n", p.getNombre(), p.getTcc(), 0, p.getTvc(), p.getNumBloq(),
                            p.getTiempoTotal(), p.getTiempoInicial(), p.getTiempoFinal());
                }
            }
            System.out.println("Tiempo total Micro " + m.getId() + " = " + m.getDuracion());
            System.out.println();
        }*/
    }


    public ArrayList<Proceso> getListaProcesos() {
        return this.listaProcesos;
    }

    public void setListaProcesos(ArrayList<Proceso> listaProcesos) {
        this.listaProcesos = listaProcesos;
    }

    public Queue<Proceso> getColaProcesos() {
        return this.colaProcesos;
    }

    public void setColaProcesos(Queue<Proceso> colaProcesos) {
        this.colaProcesos = colaProcesos;
    }

    public int getCambioContexto() {
        return this.cambioContexto;
    }

    public void setCambioContexto(int cambioContexto) {
        this.cambioContexto = cambioContexto;
    }

    public int getQuantum() {
        return this.quantum;
    }

    public void setQuantum(int quantum) {
        this.quantum = quantum;
    }

    public int getBloqueo() {
        return this.bloqueo;
    }

    public void setBloqueo(int bloqueo) {
        this.bloqueo = bloqueo;
    }

    public ArrayList<Microprocesador> getListaMicros() {
        return this.listaMicros;
    }

    public void setListaMicros(ArrayList<Microprocesador> listaMicros) {
        this.listaMicros = listaMicros;
    }

}