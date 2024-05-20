package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import main.GamePanel;
import states.GameState;

public class KeyInputs implements KeyListener {
    private GamePanel gamePanel;

    public KeyInputs(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Totalmente al pedo
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (GameState.state) {
            case MENU:
                gamePanel.getGame().getMenu().keyPressed(e);
                break;
            case CIRCUS_PLAYING:
                gamePanel.getGame().getPlaying().keyPressed(e);
                break;
            case PONG_PLAYING:
                gamePanel.getGame().getPongPlaying().keyPressed(e);
                break;
            case CONFIGURATION:
                gamePanel.getGame().getConfiguration().keyPressed(e);
                break;
            case SCORES:
                gamePanel.getGame().getScores().keyPressed(e);
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (GameState.state) {
            case MENU:
                gamePanel.getGame().getMenu().keyReleased(e);
                break;
            case CIRCUS_PLAYING:
                gamePanel.getGame().getPlaying().keyReleased(e);
                break;
            case PONG_PLAYING:
                gamePanel.getGame().getPongPlaying().keyReleased(e);
                break;
            case CONFIGURATION:
                gamePanel.getGame().getConfiguration().keyReleased(e);
            case SCORES:
                gamePanel.getGame().getScores().keyReleased(e);
            default:
                break;
        }
    }

}
