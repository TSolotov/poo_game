package states;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import Entities.Player1;
import levels.LevelHandler;
import main.Game;
import utils.Constants;
import utils.LoadSprites;

import static utils.Constants.FrameConstants.*;
import static utils.Constants.Player1Constants.*;
import static utils.Constants.CircusConstants.*;

public class CircusPlaying extends State implements StateMethods {

    private Player1 player1;
    private LevelHandler levelHandler;

    // * Los distintos bg en playing
    private BufferedImage[] environmentImages;

    // * Variables para niveles más largos los offset para el x
    // * Esto es, al 70% del frame, el juego se mueve, lo mosmo al 20%
    private int xLevelOffset;
    private int leftBorder = (int) (0.2 * FRAME_WIDTH);
    private int rightBorder = (int) (0.7 * FRAME_WIDTH);
    private int maxLevel1OffsetX;

    public CircusPlaying(Game game) {
        super(game);

        init();

    }

    private void init() {
        levelHandler = new LevelHandler(game);

        player1 = new Player1(100, 300, SPRITE_WIDTH, SPRITE_HEIGHT, this);
        player1.loadLevelData(levelHandler.getCurrentLevel().getLevelData()); // * Cargo los niveles

        this.maxLevel1OffsetX = levelHandler.getCurrentLevel().getLevelOffsetX();
        environmentImages = LoadSprites.getSprites(Constants.CircusConstants.getSpritesInfo(BG_CIRCUS));
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
        player1.update();

        checkCloseBorder();
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

    }

    @Override
    public void keyPressed(KeyEvent k) {
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
                GameState.state = GameState.MENU;
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

}
