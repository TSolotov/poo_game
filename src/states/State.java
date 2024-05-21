package states;

import audio.AudioPlayer;
import circusLevels.LevelHandler;
import main.Game;

public class State {
    protected Game game;

    public State(Game game) {
        this.game = game;
    }

    public Game getGame() {
        return game;
    }

    public void setGamestate(GameState state) {
        switch (state) {
            case MENU:
                game.getAudioPlayer().playMusic(AudioPlayer.MAIN_MENU);
                break;
            case CIRCUS_PLAYING:
                game.getAudioPlayer().setMusic(LevelHandler.getNumberLevel());
                break;
            case PONG_PLAYING:
                game.getAudioPlayer().playMusic(AudioPlayer.PONG);
            default:
                break;
        }

        GameState.state = state;
    }
}
