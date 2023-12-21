package View;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class SoundPlayer {

    String soundFileName = "sounds/flipcard-91468.mp3";

    public void playSound(String soundFilename){
        try {
            AudioInputStream a = AudioSystem.getAudioInputStream(new File(soundFileName));
            Clip clip = AudioSystem.getClip();
            clip.open(a);
            clip.start();

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }

    }


}
///resources/flipcard-91468.mp3