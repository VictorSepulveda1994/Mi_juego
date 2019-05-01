package principal;

import controles.Teclado;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

/**
 *
 * @author Victor Sepulveda
 */
public class Juego extends Canvas implements Runnable{
    
    //Ancho y Alto de ventana
    private static final Toolkit TK = Toolkit.getDefaultToolkit();
    private static final int ANCHO = ((int) TK.getScreenSize().getWidth());
    private static final int ALTO = ((int) TK.getScreenSize().getHeight());
    
    //Variable para saber si el juego esta corriendo
    private static volatile boolean enFuncionamiento = false;
    
    //Nombre de la ventana
    private static final String NOMBRE ="Final War";
    
    //Variables "aps" y "fps"
    private static int aps = 0;
    private static int fps = 0;
    
    //Creación de variable "ventana"
    private static JFrame ventana;
        
    //Creción de nuevo hilo
    private static Thread hiloGraficos;
    
    //Creación clase teclado
    private static Teclado teclado;
    
    //Constructor de Juego
    private Juego(){
        //Inicialización del teclado y detección de teclas
        teclado = new Teclado();
        addKeyListener(teclado);
        
        //Características de la ventana y se muestra en pantalla
        setPreferredSize(new Dimension(ANCHO, ALTO));
        ventana = new JFrame(NOMBRE);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Quizas no necesario
        ventana.setResizable(false);
        ventana.setLayout(new BorderLayout());
        ventana.add(this, BorderLayout.CENTER);
        //Sin adornos en la ventana
        //ventana.setUndecorated(true);
        ventana.pack();
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
    }
    
    //Método Principal
    public static void main(String[] args){
        Juego juego = new Juego();
        juego.iniciar();
    }
    
    //Método para iniciar juego
    private synchronized void iniciar(){
        enFuncionamiento = true;
        
        hiloGraficos = new Thread(this,"Graficos");
        hiloGraficos.start();
    }
    
    //Método para detener juego
    private synchronized void detener(){
        enFuncionamiento = false;
        
        try {
            hiloGraficos.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Juego.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void actualizar(){
        teclado.actualizar();
        
        if(teclado.arriba){
            System.out.println("Arriba");
        }
        if(teclado.abajo){
            System.out.println("Abajo");
        }
        if(teclado.derecha){
            System.out.println("Derecha");
        }
        if(teclado.izquierda){
            System.out.println("Izquierda");
        }
        
        aps++;
    }
    
    private void mostrar(){
        fps++;
    }
    
    public void run() {
        final int NS_POR_SEGUNDO = 1000000000;
        final byte APS_OBJETIVO = 60;
        final double NS_POR_ACTUALIZACION = NS_POR_SEGUNDO / APS_OBJETIVO;
        
        long referenciaActualizacion = System.nanoTime();
        long referenciaContador = System.nanoTime();
        
        double tiempoTranscurrido;
        double delta = 0;
        
        requestFocus();
        
        while(enFuncionamiento){
            final long inicioBucle = System.nanoTime();
            
            tiempoTranscurrido = inicioBucle - referenciaActualizacion;
            referenciaActualizacion = inicioBucle;
            
            delta += tiempoTranscurrido / NS_POR_ACTUALIZACION;
            
            while(delta >= 1){
                actualizar();
                delta--;
            }
            
            mostrar();
            
            if(System.nanoTime() - referenciaContador > NS_POR_SEGUNDO){
                ventana.setTitle(NOMBRE + " || APS: " + aps + " || FPS:" + fps);
                aps = 0;
                fps = 0;
                referenciaContador = System.nanoTime();
            }
        }
    }
}
