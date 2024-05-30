package circus.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import circus.levels.Level;
import utils.Constants;
import utils.LevelsCreation;
import utils.Constants.Directions;
import utils.Constants.EnemyConstants;

import static utils.Constants.CircusConstants.TILES_SIZE;

public class BombEnemy extends Enemy {

    private Rectangle2D.Float jumpBox;

    private final int RANGE_TO_JUMP = (int) (150 * Constants.SCALE);

    // * Controlan el salto de la bomba
    private float jumpSpeed = -2.25f * Constants.SCALE;

    public BombEnemy(float x, float y) {
        super(x, y, EnemyConstants.BOMB_SPRITE_WIDTH, EnemyConstants.BOMB_SPRITE_HEIGHT, LevelsCreation.BOMB);
        this.walkSpeed = 0.8f * Constants.SCALE;
        this.walkDir = Directions.RIGHT;
        this.active = true;
        initHitbox(EnemyConstants.BOMB_REAL_WIDTH, EnemyConstants.BOMB_REAL_HEIGHT);

        initJumpBox();
    }

    // * Verifica si la hitbox de salto y la del player intersectan, y salta
    private void checkIntersectJumpAndHitbox(Hero player) {
        if (jumpBox.intersects(player.hitbox))
            jump = true;
    }

    // * Esta es la hitbox donde el mono saltaría cuando lo detecte
    private void initJumpBox() {
        jumpBox = new Rectangle2D.Float(x, y, 32 * Constants.SCALE, 32 * Constants.SCALE);
    }

    public int flipX() {
        if (walkDir == Directions.RIGHT)
            return width - EnemyConstants.BOMB_REAL_WIDTH;
        else
            return -EnemyConstants.BOMB_REAL_WIDTH;
    }

    // TODO - Puede llegar a ser protected en Enemy
    public int flipW() {
        if (walkDir == Directions.RIGHT)
            return -1;
        else
            return 1;
    }

    public void updateMove(int[][] levelData, Hero player) {
        if (firstUpdate)
            firstUpdateCheck(levelData);

        if (jump) {
            if (!inAir) {
                inAir = true;
                airSpeed = jumpSpeed;
            }
        }

        if (!inAir) {
            if (!isEntityOnFloor(hitbox, levelData)) {
                inAir = true;
            }
        }

        // * Hace caer a los que están en el aire
        if (inAir) {
            updateMovesInAir(levelData);
            updateXMoves(levelData);
            if (airSpeed < 0)
                changeAction(Constants.EnemyConstants.BOMB_JUMP);
            else {
                changeAction(Constants.EnemyConstants.BOMB_FALLING);
            }
        } else {
            // !

            // !
            switch (enemyAction) {
                case EnemyConstants.BOMB_FALLING:
                    changeAction(Constants.EnemyConstants.BOMB_RUNNING);
                    break;
                case EnemyConstants.BOMB_RUNNING:
                    updateXMoves(levelData);
                    checkIntersectJumpAndHitbox(player);
                    break;

            }
        }
        checkIntersectHitboxes(player);

    }

    // * Helpers para la creación de enemigos
    public static ArrayList<BombEnemy> getBombs(Level level) {
        ArrayList<BombEnemy> bombs = new ArrayList<>();

        for (int i = 0; i < level.getLevelHeight(); i++) {
            for (int j = 0; j < level.getLevelWidth(); j++) {
                if (level.getTileToDraw(i, j) == LevelsCreation.BOMB) {
                    bombs.add(new BombEnemy(j * TILES_SIZE, i * TILES_SIZE));
                }
            }
        }
        return bombs;
    }

    // ! Update
    public void update(int[][] levelData, Hero player) {
        updateMove(levelData, player);
        updateJumpBox();
        updateAnimationTick();
    }

    // * Actualiza la posición de la jumpBox acorde a su direccion
    private void updateJumpBox() {
        if (walkDir == Directions.RIGHT) {
            jumpBox.x = hitbox.x + hitbox.width + RANGE_TO_JUMP;
        } else if (walkDir == Directions.LEFT) {
            jumpBox.x = hitbox.x - hitbox.width - RANGE_TO_JUMP;
        }
        jumpBox.y = hitbox.y;
    }

    // * Dibuja la hitbox donde al intersectarla la bomba salta
    public void drawJumpBox(Graphics g, int offeset) {
        g.setColor(Color.RED);
        g.drawRect((int) jumpBox.x - offeset, (int) jumpBox.y, (int) jumpBox.width, (int) jumpBox.height);
    }

}
