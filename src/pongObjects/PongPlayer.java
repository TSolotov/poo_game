package pongObjects;

import static utils.Constants.FrameConstants.FRAME_HEIGHT;
import static utils.Constants.FrameConstants.FRAME_WIDTH;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import audio.AudioPlayer;
import states.PongPlaying;
import utils.Constants.PongConstants;

public class PongPlayer {
    private PongPlaying pongPlaying;

    private int posX, posY;
    private int goals = 0, dir = 0;
    private boolean player_left, isWinner = false;
    private String username = "";

    public PongPlayer(Boolean pleft, PongPlaying pongPlaying) {
        this.pongPlaying = pongPlaying;
        this.player_left = pleft;

        if (pleft) {
            posX = PongConstants.PLAYER_1_START;
        } else {
            posX = PongConstants.PLAYER_2_START;
        }
        posY = PongConstants.PLAYER_START;
    }

    public void set_goal() {
        this.goals++;
        pongPlaying.getGame().getAudioPlayer().playSounds(AudioPlayer.GOAL);

        pongPlaying.setPause(true);
        if (goals >= 1) {
            pongPlaying.setWin(true);
            this.isWinner = true;
        }
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(posX, posY, PongConstants.PLAYER_WIDTH, PongConstants.PLAYER_HEIGHT);
        drawScore(g);
    }

    public void drawScore(Graphics g) {
        int dibujo;
        int padding = 25;
        String goalsText = Integer.toString(goals);
        Font fuente = new Font("Roboto", Font.PLAIN, 30);

        if (player_left) {
            int strWidth = g.getFontMetrics(fuente).stringWidth(goalsText);
            dibujo = FRAME_WIDTH / 2 - padding - strWidth;
        } else {
            dibujo = FRAME_WIDTH / 2 + padding;
        }

        g.setFont(fuente);
        g.drawString(goalsText, dibujo, 50);

    }

    public void update(Ball ball) {
        posY = Math.min(Math.max(posY + dir, 0), FRAME_HEIGHT - PongConstants.PLAYER_HEIGHT);

        int ballPosX = Ball.get_posicion(false);
        int ballPosY = Ball.get_posicion(true);

        // Player 1 o 2
        if (player_left) {
            if (ballPosX <= (PongConstants.PLAYER_WIDTH + posX) && posY <= (ballPosY + PongConstants.BALL_SIZE)
                    && ballPosY <= posY + PongConstants.PLAYER_HEIGHT) {
                ball.change_direction(false);
            }
        } else {
            if (ballPosX + PongConstants.BALL_SIZE >= posX && ballPosY + PongConstants.BALL_SIZE >= posY
                    && ballPosY <= posY + PongConstants.PLAYER_HEIGHT) {
                ball.change_direction(false);
            }
        }
    }

    public void stop() {
        dir = 0;
    }

    public void change_direction(int direction) {
        dir = PongConstants.PLAYER_SPEED * direction;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void addLetterToUsername(char letter) {
        this.username += letter;
    }

    public boolean getIsWinner() {
        return isWinner;
    }

    public int getGoals() {
        return goals;
    }

    public void resetPlayer() {
        goals = 0;
        isWinner = false;
        username = "";
        if (player_left) {
            posX = PongConstants.PLAYER_1_START;
        } else {
            posX = PongConstants.PLAYER_2_START;
        }
    }
}
