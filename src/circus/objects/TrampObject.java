package circus.objects;

import static utils.Constants.CircusConstants.TILES_SIZE;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import circus.entities.Hero;
import circus.levels.Level;
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

    private void checkIntersectHitboxes2(Hero player) {
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

    public void update(Hero player) {
        if (isOverTrampoline(player)) {
            player.setMiniJump();
            setLastAnimDoit(false);
        }
        if (!getLastAnimDoit())
            updateAnimationTick(ObjectConstants.TRAMPOLINE);
        checkIntersectHitboxes2(player);
    }

    private boolean isOverTrampoline(Hero player) {
        return hitbox.intersects(player.getHitbox());
    }
}
