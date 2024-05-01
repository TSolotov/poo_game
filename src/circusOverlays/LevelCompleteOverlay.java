package circusOverlays;

import static utils.Constants.FrameConstants.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import circusUI.CRMButton;
import states.CircusPlaying;
import states.GameState;
import utils.LoadSprites;

import utils.Constants.OverlayConstants;
import utils.Constants.UIConstants;

public class LevelCompleteOverlay {
    private CircusPlaying circusPlaying;
    private FontMetrics metrics;

    private BufferedImage[] bg_overlay;
    private CRMButton menu, play;

    public LevelCompleteOverlay(CircusPlaying circusPlaying) {
        this.circusPlaying = circusPlaying;
        loadNecesariesImages();
    }

    private void loadNecesariesImages() {
        bg_overlay = LoadSprites.getSprites(OverlayConstants.getSpritesInfo(OverlayConstants.BG_LOSE_OVERLAY));

        play = new CRMButton(
                (FRAME_WIDTH - OverlayConstants.BG_LOSE_OVERLAY_WIDTH) / 2
                        + (OverlayConstants.BG_LOSE_OVERLAY_WIDTH / 3 - UIConstants.SQUARE_BUTTON_SIZE / 2),
                FRAME_HEIGHT - (FRAME_HEIGHT - OverlayConstants.BG_LOSE_OVERLAY_HEIGHT) - 50,
                UIConstants.SQUARE_BUTTON_SIZE,
                UIConstants.SQUARE_BUTTON_SIZE, UIConstants.CONTINUE);

        menu = new CRMButton((FRAME_WIDTH - OverlayConstants.BG_LOSE_OVERLAY_WIDTH) / 2
                + (OverlayConstants.BG_LOSE_OVERLAY_WIDTH * 2 / 3 - UIConstants.SQUARE_BUTTON_SIZE / 2),
                FRAME_HEIGHT - (FRAME_HEIGHT - OverlayConstants.BG_LOSE_OVERLAY_HEIGHT) - 50,
                UIConstants.SQUARE_BUTTON_SIZE,
                UIConstants.SQUARE_BUTTON_SIZE, UIConstants.MENU);

    }

    private boolean isMouseIn(CRMButton b, MouseEvent e) {
        return b.getButtonBox().contains(e.getX(), e.getY());
    }

    // ! Update & Draw
    public void update() {
        menu.update();
        play.update();
    }

    // TODO - Mejorar aspecto, sirve para comprobar funcionamieto
    public void draw(Graphics g) {

        g.setColor(new Color(0, 0, 0, 100));
        g.fillRect(0, 0, FRAME_WIDTH, FRAME_HEIGHT);

        g.drawImage(bg_overlay[0], (FRAME_WIDTH - OverlayConstants.BG_LOSE_OVERLAY_WIDTH) / 2,
                (FRAME_HEIGHT - OverlayConstants.BG_LOSE_OVERLAY_HEIGHT) / 2, OverlayConstants.BG_LOSE_OVERLAY_WIDTH,
                OverlayConstants.BG_LOSE_OVERLAY_HEIGHT, null);

        g.setFont(new Font("Arial", Font.BOLD, 56));
        g.setColor(new Color(14, 165, 233));

        metrics = g.getFontMetrics();
        g.drawString("Nivel Completado", FRAME_WIDTH / 2 - metrics.stringWidth("Nivel Completado") / 2,
                (FRAME_HEIGHT - OverlayConstants.BG_LOSE_OVERLAY_HEIGHT) / 2 + 100);

        g.setFont(new Font("Arial", Font.BOLD, 32));
        metrics = g.getFontMetrics();
        g.drawString("Score: 1080 puntos", FRAME_WIDTH / 2 - metrics.stringWidth("Score: 1080 puntos") / 2,
                (FRAME_HEIGHT - OverlayConstants.BG_LOSE_OVERLAY_HEIGHT) / 2 + 200);

        play.draw(g, "Next level");
        menu.draw(g, "Go to menu");

    }

    // * Events

    public void mousePressed(MouseEvent e) {
        if (isMouseIn(menu, e))
            menu.setMousePressed(true);
        else if (isMouseIn(play, e))
            play.setMousePressed(true);

    }

    public void mouseMoved(MouseEvent e) {
        menu.setMouseOver(false);
        play.setMouseOver(false);
        if (isMouseIn(menu, e))
            menu.setMouseOver(true);
        else if (isMouseIn(play, e))
            play.setMouseOver(true);
    }

    public void mouseReleased(MouseEvent e) {
        if (isMouseIn(menu, e)) {
            if (menu.isMousePressed()) {
                circusPlaying.resetLevel(false);
                GameState.state = GameState.MENU;
            }
        } else if (isMouseIn(play, e)) {
            if (play.isMousePressed()) {
                circusPlaying.loadNextLevel();
            }
        }
    }

}
