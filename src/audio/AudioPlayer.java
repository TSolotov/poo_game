package audio;

import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.BooleanControl;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class AudioPlayer {
    public static int RUN = 0, JUMP = 1, DIE = 2, PAUSE = 3, LVL_COMPLETED = 3, GAME_OVER = 4;

    public static int MAIN_MENU = 0, LEVEL_1 = 1, LEVEL_2 = 2, LEVEL_3 = 3;

    private Clip[] musics, sounds;
    private int currentMusicID = MAIN_MENU;
    private boolean musicMute, soundMute;

    public AudioPlayer() {
        loadMusics();
        loadSounds();
        playMusic(MAIN_MENU);
    }

    private void loadMusics() {
        String[] names = { "main_menu", "level1", "level2", "level3" };
        musics = new Clip[names.length];
        for (int i = 0; i < names.length; i++) {
            musics[i] = getClip(names[i]);
        }
    }

    private void loadSounds() {
        String[] names = { "run", "jump", "die", "pause", "level_completed", "game_over" };
        sounds = new Clip[names.length];
        for (int i = 0; i < names.length; i++) {
            sounds[i] = getClip(names[i]);
        }

    }

    // * Se encarga de agarrar los audios con el nombre y la ruta especificada
    private Clip getClip(String name) {
        URL url = getClass().getResource("/resources/audio/" + name + ".wav");
        AudioInputStream audioStream;
        try {
            // * Obtener el AudioInputStream original
            audioStream = AudioSystem.getAudioInputStream(url);

            // * Obtener el formato del AudioInputStream original
            AudioFormat originalFormat = audioStream.getFormat();

            // * Crear un nuevo formato de audio (opcional)
            AudioFormat targetFormat = new AudioFormat(
                    AudioFormat.Encoding.PCM_SIGNED,
                    originalFormat.getSampleRate(), // * Frecuencia de muestreo
                    16, // * Resolución de bits
                    originalFormat.getChannels(), // * Número de canales
                    originalFormat.getChannels() * 2, // * Tamaño del frame (bytes)
                    originalFormat.getSampleRate(), // * Frecuencia de fotogramas
                    false // * Endianess (little-endian en este caso)
            );

            // * Crear un nuevo AudioInputStream con el formato deseado
            AudioInputStream formattedStream = AudioSystem.getAudioInputStream(targetFormat, audioStream);

            // * Abrir el Clip con el AudioInputStream modificado
            Clip clip = AudioSystem.getClip();
            clip.open(formattedStream);

            return clip;

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void togleMusicMute() {
        this.musicMute = !musicMute;
        for (Clip clip : musics) {
            BooleanControl booleanControl = (BooleanControl) clip.getControl(BooleanControl.Type.MUTE);
            booleanControl.setValue(musicMute);
        }
    }

    public void togleSoundsMute() {
        this.soundMute = !soundMute;
        for (Clip clip : sounds) {
            BooleanControl booleanControl = (BooleanControl) clip.getControl(BooleanControl.Type.MUTE);
            booleanControl.setValue(soundMute);
        }
    }

    public void playMusic(int music) {
        stopMusic();
        currentMusicID = music;
        musics[currentMusicID].setMicrosecondPosition(0);
        musics[currentMusicID].loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void playSounds(int sound) {
        sounds[sound].setMicrosecondPosition(0);
        sounds[sound].start();
    }

    public void stopMusic() {
        if (musics[currentMusicID].isActive())
            musics[currentMusicID].stop();
    }

    public void setMusic(int lvlIndex) {
        switch (lvlIndex) {
            case 0:
                playMusic(LEVEL_1);
                break;
            case 1:
                playMusic(LEVEL_2);
                break;
            case 2:
                playMusic(LEVEL_3);
                break;
            default:
                break;
        }
    }

    public void levelCompleted() {
        stopMusic();
        playSounds(LVL_COMPLETED);
    }

}
