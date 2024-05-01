package levels;

import static utils.Constants.CircusConstants.*;

public class Level {
    private int[][] levelData;

    private int levelTilesWidth;
    private int maxTilesOffset;
    private int maxLevelOffsetX;

    public Level(int[][] levelData) {
        this.levelData = levelData;
        calculateLevelsOffest();
    }

    // * Calcula el offset del level, necesario para el mov del bg
    private void calculateLevelsOffest() {
        levelTilesWidth = levelData[0].length;
        maxTilesOffset = levelTilesWidth - TILES_WIDTH;
        maxLevelOffsetX = maxTilesOffset * TILES_SIZE;

    }

    // * Devuelve el valor [x][y] de la matriz para en base a las tiles definidas
    public int getTileToDraw(int x, int y) {
        return levelData[x][y];
    }

    // * Largo del mapa
    public int getLevelWidth() {
        return levelData[0].length;
    }

    // * Alto del mapa
    public int getLevelHeight() {
        return levelData.length;
    }

    // * Retorna el level pero en formato int[][]
    public int[][] getLevelData() {
        return levelData;
    }

    // ? Getter
    public int getLevelOffsetX() {
        return maxLevelOffsetX;
    }
}
