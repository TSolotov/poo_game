package ui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import utils.LoadSprites;
import utils.Constants.UIConstants;

public class TogleButton extends CButton {

    private ArrayList<BufferedImage[]> onAndOffImages;


    private boolean isOff = false;
    private int whatButton = 0, indexOfButton = 0;

    private String[] onPath, offPath;

    public TogleButton(int x, int y, String[] onPath, String[] offPath) {
        super(x, y, UIConstants.CONFIGURATION_BUTTON_SIZE, UIConstants.CONFIGURATION_BUTTON_SIZE);
        this.onPath = onPath;
        this.offPath = offPath;
        LoadSprites();
    }

    public TogleButton(int x, int y, String[] onPath, String[] offPath, boolean state) {
        super(x, y, UIConstants.CONFIGURATION_BUTTON_SIZE, UIConstants.CONFIGURATION_BUTTON_SIZE);
        this.onPath = onPath;
        this.offPath = offPath;
        this.isOff = state;
        LoadSprites();
    }

    private void LoadSprites() {
        onAndOffImages = new ArrayList<>();
        onAndOffImages.add(LoadSprites.getSprites(onPath));
        onAndOffImages.add(LoadSprites.getSprites(offPath));
    }

    public void update() {
        if (isOff)
            whatButton = 1;
        else
            whatButton = 0;

        indexOfButton = 0;
        if (mouseOver)
            indexOfButton = 1;

    }


    public void draw(Graphics g) {
        g.drawImage(onAndOffImages.get(whatButton)[indexOfButton], this.getX(), this.getY(), this.getWidth(),
                this.getHeight(), null);
    }

    public void togleState() {
        this.isOff = !this.isOff;
    }

    public boolean isMouseOver() {
        return mouseOver;
    }

    public boolean getState() {
        return isOff;
    }

}
