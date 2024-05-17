package utils;

import java.awt.Point;
import java.awt.geom.Rectangle2D;

import levels.Level;

import static utils.Constants.CircusConstants.*;
import static utils.Constants.Player1Constants.*;
import static utils.Constants.FrameConstants.*;

public class Helpers {

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

        // * Si es un valor es distinto de void y menor a 30 es un hitbox sólido
        if (value != LevelsCreation.VOID && value != LevelsCreation.WINN && value < 30)
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
            return tileXPos + xOffset + REAL_WIDTH - (5 * Constants.SCALE);
        } else {
            // * Left
            return currentTile * TILES_SIZE;

        }
    }

    // * Verifica si es suelo lo que se está pisando
    public static boolean isFloor(Rectangle2D.Float hitbox, float xSpeed, int[][] levelData) {
        if (xSpeed < 0)
            return isSolid(hitbox.x + xSpeed, hitbox.y + hitbox.height + 1, levelData);
        else
            return isSolid(hitbox.x + hitbox.width + xSpeed, hitbox.y + hitbox.height + 1, levelData);
    }

    // * Me da la ubicación del spawn point
    public static Point getPlayerSpawn(Level level) {
        for (int i = 0; i < level.getLevelHeight(); i++) {
            for (int j = 0; j < level.getLevelWidth(); j++) {
                if (level.getTileToDraw(i, j) == LevelsCreation.SPWN) {
                    return new Point(j * TILES_SIZE, i * TILES_SIZE);
                }
            }
        }
        return new Point(TILES_SIZE, TILES_SIZE);
    }
}
