package utils;

public class Constants {

    // * Contiene todos los paths e información acerca del player 1
    public static class Player1Constants {
        public static final int SPRITE_WIDTH = 64, SPRITE_HEIGHT = 44;

        public static final int IDLE = 0;
        public static final int RUNNING = 1;
        public static final int JUMP = 2;
        public static final int FALLING = 3;

        // * Player 1 paths atlas
        public static final String player1IdleAtlas = "resources/player1/idle/player_idle_";
        public static final String player1RunningAtlas = "resources/player1/run/player_run_";
        public static final String player1JumpingAtlas = "resources/player1/jump/player_jump_";
        public static final String player1FallingAtlas = "resources/player1/falling/player_falling_";

        // * Retorna la info necesaria para que la funcion loadSprites haga su trabajo.
        public static String[] getPlayer1SpritesInfo(int playerAction) {
            switch (playerAction) {
                case IDLE:
                    return new String[] { player1IdleAtlas, "6" };
                case RUNNING:
                    return new String[] { player1RunningAtlas, "8" };
                case JUMP:
                    return new String[] { player1JumpingAtlas, "3" };
                case FALLING:
                    return new String[] { player1FallingAtlas, "3" };
                default:
                    return new String[] { player1IdleAtlas, "6" };
            }

        }
    }

    // * Contiene tda la informacion en Menu
    public static class MenuConstants {
        public static final String menuAtlas = "resources/menu/bg_";

        public static String[] getPlayer1SpritesInfo() {
            return new String[] { menuAtlas, "2" };
        }

    }

    // * Contiene toda la información de lo que es el frame, la ventana
    public static class FrameConstants {
        public static final int FRAME_WIDTH = 1240;
        public static final int FRAME_HEIGHT = 720;
        public static final int FPS_SET = 144;
        public static final int UPS_SET = 240;

    }

}
