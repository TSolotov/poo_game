package utils;

import java.util.ArrayList;

import levels.Level;

public class LevelsCreation {
        // * Tiles constants
        public static final int VOID = -1, DIRT = 0, GRAS = 1;

        public static void addAllLevels(ArrayList<Level> levels) {
                levels.add(level1());

        }

        private static Level level1() {
                int[][] level1 = {
                                { VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID },
                                { VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID },
                                { VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID },
                                { VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID },
                                { VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID },
                                { VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID },
                                { VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID },
                                { VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID },
                                { VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID },
                                { VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID },
                                { VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID },
                                { VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID },
                                { VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID },
                                { VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID },
                                { VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID },
                                { VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID },
                                { VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID },
                                { VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, GRAS, GRAS, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID },
                                { VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, DIRT, DIRT, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID },
                                { VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID },
                                { VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID, VOID,
                                                VOID, VOID },
                                { GRAS, GRAS, GRAS, GRAS, GRAS, GRAS, GRAS, GRAS, GRAS, GRAS, GRAS, GRAS, GRAS, GRAS,
                                                GRAS, GRAS, GRAS, GRAS, GRAS, GRAS, GRAS, GRAS, GRAS, GRAS, GRAS, GRAS,
                                                GRAS, GRAS, GRAS, GRAS, GRAS, GRAS, GRAS, GRAS, GRAS, GRAS, GRAS, GRAS,
                                                GRAS, GRAS, GRAS, GRAS, GRAS, GRAS, GRAS, GRAS, GRAS, GRAS, GRAS, GRAS,
                                                GRAS, GRAS, GRAS, GRAS, GRAS, GRAS, GRAS, GRAS, GRAS, GRAS, GRAS, GRAS,
                                                GRAS, GRAS, GRAS, GRAS, GRAS, GRAS, GRAS, GRAS, GRAS, GRAS, GRAS, GRAS,
                                                GRAS, GRAS, GRAS, GRAS, GRAS, GRAS, GRAS, GRAS, GRAS, GRAS, GRAS, GRAS,
                                                GRAS, GRAS, GRAS, GRAS, GRAS, GRAS, GRAS, GRAS, GRAS, GRAS, GRAS, GRAS,
                                                GRAS, GRAS },
                                { DIRT, DIRT, DIRT, DIRT, DIRT, DIRT, DIRT, DIRT, DIRT, DIRT, DIRT, DIRT, DIRT, DIRT,
                                                DIRT, DIRT, DIRT, DIRT, DIRT, DIRT, DIRT, DIRT, DIRT, DIRT, DIRT, DIRT,
                                                DIRT, DIRT, DIRT, DIRT, DIRT, DIRT, DIRT, DIRT, DIRT, DIRT, DIRT, DIRT,
                                                DIRT, DIRT, DIRT, DIRT, DIRT, DIRT, DIRT, DIRT, DIRT, DIRT, DIRT, DIRT,
                                                DIRT, DIRT, DIRT, DIRT, DIRT, DIRT, DIRT, DIRT, DIRT, DIRT, DIRT, DIRT,
                                                DIRT, DIRT, DIRT, DIRT, DIRT, DIRT, DIRT, DIRT, DIRT, DIRT, DIRT, DIRT,
                                                DIRT, DIRT, DIRT, DIRT, DIRT, DIRT, DIRT, DIRT, DIRT, DIRT, DIRT, DIRT,
                                                DIRT, DIRT, DIRT, DIRT, DIRT, DIRT, DIRT, DIRT, DIRT, DIRT, DIRT, DIRT,
                                                DIRT, DIRT },
                                { DIRT, DIRT, DIRT, DIRT, DIRT, DIRT, DIRT, DIRT, DIRT, DIRT, DIRT, DIRT, DIRT, DIRT,
                                                DIRT, DIRT, DIRT, DIRT, DIRT, DIRT, DIRT, DIRT, DIRT, DIRT, DIRT, DIRT,
                                                DIRT, DIRT, DIRT, DIRT, DIRT, DIRT, DIRT, DIRT, DIRT, DIRT, DIRT, DIRT,
                                                DIRT, DIRT, DIRT, DIRT, DIRT, DIRT, DIRT, DIRT, DIRT, DIRT, DIRT, DIRT,
                                                DIRT, DIRT, DIRT, DIRT, DIRT, DIRT, DIRT, DIRT, DIRT, DIRT, DIRT, DIRT,
                                                DIRT, DIRT, DIRT, DIRT, DIRT, DIRT, DIRT, DIRT, DIRT, DIRT, DIRT, DIRT,
                                                DIRT, DIRT, DIRT, DIRT, DIRT, DIRT, DIRT, DIRT, DIRT, DIRT, DIRT, DIRT,
                                                DIRT, DIRT, DIRT, DIRT, DIRT, DIRT, DIRT, DIRT, DIRT, DIRT, DIRT, DIRT,
                                                DIRT, DIRT },
                };

                return new Level(level1);
        }

}