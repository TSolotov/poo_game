package circusUI;

import java.awt.Rectangle;

public class CButton {
    private int x, y, width, height;
    private Rectangle buttonBox;

    public CButton(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        buttonBox = new Rectangle(x, y, width, height);
    }

    // * Getters & Setters
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Rectangle getButtonBox() {
        return buttonBox;
    }
}