package states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import circusLevels.LevelHandler;
import utils.LoadSprites;
import main.Game;
import utils.Constants;

import static utils.Constants.FrameConstants.*;

public class Menu extends State implements StateMethods {

    private BufferedImage[] bg_images;

    // Gestiona la selección del menu
    private int currentOption = 0;
    private String[] options = { "Play Circus (1 Jugador)", "Play Pong (2 Jugadores)",
            "Configuraciones", "Puntuaciones", "Salir" };

    public Menu(Game game) {
        super(game);

        loadBackground();
    }

    private void loadBackground() {
        bg_images = LoadSprites.getSprites(Constants.MenuConstants.getMenuSpritesInfo());
    }

    // * ---------------------------------------

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics g) {
        g.setColor(new Color(0, 0, 0, 50));
        g.drawImage(bg_images[0], 0, 0, FRAME_WIDTH, FRAME_HEIGHT, null);
        g.fillRect(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
        g.drawImage(bg_images[1], (FRAME_WIDTH - bg_images[1].getWidth()) / 2,
                75, null);

        g.setFont(new Font("Verdana", Font.CENTER_BASELINE, (int) (22 * Constants.SCALE)));

        for (int i = 0; i < options.length; i++) {
            if (i == currentOption)
                g.setColor(Color.GREEN);
            else
                g.setColor(Color.WHITE);

            g.drawString(options[i], (int) (100 * Constants.SCALE),
                    (int) ((400 * Constants.SCALE)
                            + (50 * Constants.SCALE) * i * Constants.SCALE));
        }
    }

    @Override
    public void keyPressed(KeyEvent k) {
        switch (k.getKeyCode()) {
            case KeyEvent.VK_W:
            case KeyEvent.VK_UP:
                if (currentOption == 0) {
                    currentOption = options.length - 1;
                } else {
                    currentOption--;
                }
                break;
            case KeyEvent.VK_S:
            case KeyEvent.VK_DOWN:
                if (currentOption == options.length - 1) {
                    currentOption = 0;
                } else {
                    currentOption++;
                }
                break;

            case KeyEvent.VK_ENTER:
            case KeyEvent.VK_Z:
                switch (currentOption) {
                    case 0:
                        this.setGamestate(GameState.CIRCUS_PLAYING);
                        game.getAudioPlayer().setMusic(LevelHandler.getNumberLevel());
                        break;
                    case 1:
                        this.setGamestate(GameState.PONG_PLAYING);
                        break;
                    case 2:
                        this.setGamestate(GameState.CONFIGURATION);
                        break;
                    case 3:
                        this.setGamestate(GameState.SCORES);
                        break;
                    default:
                        System.exit(0);
                        break;
                }
            default:
                break;
        }

    }

    @Override
    public void keyReleased(KeyEvent k) {
        return;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        return;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        return;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        return;
    }

}
