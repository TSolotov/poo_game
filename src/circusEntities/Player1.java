package circusEntities;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import audio.AudioPlayer;
import states.CircusPlaying;
import utils.Constants;
import utils.Helpers;
import utils.LoadSprites;

import static utils.Constants.CircusConstants.GRAVITY;
import static utils.Constants.Player1Constants.*;

public class Player1 extends Entity {
    // * Contiene info respecto al estado de juego de circus
    private CircusPlaying circusPlaying;
    private int[][] levelData;

    // * Sprites para las animaciones
    private ArrayList<BufferedImage[]> animations;
    private int playerAction = IDLE;
    private boolean isDead = false, deadAnimDoit = false;

    // * Velocidad de animación
    private int aniSpeed;

    // * Movimiento
    private boolean left, right, jump, moving = false;
    private float jumpSpeed = (-2.5f * Constants.SCALE), fallSpeedAfterCollision = (0.5f * Constants.SCALE);
    private int flipX = 0, flipW = 1;

    public Player1(float x, float y, int width, int heigth, CircusPlaying circusPlaying) {
        super(x, y, width, heigth);
        this.circusPlaying = circusPlaying;
        this.currentLives = 3;
        this.walkSpeed = 1.0f * Constants.SCALE;

        loadAnimationsSprites();
        initHitbox(REAL_WIDTH, REAL_HEIGHT);

        System.out.println(Constants.SCALE);
        System.out.println(GRAVITY);

    }

    public void setSpawnPoint(Point spawn) {
        this.x = spawn.x;
        this.y = spawn.y;
        this.hitbox.x = x;
        this.hitbox.y = y;
    }

    // * Carga los sprites de animacion del player en animations
    private void loadAnimationsSprites() {
        animations = new ArrayList<>();
        animations.addLast(LoadSprites.getSprites(getPlayer1SpritesInfo(IDLE)));
        animations.addLast(LoadSprites.getSprites(getPlayer1SpritesInfo(RUNNING)));
        animations.addLast(LoadSprites.getSprites(getPlayer1SpritesInfo(JUMP)));
        animations.addLast(LoadSprites.getSprites(getPlayer1SpritesInfo(FALLING)));
        animations.addLast(LoadSprites.getSprites(getPlayer1SpritesInfo(DEAD)));
    }

    // * Carga el nivel actual
    public void loadLevelData(int[][] levelData) {
        this.levelData = levelData;
        if (!Helpers.IsEntityOnFloor(hitbox, levelData))
            inAir = true;
    }

    // * Controla la animación
    private void updateAnimationTick() {
        aniSpeed = 30;
        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (playerAction == RUNNING && aniIndex % 2 == 1)
                circusPlaying.getGame().getAudioPlayer().playSounds(AudioPlayer.RUN);
            if (aniIndex >= Integer.parseInt(getPlayer1SpritesInfo(playerAction)[1])) {

                if (isDead)
                    deadAnimDoit = true;
                aniIndex = 0;
            }
        }

    }

    // * Para realizar un minisalto
    public void setMiniJump() {
        airSpeed = jumpSpeed / 2;
        inAir = true;
        CircusPlaying.setScore(25);
    }

    // * Actualiza la posicion en la que se encuentra el plauyer
    private void updatePosition() {
        moving = false;

        // * Si salta
        if (jump) {
            if (!inAir) {
                inAir = true;
                airSpeed = jumpSpeed;
                circusPlaying.getGame().getAudioPlayer().playSounds(AudioPlayer.JUMP);
            }
        }

        if (!inAir) {
            if ((!right && !left) || (right && left)) {
                return;
            }
        }

        float xSpeed = 0;

        if (left) {
            xSpeed -= walkSpeed;
            flipX = width - (int) (18 * Constants.SCALE);
            flipW = -1;

        }

        if (right) {
            xSpeed += walkSpeed;
            flipX = 0;
            flipW = 1;
        }

        if (!inAir) {
            if (!Helpers.IsEntityOnFloor(hitbox, levelData)) {
                inAir = true;
            }
        }

        if (inAir) {
            if (Helpers.CanMoveHere(hitbox.x, hitbox.y + airSpeed, hitbox.width, hitbox.height, levelData)) {
                hitbox.y += airSpeed;
                airSpeed += Constants.CircusConstants.GRAVITY;
                updateXPos(xSpeed);
            } else {
                hitbox.y = Helpers.getEntityYPosUnderRoofOrAboveFloor(hitbox, airSpeed);
                if (airSpeed > 0) {
                    inAir = false;
                    airSpeed = 0;
                } else {
                    airSpeed = fallSpeedAfterCollision;
                }
                updateXPos(xSpeed);
            }
        } else {
            updateXPos(xSpeed);
        }

        moving = true;
    }

    private void updateXPos(float xSpeed) {
        if (Helpers.CanMoveHere(hitbox.x + xSpeed, hitbox.y, hitbox.width, hitbox.height, levelData)) {
            hitbox.x += xSpeed;
        } else {
            hitbox.x = Helpers.getEntityXPosNextToWall(hitbox, xSpeed);
        }
    }

    // *
    private void setAnimations() {
        int startAnimation = playerAction;

        if (deadAnimDoit) {
            aniIndex = Integer.parseInt(getPlayer1SpritesInfo(playerAction)[1]) - 1;
            return;
        }

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

        if (isDead) {
            playerAction = DEAD;
        }

        // * Resetea los sprites de animación
        if (startAnimation != playerAction) {
            this.aniIndex = 0;
            this.aniTick = 0;
        }
    }

    // ! Draw & Update Methods
    public void update() {
        if (currentLives <= 0 && deadAnimDoit) {
            circusPlaying.setGameOver(true);
        }
        updateAnimationTick();
        updatePosition();
        setAnimations();
    }

    public void draw(Graphics g, int xLevelOffset) {
        g.drawImage(animations.get(playerAction)[aniIndex], (int) (hitbox.x - X_DRAW_OFFSET) - xLevelOffset + flipX,
                (int) (hitbox.getY() - Y_DRAW_OFFSET),
                SPRITE_WIDTH * flipW,
                SPRITE_HEIGHT, null);
        g.drawRect((int) hitbox.x - xLevelOffset, (int) hitbox.y, (int) hitbox.width, (int) hitbox.height);
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

    public void subtrackLife() {
        if (currentLives <= 0) {
            circusPlaying.setGameOver(true);
            return;
        }

        if (isDead)
            return;

        currentLives--;
        isDead = true;
        circusPlaying.togleLoseLife();
    }

    public boolean isDead() {
        return isDead;
    }

    public boolean isDeadAnimDoit() {
        return deadAnimDoit;
    }

    // * Reset player
    public void resetPlayer(boolean isCompletly) {
        if (isCompletly)
            currentLives = 3;

        left = false;
        right = false;
        inAir = false;
        moving = false;
        jump = false;
        isDead = false;
        deadAnimDoit = false;
        playerAction = IDLE;
        aniIndex = 0;
        hitbox.x = x;
        hitbox.y = y;

        if (!Helpers.IsEntityOnFloor(hitbox, levelData))
            inAir = true;
    }

    public void resetVelocity() {
        this.walkSpeed = 1.0f * Constants.SCALE;
    }

    public Point getPosition() {
        return new Point((int) this.hitbox.x, (int) this.hitbox.y);
    }

    public void setPlayerAction(int playerAction) {
        this.playerAction = playerAction;
    }
}
