package circus.levels;

import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import circus.entities.EnemyHandler;
import circus.entities.Hero;
import circus.objects.ObjectHandler;
import main.GameSystem;
import utils.Constants;
import utils.LevelsCreation;
import utils.LoadSprites;

import static utils.Constants.CircusConstants.*;

public class LevelHandler {
    private GameSystem game;
    private BufferedImage[] tiles;

    // * Acá irán todos los circus.levels
    private ArrayList<Level> levels;

    private static int currentLevel = 0;

    private EnemyHandler enemyHandler;
    private ObjectHandler objectHandler;

    public LevelHandler(GameSystem game) {
        this.game = game;
        init();
    }

    private void init() {
        enemyHandler = new EnemyHandler();
        objectHandler = new ObjectHandler();

        levels = new ArrayList<>();
        LevelsCreation.addAllLevels(levels);
        tiles = LoadSprites.getSprites(Constants.CircusConstants.getSpritesInfo(TILES_CIRCUS));

        enemyHandler.addEnemies(getCurrentLevel());
        objectHandler.addObjects(getCurrentLevel());
    }

    public void loadNextLevel() {
        currentLevel++;

        if (currentLevel >= getCantLevels()) {
            currentLevel = 0;
            // TODO - LevelCompleted overlay
        }

        Level newLevel = levels.get(currentLevel);
        enemyHandler.addEnemies(newLevel);
        objectHandler.addObjects(newLevel);
        game.getCircusGame().getPlayer().loadLevelData(newLevel.getLevelData());
        game.getCircusGame().setMaxLevel1OffsetX(newLevel.getLevelOffsetX());
        game.getAudioPlayer().setMusic(currentLevel);
    }

    // ! Draw & Update
    public void update(Hero player) {
        enemyHandler.update(getCurrentLevel().getLevelData(), player);
        objectHandler.update(player);
    }

    public void draw(Graphics g, int xLevelOffset) {
        drawTiles(g, xLevelOffset);
        enemyHandler.draw(g, xLevelOffset);
        objectHandler.draw(g, xLevelOffset);
    }

    private void drawTiles(Graphics g, int xLevelOffset) {
        for (int i = 0; i < levels.get(currentLevel).getLevelHeight(); i++) {
            for (int j = 0; j < levels.get(currentLevel).getLevelWidth(); j++) {
                int index = levels.get(currentLevel).getTileToDraw(i, j);
                if (index == LevelsCreation.VOID || index >= 30)
                    continue; // * no pinta ningun sprite si es que es vacío
                g.drawImage(tiles[index], (TILES_SIZE * j - xLevelOffset), TILES_SIZE * i, TILES_SIZE, TILES_SIZE,
                        null);
            }
        }
    }

    // * Getters & Setters
    public Level getCurrentLevel() {
        return levels.get(currentLevel);
    }

    public static int getNumberLevel() {
        return currentLevel;
    }

    public int getCantLevels() {
        return levels.size();
    }

    public void resetCurrentLevel() {
        currentLevel = 0;
        Level newLevel = levels.get(currentLevel);
        enemyHandler.addEnemies(newLevel);
        objectHandler.addObjects(newLevel);
        game.getCircusGame().getPlayer().loadLevelData(newLevel.getLevelData());
        game.getCircusGame().setMaxLevel1OffsetX(newLevel.getLevelOffsetX());
        game.getAudioPlayer().setMusic(currentLevel);
        enemyHandler.resetEnemies();
        objectHandler.resetObjects();
    }

    public EnemyHandler getEnemyHandler() {
        return enemyHandler;
    }

    public ObjectHandler getObjectHandler() {
        return objectHandler;
    }
}
