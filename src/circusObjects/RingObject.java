package circusObjects;

import static utils.Constants.CircusConstants.TILES_SIZE;
import static utils.Constants.FrameConstants.FRAME_HEIGHT;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import Entities.Player1;
import levels.Level;
import utils.Constants.ObjectConstants;
import utils.Constants.Player1Constants;
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

    public void updateMove() {
        float movementSpeed = 0.1f;
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

    private void checkIntersectHitboxes2(Player1 player) {
        if (hitbox2.intersects(player.getHitbox())) {
            player.subtrackLife();
        }

        if (hitbox2.getX() < player.getHitbox().getX() + hitbox2.width - Player1Constants.REAL_WIDTH) {
            this.setActive(false);
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
    public void update(Player1 player) {
        updateAnimationTick(ObjectConstants.RING);
        updateMove();
        checkIntersectHitboxes(player);
        checkIntersectHitboxes2(player);
    }

    public void drawHitbox2(Graphics g, int xLvlOffset) {
        g.setColor(Color.GREEN);
        g.drawRect((int) hitbox2.x - xLvlOffset, (int) hitbox2.y, (int) hitbox2.width, (int) hitbox2.height);
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
