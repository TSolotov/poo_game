package states;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import Entities.Player1;
import main.Game;
import utils.Constants;
import utils.LoadSprites;

import static utils.Constants.FrameConstants.*;
import static utils.Constants.Player1Constants.*;

public class CircusPlaying extends State implements StateMethods {

    private Player1 player1;

    // * Los distintos bg en playing
    private BufferedImage[] environmentImages;

    public CircusPlaying(Game game) {
        super(game);

        init();

    }

    private void init() {
        // TODO - Verificar o hacer constantes estas dimensiones
        player1 = new Player1(100, 300, SPRITE_WIDTH, SPRITE_HEIGHT, this);

        environmentImages = LoadSprites.getSprites(Constants.CircusConstants.getSpritesInfo());

    }

    // ! Métodos heredados
    @Override
    public void update() {
        player1.update();
    }

    @Override
    public void draw(Graphics g) {
        // * BG
        g.drawImage(environmentImages[1], 0, 0, FRAME_WIDTH, FRAME_HEIGHT, null);

        player1.draw(g);

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
