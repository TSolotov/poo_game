package ui;

import static utils.Constants.SCALE;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class KeyButton extends CButton {

    private boolean isClicked = false;
    private String stateText = "", keyCode;
    private int new_width = 0;

    public KeyButton(int x, int y, String keyCode) {
        super(x, y, (int) (47 * SCALE), (int) (47 * SCALE));
        buttonBox.y -= (int) (32 * SCALE);
        buttonBox.x -= (int) (25 * SCALE);
        this.keyCode = keyCode;
        stateText = keyCode;
    }

    public void update() {
        buttonBox.width = new_width;
        if (isClicked)
            stateText = "Presiona la Key";
        else if (mouseOver)
            stateText = "Cambiar Key";
        else
            stateText = KeyEvent.getKeyText(Integer.parseInt(keyCode));

    }

    public void draw(Graphics g, String text) {
        g.setFont(new Font("Roboto", Font.PLAIN, (int) (24 * SCALE)));
        g.setColor(Color.WHITE);

        FontMetrics metrics = g.getFontMetrics();
        this.new_width = metrics.stringWidth(text) + (int) (44 * SCALE) + metrics.stringWidth(stateText)
                + (int) (25 * SCALE);

        g.drawString(text, this.x, this.y);
        g.drawRoundRect(this.x - (int) (25 * SCALE), this.y - metrics.getHeight(),
                metrics.stringWidth(text) + (int) (44 * SCALE) + metrics.stringWidth(stateText) + (int) (25 * SCALE),
                (int) (47 * SCALE), (int) (10 * SCALE), (int) (10 * SCALE));

        if (isClicked) {
            g.setColor(Color.CYAN);
        } else if (mouseOver) {
            g.setColor(new Color(255, 255, 255, 25));
            g.fillRoundRect(this.x - (int) (25 * SCALE), this.y - metrics.getHeight(),
                    metrics.stringWidth(text) + (int) (44 * SCALE) + metrics.stringWidth(stateText)
                            + (int) (25 * SCALE),
                    (int) (47 * SCALE), (int) (10 * SCALE), (int) (10 * SCALE));
            g.setColor(Color.PINK);
        } else {
            g.setColor(Color.GREEN);
        }

        g.drawString(stateText, this.x - (int) (25 * SCALE) + metrics.stringWidth(text) + (int) (44 * SCALE),
                this.y);

        // g.setColor(Color.PINK);
        // g.drawRect(buttonBox.x, buttonBox.y, buttonBox.width, buttonBox.height);

    }

    public boolean isMouseOver() {
        return mouseOver;
    }

    public void setClicked(boolean isClicked) {
        this.isClicked = isClicked;
    }

    public boolean isClicked() {
        return isClicked;
    }

    public void setKeyCode(String keyCode) {
        this.keyCode = keyCode;
    }

    public String getKeyCode() {
        return keyCode;
    }
}
