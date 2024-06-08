package states;

import audio.AudioPlayer;
import circus.levels.LevelHandler;
import main.GameSystem;
import utils.CSVFile;

import java.util.ArrayList;

public class State {
    protected GameSystem game;

    // Creo los CSV
    protected CSVFile csvCircus, csvPong;
    protected String fileCSVCircus = "dataCircus.csv", fileCSVPong = "dataPong.csv";
    protected ArrayList<String[]> allCircusData, allPongData;

    public State(GameSystem game) {
        this.game = game;

        csvCircus = new CSVFile(fileCSVCircus);
        csvPong = new CSVFile(fileCSVPong);
        allCircusData = csvCircus.readCSV();
        allPongData = csvPong.readCSV();
    }

    public GameSystem getGame() {
        return game;
    }

    public void setGamestate(GameState state) {
        switch (state) {
            case MENU:
                game.getAudioPlayer().stopMusic();
                game.getAudioPlayer().playMusic(AudioPlayer.MAIN_MENU);
                break;
            case CIRCUS_GAME:
                game.getAudioPlayer().stopMusic();
                game.getAudioPlayer().setMusic(LevelHandler.getNumberLevel());
                break;
            case PONG_GAME:
                game.getAudioPlayer().stopMusic();
                game.getAudioPlayer().playMusic(AudioPlayer.PONG);
            default:
                break;
        }

        GameState.state = state;
    }

    public String getFileCSVCircus() {
        return fileCSVCircus;
    }

    public String getFileCSVPong() {
        return fileCSVPong;
    }

    public CSVFile getCsvCircus() {
        return csvCircus;
    }

    public CSVFile getCsvPong() {
        return csvPong;
    }
}
