package circus.entities;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import audio.AudioPlayer;
import circus.levels.Level;
import states.CircusGame;
import utils.Constants;
import utils.LevelsCreation;
import utils.LoadSprites;

import static utils.Constants.CircusConstants.GRAVITY;
import static utils.Constants.CircusConstants.TILES_SIZE;
import static utils.Constants.Player1Constants.*;

public class Hero extends Entity {
    // * Contiene info respecto al estado de juego de circus
    private CircusGame circusPlaying;
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

    public Hero(float x, float y, int width, int heigth, CircusGame circusPlaying) {
        super(x, y, width, heigth);
        this.circusPlaying = circusPlaying;
        this.currentLives = 3;
        this.walkSpeed = 1.0f * Constants.SCALE;

        loadAnimationsSprites();

        if (Constants.ORIGINAL_SPRITES) {
            initHitbox(REAL_WIDTH, REAL_HEIGHT);
        } else {
            initHitbox(HUMAN_REAL_WIDTH, HUMAN_REAL_HEIGHT);
        }

        System.out.println(Constants.SCALE);
        System.out.println(GRAVITY);

    }

    public void setSpawnPoint(Level level) {
        Point spawn = getPlayerSpawn(level);
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
        if (!isEntityOnFloor(hitbox, levelData))
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
        if (isDead)
            return;
        airSpeed = jumpSpeed / 2;
        inAir = true;
        CircusGame.setScore(25);
        circusPlaying.getGame().getAudioPlayer().playSounds(AudioPlayer.JUMP);
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
            if (Constants.ORIGINAL_SPRITES)
                flipX = width - (int) (18 * Constants.SCALE);
            else
                flipX = width - (int) (25 * Constants.SCALE);

            flipW = -1;

        }

        if (right) {
            xSpeed += walkSpeed;
            flipX = 0;
            flipW = 1;
        }

        if (!inAir) {
            if (!isEntityOnFloor(hitbox, levelData)) {
                inAir = true;
            }
        }

        if (inAir) {
            if (canMoveHere(hitbox.x, hitbox.y + airSpeed, hitbox.width, hitbox.height, levelData)) {
                hitbox.y += airSpeed;
                airSpeed += Constants.CircusConstants.GRAVITY;
                updateXPos(xSpeed);
            } else {
                hitbox.y = getEntityYPosUnderRoofOrAboveFloor(hitbox, airSpeed);
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
        if (canMoveHere(hitbox.x + xSpeed, hitbox.y, hitbox.width, hitbox.height, levelData)) {
            hitbox.x += xSpeed;
        } else {
            hitbox.x = getEntityXPosNextToWall(hitbox, xSpeed);
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
            if (airSpeed < 0) {
                playerAction = JUMP;
            } else {
                playerAction = FALLING;
            }
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
        int xOffset, yOffset, sWidth, sHeight;
        if (Constants.ORIGINAL_SPRITES) {
            xOffset = X_DRAW_OFFSET;
            yOffset = Y_DRAW_OFFSET;
            sWidth = SPRITE_WIDTH;
            sHeight = SPRITE_HEIGHT;
        } else {
            xOffset = HUMAN_X_DRAW_OFFSET;
            yOffset = HUMAN_Y_DRAW_OFFSET;
            sWidth = HUMAN_SPRITE_WIDTH;
            sHeight = HUMAN_SPRITE_HEIGHT;
        }

        g.drawImage(animations.get(playerAction)[aniIndex], (int) (hitbox.x - xOffset) - xLevelOffset + flipX,
                (int) (hitbox.getY() - yOffset),
                sWidth * flipW,
                sHeight, null);
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
        circusPlaying.toggleLoseLife();
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
        aniIndex = 0;
        hitbox.x = x;
        hitbox.y = y;
        playerAction = IDLE;

        if (!isEntityOnFloor(hitbox, levelData))
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

    // * Me da la ubicación del spawn point
    private Point getPlayerSpawn(Level level) {
        for (int i = 0; i < level.getLevelHeight(); i++) {
            for (int j = 0; j < level.getLevelWidth(); j++) {
                if (level.getTileToDraw(i, j) == LevelsCreation.SPWN) {
                    return new Point(j * TILES_SIZE, i * TILES_SIZE);
                }
            }
        }
        return new Point(TILES_SIZE, TILES_SIZE);
    }
}
