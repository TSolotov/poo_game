package circusOverlays;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import circusUI.CRMButton;
import states.CircusPlaying;
import states.GameState;
import utils.Constants.OverlayConstants;
import utils.Constants.UIConstants;
import utils.Constants;
import utils.LoadSprites;

import static utils.Constants.FrameConstants.*;

public class PauseOverlay {
    private CircusPlaying circusPlaying;
    private FontMetrics metrics;

    private BufferedImage[] bg_overlay;
    private CRMButton reset, menu, play;

    public PauseOverlay(CircusPlaying circusPlaying) {
        this.circusPlaying = circusPlaying;
        loadNecesariesImages();
    }

    private void loadNecesariesImages() {
        bg_overlay = LoadSprites.getSprites(OverlayConstants.getSpritesInfo(OverlayConstants.BG_LOSE_OVERLAY));

        play = new CRMButton((FRAME_WIDTH - OverlayConstants.BG_LOSE_OVERLAY_WIDTH) / 2
                + (OverlayConstants.BG_LOSE_OVERLAY_WIDTH / 5 - UIConstants.SQUARE_BUTTON_SIZE / 2),
                FRAME_HEIGHT - (FRAME_HEIGHT - OverlayConstants.BG_LOSE_OVERLAY_HEIGHT)
                        - (int) (50 * Constants.SCALE),
                UIConstants.SQUARE_BUTTON_SIZE,
                UIConstants.SQUARE_BUTTON_SIZE, UIConstants.CONTINUE);

        reset = new CRMButton(
                (FRAME_WIDTH - OverlayConstants.BG_LOSE_OVERLAY_WIDTH) / 2
                        + (OverlayConstants.BG_LOSE_OVERLAY_WIDTH / 2 - UIConstants.SQUARE_BUTTON_SIZE / 2),
                FRAME_HEIGHT - (FRAME_HEIGHT - OverlayConstants.BG_LOSE_OVERLAY_HEIGHT)
                        - (int) (50 * Constants.SCALE),
                UIConstants.SQUARE_BUTTON_SIZE,
                UIConstants.SQUARE_BUTTON_SIZE, UIConstants.RESET);

        menu = new CRMButton((FRAME_WIDTH - OverlayConstants.BG_LOSE_OVERLAY_WIDTH) / 2
                + (OverlayConstants.BG_LOSE_OVERLAY_WIDTH * 4 / 5 - UIConstants.SQUARE_BUTTON_SIZE / 2),
                FRAME_HEIGHT - (FRAME_HEIGHT - OverlayConstants.BG_LOSE_OVERLAY_HEIGHT)
                        - (int) (50 * Constants.SCALE),
                UIConstants.SQUARE_BUTTON_SIZE,
                UIConstants.SQUARE_BUTTON_SIZE, UIConstants.MENU);

    }

    private boolean isMouseIn(CRMButton b, MouseEvent e) {
        return b.getButtonBox().contains(e.getX(), e.getY());
    }

    // ! Update & Draw
    public void update() {
        reset.update();
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

        g.setFont(new Font("Arial", Font.BOLD, (int) (56 * Constants.SCALE)));
        g.setColor(new Color(14, 165, 233));

        metrics = g.getFontMetrics();
        g.drawString("Pausa", FRAME_WIDTH / 2 - metrics.stringWidth("Pausa") / 2,
                (FRAME_HEIGHT - OverlayConstants.BG_LOSE_OVERLAY_HEIGHT) / 2 + (int) (100 * Constants.SCALE));

        g.setFont(new Font("Arial", Font.BOLD, (int) (32 * Constants.SCALE)));
        metrics = g.getFontMetrics();
        g.drawString("Score: 1080 puntos", FRAME_WIDTH / 2 - metrics.stringWidth("Score: 1080 puntos") / 2,
                (FRAME_HEIGHT - OverlayConstants.BG_LOSE_OVERLAY_HEIGHT) / 2 + (int) (200 * Constants.SCALE));

        play.draw(g, "Play game");
        reset.draw(g, "Reset level");
        menu.draw(g, "Go to menu");

    }

    // * Events

    public void mousePressed(MouseEvent e) {
        if (isMouseIn(menu, e))
            menu.setMousePressed(true);
        else if (isMouseIn(reset, e))
            reset.setMousePressed(true);
        else if (isMouseIn(play, e))
            play.setMousePressed(true);

    }

    public void mouseMoved(MouseEvent e) {
        menu.setMouseOver(false);
        reset.setMouseOver(false);
        play.setMouseOver(false);
        if (isMouseIn(menu, e))
            menu.setMouseOver(true);
        else if (isMouseIn(reset, e))
            reset.setMouseOver(true);
        else if (isMouseIn(play, e))
            play.setMouseOver(true);
    }

    public void mouseReleased(MouseEvent e) {
        if (isMouseIn(menu, e)) {
            if (menu.isMousePressed()) {
                circusPlaying.resetLevel(true);
                circusPlaying.setGamestate(GameState.MENU);
            }
        } else if (isMouseIn(reset, e)) {
            if (reset.isMousePressed()) {
                circusPlaying.resetLevel(false);
            }
        } else if (isMouseIn(play, e)) {
            if (play.isMousePressed()) {
                circusPlaying.setPause(false);
            }
        }
    }

}
