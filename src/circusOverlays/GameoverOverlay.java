package circusOverlays;

import static utils.Constants.FrameConstants.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import states.CircusPlaying;

public class GameoverOverlay {

    private CircusPlaying circusPlaying;

    public GameoverOverlay(CircusPlaying circusPlaying) {
        this.circusPlaying = circusPlaying;
    }

    // ! Update & Draw
    public void update() {

    }

    // TODO - Mejorar aspecto, sirve para comprobar funcionamieto
    public void draw(Graphics g) {
        g.setColor(new Color(0, 0, 0, 100));
        g.fillRect(0, 0, FRAME_WIDTH, FRAME_HEIGHT);

        g.setColor(new Color(252, 252, 252));
        g.fillRect(FRAME_WIDTH / 2 - 300, FRAME_HEIGHT / 2 - 300, 600, 600);

        g.setColor(new Color(33, 37, 41));
        g.setFont(new Font("verdana", Font.BOLD, 32));
        g.drawString("Game Over", FRAME_WIDTH / 2 - 50, FRAME_HEIGHT / 2 - 250);

        g.setColor(new Color(33, 37, 41));
        g.setFont(new Font("verdana", Font.PLAIN, 20));
        g.drawString("Reset level", FRAME_WIDTH / 2 - 250, FRAME_HEIGHT / 2 - 150);
        g.drawString("Reset game", FRAME_WIDTH / 2 - 250, FRAME_HEIGHT / 2 - 100);
        g.drawString("Go menu", FRAME_WIDTH / 2 - 250, FRAME_HEIGHT / 2 - 50);
        g.drawString("Exit", FRAME_WIDTH / 2 - 250, FRAME_HEIGHT / 2 - 0);

    }
}
