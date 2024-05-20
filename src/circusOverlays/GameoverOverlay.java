package circusOverlays;

import static utils.Constants.FrameConstants.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.FileWriter;
import java.io.IOException;

import circusUI.CRMButton;
import states.CircusPlaying;
import states.GameState;
import utils.Constants.OverlayConstants;
import utils.Constants.UIConstants;
import utils.CSVFile;
import utils.Constants;
import utils.LoadSprites;

public class GameoverOverlay {
    private CircusPlaying circusPlaying;

    private FontMetrics metrics;
    private BufferedImage[] bg_overlay;
    private CRMButton reset, menu;

    public GameoverOverlay(CircusPlaying circusPlaying) {
        this.circusPlaying = circusPlaying;
        loadNecesariesImages();
    }

    private boolean isMouseIn(CRMButton b, MouseEvent e) {
        return b.getButtonBox().contains(e.getX(), e.getY());
    }

    private void loadNecesariesImages() {
        bg_overlay = LoadSprites.getSprites(OverlayConstants.getSpritesInfo(OverlayConstants.BG_LOSE_OVERLAY));

        reset = new CRMButton(
                (FRAME_WIDTH - OverlayConstants.BG_LOSE_OVERLAY_WIDTH) / 2
                        + (OverlayConstants.BG_LOSE_OVERLAY_WIDTH / 3 - UIConstants.SQUARE_BUTTON_SIZE / 2),
                FRAME_HEIGHT - (FRAME_HEIGHT - OverlayConstants.BG_LOSE_OVERLAY_HEIGHT)
                        - (int) (50 * Constants.SCALE),
                UIConstants.SQUARE_BUTTON_SIZE,
                UIConstants.SQUARE_BUTTON_SIZE, UIConstants.RESET);

        menu = new CRMButton((FRAME_WIDTH - OverlayConstants.BG_LOSE_OVERLAY_WIDTH) / 2
                + (OverlayConstants.BG_LOSE_OVERLAY_WIDTH * 2 / 3 - UIConstants.SQUARE_BUTTON_SIZE / 2),
                FRAME_HEIGHT - (FRAME_HEIGHT - OverlayConstants.BG_LOSE_OVERLAY_HEIGHT)
                        - (int) (50 * Constants.SCALE),
                UIConstants.SQUARE_BUTTON_SIZE,
                UIConstants.SQUARE_BUTTON_SIZE, UIConstants.MENU);

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

        g.setFont(new Font("Arial", Font.BOLD, (int) (56 * Constants.SCALE)));
        g.setColor(new Color(14, 165, 233));

        metrics = g.getFontMetrics();
        g.drawString("Game Over", FRAME_WIDTH / 2 - metrics.stringWidth("Game Over") / 2,
                (FRAME_HEIGHT - OverlayConstants.BG_LOSE_OVERLAY_HEIGHT) / 2 + (int) (100 * Constants.SCALE));

        g.setFont(new Font("Arial", Font.BOLD, (int) (32 * Constants.SCALE)));
        metrics = g.getFontMetrics();
        g.drawString("Score: " + CircusPlaying.getScore(),
                FRAME_WIDTH / 2 - metrics.stringWidth("Score: " + CircusPlaying.getScore()) / 2,
                (FRAME_HEIGHT - OverlayConstants.BG_LOSE_OVERLAY_HEIGHT) / 2 + (int) (200 * Constants.SCALE));

        g.drawString("Username: " + circusPlaying.getUsername(),
                FRAME_WIDTH / 2 - metrics.stringWidth("Username: " + circusPlaying.getUsername()) / 2,
                (FRAME_HEIGHT - OverlayConstants.BG_LOSE_OVERLAY_HEIGHT) / 2 + (int) (300 * Constants.SCALE));

        reset.draw(g, "Volver a intentarlo");
        menu.draw(g, "Ir al menu");

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
                circusPlaying.setGamestate(GameState.MENU);
            }
        } else if (isMouseIn(reset, e)) {
            if (reset.isMousePressed()) {
                if (circusPlaying.getUsername().length() == 0)
                    return;
                try (FileWriter writer = new FileWriter(CSVFile.getFileNameCircus(), true)) {
                    CSVFile.writeLine(writer,
                            new String[] { circusPlaying.getUsername(),
                                    Integer.toString(CircusPlaying.getScore()),
                                    Integer.toString(ScoreOverlay.getTotalSeconds()) });
                } catch (IOException error) {
                    error.printStackTrace();
                }
                circusPlaying.setGamestate(GameState.MENU);
                circusPlaying.resetLevel(true);
                ScoreOverlay.resetTimer();
            }
        }

        menu.resetMouseBooleans();
        reset.resetMouseBooleans();
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
            if (circusPlaying.getUsername().length() > 0)
                circusPlaying.setUsername(
                        circusPlaying.getUsername().substring(0, circusPlaying.getUsername().length() - 1));
            return;
        }

        if (circusPlaying.getUsername().length() > 10)
            return;
        if ((e.getKeyCode() >= 'a' && e.getKeyCode() <= 'z') || (e.getKeyCode() >= 'A' && e.getKeyCode() <= 'Z')) {
            circusPlaying.addLettersToUsername(e.getKeyChar());
        }
    }
}
