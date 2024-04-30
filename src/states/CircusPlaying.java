package states;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import Entities.EnemyHandler;
import Entities.Player1;
import circusOverlays.GameoverOverlay;
import circusOverlays.LoseOverlay;
import circusOverlays.PauseOverlay;
import levels.LevelHandler;
import main.Game;
import utils.Constants;
import utils.Helpers;
import utils.LoadSprites;

import static utils.Constants.FrameConstants.*;
import static utils.Constants.Player1Constants.*;
import static utils.Constants.CircusConstants.*;

public class CircusPlaying extends State implements StateMethods {

    private Player1 player1;
    private LevelHandler levelHandler;
    private EnemyHandler enemyHandler;

    // * Overlays
    private PauseOverlay pauseOverlay;
    private GameoverOverlay gameoverOverlay;
    private LoseOverlay loseOverlay;

    // * Los distintos bg en playing
    private BufferedImage[] environmentImages;

    // * Variables para niveles más largos los offset para el x
    // * Esto es, al 70% del frame, el juego se mueve, lo mosmo al 20%
    private int xLevelOffset;
    private int leftBorder = (int) (0.2 * FRAME_WIDTH);
    private int rightBorder = (int) (0.7 * FRAME_WIDTH);
    private int maxLevel1OffsetX;

    // *
    private boolean pause = false, gameOver = false, loseLife = false;

    public CircusPlaying(Game game) {
        super(game);

        init();

        // *
        // LoadSprites.getSpritesBySlices();

        // *

    }

    private void init() {
        levelHandler = new LevelHandler(game);
        enemyHandler = new EnemyHandler(this);
        pauseOverlay = new PauseOverlay(this);
        gameoverOverlay = new GameoverOverlay(this);
        loseOverlay = new LoseOverlay(this);

        player1 = new Player1(100, 100, SPRITE_WIDTH, SPRITE_HEIGHT, this);
        player1.loadLevelData(levelHandler.getCurrentLevel().getLevelData()); // * Cargo los niveles
        player1.setSpawnPont(Helpers.getPlayerSpawn(levelHandler.getCurrentLevel()));

        this.maxLevel1OffsetX = levelHandler.getCurrentLevel().getLevelOffsetX();
        environmentImages = LoadSprites.getSprites(Constants.CircusConstants.getSpritesInfo(BG_CIRCUS));

        // * Añade los enemigos al mapa
        enemyHandler.addEnemies(levelHandler.getCurrentLevel());
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
        } else if (loseLife && player1.isDeadAnimDoit()) {
            loseOverlay.update();
        } else if (!gameOver) {
            player1.update();
            enemyHandler.update(levelHandler.getCurrentLevel().getLevelData(), player1);
            checkCloseBorder();
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

        if (pause) {
            pauseOverlay.draw(g);
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
                player1.setLeft(true);
                break;
            case KeyEvent.VK_D:
                player1.setRight(true);
                break;
            case KeyEvent.VK_SPACE:
                player1.setJump(true);
                break;
            case KeyEvent.VK_ESCAPE:
                toglePause();
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent k) {
        switch (k.getKeyCode()) {
            case KeyEvent.VK_A:
                player1.setLeft(false);
                break;
            case KeyEvent.VK_D:
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
        else if (loseLife && player1.isDeadAnimDoit())
            loseOverlay.mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (pause)
            pauseOverlay.mouseReleased(e);
        else if (loseLife && player1.isDeadAnimDoit())
            loseOverlay.mouseReleased(e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (pause)
            pauseOverlay.mouseMoved(e);
        else if (loseLife && player1.isDeadAnimDoit())
            loseOverlay.mouseMoved(e);
    }

    // * Setters & Getters
    public void toglePause() {
        pause = !pause;
    }

    public void togleLoseLife() {
        loseLife = !loseLife;
    }

    public void resetLevel(boolean isCompletly) {
        gameOver = false;
        pause = false;
        loseLife = false;
        enemyHandler.resetEnemies();
        player1.resetPlayer(isCompletly);
    }
}
