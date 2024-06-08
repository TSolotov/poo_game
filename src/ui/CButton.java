package ui;

import java.awt.Rectangle;

public abstract class CButton {
    protected int x, y, width, height;
    protected Rectangle buttonBox;
    protected boolean mouseOver, mousePressed;

    public CButton(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        buttonBox = new Rectangle(x, y, width, height);
    }

    // * Getters & Setters
    protected int getX() {
        return x;
    }

    protected int getY() {
        return y;
    }

    protected int getWidth() {
        return width;
    }

    protected int getHeight() {
        return height;
    }

    public Rectangle getButtonBox() {
        return buttonBox;
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

    public void resetMouseBooleans() {
        mouseOver = false;
        mousePressed = false;
    }
}
