package Entities;

import java.awt.geom.Rectangle2D;

public abstract class Entity {
    // * Posición y rectangulo de la entidad
    protected float x, y;
    protected int width, height;
    protected Rectangle2D.Float hitbox;

    // *
    protected int currentLives;
    protected float walkSpeed, airSpeed;
    protected boolean inAir = false;

    // * Tema de animaciones de las entides
    protected int aniTick, aniIndex;

    public Entity(float x, float y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    protected void initHitbox(int width, int height) {
        hitbox = new Rectangle2D.Float(x, y, width, height);
    }

    public Rectangle2D.Float getHitbox() {
        return hitbox;
    }
}
