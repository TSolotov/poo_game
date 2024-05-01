package circusObjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

import Entities.Player1;
import utils.Constants.ObjectConstants;

public class GameObject {
    protected int aniSpeed;
    protected int x, y, objType;
    protected Rectangle2D.Float hitbox;
    protected boolean active = true;
    protected int aniTick, aniIndex;
    protected int xDrawOffset, yDrawOffset;

    public GameObject(int x, int y, int objType) {
        this.x = x;
        this.y = y;
        this.objType = objType;
    }

    protected void initHitbox(int width, int height) {
        hitbox = new Rectangle2D.Float(x, y, width, height);
    }

    protected void checkIntersectHitboxes(Player1 player) {
        if (hitbox.intersects(player.getHitbox())) {
            player.subtrackLife();
        }
    }

    protected void updateAnimationTick() {
        aniSpeed = 30;
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= Integer.parseInt(ObjectConstants.getSpritesInfo(ObjectConstants.FLAME)[1])) {
                aniIndex = 0;
            }
        }
    }

    public void drawHitbox(Graphics g, int xLvlOffset) {
        g.setColor(Color.PINK);
        g.drawRect((int) hitbox.x - xLvlOffset, (int) hitbox.y, (int) hitbox.width, (int) hitbox.height);
    }

    // * Getters & Setters
    public int getObjType() {
        return objType;
    }

    public Rectangle2D.Float getHitbox() {
        return hitbox;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getxDrawOffset() {
        return xDrawOffset;
    }

    public int getyDrawOffset() {
        return yDrawOffset;
    }

    public int getAniIndex() {
        return aniIndex;
    }

    public void resetObject() {
        aniIndex = 0;
        aniTick = 0;
        active = true;
    }

}
