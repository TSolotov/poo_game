package states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import utils.LoadSprites;
import main.Game;
import utils.Constants;
import static utils.Constants.FrameConstants.*;

public class Menu extends State implements StateMethods {

    private BufferedImage[] bg_images;

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

        g.setColor(Color.WHITE);
        g.setFont(new Font("Verdana", Font.BOLD, 22));

        // TODO - Agregar funcionalidad
        g.drawString("Play Circus (1 Jugador)", 100, 400);
        g.drawString("Play Pong (2 Jugadores)", 100, 450);
        g.drawString("Salir", 100, 500);
    }

}
