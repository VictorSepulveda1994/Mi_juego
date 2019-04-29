package principal;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author Victor Sepulveda
 */
public class Juego extends Canvas{
    
    //Ancho y Alto de ventana
    private static final int ANCHO = 800;
    private static final int ALTO = 600;
    //nombre de la ventana
    private static final String NOMBRE ="Juego";
    //Creación de variable "ventana"
    private static JFrame ventana;
    
    //Constructor de Juego
    private Juego(){
        //Ingreso de dimensión de la ventana
        setPreferredSize(new Dimension(ANCHO, ALTO));
        //Ingreso del nombre de la ventana
        ventana = new JFrame(NOMBRE);
        //Para cerrar totalmente la aplicación
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Ventana no redimensionable
        ventana.setResizable(false);
        //Organización de la ventana
        ventana.setLayout(new BorderLayout());
        //Añadimos canvas en el centro de la ventana
        ventana.add(this, BorderLayout.CENTER);
        //Método para contenido se ajuste al tamaño de la ventana
        ventana.pack();
        //Ventana en centro de escritorio
        ventana.setLocationRelativeTo(null);
        //Ventana visible
        ventana.setVisible(true);
    }
    
    //Método Main
    public static void main(String[] args){
        Juego juego = new Juego();
    }
}
