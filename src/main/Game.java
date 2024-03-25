package main;

public class Game {
    private GamePanel gamePanel;
    private GameFrame gameFrame;

    public Game() {
        this.gamePanel = new GamePanel(this);
        this.gameFrame = new GameFrame(gamePanel);
        this.gamePanel.setFocusable(true);
        this.gamePanel.requestFocus();
    }
}
