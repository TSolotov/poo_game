package circusObjects;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Entities.Player1;
import levels.Level;
import utils.LoadSprites;
import utils.Constants.ObjectConstants;

public class ObjectHandler {

    private BufferedImage[] flameSprites;
    private BufferedImage[] ringSprites;
    private ArrayList<FlameObject> flames = new ArrayList<>();
    private ArrayList<RingObject> rings = new ArrayList<>();
    private ArrayList<RingObject> smallRings = new ArrayList<>();

    public ObjectHandler() {
        loadSprites();
    }

    private void loadSprites() {
        flameSprites = LoadSprites.getSprites(ObjectConstants.getSpritesInfo(ObjectConstants.FLAME));
        ringSprites = LoadSprites.getSprites(ObjectConstants.getSpritesInfo(ObjectConstants.RING));
    }

    public void addObjects(Level level) {
        flames = FlameObject.getFlames(level);
        rings = RingObject.getRings(level);
        smallRings = RingObject.getSmallRings(level);
    }

    // ! Update & Draw
    public void update(Player1 player) {
        for (FlameObject flame : flames) {
            flame.update(player);
        }

        for (RingObject ring : rings) {
            ring.update(player);
        }
        for (RingObject ring : smallRings) {
            ring.update(player);
        }
    }

    public void draw(Graphics g, int xLevelOffset) {
        drawFlames(g, xLevelOffset);
        drawRings(g, xLevelOffset);
        drawSmallRings(g, xLevelOffset);
    }

    private void drawRings(Graphics g, int xLevelOffset) {
        for (RingObject ring : rings) {
            g.drawImage(ringSprites[ring.getAniIndex()],
                    (int) ring.getHitbox().getX() - xLevelOffset - ObjectConstants.RING_X_DRAW_OFFSET,
                    (int) ring.getHitbox().getY() - ObjectConstants.RING_Y_DRAW_OFFSET,
                    ObjectConstants.RING_SPRITE_WIDTH,
                    ObjectConstants.RING_SPRITE_HEIGHT, null);
            ring.drawHitbox(g, xLevelOffset);
            ring.drawHitbox2(g, xLevelOffset);
        }
    }

    private void drawSmallRings(Graphics g, int xLevelOffset) {
        for (RingObject ring : smallRings) {
            g.drawImage(ringSprites[ring.getAniIndex()],
                    (int) ring.getHitbox().getX() - xLevelOffset - ObjectConstants.SMALL_RING_X_DRAW_OFFSET,
                    (int) ring.getHitbox().getY() - ObjectConstants.SMALL_RING_Y_DRAW_OFFSET,
                    ObjectConstants.SMALL_RING_SPRITE_WIDTH,
                    ObjectConstants.SMALL_RING_SPRITE_HEIGHT, null);
            ring.drawHitbox(g, xLevelOffset);
            ring.drawHitbox2(g, xLevelOffset);
        }
    }

    private void drawFlames(Graphics g, int xLevelOffset) {
        for (FlameObject flame : flames) {
            g.drawImage(flameSprites[flame.getAniIndex()],
                    (int) flame.getHitbox().getX() - xLevelOffset - ObjectConstants.FLAME_X_DRAW_OFFSET,
                    (int) flame.getHitbox().getY() - ObjectConstants.FLAME_Y_DRAW_OFFSET,
                    ObjectConstants.FLAME_SPRITE_WIDTH,
                    ObjectConstants.FLAME_SPRITE_HEIGHT, null);
            flame.drawHitbox(g, xLevelOffset);
        }
    }
}
