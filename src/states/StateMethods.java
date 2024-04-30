package states;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public interface StateMethods {

    public void update();

    public void draw(Graphics g);

    // Events methods
    public void keyPressed(KeyEvent k);

    public void keyReleased(KeyEvent k);

    public void mousePressed(MouseEvent e);

    public void mouseReleased(MouseEvent e);

    public void mouseMoved(MouseEvent e);
}
