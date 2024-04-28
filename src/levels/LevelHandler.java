package levels;

import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Game;
import utils.Constants;
import utils.LevelsCreation;
import utils.LoadSprites;

import static utils.Constants.CircusConstants.*;

public class LevelHandler {
    private Game game;
    private BufferedImage[] tiles;

    // * Acá irán todos los levels
    private ArrayList<Level> levels;

    private static int currentLevel = 0;

    public LevelHandler(Game game) {
        this.game = game;
        levels = new ArrayList<>();
        buildAllLevels();

        tiles = LoadSprites.getSprites(Constants.CircusConstants.getSpritesInfo(TILES_CIRCUS));

    }

    // * Se encarga de meter todos los levels y ya con el indesLevel seleccionamos
    private void buildAllLevels() {
        LevelsCreation.addAllLevels(levels);
    }

    // * Obtiene el nivel actual
    public Level getCurrentLevel() {
        return levels.get(currentLevel);
    }

    // ! Draw & Update
    public void draw(Graphics g, int xLevelOffset) {
        for (int i = 0; i < levels.get(currentLevel).getLevelHeight(); i++) {
            for (int j = 0; j < levels.get(currentLevel).getLevelWidth(); j++) {
                int index = levels.get(currentLevel).getTileToDraw(i, j);
                if (index == LevelsCreation.VOID)
                    continue; // * no pinta ningun sprite si es que es vacío
                g.drawImage(tiles[index], TILES_SIZE * j - xLevelOffset, TILES_SIZE * i, TILES_SIZE, TILES_SIZE, null);
            }
        }
    }

}
