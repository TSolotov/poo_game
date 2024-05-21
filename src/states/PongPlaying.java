package states;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import main.Game;
import pongObjects.Ball;
import pongObjects.PongPlayer;
import pongOverlays.WinnerOverlay;

import static utils.Constants.SCALE;
import static utils.Constants.FrameConstants.FRAME_HEIGHT;
import static utils.Constants.FrameConstants.FRAME_WIDTH;

public class PongPlaying extends State implements StateMethods {
    private WinnerOverlay winnerOverlay;

    private PongPlayer player1, player2;
    private Ball ball;
    private FontMetrics metrics;

    private boolean p1up = false, p1down = false, p2up = false, p2down = false;
    private boolean pause = false, win = false;

    public PongPlaying(Game game) {
        super(game);
        create_components();

        winnerOverlay = new WinnerOverlay(player1, player2, this);
    }

    public void create_components() {
        ball = new Ball(this);
        player1 = new PongPlayer(true, this);
        player2 = new PongPlayer(false, this);

    }

    // ! Update & Draw
    public void update() {
        if (!pause) {
            ball.update(player1, player2);
            player1.update(ball);
            player2.update(ball);
        }
    }

    public void draw(Graphics g) {
        drawBackground(g);
        ball.draw(g);
        player1.draw(g);
        player2.draw(g);

        if (win) {
            winnerOverlay.draw(g);
        } else if (pause)
            drawPause(g);
    }

    public void drawPause(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRoundRect((int) (FRAME_WIDTH / 2 - 400 * SCALE / 2), (int) (FRAME_HEIGHT / 2 - 200 * SCALE / 2),
                (int) (400 * SCALE), (int) (200 * SCALE), (int) (10 * SCALE), (int) (10 * SCALE));
        g.setColor(Color.WHITE);
        g.drawRoundRect((int) (FRAME_WIDTH / 2 - 400 * SCALE / 2), (int) (FRAME_HEIGHT / 2 - 200 * SCALE / 2),
                (int) (400 * SCALE), (int) (200 * SCALE), (int) (10 * SCALE), (int) (10 * SCALE));

        g.setFont(new Font("Roboto", Font.BOLD, (int) (32 * SCALE)));
        metrics = g.getFontMetrics();

        g.drawString("Juego Pausado", (int) ((FRAME_WIDTH - metrics.stringWidth("Juego Pausado")) / 2),
                (int) (((FRAME_HEIGHT - 200 * SCALE) / 2 + metrics.getHeight())));

        g.setFont(new Font("Roboto", Font.BOLD, (int) (16 * SCALE)));
        metrics = g.getFontMetrics();
        g.drawString("Pulsa ENTER para quitar la pausa",
                (int) (((FRAME_WIDTH - metrics.stringWidth("Pulsa ENTER para quitar la pausa")) / 2)),
                (int) (FRAME_HEIGHT / 2));

        g.drawString("Pulsa ESCAPE para salir",
                (int) (((FRAME_WIDTH - metrics.stringWidth("Pulsa ESCAPE para salir")) / 2)),
                (int) (FRAME_HEIGHT / 2 + 50 * SCALE));
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
            if (pause) {
                resetPong();
                this.setGamestate(GameState.MENU);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent k) {
        winnerOverlay.keyReleased(k);

        switch (k.getKeyCode()) {
            case KeyEvent.VK_UP:
                p2up = false;
                break;
            case KeyEvent.VK_DOWN:
                p2down = false;
                break;
            case KeyEvent.VK_W:
                p1up = false;
                break;
            case KeyEvent.VK_S:
                p1down = false;
                break;
            case KeyEvent.VK_ENTER:
                if (!win)
                    pause = !pause;
                break;
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
        return;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        return;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        return;
    }

    public void setPause(boolean pause) {
        this.pause = pause;
    }

    public void setWin(boolean win) {
        this.win = win;
    }

    public boolean getWin() {
        return win;
    }

    public void resetPong() {
        player1.resetPlayer();
        player2.resetPlayer();
        ball.reset_ball();
        win = false;
        pause = false;
        p1up = false;
        p1down = false;
        p2up = false;
        p2down = false;
    }
}
