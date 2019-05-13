package principal;

import controles.Teclado;
import escenarios.Escenario;
import escenarios.EscenarioGenerado;
import graficos.Pantalla;
import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
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
    
    //Strings para los APS y FPS
    private static String CONTADOR_APS = "";
    private static String CONTADOR_FPS = "";
    
    //Variables "aps" y "fps"
    private static int aps = 0;
    private static int fps = 0;
    
    //Variables para posición
    private static int x = 0;
    private static int y = 0;
    
    //Creación objeto "ventana"
    private static JFrame ventana;
    //Creción de nuevo hilo
    private static Thread hiloGraficos;
    //Creación objeto teclado
    private static Teclado teclado;
    //Creación objeto pantalla
    private static Pantalla pantalla;
    //Creación objeto escenario
    private static Escenario escenario;
    
    //Para manejar los pixeles dentro del juego
    private static BufferedImage imagen = new BufferedImage(ANCHO, ALTO, BufferedImage.TYPE_INT_RGB);
    private static int[] pixeles = ((DataBufferInt) imagen.getRaster().getDataBuffer()).getData();
    
    //Constructor de Juego
    private Juego(){
        //Inicialización de pantalla
        pantalla = new Pantalla(ANCHO, ALTO);
        
        //Inicialización de escenarioGenerado
        escenario = new EscenarioGenerado(128, 128);
        
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
        ventana.setUndecorated(true);
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
            y--;
        }
        if(teclado.abajo){
            y++;
        }
        if(teclado.derecha){
            x++;
        }
        if(teclado.izquierda){
            x--;
        }
        if(teclado.salir){
            System.exit(0);
        }
        
        aps++;
    }
    
    private void mostrar(){
        BufferStrategy estrategia = getBufferStrategy();
        
        if(estrategia == null){
            createBufferStrategy(3);
            return;
        }
        
        pantalla.limpiar();
        escenario.mostrar(x, y, pantalla);
        
        //Copia de array pantalla a array en juego
        System.arraycopy(pantalla.pixeles, 0, pixeles, 0, pixeles.length);
        
        //Se dibuja la imagen
        Graphics g = estrategia.getDrawGraphics();
        g.drawImage(imagen, 0, 0, getWidth(), getHeight(), null);
        g.setColor(Color.white);
        g.fillRect(ANCHO / 2, ALTO / 2, 32, 32);
        g.drawString(CONTADOR_APS, 10, 20);
        g.drawString(CONTADOR_FPS, 10, 35);
        g.dispose();
        
        //Se muestra en pantalla lo dibujado
        estrategia.show();
        
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
                //ventana.setTitle(NOMBRE + " || APS: " + aps + " || FPS:" + fps);
                CONTADOR_APS = "APS: " + aps;
                CONTADOR_FPS = "FPS: " + fps;
                
                aps = 0;
                fps = 0;
                referenciaContador = System.nanoTime();
            }
        }
    }
}
