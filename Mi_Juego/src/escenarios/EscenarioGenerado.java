package escenarios;

import java.util.Random;

/**
 *
 * @author Victor Sepulveda
 */
public class EscenarioGenerado extends Escenario{
    
    private static final Random ALEATORIO = new Random();
    
    public EscenarioGenerado(int ancho, int alto) {
        super(ancho, alto);
    }
    
    protected void generarEscenario(){
        for (int y = 0; y < alto; y++) {
            for (int x = 0; x < ancho; x++) {
                cuadros[x + y * ancho] = ALEATORIO.nextInt(3);
            }
        }
    }
    
}
