package graficos;

/**
 *
 * @author Victor Sepulveda
 */
public final class Pantalla {
    private final int ancho;
    private final int alto;
    
    public final int[] pixeles;
    
    public Pantalla(final int ancho, final int alto){
        this.ancho = ancho;
        this.alto = alto;
        
        pixeles = new int[ancho * alto];
    }
    
    //Método para limpiar la pantalla
    public void limpiar(){
        for (int i = 0; i < pixeles.length; i++) {
            pixeles[i] = 0;
        }
    }
    
    //Método para mostrar en pantalla
    public void mostrar(final int compensacionX, final int compensacionY){
        for (int y = 0; y < alto; y++) {
            int posicionY = y + compensacionY;
            if(posicionY < 0 || posicionY >= alto){
                continue;
            }
            
            for (int x = 0; x < ancho; x++) {
                int posicionX = x + compensacionX;
                if(posicionX < 0 || posicionX >= ancho){
                    continue;
                }
                
                //Código para redibujar
                
            }
        }
    }
}
