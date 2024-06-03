package utils;

import static utils.Constants.FrameConstants.FRAME_HEIGHT;
import static utils.Constants.FrameConstants.FRAME_WIDTH;

import java.awt.Toolkit;
import java.util.Properties;

public class Constants {
    public static float SCALE;
    public static boolean ORIGINAL_SPRITES;

    public Constants(Properties props) {
        ORIGINAL_SPRITES = Boolean.parseBoolean(props.getProperty("ORIGINAL_SPRITES"));
        if (Boolean.parseBoolean(props.getProperty("FULL_SCREEN"))) {
            SCALE = (float) Toolkit.getDefaultToolkit().getScreenSize().getHeight()
                    / (float) FrameConstants.FRAME_HEIGHT;
            FrameConstants.FRAME_WIDTH = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
            FrameConstants.FRAME_HEIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
        } else {
            FrameConstants.FRAME_WIDTH = 1280;
            FrameConstants.FRAME_HEIGHT = 720;
            SCALE = 1.0f;
        }
    }

    // * Contiene tda la informacion en Menu
    public static class EnemyConstants {
        // * Sizes
        public static int BOMB_X_DRAW_OFFSET = (int) ((23 * SCALE * 3 / 2)),
                BOMB_Y_DRAW_OFFSET = (int) ((22 * SCALE * 3 / 2));
        public static int BOMB_SPRITE_WIDTH = (int) ((64 * SCALE * 3 / 2)),
                BOMB_SPRITE_HEIGHT = (int) ((64 * SCALE * 3 / 2));
        public static int BOMB_REAL_WIDTH = (int) ((22 * SCALE * 3 / 2)),
                BOMB_REAL_HEIGHT = (int) ((29 * SCALE * 3 / 2));

        public static int CHICKEN_X_DRAW_OFFSET = (int) (1 * SCALE * 2),
                CHICKEN_Y_DRAW_OFFSET = (int) (2 * SCALE * 2);
        public static int CHICKEN_SPRITE_WIDTH = (int) (20 * SCALE * 2),
                CHICKEN_SPRITE_HEIGHT = (int) (21 * SCALE * 2);
        public static int CHICKEN_REAL_WIDTH = (int) (18 * SCALE * 2),
                CHICKEN_REAL_HEIGHT = (int) (18 * SCALE * 2);

        public static int HORSE_X_DRAW_OFFSET = (int) (13 * SCALE * 2),
                HORSE_Y_DRAW_OFFSET = (int) (31 * SCALE * 2);
        public static int HORSE_SPRITE_WIDTH = (int) (48 * SCALE * 2),
                HORSE_SPRITE_HEIGHT = (int) (48 * SCALE * 2);
        public static int HORSE_REAL_WIDTH = (int) (23 * SCALE * 2),
                HORSE_REAL_HEIGHT = (int) (16 * SCALE * 2);

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

        // * Soldier sizes
        public static int X_DRAW_OFFSET = (int) (18 * SCALE * 2), Y_DRAW_OFFSET = (int) (10 * SCALE * 2);
        public static int SPRITE_WIDTH = (int) (64 * SCALE * 2), SPRITE_HEIGHT = (int) (44 * SCALE * 2);
        public static int REAL_WIDTH = (int) (18 * SCALE * 2), REAL_HEIGHT = (int) (33 * SCALE * 2);

        // * Soldier actions
        public static final int IDLE = 0;
        public static final int RUNNING = 1;
        public static final int JUMP = 2;
        public static final int FALLING = 3;
        public static final int DEAD = 4;

        // * Soldier 1 paths atlas
        private static final String soldierIdleAtlas = "resources/player1/idle/player_idle_";
        private static final String soldierRunningAtlas = "resources/player1/run/player_run_";
        private static final String soldierJumpingAtlas = "resources/player1/jump/player_jump_";
        private static final String soldierFallingAtlas = "resources/player1/falling/player_falling_";
        private static final String soldierDeadAtlas = "resources/player1/dead/player_dead_";

        // * HUMAN sizes
        public static int HUMAN_X_DRAW_OFFSET = (int) (16 * SCALE * 2), HUMAN_Y_DRAW_OFFSET = (int) (2 * SCALE * 2);
        public static int HUMAN_SPRITE_WIDTH = (int) (48 * SCALE * 2), HUMAN_SPRITE_HEIGHT = (int) (36 * SCALE * 2);
        public static int HUMAN_REAL_WIDTH = (int) (18 * SCALE * 2), HUMAN_REAL_HEIGHT = (int) (33 * SCALE * 2);

        // * Soldier 1 paths atlas
        private static final String humanIdleAtlas = "resources/player1/human_idle/idle_";
        private static final String humanRunningAtlas = "resources/player1/human_run/run_";
        private static final String humanJumpingAtlas = "resources/player1/human_jump/jump_";
        private static final String humanFallingAtlas = "resources/player1/human_falling/falling_";
        private static final String humanDeadAtlas = "resources/player1/human_dead/dead_";

