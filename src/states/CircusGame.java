package states;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import audio.AudioPlayer;
import circus.entities.Hero;
import circus.levels.LevelHandler;
import circus.overlays.GameoverOverlay;
import circus.overlays.LevelCompleteOverlay;
import circus.overlays.LoseOverlay;
import circus.overlays.PauseOverlay;
import circus.overlays.ScoreOverlay;
import main.GameSystem;
import utils.Constants;
import utils.LevelsCreation;
import utils.LoadSprites;

import static utils.Constants.FrameConstants.*;
import static utils.Constants.CircusConstants.*;
import static utils.Constants.Player1Constants.*;

public class CircusGame extends State implements StateMethods {

    private Hero player;
    private String username = "";
    private LevelHandler levelHandler;

    // * Overlays
    private PauseOverlay pauseOverlay;
    private GameoverOverlay gameoverOverlay;
    private LoseOverlay loseOverlay;
    private LevelCompleteOverlay levelCompleteOverlay;
    private ScoreOverlay scoreOverlay;

    // * Los distintos bg en playing
    private BufferedImage[] environmentImages;

    // * Variables para niveles más largos los offset para el x
    // * Esto es, al 70% del frame, el juego se mueve, lo mosmo al 20%
    private int xLevelOffset;
    private final int leftBorder = (int) (0.2 * FRAME_WIDTH);
    private final int rightBorder = (int) (0.7 * FRAME_WIDTH);
    private int maxLevel1OffsetX;

    // *
    private boolean pause = false, gameOver = false, loseLife = false, levelCompleted = false;

    // * Variable que maneja el score
    private static int score = 5000;

    public CircusGame(GameSystem game) {
        super(game);
        init();
    }

    private void init() {
        levelHandler = new LevelHandler(game);

        if (Constants.ORIGINAL_SPRITES)
            player = new Hero(100, 100, SPRITE_WIDTH, SPRITE_HEIGHT, this);
        else
            player = new Hero(100, 100, SPRITE_WIDTH, SPRITE_HEIGHT, this);

        player.loadLevelData(levelHandler.getCurrentLevel().getLevelData()); // * Cargo los niveles
        player.setSpawnPoint(levelHandler.getCurrentLevel());

        this.maxLevel1OffsetX = levelHandler.getCurrentLevel().getLevelOffsetX();
        environmentImages = LoadSprites.getSprites(Constants.CircusConstants.getSpritesInfo(BG_CIRCUS));

        pauseOverlay = new PauseOverlay(this);
        gameoverOverlay = new GameoverOverlay(this);
        loseOverlay = new LoseOverlay(this);
        levelCompleteOverlay = new LevelCompleteOverlay(this);
        scoreOverlay = new ScoreOverlay(this);

    }

    public void loadNextLevel() {
        resetLevel(false);
        levelHandler.loadNextLevel();
        player.setSpawnPoint(levelHandler.getCurrentLevel());
    }

    public void setMaxLevel1OffsetX(int maxLevel1OffsetX) {
        this.maxLevel1OffsetX = maxLevel1OffsetX;
    }

    // * Chequea si el muñeco está cerca del borde para generar más nivel
    private void checkCloseBorder() {
        int playerX = (int) player.getHitbox().getX();
        int diff = playerX - xLevelOffset;

        if (diff > rightBorder)
            xLevelOffset += diff - rightBorder;
        if (diff < leftBorder)
            xLevelOffset += diff - leftBorder;

        if (xLevelOffset > maxLevel1OffsetX)
            xLevelOffset = maxLevel1OffsetX;
        else if (xLevelOffset < 0)
            xLevelOffset = 0;
    }

    // ! Métodos heredados
    @Override
    public void update() {
        if (pause) {
            pauseOverlay.update();

        } else if (levelCompleted) {
            levelCompleteOverlay.update();

        } else if (loseLife && player.isDeadAnimDoit() && !gameOver) {
            loseOverlay.update();
            player.update();

        } else if (!gameOver) {
            scoreOverlay.update(player);
            player.update();
            levelHandler.update(player);
            checkCloseBorder();

            // * Si el personaje está por sobre la bandera, ganó
            if (levelHandler.getCurrentLevel().getTileToDraw(
                    (int) ((player.getHitbox().getY() + REAL_HEIGHT) / TILES_SIZE),
                    (int) (player.getHitbox().getX() + REAL_WIDTH / 2)
                            / TILES_SIZE) == LevelsCreation.WINN) {
                levelCompleted = true;
            }

        } else {
            gameoverOverlay.update();
        }

    }

