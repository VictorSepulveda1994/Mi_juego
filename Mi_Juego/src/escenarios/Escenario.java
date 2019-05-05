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
        
        pantalla.establecerDiferencia(compensacionX, compensacionY);
        
        int o = compensacionX >> 5; //Division usando bit shifting
        int e = (compensacionX + pantalla.getAncho() + Cuadro.LADO) >> 5;
        int n = compensacionY >> 5;
        int s = (compensacionY + pantalla.getAlto() + Cuadro.LADO) >> 5;
        
        for(int y = n; y < s; y++){
            for(int x = o; x < e; x++){
                obtenerCuadro(x, y).mostrar(x, y, pantalla);
            }
        }
    }
    
    public Cuadro obtenerCuadro(final int x, final int y){
        if(x < 0 || y < 0 || x >= ancho || y >= alto){
            return Cuadro.VACIO;
        }
        switch(cuadros[x + y * ancho]){
            case 0:
                return Cuadro.AGUA;
            case 1:
            case 2:
                
            default:
                return Cuadro.VACIO;
        }
    }
}
