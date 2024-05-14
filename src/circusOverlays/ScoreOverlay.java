package circusOverlays;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.Timer;
import java.util.TimerTask;

import Entities.Player1;
import levels.LevelHandler;
import states.CircusPlaying;

import static utils.Constants.FrameConstants.*;

public class ScoreOverlay {
    private CircusPlaying circusPlaying;
    private FontMetrics metrics;

    // * Posicionamiento
    private final int SCORE_OVERLAY_HEIGHT = 150;
    private final int XPadding = 100, YPadding = 14;
    private int ScoreXPosition = 0, ScoreYPosition = 0;

    // * Scores variables
    private int currentLives;
    private int currentLevel;
    private int seconds = 0, secondLimit = 120;;
    private Timer timer;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public ScoreOverlay(CircusPlaying circusPlaying) {
        this.circusPlaying = circusPlaying;
        timer = new Timer();
    }

    private void startTimer() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                seconds++;
                if (seconds >= secondLimit) {
                    // circusPlaying.setGameOver(true);
                    cancel();
                }
            }
        }, 0, 1000);
    }

    // * Reseteo del timer al pasar de level
    public void resetTimer() {
        timer.cancel();
        timer = new Timer();
        startTimer();
    }

    // ! Update & Draw
    public void update(Player1 player) {
        this.currentLives = player.getCurrentLives();
        this.currentLevel = LevelHandler.getNumberLevel();

        ScoreYPosition = 0;
        if (currentLevel == 1)
            ScoreYPosition = FRAME_HEIGHT - SCORE_OVERLAY_HEIGHT;

    }

    public void draw(Graphics g) {
        g.setColor(new Color(0, 0, 0, 200));
        g.fillRect(ScoreXPosition, ScoreYPosition, FRAME_WIDTH, SCORE_OVERLAY_HEIGHT);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Verdana", Font.PLAIN, 24));

        metrics = g.getFontMetrics();
        g.drawString("Vidas: " + currentLives + " | Nivel: " + currentLevel, ScoreXPosition + XPadding,
                ScoreYPosition + YPadding + metrics.getHeight());
        g.drawString("Tiempo restante: " + seconds + "/" + secondLimit + " segundos", ScoreXPosition + XPadding,
                ScoreYPosition + YPadding * 2 + metrics.getHeight() * 2);
        g.drawString("Score: " + screenSize.getWidth() + " | " + screenSize.getHeight(), ScoreXPosition + XPadding,
                ScoreYPosition + YPadding * 3 + metrics.getHeight() * 3);

    }

}
