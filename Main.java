import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        //Scanner sc = new Scanner(System.in);
        
        ArrayList<Proceso> lista = LeerArchivo("procesos.txt");
        System.out.println(lista);
    }

   

    public static ArrayList<Proceso> LeerArchivo(String nombre) throws FileNotFoundException, IOException{ //populate list
        ArrayList<Proceso> lista = new ArrayList<>();
        File archivo = new File (nombre);
        FileReader lector = new FileReader (archivo);
        BufferedReader lector2 = new BufferedReader(lector);

        String linea;

        while((linea = lector2.readLine()) != null){
            String nombre2 = "";
            int te = 0;
            int tiempoEntrada = 0;

            String[] temp = SepararConComa(linea);
            nombre2 = temp[0];
            te = Integer.parseInt(temp[1]);
            tiempoEntrada = Integer.parseInt(temp[2]);


            Proceso x = new Proceso(nombre2, te, tiempoEntrada);
            lista.add(x);

        }
        lector2.close();
        return lista;
    }

    public static String[] SepararConComa (String linea){
        return linea.split(",");
      }


        
}