package pongObjects;

import static utils.Constants.FrameConstants.FRAME_HEIGHT;
import static utils.Constants.FrameConstants.FRAME_WIDTH;

import java.awt.*;

import utils.Constants.PongConstants;

public class Ball {

    private static int posX, posY;
    public static int velX, velY;

    public Ball() {
        reset_ball();
    }

    public void reset_ball() {
        posX = PongConstants.getXposition();
        posY = PongConstants.getYposition();
        velX = PongConstants.sign(Math.random() * 2.0 - 1);
        velY = PongConstants.sign(Math.random() * 2.0 - 1);
    }

    public void draw(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(posX, posY, PongConstants.BALL_SIZE, PongConstants.BALL_SIZE);
    }

    public void update(PongPlayer player1, PongPlayer player2) {

        posX = posX + PongConstants.BALL_SPEED * velX;
        posY = posY + PongConstants.BALL_SPEED * velY;

        // Punto para jugador 1
        if (FRAME_WIDTH <= (posX + PongConstants.BALL_SIZE)) {
            reset_ball();
            player1.set_goal();
        }

        // Punto para jugador 2
        if ((posX + PongConstants.BALL_SIZE) <= 0) {
            reset_ball();
            player2.set_goal();
        }
        // System.out.println((posX+PongConstants.BALL_SIZE));
        // Rebote en vertical
        if (FRAME_HEIGHT <= (posY + PongConstants.BALL_SIZE) || posY <= 0) {
            change_direction(true);
        }

    }

    // Retorna la posicion de la pelota
    public static int get_posicion(Boolean vertical) {
        int retorno;
        if (vertical) {
            retorno = posY;
        } else {
            retorno = posX;
        }

        return retorno;
    }

    public void change_direction(Boolean vertical) {
        // Movimiento vertical u horizontal

        if (vertical) {
            velY *= (-1);
        } else {
            velX *= (-1);
        }
    }
}
