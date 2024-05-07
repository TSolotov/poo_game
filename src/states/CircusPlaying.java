package states;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import Entities.EnemyHandler;
import Entities.Player1;
import circusObjects.ObjectHandler;
import circusOverlays.GameoverOverlay;
import circusOverlays.LevelCompleteOverlay;
import circusOverlays.LoseOverlay;
import circusOverlays.PauseOverlay;
import circusOverlays.ScoreOverlay;
import levels.LevelHandler;
import main.Game;
import utils.Constants;
import utils.Helpers;
import utils.LevelsCreation;
import utils.LoadSprites;
import utils.Constants.Player1Constants;

import static utils.Constants.FrameConstants.*;
import static utils.Constants.Player1Constants.*;
import static utils.Constants.CircusConstants.*;

public class CircusPlaying extends State implements StateMethods {

    private Player1 player1;
    private LevelHandler levelHandler;
    private EnemyHandler enemyHandler;
    private ObjectHandler objectHandler;

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
    private int leftBorder = (int) (0.2 * FRAME_WIDTH);
    private int rightBorder = (int) (0.7 * FRAME_WIDTH);
    private int maxLevel1OffsetX;

    // *
    private boolean pause = false, gameOver = false, loseLife = false, levelCompleted = false;

    public CircusPlaying(Game game) {
        super(game);

        init();

        // *
        // LoadSprites.getSpritesBySlices();

        // *

    }

    private void init() {
        levelHandler = new LevelHandler(game);
        enemyHandler = new EnemyHandler();
        objectHandler = new ObjectHandler();

        pauseOverlay = new PauseOverlay(this);
        gameoverOverlay = new GameoverOverlay(this);
        loseOverlay = new LoseOverlay(this);
        levelCompleteOverlay = new LevelCompleteOverlay(this);
        scoreOverlay = new ScoreOverlay(this);

        player1 = new Player1(100, 100, SPRITE_WIDTH, SPRITE_HEIGHT, this);
        player1.loadLevelData(levelHandler.getCurrentLevel().getLevelData()); // * Cargo los niveles
        player1.setSpawnPoint(Helpers.getPlayerSpawn(levelHandler.getCurrentLevel()));

        this.maxLevel1OffsetX = levelHandler.getCurrentLevel().getLevelOffsetX();
        environmentImages = LoadSprites.getSprites(Constants.CircusConstants.getSpritesInfo(BG_CIRCUS));

        // * Añade los enemigos al mapa
        enemyHandler.addEnemies(levelHandler.getCurrentLevel());
        objectHandler.addObjects(levelHandler.getCurrentLevel());

    }

    public void loadNextLevel() {
        resetLevel(false);
        levelHandler.loadNextLevel();
        player1.setSpawnPoint(Helpers.getPlayerSpawn(levelHandler.getCurrentLevel()));
    }

    public void setMaxLevel1OffsetX(int maxLevel1OffsetX) {
        this.maxLevel1OffsetX = maxLevel1OffsetX;
    }

    // * Chequea si el muñeco está cerca del borde para generar más nivel
    private void checkCloseBorder() {
        int playerX = (int) player1.getHitbox().getX();
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

        } else if (loseLife && player1.isDeadAnimDoit() && !gameOver) {
            loseOverlay.update();
            player1.update();

        } else if (!gameOver) {
            scoreOverlay.update(player1);
            player1.update();
            enemyHandler.update(levelHandler.getCurrentLevel().getLevelData(), player1);
            objectHandler.update(player1);
            checkCloseBorder();

            // * Si el personaje está por sobre la bandera, ganó
            if (levelHandler.getCurrentLevel().getTileToDraw(
                    (int) ((player1.getHitbox().getY() + Player1Constants.REAL_HEIGHT) / TILES_SIZE),
                    (int) (player1.getHitbox().getX() + Player1Constants.REAL_WIDTH / 2)
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
        player1.draw(g, xLevelOffset);
        enemyHandler.draw(g, xLevelOffset);
        objectHandler.draw(g, xLevelOffset);
        scoreOverlay.draw(g);

        if (pause) {
            pauseOverlay.draw(g);
        } else if (levelCompleted) {
            levelCompleteOverlay.draw(g);
        } else if (gameOver) {
            gameoverOverlay.draw(g);
        } else if (loseLife && player1.isDeadAnimDoit()) {
            loseOverlay.draw(g);
        }
    }

    // * Eventos de mouse y teclado
    @Override
    public void keyPressed(KeyEvent k) {
        // * Si está muerto no dejo que el personaje se pueda mover
        if (player1.isDead()) {
            player1.setLeft(false);
            player1.setRight(false);
            return;
        }

        switch (k.getKeyCode()) {
            case KeyEvent.VK_A:
                if (LevelHandler.getNumberLevel() == 2) {
                    break;
                }
                player1.setLeft(true);
                break;
            case KeyEvent.VK_D:
                if (LevelHandler.getNumberLevel() == 2) {
                    break;
                }
                player1.setRight(true);
                break;
            case KeyEvent.VK_SPACE:
                player1.setJump(true);
                break;
            case KeyEvent.VK_ESCAPE:
                setPause(true);
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent k) {
        switch (k.getKeyCode()) {
            case KeyEvent.VK_A:
                if (LevelHandler.getNumberLevel() == 2) {
                    break;
                }
                player1.setLeft(false);
                break;
            case KeyEvent.VK_D:
                if (LevelHandler.getNumberLevel() == 2) {
                    break;
                }
                player1.setRight(false);
                break;
            case KeyEvent.VK_SPACE:
                player1.setJump(false);
                break;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (pause)
            pauseOverlay.mousePressed(e);
        else if (gameOver)
            gameoverOverlay.mousePressed(e);
        else if (loseLife && player1.isDeadAnimDoit())
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
        else if (loseLife && player1.isDeadAnimDoit())
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
        else if (loseLife && player1.isDeadAnimDoit())
            loseOverlay.mouseMoved(e);
        else if (levelCompleted)
            levelCompleteOverlay.mouseMoved(e);

    }

    // * Setters & Getters
    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public void setPause(boolean pause) {
        this.pause = pause;
    }

    public void togleLoseLife() {
        loseLife = !loseLife;
    }

    public void resetLevel(boolean isCompletly) {
        gameOver = false;
        pause = false;
        loseLife = false;
        levelCompleted = false;
        enemyHandler.resetEnemies();
        objectHandler.resetObjects();
        scoreOverlay.resetTimer();
        player1.resetPlayer(isCompletly);
    }

    public boolean getLoseLife() {
        return loseLife;
    }

    public EnemyHandler getEnemyHandler() {
        return enemyHandler;
    }

    public ObjectHandler getObjectHandler() {
        return objectHandler;
    }

    public LevelHandler getLevelHandler() {
        return levelHandler;
    }

    public ScoreOverlay getScoreOverlay() {
        return scoreOverlay;
    }

    public Player1 getPlayer1() {
        return player1;
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
}
