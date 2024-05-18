package Entities;

import static utils.Constants.CircusConstants.TILES_SIZE;

import java.util.ArrayList;

import levels.Level;
import utils.Constants;
import utils.Constants.Directions;
import utils.Constants.EnemyConstants;
import utils.LevelsCreation;

public class ChickenEnemy extends Enemy {

    public ChickenEnemy(float x, float y) {
        super(x, y, EnemyConstants.CHICKEN_SPRITE_WIDTH, EnemyConstants.CHICKEN_SPRITE_HEIGHT, LevelsCreation.CKEN);
        this.active = true;
        initHitbox(EnemyConstants.CHICKEN_REAL_WIDTH, EnemyConstants.CHICKEN_REAL_HEIGHT);
    }

    public int flipX() {
        if (walkDir == Directions.RIGHT)
            return width - 0;
        else
            return 0;
    }

    public int flipW() {
        if (walkDir == Directions.RIGHT)
            return -1;
        else
            return 1;
    }

    // * Actualiza el movimiento del pollo
    public void updateMove(int[][] levelData, Player1 player) {
        if (firstUpdate) {
            changeAction(Constants.EnemyConstants.CHICKEN_FALLING);
            firstUpdateCheck(levelData);
        }

        // * Hace caer a los que están en el aire
        if (inAir) {
            updateMovesInAir(levelData);
            updateXMoves(levelData);
        } else {
            switch (enemyAction) {
                case EnemyConstants.CHICKEN_FALLING:
                    changeAction(Constants.EnemyConstants.CHICKEN_WALK);
                    break;
                case EnemyConstants.CHICKEN_WALK:
                    updateXMoves(levelData);
                    break;

            }
        }
        checkIntersectHitboxes(player);

    }

    // * Helpers para la creación de enemigos
    public static ArrayList<ChickenEnemy> getChickens(Level level) {
        ArrayList<ChickenEnemy> chickens = new ArrayList<>();
        for (int i = 0; i < level.getLevelHeight(); i++) {
            for (int j = 0; j < level.getLevelWidth(); j++) {
                if (level.getTileToDraw(i, j) == LevelsCreation.CKEN) {
                    chickens.add(new ChickenEnemy(j * TILES_SIZE, i * TILES_SIZE));
                }
            }
        }
        return chickens;
    }

    public void update(int[][] levelData, Player1 player) {
        updateMove(levelData, player);
        updateAnimationTick();
    }

}
