package states;

import audio.AudioPlayer;
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
                game.getAudioPlayer().setMusic(game.getPlaying().getLevelHandler().getNumberLevel());
                break;
            default:
                break;
        }

        GameState.state = state;
    }
}
