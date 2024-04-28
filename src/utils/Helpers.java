package utils;

import java.awt.geom.Rectangle2D;

import static utils.Constants.CircusConstants.*;
import static utils.Constants.Player1Constants.*;
import static utils.Constants.FrameConstants.*;

public class Helpers {

    // TODO - Colisiones acá

    /*
     * Se encarga de ver que el personaje no salga de la pantalla
     * O que el tile que está tocando sea solido
     */

    private static boolean isSolid(float x, float y, int[][] levelData) {
        if (x < 0 || x >= levelData[0].length * TILES_SIZE)
            return true;
        if (y < 0 || y >= FRAME_HEIGHT)
            return true;

        float xIndex = x / TILES_SIZE;
        float yIndex = y / TILES_SIZE;

        return isTileSolid((int) xIndex, (int) yIndex, levelData);
    }

    // * Verifica si el tile es sólido
    public static boolean isTileSolid(int xTile, int yTile, int[][] levelData) {
        int value = levelData[yTile][xTile];

        // * Si es un valor void retorna false, es decir, se puede mover por ese pixel
        if (value != LevelsCreation.VOID)
            return true;
        return false;

    }

    // * Verifico si me puedo mover en este point
    public static boolean CanMoveHere(float x, float y, float width, float height, int[][] levelData) {
        if (!isSolid(x, y, levelData))
            if (!isSolid(x + width, y + height, levelData))
                if (!isSolid(x + width, y, levelData))
                    return !isSolid(x, y + height, levelData);

        return false;
    }

    // * Veo si la entidad está sobre el suelo
    public static boolean IsEntityOnFloor(Rectangle2D.Float hitbox, int[][] levelData) {
        if (!isSolid(hitbox.x, hitbox.y + hitbox.height + 1, levelData))
            return isSolid(hitbox.x + hitbox.width, hitbox.y + hitbox.height + 1, levelData);

        return true;
    }

    // * Controla que no sobrepase el suelo ni el techo
    public static float getEntityYPosUnderRoofOrAboveFloor(Rectangle2D.Float hitbox, float airSpeed) {
        int currentTile = (int) (hitbox.y / TILES_SIZE);
        if (airSpeed > 0) {
            // * falling -
            int tileYPos = (currentTile + 2) * TILES_SIZE;
            int yOffset = (int) (TILES_SIZE - hitbox.height);
            return tileYPos + yOffset - 1;
        } else {
            // * jumping -
            return currentTile * TILES_SIZE;
        }
    }

    // * Controla que no sobrepase las paredes
    public static float getEntityXPosNextToWall(Rectangle2D.Float hitbox, float xSpeed) {
        int currentTile = (int) (hitbox.x / TILES_SIZE);

        if (xSpeed > 0) {
            // * Right
            int tileXPos = currentTile * TILES_SIZE;
            int xOffset = (int) (TILES_SIZE - hitbox.width);
            return tileXPos + xOffset + REAL_WIDTH - 5;
        } else {
            // * Left
            return currentTile * TILES_SIZE;

        }
    }
}
