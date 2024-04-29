package Entities;

import static utils.Constants.CircusConstants.TILES_SIZE;

import utils.Constants;
import utils.Helpers;

public class Enemy extends Entity {
    protected int aniSpeed;
    protected int enemyAction, enemyType;

    protected boolean firstUpdate = true;

    protected int walkDir = Constants.Directions.LEFT;
    protected float xSpeed = 0;
    protected boolean jump = false;

    // * Va a controlar la vida de los enemigos
    protected boolean active;

    public Enemy(float x, float y, int width, int height, int enemyType) {
        super(x, y, width, height);

        this.enemyType = enemyType;
        this.walkSpeed = 0.5f;

        initHitbox(width, height);
    }

    // * Se encarga de hacer el primer chequeo, al comienzo chequea si el enemigo
    // * está en el aire
    protected void firstUpdateCheck(int[][] levelData) {
        if (!Helpers.IsEntityOnFloor(hitbox, levelData)) {
            inAir = true;
        }
        firstUpdate = false;
    }

    // * Para la animación, al igual que el player
    protected void updateAnimationTick() {
        aniSpeed = 30;
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= Integer.parseInt(Constants.EnemyConstants.getEnemySpritesInfo(enemyAction)[1])) {
                aniIndex = 0;
            }
        }
    }

    // * Actualiza los movimientos en aire de los enemigos
    protected void updateMovesInAir(int[][] levelData) {
        if (Helpers.CanMoveHere(hitbox.x, hitbox.y + airSpeed + TILES_SIZE, hitbox.width, hitbox.height + 1,
                levelData)) {
            hitbox.y += airSpeed;
            airSpeed += Constants.CircusConstants.GRAVITY;
        } else {
            // inAir = false;
            hitbox.y = Helpers.getEntityYPosUnderRoofOrAboveFloor(hitbox, airSpeed);
            if (inAir) {
                if (airSpeed > 0) {
                    inAir = false;
                    jump = false;
                    airSpeed = 0;
                } else {
                    airSpeed += Constants.CircusConstants.GRAVITY;
                }
                hitbox.y += airSpeed;
            }
        }
    }

    // * Actualiza los movimientos en x
    protected void updateXMoves(int[][] levelData) {
        // float xSpeed = 0;

        if (walkDir == Constants.Directions.LEFT) {
            xSpeed = -walkSpeed;
        } else {
            xSpeed = walkSpeed;
        }
        if (Helpers.CanMoveHere(hitbox.x + xSpeed, hitbox.y, hitbox.width, hitbox.height, levelData)) {
            if (Helpers.isFloor(hitbox, xSpeed, levelData)) {
                hitbox.x += xSpeed;
                return;
            }
            hitbox.x += xSpeed;
        }

        if (!inAir)
            changeWalkDir();
    }

    protected void changeWalkDir() {
        if (walkDir == Constants.Directions.LEFT)
            walkDir = Constants.Directions.RIGHT;
        else
            walkDir = Constants.Directions.LEFT;
    }

    // * Actualiza es estado (accion) de los enemigos
    protected void changeAction(int newAction) {
        this.enemyAction = newAction;
        aniTick = 0;
        aniIndex = 0;
    }

    public int getEnemyAction() {
        return enemyAction;
    }

    public int getAniIndex() {
        return aniIndex;
    }

    public boolean isActive() {
        return active;
    }
}
