package controles;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Victor Sepulveda
 */
public final class Teclado implements KeyListener{

    private final static int NUMERO_TECLAS = 120;
    private final boolean[] teclas = new boolean[NUMERO_TECLAS];
    
    //Variables a utilizar en el juego
    public boolean arriba;
    public boolean abajo;
    public boolean derecha;
    public boolean izquierda;
    
    //Asignar teclas a las variables
    public void actualizar(){
        arriba = teclas[KeyEvent.VK_UP];
        abajo = teclas[KeyEvent.VK_DOWN];
        derecha = teclas[KeyEvent.VK_RIGHT];
        izquierda = teclas[KeyEvent.VK_LEFT];
    }
    
    //Al teclear la(s) tecla(s)
    public void keyTyped(KeyEvent ke) {
        
    }
    
    //Al mantener presionada la(s) tecla(s)
    public void keyPressed(KeyEvent ke) {
        teclas[ke.getKeyCode()] = true;
    }
    
    //Al soltar tecla(s)
    public void keyReleased(KeyEvent ke) {
        teclas[ke.getKeyCode()] = false;
    }
    
}
