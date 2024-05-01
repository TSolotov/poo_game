package circusUI;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import utils.Constants.UIConstants;
import utils.LoadSprites;

// * Continue - Reset - Menu significa
public class CRMButton extends CButton {

    private BufferedImage[] CRMButtonImage;
    private int indexButtonImage;

    private boolean mouseOver, mousePressed;

    public CRMButton(int x, int y, int width, int height, int whatButton) {
        super(x, y, width, height);
        CRMButtonImage = LoadSprites.getSprites(UIConstants.getSpritesInfo(whatButton));
    }

    // ! Update & Draw
    public void update() {
        indexButtonImage = 0;
        if (mouseOver)
            indexButtonImage = 1;
    }

    public void draw(Graphics g, String text) {
        g.setFont(new Font("Arial", Font.BOLD, 18));
        FontMetrics metrics = g.getFontMetrics();
        g.drawImage(CRMButtonImage[indexButtonImage], this.getX(), this.getY(), this.getWidth(), this.getHeight(),
                null);
        g.drawString(text, this.getX() + (UIConstants.SQUARE_BUTTON_SIZE - metrics.stringWidth(text)) / 2,
                this.getY() + UIConstants.SQUARE_BUTTON_SIZE + 25);
    }

    // * Setters & Getters
    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

    public boolean isMousePressed() {
        return mousePressed;
    }

    // * Reset Booleans
    public void resetMouseBooleans() {
        mouseOver = false;
        mousePressed = false;
    }
}
