package states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import utils.LoadSprites;
import main.Game;
import utils.Constants;
import static utils.Constants.FrameConstants.*;

public class Menu extends State implements StateMethods {

    private BufferedImage[] bg_images;

    // Gestiona la selección del menu
    private int currentOption = 0;
    private String[] options = { "Play Circus (1 Jugador)", "Play Pong (2 Jugadores)", "TODO - Archivos",
            "TODO - Configuraciones", "Salir" };

    public Menu(Game game) {
        super(game);

        loadBackground();
    }

    private void loadBackground() {
        bg_images = LoadSprites.getSprites(Constants.MenuConstants.getPlayer1SpritesInfo());
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

        g.setFont(new Font("Verdana", Font.CENTER_BASELINE, 22));
        // TODO - Agregar funcionalidad

        for (int i = 0; i < options.length; i++) {
            if (i == currentOption)
                g.setColor(Color.GREEN);
            else
                g.setColor(Color.WHITE);

            g.drawString(options[i], 100, 400 + 50 * i);
        }
    }

    @Override
    public void keyPressed(KeyEvent k) {
        switch (k.getKeyCode()) {
            case KeyEvent.VK_W:
                if (currentOption == 0) {
                    currentOption = options.length - 1;
                } else {
                    currentOption--;
                }
                break;
            case KeyEvent.VK_S:
                if (currentOption == options.length - 1) {
                    currentOption = 0;
                } else {
                    currentOption++;
                }
                break;

            case KeyEvent.VK_ENTER:
                switch (currentOption) {
                    case 0:
                        GameState.state = GameState.CIRCUS_PLAYING;
                        break;
                    case 1:
                        GameState.state = GameState.PONG_PLAYING;
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
        // ! Innecesario por ahora
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
