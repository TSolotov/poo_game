package Entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import levels.Level;
import states.CircusPlaying;
import utils.LoadSprites;
import utils.Constants.EnemyConstants;

public class EnemyHandler {
    private CircusPlaying circusPlaying;

    // * Tiene todas las animaciones de las bombas
    private ArrayList<BufferedImage[]> bombSprites;
    private ArrayList<BufferedImage[]> chickenSprites;
    private ArrayList<BombEnemy> bombs = new ArrayList<>();
    private ArrayList<ChickenEnemy> chickens = new ArrayList<>();

    public EnemyHandler(CircusPlaying circusPlaying) {
        this.circusPlaying = circusPlaying;
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
    }

    public void addEnemies(Level level) {
        bombs = BombEnemy.getBombs(level);
        chickens = ChickenEnemy.getChickens(level);
    }

    // ! Update y Draw
    public void update(int[][] levelData, Player1 player) {
        for (BombEnemy bomb : bombs) {
            bomb.update(levelData, player);
        }
        for (ChickenEnemy chicken : chickens) {
            chicken.update(levelData, player);
        }
    }

    public void draw(Graphics g, int xLevelOffset) {
        drawBombs(g, xLevelOffset);
        drawChickens(g, xLevelOffset);
    }

    private void drawBombs(Graphics g, int xLevelOffset) {
        for (BombEnemy bomb : bombs) {
            g.drawImage(bombSprites.get(bomb.getEnemyAction())[bomb.getAniIndex()],
                    (int) bomb.getHitbox().getX() - xLevelOffset + bomb.flipX(),
                    (int) (bomb.getHitbox().getY() - EnemyConstants.BOMB_Y_DRAW_OFFSET),
                    EnemyConstants.BOMB_SPRITE_WIDTH * bomb.flipW(),
                    EnemyConstants.BOMB_SPRITE_HEIGHT, null);

            bomb.drawHitbox(g, xLevelOffset);
            bomb.drawJumpBox(g, xLevelOffset);
        }

    }

    private void drawChickens(Graphics g, int xLevelOffset) {
        int action = 0;
        for (ChickenEnemy chicken : chickens) {
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
