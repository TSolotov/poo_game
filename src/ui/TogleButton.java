package ui;

import static utils.Constants.SCALE;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

public class TogleButton extends CButton {

    private boolean isOn = true;
    private String stateText = "Activada";
    private int new_width = 0;

    public TogleButton(int x, int y) {
        super(x, y, (int) (47 * SCALE), (int) (47 * SCALE));
        buttonBox.x -= (int) (25 * SCALE);
        buttonBox.y -= (int) (32 * SCALE);

    }

    public TogleButton(int x, int y, boolean state) {
        super(x, y, (int) (47 * SCALE), (int) (47 * SCALE));
        buttonBox.y -= (int) (32 * SCALE);
        buttonBox.x -= (int) (25 * SCALE);
        this.isOn = state;
    }

    public void update() {
        if (isOn)
            stateText = "Activado";
        else
            stateText = "Desactivado";

        buttonBox.width = new_width;
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

        if (mouseOver) {
            g.setColor(new Color(255, 255, 255, 25));
            g.fillRoundRect(this.x - (int) (25 * SCALE), this.y - metrics.getHeight(),
                    metrics.stringWidth(text) + (int) (44 * SCALE) + metrics.stringWidth(stateText)
                            + (int) (25 * SCALE),
                    (int) (47 * SCALE), (int) (10 * SCALE), (int) (10 * SCALE));
        }

        g.setColor(Color.red);
        if (isOn)
            g.setColor(Color.green);

        g.drawString(stateText, this.x - (int) (25 * SCALE) + metrics.stringWidth(text) + (int) (44 * SCALE),
                this.y);

        // g.setColor(Color.PINK);
        // g.drawRect(buttonBox.x, buttonBox.y, buttonBox.width, buttonBox.height);

    }

    public void togleState() {
        this.isOn = !this.isOn;
    }

    public boolean isMouseOver() {
        return mouseOver;
    }

    public boolean getState() {
        return isOn;
    }

}
