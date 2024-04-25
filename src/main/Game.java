package main;

import java.lang.Thread;

import states.GameState;
import states.Menu;
import states.CircusPlaying;

import java.awt.Graphics;

import static utils.Constants.FrameConstants.FPS_SET;
import static utils.Constants.FrameConstants.UPS_SET;

public class Game implements Runnable {
    private GamePanel gamePanel;

    // * Variables para el func del Hilo
    private Thread gameThread;

    // * Objeto Playing
    private CircusPlaying circusPlaying;
    private Menu menu;

    public Game() {
        init();
        this.gamePanel = new GamePanel(this);
        new GameFrame(gamePanel);
        this.gamePanel.setFocusable(true);
        this.gamePanel.requestFocus();

        // game loop
        startGameLoop();
    }

    public void init() {
        circusPlaying = new CircusPlaying(this);
        menu = new Menu(this);

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
        // El valor se toma en nanosegundos
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

            // Imprime los frames cada segundo "1000 milis"
            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames + " | UPS: " + updates);
                frames = 0;
                updates = 0;
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
            case CIRCUS_PLAYING:
                circusPlaying.update();
                break;
            case MENU:
                menu.update();
                break;

            default:
                break;
        }
    }

    // * Igual que el update pero con el draw
    public void draw(Graphics g) {
        switch (GameState.state) {
            case CIRCUS_PLAYING:
                circusPlaying.draw(g);
                break;
            case MENU:
                menu.draw(g);
                break;

            default:
                break;
        }
    }

    // * Getters
    public CircusPlaying getPlaying() {
        return circusPlaying;
    }
}
