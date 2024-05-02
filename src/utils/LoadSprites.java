package utils;

import java.awt.image.BufferedImage;
import java.io.File;
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

    // * Para recortar sprites todo en uno - Al final se elimina
    public static BufferedImage[] getSpritesBySlices() {
        int spriteWidth = 512, spriteHeight = 512;

        BufferedImage[] sprites = getSprites(new String[] { "resources/cut_pictures/trampoline_", "1" });

        try {
            BufferedImage spriteSheet = sprites[0];

            int columns = spriteSheet.getWidth() / spriteWidth;
            int rows = spriteSheet.getHeight() / spriteHeight;
            int i = 0;
            for (int y = 0; y < rows; y++) {
                for (int x = 0; x < columns; x++) {
                    // Crear un nuevo sprite individual
                    BufferedImage sprite = spriteSheet.getSubimage(
                            x * spriteWidth, y * spriteHeight, spriteWidth, spriteHeight);

                    // Guardar el sprite individual como una nueva imagen
                    File output = new File("C:\\Users\\Fabricio\\Desktop\\cut\\" + "tramp_" + i + ".png");
                    ImageIO.write(sprite, "png", output);
                    i++;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return sprites;
    }
}
