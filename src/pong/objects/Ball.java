package pong.objects;

import static utils.Constants.FrameConstants.FRAME_HEIGHT;
import static utils.Constants.FrameConstants.FRAME_WIDTH;

import java.awt.*;

import audio.AudioPlayer;
import states.PongGame;
import utils.Constants.PongConstants;

public class Ball {

    private static int posX, posY;
    public static float velX, velY;
    private PongGame pongPlaying;

    public Ball(PongGame pongPlaying) {
        this.pongPlaying = pongPlaying;
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


        posX = (int) (posX + PongConstants.BALL_SPEED * velX);
        posY = (int) (posY + PongConstants.BALL_SPEED * velY);


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
        pongPlaying.getGame().getAudioPlayer().playSounds(AudioPlayer.BALL_BOUNCE);
        if (vertical) {
            velY *= (-1);
        } else {
            if (velX < 0)
                velX -= 0.2f;
            else
                velX += 0.2f;

            System.out.println(velX);
            velX *= (-1);
        }
    }
}
