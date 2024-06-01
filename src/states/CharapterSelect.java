package states;

import main.GameSystem;
import ui.CharapterButton;
import utils.Constants;
import utils.LoadSprites;
import utils.Constants.Player1Constants;

import static utils.Constants.FrameConstants.FRAME_HEIGHT;
import static utils.Constants.FrameConstants.FRAME_WIDTH;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import circus.levels.LevelHandler;

public class CharapterSelect extends State implements StateMethods {
    GameSystem gameSystem;

    private BufferedImage[] bg_images;
    private FontMetrics metrics;

    private CharapterButton soldierButton, humanButton;
    private boolean isAnySelected = false;

    public CharapterSelect(GameSystem gameSystem) {
        super(gameSystem);
        this.gameSystem = gameSystem;

        loadBackground();
        loadButtons();
    }

    private void loadButtons() {
        soldierButton = new CharapterButton((FRAME_HEIGHT - (Player1Constants.SPRITE_HEIGHT * 2)) / 2, 200,
                (Player1Constants.SPRITE_WIDTH * 2) * 150 / (Player1Constants.SPRITE_HEIGHT * 2),
                (Player1Constants.SPRITE_HEIGHT * 2) * 150 / (Player1Constants.SPRITE_HEIGHT * 2));

        humanButton = new CharapterButton((FRAME_HEIGHT + (Player1Constants.HUMAN_SPRITE_WIDTH * 2)) / 2, 200,
                (Player1Constants.HUMAN_SPRITE_WIDTH * 2) * 150 / (Player1Constants.HUMAN_SPRITE_HEIGHT * 2),
                (Player1Constants.HUMAN_SPRITE_HEIGHT * 2) * 150 / (Player1Constants.HUMAN_SPRITE_HEIGHT * 2));
    }

    private void loadBackground() {
        bg_images = LoadSprites.getSprites(Constants.MenuConstants.getMenuSpritesInfo());
    }

    // * ---------------------------------------

    @Override
    public void update() {
        soldierButton.update();
        humanButton.update();
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(bg_images[0], 0, 0, FRAME_WIDTH, FRAME_HEIGHT, null);
        g.setColor(new Color(0, 0, 0, 150));
        g.fillRect(0, 0, FRAME_WIDTH, FRAME_HEIGHT);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Roboto", Font.BOLD, 46));

        metrics = g.getFontMetrics();
        g.drawString("Selecciona tu personaje", (FRAME_WIDTH - metrics.stringWidth("Selecciona tu personaje")) / 2,
                100);

        g.setFont(new Font("Roboto", Font.BOLD, 24));
        g.setColor(Color.GREEN);
        metrics = g.getFontMetrics();
        if (isAnySelected)
            g.drawString("Presiona ENTER para empezar",
                    (FRAME_WIDTH - metrics.stringWidth("Presiona ENTER para empezar")) / 2,
                    FRAME_HEIGHT - 25);

        // * Buttons
        soldierButton.draw(g, "Soldier");
        humanButton.draw(g, "Human");
    }

    @Override
    public void keyPressed(KeyEvent k) {
        return;
    }

    @Override
    public void keyReleased(KeyEvent k) {
        if (k.getKeyCode() == KeyEvent.VK_ESCAPE)
            this.setGamestate(GameState.MENU);
        else if (k.getKeyCode() == KeyEvent.VK_ENTER)
            if (isAnySelected) {
                this.setGamestate(GameState.CIRCUS_GAME);
                gameSystem.getAudioPlayer().setMusic(LevelHandler.getNumberLevel());
            }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (isMouseIn(soldierButton, e))
            soldierButton.setMousePressed(true);
        else if (isMouseIn(humanButton, e))
            humanButton.setMousePressed(true);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (isMouseIn(soldierButton, e)) {
            if (soldierButton.isMousePressed()) {
                resetButtonsBooleans();
                soldierButton.setSelected(true);
            }
        } else if (isMouseIn(humanButton, e)) {
            if (humanButton.isMousePressed()) {
                resetButtonsBooleans();
                humanButton.setSelected(true);
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        soldierButton.setMouseOver(false);
        humanButton.setMouseOver(false);
        if (isMouseIn(soldierButton, e))
            soldierButton.setMouseOver(true);
        else if (isMouseIn(humanButton, e))
            humanButton.setMouseOver(true);
    }

    private boolean isMouseIn(CharapterButton b, MouseEvent e) {
        return b.getButtonBox().contains(e.getX(), e.getY());
    }

    private void resetButtonsBooleans() {
        isAnySelected = true;
        soldierButton.setSelected(false);
        humanButton.setSelected(false);
    }

    public void setAnySelected(boolean isAnySelected) {
        this.isAnySelected = isAnySelected;
    }
}
