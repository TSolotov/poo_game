package pongOverlays;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import static utils.Constants.SCALE;

import static utils.Constants.FrameConstants.FRAME_HEIGHT;
import static utils.Constants.FrameConstants.FRAME_WIDTH;

public class WinnerOverlay {
    private FontMetrics metrics;

    public WinnerOverlay() {

    }

    // ! Update & Draw
    public void update() {
    }

    public void draw(Graphics g) {
        g.setColor(Color.PINK);
        g.fillRect((int) ((FRAME_WIDTH - 600) / 2 * SCALE), (int) ((FRAME_HEIGHT - 400) / 2 * SCALE),
                (int) (600 * SCALE), (int) (400 * SCALE));

        g.setColor(Color.BLACK);
        g.setFont(new Font("Roboto", Font.BOLD, (int) (52 * SCALE)));
        metrics = g.getFontMetrics();

        g.drawString("Ganaste", (int) ((FRAME_WIDTH - metrics.stringWidth("Ganaste")) / 2 * SCALE),
                (int) (((FRAME_HEIGHT - 400) / 2 + metrics.getHeight()) * SCALE));

        g.setFont(new Font("Roboto", Font.BOLD, (int) (32 * SCALE)));
        metrics = g.getFontMetrics();
        g.drawString("Pulsa ENTER para terminar",
                (int) (((FRAME_WIDTH - metrics.stringWidth("Pulsa ENTER para terminar")) / 2) * SCALE),
                (int) ((FRAME_HEIGHT / 2 + 50) * SCALE));
    }

}
