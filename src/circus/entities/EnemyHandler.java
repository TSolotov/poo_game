package circus.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import circus.levels.Level;
import utils.LoadSprites;
import utils.Constants.EnemyConstants;

public class EnemyHandler {

    // * Tiene todas las animaciones de las bombas
    private ArrayList<BufferedImage[]> bombSprites;
    private ArrayList<BufferedImage[]> chickenSprites;
    private ArrayList<BufferedImage[]> horseSprites;
    private ArrayList<BombEnemy> bombs = new ArrayList<>();
    private ArrayList<ChickenEnemy> chickens = new ArrayList<>();
    private Horse horse;

    public EnemyHandler() {
        loadSprites();
    }

    private void loadSprites() {
        bombSprites = new ArrayList<>();
        bombSprites.addLast(LoadSprites.getSprites(EnemyConstants.getEnemySpritesInfo(EnemyConstants.BOMB_RUNNING)));
        bombSprites.addLast(LoadSprites.getSprites(EnemyConstants.getEnemySpritesInfo(EnemyConstants.BOMB_JUMP)));
        bombSprites.addLast(LoadSprites.getSprites(EnemyConstants.getEnemySpritesInfo(EnemyConstants.BOMB_FALLING)));

        chickenSprites = new ArrayList<>();
        chickenSprites.addLast(LoadSprites.getSprites(EnemyConstants.getEnemySpritesInfo(EnemyConstants.CHICKEN_WALK)));
        chickenSprites
                .addLast(LoadSprites.getSprites(EnemyConstants.getEnemySpritesInfo(EnemyConstants.CHICKEN_FALLING)));

        horseSprites = new ArrayList<>();
        horseSprites.addLast(LoadSprites.getSprites(EnemyConstants.getEnemySpritesInfo(EnemyConstants.HORSE_WALK)));
        horseSprites.addLast(LoadSprites.getSprites(EnemyConstants.getEnemySpritesInfo(EnemyConstants.HORSE_RUN)));
    }

    public void addEnemies(Level level) {
        bombs = BombEnemy.getBombs(level);
        chickens = ChickenEnemy.getChickens(level);
        horse = Horse.getHorse(level);
    }

    // ! Update y Draw
    public void update(int[][] levelData, Hero player) {
        for (BombEnemy bomb : bombs) {
            if (bomb.isActive()) {
                bomb.update(levelData, player);
            }
        }
        for (ChickenEnemy chicken : chickens) {
            if (chicken.isActive()) {
                chicken.update(levelData, player);
            }
        }

        horse.update(player);

    }

    public void draw(Graphics g, int xLevelOffset) {
        drawBombs(g, xLevelOffset);
        drawChickens(g, xLevelOffset);
        drawHorse(g, xLevelOffset);
    }

    private void drawBombs(Graphics g, int xLevelOffset) {
        for (BombEnemy bomb : bombs) {
            if (bomb.isActive()) {
                g.drawImage(bombSprites.get(bomb.getEnemyAction())[bomb.getAniIndex()],
                        (int) bomb.getHitbox().getX() - xLevelOffset + bomb.flipX(),
                        (int) (bomb.getHitbox().getY() - EnemyConstants.BOMB_Y_DRAW_OFFSET),
                        EnemyConstants.BOMB_SPRITE_WIDTH * bomb.flipW(),
                        EnemyConstants.BOMB_SPRITE_HEIGHT, null);
                bomb.drawHitbox(g, xLevelOffset);
                bomb.drawJumpBox(g, xLevelOffset);
            }
        }

    }

    private void drawChickens(Graphics g, int xLevelOffset) {
        int action = 0;
        for (ChickenEnemy chicken : chickens) {
            if (chicken.isActive()) {
                switch (chicken.getEnemyAction()) {
                    case EnemyConstants.CHICKEN_WALK:
                        action = 0;
                        break;
                    case EnemyConstants.CHICKEN_FALLING:
                        action = 1;
                        break;
                }
                g.drawImage(chickenSprites.get(action)[chicken.getAniIndex()],
                        (int) chicken.getHitbox().getX() - xLevelOffset + chicken.flipX(),
                        (int) (chicken.getHitbox().getY() - EnemyConstants.CHICKEN_Y_DRAW_OFFSET),
                        EnemyConstants.CHICKEN_SPRITE_WIDTH * chicken.flipW(),
                        EnemyConstants.CHICKEN_SPRITE_HEIGHT, null);
                chicken.drawHitbox(g, xLevelOffset);
            }
        }

    }

    private void drawHorse(Graphics g, int xLevelOffset) {
        int action = 0;
        switch (horse.getHorseAction()) {
            case EnemyConstants.HORSE_WALK:
                action = 0;
                break;
            case EnemyConstants.HORSE_RUN:
                action = 1;
                break;
        }

        g.drawImage(horseSprites.get(action)[horse.getAniIndex()],
                (int) horse.getPosition().x - EnemyConstants.HORSE_REAL_WIDTH / 2 - xLevelOffset,
                (int) horse.getPosition().y - EnemyConstants.HORSE_Y_DRAW_OFFSET,
                EnemyConstants.HORSE_SPRITE_WIDTH,
                EnemyConstants.HORSE_SPRITE_HEIGHT, null);

        horse.drawHitbox(g, xLevelOffset);
    }

    public void resetEnemies() {
        for (BombEnemy bomb : bombs) {
            bomb.resetEnemy();
        }
        for (ChickenEnemy chicken : chickens) {
            chicken.resetEnemy();
        }
        horse.resetHorse();
    }

}
