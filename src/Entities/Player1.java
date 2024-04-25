package Entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import states.CircusPlaying;
import utils.Constants;
import utils.LoadSprites;

import static utils.Constants.Player1Constants.*;

public class Player1 extends Entity {
    // * Contiene info respecto al estado de juego de circus
    private CircusPlaying playing;

    // * Sprites para las animaciones
    private ArrayList<BufferedImage[]> animations;
    private int playerAction = IDLE;

    // * Velocidad de animación
    private int aniSpeed;

    // * Movimiento
    private boolean left, right, jump, moving = false;
    private float jumpSpeed = -3.0f;
    private int flipX = 0, flipW = 1;

    public Player1(float x, float y, int width, int heigth, CircusPlaying playing) {
        super(x, y, width, heigth);
        this.playing = playing;
        loadAnimationsSprites();

        // TODO - Verificar estas constantes
        initHitbox(18, 33);

    }

    // * Carga los sprites de animacion del player en animations
    private void loadAnimationsSprites() {
        animations = new ArrayList<>();
        animations.addLast(LoadSprites.getSprites(getPlayer1SpritesInfo(IDLE)));
        animations.addLast(LoadSprites.getSprites(getPlayer1SpritesInfo(RUNNING)));
        animations.addLast(LoadSprites.getSprites(getPlayer1SpritesInfo(JUMP)));
        animations.addLast(LoadSprites.getSprites(getPlayer1SpritesInfo(FALLING)));
    }

    // * Controla la animación
    private void updateAnimationTick() {
        aniSpeed = 30;
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= Integer.parseInt(getPlayer1SpritesInfo(playerAction)[1])) {
                aniIndex = 0;
            }
        }

    }

    private void updatePosition() {

        moving = false;

        // * Si salta
        if (jump) {
            if (inAir)
                return;
            inAir = true;
            airSpeed = jumpSpeed;
        }

        if (!inAir) {
            if ((!right && !left) || (right && left)) {
                return;
            }
        }

        float xSpeed = 0;

        if (left) {
            xSpeed -= walkSpeed;
            flipX = width - 10;
            flipW = -1;

        }

        if (right) {
            xSpeed += walkSpeed;
            flipX = 0;
            flipW = 1;
        }

        updateXPos(xSpeed);

        moving = true;
    }

    private void updateXPos(float xSpeed) {
        hitbox.x += xSpeed;
    }

    private void setAnimations() {
        int startAnimation = playerAction;
        walkSpeed = 0.75f;

        if (moving)
            playerAction = RUNNING;
        else
            playerAction = IDLE;

        if (inAir) {
            if (airSpeed < 0)
                playerAction = JUMP;
            else
                playerAction = FALLING;
        }

        // * Resetea los sprites de animación
        if (startAnimation != playerAction) {
            this.aniIndex = 0;
            this.aniTick = 0;
        }
    }

    // ! Draw & Update Methods
    public void update() {
        updateAnimationTick();
        updatePosition();
        setAnimations();
    }

    public void draw(Graphics g) {
        // TODO - Ver variables
        int heightSprite = 44 * 2, widthSprite = 64 * 2;
        g.drawImage(animations.get(playerAction)[aniIndex], (int) hitbox.getX(), (int) hitbox.getY(),
                widthSprite * flipW,
                heightSprite, null);
    }

    // ! Setters y Getters
    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public void setJump(boolean jump) {
        this.jump = jump;
    }
}
