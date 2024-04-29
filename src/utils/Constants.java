package utils;

import static utils.Constants.CircusConstants.TILES_SIZE;
import static utils.Constants.CircusConstants.TILES_WIDTH;
import static utils.Constants.CircusConstants.TILE_HEIGHT;

public class Constants {
    // * Contiene tda la informacion en Menu
    public static class EnemyConstants {

        // * Sizes
        public static final int BOMB_X_DRAW_OFFSET = 23 * 3 / 2, BOMB_Y_DRAW_OFFSET = 22 * 3 / 2;
        public static final int BOMB_SPRITE_WIDTH = 64 * 3 / 2, BOMB_SPRITE_HEIGHT = 64 * 3 / 2;
        public static final int BOMB_REAL_WIDTH = 22 * 3 / 2, BOMB_REAL_HEIGHT = 29 * 3 / 2;

        // * Actions
        public static final int BOMB_RUNNING = 0;
        public static final int BOMB_JUMP = 1;
        public static final int BOMB_FALLING = 2;

        // * Paths
        private static final String bombRunningAtlas = "resources/circus/bomb/run/bomb_run_";
        private static final String bombJumpingAtlas = "resources/circus/bomb/jump/bomb_jump_";
        private static final String bombFallingAtlas = "resources/circus/bomb/falling/bomb_falling_";

        // * Retorna la info necesaria para que la funcion loadSprites haga su trabajo.
        public static String[] getEnemySpritesInfo(int enemyAction) {
            switch (enemyAction) {
                case BOMB_RUNNING:
                    return new String[] { bombRunningAtlas, "6" };
                case BOMB_JUMP:
                    return new String[] { bombJumpingAtlas, "1" };
                case BOMB_FALLING:
                    return new String[] { bombFallingAtlas, "1" };
                default:
                    return new String[] { bombRunningAtlas, "6" };
            }

        }

    }

    // * Contiene todos los paths e información acerca del player 1
    public static class Player1Constants {

        public static final int X_DRAW_OFFSET = 18 * 2, Y_DRAW_OFFSET = 10 * 2;
        public static final int SPRITE_WIDTH = 64 * 2, SPRITE_HEIGHT = 44 * 2;
        public static final int REAL_WIDTH = 18 * 2, REAL_HEIGHT = 33 * 2;

        public static final int IDLE = 0;
        public static final int RUNNING = 1;
        public static final int JUMP = 2;
        public static final int FALLING = 3;
        public static final int DEAD = 4;

        // * Player 1 paths atlas
        private static final String player1IdleAtlas = "resources/player1/idle/player_idle_";
        private static final String player1RunningAtlas = "resources/player1/run/player_run_";
        private static final String player1JumpingAtlas = "resources/player1/jump/player_jump_";
        private static final String player1FallingAtlas = "resources/player1/falling/player_falling_";
        private static final String player1DeadAtlas = "resources/player1/dead/player_dead_";

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
                case DEAD:
                    return new String[] { player1DeadAtlas, "10" };
                default:
                    return new String[] { player1IdleAtlas, "6" };
            }

        }
    }

    // * Contiene tda la informacion en Menu
    public static class MenuConstants {
        private static final String menuAtlas = "resources/menu/bg_";

        public static String[] getPlayer1SpritesInfo() {
            return new String[] { menuAtlas, "2" };
        }

    }

    public static class CircusConstants {
        public final static float GRAVITY = 0.0025f;

        // ! Constantes para la construcción del mapa (Los tiles) - Testing
        public final static int TILE_DEFAULT_SIZE = 32; // px
        public final static float SCALE = 1.0f;
        public final static int TILES_WIDTH = 40; // * De máximo serían 32 blocks de 32px (según la escala) de largo */
        public final static int TILE_HEIGHT = 24; // * De máximo serían 20 blocks de 32px (según la escala) de alto */
        public final static int TILES_SIZE = (int) (TILE_DEFAULT_SIZE * SCALE);

        // ! Constantes para elegir la info
        public static final int BG_CIRCUS = 0;
        public static final int TILES_CIRCUS = 1;
        // ! Constantes de los paths
        private static final String bgAtlas = "resources/circus/bg/bg_";
        private static final String tilesAtlas = "resources/circus/tiles/tile_";

        public static String[] getSpritesInfo(int option) {
            switch (option) {
                case BG_CIRCUS:
                    return new String[] { bgAtlas, "4" };
                case TILES_CIRCUS:
                    return new String[] { tilesAtlas, "3" };
                default:
                    System.out.println("Error en la opción");
                    return new String[] { "Null", "0" };
            }
        }

    }

    // * Contiene toda la información de lo que es el frame, la ventana
    public static class FrameConstants {
        public static final int FRAME_WIDTH = TILES_SIZE * TILES_WIDTH;
        public static final int FRAME_HEIGHT = TILES_SIZE * TILE_HEIGHT;
        public static final int FPS_SET = 144;
        public static final int UPS_SET = 240;

    }

    public static class Directions {
        public static final int RIGHT = 0;
        public static final int LEFT = 1;
    }
}
