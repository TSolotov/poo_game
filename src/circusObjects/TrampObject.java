package circusObjects;

import static utils.Constants.CircusConstants.TILES_SIZE;

import java.util.ArrayList;

import Entities.Player1;
import levels.Level;
import utils.LevelsCreation;
import utils.Constants.ObjectConstants;

public class TrampObject extends GameObject {

    public TrampObject(int x, int y, int objType) {
        super(x, y, objType);
        initHitbox(ObjectConstants.TRAMPOLINE_REAL_WIDTH, ObjectConstants.TRAMPOLINE_REAL_HEIGHT);
    }

    // * Helper para la creación de flamas
    public static ArrayList<TrampObject> getTrampolines(Level level) {
        ArrayList<TrampObject> tramp = new ArrayList<>();
        for (int i = 0; i < level.getLevelHeight(); i++) {
            for (int j = 0; j < level.getLevelWidth(); j++) {
                if (level.getTileToDraw(i, j) == LevelsCreation.TRMP
                        || level.getTileToDraw(i, j) == LevelsCreation.STRP) {
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

        // checkIntersectHitboxes(player);
    }

    private boolean isOverTrampoline(Player1 player) {
        return hitbox.intersects(player.getHitbox());
    }
}
