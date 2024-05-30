package main;

import java.awt.*;
import java.lang.Thread;

import audio.AudioPlayer;

import states.GameState;
import states.Menu;
import states.PongGame;
import states.Score;
import states.CircusGame;
import states.Configuration;
import utils.EnvConfig;

import static utils.Constants.FrameConstants.*;

public class GameSystem implements Runnable {
    private GamePanel gamePanel;

    // * Variables para el func del Hilo
    private Thread gameThread;

    // * Objeto drawiables
    private CircusGame circusPlaying;
    private Menu menu;
    private PongGame pongPlaying;
    private Configuration configuration;
    private Score scores;

    // * Audio
    private AudioPlayer audioPlayer;

    // .env
    private EnvConfig envFile;
    private boolean envFileChanged = false;
    private int secondsToRestart = 6;
    private FontMetrics metrics;

    public GameSystem(EnvConfig envFile) {
        this.envFile = envFile;

        init();
        this.gamePanel = new GamePanel(this);
        new GameFrame(gamePanel);
        this.gamePanel.setFocusable(true);
        this.gamePanel.requestFocus();

        // game loop
        startGameLoop();
    }

    public void init() {

        circusPlaying = new CircusGame(this);
        menu = new Menu(this);
        pongPlaying = new PongGame(this);
        configuration = new Configuration(this);
        scores = new Score(this);

        audioPlayer = new AudioPlayer();

    }

    // * Inicializo el hilo que usará el juego
    private void startGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    // * Funcion requerida por la interface "Runnable" - Controla los draw y updates
    // * de los estados, también tiene un controlador de fps y ups integradao a
    // * 144fps y 240ups
    @Override
    public void run() {
        // * El valor se toma en nanosegundos
        double timePerFrame = 1000000000.0 / FPS_SET; // Frames per second
        double timePerUpdate = 1000000000.0 / UPS_SET; // Update per second

        long previousTime = System.nanoTime();

        int frames = 0;
        int updates = 0;
        long lastCheck = System.currentTimeMillis();

        double deltaU = 0, deltaF = 0;

        while (true) {
            long currentTime = System.nanoTime();
            deltaF += (currentTime - previousTime) / timePerFrame;
            deltaU += (currentTime - previousTime) / timePerUpdate;
            previousTime = currentTime;

            if (deltaU >= 1) {
                update();
                updates++;
                deltaU--;
            }

            if (deltaF >= 1) {
                gamePanel.repaint();
                frames++;
                deltaF--;
            }

            // * Imprime los frames cada segundo "1000 milis - Sin relevancia"
            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames + " | UPS:" + updates);
                frames = 0;
                updates = 0;
                if (envFileChanged)
                    secondsToRestart -= 1;
                if (secondsToRestart == 0)
                    System.exit(0);
            }
        }

    }

    /*
     * Esta función se encargará de los updates de los estados..
     * Si estamos en el estado de Menú, se actualizará el menú, lo mismo para el
     * juego y/o posibles seguientes estados
     */
    public void update() {
        switch (GameState.state) {
            case CIRCUS_GAME:
                circusPlaying.update();
                break;
            case MENU:
                menu.update();
                break;
            case PONG_GAME:
                pongPlaying.update();
                break;
            case CONFIGURATION:
                configuration.update();
                break;
            case SCORES:
                scores.update();
                break;
            default:
                break;
        }
    }

    // * Igual que el update pero con el draw
    public void draw(Graphics g) {
        switch (GameState.state) {
            case CIRCUS_GAME:
                circusPlaying.draw(g);
                break;
            case MENU:
                menu.draw(g);
                break;
            case PONG_GAME:
                pongPlaying.draw(g);
                break;
            case CONFIGURATION:
                configuration.draw(g);
                break;
            case SCORES:
                scores.draw(g);
                break;
            default:
                break;
        }

        if (envFileChanged) {
            g.setFont(new Font("Roboto", Font.BOLD, 24));
            metrics = g.getFontMetrics();
            g.setColor(Color.BLACK);
            g.fillRoundRect((FRAME_WIDTH - 400) / 2, (FRAME_HEIGHT - 150) / 2, 400, 150, 20, 20);
            g.setColor(Color.WHITE);
            g.drawString("Se han realizado cambios",
                    (FRAME_WIDTH - metrics.stringWidth("Se han realizado cambios")) / 2,
                    (FRAME_HEIGHT - 150) / 2 + metrics.getHeight() + 25);
            g.drawString(secondsToRestart + " sec. para reiniciar",
                    (FRAME_WIDTH - metrics.stringWidth(secondsToRestart + " sec. para reiniciar")) / 2,
                    (FRAME_HEIGHT - 150) / 2 + metrics.getHeight() + 75);
        }
    }

    // * Getters
    public CircusGame getCircusGame() {
        return circusPlaying;
    }

    public Menu getMenu() {
        return menu;
    }

    public PongGame getPongGame() {
        return pongPlaying;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public Score getScores() {
        return scores;
    }

    public AudioPlayer getAudioPlayer() {
        return audioPlayer;
    }

    public EnvConfig getEnvFile() {
        return envFile;
    }

    public void setEnvFileChanged(boolean envFileChanged) {
        this.envFileChanged = envFileChanged;
    }
}
