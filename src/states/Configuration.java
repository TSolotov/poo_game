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

import static utils.Constants.FrameConstants.*;
import static utils.Constants.*;

public class Configuration extends State implements StateMethods {
    private BufferedImage[] bg_images;
    private FontMetrics metrics;

    private TogleButton musicButton, soundButton, screenButton, spritesButton;

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
        boolean spitesState = Boolean.parseBoolean(game.getEnvFile().getEnvProps().getProperty("ORIGINAL_SPRITES"));

        screenButton = new TogleButton((int) (100 * SCALE), (int) (200 * SCALE), screenState);
        musicButton = new TogleButton((int) (100 * SCALE), (int) (275 * SCALE));
        soundButton = new TogleButton((int) (100 * SCALE), (int) (350 * SCALE));
        spritesButton = new TogleButton((int) (100 * SCALE), (int) (425 * SCALE), spitesState);

    }

    // * ---------------------------------------

    @Override
    public void update() {
        musicButton.update();
        soundButton.update();
        screenButton.update();
        spritesButton.update();
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(bg_images[0], 0, 0, FRAME_WIDTH, FRAME_HEIGHT, null);
        g.setColor(new Color(0, 0, 0, 200));
        g.fillRect(0, 0, FRAME_WIDTH, FRAME_HEIGHT);

        g.setFont(new Font("Roboto", Font.BOLD, (int) (62 * SCALE)));
        g.setColor(Color.WHITE);
        metrics = g.getFontMetrics();
        g.drawString("Configuraciones", FRAME_WIDTH / 2 - metrics.stringWidth("Configuraciones") / 2,
                (int) (100 * SCALE));

        screenButton.draw(g, "Pantalla completa: ");
        musicButton.draw(g, "Música: ");
        soundButton.draw(g, "Efectos: ");
        spritesButton.draw(g, "Sprites originales: ");

        g.setColor(Color.pink);
        g.setFont(new Font("Roboto", Font.BOLD, (int) (24 * SCALE)));
        metrics = g.getFontMetrics();
        g.drawString("Presiona ENTER para confirmar los cambios",
                (FRAME_WIDTH - metrics.stringWidth("Presiona ENTER para confirmar los cambios")) / 2,
                FRAME_HEIGHT - 50);

        g.setFont(new Font("Roboto", Font.ITALIC, (int) (12 * SCALE)));
        metrics = g.getFontMetrics();
        g.drawString("El juego se cerrará al terminar alceptar las configuraciones",
                (FRAME_WIDTH - metrics.stringWidth("El juego se cerrará al terminar alceptar las configuraciones")) / 2,
                FRAME_HEIGHT - 20);
    }

    @Override
    public void keyPressed(KeyEvent k) {
        if (k.getKeyCode() == KeyEvent.VK_ESCAPE) {
            GameState.state = GameState.MENU;
        } else if (k.getKeyCode() == KeyEvent.VK_ENTER) {
            apply();
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
        else if (isMouseIn(e, spritesButton))
            spritesButton.setMousePressed(true);
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
            }
        } else if (isMouseIn(e, spritesButton)) {
            if (spritesButton.isMousePressed()) {
                spritesButton.togleState();
            }
        }
        musicButton.resetMouseBooleans();
        soundButton.resetMouseBooleans();
        screenButton.resetMouseBooleans();
        spritesButton.resetMouseBooleans();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        soundButton.setMouseOver(false);
        musicButton.setMouseOver(false);
        screenButton.setMouseOver(false);
        spritesButton.setMouseOver(false);
        if (isMouseIn(e, soundButton))
            soundButton.setMouseOver(true);
        else if (isMouseIn(e, musicButton))
            musicButton.setMouseOver(true);
        else if (isMouseIn(e, screenButton))
            screenButton.setMouseOver(true);
        else if (isMouseIn(e, spritesButton))
            spritesButton.setMouseOver(true);
    }

    // * Chequea que el mouse esté sobre el botón
    public boolean isMouseIn(MouseEvent e, TogleButton tb) {
        return tb.getButtonBox().contains(e.getX(), e.getY());
    }

    // * Aplicar cambios
    private void apply() {
        try {
            game.getEnvFile().setEnvVariable("FULL_SCREEN", String.valueOf(screenButton.getState()));
            game.getEnvFile().setEnvVariable("ORIGINAL_SPRITES", String.valueOf(spritesButton.getState()));
            game.setEnvFileChanged(true);
        } catch (IOException err) {
            err.printStackTrace();
        }
    }
}
