package ui;

import static utils.Constants.SCALE;
import static utils.Constants.Player1Constants.IDLE;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import utils.Constants;
import utils.LoadSprites;
import utils.Constants.Player1Constants;

public class CharapterButton extends CButton {
    private BufferedImage[] CharapterButtonImage;

    private int aniIndex = 0, aniSpeed, aniTick;
    private boolean isSelected = false;

    public CharapterButton(int x, int y, int width, int height) {
        super(x, y, width, height);
        CharapterButtonImage = LoadSprites.getSprites(Constants.Player1Constants.getPlayer1SpritesInfo(IDLE));
    }

    // ! Update & Draw
    public void update() {
        if (mouseOver || isSelected) {
            updateAnimationTick();
        } else {
            aniIndex = 0;
        }
    }

    // * Controla la animación
    private void updateAnimationTick() {
        aniSpeed = 30;
        aniSpeed = 15;

        aniTick++;
        if (aniTick >= aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= Integer.parseInt(Player1Constants.getPlayer1SpritesInfo(IDLE)[1])) {
                aniIndex = 0;
            }
        }

    }

    public void draw(Graphics g, String name) {
        g.drawImage(CharapterButtonImage[aniIndex], this.getX(), this.getY(),
                this.getWidth(), this.getHeight(),
                null);

        g.setFont(new Font("Roboto", Font.ITALIC, (int) (32 * SCALE)));
        FontMetrics metrics = g.getFontMetrics();

        g.setColor(Color.WHITE);
        if (mouseOver)
            g.setColor(Color.PINK);
        if (isSelected) {
            g.setFont(new Font("Roboto", Font.BOLD, (int) (32 * SCALE)));
            g.setColor(Color.GREEN);
        }

        g.drawString(name, this.getX() + (this.getWidth() - metrics.stringWidth(name)) / 3,
                this.getY() + this.getHeight() + (int) (30 * SCALE));
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }
}
