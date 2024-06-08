package pong.overlays;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.io.FileWriter;
import java.io.IOException;

import pong.objects.PongPlayer;
import states.GameState;
import states.PongGame;

import static utils.Constants.SCALE;

import static utils.Constants.FrameConstants.FRAME_HEIGHT;
import static utils.Constants.FrameConstants.FRAME_WIDTH;

public class WinnerOverlay {
    private FontMetrics metrics;
    private PongPlayer p1, p2;
    private PongGame pongPlaying;

    private boolean isFocusP1 = true;

    public WinnerOverlay(PongPlayer p1, PongPlayer p2, PongGame pongPlaying) {
        this.pongPlaying = pongPlaying;
        this.p1 = p1;
        this.p2 = p2;
    }

    // ! Update & Draw
    public void update() {

    }

    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRoundRect((int) ((FRAME_WIDTH / 2) - (600 * SCALE) / 2),
                (int) (((FRAME_HEIGHT - 400 * SCALE) / 2)),
                (int) (600 * SCALE), (int) (400 * SCALE), (int) (10 * SCALE), (int) (10 * SCALE));

        g.setColor(Color.WHITE);
        g.drawRoundRect((int) ((FRAME_WIDTH / 2) - (600 * SCALE) / 2),
                (int) (((FRAME_HEIGHT - 400 * SCALE) / 2)),
                (int) (600 * SCALE), (int) (400 * SCALE), (int) (10 * SCALE), (int) (10 * SCALE));

        g.setFont(new Font("Roboto", Font.BOLD, (int) (46 * SCALE)));
        metrics = g.getFontMetrics();

        g.drawString("Tenemos un ganador",
                (int) ((FRAME_WIDTH / 2) - (metrics.stringWidth("Tenemos un ganador") / 2)),
                (int) ((FRAME_HEIGHT - 400 * SCALE) / 2 + metrics.getHeight()));

        g.setFont(new Font("Roboto", Font.BOLD, (int) (24 * SCALE)));
        metrics = g.getFontMetrics();

        if (isFocusP1)
            g.setColor(Color.GREEN);
        else
            g.setColor(Color.WHITE);

        g.drawString("🔼 Player 1: " + p1.getUsername(),
                (int) ((FRAME_WIDTH - metrics.stringWidth("🔼 Player 1: " + p1.getUsername())) / 2),
                (int) (FRAME_HEIGHT / 2 - 50 * SCALE));

        if (!isFocusP1)
            g.setColor(Color.GREEN);
        else
            g.setColor(Color.WHITE);
        g.drawString("🔽 Player 2: " + p2.getUsername(),
                (int) ((FRAME_WIDTH - metrics.stringWidth("🔽 Player 2: " + p2.getUsername())) / 2),
                (int) (FRAME_HEIGHT / 2));

        g.setColor(Color.WHITE);
        g.drawString(p1.getUsername() + " " + p1.getGoals() + " - " + p2.getGoals() + " " + p2.getUsername(),
                (int) ((FRAME_WIDTH - metrics
                        .stringWidth(p1.getUsername() + " " + p1.getGoals() + " - " + p2.getGoals() + " "
                                + p2.getUsername()))
                        / 2),
                (int) ((FRAME_HEIGHT / 2 + 100 * SCALE)));

        g.drawString("Pulsa ENTER para aceptar",
                (int) (((FRAME_WIDTH - metrics.stringWidth("Pulsa ENTER para aceptar")) / 2)),
                (int) (((FRAME_HEIGHT - 400 * SCALE) / 2 + (400 * SCALE) - metrics.getHeight())));
    }

    // * Evento
    public void keyReleased(KeyEvent k) {
        if (!pongPlaying.getWin())
            return;

        switch (k.getKeyCode()) {
            case KeyEvent.VK_UP:
            case KeyEvent.VK_DOWN:
                isFocusP1 = !isFocusP1;
                break;
            case KeyEvent.VK_ENTER:
                if (p1.getUsername().isEmpty() || p2.getUsername().isEmpty())
                    break;
                try (FileWriter writer = new FileWriter(pongPlaying.getFileCSVPong(), true)) {
                    pongPlaying.getCsvPong().writeLine(writer, new String[] { p1.getUsername(),
                            Integer.toString(p1.getGoals()), p2.getUsername(),
                            Integer.toString(p2.getGoals()) });
                } catch (IOException error) {
                    error.printStackTrace();
                }
                pongPlaying.resetPong();
                pongPlaying.setGamestate(GameState.MENU);
                break;
        }

        if (isFocusP1) {
            if (k.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                if (!p1.getUsername().isEmpty())
                    p1.setUsername(p1.getUsername().substring(0, p1.getUsername().length() - 1));
                return;
            }

            if (p1.getUsername().length() > 10)
                return;

            if ((k.getKeyCode() >= 'a' && k.getKeyCode() <= 'z')
                    || (k.getKeyCode() >= 'A' && k.getKeyCode() <= 'Z')) {
                p1.addLetterToUsername(k.getKeyChar());
            }
        } else {
            if (k.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
                if (!p2.getUsername().isEmpty())
                    p2.setUsername(p2.getUsername().substring(0, p2.getUsername().length() - 1));
                return;
            }

            if (p2.getUsername().length() >= 10)
                return;

            if ((k.getKeyCode() >= 'a' && k.getKeyCode() <= 'z')
                    || (k.getKeyCode() >= 'A' && k.getKeyCode() <= 'Z')) {
                p2.addLetterToUsername(k.getKeyChar());
            }
        }

    }
}
