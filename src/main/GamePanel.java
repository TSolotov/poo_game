package main;

import static utils.Constants.FrameConstants.*;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    private Game game;

    public GamePanel(Game game) {
        this.game = game;
        this.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT));

        // TODO - Realizar los eventos de teclado
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
