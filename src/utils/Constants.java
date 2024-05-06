package utils;

import static utils.Constants.CircusConstants.TILES_SIZE;
import static utils.Constants.CircusConstants.TILES_WIDTH;
import static utils.Constants.CircusConstants.TILE_HEIGHT;
import static utils.Constants.FrameConstants.FRAME_HEIGHT;
import static utils.Constants.FrameConstants.FRAME_WIDTH;

public class Constants {
    // * Contiene tda la informacion en Menu
    public static class EnemyConstants {

        // * Sizes
        public static final int BOMB_X_DRAW_OFFSET = 23 * 3 / 2, BOMB_Y_DRAW_OFFSET = 22 * 3 / 2;
        public static final int BOMB_SPRITE_WIDTH = 64 * 3 / 2, BOMB_SPRITE_HEIGHT = 64 * 3 / 2;
        public static final int BOMB_REAL_WIDTH = 22 * 3 / 2, BOMB_REAL_HEIGHT = 29 * 3 / 2;

        public static final int CHICKEN_X_DRAW_OFFSET = 1 * 2, CHICKEN_Y_DRAW_OFFSET = 2 * 2;
        public static final int CHICKEN_SPRITE_WIDTH = 20 * 2, CHICKEN_SPRITE_HEIGHT = 21 * 2;
        public static final int CHICKEN_REAL_WIDTH = 18 * 2, CHICKEN_REAL_HEIGHT = 18 * 2;

        public static final int HORSE_X_DRAW_OFFSET = 13 * 2, HORSE_Y_DRAW_OFFSET = 31 * 2;
        public static final int HORSE_SPRITE_WIDTH = 48 * 2, HORSE_SPRITE_HEIGHT = 48 * 2;
        public static final int HORSE_REAL_WIDTH = 23 * 2, HORSE_REAL_HEIGHT = 16 * 2;

        // * Actions
        public static final int BOMB_RUNNING = 0;
        public static final int BOMB_JUMP = 1;
        public static final int BOMB_FALLING = 2;
        public static final int CHICKEN_WALK = 3;
        public static final int CHICKEN_FALLING = 4;
        public static final int HORSE_WALK = 5;
        public static final int HORSE_RUN = 6;

        // * Paths
        private static final String bombRunningAtlas = "resources/circus/bomb/run/bomb_run_";
        private static final String bombJumpingAtlas = "resources/circus/bomb/jump/bomb_jump_";
        private static final String bombFallingAtlas = "resources/circus/bomb/falling/bomb_falling_";
        private static final String chickenWalkAtlas = "resources/circus/chicken/walk/chicken_walk_";
        private static final String chickenFallingAtlas = "resources/circus/chicken/falling/chicken_falling_";
        private static final String horseWalkAtlas = "resources/circus/horse/walk/walk_";
        private static final String horseRunAtlas = "resources/circus/horse/run/run_";

        // * Retorna la info necesaria para que la funcion loadSprites haga su trabajo.
        public static String[] getEnemySpritesInfo(int enemyAction) {
            switch (enemyAction) {
                case BOMB_RUNNING:
                    return new String[] { bombRunningAtlas, "6" };
                case BOMB_JUMP:
                    return new String[] { bombJumpingAtlas, "1" };
                case BOMB_FALLING:
                    return new String[] { bombFallingAtlas, "1" };
                case CHICKEN_WALK:
                    return new String[] { chickenWalkAtlas, "4" };
                case CHICKEN_FALLING:
                    return new String[] { chickenFallingAtlas, "5" };
                case HORSE_WALK:
                    return new String[] { horseWalkAtlas, "8" };
                case HORSE_RUN:
                    return new String[] { horseRunAtlas, "5" };

                default:
                    return new String[] { bombRunningAtlas, "6" };
            }
        }

    }

    // * Contiene todos los paths e información acerca del player 1
    public static class Player1Constants {

        // * Player sizes
        public static final int X_DRAW_OFFSET = 18 * 2, Y_DRAW_OFFSET = 10 * 2;
        public static final int SPRITE_WIDTH = 64 * 2, SPRITE_HEIGHT = 44 * 2;
        public static final int REAL_WIDTH = 18 * 2, REAL_HEIGHT = 33 * 2;

