package utils;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class LoadSprites {
    /*
     * Retorna un array de imagenes, esto es porque se utilizan sprites en
     * diferentes imagenes con cierto patron de nombre
     * por ejemplo, player_run_0.png, player_run_1.png, ..., etc.
     * Es más ineficiente, pero es más facil de entender el funcionamiento.
     * Y no hay que pensar en cortar en cierta cantidad de puntos la imágen
     */

    public static BufferedImage[] getSprites(String[] infoSprites) {
        // System.out.println(infoSprites[0] + " | " + infoSprites[1]);
        BufferedImage[] sprites = new BufferedImage[Integer.parseInt(infoSprites[1])];
        for (int i = 0; i < Integer.parseInt(infoSprites[1]); i++) {
            InputStream imageSprite = LoadSprites.class.getResourceAsStream("/" + infoSprites[0] + i + ".png");
            try {
                assert imageSprite != null;
                sprites[i] = ImageIO.read(imageSprite);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sprites;
    }
}
