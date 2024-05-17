package circusObjects;

import static utils.Constants.CircusConstants.TILES_SIZE;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import Entities.Player1;
import levels.Level;
import utils.LevelsCreation;
import utils.Constants.ObjectConstants;

public class TrampObject extends GameObject {
    private Rectangle2D.Float hitbox2;

    public TrampObject(int x, int y, int objType) {
        super(x, y, objType);
        initHitbox(ObjectConstants.TRAMPOLINE_REAL_WIDTH, ObjectConstants.TRAMPOLINE_REAL_HEIGHT);
        initHitbox2();
    }

    private void initHitbox2() {
        hitbox2 = new Rectangle2D.Float(x,
                y + ObjectConstants.TRAMPOLINE_SPRITE_HEIGHT - ObjectConstants.TRAMPOLINE_REAL_HEIGHT
                        - ObjectConstants.TRAMPOLINE_Y_DRAW_OFFSET + 5,
                5,
                ObjectConstants.TRAMPOLINE_REAL_HEIGHT - 5);
    }

    private void checkIntersectHitboxes2(Player1 player) {
        if (hitbox2.intersects(player.getHitbox())) {
            player.subtrackLife();
        }
    }

    // * Helper para la creación de flamas
    public static ArrayList<TrampObject> getTrampolines(Level level) {
        ArrayList<TrampObject> tramp = new ArrayList<>();
        for (int i = 0; i < level.getLevelHeight(); i++) {
            for (int j = 0; j < level.getLevelWidth(); j++) {
                if (level.getTileToDraw(i, j) == LevelsCreation.TRMP) {
                    tramp.add(new TrampObject(j * TILES_SIZE, i * TILES_SIZE, LevelsCreation.TRMP));
                }
            }
        }
        return tramp;
    }

    public void update(Player1 player) {
        if (isOverTrampoline(player)) {
            player.setMiniJump();
            setLastAnimDoit(false);
        }
        if (!getLastAnimDoit())
            updateAnimationTick(ObjectConstants.TRAMPOLINE);
        checkIntersectHitboxes2(player);
    }

    public void drawHitbox2(Graphics g, int xLvlOffset) {
        g.setColor(Color.PINK);
        g.drawRect((int) hitbox2.x - xLvlOffset, (int) hitbox2.y, (int) hitbox2.width, (int) hitbox2.height);
    }

    private boolean isOverTrampoline(Player1 player) {
        return hitbox.intersects(player.getHitbox());
    }
}
