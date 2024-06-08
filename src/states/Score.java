package states;

import main.GameSystem;
import utils.Constants;
import utils.LoadSprites;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.Comparator;

import static utils.Constants.SCALE;
import static utils.Constants.FrameConstants.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

public class Score extends State implements StateMethods {
    private BufferedImage[] bg_images;
    private FontMetrics metrics;

    private String[] headerCircus = { "Username", "Puntuación", "Tiempo (sec)" };
    private String[] headerPong = { "Player 1", "Resultado", "Player 2" };
    private String[] gamesScore = { "Circus Scores", "Pong Scores" };
    private int currentScoreView = 0;

    public Score(GameSystem game) {
        super(game);
        loadBackground();
    }

    private void sortDataByScore() {
        allCircusData.sort(new Comparator<String[]>() {
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
        allCircusData = csvCircus.readCSV();
        allPongData = csvPong.readCSV();
        sortDataByScore();
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(bg_images[0], 0, 0, FRAME_WIDTH, FRAME_HEIGHT, null);

        g.setColor(new Color(0, 0, 0, 50));
        g.fillRect(0, 0, FRAME_WIDTH, FRAME_HEIGHT);

        g.setColor(Color.PINK);
        g.setFont(new Font("Roboto", Font.BOLD, (int) (24 * SCALE)));
        g.drawString("Juegos Disponibles", (int) (100 * SCALE), (int) (200 * SCALE));
        for (int i = 0; i < gamesScore.length; i++) {
            g.setColor(Color.WHITE);
            if (i == currentScoreView) {
                g.setColor(Color.GREEN);
            }
            g.drawString(gamesScore[i], (int) (100 * SCALE), (int) (250 * SCALE + 50 * SCALE * i));
        }

        switch (currentScoreView) {
            case 0:
                drawCircusScore(g);
                break;
            case 1:
                drawPongScore(g);
                break;
        }

    }

    // * Parte Circus ------------------------------------------
    private void drawCircusScore(Graphics g) {
        g.setColor(new Color(0, 0, 0, 150));
        g.fillRoundRect((int) (FRAME_WIDTH - 500 * SCALE - (100 * SCALE)), (int) (100 * SCALE), (int) (500 * SCALE),
                FRAME_HEIGHT - (int) (200 * SCALE), (int) (10 * SCALE), (int) (10 * SCALE));

        g.setColor(Color.PINK);
        g.setFont(new Font("Roboto", Font.BOLD, (int) (40 * SCALE)));

        metrics = g.getFontMetrics();
        g.drawString("Circus Charlie",
                (int) ((FRAME_WIDTH - 500 * SCALE - (100 * SCALE)) + (500 / 2 * SCALE)
                        - (metrics.stringWidth("Circus Charlie") / 2)),
                (int) (150 * SCALE));

        g.setColor(Color.GREEN);
        g.setFont(new Font("Roboto", Font.BOLD, (int) (24 * SCALE)));

        for (int i = 0; i < 3; i++) {
            g.drawString(headerCircus[i],
                    (int) (FRAME_WIDTH - 500 * SCALE - (100 * SCALE) / 2) + (int) (150 * SCALE) * i,
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

                g.drawString(allCircusData.get(i)[j] + complement,
                        (int) (FRAME_WIDTH - 500 * SCALE - (100 * SCALE) / 2) + (int) (150 * SCALE) * j,
                        (int) (200 * SCALE) + (int) (40 * SCALE) * (i + 1));
            }
        }
    }

    // * Parte Pong --------------------------------------------
    private void drawPongScore(Graphics g) {
        g.setColor(new Color(0, 0, 0, 150));
        g.fillRoundRect((int) (FRAME_WIDTH - 500 * SCALE - (100 * SCALE)), (int) (100 * SCALE), (int) (500 * SCALE),
                FRAME_HEIGHT - (int) (200 * SCALE), (int) (10 * SCALE), (int) (10 * SCALE));

        g.setColor(Color.PINK);
        g.setFont(new Font("Roboto", Font.BOLD, (int) (40 * SCALE)));

        metrics = g.getFontMetrics();
        g.drawString("Pong",
                (int) ((FRAME_WIDTH - 500 * SCALE - (100 * SCALE)) + (500 / 2 * SCALE)
                        - (metrics.stringWidth("Pong") / 2)),
                (int) (150 * SCALE));

        g.setColor(Color.GREEN);
        g.setFont(new Font("Roboto", Font.BOLD, (int) (24 * SCALE)));

        for (int i = 0; i < 3; i++) {
            g.drawString(headerPong[i], (int) (FRAME_WIDTH - 500 * SCALE - (100 * SCALE) / 2) + (int) (150 * SCALE) * i,
                    (int) (200 * SCALE));
        }

        g.setColor(Color.WHITE);
        g.setFont(new Font("Roboto", Font.PLAIN, (int) (24 * SCALE)));
        for (int i = 0; i < allPongData.size() && i < 10; i++) {
            g.drawString(allPongData.get(i)[0], (int) (FRAME_WIDTH - 500 * SCALE - (100 * SCALE) / 2) +
                    (int) (150 * SCALE) * 0, (int) (200 * SCALE) + (int) (40 * SCALE) * (i + 1));

            g.drawString(allPongData.get(i)[1] + " - " + allPongData.get(i)[3],
                    (int) (FRAME_WIDTH - 500 * SCALE - (100 * SCALE) / 2) +
                            (int) (180 * SCALE) * 1,
                    (int) (200 * SCALE) + (int) (40 * SCALE) * (i + 1));

            g.drawString(allPongData.get(i)[2], (int) (FRAME_WIDTH - 500 * SCALE - (100 * SCALE) / 2) +
                    (int) (150 * SCALE) * 2, (int) (200 * SCALE) + (int) (40 * SCALE) * (i + 1));

        }
    }

    @Override
    public void keyPressed(KeyEvent k) {
        if (k.getKeyCode() == KeyEvent.VK_ESCAPE) {
            GameState.state = GameState.MENU;
        }
    }

    @Override
    public void keyReleased(KeyEvent k) {
        switch (k.getKeyCode()) {
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                if (currentScoreView == gamesScore.length - 1)
                    currentScoreView = 0;
                else
                    currentScoreView++;
                break;
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                if (currentScoreView == 0)
                    currentScoreView = gamesScore.length - 1;
                else
                    currentScoreView--;
                break;
        }
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
