package circus.entities;

import java.awt.Point;

import circus.levels.Level;
import utils.Constants.EnemyConstants;
import utils.Constants.Player1Constants;
import utils.Constants;
import utils.LevelsCreation;

import static utils.Constants.CircusConstants.TILES_SIZE;

public class Horse extends Entity {
    // * Velocidad de animación
    private int aniSpeed, aniIndex;
    private int horseAction = EnemyConstants.HORSE_WALK;

    public Horse(float x, float y) {
        super(x, y, EnemyConstants.HORSE_SPRITE_WIDTH, EnemyConstants.HORSE_SPRITE_HEIGHT);
        this.currentLives = 0;
        initHitbox(EnemyConstants.HORSE_REAL_WIDTH, EnemyConstants.HORSE_REAL_HEIGHT);
    }

    // * Chequea la colision entre la hitbox del enemigo y la del player
    protected void checkIntersectHitboxes(Hero player) {
        if (player.isDead()) {
            player.walkSpeed = 0;
            player.setRight(false);
            return;
        }

        if (hitbox.intersects(player.getHitbox())) {
            player.inAir = false;
            player.airSpeed = 0;
            player.setPlayerAction(Player1Constants.IDLE);
        }
        if (!player.inAir) {
            if (horseAction != EnemyConstants.HORSE_RUN)
                aniIndex = 0;
            player.walkSpeed = 2.0f * Constants.SCALE;
            horseAction = EnemyConstants.HORSE_RUN;
        } else {
            if (horseAction != EnemyConstants.HORSE_WALK)
                aniIndex = 0;
            player.walkSpeed = 1.0f * Constants.SCALE;
            player.setRight(true);
            horseAction = EnemyConstants.HORSE_WALK;
        }
    }

    // * Controla la animación
    private void updateAnimationTick() {
        aniSpeed = 30;
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;

            if (aniIndex >= Integer.parseInt(EnemyConstants.getEnemySpritesInfo(horseAction)[1])) {
                aniIndex = 0;
            }
        }
    }

    public void setSpawnPoint(Point spawn) {
        this.x = spawn.x;
        this.hitbox.x = this.x;
    }

    public Point getPosition() {
        return new Point((int) this.x, (int) this.y);
    }

    // * Helper para la creación de flamas
    public static Horse getHorse(Level level) {
        Horse horse = new Horse(-EnemyConstants.HORSE_SPRITE_WIDTH, -EnemyConstants.HORSE_REAL_HEIGHT);
        for (int i = 0; i < level.getLevelHeight(); i++) {
            for (int j = 0; j < level.getLevelWidth(); j++) {
                if (level.getTileToDraw(i, j) == LevelsCreation.HRSE) {
                    horse = new Horse(j * TILES_SIZE, i * TILES_SIZE);
                }
            }
        }
        return horse;
    }

    // ! Update & Draw
    public void update(Hero player) {
        if (circus.levels.LevelHandler.getNumberLevel() != 2) {
            player.resetVelocity();
            return;
        }
        setSpawnPoint(player.getPosition());
        updateAnimationTick();
        checkIntersectHitboxes(player);
    }

    // * Getters & Setters
    public int getHorseAction() {
        return horseAction;
    }

    public int getAniIndex() {
        return aniIndex;
    }

    public void resetHorse() {
        hitbox.x = x;
        hitbox.y = y;
        airSpeed = 0;
        aniIndex = 0;
    }

}
