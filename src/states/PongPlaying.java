package states;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import main.Game;

public class PongPlaying extends State implements StateMethods {

    public PongPlaying(Game game) {
        super(game);
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics g) {
    }

    @Override
    public void keyPressed(KeyEvent k) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'keyPressed'");
    }

    @Override
    public void keyReleased(KeyEvent k) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'keyReleased'");
    }
}
