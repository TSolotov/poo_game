package pongObjects;

import static utils.Constants.FrameConstants.FRAME_HEIGHT;

import java.awt.Color;
import java.awt.Graphics;

import utils.Constants.PongConstants;

public class PongPlayer {
    private int posX, posY;
    private int goals = 0, dir = 0;
    private boolean player_left;

    public PongPlayer(Boolean pleft) {
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
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(posX, posY, PongConstants.PLAYER_WIDTH, PongConstants.PLAYER_HEIGHT);
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
                    && ballPosY <= posY + FRAME_HEIGHT) {
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

}
