package states;

import main.Game;
import utils.CSVFile;
import utils.Constants;
import utils.LoadSprites;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static utils.Constants.SCALE;
import static utils.Constants.FrameConstants.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

public class Scores extends State implements StateMethods {
    private BufferedImage[] bg_images;
    private CSVFile csvFile;
    private FontMetrics metrics;

    private ArrayList<String[]> allCircusData;
    private String[] header = { "Username", "Puntuación", "Tiempo (sec)" };

    public Scores(Game game) {
        super(game);
        init();
        loadBackground();
    }

    private void init() {
        csvFile = new CSVFile();
        allCircusData = csvFile.readCSV(CSVFile.getFileNameCircus());
    }

    private void sortDataByScore() {
        Collections.sort(allCircusData, new Comparator<String[]>() {
            @Override
            public int compare(String[] o1, String[] o2) {
                return Integer.compare(Integer.parseInt(o2[1]), Integer.parseInt(o1[1]));
            }
        });
    }

    private void loadBackground() {
        bg_images = LoadSprites.getSprites(Constants.MenuConstants.getMenuSpritesInfo());
    }

    // * ---------------------------------------

    @Override
    public void update() {
        allCircusData = csvFile.readCSV(CSVFile.getFileNameCircus());
        sortDataByScore();
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(new Color(0, 0, 0, 50));
        g.drawImage(bg_images[0], 0, 0, FRAME_WIDTH, FRAME_HEIGHT, null);
        g.fillRect(0, 0, FRAME_WIDTH, FRAME_HEIGHT);

        g.setColor(new Color(0, 0, 0, 200));
        g.fillRect((int) (100 * SCALE), (int) (100 * SCALE), (int) (500 * SCALE), FRAME_HEIGHT - (int) (200 * SCALE));

        g.setColor(Color.PINK);
        g.setFont(new Font("Roboto", Font.BOLD, (int) (40 * SCALE)));

        metrics = g.getFontMetrics();
        g.drawString("Circus Charlie",
                (int) (100 + (500 / 2 * SCALE) - (metrics.stringWidth("Circus Charlie") / 2 * SCALE)),
                (int) (150 * SCALE));

        g.setColor(Color.GREEN);

        g.setFont(new Font("Roboto", Font.BOLD, (int) (24 * SCALE)));
        for (int i = 0; i < 3; i++) {
            g.drawString(header[i], (int) (120 * SCALE) + (int) (150 * SCALE) * i,
                    (int) (200 * SCALE));
        }

        g.setColor(Color.WHITE);
        g.setFont(new Font("Roboto", Font.PLAIN, (int) (24 * SCALE)));
        for (int i = 0; i < allCircusData.size() && i < 10; i++) {
            for (int j = 0; j < allCircusData.get(i).length; j++) {
                String complement;
                if (j == 1)
                    complement = " pts.";
                else if (j == 2)
                    complement = " sec.";
                else
                    complement = "";

                g.drawString(allCircusData.get(i)[j] + complement, (int) (120 * SCALE) + (int) (150 * SCALE) * j,
                        (int) (200 * SCALE) + (int) (40 * SCALE) * (i + 1));
            }
        }

    }

    @Override
    public void keyPressed(KeyEvent k) {
        switch (k.getKeyCode()) {
            case KeyEvent.VK_ESCAPE:
                GameState.state = GameState.MENU;
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent k) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

}