        // * Retorna la info necesaria para que la funcion loadSprites haga su trabajo.
        public static String[] getPlayer1SpritesInfo(int playerAction) {
            switch (playerAction) {
                case IDLE:
                    if (ORIGINAL_SPRITES)
                        return new String[] { soldierIdleAtlas, "6" };
                    else
                        return new String[] { humanIdleAtlas, "10" };

                case RUNNING:
                    if (ORIGINAL_SPRITES)
                        return new String[] { soldierRunningAtlas, "8" };
                    else
                        return new String[] { humanRunningAtlas, "9" };

                case JUMP:
                    if (ORIGINAL_SPRITES)
                        return new String[] { soldierJumpingAtlas, "3" };
                    else
                        return new String[] { humanJumpingAtlas, "1" };

                case FALLING:
                    if (ORIGINAL_SPRITES)
                        return new String[] { soldierFallingAtlas, "3" };
                    else
                        return new String[] { humanFallingAtlas, "1" };

                case DEAD:
                    if (ORIGINAL_SPRITES)
                        return new String[] { soldierDeadAtlas, "10" };
                    else
                        return new String[] { humanDeadAtlas, "1" };

                default:
                    return new String[] { soldierIdleAtlas, "6" };
            }

        }
    }

    // * Contiene tda la informacion en Menu
    public static class MenuConstants {
        private static final String menuAtlas = "resources/menu/bg_";

        public static String[] getMenuSpritesInfo() {
            return new String[] { menuAtlas, "2" };
        }

    }

    public static class CircusConstants {

        // ! Constantes para la construcción del mapa (Los tiles)
        public final static int TILE_DEFAULT_SIZE = 32;
        // public static float SCALE = 900.0f / 768.0f;

        public static int TILES_WIDTH = 40; // * De máximo serían 43 blocks de 32px (según la escala) de largo */
        public static int TILE_HEIGHT = 22; // * De máximo serían 24 blocks de 32px (según la escala) de alto */
        public static int TILES_SIZE = (int) (TILE_DEFAULT_SIZE * SCALE);

        public static float GRAVITY = 0.025f * SCALE;

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
        public static int FLAME_X_DRAW_OFFSET = (int) (14 * 3 * SCALE / 2),
                FLAME_Y_DRAW_OFFSET = (int) (22 * 3 * SCALE / 2),
                FLAME_Y_DISTANCE_TO_TOUCH_FLOOR = (int) (8 * 3 * SCALE / 2);
        public static int FLAME_SPRITE_WIDTH = (int) (64 * 3 * SCALE / 2),
                FLAME_SPRITE_HEIGHT = (int) (64 * 3 * SCALE / 2);
        public static int FLAME_REAL_WIDTH = (int) (36 * 3 * SCALE / 2),
                FLAME_REAL_HEIGHT = (int) (35 * 3 * SCALE / 2);

        public static int RING_X_DRAW_OFFSET = (int) (70 * SCALE),
                RING_Y_DRAW_OFFSET = (int) (10 * SCALE);
        public static int RING_SPRITE_WIDTH = (int) (192 * SCALE),
                RING_SPRITE_HEIGHT = (int) (192 * SCALE);
        public static int RING_REAL_WIDTH = (int) (35 * SCALE),
                RING_REAL_HEIGHT = (int) (20 * SCALE);

        public static int SMALL_RING_X_DRAW_OFFSET = (int) (74 * 3 * SCALE / 4),
                SMALL_RING_Y_DRAW_OFFSET = (int) (10 * 3 * SCALE / 4);
        public static int SMALL_RING_SPRITE_WIDTH = (int) (192 * 3 * SCALE / 4),
                SMALL_RING_SPRITE_HEIGHT = (int) (192 * 3 * SCALE / 4);
        public static int SMALL_RING_REAL_WIDTH = (int) (30 * 3 * SCALE / 4),
                SMALL_RING_REAL_HEIGHT = (int) (20 * 3 * SCALE / 4);

        public static int TRAMPOLINE_X_DRAW_OFFSET = (int) (0 * SCALE),
                TRAMPOLINE_Y_DRAW_OFFSET = (int) (0 * SCALE);
        public static int TRAMPOLINE_SPRITE_WIDTH = (int) (32 * SCALE),
                TRAMPOLINE_SPRITE_HEIGHT = (int) (32 * SCALE);
        public static int TRAMPOLINE_REAL_WIDTH = (int) (32 * SCALE),
                TRAMPOLINE_REAL_HEIGHT = (int) (32 * SCALE);

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
        public static int BG_LOSE_OVERLAY_WIDTH = (int) (FRAME_WIDTH / 2),
                BG_LOSE_OVERLAY_HEIGHT = (int) (FRAME_HEIGHT * 3 / 4);

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

        public static int SQUARE_BUTTON_SIZE = (int) (75 * SCALE);
        public static int CONFIGURATION_BUTTON_SIZE = (int) (56 * SCALE);

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
        public static int FRAME_WIDTH = 1280;
        public static int FRAME_HEIGHT = 720;
        public static final int FPS_SET = 144;
        public static final int UPS_SET = 240;

    }

    public static class Directions {
        public static final int RIGHT = 0;
        public static final int LEFT = 1;
    }

    public static class PongConstants {
        // Pelota
        public static final int BALL_SIZE = (int) (15 * SCALE);
        public static final int BALL_SPEED = (int) (1 * SCALE);
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
        public static final int PLAYER_SPEED = (int) (5 * SCALE);
        public static final int PLAYER_HEIGHT = (int) (80 * SCALE);
        public static final int PLAYER_WIDTH = (int) (20 * SCALE);
        public static final int PLAYER_PADDING = (int) (20 * SCALE);

        public static final int PLAYER_1_START = 0;
        public static final int PLAYER_2_START = FRAME_WIDTH - PLAYER_WIDTH;
        public static final int PLAYER_START = (FRAME_HEIGHT / 2) - (PLAYER_HEIGHT / 2);
    }

}
