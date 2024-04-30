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

public class LoseOverlay {
    private CircusPlaying circusPlaying;
    private FontMetrics metrics;

    private BufferedImage[] bg_overlay;
    private CRMButton reset, menu;

    int text;

    public LoseOverlay(CircusPlaying circusPlaying) {
        this.circusPlaying = circusPlaying;
        loadNecesariesImages();

    }

    private void loadNecesariesImages() {
        bg_overlay = LoadSprites.getSprites(OverlayConstants.getSpritesInfo(OverlayConstants.BG_LOSE_OVERLAY));

        reset = new CRMButton(
                (FRAME_WIDTH - OverlayConstants.BG_LOSE_OVERLAY_WIDTH) / 2
                        + (OverlayConstants.BG_LOSE_OVERLAY_WIDTH / 3 - UIConstants.SQUARE_BUTTON_SIZE / 2),
                FRAME_HEIGHT - (FRAME_HEIGHT - OverlayConstants.BG_LOSE_OVERLAY_HEIGHT) - 50,
                UIConstants.SQUARE_BUTTON_SIZE,
                UIConstants.SQUARE_BUTTON_SIZE, UIConstants.RESET);

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
        reset.update();
        menu.update();
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
        g.drawString("Perdedor", FRAME_WIDTH / 2 - metrics.stringWidth("Perdedor") / 2,
                (FRAME_HEIGHT - OverlayConstants.BG_LOSE_OVERLAY_HEIGHT) / 2 + 100);

        g.setFont(new Font("Arial", Font.BOLD, 32));
        metrics = g.getFontMetrics();
        g.drawString("Score: ", FRAME_WIDTH / 2 - metrics.stringWidth("Score: ") / 2,
                (FRAME_HEIGHT - OverlayConstants.BG_LOSE_OVERLAY_HEIGHT) / 2 + 200);

        reset.draw(g, "Reset level");
        menu.draw(g, "Go to menu");

    }

    // * Eventos de mouse
    public void mousePressed(MouseEvent e) {
        if (isMouseIn(menu, e))
            menu.setMousePressed(true);
        else if (isMouseIn(reset, e))
            reset.setMousePressed(true);
    }

    public void mouseMoved(MouseEvent e) {
        reset.setMouseOver(false);
        menu.setMouseOver(false);

        if (isMouseIn(menu, e))
            menu.setMouseOver(true);
        else if (isMouseIn(reset, e))
            reset.setMouseOver(true);
    }

    public void mouseReleased(MouseEvent e) {
        if (isMouseIn(menu, e)) {
            if (menu.isMousePressed()) {
                circusPlaying.resetLevel(true);
                GameState.state = GameState.MENU;
            }
        } else if (isMouseIn(reset, e)) {
            if (reset.isMousePressed()) {
                circusPlaying.resetLevel(false);
            }
        }

        menu.resetMouseBooleans();
        reset.resetMouseBooleans();
    }
}
