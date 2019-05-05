package escenarios;

import escenarios.cuadros.Cuadro;
import graficos.Pantalla;

/**
 *
 * @author Victor Sepulveda
 */
public abstract class Escenario {
    protected int ancho;
    protected int alto;
    
    protected int[] cuadros;
    
    public Escenario(int ancho, int alto){
        this.ancho = ancho;
        this.alto = alto;
        
        cuadros = new int[ancho * alto];
        generarEscenario();
    }
    
    public Escenario(String ruta){
        cargarEscenario(ruta);
    }

    protected void generarEscenario() {
    }

    private void cargarEscenario(String ruta) {
    }
    
    public void actualizar(){
    }
    
    public void mostrar(final int compensacionX, final int compensacionY, final Pantalla pantalla){
        int o = compensacionX >> 5; //Division usando bit shifting
        int e = (compensacionX + pantalla.getAncho()) >> 5;
        int n = compensacionY >> 5;
        int s = (compensacionY + pantalla.getAlto()) >> 5;
    }
    
    public Cuadro obtenerCuadro(final int x, final int y){
        switch(cuadros[x + y * ancho]){
            case 0:
                return Cuadro.AGUA;
            case 1:
            case 2:
                
            default:
                return null;
        }
    }
}
