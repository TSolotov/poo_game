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
    private ArrayList<BombEnemy> bombs = new ArrayList<>();

    public EnemyHandler(CircusPlaying circusPlaying) {
        this.circusPlaying = circusPlaying;
        loadBombSprites();
    }

    private void loadBombSprites() {
        bombSprites = new ArrayList<>();
        bombSprites.addLast(LoadSprites.getSprites(EnemyConstants.getEnemySpritesInfo(EnemyConstants.BOMB_RUNNING)));
        bombSprites.addLast(LoadSprites.getSprites(EnemyConstants.getEnemySpritesInfo(EnemyConstants.BOMB_JUMP)));
        bombSprites.addLast(LoadSprites.getSprites(EnemyConstants.getEnemySpritesInfo(EnemyConstants.BOMB_FALLING)));
    }

    public void addEnemies(Level level) {
        bombs = BombEnemy.getBombs(level);
    }

    // ! Update y Draw
    public void update(int[][] levelData, Player1 player) {
        for (BombEnemy bomb : bombs) {
            bomb.update(levelData, player);
        }
    }

    public void draw(Graphics g, int xLevelOffset) {
        drawBombs(g, xLevelOffset);
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
}
