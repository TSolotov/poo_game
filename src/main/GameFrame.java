package main;

import javax.swing.*;

public class GameFrame {
    private JFrame jframe;

    public GameFrame(GamePanel panel) {
        jframe = new JFrame("Circus Charlie");
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.add(panel);
        jframe.setResizable(false);

        jframe.pack();
        jframe.setLocationRelativeTo(null);
        jframe.setVisible(true);
    }

}
