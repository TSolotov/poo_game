package circusObjects;

import static utils.Constants.CircusConstants.TILES_SIZE;

import java.util.ArrayList;

import Entities.Player1;
import levels.Level;
import utils.LevelsCreation;
import utils.Constants.ObjectConstants;

public class FlameObject extends GameObject {

    public FlameObject(int x, int y, int objType) {
        super(x, y - ObjectConstants.FLAME_Y_DRAW_OFFSET + ObjectConstants.FLAME_Y_DISTANCE_TO_TOUCH_FLOOR, objType);
        initHitbox(ObjectConstants.FLAME_REAL_WIDTH, ObjectConstants.FLAME_REAL_HEIGHT);

    }

    // * Helper para la creación de flamas
    public static ArrayList<FlameObject> getFlames(Level level) {
        ArrayList<FlameObject> flames = new ArrayList<>();
        for (int i = 0; i < level.getLevelHeight(); i++) {
            for (int j = 0; j < level.getLevelWidth(); j++) {
                if (level.getTileToDraw(i, j) == LevelsCreation.FLME) {
                    flames.add(new FlameObject(j * TILES_SIZE, i * TILES_SIZE, LevelsCreation.FLME));
                }
            }
        }
        return flames;
    }

    public void update(Player1 player) {
        updateAnimationTick(ObjectConstants.FLAME);
        checkIntersectHitboxes(player);
    }

}
