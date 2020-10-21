import java.util.*;
import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Main extends JFrame {
    private JTextField microsTF;
    private JTextField quantumTF;
    private JTextField contextoTF;
    private JTextField bloqueoTF;

    private ActionListener EnviarDatos = new ActionListener()
    {
        @Override
        public void actionPerformed(ActionEvent actionEvent)
        {
            // Obtener los datos
            try
            {
                int micros = Integer.parseInt(microsTF.getText());
                int quantum = Integer.parseInt(quantumTF.getText());
                int contexto = Integer.parseInt(contextoTF.getText());
                int bloqueo = Integer.parseInt(bloqueoTF.getText());
                if (micros<1 || quantum<1 || contexto<0 || bloqueo<0) {
                    throw new Exception("Input number error");
                }

                ArrayList<Proceso> lista = LeerArchivo("procesos.txt");
                System.out.println(micros);
                System.out.println(quantum);
                System.out.println(contexto);
                System.out.println(bloqueo);
                Despachador x = new Despachador(lista, micros, contexto, quantum, bloqueo);
                // Procesamiento de los datos
                x.iniciarDespacho(); //process se refiere a la clase donde realizar toda la asignación
                x.imprimirTablas();

            }
            catch (NumberFormatException e)
            {
                JOptionPane.showMessageDialog(null, "Inputs deben ser números enteros.");
            } catch (Exception e) {
                // crear una alerta de swing de error;
                JOptionPane.showMessageDialog(null,"Error en los valores ingresados.");
            }
        }
    };




    public static void main(String[] args) throws FileNotFoundException, IOException {
        //Scanner sc = new Scanner(System.in);
        
        //ArrayList<Proceso> lista = LeerArchivo("procesos.txt");
        //System.out.println(lista);

        //Todo bien

        new Main();

        





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
            int numBloq = 0;

            String[] temp = SepararConComa(linea);
            nombre2 = temp[0];
            te = Integer.parseInt(temp[1]);
            tiempoEntrada = Integer.parseInt(temp[2]);
            numBloq = Integer.parseInt(temp[3]);


            Proceso x = new Proceso(nombre2, te, tiempoEntrada, numBloq);
            lista.add(x);

        }
        lector2.close();
        return lista;
    }

    public static String[] SepararConComa (String linea){
        return linea.split(",");
      }



      private Main()
      {
          super();
          //Elementos de JPanel
          JButton boton = new JButton("Despachar");
          JLabel titulo = new JLabel("J-CAN");
          JLabel micros = new JLabel("Microprocesadores");
          JLabel quantum = new JLabel("Quantum");
          JLabel contexto = new JLabel("Cambio de contexto");
          JLabel bloqueo = new JLabel("Bloqueo");
          microsTF = new JTextField();
          quantumTF = new JTextField();
          contextoTF = new JTextField();
          bloqueoTF = new JTextField();
  
          // Posicionamiento de los elementos
          titulo.setBounds(100, 25, 300, 60);
          micros.setBounds(40, 100, 150, 40);
          quantum.setBounds(50, 240, 100, 40);
          contexto.setBounds(350, 200, 100, 40);
          bloqueo.setBounds(350, 240, 100, 40);
          microsTF.setBounds(150, 200, 100, 35);
          quantumTF.setBounds(150, 240, 100, 35);
          contextoTF.setBounds(450,200, 100, 35);
          bloqueoTF.setBounds(450, 240, 100, 35);
          boton.setBounds(300, 300, 100, 40);
  
          //Asignación de fuentes
          Font fuente = new Font("Arial", Font.BOLD, 14);
          titulo.setFont(new Font("Arial", Font.BOLD, 30));
          micros.setFont(fuente);
          quantum.setFont(fuente);
          contexto.setFont(fuente);
          bloqueo.setFont(fuente);
          microsTF.setFont(fuente);
          quantumTF.setFont(fuente);
          contextoTF.setFont(fuente);
          bloqueoTF.setFont(fuente);
  
          // Colocación de los elementos
          add(titulo);
          add(micros);
          add(quantum);
          add(contexto);
          add(bloqueo);
          add(microsTF);
          add(quantumTF);
          add(contextoTF);
          add(bloqueoTF);
          add(boton);
          setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          setSize(650, 430);
          setLayout(null);
          setVisible(true);
          getContentPane().setBackground(new Color(115, 185, 255));
          //Listeners
          boton.addActionListener(EnviarDatos);
      }



        
}