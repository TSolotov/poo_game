package states;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import Entities.Player1;
import main.Game;

public class CircusPlaying extends State implements StateMethods {

    private Player1 player1;

    public CircusPlaying(Game game) {
        super(game);
        init();
    }

    private void init() {
        // TODO - Verificar o hacer constantes estas dimensiones
        player1 = new Player1(100, 300, 64 * 2, 44 * 2, this);
    }

    // ! Métodos heredados
    @Override
    public void update() {
        player1.update();
    }

    @Override
    public void draw(Graphics g) {
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
                // TODO - Hacer la pausa
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
