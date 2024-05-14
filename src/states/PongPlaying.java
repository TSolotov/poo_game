package states;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import main.Game;
import pongObjects.Ball;
import pongObjects.PongPlayer;

import static utils.Constants.FrameConstants.FRAME_HEIGHT;
import static utils.Constants.FrameConstants.FRAME_WIDTH;

public class PongPlaying extends State implements StateMethods {

    private boolean p1up = false, p1down = false, p2up = false, p2down = false;

    public PongPlayer player1, player2;
    public Ball ball;

    public PongPlaying(Game game) {
        super(game);
        create_components();
    }

    public void create_components() {
        ball = new Ball();
        player1 = new PongPlayer(true);
        player2 = new PongPlayer(false);

    }

    // ! Update & Draw
    public void update() {
        ball.update(player1, player2);
        player1.update(ball);
        player2.update(ball);
    }

    public void draw(Graphics g) {
        drawBackground(g);
        ball.draw(g);
        player1.draw(g);
        player2.draw(g);
    }

    public void drawBackground(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, FRAME_WIDTH, FRAME_HEIGHT);

    }

    // * Events
    @Override
    public void keyPressed(KeyEvent k) {
        int key = k.getKeyCode();

        if (key == KeyEvent.VK_UP) {
            player2.change_direction(-1);
            p2up = true;
        }
        if (key == KeyEvent.VK_DOWN) {
            player2.change_direction(1);
            p2down = true;
        }
        if (key == KeyEvent.VK_W) {
            player1.change_direction(-1);
            p1up = true;
        }
        if (key == KeyEvent.VK_S) {
            player1.change_direction(1);
            p1down = true;
        }

        // exit
        if (key == KeyEvent.VK_ESCAPE) {
            this.setGamestate(GameState.MENU);
        }
    }

    @Override
    public void keyReleased(KeyEvent k) {
        int key = k.getKeyCode();

        if (key == KeyEvent.VK_UP) {
            p2up = false;
        }
        if (key == KeyEvent.VK_DOWN) {
            p2down = false;
        }
        if (key == KeyEvent.VK_W) {
            p1up = false;
        }
        if (key == KeyEvent.VK_S) {
            p1down = false;
        }

        if (!p1up && !p1down)
            player1.stop();
        if (!p2up && !p2down)
            player2.stop();
    }

    // * Estas funciones están al pedo, pero al heredar de una interface hay que
    // * ponerlas.
    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

}
