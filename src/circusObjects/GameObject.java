package circusObjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

import Entities.Player1;
import utils.Constants.ObjectConstants;
import utils.LevelsCreation;

public class GameObject {
    protected int aniSpeed;
    protected int x, y, objType;
    protected Rectangle2D.Float hitbox;

    protected boolean active = true, lastAnimDoit = false;
    protected int aniTick, aniIndex;
    protected int xDrawOffset, yDrawOffset;

    public GameObject(int x, int y, int objType) {
        this.x = x;
        this.y = y;
        if (objType == LevelsCreation.RING) {
            this.y -= ObjectConstants.RING_Y_DRAW_OFFSET;
        } else if (objType == LevelsCreation.SRNG) {
            this.y -= ObjectConstants.SMALL_RING_Y_DRAW_OFFSET;
        }

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

    protected void updateAnimationTick(int objectType) {
        int finalIndex = Integer.parseInt(ObjectConstants.getSpritesInfo(objectType)[1]);
        aniSpeed = 20;
        if (objectType == ObjectConstants.RING) {
            aniSpeed = 10;
            finalIndex = 19;
            if (!active) {
                finalIndex = Integer.parseInt(ObjectConstants.getSpritesInfo(objectType)[1]);
            }

        }
        if (objectType == ObjectConstants.TRAMPOLINE) {
            aniSpeed = 4;
        }

        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= finalIndex) {
                if (!active) {
                    lastAnimDoit = true;
                    return;
                }

                if (objectType == ObjectConstants.TRAMPOLINE) {
                    lastAnimDoit = true;
                }

                aniIndex = 0;
                if (objectType == ObjectConstants.RING)
                    aniIndex = 18;
            }
        }
    }

    public void drawHitbox(Graphics g, int xLvlOffset) {
        g.setColor(Color.RED);
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

    public boolean getLastAnimDoit() {
        return lastAnimDoit;
    }

    public void setLastAnimDoit(boolean lastAnimDoit) {
        this.lastAnimDoit = lastAnimDoit;
    }

    public void resetObject() {
        aniIndex = 0;
        aniTick = 0;
        active = true;
        lastAnimDoit = false;
    }

    public void setAniIndex(int aniIndex) {
        this.aniIndex = aniIndex;
    }
}