package states;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import ui.TogleButton;
import utils.LoadSprites;
import main.GameSystem;
import utils.Constants;
import utils.Constants.UIConstants;

import static utils.Constants.FrameConstants.*;
import static utils.Constants.*;

public class Configuration extends State implements StateMethods {
    private BufferedImage[] bg_images;
    private FontMetrics metrics;

    private final String[] optionsString = {"Pantalla Completa: ", "Activar Música: ", "Activar Sonidos: "};
    private TogleButton musicButton, soundButton, screenButton;

    public Configuration(GameSystem game) {
        super(game);
        loadBackground();


        loadButons();
    }

    private void loadBackground() {
        bg_images = LoadSprites.getSprites(Constants.MenuConstants.getMenuSpritesInfo());
    }

    private void loadButons() {
        boolean screenState = Boolean.parseBoolean(game.getEnvFile().getEnvProps().getProperty("FULL_SCREEN"));
        screenButton = new TogleButton((int) ((150 + 25 + 298) * SCALE),
                (int) ((214) * SCALE + (87) * 0 * SCALE),
                UIConstants.getSpritesInfo(UIConstants.WINDOW),
                UIConstants.getSpritesInfo(UIConstants.FULLSCREEN), screenState);

        musicButton = new TogleButton((int) ((150 + 25 + 248) * SCALE),
                (int) ((214) * SCALE + (87) * 1 * SCALE),
                UIConstants.getSpritesInfo(UIConstants.MUSICON),
                UIConstants.getSpritesInfo(UIConstants.MUSICOFF));

        soundButton = new TogleButton((int) ((150 + 25 + 263) * SCALE),
                (int) ((214) * SCALE + (87) * 2 * SCALE),
                UIConstants.getSpritesInfo(UIConstants.SOUNDON),
                UIConstants.getSpritesInfo(UIConstants.SOUNDOFF));

    }

    // * ---------------------------------------

    @Override
    public void update() {
        musicButton.update();
        soundButton.update();
        screenButton.update();
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(new Color(0, 0, 0, 200));
        g.drawImage(bg_images[0], 0, 0, FRAME_WIDTH, FRAME_HEIGHT, null);
        g.fillRect(0, 0, FRAME_WIDTH, FRAME_HEIGHT);

        g.setFont(new Font("Roboto", Font.BOLD, (int) (62 * SCALE)));
        g.setColor(Color.WHITE);
        metrics = g.getFontMetrics();
        g.drawString("Configuraciones", FRAME_WIDTH / 2 - metrics.stringWidth("Configuraciones") / 2,
                (int) (100 * SCALE));

        g.setFont(new Font("Roboto", Font.BOLD, (int) (32 * SCALE)));
        metrics = g.getFontMetrics();

        for (int i = 0; i < optionsString.length; i++) {
            g.drawString(optionsString[i], (int) (150 * SCALE),
                    (int) (250 * SCALE + (metrics.getHeight() + 50 * SCALE) * i));
        }

        musicButton.draw(g);
        soundButton.draw(g);
        screenButton.draw(g);
    }

    @Override
    public void keyPressed(KeyEvent k) {
        if (k.getKeyCode() == KeyEvent.VK_ESCAPE) {
            GameState.state = GameState.MENU;
        }
    }

    @Override
    public void keyReleased(KeyEvent k) {
        // ! Innecesario por ahora
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (isMouseIn(e, musicButton))
            musicButton.setMousePressed(true);
        else if (isMouseIn(e, soundButton))
            soundButton.setMousePressed(true);
        else if (isMouseIn(e, screenButton))
            screenButton.setMousePressed(true);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (isMouseIn(e, musicButton)) {
            if (musicButton.isMousePressed()) {
                musicButton.togleState();
                game.getAudioPlayer().togleMusicMute();
            }
        } else if (isMouseIn(e, soundButton)) {
            if (soundButton.isMousePressed()) {
                soundButton.togleState();
                game.getAudioPlayer().togleSoundsMute();
            }
        } else if (isMouseIn(e, screenButton)) {
            if (screenButton.isMousePressed()) {
                screenButton.togleState();
                try {
                    game.getEnvFile().setEnvVariable("FULL_SCREEN", String.valueOf(screenButton.getState()));
                    game.setEnvFileChanged(true);
                } catch (IOException err) {
                    err.printStackTrace();
                }
            }
        }
        musicButton.resetMouseBooleans();
        soundButton.resetMouseBooleans();
        screenButton.resetMouseBooleans();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        soundButton.setMouseOver(false);
        musicButton.setMouseOver(false);
        screenButton.setMouseOver(false);
        if (isMouseIn(e, soundButton))
            soundButton.setMouseOver(true);
        else if (isMouseIn(e, musicButton))
            musicButton.setMouseOver(true);

        else if (isMouseIn(e, screenButton))
            screenButton.setMouseOver(true);
    }

    // * Chequea que el mouse esté sobre el botón
    public boolean isMouseIn(MouseEvent e, TogleButton tb) {
        return tb.getButtonBox().contains(e.getX(), e.getY());
    }

}
