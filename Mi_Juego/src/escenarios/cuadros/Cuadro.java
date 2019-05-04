package escenarios.cuadros;

import graficos.Pantalla;
import graficos.Sprite;

/**
 *
 * @author Victor Sepulveda
 */
public abstract class Cuadro {
    public int x;
    public int y;
    
    public Sprite sprite;
    
    public Cuadro(Sprite sprite){
        this.sprite = sprite;
    }
    
    public void mostrar(int x, int y, Pantalla pantalla){
    }
    
    public boolean solido(){
        return false;
    }
}
