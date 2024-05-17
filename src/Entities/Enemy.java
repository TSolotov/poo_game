package Entities;

import static utils.Constants.CircusConstants.TILES_SIZE;

import states.CircusPlaying;
import utils.Constants;
import utils.Constants.Player1Constants;
import utils.Helpers;
import utils.LevelsCreation;

public class Enemy extends Entity {
    protected int aniSpeed;
    protected int enemyAction, enemyType;

    protected boolean firstUpdate = true;

    protected int walkDir = Constants.Directions.RIGHT;
    protected float xSpeed = 0;
    protected boolean jump = false;

    // * Va a controlar la vida de los enemigos
    protected boolean active;

    public Enemy(float x, float y, int width, int height, int enemyType) {
        super(x, y, width, height);

        this.enemyType = enemyType;
        this.walkSpeed = 0.5f * Constants.SCALE;

        initHitbox(width, height);
    }

    // * Chequea la colision entre la hitbox del enemigo y la del player
    protected void checkIntersectHitboxes(Player1 player) {
        if (hitbox.intersects(player.getHitbox())) {
            player.subtrackLife();
        }

        if (hitbox.getX() < player.getHitbox().getX() + hitbox.width - Player1Constants.REAL_WIDTH && active) {
            this.setActive(false);
            if (enemyType == LevelsCreation.BOMB)
                CircusPlaying.setScore(100);
            else
                CircusPlaying.setScore(25);

        }
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

    public void resetEnemy() {
        hitbox.x = x;
        hitbox.y = y;
        firstUpdate = true;
        changeAction(0);
        active = true;
        airSpeed = 0;
        aniIndex = 0;
        walkDir = Constants.Directions.RIGHT;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
