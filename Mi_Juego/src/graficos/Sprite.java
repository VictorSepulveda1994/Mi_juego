package graficos;

/**
 *
 * @author Victor Sepulveda
 */
public class Sprite {
    private final int lado;
    
    private int x;
    private int y;
    
    private HojaSprites hoja;
    public int[] pixeles;
    
    //Colección de sprites
    public static final Sprite AGUA = new Sprite(32, 2, 1, HojaSprites.texturasV1);
    public static final Sprite VACIO = new Sprite(32, 0);
    //Fín de la colección
    
    //Constructor 1 de "Sprite"
    public Sprite(final int lado, final int columna, final int fila, final HojaSprites hoja){
        this.lado = lado;
        
        pixeles = new int[lado * lado];
        
        this.x = columna * lado;
        this.y = fila * lado;
        this.hoja = hoja;
        
        //Obtención de los valores del "Sprite"
        for (int j = 0; j < lado; j++) {
            for (int i = 0; i < lado; i++) {
                pixeles[i + j * lado] = hoja.pixeles[(i + this.x) + (j + this.y) * this.hoja.getAncho()];
            }
        }
    }
    
    //Constructor 2 de "Sprite"
    public Sprite(final int lado, final int color){
        this.lado = lado;
        pixeles = new int[lado * lado];
        
        for (int i = 0; i < pixeles.length; i++) {
            pixeles[i] = color;
        }
    }

    public int getLado() {
        return lado;
    }
    
    
}