    @Override
    public void draw(Graphics g) {
        // * BG - El 0.2 controla la velocidad de movimiento del background
        for (int i = 0; i < 2; i++) {
            g.drawImage(environmentImages[1], i * FRAME_WIDTH - (int) (xLevelOffset * 0.1), 0, FRAME_WIDTH,
                    FRAME_HEIGHT, null);
        }
        levelHandler.draw(g, xLevelOffset);
        player.draw(g, xLevelOffset);
        scoreOverlay.draw(g);

        if (pause) {
            pauseOverlay.draw(g);

        } else if (levelCompleted) {
            levelCompleteOverlay.draw(g);
        } else if (gameOver) {
            gameoverOverlay.draw(g);
        } else if (loseLife && player.isDeadAnimDoit()) {
            loseOverlay.draw(g);
        }
    }

    // * Eventos de mouse y teclado
    @Override
    public void keyPressed(KeyEvent k) {
        // * Si está muerto no dejo que el personaje se pueda mover
        if (player.isDead()) {
            player.setLeft(false);
            player.setRight(false);
            return;
        }

        if (k.getKeyCode() == Integer.valueOf(Constants.LEFT_KEY_CODE)) {
            if (LevelHandler.getNumberLevel() == 2)
                return;
            player.setLeft(true);
        } else if (k.getKeyCode() == Integer.valueOf(Constants.RIGTH_KEY_CODE)) {
            if (LevelHandler.getNumberLevel() == 2)
                return;
            player.setRight(true);
        } else if (k.getKeyCode() == Integer.valueOf(Constants.JUMP_KEY_CODE)) {
            player.setJump(true);
        } else if (k.getKeyCode() == KeyEvent.VK_ESCAPE) {
            setPause(true);
        }
    }

    @Override
    public void keyReleased(KeyEvent k) {
        if (k.getKeyCode() == Integer.valueOf(Constants.LEFT_KEY_CODE)) {
            if (LevelHandler.getNumberLevel() == 2)
                return;
            player.setLeft(false);
        } else if (k.getKeyCode() == Integer.valueOf(Constants.RIGTH_KEY_CODE)) {
            if (LevelHandler.getNumberLevel() == 2)
                return;
            player.setRight(false);
        } else if (k.getKeyCode() == Integer.valueOf(Constants.JUMP_KEY_CODE)) {
            player.setJump(false);
        }

        if (gameOver)
            gameoverOverlay.keyReleased(k);

        if (levelCompleted && (LevelHandler.getNumberLevel() + 1) == levelHandler.getCantLevels())
            levelCompleteOverlay.keyReleased(k);

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (pause)
            pauseOverlay.mousePressed(e);
        else if (gameOver)
            gameoverOverlay.mousePressed(e);
        else if (loseLife && player.isDeadAnimDoit())
            loseOverlay.mousePressed(e);
        else if (levelCompleted)
            levelCompleteOverlay.mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (pause)
            pauseOverlay.mouseReleased(e);
        else if (gameOver)
            gameoverOverlay.mouseReleased(e);
        else if (loseLife && player.isDeadAnimDoit())
            loseOverlay.mouseReleased(e);
        else if (levelCompleted)
            levelCompleteOverlay.mouseReleased(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (pause)
            pauseOverlay.mouseMoved(e);
        else if (gameOver)
            gameoverOverlay.mouseMoved(e);
        else if (loseLife && player.isDeadAnimDoit())
            loseOverlay.mouseMoved(e);
        else if (levelCompleted)
            levelCompleteOverlay.mouseMoved(e);

    }

    // * Setters & Getters
    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
        this.getGame().getAudioPlayer().stopMusic();
        this.getGame().getAudioPlayer().playSounds(AudioPlayer.GAME_OVER);
    }

    public void setPause(boolean pause) {
        if (pause)
            this.getGame().getAudioPlayer().stopMusic();
        else
            this.getGame().getAudioPlayer().setMusic(LevelHandler.getNumberLevel());
        this.getGame().getAudioPlayer().playSounds(AudioPlayer.PAUSE);
        this.pause = pause;
    }

    public void toggleLoseLife() {
        this.getGame().getAudioPlayer().playSounds(AudioPlayer.DIE);
        loseLife = !loseLife;
    }

    public void resetLevel(boolean isCompletly) {
        if (isCompletly) {
            levelHandler.resetCurrentLevel();
            score = 5000;
            username = "";
        }
        gameOver = false;
        pause = false;
        loseLife = false;
        levelCompleted = false;
        player.resetPlayer(isCompletly);
        ScoreOverlay.resetTimer();
        levelHandler.getEnemyHandler().addEnemies(levelHandler.getCurrentLevel());
        levelHandler.getObjectHandler().addObjects(levelHandler.getCurrentLevel());
    }

    public LevelHandler getLevelHandler() {
        return levelHandler;
    }

    public Hero getPlayer() {
        return player;
    }

    public boolean getPause() {
        return pause;
    }

    public boolean getGameOver() {
        return gameOver;
    }

    public boolean getLevelComplete() {
        return levelCompleted;
    }

    public static void setScore(int score) {
        CircusGame.score += score;
    }

    public static int getScore() {
        return score;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void addLettersToUsername(char charKey) {
        this.username += charKey;
    }

    public String getUsername() {
        return username;
    }
}