        // * Player actions
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
        public final static float GRAVITY = 0.025f;

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
                    return new String[] { tilesAtlas, "4" };
                default:
                    System.out.println("Error en la opción");
                    return new String[] { "Null", "0" };
            }
        }

    }

    public static class ObjectConstants {
        // ! Sizes
        public static int FLAME_X_DRAW_OFFSET = 14 * 3 / 2, FLAME_Y_DRAW_OFFSET = 22 * 3 / 2,
                FLAME_Y_DISTANCE_TO_TOUCH_FLOOR = 8 * 3 / 2;
        public static final int FLAME_SPRITE_WIDTH = 64 * 3 / 2, FLAME_SPRITE_HEIGHT = 64 * 3 / 2;
        public static final int FLAME_REAL_WIDTH = 36 * 3 / 2, FLAME_REAL_HEIGHT = 35 * 3 / 2;

        public static int RING_X_DRAW_OFFSET = 70, RING_Y_DRAW_OFFSET = 10;
        public static final int RING_SPRITE_WIDTH = 192, RING_SPRITE_HEIGHT = 192;
        public static final int RING_REAL_WIDTH = 35, RING_REAL_HEIGHT = 20;

        public static int SMALL_RING_X_DRAW_OFFSET = 74 * 3 / 4, SMALL_RING_Y_DRAW_OFFSET = 10 * 3 / 4;
        public static final int SMALL_RING_SPRITE_WIDTH = 192 * 3 / 4, SMALL_RING_SPRITE_HEIGHT = 192 * 3 / 4;
        public static final int SMALL_RING_REAL_WIDTH = 30 * 3 / 4, SMALL_RING_REAL_HEIGHT = 20 * 3 / 4;

        public static int TRAMPOLINE_X_DRAW_OFFSET = 0, TRAMPOLINE_Y_DRAW_OFFSET = 0;
        public static final int TRAMPOLINE_SPRITE_WIDTH = 32, TRAMPOLINE_SPRITE_HEIGHT = 32;
        public static final int TRAMPOLINE_REAL_WIDTH = 32, TRAMPOLINE_REAL_HEIGHT = 32;

        // ! Constantes para elegir la info
        public static final int FLAME = 0, RING = 1, TRAMPOLINE = 2;

        // ! Constantes de los paths
        private static final String flame_sprites = "resources/circus/flame/flame_";
        private static final String ring_sprites = "resources/circus/ring/ring_";
        private static final String trampoline_sprites = "resources/circus/trampoline/tramp_";

        public static String[] getSpritesInfo(int option) {
            switch (option) {
                case FLAME:
                    return new String[] { flame_sprites, "12" };

                case RING:
                    return new String[] { ring_sprites, "40" };

                case TRAMPOLINE:
                    return new String[] { trampoline_sprites, "5" };

                default:
                    System.out.println("Error en la opción");
                    return new String[] { "Null", "0" };
            }
        }

    }

    public static class OverlayConstants {

        // ! Constantes para elegir la info
        public static final int BG_LOSE_OVERLAY = 0;
        public static int BG_LOSE_OVERLAY_WIDTH = FRAME_WIDTH / 2, BG_LOSE_OVERLAY_HEIGHT = FRAME_HEIGHT * 3 / 4;

        // ! Constantes de los paths
        private static final String bg_lose_overlay = "resources/UI/bg_overlay/bg_";

        public static String[] getSpritesInfo(int option) {
            switch (option) {
                case BG_LOSE_OVERLAY:
                    return new String[] { bg_lose_overlay, "1" };
                default:
                    System.out.println("Error en la opción");
                    return new String[] { "Null", "0" };
            }
        }

    }

    public static class UIConstants {

        public static final int SQUARE_BUTTON_SIZE = 75; // * 32x32px

        // ! Constantes para elegir la info
        public static final int CONTINUE = 0;
        public static final int RESET = 1;
        public static final int MENU = 2;

        // ! Constantes de los paths
        private static final String contunueButton = "resources/UI/buttons/continue_button_";
        private static final String resetButton = "resources/UI/buttons/reset_button_";
        private static final String menuButton = "resources/UI/buttons/menu_button_";

        public static String[] getSpritesInfo(int option) {
            switch (option) {
                case CONTINUE:
                    return new String[] { contunueButton, "2" };
                case RESET:
                    return new String[] { resetButton, "2" };
                case MENU:
                    return new String[] { menuButton, "2" };
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

    public static class PongConstants {
        // Pelota
        public static final int BALL_SIZE = 15;
        public static final int BALL_SPEED = 1;
        public static final int BALL_MOVEMENT = BALL_SIZE * BALL_SPEED;

        public static int getXposition() {
            int retorno = (FRAME_WIDTH / 2) - (BALL_SIZE / 2);
            return retorno;
        }

        public static int getYposition() {
            int retorno = (FRAME_HEIGHT / 2) - (BALL_SIZE / 2);
            return retorno;
        }

        // Sign
        public static int sign(double d) {
            if (d <= 0)
                return -1;

            return 1;
        }

        // Player
        public static final int PLAYER_SPEED = 5;
        public static final int PLAYER_HEIGHT = 80;
        public static final int PLAYER_WIDTH = 20;
        public static final int PLAYER_PADDING = 20;

        public static final int PLAYER_1_START = 0;
        public static final int PLAYER_2_START = FRAME_WIDTH - PLAYER_WIDTH;
        public static final int PLAYER_START = (FRAME_HEIGHT / 2) - (PLAYER_HEIGHT / 2);
    }

}
