package circus.objects;

import static utils.Constants.CircusConstants.TILES_SIZE;
import static utils.Constants.FrameConstants.FRAME_HEIGHT;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import circus.entities.Hero;
import circus.levels.Level;
import states.CircusGame;
import utils.Constants.ObjectConstants;
import utils.Constants.Player1Constants;
import utils.Constants;
import utils.LevelsCreation;

public class RingObject extends GameObject {
    private Rectangle2D.Float hitbox2;

    public RingObject(int x, int y, int objType) {
        super(x, y, objType);

        if (objType == LevelsCreation.RING) {
            initHitbox(ObjectConstants.RING_REAL_WIDTH, ObjectConstants.RING_REAL_HEIGHT);
            initHitbox2();
        } else {
            initHitbox(ObjectConstants.SMALL_RING_REAL_WIDTH, ObjectConstants.SMALL_RING_REAL_HEIGHT);
            initHitbox2SMall();
        }
    }

    private void updateMove() {
        float movementSpeed = 0.1f * Constants.SCALE;
        hitbox.x -= movementSpeed;
        hitbox2.x -= movementSpeed;
    }

    private void initHitbox2() {
        hitbox2 = new Rectangle2D.Float(x,
                y + ObjectConstants.RING_SPRITE_HEIGHT - ObjectConstants.RING_REAL_HEIGHT
                        - ObjectConstants.RING_Y_DRAW_OFFSET,
                ObjectConstants.RING_REAL_WIDTH,
                ObjectConstants.RING_REAL_HEIGHT + FRAME_HEIGHT / 2);
    }

    private void initHitbox2SMall() {
        hitbox2 = new Rectangle2D.Float(x,
                y + ObjectConstants.SMALL_RING_SPRITE_HEIGHT - ObjectConstants.SMALL_RING_REAL_HEIGHT
                        - ObjectConstants.SMALL_RING_Y_DRAW_OFFSET,
                ObjectConstants.SMALL_RING_REAL_WIDTH,
                ObjectConstants.SMALL_RING_REAL_HEIGHT + FRAME_HEIGHT / 2);
    }

    private void checkIntersectHitboxes2(Hero player) {
        if (hitbox2.intersects(player.getHitbox())) {
            player.subtrackLife();
        }

        if (hitbox2.getX() < player.getHitbox().getX() + hitbox2.width - Player1Constants.REAL_WIDTH && active) {
            this.setActive(false);
            if (objType == LevelsCreation.SRNG)
                CircusGame.setScore(500);
            else
                CircusGame.setScore(50);

        }

    }

    // * Helper para la creación de los rings
    public static ArrayList<RingObject> getRings(Level level) {
        ArrayList<RingObject> rings = new ArrayList<>();
        for (int i = 0; i < level.getLevelHeight(); i++) {
            for (int j = 0; j < level.getLevelWidth(); j++) {
                if (level.getTileToDraw(i, j) == LevelsCreation.RING) {
                    rings.add(new RingObject(j * TILES_SIZE, i * TILES_SIZE, LevelsCreation.RING));
                }
            }
        }
        return rings;
    }

    public static ArrayList<RingObject> getSmallRings(Level level) {
        ArrayList<RingObject> rings = new ArrayList<>();
        for (int i = 0; i < level.getLevelHeight(); i++) {
            for (int j = 0; j < level.getLevelWidth(); j++) {
                if (level.getTileToDraw(i, j) == LevelsCreation.SRNG) {
                    rings.add(new RingObject(j * TILES_SIZE, i * TILES_SIZE, LevelsCreation.SRNG));
                }
            }
        }
        return rings;
    }

    // ! Update & Draw
    public void update(Hero player) {
        updateAnimationTick(ObjectConstants.RING);
        updateMove();
        checkIntersectHitboxes(player);
        checkIntersectHitboxes2(player);
    }

    public void resetRing() {
        hitbox.x = x;
        hitbox.y = y;
        hitbox2.x = x;
        hitbox2.y = y + ObjectConstants.RING_SPRITE_HEIGHT - ObjectConstants.RING_REAL_HEIGHT
                - ObjectConstants.RING_Y_DRAW_OFFSET;

        resetObject();
    }

    public void resetSmallRing() {
        hitbox.x = x;
        hitbox.y = y;
        hitbox2.x = x;
        hitbox2.y = y + ObjectConstants.SMALL_RING_SPRITE_HEIGHT - ObjectConstants.SMALL_RING_REAL_HEIGHT
                - ObjectConstants.SMALL_RING_Y_DRAW_OFFSET;

        resetObject();
    }

}
