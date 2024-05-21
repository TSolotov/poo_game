package circusOverlays;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.Timer;
import java.util.TimerTask;

import circusEntities.Player1;
import circusLevels.LevelHandler;
import states.CircusPlaying;
import states.GameState;

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
    private static int seconds = 0, secondLimit = 120, totalSeconds = 0;
    private Timer timer;

    public ScoreOverlay(CircusPlaying circusPlaying) {
        this.circusPlaying = circusPlaying;
        timer = new Timer();
        startTimer();
    }

    private void startTimer() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (GameState.state == GameState.CIRCUS_PLAYING) {
                    if (!circusPlaying.getPause())
                        if (!circusPlaying.getPlayer1().isDead() && !circusPlaying.getLevelComplete()
                                && !circusPlaying.getGameOver()) {
                            seconds++;
                            totalSeconds++;
                            CircusPlaying.setScore(-20);
                        }

                    if ((seconds >= secondLimit || CircusPlaying.getScore() <= 0) && !circusPlaying.getGameOver()) {
                        circusPlaying.setGameOver(true);
                        resetTimer();
                    }
                }
            }
        }, 0, 1000);
    }

    // ! Update & Draw
    public void update(Player1 player) {
        this.currentLives = player.getCurrentLives();
        this.currentLevel = LevelHandler.getNumberLevel() + 1;

        ScoreYPosition = 0;
        if (currentLevel == 2)
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
        g.drawString("Score: " + CircusPlaying.getScore(), ScoreXPosition + XPadding,
                ScoreYPosition + YPadding * 3 + metrics.getHeight() * 3);

    }

    public static void resetTimer() {
        seconds = 0;
    }

    public static int getTotalSeconds() {
        return totalSeconds;
    }
}
