package main;

import static utils.Constants.FrameConstants.*;

import javax.swing.*;

import inputs.KeyInputs;
import inputs.MouseInputs;

import java.awt.*;

public class GamePanel extends JPanel {
    private Game game;
    private MouseInputs mouseInputs;

    public GamePanel(Game game) {
        this.game = game;
        this.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));

        mouseInputs = new MouseInputs(this);
        // TODO - Realizar los eventos de teclado
        addKeyListener(new KeyInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    public Game getGame() {
        return game;
    }

    // Se encarga del pintado global por así decirlo.
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        game.draw(g);

    }
}
