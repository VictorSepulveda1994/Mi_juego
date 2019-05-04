package escenarios;

import graficos.Pantalla;

/**
 *
 * @author Victor Sepulveda
 */
public abstract class Escenario {
    private int ancho;
    private int alto;
    
    private int[] cuadros;
    
    public Escenario(int ancho, int alto){
        this.ancho = ancho;
        this.alto = alto;
        
        cuadros = new int[ancho * alto];
        generarEscenario();
    }
    
    public Escenario(String ruta){
        cargarEscenario(ruta);
    }

    private void generarEscenario() {
    }

    private void cargarEscenario(String ruta) {
    }
    
    public void actualizar(){
    }
    
    public void mostrar(int compensacionX, int compensaciónY, Pantalla pantalla){
    }
}
