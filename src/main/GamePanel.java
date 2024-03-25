package main;

import static utils.Constants.FrameConstants.*;
// Constants.FrameConstants.FRAME_WIDTH, Constants.FrameConstants.FRAME_HEIGHT

import javax.swing.*;
import java.awt.*;



public class GamePanel extends JPanel {
    private Game game;
    public GamePanel(Game game) {
        this.game = game;
        this.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));
    }

}
