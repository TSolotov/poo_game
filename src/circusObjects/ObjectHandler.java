package circusObjects;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Entities.Player1;
import levels.Level;
import utils.LoadSprites;
import utils.Constants.ObjectConstants;

public class ObjectHandler {

    private ArrayList<BufferedImage[]> flameSprites;
    private ArrayList<FlameObject> flames = new ArrayList<>();

    public ObjectHandler() {
        loadSprites();
    }

    private void loadSprites() {
        flameSprites = new ArrayList<>();
        flameSprites.add(LoadSprites.getSprites(ObjectConstants.getSpritesInfo(ObjectConstants.FLAME)));
    }

    public void addObjects(Level level) {
        flames = FlameObject.getFlames(level);
    }

    // ! Update & Draw
    public void update(Player1 player) {
        for (FlameObject flame : flames) {
            flame.update(player);
        }
    }

    public void draw(Graphics g, int xLevelOffset) {
        drawFlames(g, xLevelOffset);
    }

    private void drawFlames(Graphics g, int xLevelOffset) {
        for (FlameObject flame : flames) {
            g.drawImage(flameSprites.getFirst()[flame.getAniIndex()],
                    (int) flame.getHitbox().getX() - xLevelOffset - ObjectConstants.FLAME_X_DRAW_OFFSET,
                    (int) flame.getHitbox().getY() - ObjectConstants.FLAME_Y_DRAW_OFFSET,
                    ObjectConstants.FLAME_SPRITE_WIDTH,
                    ObjectConstants.FLAME_SPRITE_HEIGHT, null);
            flame.drawHitbox(g, xLevelOffset);
        }
    }
}
