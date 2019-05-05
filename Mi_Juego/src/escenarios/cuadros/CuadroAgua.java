package escenarios.cuadros;

import graficos.Pantalla;
import graficos.Sprite;

/**
 *
 * @author Victor Sepulveda
 */
public class CuadroAgua extends Cuadro{
    
    public CuadroAgua(Sprite sprite) {
        super(sprite);
    }
    
    public void mostrar(int x, int y , Pantalla pantalla){
        pantalla.mostrarCuadro(x, y, this);
    }
}
