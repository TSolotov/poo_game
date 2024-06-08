package circus.entities;

import utils.Constants;
import utils.LevelsCreation;

import java.awt.geom.Rectangle2D;

import static utils.Constants.CircusConstants.TILES_SIZE;
import static utils.Constants.FrameConstants.FRAME_HEIGHT;
import static utils.Constants.Player1Constants.REAL_WIDTH;

public abstract class Entity {
    // * Posición y rectangulo de la entidad
    protected float x, y;
    protected int width, height;
    protected Rectangle2D.Float hitbox;

    // *
    protected int currentLives = 0;
    protected float walkSpeed, airSpeed;
    protected boolean inAir = false;

    // * Tema de animaciones de las entides
    protected int aniTick, aniIndex;

    public Entity(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    protected void initHitbox(int width, int height) {
        hitbox = new Rectangle2D.Float(x, y, width, height);
    }

    public Rectangle2D.Float getHitbox() {
        return hitbox;
    }

    public int getCurrentLives() {
        return currentLives;
    }

    // Colisiones

    /*
     * Se encarga de ver que el personaje no salga de la pantalla
     * O que el tile que está tocando sea solido - Nadie
     */
    private boolean isSolid(float x, float y, int[][] levelData) {
        if (x < 0 || x >= levelData[0].length * TILES_SIZE)
            return true;
        if (y < 0 || y >= FRAME_HEIGHT)
            return true;

        float xIndex = x / TILES_SIZE;
        float yIndex = y / TILES_SIZE;

        return isTileSolid((int) xIndex, (int) yIndex, levelData);
    }

    // * Verifica si el tile es sólido - Nadie
    private boolean isTileSolid(int xTile, int yTile, int[][] levelData) {
        int value = levelData[yTile][xTile];

        // * Si es un valor es distinto de void y menor a 30 es un hitbox sólido
        return value != LevelsCreation.VOID && value != LevelsCreation.WINN && value < 30;

    }

    // * Verifico si me puedo mover en este point - Enemy Hero
    protected boolean canMoveHere(float x, float y, float width, float height, int[][] levelData) {
        if (!isSolid(x, y, levelData))
            if (!isSolid(x + width, y + height, levelData))
                if (!isSolid(x + width, y, levelData))
                    return !isSolid(x, y + height, levelData);

        return false;
    }

    // * Veo si la entidad está sobre el suelo - Enemy Hero
    protected boolean isEntityOnFloor(Rectangle2D.Float hitbox, int[][] levelData) {
        if (!isSolid(hitbox.x, hitbox.y + hitbox.height + 1, levelData))
            return isSolid(hitbox.x + hitbox.width, hitbox.y + hitbox.height + 1, levelData);

        return true;
    }

    // * Controla que no sobrepase el suelo ni el techo - Enemy Hero
    protected float getEntityYPosUnderRoofOrAboveFloor(Rectangle2D.Float hitbox, float airSpeed) {
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

    // * Controla que no sobrepase las paredes - Hero
    protected float getEntityXPosNextToWall(Rectangle2D.Float hitbox, float xSpeed) {
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

    // * Verifica si es suelo lo que se está pisando - Enemy
    protected boolean isFloor(Rectangle2D.Float hitbox, float xSpeed, int[][] levelData) {
        if (xSpeed < 0)
            return isSolid(hitbox.x + xSpeed, hitbox.y + hitbox.height + 1, levelData);
        else
            return isSolid(hitbox.x + hitbox.width + xSpeed, hitbox.y + hitbox.height + 1, levelData);
    }
}
