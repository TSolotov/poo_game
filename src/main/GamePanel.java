package main;

import static utils.Constants.FrameConstants.*;

import javax.swing.*;

import inputs.KeyInputs;
import inputs.MouseInputs;

import java.awt.*;

public class GamePanel extends JPanel {
    private GameSystem game;
    private MouseInputs mouseInputs;

    public GamePanel(GameSystem game) {
        this.game = game;

        this.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));

        mouseInputs = new MouseInputs(this);
        addKeyListener(new KeyInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    public GameSystem getGame() {
        return game;
    }

    // Se encarga del pintado global por así decirlo.
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        game.draw(g);

    }
}
