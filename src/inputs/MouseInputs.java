package inputs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import main.GamePanel;
import states.GameState;

public class MouseInputs implements MouseListener, MouseMotionListener {
    private GamePanel gamePanel;

    public MouseInputs(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        switch (GameState.state) {
            case CIRCUS_PLAYING:
                gamePanel.getGame().getPlaying().mousePressed(e);
                break;
            case CONFIGURATION:
                gamePanel.getGame().getConfiguration().mousePressed(e);
                break;
            default:
                break;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        switch (GameState.state) {
            case CIRCUS_PLAYING:
                gamePanel.getGame().getPlaying().mouseReleased(e);
                break;
            case CONFIGURATION:
                gamePanel.getGame().getConfiguration().mouseReleased(e);
                break;
            default:
                break;
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        switch (GameState.state) {
            case CIRCUS_PLAYING:
                gamePanel.getGame().getPlaying().mouseMoved(e);
                break;
            case CONFIGURATION:
                gamePanel.getGame().getConfiguration().mouseMoved(e);
                break;
            default:
                break;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        return;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        return;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        return;
    }

    @Override
    public void mouseExited(MouseEvent e) {
        return;
    }
}
