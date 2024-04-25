package states;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

public interface StateMethods {

    public void update();

    public void draw(Graphics g);

    // Events methods
    public void keyPressed(KeyEvent k);

    public void keyReleased(KeyEvent k);
}
