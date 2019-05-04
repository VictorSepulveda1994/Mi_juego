package graficos;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Victor Sepulveda
 */
public class HojaSprites {
    private final int ancho;
    private final int alto;
    public final int[] pixeles;
    
    //Colección de hojas de sprites
    public static HojaSprites texturasV1 = new HojaSprites("/recursos/texturas/texturasV1.png", 96, 768);
    //Fin de la colección
    
    //Constructor de "HojaSprites"
    public HojaSprites(final String ruta, final int ancho, final int alto){
        this.ancho = ancho;
        this.alto = alto;
        
        this.pixeles = new int[ancho * alto];
        
        //Obtención de los valores de cada pixel en la "HojaSprites"
        BufferedImage imagen;
        try {
            imagen = ImageIO.read(HojaSprites.class.getResource(ruta));
            imagen.getRGB(0, 0, ancho, alto, pixeles, 0, ancho);
        } catch (IOException ex) {
            Logger.getLogger(HojaSprites.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getAncho() {
        return ancho;
    }

    public int getAlto() {
        return alto;
    }
}
